import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.tree.analysis.*;

/** Reverse the exact recorded readability projection, including descriptor suffixes and lambda SAMs. */
public final class RestoreOriginalNames {
    static Long constLong(AbstractInsnNode insn) {
        if (insn == null) return null;
        if (insn.getOpcode() == Opcodes.LCONST_0) return Long.valueOf(0L);
        if (insn.getOpcode() == Opcodes.LCONST_1) return Long.valueOf(1L);
        if (insn instanceof LdcInsnNode && ((LdcInsnNode) insn).cst instanceof Long) return (Long) ((LdcInsnNode) insn).cst;
        return null;
    }

    static InsnList buildZkmClinitPrologue(ClassNode baseline, Set<String> retainedSentinelFields) {
        InsnList prologue = new InsnList();
        MethodNode baseClinit = null;
        for (MethodNode m : baseline.methods) {
            if ("<clinit>".equals(m.name) && m.instructions != null && m.instructions.size() > 0) {
                baseClinit = m;
                break;
            }
        }
        if (baseClinit == null) return prologue;
        AbstractInsnNode[] arr = baseClinit.instructions.toArray();
        // 1. Retain ZKM 4f cryptographic key-tree advancement (if present in baseline <clinit>)
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] instanceof MethodInsnNode) {
                MethodInsnNode mi = (MethodInsnNode) arr[i];
                if ("net/4f".equals(mi.owner) && "a".equals(mi.name) && "(JJLjava/lang/Object;)Lnet/4p;".equals(mi.desc)) {
                    List<Long> ldcs = new ArrayList<Long>();
                    for (int j = i - 1; j >= 0 && ldcs.size() < 2; j--) {
                        Long v = constLong(arr[j]);
                        if (v != null) ldcs.add(0, v);
                    }
                    Long k3 = null;
                    for (int j = i + 1; j < Math.min(arr.length, i + 12); j++) {
                        if (k3 == null) k3 = constLong(arr[j]);
                    }
                    if (ldcs.size() == 2 && k3 != null) {
                        prologue.add(new LdcInsnNode(ldcs.get(0)));
                        prologue.add(new LdcInsnNode(ldcs.get(1)));
                        prologue.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/invoke/MethodHandles", "lookup", "()Ljava/lang/invoke/MethodHandles$Lookup;", false));
                        prologue.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/invoke/MethodHandles$Lookup", "lookupClass", "()Ljava/lang/Class;", false));
                        prologue.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "net/4f", "a", "(JJLjava/lang/Object;)Lnet/4p;", false));
                        prologue.add(new LdcInsnNode(k3));
                        prologue.add(new MethodInsnNode(Opcodes.INVOKEINTERFACE, "net/4p", "a", "(J)J", true));
                        prologue.add(new InsnNode(Opcodes.POP2));
                    }
                    break;
                }
            }
        }
        // 2. Retain non-default ZKM sentinel field initializations in <clinit>
        if (!retainedSentinelFields.isEmpty()) {
            Map<String, List<FieldInsnNode>> settersByDesc = new HashMap<String, List<FieldInsnNode>>();
            Map<String, FieldInsnNode> settersByMethod = new HashMap<String, FieldInsnNode>();
            for (MethodNode m : baseline.methods) {
                if ((m.access & Opcodes.ACC_PUBLIC) == 0 || (m.access & Opcodes.ACC_STATIC) == 0 || m.instructions == null) continue;
                List<AbstractInsnNode> ex = new ArrayList<AbstractInsnNode>();
                for (AbstractInsnNode n = m.instructions.getFirst(); n != null; n = n.getNext())
                    if (n.getOpcode() >= 0) ex.add(n);
                if (ex.size() == 3 && ex.get(0) instanceof VarInsnNode && ((VarInsnNode) ex.get(0)).var == 0
                        && ex.get(1) instanceof FieldInsnNode && ex.get(1).getOpcode() == Opcodes.PUTSTATIC
                        && ((FieldInsnNode) ex.get(1)).owner.equals(baseline.name)
                        && ex.get(2).getOpcode() == Opcodes.RETURN) {
                    FieldInsnNode fi = (FieldInsnNode) ex.get(1);
                    if (!retainedSentinelFields.contains(fi.name + ":" + fi.desc)) continue;
                    String erasedArg = fi.desc.startsWith("L") || fi.desc.startsWith("[") ? "Ljava/lang/Object;" : fi.desc;
                    settersByDesc.computeIfAbsent("(" + erasedArg + "JJ)V", k -> new ArrayList<FieldInsnNode>()).add(fi);
                    if ("Ljava/lang/String;".equals(fi.desc)) {
                        settersByDesc.computeIfAbsent("(Ljava/lang/String;JJ)V", k -> new ArrayList<FieldInsnNode>()).add(fi);
                    }
                    settersByMethod.put(m.name + m.desc, fi);
                }
            }
            if (!settersByMethod.isEmpty()) {
                try {
                    baseClinit.maxStack += 16;
                    baseClinit.maxLocals += 16;
                    Frame<SourceValue>[] frames = new Analyzer<SourceValue>(new SourceInterpreter()).analyze(baseline.name, baseClinit);
                    Map<LabelNode, LabelNode> emptyLabels = Collections.emptyMap();
                    for (int i = 0; i < arr.length; i++) {
                        if (frames[i] == null) continue;
                        List<FieldInsnNode> candidateFields = null;
                        int argSlot = -1;
                        if (arr[i] instanceof InvokeDynamicInsnNode) {
                            InvokeDynamicInsnNode idy = (InvokeDynamicInsnNode) arr[i];
                            candidateFields = settersByDesc.get(idy.desc);
                            if (candidateFields != null) argSlot = frames[i].getStackSize() - 3;
                        } else if (arr[i] instanceof MethodInsnNode) {
                            MethodInsnNode mi = (MethodInsnNode) arr[i];
                            if (mi.owner.equals(baseline.name)) {
                                FieldInsnNode single = settersByMethod.get(mi.name + mi.desc);
                                if (single != null) {
                                    candidateFields = Collections.singletonList(single);
                                    argSlot = frames[i].getStackSize() - 1;
                                }
                            }
                        }
                        if (candidateFields == null || candidateFields.isEmpty() || argSlot < 0) continue;
                        SourceValue sv = frames[i].getStack(argSlot);
                        if (sv.insns.size() != 1) continue;
                        AbstractInsnNode prod = sv.insns.iterator().next();
                        int op = prod.getOpcode();
                        for (FieldInsnNode targetField : candidateFields) {
                            if ((op >= Opcodes.ICONST_M1 && op <= Opcodes.ICONST_5 && op != Opcodes.ICONST_0)
                                    || op == Opcodes.BIPUSH || op == Opcodes.SIPUSH
                                    || (op == Opcodes.LDC && ((LdcInsnNode) prod).cst instanceof Integer)) {
                                if ("I".equals(targetField.desc) || "Z".equals(targetField.desc)
                                        || "B".equals(targetField.desc) || "S".equals(targetField.desc) || "C".equals(targetField.desc)) {
                                    prologue.add(prod.clone(emptyLabels));
                                    prologue.add(new FieldInsnNode(Opcodes.PUTSTATIC, baseline.name, targetField.name, targetField.desc));
                                    break;
                                }
                            } else if (op == Opcodes.LDC && ((LdcInsnNode) prod).cst instanceof String) {
                                if ("Ljava/lang/String;".equals(targetField.desc) || "Ljava/lang/Object;".equals(targetField.desc)) {
                                    prologue.add(prod.clone(emptyLabels));
                                    prologue.add(new FieldInsnNode(Opcodes.PUTSTATIC, baseline.name, targetField.name, targetField.desc));
                                    break;
                                }
                            } else if (op == Opcodes.NEWARRAY || op == Opcodes.ANEWARRAY) {
                                String prodDesc = null;
                                if (op == Opcodes.NEWARRAY) {
                                    switch (((IntInsnNode) prod).operand) {
                                        case Opcodes.T_BOOLEAN: prodDesc = "[Z"; break;
                                        case Opcodes.T_CHAR: prodDesc = "[C"; break;
                                        case Opcodes.T_FLOAT: prodDesc = "[F"; break;
                                        case Opcodes.T_DOUBLE: prodDesc = "[D"; break;
                                        case Opcodes.T_BYTE: prodDesc = "[B"; break;
                                        case Opcodes.T_SHORT: prodDesc = "[S"; break;
                                        case Opcodes.T_INT: prodDesc = "[I"; break;
                                        case Opcodes.T_LONG: prodDesc = "[J"; break;
                                    }
                                } else {
                                    String t = ((TypeInsnNode) prod).desc;
                                    prodDesc = t.startsWith("[") ? ("[" + t) : ("[L" + t + ";");
                                }
                                if (prodDesc != null && prodDesc.equals(targetField.desc)) {
                                    int pidx = baseClinit.instructions.indexOf(prod);
                                    SourceValue szSv = frames[pidx].getStack(frames[pidx].getStackSize() - 1);
                                    if (szSv.insns.size() == 1) {
                                        AbstractInsnNode szProd = szSv.insns.iterator().next();
                                        int szOp = szProd.getOpcode();
                                        if ((szOp >= Opcodes.ICONST_0 && szOp <= Opcodes.ICONST_5) || szOp == Opcodes.BIPUSH || szOp == Opcodes.SIPUSH) {
                                            prologue.add(szProd.clone(emptyLabels));
                                            prologue.add(prod.clone(emptyLabels));
                                            prologue.add(new FieldInsnNode(Opcodes.PUTSTATIC, baseline.name, targetField.name, targetField.desc));
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (AnalyzerException ignored) {
                }
            }
        }
        return prologue;
    }

    static void addParents(byte[] classBytes, Remapper nameMapper, Map<String, List<String>> parentsMap) {
        ClassReader cr = new ClassReader(classBytes);
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
        }
        String[] itfs = cr.getInterfaces();
        if (itfs != null) {
            for (String itf : itfs) {
                String mappedItf = nameMapper != null ? nameMapper.map(itf) : itf;
                if (!parents.contains(mappedItf)) parents.add(mappedItf);
            }
        }
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

    public static void main(String[] args) throws Exception {
        final Map<String, String> forward = new HashMap<String, String>(),
                legacyForward = new HashMap<String, String>(),
                reverse = new HashMap<String, String>();
        final Map<String, String> methods = new HashMap<String, String>(),
                fields = new HashMap<String, String>();
        List<String[]> rows = new ArrayList<String[]>();
        for (String line : Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8)) {
            String[] row = line.split("\t", -1);
            if (row.length != 5) continue;
            rows.add(row);
            if (row[0].equals("CLASS")) {
                if (!legacyForward.containsKey(row[1])) legacyForward.put(row[1], row[4]);
                forward.put(row[1], row[4]);
                reverse.put(row[4], row[1]);
            }
        }
        final Remapper toReadable = new Remapper() {
            public String map(String name) {
                return forward.containsKey(name) ? forward.get(name) : name;
            }
        };
        final Remapper toLegacy = new Remapper() {
            public String map(String name) {
                return legacyForward.containsKey(name) ? legacyForward.get(name) : name;
            }
        };
        for (String[] row : rows) {
            if (row[0].equals("METHOD"))
                methods.put(toReadable.map(row[1]) + "\t" + row[4] + "\t" + toReadable.mapMethodDesc(row[3]), row[2]);
            if (row[0].equals("FIELD"))
                fields.put(toReadable.map(row[1]) + "\t" + row[4] + "\t" + toReadable.mapDesc(row[3]), row[2]);
        }

        final Map<String, List<String>> parentsMap = new HashMap<String, List<String>>();
        final Map<String, List<String>> ancestorsCache = new HashMap<String, List<String>>();
        try (JarFile srcHierarchy = new JarFile(args[1])) {
            Enumeration<JarEntry> en = srcHierarchy.entries();
            while (en.hasMoreElements()) {
                JarEntry je = en.nextElement();
                if (!je.isDirectory() && je.getName().endsWith(".class")) {
                    addParents(RestoreSourceBridges.read(srcHierarchy.getInputStream(je)), null, parentsMap);
                }
            }
        }
        if (args.length >= 4 && Files.exists(Paths.get(args[3]))) {
            try (JarFile rawHierarchy = new JarFile(args[3])) {
                Enumeration<JarEntry> en = rawHierarchy.entries();
                while (en.hasMoreElements()) {
                    JarEntry je = en.nextElement();
                    if (!je.isDirectory() && je.getName().endsWith(".class")) {
                        addParents(RestoreSourceBridges.read(rawHierarchy.getInputStream(je)), toReadable, parentsMap);
                    }
                }
            }
        }
        for (int extra = 4; extra < args.length; extra++) {
            if (!Files.exists(Paths.get(args[extra]))) continue;
            try (JarFile extraHierarchy = new JarFile(args[extra])) {
                Enumeration<JarEntry> en = extraHierarchy.entries();
                while (en.hasMoreElements()) {
                    JarEntry je = en.nextElement();
                    if (!je.isDirectory() && je.getName().endsWith(".class")) {
                        addParents(RestoreSourceBridges.read(extraHierarchy.getInputStream(je)), null, parentsMap);
                    }
                }
            }
        }

        final int[] renamed = {0, 0, 0};
        Remapper original = new Remapper() {
            public String map(String name) {
                String result = reverse.get(name);
                if (result != null) {
                    renamed[0]++;
                    return result;
                }
                int dollar = name.indexOf('$');
                if (dollar > 0) {
                    String outer = reverse.get(name.substring(0, dollar));
                    if (outer != null) {
                        renamed[0]++;
                        return outer + name.substring(dollar);
                    }
                }
                return name;
            }

            public String mapMethodName(String owner, String name, String descriptor) {
                String base = name;
                if (name.startsWith("method_") && name.matches(".*\\$[0-9a-f]{10}$")) {
                    base = name.substring(0, name.length() - 11);
                    String legacyDescriptor = toLegacy.mapMethodDesc(mapMethodDesc(descriptor));
                    if (!SourceMethodNames.name(base, legacyDescriptor).equals(name))
                        throw new IllegalStateException("Wrong descriptor suffix: " + name);
                }
                String result = methods.get(owner + "\t" + base + "\t" + descriptor);
                if (result == null) {
                    for (String anc : getAncestors(owner, parentsMap, ancestorsCache)) {
                        result = methods.get(anc + "\t" + base + "\t" + descriptor);
                        if (result != null) break;
                    }
                }
                String runtimeDescriptor = mapMethodDesc(descriptor);
                if (result == null && base.equals("onRender2D") && runtimeDescriptor.equals("(IFCILnet/477t;)V"))
                    result = "44";
                if (result == null && base.equals("onRender3D") && runtimeDescriptor.equals("(JF)V"))
                    result = "43";
                if (result == null && base.equals("onRenderOverlay") && runtimeDescriptor.equals("(FJLnet/477t;B)V"))
                    result = "45";
                if (result == null && base.startsWith("method_")) {
                    String candidate = base.substring(7);
                    if (!candidate.isEmpty() && Character.isDigit(candidate.charAt(0))) result = candidate;
                }
                if (result == null) result = base;
                if (!result.equals(name)) renamed[1]++;
                return result;
            }

            public String mapFieldName(String owner, String name, String descriptor) {
                String result = fields.get(owner + "\t" + name + "\t" + descriptor);
                if (result == null) {
                    for (String anc : getAncestors(owner, parentsMap, ancestorsCache)) {
                        result = fields.get(anc + "\t" + name + "\t" + descriptor);
                        if (result != null) break;
                    }
                }
                if (result == null && name.startsWith("field_") && name.length() > 6 && Character.isDigit(name.charAt(6)))
                    result = name.substring(6);
                if (result == null) result = name;
                if (!result.equals(name)) renamed[2]++;
                return result;
            }
        };

        JarFile raw = (args.length >= 4 && Files.exists(Paths.get(args[3]))) ? new JarFile(args[3]) : null;
        try (JarFile source = new JarFile(args[1]);
             JarOutputStream target = new JarOutputStream(new java.io.BufferedOutputStream(Files.newOutputStream(Paths.get(args[2])), 65536))) {
            Enumeration<JarEntry> entries = source.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.isDirectory()) continue;
                byte[] bytes = RestoreSourceBridges.read(source.getInputStream(entry));
                String name = entry.getName();
                if (name.endsWith(".class")) {
                    ClassNode pre = new ClassNode();
                    new ClassReader(bytes).accept(pre, 0);
                    for (MethodNode m : pre.methods) {
                        for (AbstractInsnNode insn : m.instructions.toArray()) {
                            if (insn instanceof InvokeDynamicInsnNode) {
                                InvokeDynamicInsnNode indy = (InvokeDynamicInsnNode) insn;
                                if (indy.bsm != null
                                        && "java/lang/invoke/LambdaMetafactory".equals(indy.bsm.getOwner())
                                        && indy.bsmArgs != null
                                        && indy.bsmArgs.length >= 1
                                        && indy.bsmArgs[0] instanceof Type) {
                                    Type retType = Type.getReturnType(indy.desc);
                                    if (retType.getSort() == Type.OBJECT) {
                                        indy.name = original.mapMethodName(
                                                retType.getInternalName(),
                                                indy.name,
                                                ((Type) indy.bsmArgs[0]).getDescriptor());
                                    }
                                }
                            }
                        }
                    }
                    ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                    pre.accept(new ClassRemapper(writer, original));
                    bytes = writer.toByteArray();
                    ClassNode check = new ClassNode();
                    new ClassReader(bytes).accept(check, 0);
                    if (raw != null) {
                        // The earlier deobfuscator removes this proven identity helper.
                        // Retain its original declaration for reflective/ABI compatibility when present in raw.
                        JarEntry rawEntry = raw.getJarEntry(check.name + ".class");
                        if (rawEntry != null) {
                            ClassNode baseline = new ClassNode();
                            new ClassReader(RestoreSourceBridges.read(raw.getInputStream(rawEntry))).accept(baseline, 0);
                            boolean addedHelper = false;
                            Set<String> checkFields = new HashSet<String>();
                            for (FieldNode fn : check.fields) checkFields.add(fn.name + ":" + fn.desc);
                            Set<String> retainedSentinelFields = new LinkedHashSet<String>();
                            for (MethodNode method : baseline.methods) {
                                if ((method.access & Opcodes.ACC_STATIC) == 0 || method.instructions == null) continue;
                                boolean exists = false;
                                for (MethodNode existing : check.methods) {
                                    if (existing.name.equals(method.name) && existing.desc.equals(method.desc)) {
                                        exists = true;
                                        break;
                                    }
                                }
                                if (exists) continue;
                                List<AbstractInsnNode> executable = new ArrayList<AbstractInsnNode>();
                                for (AbstractInsnNode instruction : method.instructions)
                                    if (instruction.getOpcode() >= 0) executable.add(instruction);
                                if ((method.desc.equals("(Ljava/lang/Exception;)Ljava/lang/Exception;")
                                        || method.desc.equals("(Ljava/lang/Throwable;)Ljava/lang/Throwable;"))
                                        && executable.size() == 2
                                        && executable.get(0) instanceof VarInsnNode
                                        && executable.get(0).getOpcode() == Opcodes.ALOAD
                                        && ((VarInsnNode) executable.get(0)).var == 0
                                        && executable.get(1).getOpcode() == Opcodes.ARETURN) {
                                    check.methods.add(method);
                                    addedHelper = true;
                                    continue;
                                }
                                // Retain ZKM static sentinel getter/setter methods & backing field for binary-class reflection ABI
                                if ((method.access & Opcodes.ACC_PUBLIC) != 0 && executable.size() == 2
                                        && executable.get(0) instanceof FieldInsnNode
                                        && executable.get(0).getOpcode() == Opcodes.GETSTATIC
                                        && (executable.get(1).getOpcode() >= Opcodes.IRETURN && executable.get(1).getOpcode() <= Opcodes.ARETURN)) {
                                    FieldInsnNode fi = (FieldInsnNode) executable.get(0);
                                    if (fi.owner.equals(baseline.name)) {
                                        retainedSentinelFields.add(fi.name + ":" + fi.desc);
                                        check.methods.add(method);
                                        addedHelper = true;
                                    }
                                } else if ((method.access & Opcodes.ACC_PUBLIC) != 0 && executable.size() == 3
                                        && executable.get(0) instanceof VarInsnNode
                                        && ((VarInsnNode) executable.get(0)).var == 0
                                        && executable.get(1) instanceof FieldInsnNode
                                        && executable.get(1).getOpcode() == Opcodes.PUTSTATIC
                                        && executable.get(2).getOpcode() == Opcodes.RETURN) {
                                    FieldInsnNode fi = (FieldInsnNode) executable.get(1);
                                    if (fi.owner.equals(baseline.name)) {
                                        retainedSentinelFields.add(fi.name + ":" + fi.desc);
                                        check.methods.add(method);
                                        addedHelper = true;
                                    }
                                } else if ((method.access & Opcodes.ACC_PUBLIC) != 0
                                        && ("()Z".equals(method.desc) || "()I".equals(method.desc))
                                        && executable.size() >= 5 && executable.size() <= 12
                                        && executable.get(0) instanceof MethodInsnNode
                                        && executable.get(0).getOpcode() == Opcodes.INVOKESTATIC
                                        && ((MethodInsnNode) executable.get(0)).owner.equals(baseline.name)
                                        && ((MethodInsnNode) executable.get(0)).desc.equals(method.desc)
                                        && executable.get(1).getOpcode() == Opcodes.ISTORE
                                        && executable.get(2).getOpcode() == Opcodes.ILOAD
                                        && (executable.get(3).getOpcode() == Opcodes.IFNE || executable.get(3).getOpcode() == Opcodes.IFEQ)
                                        && executable.get(5).getOpcode() == Opcodes.IRETURN
                                        && executable.get(executable.size() - 1).getOpcode() == Opcodes.IRETURN) {
                                    MethodInsnNode call = (MethodInsnNode) executable.get(0);
                                    MethodNode synth = new MethodNode(method.access, method.name, method.desc, method.signature, null);
                                    LabelNode altLabel = new LabelNode();
                                    Map<LabelNode, LabelNode> emptyLabels = Collections.emptyMap();
                                    synth.instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, call.owner, call.name, call.desc, call.itf));
                                    synth.instructions.add(new JumpInsnNode(executable.get(3).getOpcode(), altLabel));
                                    synth.instructions.add(executable.get(4).clone(emptyLabels));
                                    synth.instructions.add(new InsnNode(Opcodes.IRETURN));
                                    synth.instructions.add(altLabel);
                                    synth.instructions.add(new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
                                    synth.instructions.add(executable.get(executable.size() - 2).clone(emptyLabels));
                                    synth.instructions.add(new InsnNode(Opcodes.IRETURN));
                                    synth.maxStack = 2;
                                    synth.maxLocals = 0;
                                    check.methods.add(synth);
                                    addedHelper = true;
                                }
                            }
                            if (!retainedSentinelFields.isEmpty()) {
                                for (FieldNode bfn : baseline.fields) {
                                    String fk = bfn.name + ":" + bfn.desc;
                                    if (retainedSentinelFields.contains(fk) && checkFields.add(fk)) {
                                        check.fields.add(new FieldNode(bfn.access, bfn.name, bfn.desc, bfn.signature, bfn.value));
                                        addedHelper = true;
                                    }
                                }
                            }
                            InsnList zkmPrologue = buildZkmClinitPrologue(baseline, retainedSentinelFields);
                            if (zkmPrologue.size() > 0) {
                                MethodNode checkClinit = null;
                                for (MethodNode m : check.methods) {
                                    if ("<clinit>".equals(m.name)) {
                                        checkClinit = m;
                                        break;
                                    }
                                }
                                if (checkClinit == null) {
                                    checkClinit = new MethodNode(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null);
                                    checkClinit.instructions.add(zkmPrologue);
                                    checkClinit.instructions.add(new InsnNode(Opcodes.RETURN));
                                    checkClinit.maxStack = 6;
                                    checkClinit.maxLocals = 0;
                                    check.methods.add(checkClinit);
                                } else {
                                    AbstractInsnNode first = checkClinit.instructions.getFirst();
                                    if (first instanceof LabelNode) {
                                        LabelNode firstLbl = (LabelNode) first;
                                        boolean targeted = false;
                                        for (AbstractInsnNode ins = first.getNext(); ins != null; ins = ins.getNext()) {
                                            if (ins instanceof JumpInsnNode && ((JumpInsnNode) ins).label == firstLbl) { targeted = true; break; }
                                        }
                                        if (!targeted && checkClinit.tryCatchBlocks != null) {
                                            for (TryCatchBlockNode tcb : checkClinit.tryCatchBlocks) {
                                                if (tcb.handler == firstLbl) { targeted = true; break; }
                                            }
                                        }
                                        if (targeted) {
                                            AbstractInsnNode next = first.getNext();
                                            while (next instanceof LineNumberNode || next instanceof LabelNode) next = next.getNext();
                                            if (!(next instanceof FrameNode)) {
                                                checkClinit.instructions.insert(first, new FrameNode(Opcodes.F_SAME, 0, null, 0, null));
                                            }
                                        }
                                    }
                                    checkClinit.instructions.insert(zkmPrologue);
                                    checkClinit.maxStack = Math.max(checkClinit.maxStack, 6);
                                }
                                addedHelper = true;
                            }
                            if (addedHelper) {
                                ClassWriter merged = new ClassWriter(ClassWriter.COMPUTE_MAXS);
                                check.accept(merged);
                                bytes = merged.toByteArray();
                            }
                        }
                    }
                    for (MethodNode method : check.methods)
                        if ((method.access & (Opcodes.ACC_NATIVE | Opcodes.ACC_ABSTRACT)) == 0)
                            new Analyzer<BasicValue>(new BasicVerifier()).analyze(check.name, method);
                    name = check.name + ".class";
                }
                target.putNextEntry(new JarEntry(name));
                target.write(bytes);
                target.closeEntry();
            }
        } finally {
            if (raw != null) raw.close();
        }
        System.out.println("PASS original_names_restored class_refs=" + renamed[0] + " method_refs=" + renamed[1]
                + " field_refs=" + renamed[2] + " basic_verifier=true gameplay_not_verified=true");
    }
}
