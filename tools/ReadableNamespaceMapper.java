import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.tree.analysis.*;

/**
 * Bidirectional bytecode remapper between legacy-readable (C_*, field_*, method_*) and 100% readable symbols,
 * with optional ZKM ABI parameter stripping (forward, header stubs or --strip-bodies) and constant/slot restoration (reverse).
 */
public final class ReadableNamespaceMapper {
    static final class LegMember {
        final String legOwner;
        final String legName;
        final String legDesc;
        final String readDesc;

        LegMember(String legOwner, String legName, String legDesc, String readDesc) {
            this.legOwner = legOwner;
            this.legName = legName;
            this.legDesc = legDesc;
            this.readDesc = readDesc;
        }
    }

    static final class ZkmRule {
        final String type; // DECL_STRIP or CALL_STRIP
        final String legOwner;
        final String legName;
        final String legDesc;
        final int[] strippedIndices;
        final Map<Integer, Object> canonicalConstants = new HashMap<Integer, Object>();

        String readOwner;
        String readName;
        String origReadDesc;
        String cleanReadDesc;

        ZkmRule(String type, String legOwner, String legName, String legDesc, int[] strippedIndices, String constStr) {
            this.type = type;
            this.legOwner = legOwner;
            this.legName = legName;
            this.legDesc = legDesc;
            this.strippedIndices = strippedIndices;
            if (constStr != null && !constStr.isEmpty()) {
                parseConstants(constStr, canonicalConstants);
            }
        }
    }

    static final class ZkmOverride {
        final String legOwner;
        final String legName;
        final String legDesc;
        final String callerClass;
        final String callerMethod;
        final String callerDesc;
        final int ordinal;
        final Map<Integer, Object> constants = new HashMap<Integer, Object>();

        ZkmOverride(
            String legOwner,
            String legName,
            String legDesc,
            String callerClass,
            String callerMethod,
            String callerDesc,
            int ordinal,
            String constStr
        ) {
            this.legOwner = legOwner;
            this.legName = legName;
            this.legDesc = legDesc;
            this.callerClass = callerClass;
            this.callerMethod = callerMethod;
            this.callerDesc = callerDesc;
            this.ordinal = ordinal;
            if (constStr != null && !constStr.isEmpty()) {
                parseConstants(constStr, constants);
            }
        }
    }

    static final class ConstVal implements Value {
        static final int KIND_UNKNOWN = 0;
        static final int KIND_CONST = 1;
        static final int KIND_ENTRY_PARAM = 2;
        static final int KIND_PURE_KEY = 3;

        final int size;
        final int kind;
        final long numVal;
        final boolean isNull;
        final int entrySlot;
        final long entrySlotMask;

        ConstVal(int size, int kind, long numVal, boolean isNull, int entrySlot, long entrySlotMask) {
            this.size = size;
            this.kind = kind;
            this.numVal = numVal;
            this.isNull = isNull;
            this.entrySlot = entrySlot;
            this.entrySlotMask = entrySlotMask;
        }

        static ConstVal unknown(int size) {
            return new ConstVal(size, KIND_UNKNOWN, 0L, false, -1, -1L);
        }

        static ConstVal constant(int size, long val) {
            return new ConstVal(size, KIND_CONST, val, false, -1, 0L);
        }

        static ConstVal nullConst() {
            return new ConstVal(1, KIND_CONST, 0L, true, -1, 0L);
        }

        static ConstVal entryParam(int size, int slot) {
            long mask = (slot >= 0 && slot < 62) ? (1L << slot) : -1L;
            return new ConstVal(size, KIND_ENTRY_PARAM, 0L, false, slot, mask);
        }

        static ConstVal pureKey(int size, long mask) {
            if (mask < 0L) return unknown(size);
            return new ConstVal(size, KIND_PURE_KEY, 0L, false, -1, mask);
        }

        boolean isPureNumeric() {
            return (kind == KIND_CONST && !isNull)
                || (kind == KIND_ENTRY_PARAM && entrySlotMask >= 0L)
                || (kind == KIND_PURE_KEY && entrySlotMask >= 0L);
        }

