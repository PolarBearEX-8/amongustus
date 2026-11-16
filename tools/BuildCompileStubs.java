import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

/**
 * Generates compile-only header stubs (`synth-compile-headers.jar`) so `javac --release 8`
 * resolves:
 *  1) Third-party library inner classes whose `InnerClasses` attributes were stripped by ZKM.
 *  2) The 5 third-party library classes omitted from `recovered-analysis.jar` and the 44
 *     encrypted `data/classes.bin` classes loaded dynamically by ElectricLauncher at runtime,
 *     declared in `tools/compile-stubs.tsv`.
 *
 * Used strictly on `javac -cp`; never packaged into `compiled-source-overlay.jar`.
 */
public final class BuildCompileStubs {

    private static final class StubSpec {
        final String name;
        String superName = "java/lang/Object";
        boolean isInterface = false;
        final Set<String> interfaces = new LinkedHashSet<String>();
        final Map<String, String> fields = new LinkedHashMap<String, String>();
        final Map<String, Integer> fieldAccess = new LinkedHashMap<String, Integer>();
        final Map<String, Integer> methods = new LinkedHashMap<String, Integer>();

        StubSpec(String name) {
            this.name = name;
        }

        void addField(String fname, String desc, int access) {
            String key = fname + ":" + desc;
            Integer prev = fieldAccess.get(key);
            if (prev == null) {
                fields.put(key, desc);
                fieldAccess.put(key, access);
            } else {
                fieldAccess.put(key, prev.intValue() | (access & Opcodes.ACC_STATIC));
            }
        }

        void addMethod(String mname, String desc, int access) {
            String key = mname + desc;
            if (!methods.containsKey(key)) {
                methods.put(key, access);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 4) {
            throw new IllegalArgumentException("Usage: BuildCompileStubs <output.jar> <compile-stubs.tsv> <filtered-lib-headers.jar> <interface-jar>...");
        }
        Path outJar = Paths.get(args[0]);
        Path stubsTsv = Paths.get(args[1]);
        Path filteredLibJar = Paths.get(args[2]);

        Map<String, ClassNode> interfaceDefs = new HashMap<String, ClassNode>();
        collectInterfaces(filteredLibJar, interfaceDefs);
        for (int i = 3; i < args.length; i++) {
            for (String part : args[i].split(File.pathSeparator)) {
                if (!part.isEmpty()) {
                    Path p = Paths.get(part);
                    if (Files.exists(p)) collectInterfaces(p, interfaceDefs);
                }
            }
        }

        Map<String, StubSpec> stubs = new LinkedHashMap<String, StubSpec>();
        for (String line : Files.readAllLines(stubsTsv, StandardCharsets.UTF_8)) {
            if (line.isEmpty() || line.startsWith("#")) continue;
            String[] p = line.split("\t", -1);
            if (p.length < 5) continue;
            if ("CLASS".equals(p[0])) {
                StubSpec s = new StubSpec(p[1]);
                s.isInterface = "I".equals(p[2]);
                s.superName = p[3].isEmpty() ? "java/lang/Object" : p[3];
                if (!p[4].isEmpty()) {
                    for (String itf : p[4].split(",")) {
                        if (!itf.isEmpty()) s.interfaces.add(itf);
                    }
                }
                stubs.put(s.name, s);
            } else if ("FIELD".equals(p[0])) {
                StubSpec s = stubs.get(p[1]);
                if (s != null) {
                    int acc = Opcodes.ACC_PUBLIC | ("S".equals(p[2]) ? Opcodes.ACC_STATIC : 0);
                    s.addField(p[3], p[4], acc);
                }
            } else if ("METHOD".equals(p[0])) {
                StubSpec s = stubs.get(p[1]);
                if (s != null) {
                    int acc = Opcodes.ACC_PUBLIC;
                    if ("S".equals(p[2])) acc |= Opcodes.ACC_STATIC;
                    else if ("A".equals(p[2])) acc |= Opcodes.ACC_ABSTRACT;
                    s.addMethod(p[3], p[4], acc);
                }
            }
        }

        Map<String, byte[]> outEntries = new LinkedHashMap<String, byte[]>();

        // 1) Synthesize missing InnerClasses attributes for library classes in filtered-lib-headers.jar
        Map<String, byte[]> libRaw = new LinkedHashMap<String, byte[]>();
        Map<String, List<String>> outerToInners = new LinkedHashMap<String, List<String>>();
        try (JarFile jf = new JarFile(filteredLibJar.toFile())) {
            Enumeration<JarEntry> en = jf.entries();
            while (en.hasMoreElements()) {
                JarEntry je = en.nextElement();
                if (je.isDirectory() || !je.getName().endsWith(".class")) continue;
                String cname = je.getName().substring(0, je.getName().length() - 6);
                libRaw.put(cname, readAll(jf.getInputStream(je)));
            }
        }

        Set<String> allLibNames = new LinkedHashSet<String>(libRaw.keySet());
        allLibNames.addAll(stubs.keySet());

        for (String cname : allLibNames) {
            int idx = cname.lastIndexOf('$');
            if (idx > 0 && idx < cname.length() - 1) {
                String outer = cname.substring(0, idx);
                String simple = cname.substring(idx + 1);
                if (!Character.isDigit(simple.charAt(0)) && allLibNames.contains(outer)) {
                    List<String> list = outerToInners.get(outer);
                    if (list == null) {
                        list = new ArrayList<String>();
                        outerToInners.put(outer, list);
                    }
                    list.add(cname);
                }
            }
        }

        Set<String> needsInnerPatch = new LinkedHashSet<String>();
        for (Map.Entry<String, List<String>> e : outerToInners.entrySet()) {
            needsInnerPatch.add(e.getKey());
            for (String in : e.getValue()) {
                if (libRaw.containsKey(in)) needsInnerPatch.add(in);
            }
        }

        for (String cname : needsInnerPatch) {
            byte[] raw = libRaw.get(cname);
            if (raw == null) continue;
            ClassNode cn = new ClassNode();
            new ClassReader(raw).accept(cn, 0);
            Set<String> existingIc = new HashSet<String>();
            if (cn.innerClasses == null) cn.innerClasses = new ArrayList<InnerClassNode>();
            for (InnerClassNode icn : cn.innerClasses) {
                existingIc.add(icn.name);
            }
            boolean changed = false;
            int dollar = cname.lastIndexOf('$');
            if (dollar > 0 && allLibNames.contains(cname.substring(0, dollar))) {
                String outer = cname.substring(0, dollar);
                String simple = cname.substring(dollar + 1);
                if (!Character.isDigit(simple.charAt(0)) && !existingIc.contains(cname)) {
                    int flags = (cn.access & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED | Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC | Opcodes.ACC_FINAL | Opcodes.ACC_INTERFACE | Opcodes.ACC_ABSTRACT | Opcodes.ACC_ENUM)) | Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC;
                    cn.innerClasses.add(new InnerClassNode(cname, outer, simple, flags));
                    existingIc.add(cname);
                    changed = true;
                }
            }
            List<String> children = outerToInners.get(cname);
            if (children != null) {
                for (String child : children) {
                    if (!existingIc.contains(child)) {
                        String simple = child.substring(cname.length() + 1);
                        int flags = Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC;
                        byte[] childRaw = libRaw.get(child);
                        if (childRaw != null) {
                            ClassReader ccr = new ClassReader(childRaw);
                            if ((ccr.getAccess() & Opcodes.ACC_INTERFACE) != 0) {
                                flags |= Opcodes.ACC_INTERFACE | Opcodes.ACC_ABSTRACT;
                            }
                        }
                        cn.innerClasses.add(new InnerClassNode(child, cname, simple, flags));
                        existingIc.add(child);
                        changed = true;
                    }
                }
            }
            if (changed) {
                ClassWriter cw = new ClassWriter(0);
                cn.accept(cw);
                outEntries.put(cname + ".class", cw.toByteArray());
            }
        }

