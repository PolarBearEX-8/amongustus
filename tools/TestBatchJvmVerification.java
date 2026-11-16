import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.*;

/** Actual JVM verification, with no class initializers or module constructors invoked. */
public final class TestBatchJvmVerification {
 public static void main(String[] args)throws Exception{
  final Map<String,byte[]> types=new HashMap<String,byte[]>();List<String> names=new ArrayList<String>();
  for(int i=0;i<args.length-2;i++)try(JarFile jar=new JarFile(args[i])){
   Enumeration<JarEntry> entries=jar.entries();while(entries.hasMoreElements()){
    JarEntry entry=entries.nextElement();if(!entry.getName().endsWith(".class"))continue;String name=entry.getName().substring(0,entry.getName().length()-6).replace('/','.');
    types.put(name,GuardEarlyChatClear.read(jar,entry.getName()));if(i==args.length-3)names.add(name);
   }
  }
  ClassLoader loader=new ClassLoader(TestBatchJvmVerification.class.getClassLoader()){
   protected Class<?> findClass(String name)throws ClassNotFoundException{byte[] bytes=types.get(name);if(bytes==null)throw new ClassNotFoundException(name);return defineClass(name,bytes,0,bytes.length);}
  };
  List<String> report=new ArrayList<String>();report.add("class\tstatus\tdetail");int passed=0,rejected=0;
  try(JarOutputStream output=new JarOutputStream(new java.io.BufferedOutputStream(Files.newOutputStream(Paths.get(args[args.length-2])), 65536))){
   for(String name:names)try{
    Class<?> type=Class.forName(name,false,loader);type.getDeclaredConstructors();
    String path=name.replace('.','/')+".class";output.putNextEntry(new JarEntry(path));output.write(types.get(name));output.closeEntry();passed++;report.add(name.replace('.','/')+"\tJVM_VERIFIED\tNOT_INITIALIZED");
   }catch(Throwable error){rejected++;report.add(name.replace('.','/')+"\tHELD\t"+error.toString().replace("\r","\\r").replace("\n","\\n").replace("\t","\\t"));}
  }
  Files.write(Paths.get(args[args.length-1]),report,StandardCharsets.UTF_8);System.out.println("BATCH_REAL_JVM_VERIFY approved="+passed+" held="+rejected+" class_initialization=false constructor_invocation=false");
 }
}
