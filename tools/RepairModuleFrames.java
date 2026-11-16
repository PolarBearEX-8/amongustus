import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

/** Computes missing verifier frames without loading or initializing client classes. */
public final class RepairModuleFrames implements Opcodes {
 final Map<String,ClassNode> headers=new HashMap<String,ClassNode>();
 void load(String path)throws Exception{
  try(JarFile jar=new JarFile(path)){Enumeration<JarEntry> entries=jar.entries();while(entries.hasMoreElements()){
   JarEntry e=entries.nextElement();if(!e.getName().endsWith(".class"))continue;
   ClassNode n=new ClassNode();new ClassReader(GuardEarlyChatClear.read(jar,e.getName())).accept(n,ClassReader.SKIP_CODE|ClassReader.SKIP_DEBUG|ClassReader.SKIP_FRAMES);headers.put(n.name,n);
  }}
 }
 boolean assignable(String target,String source,Set<String> seen){
  if(target.equals(source)||target.equals("java/lang/Object"))return true;if(!seen.add(source))return false;
  ClassNode n=headers.get(source);if(n!=null){if(n.superName!=null&&assignable(target,n.superName,seen))return true;for(String i:n.interfaces)if(assignable(target,i,seen))return true;return false;}
  if((source.startsWith("java/")||source.startsWith("javax/"))&&(target.startsWith("java/")||target.startsWith("javax/"))){
   try{return Class.forName(target.replace('/','.'),false,RepairModuleFrames.class.getClassLoader()).isAssignableFrom(Class.forName(source.replace('/','.'),false,RepairModuleFrames.class.getClassLoader()));}catch(ClassNotFoundException e){throw new IllegalStateException(e);}
  }
  throw new IllegalStateException("Missing hierarchy metadata: "+source+" while checking "+target);
 }
 String common(String a,String b){
  if(assignable(a,b,new HashSet<String>()))return a;if(assignable(b,a,new HashSet<String>()))return b;
  ClassNode n=headers.get(a);if(n!=null){for(String p=n.superName;p!=null;){if(assignable(p,b,new HashSet<String>()))return p;ClassNode parent=headers.get(p);p=parent==null?null:parent.superName;}return "java/lang/Object";}
  try{Class<?> c=Class.forName(a.replace('/','.'),false,RepairModuleFrames.class.getClassLoader());while(c!=null){String p=Type.getInternalName(c);if(assignable(p,b,new HashSet<String>()))return p;c=c.getSuperclass();}return "java/lang/Object";}catch(ClassNotFoundException e){throw new IllegalStateException(e);}
 }
 public static void main(String[] args)throws Exception{
  RepairModuleFrames tool=new RepairModuleFrames();tool.load(args[0]);tool.load(args[1]);tool.load(args[2]);int methods=0,classes=0;
  try(JarFile input=new JarFile(args[2]);JarOutputStream output=new JarOutputStream(new java.io.BufferedOutputStream(Files.newOutputStream(Paths.get(args[3])), 65536))){
   Enumeration<JarEntry> entries=input.entries();while(entries.hasMoreElements()){
    JarEntry entry=entries.nextElement();byte[] bytes=GuardEarlyChatClear.read(input,entry.getName());
    if(entry.getName().endsWith(".class")){
     ClassNode node=new ClassNode();new ClassReader(bytes).accept(node,0);int repaired=0;
     for(int index=0;index<node.methods.size();index++){
      MethodNode method=node.methods.get(index);boolean branch=!method.tryCatchBlocks.isEmpty(),frames=false;
      for(AbstractInsnNode ins:method.instructions){if(ins instanceof FrameNode)frames=true;if(ins instanceof JumpInsnNode||ins instanceof TableSwitchInsnNode||ins instanceof LookupSwitchInsnNode)branch=true;}
      if(!branch||frames||(method.access&(ACC_ABSTRACT|ACC_NATIVE))!=0)continue;
      ClassWriter writer=new ClassWriter(ClassWriter.COMPUTE_FRAMES|ClassWriter.COMPUTE_MAXS){protected String getCommonSuperClass(String a,String b){return tool.common(a,b);}};
      writer.visit(node.version,node.access,node.name,node.signature,node.superName,node.interfaces.toArray(new String[0]));method.accept(writer);writer.visitEnd();
      ClassNode framed=new ClassNode();new ClassReader(writer.toByteArray()).accept(framed,0);node.methods.set(index,framed.methods.get(0));repaired++;methods++;
     }
     ClassWriter writer=new ClassWriter(0);node.accept(writer);bytes=writer.toByteArray();classes++;System.out.println("FRAME_REPAIR "+node.name+" methods="+repaired);
    }
    output.putNextEntry(new JarEntry(entry.getName()));output.write(bytes);output.closeEntry();
   }
  }
  System.out.println("FRAME_REPAIR_PASS classes="+classes+" missing_frame_methods_repaired="+methods+" original_client_initialized=false");
 }
}