        @Override
        public int getSize() {
            return size;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ConstVal)) return false;
            ConstVal other = (ConstVal) o;
            return size == other.size
                && kind == other.kind
                && numVal == other.numVal
                && isNull == other.isNull
                && entrySlot == other.entrySlot
                && entrySlotMask == other.entrySlotMask;
        }

        @Override
        public int hashCode() {
            int h = size * 31 + kind;
            h = h * 31 + (int) (numVal ^ (numVal >>> 32));
            h = h * 31 + (isNull ? 1 : 0);
            h = h * 31 + entrySlot;
            h = h * 31 + (int) (entrySlotMask ^ (entrySlotMask >>> 32));
            return h;
        }
    }

    static final class ConstInterpreter extends Interpreter<ConstVal> {
        private final boolean isStatic;
        private int nextInitSlot = 0;

        ConstInterpreter(boolean isStatic) {
            super(Opcodes.ASM9);
            this.isStatic = isStatic;
        }

        @Override
        public ConstVal newValue(Type type) {
            if (type == Type.VOID_TYPE) return null;
            int sz = (type == null) ? 1 : type.getSize();
            return ConstVal.unknown(sz);
        }

        @Override
        public ConstVal newParameterValue(boolean isInstanceMethod, int local, Type type) {
            int sz = type.getSize();
            if (isInstanceMethod && local == 0) {
                return ConstVal.unknown(1);
            }
            return ConstVal.entryParam(sz, local);
        }

        @Override
        public ConstVal newOperation(AbstractInsnNode insn) {
            int op = insn.getOpcode();
            switch (op) {
                case Opcodes.ACONST_NULL:
                    return ConstVal.nullConst();
                case Opcodes.ICONST_M1:
                case Opcodes.ICONST_0:
                case Opcodes.ICONST_1:
                case Opcodes.ICONST_2:
                case Opcodes.ICONST_3:
                case Opcodes.ICONST_4:
                case Opcodes.ICONST_5:
                    return ConstVal.constant(1, op - Opcodes.ICONST_0);
                case Opcodes.LCONST_0:
                    return ConstVal.constant(2, 0L);
                case Opcodes.LCONST_1:
                    return ConstVal.constant(2, 1L);
                case Opcodes.FCONST_0:
                case Opcodes.FCONST_1:
                case Opcodes.FCONST_2:
                    return ConstVal.constant(1, Float.floatToRawIntBits((float) (op - Opcodes.FCONST_0)));
                case Opcodes.DCONST_0:
                    return ConstVal.constant(2, Double.doubleToRawLongBits(0.0));
                case Opcodes.DCONST_1:
                    return ConstVal.constant(2, Double.doubleToRawLongBits(1.0));
                case Opcodes.BIPUSH:
                case Opcodes.SIPUSH:
                    return ConstVal.constant(1, ((IntInsnNode) insn).operand);
                case Opcodes.LDC: {
                    Object cst = ((LdcInsnNode) insn).cst;
                    if (cst instanceof Integer) return ConstVal.constant(1, ((Integer) cst).intValue());
                    if (cst instanceof Short) return ConstVal.constant(1, ((Short) cst).shortValue());
                    if (cst instanceof Byte) return ConstVal.constant(1, ((Byte) cst).byteValue());
                    if (cst instanceof Character) return ConstVal.constant(1, ((Character) cst).charValue());
                    if (cst instanceof Boolean) return ConstVal.constant(1, ((Boolean) cst).booleanValue() ? 1 : 0);
                    if (cst instanceof Long) return ConstVal.constant(2, ((Long) cst).longValue());
                    if (cst instanceof Float) return ConstVal.constant(1, Float.floatToRawIntBits(((Float) cst).floatValue()));
                    if (cst instanceof Double) return ConstVal.constant(2, Double.doubleToRawLongBits(((Double) cst).doubleValue()));
                    return ConstVal.constant(1, 0L);
                }
                case Opcodes.GETSTATIC:
                    return ConstVal.unknown(Type.getType(((FieldInsnNode) insn).desc).getSize());
                default:
                    return ConstVal.unknown(1);
            }
        }

        @Override
        public ConstVal copyOperation(AbstractInsnNode insn, ConstVal value) {
            return value;
        }

        @Override
        public ConstVal unaryOperation(AbstractInsnNode insn, ConstVal v) {
            int op = insn.getOpcode();
            boolean known = (v.kind == ConstVal.KIND_CONST && !v.isNull);
            boolean pure = v.isPureNumeric();
            switch (op) {
                case Opcodes.INEG:
                    return known ? ConstVal.constant(1, -((int) v.numVal)) : (pure ? ConstVal.pureKey(1, v.entrySlotMask) : ConstVal.unknown(1));
                case Opcodes.LNEG:
                    return known ? ConstVal.constant(2, -v.numVal) : (pure ? ConstVal.pureKey(2, v.entrySlotMask) : ConstVal.unknown(2));
                case Opcodes.I2L:
                    return known ? ConstVal.constant(2, (long) ((int) v.numVal)) : (pure ? ConstVal.pureKey(2, v.entrySlotMask) : ConstVal.unknown(2));
                case Opcodes.I2B:
                    return known ? ConstVal.constant(1, (byte) ((int) v.numVal)) : (pure ? ConstVal.pureKey(1, v.entrySlotMask) : ConstVal.unknown(1));
                case Opcodes.I2C:
                    return known ? ConstVal.constant(1, (char) ((int) v.numVal)) : (pure ? ConstVal.pureKey(1, v.entrySlotMask) : ConstVal.unknown(1));
                case Opcodes.I2S:
                    return known ? ConstVal.constant(1, (short) ((int) v.numVal)) : (pure ? ConstVal.pureKey(1, v.entrySlotMask) : ConstVal.unknown(1));
                case Opcodes.L2I:
                    return known ? ConstVal.constant(1, (int) v.numVal) : (pure ? ConstVal.pureKey(1, v.entrySlotMask) : ConstVal.unknown(1));
                case Opcodes.IINC:
                    return ConstVal.unknown(1);
                case Opcodes.I2D:
                case Opcodes.F2L:
                case Opcodes.F2D:
                case Opcodes.L2D:
                case Opcodes.DNEG:
                    return ConstVal.unknown(2);
                case Opcodes.IFEQ:
                case Opcodes.IFNE:
                case Opcodes.IFLT:
                case Opcodes.IFGE:
                case Opcodes.IFGT:
                case Opcodes.IFLE:
                case Opcodes.TABLESWITCH:
                case Opcodes.LOOKUPSWITCH:
                case Opcodes.IRETURN:
                case Opcodes.LRETURN:
                case Opcodes.FRETURN:
                case Opcodes.DRETURN:
                case Opcodes.ARETURN:
                case Opcodes.PUTSTATIC:
                case Opcodes.ATHROW:
                case Opcodes.MONITORENTER:
                case Opcodes.MONITOREXIT:
                case Opcodes.IFNULL:
                case Opcodes.IFNONNULL:
                    return null;
                case Opcodes.GETFIELD:
                    return ConstVal.unknown(Type.getType(((FieldInsnNode) insn).desc).getSize());
                default:
                    return ConstVal.unknown(1);
            }
        }

        @Override
        public ConstVal binaryOperation(AbstractInsnNode insn, ConstVal v1, ConstVal v2) {
            int op = insn.getOpcode();
            boolean known = (v1.kind == ConstVal.KIND_CONST && !v1.isNull && v2.kind == ConstVal.KIND_CONST && !v2.isNull);
            boolean pure = (v1.isPureNumeric() && v2.isPureNumeric());
            long pMask = pure ? (v1.entrySlotMask | v2.entrySlotMask) : -1L;
            switch (op) {
                case Opcodes.IADD: return known ? ConstVal.constant(1, (int) v1.numVal + (int) v2.numVal) : (pure ? ConstVal.pureKey(1, pMask) : ConstVal.unknown(1));
                case Opcodes.ISUB: return known ? ConstVal.constant(1, (int) v1.numVal - (int) v2.numVal) : (pure ? ConstVal.pureKey(1, pMask) : ConstVal.unknown(1));
                case Opcodes.IMUL: return known ? ConstVal.constant(1, (int) v1.numVal * (int) v2.numVal) : (pure ? ConstVal.pureKey(1, pMask) : ConstVal.unknown(1));
                case Opcodes.IAND: return known ? ConstVal.constant(1, (int) v1.numVal & (int) v2.numVal) : (pure ? ConstVal.pureKey(1, pMask) : ConstVal.unknown(1));
                case Opcodes.IOR:  return known ? ConstVal.constant(1, (int) v1.numVal | (int) v2.numVal) : (pure ? ConstVal.pureKey(1, pMask) : ConstVal.unknown(1));
                case Opcodes.IXOR: return known ? ConstVal.constant(1, (int) v1.numVal ^ (int) v2.numVal) : (pure ? ConstVal.pureKey(1, pMask) : ConstVal.unknown(1));
                case Opcodes.ISHL: return known ? ConstVal.constant(1, (int) v1.numVal << (int) v2.numVal) : (pure ? ConstVal.pureKey(1, pMask) : ConstVal.unknown(1));
                case Opcodes.ISHR: return known ? ConstVal.constant(1, (int) v1.numVal >> (int) v2.numVal) : (pure ? ConstVal.pureKey(1, pMask) : ConstVal.unknown(1));
                case Opcodes.IUSHR:return known ? ConstVal.constant(1, (int) v1.numVal >>> (int) v2.numVal) : (pure ? ConstVal.pureKey(1, pMask) : ConstVal.unknown(1));
                case Opcodes.LADD: return known ? ConstVal.constant(2, v1.numVal + v2.numVal) : (pure ? ConstVal.pureKey(2, pMask) : ConstVal.unknown(2));
                case Opcodes.LSUB: return known ? ConstVal.constant(2, v1.numVal - v2.numVal) : (pure ? ConstVal.pureKey(2, pMask) : ConstVal.unknown(2));
                case Opcodes.LMUL: return known ? ConstVal.constant(2, v1.numVal * v2.numVal) : (pure ? ConstVal.pureKey(2, pMask) : ConstVal.unknown(2));
                case Opcodes.LAND: return known ? ConstVal.constant(2, v1.numVal & v2.numVal) : (pure ? ConstVal.pureKey(2, pMask) : ConstVal.unknown(2));
                case Opcodes.LOR:  return known ? ConstVal.constant(2, v1.numVal | v2.numVal) : (pure ? ConstVal.pureKey(2, pMask) : ConstVal.unknown(2));
                case Opcodes.LXOR: return known ? ConstVal.constant(2, v1.numVal ^ v2.numVal) : (pure ? ConstVal.pureKey(2, pMask) : ConstVal.unknown(2));
                case Opcodes.LSHL: return known ? ConstVal.constant(2, v1.numVal << (int) v2.numVal) : (pure ? ConstVal.pureKey(2, pMask) : ConstVal.unknown(2));
                case Opcodes.LSHR: return known ? ConstVal.constant(2, v1.numVal >> (int) v2.numVal) : (pure ? ConstVal.pureKey(2, pMask) : ConstVal.unknown(2));
                case Opcodes.LUSHR:return known ? ConstVal.constant(2, v1.numVal >>> (int) v2.numVal) : (pure ? ConstVal.pureKey(2, pMask) : ConstVal.unknown(2));
                case Opcodes.LDIV:
                case Opcodes.LREM:
                case Opcodes.LALOAD:
                case Opcodes.DALOAD:
                case Opcodes.DADD:
                case Opcodes.DSUB:
                case Opcodes.DMUL:
                case Opcodes.DDIV:
                case Opcodes.DREM:
                    return ConstVal.unknown(2);
                case Opcodes.IF_ICMPEQ:
                case Opcodes.IF_ICMPNE:
                case Opcodes.IF_ICMPLT:
                case Opcodes.IF_ICMPGE:
                case Opcodes.IF_ICMPGT:
                case Opcodes.IF_ICMPLE:
                case Opcodes.IF_ACMPEQ:
                case Opcodes.IF_ACMPNE:
                case Opcodes.PUTFIELD:
                    return null;
                default:
                    return ConstVal.unknown(1);
            }
        }

        @Override
        public ConstVal ternaryOperation(AbstractInsnNode insn, ConstVal v1, ConstVal v2, ConstVal v3) {
            return null;
        }

        @Override
        public ConstVal naryOperation(AbstractInsnNode insn, List<? extends ConstVal> values) {
            int op = insn.getOpcode();
            if (op == Opcodes.MULTIANEWARRAY) return ConstVal.unknown(1);
            if (op == Opcodes.INVOKEDYNAMIC) {
                InvokeDynamicInsnNode indy = (InvokeDynamicInsnNode) insn;
                Type ret = Type.getReturnType(indy.desc);
                if (ret == Type.VOID_TYPE) return null;
                if (indy.bsm != null
                    && !"java/lang/invoke/LambdaMetafactory".equals(indy.bsm.getOwner())
                    && !"java/lang/invoke/StringConcatFactory".equals(indy.bsm.getOwner())
                    && "(IJ)J".equals(indy.desc)
                    && values != null && values.size() == 2
                    && values.get(0).isPureNumeric() && values.get(1).isPureNumeric()) {
                    return ConstVal.pureKey(2, values.get(0).entrySlotMask | values.get(1).entrySlotMask);
                }
                return ConstVal.unknown(ret.getSize());
            }
            String desc = ((MethodInsnNode) insn).desc;
            Type ret = Type.getReturnType(desc);
            if (ret == Type.VOID_TYPE) return null;
            return ConstVal.unknown(ret.getSize());
        }

        @Override
        public void returnOperation(AbstractInsnNode insn, ConstVal value, ConstVal expected) {
        }

        @Override
        public ConstVal merge(ConstVal v1, ConstVal v2) {
            if (v1.equals(v2)) return v1;
            if (v1.size == v2.size && v1.isPureNumeric() && v2.isPureNumeric()) {
                return ConstVal.pureKey(v1.size, v1.entrySlotMask | v2.entrySlotMask);
            }
            return ConstVal.unknown(Math.min(v1.size, v2.size));
        }
    }

    static void parseConstants(String str, Map<Integer, Object> map) {
        for (String part : str.split(";")) {
            if (part.trim().isEmpty()) continue;
            String[] tokens = part.split(":", 3);
            if (tokens.length < 3) continue;
            int idx = Integer.parseInt(tokens[0]);
            String desc = tokens[1];
            String valStr = tokens[2];
            Object val;
            if ("J".equals(desc)) {
                val = Long.valueOf(Long.parseLong(valStr.endsWith("L") ? valStr.substring(0, valStr.length() - 1) : valStr));
            } else if ("I".equals(desc)) {
                val = Integer.valueOf(Integer.parseInt(valStr));
            } else if ("S".equals(desc)) {
                val = Short.valueOf((short) Integer.parseInt(valStr));
            } else if ("B".equals(desc)) {
                val = Byte.valueOf((byte) Integer.parseInt(valStr));
            } else if ("C".equals(desc)) {
                if (valStr.startsWith("u") && valStr.length() == 5) {
                    val = Character.valueOf((char) Integer.parseInt(valStr.substring(1), 16));
                } else if (valStr.length() == 1) {
                    val = Character.valueOf(valStr.charAt(0));
                } else {
                    val = Character.valueOf((char) Integer.parseInt(valStr));
                }
            } else if ("Z".equals(desc)) {
                val = Boolean.valueOf(Boolean.parseBoolean(valStr) || "1".equals(valStr));
            } else {
                val = valStr;
            }
            map.put(Integer.valueOf(idx), val);
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            throw new IllegalArgumentException(
                "Usage: ReadableNamespaceMapper <forward|reverse> <readable-symbols.tsv> [--widen-package-access] [--strip-bodies] [--runtime-symbols <symbol-map.tsv>] [--hierarchy <jars>] [--zkm-spec <spec.tsv>] <in.jar> <out.jar>..."
            );
        }
        final boolean isForward = "forward".equals(args[0]);
        if (!isForward && !"reverse".equals(args[0])) {
            throw new IllegalArgumentException("Mode must be 'forward' or 'reverse': " + args[0]);
        }
        Path symbolsPath = Paths.get(args[1]);
        boolean widenPackageAccess = false;
        boolean stripBodies = false;
        Path runtimeSymbolsPath = null;
        Path zkmSpecPath = null;
        List<Path> hierarchyJars = new ArrayList<Path>();
        int argIdx = 2;
        while (argIdx < args.length && args[argIdx].startsWith("--")) {
            String opt = args[argIdx++];
            if ("--widen-package-access".equals(opt)) {
                widenPackageAccess = true;
            } else if ("--strip-bodies".equals(opt)) {
                stripBodies = true;
            } else if ("--runtime-symbols".equals(opt)) {
                if (argIdx >= args.length) throw new IllegalArgumentException("Missing --runtime-symbols value");
                runtimeSymbolsPath = Paths.get(args[argIdx++]);
            } else if ("--hierarchy".equals(opt)) {
                if (argIdx >= args.length) throw new IllegalArgumentException("Missing --hierarchy value");
                for (String part : args[argIdx++].split(";")) {
                    if (!part.trim().isEmpty()) {
                        Path p = Paths.get(part.trim());
                        if (Files.exists(p)) hierarchyJars.add(p);
                    }
                }
            } else if ("--zkm-abi-spec".equals(opt) || "--zkm-spec".equals(opt)) {
                if (argIdx >= args.length) throw new IllegalArgumentException("Missing " + opt + " value");
                zkmSpecPath = Paths.get(args[argIdx++]);
            } else {
                throw new IllegalArgumentException("Unknown option: " + opt);
            }
        }
        if ((args.length - argIdx) < 2 || ((args.length - argIdx) % 2) != 0) {
            throw new IllegalArgumentException("Expected pairs of <in.jar> <out.jar>");
        }

        final Map<String, String> forwardClass = new HashMap<String, String>();
        final Map<String, String> reverseClass = new HashMap<String, String>();
        List<String[]> symbolRows = new ArrayList<String[]>();
        for (String line : Files.readAllLines(symbolsPath, StandardCharsets.UTF_8)) {
            String[] row = line.split("\t", -1);
            if (row.length != 5 || "kind".equals(row[0])) continue;
            if ("CLASS".equals(row[0])) {
                forwardClass.put(row[1], row[4]);
                reverseClass.put(row[4], row[1]);
            } else {
                symbolRows.add(row);
            }
        }

        final Remapper forwardTypeMapper = new Remapper() {
            @Override
            public String map(String typeName) {
                return mapClassInternal(typeName, forwardClass);
            }
        };

        final Map<String, String> activeClassMap = isForward ? forwardClass : reverseClass;
        final Map<String, String> fieldMap = new HashMap<String, String>();
        final Map<String, String> methodMap = new HashMap<String, String>();
        final Map<String, String> forwardMethodExact = new HashMap<String, String>();
        final Map<String, List<LegMember>> reverseFieldByName = new HashMap<String, List<LegMember>>();
        final Map<String, List<LegMember>> reverseMethodByName = new HashMap<String, List<LegMember>>();

        for (String[] row : symbolRows) {
            String kind = row[0];
            String legOwner = row[1];
            String legName = row[2];
            String legDesc = row[3];
            String readName = row[4];
            if ("FIELD".equals(kind)) {
                if (isForward) {
                    fieldMap.put(legOwner + "\t" + legName + "\t" + legDesc, readName);
                } else {
                    String rOwner = forwardTypeMapper.map(legOwner);
                    String rDesc = forwardTypeMapper.mapDesc(legDesc);
                    fieldMap.put(rOwner + "\t" + readName + "\t" + rDesc, legName);
                    addLegMember(reverseFieldByName, rOwner + "\t" + readName, new LegMember(legOwner, legName, legDesc, rDesc));
                }
            } else if ("METHOD".equals(kind)) {
                forwardMethodExact.put(legOwner + "\t" + legName + "\t" + legDesc, readName);
                if (isForward) {
                    methodMap.put(legOwner + "\t" + legName + "\t" + legDesc, readName);
                } else {
                    String rOwner = forwardTypeMapper.map(legOwner);
                    String rDesc = forwardTypeMapper.mapMethodDesc(legDesc);
                    methodMap.put(rOwner + "\t" + readName + "\t" + rDesc, legName);
                    addLegMember(reverseMethodByName, rOwner + "\t" + readName, new LegMember(legOwner, legName, legDesc, rDesc));
                }
            }
        }

        final Map<String, String> legToRuntimeClass = new HashMap<String, String>();
        final Map<String, String> legToRuntimeField = new HashMap<String, String>();
        final Map<String, String> legToRuntimeMethod = new HashMap<String, String>();
        final Map<String, Set<String>> legToRuntimeFieldByName = new HashMap<String, Set<String>>();
        final Map<String, Set<String>> legToRuntimeMethodByName = new HashMap<String, Set<String>>();
        if (!isForward && runtimeSymbolsPath != null && Files.exists(runtimeSymbolsPath)) {
            Map<String, String[]> dedupRtRows = new LinkedHashMap<String, String[]>();
            final Map<String, String> runtimeToLegClass = new HashMap<String, String>();
            for (String line : Files.readAllLines(runtimeSymbolsPath, StandardCharsets.UTF_8)) {
                String[] row = line.split("\t", -1);
                if (row.length != 5 || "kind".equals(row[0])) continue;
                if ("CLASS".equals(row[0])) {
                    runtimeToLegClass.put(row[1], row[4]);
                    legToRuntimeClass.put(row[4], row[1]);
                } else {
                    dedupRtRows.put(row[0] + "\t" + row[1] + "\t" + row[2] + "\t" + row[3], row);
                }
            }
            Remapper rtToLegMapper = new Remapper() {
                @Override
                public String map(String typeName) {
                    return mapClassInternal(typeName, runtimeToLegClass);
                }
            };
            for (String[] row : dedupRtRows.values()) {
                String kind = row[0];
                String rtOwner = row[1];
                String rtName = row[2];
                String rtDesc = row[3];
                String legName = row[4];
                String legOwner = rtToLegMapper.map(rtOwner);
                if ("FIELD".equals(kind)) {
                    String legDesc = rtToLegMapper.mapDesc(rtDesc);
                    legToRuntimeField.put(legOwner + "\t" + legName + "\t" + legDesc, rtName);
                    Set<String> set = legToRuntimeFieldByName.get(legOwner + "\t" + legName);
                    if (set == null) {
                        set = new LinkedHashSet<String>();
                        legToRuntimeFieldByName.put(legOwner + "\t" + legName, set);
                    }
                    set.add(rtName);
                } else if ("METHOD".equals(kind)) {
                    String legDesc = rtToLegMapper.mapMethodDesc(rtDesc);
                    legToRuntimeMethod.put(legOwner + "\t" + legName + "\t" + legDesc, rtName);
                    Set<String> set = legToRuntimeMethodByName.get(legOwner + "\t" + legName);
                    if (set == null) {
                        set = new LinkedHashSet<String>();
                        legToRuntimeMethodByName.put(legOwner + "\t" + legName, set);
                    }
                    set.add(rtName);
                }
            }
        }

        // Load class hierarchy (parents and children) in the active source namespace,
        // plus an intermediate-namespace parents map for resolving forward method names on ZkmRules.
        final Map<String, List<String>> parentsMap = new HashMap<String, List<String>>();
        final Map<String, List<String>> childrenMap = new HashMap<String, List<String>>();
        final Map<String, List<String>> intermParentsMap = new HashMap<String, List<String>>();
        final Map<String, List<String>> intermChildrenDummy = new HashMap<String, List<String>>();
        final Map<String, String> intermSuperMap = new HashMap<String, String>();
        final Map<String, List<String>> intermItfsMap = new HashMap<String, List<String>>();
        final Set<String> intermInterfaceSet = new HashSet<String>();
        final Remapper reverseTypeMapper = new Remapper() {
            @Override
            public String map(String typeName) {
                return mapClassInternal(typeName, reverseClass);
            }
        };
        for (Path hJar : hierarchyJars) {
            loadHierarchyFromJar(hJar, isForward ? null : forwardTypeMapper, parentsMap, childrenMap);
            if (zkmSpecPath != null) {
                loadHierarchyFromJar(hJar, null, intermParentsMap, intermChildrenDummy);
                loadFrameHierarchyFromJar(hJar, null, intermSuperMap, intermItfsMap, intermInterfaceSet);
            }
        }
        for (int i = argIdx; i < args.length; i += 2) {
            Path inJar = Paths.get(args[i]);
            if (Files.exists(inJar)) {
                loadHierarchyFromJar(inJar, null, parentsMap, childrenMap);
                if (zkmSpecPath != null) {
                    if (isForward) {
                        loadHierarchyFromJar(inJar, null, intermParentsMap, intermChildrenDummy);
                    } else {
                        loadHierarchyFromJar(inJar, reverseTypeMapper, intermParentsMap, intermChildrenDummy);
                        loadFrameHierarchyFromJar(inJar, reverseTypeMapper, intermSuperMap, intermItfsMap, intermInterfaceSet);
                    }
                }
            }
        }

        final Map<String, List<String>> ancestorsCache = new HashMap<String, List<String>>();
        final Map<String, List<String>> descendantsCache = new HashMap<String, List<String>>();
        final Map<String, List<String>> intermAncestorsCache = new HashMap<String, List<String>>();

        // Load ZKM ABI Spec if provided (keyed 100% by intermediate legOwner, legName, legDesc)
        final boolean hasZkmSpec = (zkmSpecPath != null && Files.exists(zkmSpecPath));
        final Map<String, ZkmRule> declStripByLegKey = new HashMap<String, ZkmRule>();
        final Map<String, List<ZkmRule>> allRulesByLegNameDesc = new HashMap<String, List<ZkmRule>>();
        final Map<String, List<ZkmRule>> callStripByReadOwner = new HashMap<String, List<ZkmRule>>();
        final Map<String, List<ZkmRule>> declStripByCleanReadSig = new HashMap<String, List<ZkmRule>>();
        final Map<String, List<ZkmRule>> callStripByCleanReadSig = new HashMap<String, List<ZkmRule>>();
        final Map<String, List<ZkmRule>> allRulesByOrigReadSig = new HashMap<String, List<ZkmRule>>();
        final Map<String, ZkmOverride> callOverrides = new HashMap<String, ZkmOverride>();

        if (hasZkmSpec) {
            int declCount = 0;
            int callCount = 0;
            for (String line : Files.readAllLines(zkmSpecPath, StandardCharsets.UTF_8)) {
                if (line.startsWith("#") || line.trim().isEmpty()) continue;
                String[] parts = line.split("\t", -1);
                String type = parts[0];
                if ("DECL_STRIP".equals(type) || "CALL_STRIP".equals(type)) {
                    String legOwner = parts[1];
                    String legName = parts[2];
                    String legDesc = parts[3];
                    int[] stripped = parseInts(parts[4]);
                    String consts = parts.length > 5 ? parts[5] : "";
                    ZkmRule rule = new ZkmRule(type, legOwner, legName, legDesc, stripped, consts);
                    rule.readOwner = forwardTypeMapper.map(legOwner);
                    rule.origReadDesc = forwardTypeMapper.mapMethodDesc(legDesc);
                    rule.cleanReadDesc = forwardTypeMapper.mapMethodDesc(computeCleanDesc(legDesc, stripped));
                    rule.readName = resolveForwardMethodName(legOwner, legName, legDesc, forwardMethodExact, intermParentsMap, intermAncestorsCache);

                    String cleanSig = rule.readName + "#" + rule.cleanReadDesc;
                    String origSig = rule.readName + "#" + rule.origReadDesc;
                    addRuleToListMap(allRulesByOrigReadSig, origSig, rule);
                    addRuleToListMap(allRulesByLegNameDesc, legName + "#" + legDesc, rule);
                    if ("DECL_STRIP".equals(type)) {
                        declStripByLegKey.put(legOwner + "#" + legName + "#" + legDesc, rule);
                        addRuleToListMap(declStripByCleanReadSig, cleanSig, rule);
                        declCount++;
                    } else {
                        addRuleToListMap(callStripByReadOwner, rule.readOwner, rule);
                        addRuleToListMap(callStripByCleanReadSig, cleanSig, rule);
                        callCount++;
                    }
                } else if ("CALL_OVERRIDE".equals(type)) {
                    String legOwner = parts[1];
                    String legName = parts[2];
                    String legDesc = parts[3];
                    String callerClass = parts[4];
                    String callerMethod = parts[5];
                    String callerDesc = parts[6];
                    int ordinal = Integer.parseInt(parts[7]);
                    String consts = parts.length > 8 ? parts[8] : "";
                    ZkmOverride ov = new ZkmOverride(legOwner, legName, legDesc, callerClass, callerMethod, callerDesc, ordinal, consts);
                    callOverrides.put(callerClass + "#" + callerMethod + "#" + callerDesc + "#" + ordinal + "#" + legOwner + "#" + legName + "#" + legDesc, ov);
                }
            }
            System.out.println(
                "Loaded ZKM ABI Spec: " + declCount + " DECL_STRIP, "
                + callCount + " CALL_STRIP, " + callOverrides.size() + " CALL_OVERRIDE"
            );
        }

        final Remapper activeRemapper = new Remapper() {
            @Override
            public String map(String typeName) {
                return mapClassInternal(typeName, activeClassMap);
            }

            @Override
            public String mapFieldName(String owner, String name, String descriptor) {
                String direct = fieldMap.get(owner + "\t" + name + "\t" + descriptor);
                if (direct != null) return direct;
                for (String anc : getAncestors(owner, parentsMap, ancestorsCache)) {
                    String res = fieldMap.get(anc + "\t" + name + "\t" + descriptor);
                    if (res != null) return res;
                }
                return name;
            }

            @Override
            public String mapMethodName(String owner, String name, String descriptor) {
                if (name.startsWith("<")) return name;
                String direct = methodMap.get(owner + "\t" + name + "\t" + descriptor);
                if (direct != null) return direct;
                for (String anc : getAncestors(owner, parentsMap, ancestorsCache)) {
                    String res = methodMap.get(anc + "\t" + name + "\t" + descriptor);
                    if (res != null) return res;
                }
                return name;
            }

            @Override
            public String mapInnerClassName(String name, String outerName, String innerName) {
                String sup = super.mapInnerClassName(name, outerName, innerName);
                if (isForward && sup != null && (sup.startsWith("C_") || sup.startsWith("4"))) {
                    String mappedType = mapType(name);
                    int sl = mappedType.lastIndexOf('/');
                    String simple = sl >= 0 ? mappedType.substring(sl + 1) : mappedType;
                    int dl = simple.lastIndexOf('$');
                    if (dl >= 0 && dl + 1 < simple.length()) simple = simple.substring(dl + 1);
                    return simple;
                }
                return sup;
            }
        };

        int totalClasses = 0;
        int[] stats = new int[5]; // [0]=declRestored, [1]=callInjected, [2]=overridesApplied, [3]=fwdDeclStripped, [4]=fwdCallStripped
        for (int i = argIdx; i < args.length; i += 2) {
            Path inJar = Paths.get(args[i]);
            Path outJar = Paths.get(args[i + 1]);
            if (outJar.getParent() != null) Files.createDirectories(outJar.getParent());
            try (JarFile source = new JarFile(inJar.toFile());
                 JarOutputStream target = new JarOutputStream(new java.io.BufferedOutputStream(Files.newOutputStream(outJar), 65536))) {
                Enumeration<JarEntry> entries = source.entries();
                while (entries.hasMoreElements()) {
                    JarEntry entry = entries.nextElement();
                    if (entry.isDirectory()) continue;
                    byte[] bytes = readAllBytes(source.getInputStream(entry));
                    String entryName = entry.getName();
                    if (entryName.endsWith(".class")) {
                        ClassNode cn = new ClassNode();
                        new ClassReader(bytes).accept(cn, 0);

                        if (!hasZkmSpec) {
                            if (isForward && widenPackageAccess) {
                                widenAccess(cn);
                            }
                            if (!isForward && runtimeSymbolsPath != null) {
                                foldRuntimeSymbolCalls(
                                    cn,
                                    activeRemapper,
                                    reverseClass,
                                    reverseFieldByName,
                                    reverseMethodByName,
                                    legToRuntimeClass,
                                    legToRuntimeField,
                                    legToRuntimeMethod,
                                    legToRuntimeFieldByName,
                                    legToRuntimeMethodByName,
                                    parentsMap,
                                    childrenMap,
                                    ancestorsCache,
                                    descendantsCache
                                );
                            }
                            remapIndySamNames(cn, activeRemapper, isForward, legToRuntimeMethod, intermParentsMap, intermAncestorsCache);
                            ClassWriter cw = new ClassWriter(0);
                            cn.accept(new ClassRemapper(cw, activeRemapper));
                            bytes = cw.toByteArray();
                        } else if (isForward) {
                            if (widenPackageAccess) {
                                widenAccess(cn);
                            }

                            // Track DECL_STRIP matches and call-site matches on intermediate cn before remapping
                            Map<Integer, ZkmRule> strippedMethodsByIndex = new HashMap<Integer, ZkmRule>();
                            Map<Integer, Map<Integer, ZkmRule>> callRulesByMethodAndOrd = new HashMap<Integer, Map<Integer, ZkmRule>>();
                            for (int mIdx = 0; mIdx < cn.methods.size(); mIdx++) {
                                MethodNode mn = cn.methods.get(mIdx);
                                ZkmRule rule = declStripByLegKey.get(cn.name + "#" + mn.name + "#" + mn.desc);
                                if (rule != null) {
                                    strippedMethodsByIndex.put(Integer.valueOf(mIdx), rule);
                                }
                                if (stripBodies && mn.instructions != null && mn.instructions.size() > 0) {
                                    Map<Integer, ZkmRule> ordMap = null;
                                    int mOrd = 0;
                                    for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
                                        if (insn instanceof MethodInsnNode) {
                                            MethodInsnNode minsn = (MethodInsnNode) insn;
                                            ZkmRule callRule = findMatchingIntermRule(
                                                allRulesByLegNameDesc.get(minsn.name + "#" + minsn.desc),
                                                minsn.owner,
                                                minsn.name,
                                                intermParentsMap,
                                                intermAncestorsCache
                                            );
                                            if (callRule != null) {
                                                if (ordMap == null) ordMap = new HashMap<Integer, ZkmRule>();
                                                ordMap.put(Integer.valueOf(mOrd), callRule);
                                            }
                                            mOrd++;
                                        }
                                    }
                                    if (ordMap != null) {
                                        callRulesByMethodAndOrd.put(Integer.valueOf(mIdx), ordMap);
                                    }
                                }
                            }

                            remapIndySamNames(cn, activeRemapper, isForward, legToRuntimeMethod, intermParentsMap, intermAncestorsCache);

                            ClassNode readableCn = new ClassNode();
                            cn.accept(new ClassRemapper(readableCn, activeRemapper));

                            boolean zkmModified = false;
                            if (!stripBodies) {
                                for (Map.Entry<Integer, ZkmRule> smEntry : strippedMethodsByIndex.entrySet()) {
                                    int mIdx = smEntry.getKey().intValue();
                                    ZkmRule rule = smEntry.getValue();
                                    MethodNode origMn = readableCn.methods.get(mIdx);

                                    MethodNode cleanMn = new MethodNode(
                                        origMn.access,
                                        origMn.name,
                                        origMn.desc,
                                        origMn.signature,
                                        origMn.exceptions != null ? origMn.exceptions.toArray(new String[0]) : null
                                    );
                                    applyMethodStrip(cleanMn, rule.strippedIndices);

                                    cleanMn.access &= ~Opcodes.ACC_ABSTRACT;
                                    cleanMn.instructions = new InsnList();
                                    if ("<init>".equals(cleanMn.name)) {
                                        cleanMn.instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
                                        String sup = readableCn.superName != null ? readableCn.superName : "java/lang/Object";
                                        cleanMn.instructions.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, sup, "<init>", "()V", false));
                                        cleanMn.instructions.add(new InsnNode(Opcodes.RETURN));
                                    } else {
                                        addDummyReturn(cleanMn.instructions, cleanMn.desc);
                                    }

                                    if (!hasMethod(readableCn, cleanMn.name, cleanMn.desc)) {
                                        readableCn.methods.add(cleanMn);
                                        zkmModified = true;
                                    }

                                    if ((origMn.access & Opcodes.ACC_ABSTRACT) != 0) {
                                        origMn.access &= ~Opcodes.ACC_ABSTRACT;
                                        if (origMn.instructions == null || origMn.instructions.size() == 0) {
                                            origMn.instructions = new InsnList();
                                            addDummyReturn(origMn.instructions, origMn.desc);
                                        }
                                        zkmModified = true;
                                    }
                                }

                                if (applyForwardCallStripStubs(readableCn, callStripByReadOwner.get(readableCn.name))) {
                                    zkmModified = true;
                                }
                            } else {
                                // Full in-place ZKM ABI stripping on concrete bodies and declarations (--strip-bodies)
                                for (int mIdx = 0; mIdx < readableCn.methods.size(); mIdx++) {
                                    MethodNode mn = readableCn.methods.get(mIdx);
                                    ZkmRule declRule = strippedMethodsByIndex.get(Integer.valueOf(mIdx));
                                    Map<Integer, ZkmRule> callRules = callRulesByMethodAndOrd.get(Integer.valueOf(mIdx));
                                    if (declRule == null && callRules == null) {
                                        if (mn.instructions != null && mn.instructions.size() > 0) {
                                            peepholeCleanDeadZkmLocals(mn);
                                            stripFrames(mn);
                                            mn.maxLocals = computeActualMaxLocals(mn);
                                        }
                                        continue;
                                    }

                                    if (mn.instructions != null && mn.instructions.size() > 0 && callRules != null) {
                                        stripCallSitesInMethodBody(readableCn.name, mn, declRule, callRules, stats);
                                        zkmModified = true;
                                    }
                                    if (declRule != null) {
                                        if (mn.instructions != null && mn.instructions.size() > 0) {
                                            peepholeCleanDeadZkmLocals(mn);
                                            stripDeclLocalsInMethodBody(readableCn.name, mn, declRule);
                                        }
                                        applyMethodStrip(mn, declRule.strippedIndices);
                                        if (declRule.strippedIndices.length > 0) {
                                            mn.signature = null;
                                        }
                                        if (mn.instructions != null && mn.instructions.size() > 0) {
                                            mn.maxLocals = computeActualMaxLocals(mn);
                                        }
                                        stats[3]++;
                                        zkmModified = true;
                                    } else if (mn.instructions != null && mn.instructions.size() > 0) {
                                        peepholeCleanDeadZkmLocals(mn);
                                        stripFrames(mn);
                                        mn.maxLocals = computeActualMaxLocals(mn);
                                    }
                                }

                                if (applyForwardCallStripStubs(readableCn, callStripByReadOwner.get(readableCn.name))) {
                                    zkmModified = true;
                                }
                            }

                            if (stripBodies) {
                                computeExactMaxsAndVerify(readableCn);
                                ClassWriter cw = new ClassWriter(0);
                                readableCn.accept(cw);
                                bytes = cw.toByteArray();
                            } else {
                                ClassWriter cw = new ClassWriter(zkmModified ? ClassWriter.COMPUTE_MAXS : 0);
                                readableCn.accept(cw);
                                bytes = cw.toByteArray();
                            }
                        } else {
                            // Reverse mode with ZKM ABI Spec
                            if (runtimeSymbolsPath != null) {
                                foldRuntimeSymbolCalls(
                                    cn,
                                    activeRemapper,
                                    reverseClass,
                                    reverseFieldByName,
                                    reverseMethodByName,
                                    legToRuntimeClass,
                                    legToRuntimeField,
                                    legToRuntimeMethod,
                                    legToRuntimeFieldByName,
                                    legToRuntimeMethodByName,
                                    parentsMap,
                                    childrenMap,
                                    ancestorsCache,
                                    descendantsCache
                                );
                            }

                            removeCallStripStubsIfOriginalPresent(cn, callStripByReadOwner.get(cn.name));

                            boolean declMod = applyReverseDeclRestore(cn, activeRemapper, declStripByCleanReadSig, parentsMap, ancestorsCache, stats);
                            boolean callMod = applyReverseCallSiteInjection(
                                cn,
                                activeRemapper,
                                declStripByCleanReadSig,
                                callStripByCleanReadSig,
                                allRulesByOrigReadSig,
                                callOverrides,
                                parentsMap,
                                ancestorsCache,
                                stats
                            );

                            remapIndySamNames(cn, activeRemapper, isForward, legToRuntimeMethod, intermParentsMap, intermAncestorsCache);

                            ClassWriter cw;
                            if (declMod || callMod || needsFrameOrMaxRecompute(cn)) {
                                boolean canComputeFrames = ((cn.version & 0xFFFF) >= Opcodes.V1_6) && !hasJsrOrRet(cn);
                                if (canComputeFrames) {
                                    cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS) {
                                        @Override
                                        protected String getCommonSuperClass(String type1, String type2) {
                                            return commonSuperClass(type1, type2, intermSuperMap, intermItfsMap, intermInterfaceSet);
                                        }
                                    };
                                } else {
                                    computeExactMaxsAndVerify(cn);
                                    cw = new ClassWriter(0);
                                }
                            } else {
                                cw = new ClassWriter(0);
                            }
                            cn.accept(new ClassRemapper(cw, activeRemapper));
                            bytes = cw.toByteArray();
                        }

                        ClassReader check = new ClassReader(bytes);
                        entryName = check.getClassName() + ".class";
                        totalClasses++;
                    }
                    target.putNextEntry(new JarEntry(entryName));
                    target.write(bytes);
                    target.closeEntry();
                }
            }
        }
        if (hasZkmSpec && isForward && stripBodies) {
            System.out.println(
                "ZKM forward --strip-bodies stats: declStripped=" + stats[3]
                + " callStripped=" + stats[4]
            );
        }
        if (hasZkmSpec && !isForward) {
            System.out.println(
                "ZKM reverse stats: declRestored=" + stats[0]
                + " callInjected=" + stats[1]
                + " overridesApplied=" + stats[2] + "/" + callOverrides.size()
            );
        }
        System.out.println("PASS readable_namespace_mapped mode=" + args[0] + " classes=" + totalClasses);
    }

    static void addRuleToListMap(Map<String, List<ZkmRule>> map, String key, ZkmRule rule) {
        List<ZkmRule> list = map.get(key);
        if (list == null) {
            list = new ArrayList<ZkmRule>();
            map.put(key, list);
        }
        list.add(rule);
    }

    static ZkmRule findMatchingIntermRule(
        List<ZkmRule> candidates,
        String intermTargetOwner,
        String intermMethodName,
        Map<String, List<String>> intermParentsMap,
        Map<String, List<String>> intermAncestorsCache
    ) {
        if (candidates == null || candidates.isEmpty()) return null;
        for (ZkmRule rule : candidates) {
            if (rule.legOwner.equals(intermTargetOwner)) return rule;
        }
        if (!"<init>".equals(intermMethodName)) {
            for (ZkmRule rule : candidates) {
                if (isSubtypeOf(intermTargetOwner, rule.legOwner, intermParentsMap, intermAncestorsCache)) {
                    return rule;
                }
            }
        }
        return null;
    }

    static boolean hasJsrOrRet(ClassNode cn) {
        for (MethodNode mn : cn.methods) {
            if (mn.instructions == null) continue;
            for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
                int op = insn.getOpcode();
                if (op == Opcodes.JSR || op == Opcodes.RET) return true;
            }
        }
        return false;
    }

    static boolean needsFrameOrMaxRecompute(ClassNode cn) {
        if ((cn.version & 0xFFFF) < Opcodes.V1_7) return false;
        for (MethodNode mn : cn.methods) {
            if ((mn.access & (Opcodes.ACC_ABSTRACT | Opcodes.ACC_NATIVE)) != 0 || mn.instructions == null) continue;
            boolean hasBranch = (mn.tryCatchBlocks != null && !mn.tryCatchBlocks.isEmpty());
            boolean hasFrame = false;
            for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
                if (insn instanceof FrameNode) {
                    hasFrame = true;
                    break;
                }
                if (insn instanceof JumpInsnNode || insn instanceof TableSwitchInsnNode || insn instanceof LookupSwitchInsnNode) {
                    hasBranch = true;
                }
            }
            if (hasBranch && !hasFrame) return true;
        }
        return false;
    }

    static void removeCallStripStubsIfOriginalPresent(ClassNode cn, List<ZkmRule> rulesForOwner) {
        if (rulesForOwner == null || rulesForOwner.isEmpty()) return;
        Set<String> origPresent = new HashSet<String>();
        for (MethodNode mn : cn.methods) {
            origPresent.add(mn.name + "#" + mn.desc);
        }
        Set<String> stubsToRemove = new HashSet<String>();
        for (ZkmRule rule : rulesForOwner) {
            if (origPresent.contains(rule.readName + "#" + rule.origReadDesc)) {
                stubsToRemove.add(rule.readName + "#" + rule.cleanReadDesc);
            }
        }
        if (stubsToRemove.isEmpty()) return;
        Iterator<MethodNode> it = cn.methods.iterator();
        while (it.hasNext()) {
            MethodNode mn = it.next();
            if (stubsToRemove.contains(mn.name + "#" + mn.desc)) {
                it.remove();
            }
        }
    }

    static void stripCallSitesInMethodBody(
        String ownerClass,
        MethodNode mn,
        ZkmRule callerDeclRule,
        Map<Integer, ZkmRule> callRulesByOrd,
        int[] stats
    ) throws Exception {
        int safeLocals = Math.max(mn.maxLocals, computeActualMaxLocals(mn) + 4);
        mn.maxLocals = safeLocals;
        mn.maxStack = Math.max(mn.maxStack + 32, 128);

        boolean callerStatic = (mn.access & Opcodes.ACC_STATIC) != 0;
        Set<Integer> callerStrippedSlots = new HashSet<Integer>();
        if (callerDeclRule != null) {
            Type[] callerArgs = Type.getArgumentTypes(mn.desc);
            Set<Integer> cIdxSet = new HashSet<Integer>();
            for (int idx : callerDeclRule.strippedIndices) cIdxSet.add(Integer.valueOf(idx));
            int slot = callerStatic ? 0 : 1;
            for (int p = 0; p < callerArgs.length; p++) {
                if (cIdxSet.contains(Integer.valueOf(p))) {
                    callerStrippedSlots.add(Integer.valueOf(slot));
                }
                slot += callerArgs[p].getSize();
            }
        }

        AbstractInsnNode[] insns = mn.instructions.toArray();
        Map<AbstractInsnNode, Integer> insnIndex = new IdentityHashMap<AbstractInsnNode, Integer>(insns.length * 2);
        for (int i = 0; i < insns.length; i++) {
            insnIndex.put(insns[i], Integer.valueOf(i));
        }

        Set<LabelNode> targetedLabels = collectTargetedLabels(mn);

        Analyzer<SourceValue> srcAnalyzer = new Analyzer<SourceValue>(new SourceInterpreter());
        Frame<SourceValue>[] srcFrames = srcAnalyzer.analyze(ownerClass, mn);

        Analyzer<ConstVal> constAnalyzer = new Analyzer<ConstVal>(new ConstInterpreter(callerStatic));
        Frame<ConstVal>[] constFrames = constAnalyzer.analyze(ownerClass, mn);

        // Count stack consumers per instruction across the method
        int[] consumerCount = computeStackConsumerCounts(insns, srcFrames, insnIndex);

        Set<AbstractInsnNode> toRemove = Collections.newSetFromMap(new IdentityHashMap<AbstractInsnNode, Boolean>());
        int mOrd = 0;
        for (int i = 0; i < insns.length; i++) {
            if (!(insns[i] instanceof MethodInsnNode)) continue;
            MethodInsnNode minsn = (MethodInsnNode) insns[i];
            ZkmRule rule = callRulesByOrd.get(Integer.valueOf(mOrd));
            mOrd++;
            if (rule == null) continue;

            Frame<SourceValue> sf = srcFrames[i];
            if (sf == null) {
                // Unreachable dead call instruction: still rewrite descriptor so class references stay consistent
                minsn.desc = rule.cleanReadDesc;
                continue;
            }

            Type[] origArgs = Type.getArgumentTypes(minsn.desc);
            int stackBase = sf.getStackSize() - origArgs.length;
            for (int sIdx : rule.strippedIndices) {
                int argStackIdx = stackBase + sIdx;
                SourceValue sv = sf.getStack(argStackIdx);
                List<AbstractInsnNode> matched = matchPureArgumentSlice(
                    sv,
                    origArgs[sIdx].getSize(),
                    i,
                    argStackIdx,
                    insns,
                    insnIndex,
                    srcFrames,
                    constFrames,
                    consumerCount,
                    callerStrippedSlots,
                    targetedLabels
                );
                if (matched == null) {
                    throw new IllegalStateException(
                        "Non-pure ZKM call-site argument push in " + ownerClass + "." + mn.name + mn.desc
                        + " calling " + minsn.owner + "." + minsn.name + minsn.desc
                        + " strippedParamIdx=" + sIdx + " insnsSize=" + sv.insns.size()
                    );
                }
                for (AbstractInsnNode rem : matched) {
                    if (!toRemove.add(rem)) {
                        throw new IllegalStateException(
                            "Shared ZKM argument producer instruction in " + ownerClass + "." + mn.name + mn.desc
                            + " calling " + minsn.owner + "." + minsn.name + minsn.desc + " param=" + sIdx
                        );
                    }
                }
            }

            minsn.desc = rule.cleanReadDesc;
            stats[4]++;
        }

        for (AbstractInsnNode rem : toRemove) {
            mn.instructions.remove(rem);
        }
        stripFrames(mn);
    }

    static List<AbstractInsnNode> matchPureArgumentSlice(
        SourceValue sv,
        int expectedSlotSize,
        int callIdx,
        int argStackIdx,
        AbstractInsnNode[] insns,
        Map<AbstractInsnNode, Integer> insnIndex,
        Frame<SourceValue>[] srcFrames,
        Frame<ConstVal>[] constFrames,
        int[] consumerCount,
        Set<Integer> callerStrippedSlots,
        Set<LabelNode> targetedLabels
    ) {
        if (sv.insns.size() != 1) return null;
        AbstractInsnNode prod = sv.insns.iterator().next();
        Integer pIdxObj = insnIndex.get(prod);
        if (pIdxObj == null) return null;
        int pIdx = pIdxObj.intValue();
        int op = prod.getOpcode();

        // Shape 1 & 2: Direct leaf push or narrowing cast (I2B / I2C / I2S) of leaf push
        List<AbstractInsnNode> directPush = matchLeafOrNarrowedPush(
            prod, pIdx, expectedSlotSize, insnIndex, srcFrames, constFrames, consumerCount, callerStrippedSlots
        );
        if (directPush != null) {
            return directPush;
        }

        // Shape 3: <1-slot pure push>; SWAP (commutes 1-slot key before 1-slot business arg)
        if (op == Opcodes.SWAP && expectedSlotSize == 1) {
            Frame<SourceValue> fSwap = srcFrames[pIdx];
            if (fSwap != null && fSwap.getStackSize() >= 2) {
                int popCount = getPoppedValueCount(prod, fSwap);
                int keyStackIdx = fSwap.getStackSize() - popCount;
                if (isCommutedKeySlotExclusive(prod, keyStackIdx, callIdx, argStackIdx, -1, insns, srcFrames)) {
                    SourceValue topSv = fSwap.getStack(fSwap.getStackSize() - 1);
                    if (topSv.insns.size() == 1) {
                        AbstractInsnNode topProd = topSv.insns.iterator().next();
                        Integer topIdxObj = insnIndex.get(topProd);
                        if (topIdxObj != null) {
                            List<AbstractInsnNode> keyPush = matchLeafOrNarrowedPush(
                                topProd, topIdxObj.intValue(), 1, insnIndex, srcFrames, constFrames, consumerCount, callerStrippedSlots
                            );
                            if (keyPush != null && isContiguousWithoutTargets(keyPush, prod, null, insns, insnIndex, targetedLabels)) {
                                List<AbstractInsnNode> res = new ArrayList<AbstractInsnNode>(keyPush.size() + 1);
                                res.addAll(keyPush);
                                res.add(prod);
                                return res;
                            }
                        }
                    }
                }
            }
        }

        // Shape 4: <1-slot pure push>; DUP_X1/DUP_X2; POP
        if ((op == Opcodes.DUP_X1 || op == Opcodes.DUP_X2) && expectedSlotSize == 1) {
            AbstractInsnNode popNode = nextRealInsn(insns, pIdx);
            if (popNode != null && popNode.getOpcode() == Opcodes.POP) {
                Frame<SourceValue> fDup = srcFrames[pIdx];
                Integer popIdxObj = insnIndex.get(popNode);
                if (fDup != null && fDup.getStackSize() >= 2 && popIdxObj != null) {
                    int popCount = getPoppedValueCount(prod, fDup);
                    int keyStackIdx = fDup.getStackSize() - popCount;
                    if (isCommutedKeySlotExclusive(prod, keyStackIdx, callIdx, argStackIdx, popIdxObj.intValue(), insns, srcFrames)) {
                        SourceValue topSv = fDup.getStack(fDup.getStackSize() - 1);
                        if (topSv.insns.size() == 1) {
                            AbstractInsnNode topProd = topSv.insns.iterator().next();
                            Integer topIdxObj = insnIndex.get(topProd);
                            if (topIdxObj != null) {
                                List<AbstractInsnNode> keyPush = matchLeafOrNarrowedPush(
                                    topProd, topIdxObj.intValue(), 1, insnIndex, srcFrames, constFrames, consumerCount, callerStrippedSlots
                                );
                                if (keyPush != null && isContiguousWithoutTargets(keyPush, prod, popNode, insns, insnIndex, targetedLabels)) {
                                    List<AbstractInsnNode> res = new ArrayList<AbstractInsnNode>(keyPush.size() + 2);
                                    res.addAll(keyPush);
                                    res.add(prod);
                                    res.add(popNode);
                                    return res;
                                }
                            }
                        }
                    }
                }
            }
        }

        // Shape 5: <2-slot pure push>; DUP2_X1/DUP2_X2; POP2
        if ((op == Opcodes.DUP2_X1 || op == Opcodes.DUP2_X2) && expectedSlotSize == 2) {
            AbstractInsnNode pop2Node = nextRealInsn(insns, pIdx);
            if (pop2Node != null && pop2Node.getOpcode() == Opcodes.POP2) {
                Frame<SourceValue> fDup = srcFrames[pIdx];
                Integer popIdxObj = insnIndex.get(pop2Node);
                if (fDup != null && fDup.getStackSize() >= 2 && popIdxObj != null) {
                    int popCount = getPoppedValueCount(prod, fDup);
                    int keyStackIdx = fDup.getStackSize() - popCount;
                    if (isCommutedKeySlotExclusive(prod, keyStackIdx, callIdx, argStackIdx, popIdxObj.intValue(), insns, srcFrames)) {
                        SourceValue topSv = fDup.getStack(fDup.getStackSize() - 1);
                        if (topSv.insns.size() == 1 && topSv.getSize() == 2) {
                            AbstractInsnNode topProd = topSv.insns.iterator().next();
                            Integer topIdxObj = insnIndex.get(topProd);
                            if (topIdxObj != null) {
                                List<AbstractInsnNode> keyPush = matchLeafOrNarrowedPush(
                                    topProd, topIdxObj.intValue(), 2, insnIndex, srcFrames, constFrames, consumerCount, callerStrippedSlots
                                );
                                if (keyPush != null && isContiguousWithoutTargets(keyPush, prod, pop2Node, insns, insnIndex, targetedLabels)) {
                                    List<AbstractInsnNode> res = new ArrayList<AbstractInsnNode>(keyPush.size() + 2);
                                    res.addAll(keyPush);
                                    res.add(prod);
                                    res.add(pop2Node);
                                    return res;
                                }
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    static boolean isCommutedKeySlotExclusive(
        AbstractInsnNode prod,
        int keyStackIdx,
        int callIdx,
        int argStackIdx,
        int popIdx,
        AbstractInsnNode[] insns,
        Frame<SourceValue>[] srcFrames
    ) {
        if (keyStackIdx < 0 || keyStackIdx != argStackIdx) return false;
        for (int j = 0; j < insns.length; j++) {
            if (j == popIdx) continue;
            Frame<SourceValue> fj = srcFrames[j];
            if (fj == null) continue;
            int popped = getPoppedValueCount(insns[j], fj);
            if (popped <= 0) continue;
            int stackSize = fj.getStackSize();
            int low = stackSize - popped;
            if (low > keyStackIdx) continue;
            int high = Math.min(stackSize - 1, keyStackIdx);
            for (int m = Math.max(0, low); m <= high; m++) {
                if (fj.getStack(m).insns.contains(prod)) {
                    if (j != callIdx || m != keyStackIdx) return false;
                }
            }
        }
        return true;
    }

    static List<AbstractInsnNode> matchLeafOrNarrowedPush(
        AbstractInsnNode prod,
        int pIdx,
        int expectedSlotSize,
        Map<AbstractInsnNode, Integer> insnIndex,
        Frame<SourceValue>[] srcFrames,
        Frame<ConstVal>[] constFrames,
        int[] consumerCount,
        Set<Integer> callerStrippedSlots
    ) {
        if (consumerCount[pIdx] != 1) return null;
        int op = prod.getOpcode();
        if (isPureLeafPush(prod, constFrames[pIdx], callerStrippedSlots, expectedSlotSize)) {
            return Collections.singletonList(prod);
        }
        if ((op == Opcodes.I2B || op == Opcodes.I2C || op == Opcodes.I2S) && expectedSlotSize == 1) {
            Frame<SourceValue> f = srcFrames[pIdx];
            if (f != null && f.getStackSize() >= 1) {
                SourceValue inSv = f.getStack(f.getStackSize() - 1);
                if (inSv.insns.size() == 1) {
                    AbstractInsnNode leaf = inSv.insns.iterator().next();
                    Integer leafIdx = insnIndex.get(leaf);
                    if (leafIdx != null && consumerCount[leafIdx.intValue()] == 1
                        && isPureLeafPush(leaf, constFrames[leafIdx.intValue()], callerStrippedSlots, 1)) {
                        List<AbstractInsnNode> pair = new ArrayList<AbstractInsnNode>(2);
                        pair.add(leaf);
                        pair.add(prod);
                        return pair;
                    }
                }
            }
        }
        return null;
    }

    static boolean isPureLeafPush(
        AbstractInsnNode insn,
        Frame<ConstVal> frameAtInsn,
        Set<Integer> callerStrippedSlots,
        int expectedSlotSize
    ) {
        int op = insn.getOpcode();
        if (op >= Opcodes.ACONST_NULL && op <= Opcodes.LDC) {
            if (op == Opcodes.LCONST_0 || op == Opcodes.LCONST_1 || op == Opcodes.DCONST_0 || op == Opcodes.DCONST_1) {
                return expectedSlotSize == 2;
            }
            if (op == Opcodes.LDC) {
                Object cst = ((LdcInsnNode) insn).cst;
                int sz = (cst instanceof Long || cst instanceof Double) ? 2 : 1;
                return sz == expectedSlotSize;
            }
            return expectedSlotSize == 1;
        }
        if ((op == Opcodes.ILOAD && expectedSlotSize == 1) || (op == Opcodes.LLOAD && expectedSlotSize == 2)) {
            if (frameAtInsn == null) return false;
            int slot = ((VarInsnNode) insn).var;
            if (slot < 0 || slot >= frameAtInsn.getLocals()) return false;
            ConstVal cv = frameAtInsn.getLocal(slot);
            if (cv == null) return false;
            if (cv.kind == ConstVal.KIND_CONST) return true;
            if (cv.kind == ConstVal.KIND_ENTRY_PARAM && cv.entrySlot >= 0 && callerStrippedSlots.contains(Integer.valueOf(cv.entrySlot))) {
                return true;
            }
            if (cv.kind == ConstVal.KIND_PURE_KEY && cv.entrySlotMask >= 0L && areAllMaskSlotsStripped(cv.entrySlotMask, callerStrippedSlots)) {
                return true;
            }
        }
        return false;
    }

    static boolean areAllMaskSlotsStripped(long mask, Set<Integer> callerStrippedSlots) {
        if (mask < 0L) return false;
        long m = mask;
        while (m != 0L) {
            int bit = Long.numberOfTrailingZeros(m);
            if (!callerStrippedSlots.contains(Integer.valueOf(bit))) return false;
            m &= (m - 1L);
        }
        return true;
    }

    static AbstractInsnNode nextRealInsn(AbstractInsnNode[] insns, int fromIdx) {
        for (int i = fromIdx + 1; i < insns.length; i++) {
            if (insns[i].getOpcode() >= 0) return insns[i];
        }
        return null;
    }

    static boolean isContiguousWithoutTargets(
        List<AbstractInsnNode> keyPush,
        AbstractInsnNode commuteNode,
        AbstractInsnNode popNode,
        AbstractInsnNode[] insns,
        Map<AbstractInsnNode, Integer> insnIndex,
        Set<LabelNode> targetedLabels
    ) {
        int start = insnIndex.get(keyPush.get(0)).intValue();
        int end = insnIndex.get(popNode != null ? popNode : commuteNode).intValue();
        Set<AbstractInsnNode> allowed = Collections.newSetFromMap(new IdentityHashMap<AbstractInsnNode, Boolean>());
        allowed.addAll(keyPush);
        allowed.add(commuteNode);
        if (popNode != null) allowed.add(popNode);

        for (int i = start; i <= end; i++) {
            AbstractInsnNode cur = insns[i];
            if (cur instanceof LabelNode) {
                if (i > start && targetedLabels.contains(cur)) return false;
                continue;
            }
            if (cur instanceof LineNumberNode || cur instanceof FrameNode) continue;
            if (!allowed.contains(cur)) return false;
        }
        return true;
    }

    static Set<LabelNode> collectTargetedLabels(MethodNode mn) {
        Set<LabelNode> set = Collections.newSetFromMap(new IdentityHashMap<LabelNode, Boolean>());
        for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
            if (insn instanceof JumpInsnNode) {
                set.add(((JumpInsnNode) insn).label);
            } else if (insn instanceof TableSwitchInsnNode) {
                TableSwitchInsnNode ts = (TableSwitchInsnNode) insn;
                if (ts.dflt != null) set.add(ts.dflt);
                if (ts.labels != null) set.addAll(ts.labels);
            } else if (insn instanceof LookupSwitchInsnNode) {
                LookupSwitchInsnNode ls = (LookupSwitchInsnNode) insn;
                if (ls.dflt != null) set.add(ls.dflt);
                if (ls.labels != null) set.addAll(ls.labels);
            }
        }
        if (mn.tryCatchBlocks != null) {
            for (TryCatchBlockNode tcb : mn.tryCatchBlocks) {
                if (tcb.start != null) set.add(tcb.start);
                if (tcb.end != null) set.add(tcb.end);
                if (tcb.handler != null) set.add(tcb.handler);
            }
        }
        return set;
    }

    static int[] computeStackConsumerCounts(
        AbstractInsnNode[] insns,
        Frame<SourceValue>[] frames,
        Map<AbstractInsnNode, Integer> insnIndex
    ) {
        int[] counts = new int[insns.length];
        for (int i = 0; i < insns.length; i++) {
            Frame<SourceValue> f = frames[i];
            if (f == null) continue;
            int popped = getPoppedValueCount(insns[i], f);
            int stackSize = f.getStackSize();
            for (int k = 0; k < popped && (stackSize - 1 - k) >= 0; k++) {
                SourceValue sv = f.getStack(stackSize - 1 - k);
                for (AbstractInsnNode prod : sv.insns) {
                    Integer pIdx = insnIndex.get(prod);
                    if (pIdx != null) counts[pIdx.intValue()]++;
                }
            }
        }
        return counts;
    }

    static int getPoppedValueCount(AbstractInsnNode insn, Frame<SourceValue> f) {
        int op = insn.getOpcode();
        if (op < 0) return 0;
        switch (op) {
            case Opcodes.NOP:
            case Opcodes.ACONST_NULL:
            case Opcodes.ICONST_M1:
            case Opcodes.ICONST_0:
            case Opcodes.ICONST_1:
            case Opcodes.ICONST_2:
            case Opcodes.ICONST_3:
            case Opcodes.ICONST_4:
            case Opcodes.ICONST_5:
            case Opcodes.LCONST_0:
            case Opcodes.LCONST_1:
            case Opcodes.FCONST_0:
            case Opcodes.FCONST_1:
            case Opcodes.FCONST_2:
            case Opcodes.DCONST_0:
            case Opcodes.DCONST_1:
            case Opcodes.BIPUSH:
            case Opcodes.SIPUSH:
            case Opcodes.LDC:
            case Opcodes.ILOAD:
            case Opcodes.LLOAD:
            case Opcodes.FLOAD:
            case Opcodes.DLOAD:
            case Opcodes.ALOAD:
            case Opcodes.IINC:
            case Opcodes.GOTO:
            case Opcodes.JSR:
            case Opcodes.RET:
            case Opcodes.RETURN:
            case Opcodes.GETSTATIC:
            case Opcodes.NEW:
                return 0;

            case Opcodes.ISTORE:
            case Opcodes.LSTORE:
            case Opcodes.FSTORE:
            case Opcodes.DSTORE:
            case Opcodes.ASTORE:
            case Opcodes.POP:
            case Opcodes.DUP:
            case Opcodes.INEG:
            case Opcodes.LNEG:
            case Opcodes.FNEG:
            case Opcodes.DNEG:
            case Opcodes.I2L:
            case Opcodes.I2F:
            case Opcodes.I2D:
            case Opcodes.L2I:
            case Opcodes.L2F:
            case Opcodes.L2D:
            case Opcodes.F2I:
            case Opcodes.F2L:
            case Opcodes.F2D:
            case Opcodes.D2I:
            case Opcodes.D2L:
            case Opcodes.D2F:
            case Opcodes.I2B:
            case Opcodes.I2C:
            case Opcodes.I2S:
            case Opcodes.IFEQ:
            case Opcodes.IFNE:
            case Opcodes.IFLT:
            case Opcodes.IFGE:
            case Opcodes.IFGT:
            case Opcodes.IFLE:
            case Opcodes.TABLESWITCH:
            case Opcodes.LOOKUPSWITCH:
            case Opcodes.IRETURN:
            case Opcodes.LRETURN:
            case Opcodes.FRETURN:
            case Opcodes.DRETURN:
            case Opcodes.ARETURN:
            case Opcodes.PUTSTATIC:
            case Opcodes.GETFIELD:
            case Opcodes.NEWARRAY:
            case Opcodes.ANEWARRAY:
            case Opcodes.ARRAYLENGTH:
            case Opcodes.ATHROW:
            case Opcodes.CHECKCAST:
            case Opcodes.INSTANCEOF:
            case Opcodes.MONITORENTER:
            case Opcodes.MONITOREXIT:
            case Opcodes.IFNULL:
            case Opcodes.IFNONNULL:
                return 1;

            case Opcodes.POP2:
            case Opcodes.DUP2: {
                if (f.getStackSize() >= 1 && f.getStack(f.getStackSize() - 1).getSize() == 2) return 1;
                return 2;
            }
            case Opcodes.DUP_X1:
            case Opcodes.SWAP:
            case Opcodes.IALOAD:
            case Opcodes.LALOAD:
            case Opcodes.FALOAD:
            case Opcodes.DALOAD:
            case Opcodes.AALOAD:
            case Opcodes.BALOAD:
            case Opcodes.CALOAD:
            case Opcodes.SALOAD:
            case Opcodes.IADD:
            case Opcodes.LADD:
            case Opcodes.FADD:
            case Opcodes.DADD:
            case Opcodes.ISUB:
            case Opcodes.LSUB:
            case Opcodes.FSUB:
            case Opcodes.DSUB:
            case Opcodes.IMUL:
            case Opcodes.LMUL:
            case Opcodes.FMUL:
            case Opcodes.DMUL:
            case Opcodes.IDIV:
            case Opcodes.LDIV:
            case Opcodes.FDIV:
            case Opcodes.DDIV:
            case Opcodes.IREM:
            case Opcodes.LREM:
            case Opcodes.FREM:
            case Opcodes.DREM:
            case Opcodes.ISHL:
            case Opcodes.LSHL:
            case Opcodes.ISHR:
            case Opcodes.LSHR:
            case Opcodes.IUSHR:
            case Opcodes.LUSHR:
            case Opcodes.IAND:
            case Opcodes.LAND:
            case Opcodes.IOR:
            case Opcodes.LOR:
            case Opcodes.IXOR:
            case Opcodes.LXOR:
            case Opcodes.LCMP:
            case Opcodes.FCMPL:
            case Opcodes.FCMPG:
            case Opcodes.DCMPL:
            case Opcodes.DCMPG:
            case Opcodes.IF_ICMPEQ:
            case Opcodes.IF_ICMPNE:
            case Opcodes.IF_ICMPLT:
            case Opcodes.IF_ICMPGE:
            case Opcodes.IF_ICMPGT:
            case Opcodes.IF_ICMPLE:
            case Opcodes.IF_ACMPEQ:
            case Opcodes.IF_ACMPNE:
            case Opcodes.PUTFIELD:
                return 2;

            case Opcodes.DUP_X2: {
                if (f.getStackSize() >= 2 && f.getStack(f.getStackSize() - 2).getSize() == 2) return 2;
                return 3;
            }
            case Opcodes.DUP2_X1: {
                if (f.getStackSize() >= 1 && f.getStack(f.getStackSize() - 1).getSize() == 2) return 2;
                return 3;
            }
            case Opcodes.DUP2_X2: {
                int top1 = f.getStack(f.getStackSize() - 1).getSize();
                if (top1 == 2) {
                    int top2 = f.getStack(f.getStackSize() - 2).getSize();
                    return top2 == 2 ? 2 : 3;
                } else {
                    int top3 = f.getStack(f.getStackSize() - 3).getSize();
                    return top3 == 2 ? 3 : 4;
                }
            }
            case Opcodes.IASTORE:
            case Opcodes.LASTORE:
            case Opcodes.FASTORE:
            case Opcodes.DASTORE:
            case Opcodes.AASTORE:
            case Opcodes.BASTORE:
            case Opcodes.CASTORE:
            case Opcodes.SASTORE:
                return 3;

            case Opcodes.INVOKEVIRTUAL:
            case Opcodes.INVOKESPECIAL:
            case Opcodes.INVOKESTATIC:
            case Opcodes.INVOKEINTERFACE: {
                MethodInsnNode mi = (MethodInsnNode) insn;
                int args = Type.getArgumentTypes(mi.desc).length;
                return (op == Opcodes.INVOKESTATIC) ? args : args + 1;
            }
            case Opcodes.INVOKEDYNAMIC: {
                InvokeDynamicInsnNode indy = (InvokeDynamicInsnNode) insn;
                return Type.getArgumentTypes(indy.desc).length;
            }
            case Opcodes.MULTIANEWARRAY:
                return ((MultiANewArrayInsnNode) insn).dims;
            default:
                return 0;
        }
    }

    static void peepholeCleanDeadZkmLocals(MethodNode mn) {
        if (mn.instructions == null || mn.instructions.size() == 0) return;
        Set<LabelNode> targetedLabels = collectTargetedLabels(mn);

        boolean changed = true;
        while (changed) {
            changed = false;

            // 1. Replace STORE to any local slot that has 0 LOAD/IINC/RET instructions with POP/POP2
            Set<Integer> readSlots = new HashSet<Integer>();
            for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
                int op = insn.getOpcode();
                if (insn instanceof VarInsnNode) {
                    if ((op >= Opcodes.ILOAD && op <= Opcodes.ALOAD) || op == Opcodes.RET) {
                        readSlots.add(Integer.valueOf(((VarInsnNode) insn).var));
                    }
                } else if (insn instanceof IincInsnNode) {
                    readSlots.add(Integer.valueOf(((IincInsnNode) insn).var));
                }
            }

            Set<Integer> eliminatedDeadSlots = null;
            for (AbstractInsnNode insn : mn.instructions.toArray()) {
                if (!(insn instanceof VarInsnNode)) continue;
                int op = insn.getOpcode();
                if (op >= Opcodes.ISTORE && op <= Opcodes.ASTORE) {
                    int v = ((VarInsnNode) insn).var;
                    if (!readSlots.contains(Integer.valueOf(v))) {
                        int popOp = (op == Opcodes.LSTORE || op == Opcodes.DSTORE) ? Opcodes.POP2 : Opcodes.POP;
                        mn.instructions.set(insn, new InsnNode(popOp));
                        if (eliminatedDeadSlots == null) eliminatedDeadSlots = new HashSet<Integer>();
                        eliminatedDeadSlots.add(Integer.valueOf(v));
                        changed = true;
                    }
                }
            }
            if (eliminatedDeadSlots != null && mn.localVariables != null) {
                Iterator<LocalVariableNode> it = mn.localVariables.iterator();
                while (it.hasNext()) {
                    LocalVariableNode lvn = it.next();
                    if (eliminatedDeadSlots.contains(Integer.valueOf(lvn.index))) {
                        it.remove();
                    }
                }
            }

            // 2. Label-safe pure-computation + POP/POP2 peephole fold
            boolean peepChanged = true;
            while (peepChanged) {
                peepChanged = false;
                for (AbstractInsnNode insn : mn.instructions.toArray()) {
                    if (insn.getNext() == null && mn.instructions.indexOf(insn) < 0) continue;
                    int popOp = insn.getOpcode();
                    if (popOp != Opcodes.POP && popOp != Opcodes.POP2) continue;
                    AbstractInsnNode prev = prevInSameBlock(insn, targetedLabels);
                    if (prev == null) continue;
                    int prevOp = prev.getOpcode();

                    if (popOp == Opcodes.POP) {
                        if (isPure1SlotPushInsn(prev) || prevOp == Opcodes.DUP) {
                            mn.instructions.remove(prev);
                            mn.instructions.remove(insn);
                            peepChanged = true;
                            changed = true;
                        } else if (prevOp == Opcodes.INEG || prevOp == Opcodes.FNEG
                            || prevOp == Opcodes.I2F || prevOp == Opcodes.F2I
                            || prevOp == Opcodes.I2B || prevOp == Opcodes.I2C || prevOp == Opcodes.I2S) {
                            mn.instructions.remove(prev);
                            peepChanged = true;
                            changed = true;
                        } else if (prevOp == Opcodes.L2I || prevOp == Opcodes.L2F || prevOp == Opcodes.D2I || prevOp == Opcodes.D2F) {
                            mn.instructions.remove(prev);
                            mn.instructions.set(insn, new InsnNode(Opcodes.POP2));
                            peepChanged = true;
                            changed = true;
                        } else if (prevOp == Opcodes.IADD || prevOp == Opcodes.ISUB || prevOp == Opcodes.IMUL
                            || prevOp == Opcodes.IAND || prevOp == Opcodes.IOR || prevOp == Opcodes.IXOR
                            || prevOp == Opcodes.ISHL || prevOp == Opcodes.ISHR || prevOp == Opcodes.IUSHR
                            || prevOp == Opcodes.FADD || prevOp == Opcodes.FSUB || prevOp == Opcodes.FMUL || prevOp == Opcodes.FDIV
                            || prevOp == Opcodes.FCMPL || prevOp == Opcodes.FCMPG) {
                            mn.instructions.set(prev, new InsnNode(Opcodes.POP));
                            peepChanged = true;
                            changed = true;
                        } else if (prevOp == Opcodes.LCMP || prevOp == Opcodes.DCMPL || prevOp == Opcodes.DCMPG) {
                            mn.instructions.set(prev, new InsnNode(Opcodes.POP2));
                            mn.instructions.set(insn, new InsnNode(Opcodes.POP2));
                            peepChanged = true;
                            changed = true;
                        }
                    } else { // POP2
                        if (isPure2SlotPushInsn(prev)) {
                            mn.instructions.remove(prev);
                            mn.instructions.remove(insn);
                            peepChanged = true;
                            changed = true;
                        } else if (prevOp == Opcodes.DUP2) {
                            // DUP2 either duplicates one 2-slot value or two 1-slot values; in both cases DUP2 + POP2 is exact NOP!
                            mn.instructions.remove(prev);
                            mn.instructions.remove(insn);
                            peepChanged = true;
                            changed = true;
                        } else if (prevOp == Opcodes.LNEG || prevOp == Opcodes.DNEG || prevOp == Opcodes.L2D || prevOp == Opcodes.D2L) {
                            mn.instructions.remove(prev);
                            peepChanged = true;
                            changed = true;
                        } else if (prevOp == Opcodes.I2L || prevOp == Opcodes.I2D || prevOp == Opcodes.F2L || prevOp == Opcodes.F2D) {
                            mn.instructions.remove(prev);
                            mn.instructions.set(insn, new InsnNode(Opcodes.POP));
                            peepChanged = true;
                            changed = true;
                        } else if (prevOp == Opcodes.LADD || prevOp == Opcodes.LSUB || prevOp == Opcodes.LMUL
                            || prevOp == Opcodes.LAND || prevOp == Opcodes.LOR || prevOp == Opcodes.LXOR
                            || prevOp == Opcodes.DADD || prevOp == Opcodes.DSUB || prevOp == Opcodes.DMUL || prevOp == Opcodes.DDIV) {
                            mn.instructions.set(prev, new InsnNode(Opcodes.POP2));
                            peepChanged = true;
                            changed = true;
                        } else if (prevOp == Opcodes.LSHL || prevOp == Opcodes.LSHR || prevOp == Opcodes.LUSHR) {
                            // Stack before LSHL: [..., J, I] -> pop I first (POP), then pop J (POP2)
                            mn.instructions.set(prev, new InsnNode(Opcodes.POP));
                            peepChanged = true;
                            changed = true;
                        }
                    }
                }
            }
        }
    }

    static AbstractInsnNode prevInSameBlock(AbstractInsnNode cursor, Set<LabelNode> targetedLabels) {
        AbstractInsnNode p = cursor.getPrevious();
        while (p != null) {
            if (p instanceof LabelNode) {
                if (targetedLabels.contains(p)) return null;
                p = p.getPrevious();
                continue;
            }
            if (p instanceof LineNumberNode || p instanceof FrameNode || p.getOpcode() == Opcodes.NOP) {
                p = p.getPrevious();
                continue;
            }
            return p;
        }
        return null;
    }

    static boolean isPure1SlotPushInsn(AbstractInsnNode insn) {
        int op = insn.getOpcode();
        if (op == Opcodes.ACONST_NULL) return true;
        if (op >= Opcodes.ICONST_M1 && op <= Opcodes.ICONST_5) return true;
        if (op >= Opcodes.FCONST_0 && op <= Opcodes.FCONST_2) return true;
        if (op == Opcodes.BIPUSH || op == Opcodes.SIPUSH) return true;
        if (op == Opcodes.LDC) {
            Object cst = ((LdcInsnNode) insn).cst;
            return !(cst instanceof Long || cst instanceof Double);
        }
        return (op == Opcodes.ILOAD || op == Opcodes.FLOAD || op == Opcodes.ALOAD);
    }

    static boolean isPure2SlotPushInsn(AbstractInsnNode insn) {
        int op = insn.getOpcode();
        if (op == Opcodes.LCONST_0 || op == Opcodes.LCONST_1 || op == Opcodes.DCONST_0 || op == Opcodes.DCONST_1) return true;
        if (op == Opcodes.LDC) {
            Object cst = ((LdcInsnNode) insn).cst;
            return (cst instanceof Long || cst instanceof Double);
        }
        return (op == Opcodes.LLOAD || op == Opcodes.DLOAD);
    }

    static void stripDeclLocalsInMethodBody(String ownerClass, MethodNode mn, ZkmRule rule) {
        if (rule.strippedIndices.length == 0) return;
        Type[] origArgs = Type.getArgumentTypes(mn.desc);
        boolean isStatic = (mn.access & Opcodes.ACC_STATIC) != 0;

        Set<Integer> strippedIdxSet = new HashSet<Integer>();
        for (int idx : rule.strippedIndices) strippedIdxSet.add(Integer.valueOf(idx));

        int[] paramSlot = new int[origArgs.length];
        int cur = isStatic ? 0 : 1;
        for (int p = 0; p < origArgs.length; p++) {
            paramSlot[p] = cur;
            cur += origArgs[p].getSize();
        }

        // Relocate any dead-on-entry stripped parameter slot that was reused as a scratch local
        int nextFresh = Math.max(mn.maxLocals, computeActualMaxLocals(mn));
        for (int p : rule.strippedIndices) {
            int sSlot = paramSlot[p];
            boolean touched = false;
            for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
                if ((insn instanceof VarInsnNode && ((VarInsnNode) insn).var == sSlot)
                    || (insn instanceof IincInsnNode && ((IincInsnNode) insn).var == sSlot)) {
                    touched = true;
                    break;
                }
            }
            if (touched) {
                boolean liveOnEntry = isSlotLiveOnEntry(mn, sSlot);
                if (liveOnEntry && !rule.canonicalConstants.containsKey(Integer.valueOf(p))) {
                    throw new IllegalStateException(
                        "DECL_STRIP parameter index " + p + " (slot " + sSlot + ") is live on entry in "
                        + ownerClass + "." + mn.name + mn.desc
                    );
                }
                int relocated = nextFresh;
                nextFresh += origArgs[p].getSize();
                for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
                    if (insn instanceof VarInsnNode && ((VarInsnNode) insn).var == sSlot) {
                        ((VarInsnNode) insn).var = relocated;
                    } else if (insn instanceof IincInsnNode && ((IincInsnNode) insn).var == sSlot) {
                        ((IincInsnNode) insn).var = relocated;
                    }
                }
                if (liveOnEntry) {
                    InsnList initSlot = new InsnList();
                    pushConstant(initSlot, origArgs[p], rule.canonicalConstants.get(Integer.valueOf(p)));
                    initSlot.add(new VarInsnNode(origArgs[p].getOpcode(Opcodes.ISTORE), relocated));
                    mn.instructions.insert(initSlot);
                }
            }
        }

        int mapLen = Math.max(nextFresh + 16, cur + 16);
        int[] legToClean = new int[mapLen];
        if (!isStatic) legToClean[0] = 0;
        int legS = isStatic ? 0 : 1;
        int cleanS = isStatic ? 0 : 1;
        for (int p = 0; p < origArgs.length; p++) {
            int sz = origArgs[p].getSize();
            if (strippedIdxSet.contains(Integer.valueOf(p))) {
                for (int k = 0; k < sz; k++) legToClean[legS + k] = -1;
                legS += sz;
            } else {
                for (int k = 0; k < sz; k++) legToClean[legS + k] = cleanS + k;
                legS += sz;
                cleanS += sz;
            }
        }
        int delta = legS - cleanS;
        for (int s = legS; s < mapLen; s++) {
            legToClean[s] = s - delta;
        }

        for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
            if (insn instanceof VarInsnNode) {
                VarInsnNode vi = (VarInsnNode) insn;
                int mapped = (vi.var >= 0 && vi.var < legToClean.length) ? legToClean[vi.var] : (vi.var - delta);
                if (mapped < 0) {
                    throw new IllegalStateException("Unrelocated stripped slot " + vi.var + " in " + ownerClass + "." + mn.name + mn.desc);
                }
                vi.var = mapped;
            } else if (insn instanceof IincInsnNode) {
                IincInsnNode ii = (IincInsnNode) insn;
                int mapped = (ii.var >= 0 && ii.var < legToClean.length) ? legToClean[ii.var] : (ii.var - delta);
                if (mapped < 0) {
                    throw new IllegalStateException("Unrelocated stripped slot " + ii.var + " in " + ownerClass + "." + mn.name + mn.desc);
                }
                ii.var = mapped;
            }
        }

        if (mn.localVariables != null) {
            Iterator<LocalVariableNode> it = mn.localVariables.iterator();
            while (it.hasNext()) {
                LocalVariableNode lvn = it.next();
                int mapped = (lvn.index >= 0 && lvn.index < legToClean.length) ? legToClean[lvn.index] : (lvn.index - delta);
                if (mapped < 0) {
                    it.remove();
                } else {
                    lvn.index = mapped;
                }
            }
        }
        remapLocalVarAnnotations(mn.visibleLocalVariableAnnotations, legToClean, delta);
        remapLocalVarAnnotations(mn.invisibleLocalVariableAnnotations, legToClean, delta);
        stripFrames(mn);
    }

    static void remapLocalVarAnnotations(List<LocalVariableAnnotationNode> list, int[] legToClean, int delta) {
        if (list == null) return;
        Iterator<LocalVariableAnnotationNode> it = list.iterator();
        while (it.hasNext()) {
            LocalVariableAnnotationNode an = it.next();
            if (an.index == null) continue;
            for (int i = an.index.size() - 1; i >= 0; i--) {
                int idx = an.index.get(i).intValue();
                int mapped = (idx >= 0 && idx < legToClean.length) ? legToClean[idx] : (idx - delta);
                if (mapped < 0) {
                    an.index.remove(i);
                    if (an.start != null && i < an.start.size()) an.start.remove(i);
                    if (an.end != null && i < an.end.size()) an.end.remove(i);
                } else {
                    an.index.set(i, Integer.valueOf(mapped));
                }
            }
            if (an.index.isEmpty()) it.remove();
        }
    }

    static boolean isSlotLiveOnEntry(MethodNode mn, int targetSlot) {
        if (mn.instructions == null || mn.instructions.size() == 0) return false;
        AbstractInsnNode[] insns = mn.instructions.toArray();
        Map<LabelNode, Integer> labelIdx = new IdentityHashMap<LabelNode, Integer>();
        for (int i = 0; i < insns.length; i++) {
            if (insns[i] instanceof LabelNode) labelIdx.put((LabelNode) insns[i], Integer.valueOf(i));
        }
        boolean[] visited = new boolean[insns.length];
        Deque<Integer> q = new ArrayDeque<Integer>();
        q.add(Integer.valueOf(0));
        visited[0] = true;

        while (!q.isEmpty()) {
            int idx = q.poll().intValue();
            AbstractInsnNode insn = insns[idx];
            int op = insn.getOpcode();
            if (insn instanceof VarInsnNode && ((VarInsnNode) insn).var == targetSlot) {
                if ((op >= Opcodes.ILOAD && op <= Opcodes.ALOAD) || op == Opcodes.RET) return true;
                if (op >= Opcodes.ISTORE && op <= Opcodes.ASTORE) continue;
            } else if (insn instanceof IincInsnNode && ((IincInsnNode) insn).var == targetSlot) {
                return true;
            }

            if (mn.tryCatchBlocks != null) {
                for (TryCatchBlockNode tcb : mn.tryCatchBlocks) {
                    Integer s = labelIdx.get(tcb.start);
                    Integer e = labelIdx.get(tcb.end);
                    Integer h = labelIdx.get(tcb.handler);
                    if (s != null && e != null && h != null && idx >= s.intValue() && idx < e.intValue() && !visited[h.intValue()]) {
                        visited[h.intValue()] = true;
                        q.add(h);
                    }
                }
            }

            if (insn instanceof JumpInsnNode) {
                JumpInsnNode ji = (JumpInsnNode) insn;
                Integer t = labelIdx.get(ji.label);
                if (t != null && !visited[t.intValue()]) {
                    visited[t.intValue()] = true;
                    q.add(t);
                }
                if (op != Opcodes.GOTO && op != Opcodes.JSR && idx + 1 < insns.length && !visited[idx + 1]) {
                    visited[idx + 1] = true;
                    q.add(Integer.valueOf(idx + 1));
                }
            } else if (insn instanceof TableSwitchInsnNode) {
                TableSwitchInsnNode ts = (TableSwitchInsnNode) insn;
                if (ts.dflt != null) {
                    Integer t = labelIdx.get(ts.dflt);
                    if (t != null && !visited[t.intValue()]) { visited[t.intValue()] = true; q.add(t); }
                }
                if (ts.labels != null) {
                    for (LabelNode l : ts.labels) {
                        Integer t = labelIdx.get(l);
                        if (t != null && !visited[t.intValue()]) { visited[t.intValue()] = true; q.add(t); }
                    }
                }
            } else if (insn instanceof LookupSwitchInsnNode) {
                LookupSwitchInsnNode ls = (LookupSwitchInsnNode) insn;
                if (ls.dflt != null) {
                    Integer t = labelIdx.get(ls.dflt);
                    if (t != null && !visited[t.intValue()]) { visited[t.intValue()] = true; q.add(t); }
                }
                if (ls.labels != null) {
                    for (LabelNode l : ls.labels) {
                        Integer t = labelIdx.get(l);
                        if (t != null && !visited[t.intValue()]) { visited[t.intValue()] = true; q.add(t); }
                    }
                }
            } else if (op != Opcodes.RETURN && op != Opcodes.IRETURN && op != Opcodes.LRETURN
                && op != Opcodes.FRETURN && op != Opcodes.DRETURN && op != Opcodes.ARETURN
                && op != Opcodes.ATHROW && op != Opcodes.RET) {
                if (idx + 1 < insns.length && !visited[idx + 1]) {
                    visited[idx + 1] = true;
                    q.add(Integer.valueOf(idx + 1));
                }
            }
        }
        return false;
    }

    static int computeActualMaxLocals(MethodNode mn) {
        int max = (mn.access & Opcodes.ACC_STATIC) != 0 ? 0 : 1;
        for (Type t : Type.getArgumentTypes(mn.desc)) {
            max += t.getSize();
        }
        if (mn.instructions != null) {
            for (AbstractInsnNode insn = mn.instructions.getFirst(); insn != null; insn = insn.getNext()) {
                if (insn instanceof VarInsnNode) {
                    VarInsnNode vi = (VarInsnNode) insn;
                    int op = vi.getOpcode();
                    int sz = (op == Opcodes.LLOAD || op == Opcodes.DLOAD || op == Opcodes.LSTORE || op == Opcodes.DSTORE) ? 2 : 1;
                    if (vi.var + sz > max) max = vi.var + sz;
                } else if (insn instanceof IincInsnNode) {
                    IincInsnNode ii = (IincInsnNode) insn;
                    if (ii.var + 1 > max) max = ii.var + 1;
                }
            }
        }
        return max;
    }

    static void remapIndySamNames(
        ClassNode cn,
        Remapper activeRemapper,
        boolean isForward,
        Map<String, String> legToRuntimeMethod,
        Map<String, List<String>> intermParentsMap,
        Map<String, List<String>> intermAncestorsCache
    ) {
        for (MethodNode mn : cn.methods) {
            for (AbstractInsnNode insn : mn.instructions.toArray()) {
                if (insn instanceof InvokeDynamicInsnNode) {
                    InvokeDynamicInsnNode indy = (InvokeDynamicInsnNode) insn;
                    if (indy.bsm != null
                        && "java/lang/invoke/LambdaMetafactory".equals(indy.bsm.getOwner())
                        && indy.bsmArgs != null
                        && indy.bsmArgs.length >= 1
                        && indy.bsmArgs[0] instanceof Type) {
                        Type retType = Type.getReturnType(indy.desc);
                        if (retType.getSort() == Type.OBJECT) {
                            String samOwner = retType.getInternalName();
                            String samDesc = ((Type) indy.bsmArgs[0]).getDescriptor();
                            String mapped = activeRemapper.mapMethodName(samOwner, indy.name, samDesc);
                            if (!isForward) {
                                String base = mapped;
                                if (base.startsWith("method_") && base.matches(".*\\$[0-9a-f]{10}$")) {
                                    base = base.substring(0, base.length() - 11);
                                }
                                String intermOwner = activeRemapper.map(samOwner);
                                String intermDesc = activeRemapper.mapMethodDesc(samDesc);
                                String rt = legToRuntimeMethod.get(intermOwner + "\t" + base + "\t" + intermDesc);
                                if (rt == null) {
                                    for (String anc : getAncestors(intermOwner, intermParentsMap, intermAncestorsCache)) {
                                        rt = legToRuntimeMethod.get(anc + "\t" + base + "\t" + intermDesc);
                                        if (rt != null) break;
                                    }
                                }
                                if (rt == null && base.startsWith("method_") && base.length() > 7 && Character.isDigit(base.charAt(7))) {
                                    rt = base.substring(7);
                                }
                                mapped = (rt != null) ? rt : base;
                            }
                            indy.name = mapped;
                        }
                    }
                }
            }
        }
    }

    static String resolveForwardMethodName(
        String legOwner,
        String legName,
        String legDesc,
        Map<String, String> forwardMethodExact,
        Map<String, List<String>> intermParentsMap,
        Map<String, List<String>> intermAncestorsCache
    ) {
        if (legName.startsWith("<")) return legName;
        String direct = forwardMethodExact.get(legOwner + "\t" + legName + "\t" + legDesc);
        if (direct != null) return direct;
        for (String anc : getAncestors(legOwner, intermParentsMap, intermAncestorsCache)) {
            String res = forwardMethodExact.get(anc + "\t" + legName + "\t" + legDesc);
            if (res != null) return res;
        }
        return legName;
    }

    static boolean hasMethod(ClassNode cn, String name, String desc) {
        for (MethodNode mn : cn.methods) {
            if (mn.name.equals(name) && mn.desc.equals(desc)) return true;
        }
        return false;
    }

    static void addDummyReturn(InsnList il, String desc) {
        Type ret = Type.getReturnType(desc);
        switch (ret.getSort()) {
            case Type.VOID:
                il.add(new InsnNode(Opcodes.RETURN));
                break;
            case Type.BOOLEAN:
            case Type.BYTE:
            case Type.CHAR:
            case Type.SHORT:
            case Type.INT:
                il.add(new InsnNode(Opcodes.ICONST_0));
                il.add(new InsnNode(Opcodes.IRETURN));
                break;
            case Type.LONG:
                il.add(new InsnNode(Opcodes.LCONST_0));
                il.add(new InsnNode(Opcodes.LRETURN));
                break;
            case Type.FLOAT:
                il.add(new InsnNode(Opcodes.FCONST_0));
                il.add(new InsnNode(Opcodes.FRETURN));
                break;
            case Type.DOUBLE:
                il.add(new InsnNode(Opcodes.DCONST_0));
                il.add(new InsnNode(Opcodes.DRETURN));
                break;
            default:
                il.add(new InsnNode(Opcodes.ACONST_NULL));
                il.add(new InsnNode(Opcodes.ARETURN));
                break;
        }
    }

    static void applyMethodStrip(MethodNode mn, int[] strippedIndices) {
        Set<Integer> stripped = new HashSet<Integer>();
        for (int idx : strippedIndices) stripped.add(Integer.valueOf(idx));

        Type[] origArgs = Type.getArgumentTypes(mn.desc);
        mn.desc = computeCleanDesc(mn.desc, strippedIndices);

        if (mn.parameters != null) {
            List<ParameterNode> newParams = new ArrayList<ParameterNode>();
            for (int i = 0; i < mn.parameters.size(); i++) {
                if (!stripped.contains(Integer.valueOf(i))) newParams.add(mn.parameters.get(i));
            }
            mn.parameters = newParams;
        }

        if (mn.visibleParameterAnnotations != null) {
            int newCount = origArgs.length - stripped.size();
            @SuppressWarnings("unchecked")
            List<AnnotationNode>[] newAnn = new List[newCount];
            int dst = 0;
            for (int i = 0; i < origArgs.length && i < mn.visibleParameterAnnotations.length; i++) {
                if (!stripped.contains(Integer.valueOf(i)) && dst < newCount) {
                    newAnn[dst++] = mn.visibleParameterAnnotations[i];
                }
            }
            mn.visibleParameterAnnotations = newAnn;
            mn.visibleAnnotableParameterCount = newCount;
        }

        if (mn.invisibleParameterAnnotations != null) {
            int newCount = origArgs.length - stripped.size();
            @SuppressWarnings("unchecked")
            List<AnnotationNode>[] newAnn = new List[newCount];
            int dst = 0;
            for (int i = 0; i < origArgs.length && i < mn.invisibleParameterAnnotations.length; i++) {
                if (!stripped.contains(Integer.valueOf(i)) && dst < newCount) {
                    newAnn[dst++] = mn.invisibleParameterAnnotations[i];
                }
            }
            mn.invisibleParameterAnnotations = newAnn;
            mn.invisibleAnnotableParameterCount = newCount;
        }
    }

    static boolean applyForwardCallStripStubs(ClassNode cn, List<ZkmRule> rulesForOwner) {
        if (rulesForOwner == null || rulesForOwner.isEmpty()) return false;
        Set<String> existingMethods = new HashSet<String>();
        for (MethodNode mn : cn.methods) {
            existingMethods.add(mn.name + "#" + mn.desc);
        }

        boolean added = false;
        for (ZkmRule rule : rulesForOwner) {
            String sig = rule.readName + "#" + rule.cleanReadDesc;
            if (!existingMethods.contains(sig)) {
                existingMethods.add(sig);
                int copiedFlags = 0;
                List<String> exceptions = null;
                for (MethodNode orig : cn.methods) {
                    if (orig.name.equals(rule.readName) && orig.desc.equals(rule.origReadDesc)) {
                        copiedFlags = orig.access & (Opcodes.ACC_STATIC | Opcodes.ACC_VARARGS);
                        if (orig.exceptions != null && !orig.exceptions.isEmpty()) {
                            exceptions = orig.exceptions;
                        }
                        break;
                    }
                }
                int access = Opcodes.ACC_PUBLIC | copiedFlags;
                MethodNode stub = new MethodNode(
                    access,
                    rule.readName,
                    rule.cleanReadDesc,
                    null,
                    exceptions != null ? exceptions.toArray(new String[0]) : null
                );
                InsnList il = stub.instructions;
                if ("<init>".equals(rule.readName)) {
                    il.add(new VarInsnNode(Opcodes.ALOAD, 0));
                    String sup = cn.superName != null ? cn.superName : "java/lang/Object";
                    il.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, sup, "<init>", "()V", false));
                    il.add(new InsnNode(Opcodes.RETURN));
                } else {
                    addDummyReturn(il, rule.cleanReadDesc);
                }
                cn.methods.add(stub);
                added = true;
            }
        }
        return added;
    }

    static boolean applyReverseDeclRestore(
        ClassNode cn,
        Remapper activeRemapper,
        Map<String, List<ZkmRule>> declStripByCleanReadSig,
        Map<String, List<String>> parentsMap,
        Map<String, List<String>> ancestorsCache,
        int[] stats
    ) {
        String legClass = activeRemapper.map(cn.name);
        boolean modified = false;

        for (MethodNode mn : cn.methods) {
            List<ZkmRule> candidates = declStripByCleanReadSig.get(mn.name + "#" + mn.desc);
            if (candidates == null) continue;

            ZkmRule matchedRule = null;
            for (ZkmRule rule : candidates) {
                if (rule.legOwner.equals(legClass)) {
                    matchedRule = rule;
                    break;
                }
            }
            if (matchedRule == null && !"<init>".equals(mn.name)) {
                for (ZkmRule rule : candidates) {
                    if (isSubtypeOf(cn.name, rule.readOwner, parentsMap, ancestorsCache)) {
                        matchedRule = rule;
                        break;
                    }
                }
            }

            if (matchedRule != null) {
                Type[] origArgs = Type.getArgumentTypes(matchedRule.legDesc);
                boolean isStatic = (mn.access & Opcodes.ACC_STATIC) != 0;

                int[] slotMap = computeCleanToLegacySlotMap(isStatic, origArgs, matchedRule.strippedIndices);
                int totalInsertedSlots = 0;
                for (int idx : matchedRule.strippedIndices) totalInsertedSlots += origArgs[idx].getSize();

                int prevMaxLocals = Math.max(mn.maxLocals, computeActualMaxLocals(mn));
                for (AbstractInsnNode insn : mn.instructions.toArray()) {
                    if (insn instanceof VarInsnNode) {
                        VarInsnNode varInsn = (VarInsnNode) insn;
                        varInsn.var = remapSlot(varInsn.var, slotMap);
                    } else if (insn instanceof IincInsnNode) {
                        IincInsnNode iinc = (IincInsnNode) insn;
                        iinc.var = remapSlot(iinc.var, slotMap);
                    }
                }
                if (mn.localVariables != null) {
                    for (LocalVariableNode lvn : mn.localVariables) {
                        lvn.index = remapSlot(lvn.index, slotMap);
                    }
                }
                mn.maxLocals = prevMaxLocals + totalInsertedSlots;
                mn.desc = matchedRule.origReadDesc;
                if (matchedRule.strippedIndices.length > 0) {
                    mn.signature = null;
                }
                stripFrames(mn);
                modified = true;
                stats[0]++;
            }
        }
        return modified;
    }

    static void stripFrames(MethodNode mn) {
        for (AbstractInsnNode insn : mn.instructions.toArray()) {
            if (insn instanceof FrameNode) {
                mn.instructions.remove(insn);
            } else if (insn instanceof LabelNode) {
                ((LabelNode) insn).resetLabel();
            }
        }
    }

    static void computeExactMaxsAndVerify(ClassNode cn) {
        BasicVerifier bv = new BasicVerifier();
        Analyzer<BasicValue> verifier = new Analyzer<BasicValue>(bv);
        for (MethodNode mn : cn.methods) {
            if ((mn.access & (Opcodes.ACC_ABSTRACT | Opcodes.ACC_NATIVE)) != 0 || mn.instructions == null || mn.instructions.size() == 0) {
                continue;
            }
            stripFrames(mn);
            int exactLocals = computeActualMaxLocals(mn);
            mn.maxLocals = exactLocals;
            int boundStack = Math.min(65535, Math.max(mn.maxStack + 16, 64));
            Frame<BasicValue>[] frames = null;
            while (true) {
                mn.maxStack = boundStack;
                try {
                    frames = verifier.analyze(cn.name, mn);
                    break;
                } catch (Exception ex) {
                    if (boundStack < 65535 && ex.getMessage() != null && ex.getMessage().contains("Insufficient maximum stack size")) {
                        boundStack = Math.min(65535, boundStack * 4);
                        continue;
                    }
                    throw new IllegalStateException(
                        "BasicVerifier failed after --strip-bodies in " + cn.name + "." + mn.name + mn.desc,
                        ex
                    );
                }
            }
            AbstractInsnNode[] insns = mn.instructions.toArray();
            int maxStackSlots = 0;
            if (mn.tryCatchBlocks != null && !mn.tryCatchBlocks.isEmpty()) {
                maxStackSlots = 1;
            }
            for (int i = 0; i < insns.length; i++) {
                Frame<BasicValue> f = frames[i];
                if (f == null) continue;
                int beforeSlots = countStackSlots(f);
                if (beforeSlots > maxStackSlots) maxStackSlots = beforeSlots;
                int op = insns[i].getOpcode();
                if (op == Opcodes.JSR) {
                    if (beforeSlots + 1 > maxStackSlots) maxStackSlots = beforeSlots + 1;
                } else if (op >= 0 && op != Opcodes.GOTO && op != Opcodes.TABLESWITCH && op != Opcodes.LOOKUPSWITCH
                    && op != Opcodes.RET && (op < Opcodes.IRETURN || op > Opcodes.RETURN) && op != Opcodes.ATHROW) {
                    try {
                        Frame<BasicValue> after = new Frame<BasicValue>(f);
                        after.execute(insns[i], bv);
                        int afterSlots = countStackSlots(after);
                        if (afterSlots > maxStackSlots) maxStackSlots = afterSlots;
                    } catch (AnalyzerException ex) {
                        throw new IllegalStateException(
                            "Frame execute failed in " + cn.name + "." + mn.name + mn.desc,
                            ex
                        );
                    }
                }
            }
            mn.maxStack = maxStackSlots;
        }
    }

    static int countStackSlots(Frame<BasicValue> f) {
        int slots = 0;
        int n = f.getStackSize();
        for (int i = 0; i < n; i++) {
            slots += f.getStack(i).getSize();
        }
        return slots;
    }

    static boolean applyReverseCallSiteInjection(
        ClassNode cn,
        Remapper activeRemapper,
        Map<String, List<ZkmRule>> declStripByCleanReadSig,
        Map<String, List<ZkmRule>> callStripByCleanReadSig,
        Map<String, List<ZkmRule>> allRulesByOrigReadSig,
        Map<String, ZkmOverride> callOverrides,
        Map<String, List<String>> parentsMap,
        Map<String, List<String>> ancestorsCache,
        int[] stats
    ) {
        String callerLegClass = activeRemapper.map(cn.name);
        boolean modified = false;

        for (MethodNode mn : cn.methods) {
            if (mn.instructions != null && mn.instructions.size() > 0) {
                mn.maxLocals = Math.max(mn.maxLocals, computeActualMaxLocals(mn));
            }
            String callerLegMethod = activeRemapper.mapMethodName(cn.name, mn.name, mn.desc);
            String callerLegDesc = activeRemapper.mapMethodDesc(mn.desc);
            Map<String, Integer> ruleOrdinals = new HashMap<String, Integer>();
            boolean methodModified = false;

            for (AbstractInsnNode insn : mn.instructions.toArray()) {
                if (!(insn instanceof MethodInsnNode)) continue;
                MethodInsnNode minsn = (MethodInsnNode) insn;

                String legTargetOwner = activeRemapper.map(minsn.owner);
                String cleanSig = minsn.name + "#" + minsn.desc;

                ZkmRule matchedRule = findMatchingRule(callStripByCleanReadSig.get(cleanSig), minsn.owner, legTargetOwner, minsn.name, parentsMap, ancestorsCache);
                if (matchedRule == null) {
                    matchedRule = findMatchingRule(declStripByCleanReadSig.get(cleanSig), minsn.owner, legTargetOwner, minsn.name, parentsMap, ancestorsCache);
                }

                if (matchedRule == null) {
                    ZkmRule unstrippedRule = findMatchingRule(allRulesByOrigReadSig.get(cleanSig), minsn.owner, legTargetOwner, minsn.name, parentsMap, ancestorsCache);
                    if (unstrippedRule != null) {
                        String uRuleId = unstrippedRule.legOwner + "#" + unstrippedRule.legName + "#" + unstrippedRule.legDesc;
                        int prevOrd = ruleOrdinals.containsKey(uRuleId) ? ruleOrdinals.get(uRuleId).intValue() : 0;
                        ruleOrdinals.put(uRuleId, Integer.valueOf(prevOrd + 1));
                    }
                    continue;
                }

                String ruleId = matchedRule.legOwner + "#" + matchedRule.legName + "#" + matchedRule.legDesc;
                int prevOrd = ruleOrdinals.containsKey(ruleId) ? ruleOrdinals.get(ruleId).intValue() : 0;
                ruleOrdinals.put(ruleId, Integer.valueOf(prevOrd + 1));
                int ordinal = prevOrd;

                Map<Integer, Object> constantsToInject = new HashMap<Integer, Object>(matchedRule.canonicalConstants);
                String ovKey = callerLegClass + "#" + callerLegMethod + "#" + callerLegDesc + "#" + ordinal + "#" + ruleId;
                ZkmOverride ov = callOverrides.get(ovKey);
                if (ov != null) {
                    constantsToInject.putAll(ov.constants);
                    stats[2]++;
                }

                Type[] origArgs = Type.getArgumentTypes(matchedRule.legDesc);
                Type[] cleanArgs = Type.getArgumentTypes(minsn.desc);
                Set<Integer> strippedSet = new HashSet<Integer>();
                for (int s : matchedRule.strippedIndices) strippedSet.add(Integer.valueOf(s));

                injectConstantsBeforeCall(mn, minsn, cleanArgs, origArgs, strippedSet, constantsToInject);

                String resolvedLegName = activeRemapper.mapMethodName(minsn.owner, minsn.name, matchedRule.origReadDesc);
                minsn.desc = matchedRule.origReadDesc;
                minsn.name = (resolvedLegName != null && !resolvedLegName.equals(minsn.name)) ? resolvedLegName : matchedRule.legName;
                methodModified = true;
                modified = true;
                stats[1]++;
            }
            if (methodModified) {
                stripFrames(mn);
            }
        }
        return modified;
    }

    static ZkmRule findMatchingRule(
        List<ZkmRule> candidates,
        String readTargetOwner,
        String legTargetOwner,
        String methodName,
        Map<String, List<String>> parentsMap,
        Map<String, List<String>> ancestorsCache
    ) {
        if (candidates == null || candidates.isEmpty()) return null;
        for (ZkmRule rule : candidates) {
            if (rule.legOwner.equals(legTargetOwner)) {
                return rule;
            }
        }
        if (!"<init>".equals(methodName)) {
            for (ZkmRule rule : candidates) {
                if (isSubtypeOf(readTargetOwner, rule.readOwner, parentsMap, ancestorsCache)) {
                    return rule;
                }
            }
        }
        return null;
    }

    static void injectConstantsBeforeCall(
        MethodNode mn,
        MethodInsnNode minsn,
        Type[] cleanArgs,
        Type[] origArgs,
        Set<Integer> strippedSet,
        Map<Integer, Object> constants
    ) {
        if (cleanArgs.length == 0) {
            InsnList pushList = new InsnList();
            for (int p = 0; p < origArgs.length; p++) {
                pushConstant(pushList, origArgs[p], constants.get(Integer.valueOf(p)));
            }
            mn.instructions.insertBefore(minsn, pushList);
            return;
        }

        int firstStripped = Integer.MAX_VALUE;
        for (Integer s : strippedSet) {
            if (s.intValue() < firstStripped) firstStripped = s.intValue();
        }

        int cleanIndexOfFirstStripped = 0;
        for (int p = 0; p < firstStripped; p++) {
            if (!strippedSet.contains(Integer.valueOf(p))) cleanIndexOfFirstStripped++;
        }

        if (cleanIndexOfFirstStripped >= cleanArgs.length) {
            InsnList pushList = new InsnList();
            for (int p = firstStripped; p < origArgs.length; p++) {
                if (strippedSet.contains(Integer.valueOf(p))) {
                    pushConstant(pushList, origArgs[p], constants.get(Integer.valueOf(p)));
                }
            }
            mn.instructions.insertBefore(minsn, pushList);
            return;
        }

        int nextTemp = mn.maxLocals;
        int[] tempSlots = new int[cleanArgs.length];
        for (int a = cleanIndexOfFirstStripped; a < cleanArgs.length; a++) {
            tempSlots[a] = nextTemp;
            nextTemp += cleanArgs[a].getSize();
        }
        mn.maxLocals = nextTemp;

        InsnList popList = new InsnList();
        for (int a = cleanArgs.length - 1; a >= cleanIndexOfFirstStripped; a--) {
            popList.add(new VarInsnNode(getStoreOpcode(cleanArgs[a]), tempSlots[a]));
        }
        mn.instructions.insertBefore(minsn, popList);

        InsnList pushList = new InsnList();
        int cleanArgIdx = cleanIndexOfFirstStripped;
        for (int p = firstStripped; p < origArgs.length; p++) {
            if (strippedSet.contains(Integer.valueOf(p))) {
                pushConstant(pushList, origArgs[p], constants.get(Integer.valueOf(p)));
            } else {
                pushList.add(new VarInsnNode(getLoadOpcode(cleanArgs[cleanArgIdx]), tempSlots[cleanArgIdx]));
                cleanArgIdx++;
            }
        }
        mn.instructions.insertBefore(minsn, pushList);
    }

    static int getStoreOpcode(Type t) {
        switch (t.getSort()) {
            case Type.BOOLEAN:
            case Type.BYTE:
            case Type.CHAR:
            case Type.SHORT:
            case Type.INT:
                return Opcodes.ISTORE;
            case Type.LONG:
                return Opcodes.LSTORE;
            case Type.FLOAT:
                return Opcodes.FSTORE;
            case Type.DOUBLE:
                return Opcodes.DSTORE;
            default:
                return Opcodes.ASTORE;
        }
    }

    static int getLoadOpcode(Type t) {
        switch (t.getSort()) {
            case Type.BOOLEAN:
            case Type.BYTE:
            case Type.CHAR:
            case Type.SHORT:
            case Type.INT:
                return Opcodes.ILOAD;
            case Type.LONG:
                return Opcodes.LLOAD;
            case Type.FLOAT:
                return Opcodes.FLOAD;
            case Type.DOUBLE:
                return Opcodes.DLOAD;
            default:
                return Opcodes.ALOAD;
        }
    }

    static void pushConstant(InsnList list, Type type, Object val) {
        if (val == null) {
            val = getDefaultValue(type);
        }
        switch (type.getSort()) {
            case Type.LONG: {
                long l = ((Number) val).longValue();
                if (l == 0L) list.add(new InsnNode(Opcodes.LCONST_0));
                else if (l == 1L) list.add(new InsnNode(Opcodes.LCONST_1));
                else list.add(new LdcInsnNode(Long.valueOf(l)));
                break;
            }
            case Type.INT:
                pushIntConstant(list, ((Number) val).intValue());
                break;
            case Type.SHORT:
                pushIntConstant(list, ((Number) val).shortValue());
                break;
            case Type.BYTE:
                pushIntConstant(list, ((Number) val).byteValue());
                break;
            case Type.CHAR: {
                char c = (val instanceof Character) ? ((Character) val).charValue() : (char) ((Number) val).intValue();
                pushIntConstant(list, c);
                break;
            }
            case Type.BOOLEAN: {
                boolean b = (val instanceof Boolean) ? ((Boolean) val).booleanValue() : ((Number) val).intValue() != 0;
                list.add(new InsnNode(b ? Opcodes.ICONST_1 : Opcodes.ICONST_0));
                break;
            }
            default:
                list.add(new LdcInsnNode(val));
                break;
        }
    }

    static void pushIntConstant(InsnList list, int n) {
        if (n >= -1 && n <= 5) list.add(new InsnNode(Opcodes.ICONST_0 + n));
        else if (n >= Byte.MIN_VALUE && n <= Byte.MAX_VALUE) list.add(new IntInsnNode(Opcodes.BIPUSH, n));
        else if (n >= Short.MIN_VALUE && n <= Short.MAX_VALUE) list.add(new IntInsnNode(Opcodes.SIPUSH, n));
        else list.add(new LdcInsnNode(Integer.valueOf(n)));
    }

    static Object getDefaultValue(Type t) {
        switch (t.getSort()) {
            case Type.LONG:
                return Long.valueOf(0L);
            case Type.INT:
            case Type.SHORT:
            case Type.BYTE:
                return Integer.valueOf(0);
            case Type.CHAR:
                return Character.valueOf('\0');
            case Type.BOOLEAN:
                return Boolean.FALSE;
            default:
                return Integer.valueOf(0);
        }
    }

    static int[] computeCleanToLegacySlotMap(boolean isStatic, Type[] origArgs, int[] strippedIndices) {
        Set<Integer> strippedSet = new HashSet<Integer>();
        for (int s : strippedIndices) strippedSet.add(Integer.valueOf(s));

        int maxSlots = 1024;
        int[] slotMap = new int[maxSlots];
        int legacySlot = isStatic ? 0 : 1;
        int cleanSlot = isStatic ? 0 : 1;

        for (int p = 0; p < origArgs.length; p++) {
            int size = origArgs[p].getSize();
            if (strippedSet.contains(Integer.valueOf(p))) {
                legacySlot += size;
            } else {
                for (int s = 0; s < size; s++) {
                    slotMap[cleanSlot + s] = legacySlot + s;
                }
                cleanSlot += size;
                legacySlot += size;
            }
        }

        int delta = legacySlot - cleanSlot;
        for (int s = cleanSlot; s < maxSlots; s++) {
            slotMap[s] = s + delta;
        }
        return slotMap;
    }

    static int remapSlot(int slot, int[] slotMap) {
        return (slot >= 0 && slot < slotMap.length) ? slotMap[slot] : slot + (slotMap[slotMap.length - 1] - (slotMap.length - 1));
    }

    static String computeCleanDesc(String desc, int[] strippedIndices) {
        Type[] args = Type.getArgumentTypes(desc);
        Set<Integer> stripped = new HashSet<Integer>();
        for (int i : strippedIndices) stripped.add(Integer.valueOf(i));

        List<Type> clean = new ArrayList<Type>();
        for (int i = 0; i < args.length; i++) {
            if (!stripped.contains(Integer.valueOf(i))) clean.add(args[i]);
        }
        return Type.getMethodDescriptor(Type.getReturnType(desc), clean.toArray(new Type[0]));
    }

    static int[] parseInts(String str) {
        if (str == null || str.trim().isEmpty()) return new int[0];
        String[] parts = str.split(",");
        int[] res = new int[parts.length];
        for (int i = 0; i < parts.length; i++) res[i] = Integer.parseInt(parts[i].trim());
        return res;
    }

    static boolean isSubtypeOf(String subClass, String superClass, Map<String, List<String>> parentsMap, Map<String, List<String>> ancestorsCache) {
        if (subClass.equals(superClass)) return true;
        return getAncestors(subClass, parentsMap, ancestorsCache).contains(superClass);
    }

    static byte[] readAllBytes(InputStream is) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[8192];
        int n;
        while ((n = is.read(buf)) != -1) bos.write(buf, 0, n);
        return bos.toByteArray();
    }

    static void addLegMember(Map<String, List<LegMember>> map, String key, LegMember member) {
        List<LegMember> list = map.get(key);
        if (list == null) {
            list = new ArrayList<LegMember>();
            map.put(key, list);
        }
        list.add(member);
    }

    static void widenAccess(ClassNode cn) {
        if ((cn.access & Opcodes.ACC_PRIVATE) == 0) {
            cn.access = (cn.access & ~Opcodes.ACC_PROTECTED) | Opcodes.ACC_PUBLIC;
        }
        if (cn.innerClasses != null) {
            for (InnerClassNode icn : cn.innerClasses) {
                if ((icn.access & Opcodes.ACC_PRIVATE) == 0) {
                    icn.access = (icn.access & ~Opcodes.ACC_PROTECTED) | Opcodes.ACC_PUBLIC;
                }
            }
        }
        if (cn.fields != null) {
            for (FieldNode fn : cn.fields) {
                fn.access = (fn.access & ~(Opcodes.ACC_PRIVATE | Opcodes.ACC_PROTECTED)) | Opcodes.ACC_PUBLIC;
            }
        }
        if (cn.methods != null) {
            for (MethodNode mn : cn.methods) {
                if (!"<clinit>".equals(mn.name) && (mn.access & Opcodes.ACC_PRIVATE) == 0) {
                    mn.access = (mn.access & ~Opcodes.ACC_PROTECTED) | Opcodes.ACC_PUBLIC;
                }
            }
        }
    }

    static void foldRuntimeSymbolCalls(
        ClassNode cn,
        Remapper reverseRemapper,
        Map<String, String> reverseClass,
        Map<String, List<LegMember>> reverseFieldByName,
        Map<String, List<LegMember>> reverseMethodByName,
        Map<String, String> legToRuntimeClass,
        Map<String, String> legToRuntimeField,
        Map<String, String> legToRuntimeMethod,
        Map<String, Set<String>> legToRuntimeFieldByName,
        Map<String, Set<String>> legToRuntimeMethodByName,
        Map<String, List<String>> parentsMap,
        Map<String, List<String>> childrenMap,
        Map<String, List<String>> ancestorsCache,
        Map<String, List<String>> descendantsCache
    ) {
        for (MethodNode mn : cn.methods) {
            if ("augustus/custom/ClientAccess".equals(cn.name)
                && ("resolveClassName".equals(mn.name) || "resolveFieldName".equals(mn.name) || "resolveMethodName".equals(mn.name))) {
                continue;
            }
            for (AbstractInsnNode insn : mn.instructions.toArray()) {
                if (!(insn instanceof MethodInsnNode)) continue;
                MethodInsnNode call = (MethodInsnNode) insn;
                if (call.getOpcode() != Opcodes.INVOKESTATIC || !"augustus/custom/ClientAccess".equals(call.owner)) continue;
                if ("resolveClassName".equals(call.name) && "(Ljava/lang/String;)Ljava/lang/String;".equals(call.desc)) {
                    LdcInsnNode a1 = prevStringLdc(call);
                    if (a1 == null) throw new IllegalStateException("Non-constant resolveClassName in " + cn.name + "." + mn.name);
                    String resolved = resolveRuntimeClass((String) a1.cst, reverseClass, legToRuntimeClass);
                    mn.instructions.remove(a1);
                    mn.instructions.set(call, new LdcInsnNode(resolved));
                } else if ("resolveFieldName".equals(call.name)
                    && ("(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;".equals(call.desc)
                        || "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;".equals(call.desc))) {
                    boolean hasDesc = call.desc.startsWith("(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)");
                    LdcInsnNode a3 = hasDesc ? prevStringLdc(call) : null;
                    LdcInsnNode a2 = prevStringLdc(hasDesc ? a3 : call);
                    LdcInsnNode a1 = prevStringLdc(a2);
                    if (a1 == null || a2 == null || (hasDesc && a3 == null)) {
                        throw new IllegalStateException("Non-constant resolveFieldName in " + cn.name + "." + mn.name);
                    }
                    String resolved = resolveRuntimeMember(
                        (String) a1.cst,
                        (String) a2.cst,
                        hasDesc ? (String) a3.cst : null,
                        reverseRemapper,
                        reverseClass,
                        reverseFieldByName,
                        legToRuntimeField,
                        legToRuntimeFieldByName,
                        parentsMap,
                        childrenMap,
                        ancestorsCache,
                        descendantsCache,
                        false
                    );
                    mn.instructions.remove(a1);
                    mn.instructions.remove(a2);
                    if (a3 != null) mn.instructions.remove(a3);
                    mn.instructions.set(call, new LdcInsnNode(resolved));
                } else if ("resolveMethodName".equals(call.name)
                    && ("(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;".equals(call.desc)
                        || "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;".equals(call.desc))) {
                    boolean hasDesc = call.desc.startsWith("(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)");
                    LdcInsnNode a3 = hasDesc ? prevStringLdc(call) : null;
                    LdcInsnNode a2 = prevStringLdc(hasDesc ? a3 : call);
                    LdcInsnNode a1 = prevStringLdc(a2);
                    if (a1 == null || a2 == null || (hasDesc && a3 == null)) {
                        throw new IllegalStateException("Non-constant resolveMethodName in " + cn.name + "." + mn.name);
                    }
                    String resolved = resolveRuntimeMember(
                        (String) a1.cst,
                        (String) a2.cst,
                        hasDesc ? (String) a3.cst : null,
                        reverseRemapper,
                        reverseClass,
                        reverseMethodByName,
                        legToRuntimeMethod,
                        legToRuntimeMethodByName,
                        parentsMap,
                        childrenMap,
                        ancestorsCache,
                        descendantsCache,
                        true
                    );
                    mn.instructions.remove(a1);
                    mn.instructions.remove(a2);
                    if (a3 != null) mn.instructions.remove(a3);
                    mn.instructions.set(call, new LdcInsnNode(resolved));
                }
            }
        }
    }

    static LdcInsnNode prevStringLdc(AbstractInsnNode cursor) {
        if (cursor == null) return null;
        AbstractInsnNode p = cursor.getPrevious();
        while (p instanceof LineNumberNode || p instanceof LabelNode || p instanceof FrameNode) {
            p = p.getPrevious();
        }
        if (p instanceof LdcInsnNode && ((LdcInsnNode) p).cst instanceof String) {
            return (LdcInsnNode) p;
        }
        return null;
    }

    static String resolveRuntimeClass(String readableClass, Map<String, String> reverseClass, Map<String, String> legToRuntimeClass) {
        String slash = readableClass.replace('.', '/');
        String leg = mapClassInternal(slash, reverseClass);
        String rt = mapClassInternal(leg, legToRuntimeClass);
        if (rt.equals(slash) && !slash.startsWith("de/") && !slash.startsWith("java/") && !slash.startsWith("javax/") && !slash.startsWith("org/")) {
            throw new IllegalStateException("Unresolved runtime class: " + readableClass);
        }
        return rt.replace('/', '.');
    }

    static String resolveRuntimeMember(
        String readableOwnerDot,
        String readableMember,
        String readableDesc,
        Remapper reverseRemapper,
        Map<String, String> reverseClass,
        Map<String, List<LegMember>> reverseByName,
        Map<String, String> legToRuntimeMember,
        Map<String, Set<String>> legToRuntimeByName,
        Map<String, List<String>> parentsMap,
        Map<String, List<String>> childrenMap,
        Map<String, List<String>> ancestorsCache,
        Map<String, List<String>> descendantsCache,
        boolean isMethod
    ) {
        String kindLabel = isMethod ? "method" : "field";
        String rOwner = readableOwnerDot.replace('.', '/');
        List<String> searchOwners = new ArrayList<String>();
        searchOwners.add(rOwner);
        searchOwners.addAll(getAncestors(rOwner, parentsMap, ancestorsCache));
        searchOwners.addAll(getDescendants(rOwner, childrenMap, descendantsCache));

        Set<String> resolvedCandidates = new LinkedHashSet<String>();
        for (String candidateOwner : searchOwners) {
            List<LegMember> matches = reverseByName.get(candidateOwner + "\t" + readableMember);
            if (matches != null) {
                for (LegMember m : matches) {
                    if (readableDesc != null && !readableDesc.equals(m.readDesc)) continue;
                    String rt = lookupLegToRuntime(m.legOwner, m.legName, m.legDesc, legToRuntimeMember, legToRuntimeByName, isMethod);
                    resolvedCandidates.add(rt);
                }
            }
            String legOwner = mapClassInternal(candidateOwner, reverseClass);
            if (readableDesc != null) {
                String legDesc = isMethod ? reverseRemapper.mapMethodDesc(readableDesc) : reverseRemapper.mapDesc(readableDesc);
                String rt = legToRuntimeMember.get(legOwner + "\t" + readableMember + "\t" + legDesc);
                if (rt != null) resolvedCandidates.add(rt);
            } else {
                Set<String> rts = legToRuntimeByName.get(legOwner + "\t" + readableMember);
                if (rts != null) resolvedCandidates.addAll(rts);
            }
            if (!resolvedCandidates.isEmpty() && candidateOwner.equals(rOwner)) break;
        }
        if (resolvedCandidates.isEmpty()) {
            throw new IllegalStateException("Unresolved runtime " + kindLabel + ": " + readableOwnerDot + "." + readableMember + (readableDesc != null ? readableDesc : ""));
        }
        if (resolvedCandidates.size() > 1) {
            throw new IllegalStateException(
                "Ambiguous runtime " + kindLabel + ": " + readableOwnerDot + "." + readableMember + " -> " + resolvedCandidates + " (provide descriptor)"
            );
        }
        return resolvedCandidates.iterator().next();
    }

    static String lookupLegToRuntime(
        String legOwner,
        String legName,
        String legDesc,
        Map<String, String> legToRuntimeMember,
        Map<String, Set<String>> legToRuntimeByName,
        boolean isMethod
    ) {
        String base = legName;
        if (isMethod && base.startsWith("method_") && base.matches(".*\\$[0-9a-f]{10}$")) {
            base = base.substring(0, base.length() - 11);
        }
        String exact = legToRuntimeMember.get(legOwner + "\t" + base + "\t" + legDesc);
        if (exact != null) return exact;
        Set<String> byName = legToRuntimeByName.get(legOwner + "\t" + base);
        if (byName != null && byName.size() == 1) return byName.iterator().next();
        if (isMethod && base.startsWith("method_") && base.length() > 7 && Character.isDigit(base.charAt(7))) {
            return base.substring(7);
        }
        if (!isMethod && base.startsWith("field_") && base.length() > 6 && Character.isDigit(base.charAt(6))) {
            return base.substring(6);
        }
        return base;
    }

    static String mapClassInternal(String typeName, Map<String, String> classMap) {
        if (typeName == null) return null;
        String direct = classMap.get(typeName);
        if (direct != null) return direct;
        int dollar = typeName.indexOf('$');
        if (dollar > 0) {
            String outer = typeName.substring(0, dollar);
            String mappedOuter = classMap.get(outer);
            if (mappedOuter != null) {
                return mappedOuter + typeName.substring(dollar);
            }
        }
        return typeName;
    }

    static void loadFrameHierarchyFromJar(
        Path jarPath,
        Remapper nameMapper,
        Map<String, String> superMap,
        Map<String, List<String>> itfsMap,
        Set<String> interfaceSet
    ) throws Exception {
        try (JarFile jf = new JarFile(jarPath.toFile())) {
            Enumeration<JarEntry> en = jf.entries();
            while (en.hasMoreElements()) {
                JarEntry je = en.nextElement();
                if (!je.getName().endsWith(".class")) continue;
                ClassReader cr = new ClassReader(readAllBytes(jf.getInputStream(je)));
                String cname = cr.getClassName();
                if (nameMapper != null) cname = nameMapper.map(cname);
                if ((cr.getAccess() & Opcodes.ACC_INTERFACE) != 0) {
                    interfaceSet.add(cname);
                }
                String superName = cr.getSuperName();
                if (superName != null) {
                    if (nameMapper != null) superName = nameMapper.map(superName);
                    superMap.put(cname, superName);
                } else {
                    superMap.put(cname, null);
                }
                String[] itfs = cr.getInterfaces();
                if (itfs != null && itfs.length > 0) {
                    List<String> list = new ArrayList<String>(itfs.length);
                    for (String itf : itfs) {
                        list.add(nameMapper != null ? nameMapper.map(itf) : itf);
                    }
                    itfsMap.put(cname, list);
                }
            }
        }
    }

    static boolean isAssignableType(
        String target,
        String source,
        Map<String, String> superMap,
        Map<String, List<String>> itfsMap,
        Set<String> seen
    ) {
        if (target.equals(source) || "java/lang/Object".equals(target)) return true;
        if (!seen.add(source)) return false;
        if (superMap.containsKey(source)) {
            String sup = superMap.get(source);
            if (sup != null && isAssignableType(target, sup, superMap, itfsMap, seen)) return true;
            List<String> itfs = itfsMap.get(source);
            if (itfs != null) {
                for (String itf : itfs) {
                    if (isAssignableType(target, itf, superMap, itfsMap, seen)) return true;
                }
            }
            return false;
        }
        try {
            ClassLoader cl = ReadableNamespaceMapper.class.getClassLoader();
            Class<?> tc = Class.forName(target.replace('/', '.'), false, cl);
            Class<?> sc = Class.forName(source.replace('/', '.'), false, cl);
            return tc.isAssignableFrom(sc);
        } catch (Throwable ignored) {
            return false;
        }
    }

    static String commonSuperClass(
        String a,
        String b,
        Map<String, String> superMap,
        Map<String, List<String>> itfsMap,
        Set<String> interfaceSet
    ) {
        if (a.equals(b) || "java/lang/Object".equals(a) || "java/lang/Object".equals(b)) {
            return a.equals(b) ? a : "java/lang/Object";
        }
        if (isAssignableType(a, b, superMap, itfsMap, new HashSet<String>())) return a;
        if (isAssignableType(b, a, superMap, itfsMap, new HashSet<String>())) return b;
        if (interfaceSet.contains(a) || interfaceSet.contains(b)) return "java/lang/Object";
        if (superMap.containsKey(a)) {
            for (String p = superMap.get(a); p != null && !"java/lang/Object".equals(p); p = superMap.get(p)) {
                if (isAssignableType(p, b, superMap, itfsMap, new HashSet<String>())) return p;
            }
            return "java/lang/Object";
        }
        try {
            ClassLoader cl = ReadableNamespaceMapper.class.getClassLoader();
            Class<?> ca = Class.forName(a.replace('/', '.'), false, cl);
            Class<?> cb = Class.forName(b.replace('/', '.'), false, cl);
            if (ca.isInterface() || cb.isInterface()) return "java/lang/Object";
            for (Class<?> p = ca.getSuperclass(); p != null && p != Object.class; p = p.getSuperclass()) {
                if (p.isAssignableFrom(cb)) return Type.getInternalName(p);
            }
        } catch (Throwable ignored) {
        }
        return "java/lang/Object";
    }

    static void loadHierarchyFromJar(
        Path jarPath,
        Remapper nameMapper,
        Map<String, List<String>> parentsMap,
        Map<String, List<String>> childrenMap
    ) throws Exception {
        try (JarFile jf = new JarFile(jarPath.toFile())) {
            Enumeration<JarEntry> en = jf.entries();
            while (en.hasMoreElements()) {
                JarEntry je = en.nextElement();
                if (!je.getName().endsWith(".class")) continue;
                ClassReader cr = new ClassReader(readAllBytes(jf.getInputStream(je)));
                String cname = cr.getClassName();
                if (nameMapper != null) cname = nameMapper.map(cname);
                List<String> parents = parentsMap.get(cname);
                if (parents == null) {
                    parents = new ArrayList<String>();
                    parentsMap.put(cname, parents);
                }
                String superName = cr.getSuperName();
                if (superName != null && !"java/lang/Object".equals(superName)) {
                    if (nameMapper != null) superName = nameMapper.map(superName);
                    if (!parents.contains(superName)) parents.add(superName);
                    addChild(childrenMap, superName, cname);
                }
                String[] itfs = cr.getInterfaces();
                if (itfs != null) {
                    for (String itf : itfs) {
                        String mappedItf = nameMapper != null ? nameMapper.map(itf) : itf;
                        if (!parents.contains(mappedItf)) parents.add(mappedItf);
                        addChild(childrenMap, mappedItf, cname);
                    }
                }
            }
        }
    }

    static void addChild(Map<String, List<String>> childrenMap, String parent, String child) {
        List<String> list = childrenMap.get(parent);
        if (list == null) {
            list = new ArrayList<String>();
            childrenMap.put(parent, list);
        }
        if (!list.contains(child)) list.add(child);
    }

    static List<String> getAncestors(String cname, Map<String, List<String>> parentsMap, Map<String, List<String>> cache) {
        List<String> cached = cache.get(cname);
        if (cached != null) return cached;
        List<String> result = new ArrayList<String>();
        Set<String> visited = new HashSet<String>();
        Queue<String> queue = new ArrayDeque<String>();
        visited.add(cname);
        queue.add(cname);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            List<String> parents = parentsMap.get(cur);
            if (parents != null) {
                for (String p : parents) {
                    if (visited.add(p)) {
                        result.add(p);
                        queue.add(p);
                    }
                }
            }
        }
        cache.put(cname, result);
        return result;
    }

    static List<String> getDescendants(String cname, Map<String, List<String>> childrenMap, Map<String, List<String>> cache) {
        List<String> cached = cache.get(cname);
        if (cached != null) return cached;
        List<String> result = new ArrayList<String>();
        Set<String> visited = new HashSet<String>();
        Queue<String> queue = new ArrayDeque<String>();
        visited.add(cname);
        queue.add(cname);
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            List<String> children = childrenMap.get(cur);
            if (children != null) {
                for (String ch : children) {
                    if (visited.add(ch)) {
                        result.add(ch);
                        queue.add(ch);
                    }
                }
            }
        }
        cache.put(cname, result);
        return result;
    }
}
