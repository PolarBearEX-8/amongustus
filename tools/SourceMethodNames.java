import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.*;
import java.util.jar.*;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.*;

/** Analysis/source projection: JVM return-only overloads are not legal Java overloads. */
public final class SourceMethodNames {
    static final Map<String,String> signatures = new HashMap<String,String>();
    static String name(String name, String descriptor) {
        if (!name.startsWith("method_")) return name;
        try {
            byte[] digest=MessageDigest.getInstance("SHA-256").digest(descriptor.getBytes(StandardCharsets.UTF_8));
            StringBuilder result=new StringBuilder(name).append('$');
            for(int i=0;i<5;i++)result.append(String.format(Locale.ROOT,"%02x",digest[i]&255));
            String mapped=result.toString();
            String prior=signatures.put(mapped,descriptor);
            if(prior!=null&&!prior.equals(descriptor))throw new IllegalStateException("descriptor hash collision");
            return mapped;
        }catch(java.security.NoSuchAlgorithmException impossible){throw new AssertionError(impossible);}
    }
    public static void main(String[] args)throws Exception{
        final SortedSet<String> rows=new TreeSet<String>();
        Remapper names=new Remapper(){
            public String mapMethodName(String owner,String original,String descriptor){
                String mapped=name(original,descriptor);
                if(!mapped.equals(original))rows.add(owner+"\t"+original+"\t"+descriptor+"\t"+mapped);
                return mapped;
            }
        };
        int classes=0;
        try(JarFile input=new JarFile(args[0]);JarOutputStream output=new JarOutputStream(new java.io.BufferedOutputStream(Files.newOutputStream(Paths.get(args[1])), 65536))){
            Enumeration<JarEntry> entries=input.entries();
            while(entries.hasMoreElements()){
                JarEntry entry=entries.nextElement();if(entry.isDirectory())continue;
                byte[] bytes;
                try(InputStream in=input.getInputStream(entry);ByteArrayOutputStream buffer=new ByteArrayOutputStream()){
                    byte[] chunk=new byte[8192];int n;while((n=in.read(chunk))!=-1)buffer.write(chunk,0,n);bytes=buffer.toByteArray();
                }
                if(entry.getName().endsWith(".class")){
                    ClassWriter writer=new ClassWriter(0);new ClassReader(bytes).accept(new ClassRemapper(writer,names),0);bytes=writer.toByteArray();classes++;
                }
                output.putNextEntry(new JarEntry(entry.getName()));output.write(bytes);output.closeEntry();
            }
        }
        rows.add("owner\toriginal_name\tdescriptor\tsource_name");
        Files.write(Paths.get(args[2]),rows,StandardCharsets.UTF_8);
        System.out.println("PASS source_method_projection classes="+classes+" mappings="+(rows.size()-1)+" runtime_compatible=false");
    }
}