        // 2) Emit synthetic stubs
        for (StubSpec spec : stubs.values()) {
            if (!spec.isInterface) {
                boolean hasInit = false;
                for (String mkey : spec.methods.keySet()) {
                    if (mkey.startsWith("<init>(")) {
                        hasInit = true;
                        break;
                    }
                }
                if (!hasInit) {
                    spec.addMethod("<init>", "()V", Opcodes.ACC_PUBLIC);
                }
                Queue<String> itfQueue = new ArrayDeque<String>(spec.interfaces);
                Set<String> seenItf = new HashSet<String>(spec.interfaces);
                while (!itfQueue.isEmpty()) {
                    String itf = itfQueue.poll();
                    ClassNode icn = interfaceDefs.get(itf);
                    if (icn != null) {
                        if (icn.interfaces != null) {
                            for (String sub : icn.interfaces) {
                                if (seenItf.add(sub)) itfQueue.add(sub);
                            }
                        }
                        for (MethodNode im : icn.methods) {
                            if ((im.access & Opcodes.ACC_STATIC) == 0 && !im.name.startsWith("<")) {
                                spec.addMethod(im.name, im.desc, Opcodes.ACC_PUBLIC);
                            }
                        }
                    } else if ("java/util/concurrent/Callable".equals(itf)) {
                        spec.addMethod("call", "()Ljava/lang/Object;", Opcodes.ACC_PUBLIC);
                    }
                }
            }
            outEntries.put(spec.name + ".class", emitStub(spec));
        }

        try (JarOutputStream jos = new JarOutputStream(new java.io.BufferedOutputStream(Files.newOutputStream(outJar), 65536))) {
            for (Map.Entry<String, byte[]> e : outEntries.entrySet()) {
                JarEntry je = new JarEntry(e.getKey());
                jos.putNextEntry(je);
                jos.write(e.getValue());
                jos.closeEntry();
            }
        }
        System.out.println("BuildCompileStubs: wrote " + outEntries.size() + " entries (" + stubs.size() + " synthetic stubs)");
    }

