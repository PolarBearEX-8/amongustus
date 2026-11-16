import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.tree.analysis.*;

/** Restores compiler-only bridge declarations to their exact retained JVM bodies. */
public final class RestoreSourceBridges implements Opcodes {
    static boolean bridge(String name) {
        return name.startsWith("recovery$indy$") || name.startsWith("recovery$constant$");
    }
    public static byte[] restore(byte[] compiled, byte[] retained) throws Exception {
        return restore(compiled,retained,Collections.<String>emptySet());
    }
    public static byte[] restore(byte[] compiled, byte[] retained, Set<String> infrastructure) throws Exception {
        ClassNode source=new ClassNode(),template=new ClassNode();
        new ClassReader(compiled).accept(source,0);new ClassReader(retained).accept(template,0);
        if(!source.name.equals(template.name))throw new IllegalArgumentException("Class identity mismatch");
        Map<String,MethodNode> bodies=new HashMap<String,MethodNode>();
        for(MethodNode method:template.methods)if(bridge(method.name)||infrastructure.contains(method.name))bodies.put(method.name+method.desc,method);
        int restored=0;
        for(int i=0;i<source.methods.size();i++){
            MethodNode method=source.methods.get(i);if(!bridge(method.name)&&!infrastructure.contains(method.name))continue;
            MethodNode body=bodies.remove(method.name+method.desc);
            if(body==null)throw new IllegalStateException("Unknown bridge "+method.name+method.desc);
            if((method.access&ACC_NATIVE)==0&&!method.name.equals("<clinit>"))throw new IllegalStateException("Expected declaration "+method.name);
            if((body.access&ACC_NATIVE)!=0)throw new IllegalStateException("Retained implementation missing");
            source.methods.set(i,body);restored++;
        }
        if(!bodies.isEmpty())throw new IllegalStateException("Missing source declarations "+bodies.keySet());
        ClassWriter writer=new ClassWriter(ClassWriter.COMPUTE_MAXS);source.accept(writer);byte[] result=writer.toByteArray();
        ClassNode verify=new ClassNode();new ClassReader(result).accept(verify,0);
        for(MethodNode method:verify.methods)if((method.access&(ACC_NATIVE|ACC_ABSTRACT))==0)
            new Analyzer<BasicValue>(new BasicVerifier()).analyze(verify.name,method);
        return result;
    }
    static byte[] read(InputStream in)throws IOException{
        try(InputStream stream=in;ByteArrayOutputStream out=new ByteArrayOutputStream()){
            byte[] buffer=new byte[8192];int n;while((n=stream.read(buffer))!=-1)out.write(buffer,0,n);return out.toByteArray();
        }
    }
    public static void main(String[] args)throws Exception{
        if(args.length!=3&&args.length!=4)throw new IllegalArgumentException("compiled.jar retained-bridge.jar output.jar [retained-method-names-csv]");
        Set<String> infrastructure=args.length==4?new HashSet<String>(Arrays.asList(args[3].split(","))):Collections.<String>emptySet();
        int classes=0;
        try(JarFile compiled=new JarFile(args[0]);JarFile retained=new JarFile(args[1]);
            JarOutputStream output=new JarOutputStream(new java.io.BufferedOutputStream(Files.newOutputStream(Paths.get(args[2])), 65536))){
            Enumeration<JarEntry> entries=compiled.entries();
            while(entries.hasMoreElements()){
                JarEntry entry=entries.nextElement();if(entry.isDirectory())continue;
                byte[] bytes=read(compiled.getInputStream(entry));
                if(entry.getName().endsWith(".class")){
                    JarEntry template=retained.getJarEntry(entry.getName());
                    if(template==null)throw new IllegalStateException("No bridge template for "+entry.getName());
                    bytes=restore(bytes,read(retained.getInputStream(template)),infrastructure);classes++;
                }
                output.putNextEntry(new JarEntry(entry.getName()));output.write(bytes);output.closeEntry();
            }
        }
        System.out.println("PASS restored_classes="+classes+" basic_verifier=true");
    }
}