    private static void collectInterfaces(Path p, Map<String, ClassNode> interfaceDefs) throws IOException {
        if (Files.isDirectory(p)) {
            try (java.util.stream.Stream<Path> st = Files.walk(p)) {
                for (Path f : (Iterable<Path>) st::iterator) {
                    if (f.toString().endsWith(".class")) {
                        ClassReader cr = new ClassReader(Files.readAllBytes(f));
                        if ((cr.getAccess() & Opcodes.ACC_INTERFACE) != 0) {
                            ClassNode cn = new ClassNode();
                            cr.accept(cn, ClassReader.SKIP_CODE);
                            interfaceDefs.put(cn.name, cn);
                        }
                    }
                }
            }
        } else if (p.toString().endsWith(".jar")) {
            try (JarFile jf = new JarFile(p.toFile())) {
                Enumeration<JarEntry> en = jf.entries();
                while (en.hasMoreElements()) {
                    JarEntry je = en.nextElement();
                    if (!je.isDirectory() && je.getName().endsWith(".class")) {
                        ClassReader cr = new ClassReader(readAll(jf.getInputStream(je)));
                        if ((cr.getAccess() & Opcodes.ACC_INTERFACE) != 0) {
                            ClassNode cn = new ClassNode();
                            cr.accept(cn, ClassReader.SKIP_CODE);
                            interfaceDefs.put(cn.name, cn);
                        }
                    }
                }
            }
        }
    }

    private static byte[] emitStub(StubSpec spec) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        int access = Opcodes.ACC_PUBLIC | (spec.isInterface ? (Opcodes.ACC_INTERFACE | Opcodes.ACC_ABSTRACT) : Opcodes.ACC_SUPER);
        String[] itfs = spec.interfaces.toArray(new String[0]);
        cw.visit(Opcodes.V1_8, access, spec.name, null, spec.isInterface ? "java/lang/Object" : spec.superName, itfs);

        int dollar = spec.name.lastIndexOf('$');
        if (dollar > 0 && dollar < spec.name.length() - 1) {
            String outer = spec.name.substring(0, dollar);
            String simple = spec.name.substring(dollar + 1);
            if (!Character.isDigit(simple.charAt(0))) {
                cw.visitInnerClass(spec.name, outer, simple, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC | (spec.isInterface ? (Opcodes.ACC_INTERFACE | Opcodes.ACC_ABSTRACT) : 0));
            }
        }

        for (Map.Entry<String, String> fe : spec.fields.entrySet()) {
            String key = fe.getKey();
            String fname = key.substring(0, key.indexOf(':'));
            String fdesc = fe.getValue();
            int facc = spec.fieldAccess.get(key).intValue();
            cw.visitField(facc, fname, fdesc, null, null).visitEnd();
        }

        for (Map.Entry<String, Integer> me : spec.methods.entrySet()) {
            String key = me.getKey();
            int paren = key.indexOf('(');
            String mname = key.substring(0, paren);
            String mdesc = key.substring(paren);
            int macc = me.getValue().intValue();
            MethodVisitor mv = cw.visitMethod(macc, mname, mdesc, null, null);
            if ((macc & Opcodes.ACC_ABSTRACT) == 0) {
                mv.visitCode();
                if ("<init>".equals(mname)) {
                    mv.visitTypeInsn(Opcodes.NEW, "java/lang/UnsupportedOperationException");
                    mv.visitInsn(Opcodes.DUP);
                    mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/UnsupportedOperationException", "<init>", "()V", false);
                    mv.visitInsn(Opcodes.ATHROW);
                } else {
                    Type ret = Type.getReturnType(mdesc);
                    switch (ret.getSort()) {
                        case Type.VOID:
                            mv.visitInsn(Opcodes.RETURN);
                            break;
                        case Type.BOOLEAN:
                        case Type.BYTE:
                        case Type.CHAR:
                        case Type.SHORT:
                        case Type.INT:
                            mv.visitInsn(Opcodes.ICONST_0);
                            mv.visitInsn(Opcodes.IRETURN);
                            break;
                        case Type.LONG:
                            mv.visitInsn(Opcodes.LCONST_0);
                            mv.visitInsn(Opcodes.LRETURN);
                            break;
                        case Type.FLOAT:
                            mv.visitInsn(Opcodes.FCONST_0);
                            mv.visitInsn(Opcodes.FRETURN);
                            break;
                        case Type.DOUBLE:
                            mv.visitInsn(Opcodes.DCONST_0);
                            mv.visitInsn(Opcodes.DRETURN);
                            break;
                        default:
                            mv.visitInsn(Opcodes.ACONST_NULL);
                            mv.visitInsn(Opcodes.ARETURN);
                            break;
                    }
                }
                mv.visitMaxs(0, 0);
            }
            mv.visitEnd();
        }

        cw.visitEnd();
        return cw.toByteArray();
    }

    private static byte[] readAll(InputStream in) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[8192];
        int n;
        while ((n = in.read(buf)) >= 0) bos.write(buf, 0, n);
        return bos.toByteArray();
    }
}
