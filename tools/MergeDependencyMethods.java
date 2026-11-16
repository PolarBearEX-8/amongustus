import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.util.*;

/** Install explicitly indexed Java method bodies, retaining every original class contract. */
public final class MergeDependencyMethods {
 static Map<String,ClassNode> read(Path path)throws Exception{Map<String,ClassNode> out=new LinkedHashMap<>();try(JarFile jar=new JarFile(path.toFile())){Enumeration<JarEntry> entries=jar.entries();while(entries.hasMoreElements()){JarEntry e=entries.nextElement();if(!e.getName().endsWith(".class"))continue;ClassNode n=new ClassNode();new ClassReader(jar.getInputStream(e)).accept(n,ClassReader.SKIP_DEBUG);out.put(n.name,n);}}return out;}
 static MethodNode method(ClassNode c,String name,String desc){if(c==null)return null;for(MethodNode m:c.methods)if(m.name.equals(name)&&m.desc.equals(desc))return m;return null;}
 static String code(MethodNode m){Textifier t=new Textifier();TraceMethodVisitor visitor=new TraceMethodVisitor(t);for(TryCatchBlockNode block:m.tryCatchBlocks)block.accept(visitor);m.instructions.accept(visitor);StringWriter out=new StringWriter();t.print(new PrintWriter(out));return out.toString();}
 public static void main(String[] args)throws Exception{
  Map<String,ClassNode> original=read(Paths.get(args[0])),overlay=read(Paths.get(args[1])),compiled=read(Paths.get(args[2]));Map<String,ClassNode> changed=new LinkedHashMap<>();Set<String> seen=new HashSet<>();List<String> records=new ArrayList<>();records.add("class\tmethod\tdescriptor\toriginal_instruction_sequence_identical\tclass_contract_preserved");
  boolean expectIdentical=false;Map<String,ClassNode> templates=Collections.emptyMap();
  for(int i=6;i<args.length;i++){if(args[i].equals("--expect-identical"))expectIdentical=true;else if(args[i].equals("--retained-bridges")&&i+1<args.length)templates=read(Paths.get(args[++i]));else throw new IllegalArgumentException("Unknown dependency merge option");}int methods=0;
  for(String line:Files.readAllLines(Paths.get(args[3]),StandardCharsets.UTF_8)){
   String[] row=line.split("\t",-1);if(row.length<6||row[0].equals("runtime_class"))continue;
   String owner=row[0],name=row[2],desc=row[3];if(!seen.add(owner+"\t"+name+"\t"+desc))throw new AssertionError("Duplicate patch method");
   ClassNode target=changed.get(owner);if(target==null){target=overlay.containsKey(owner)?overlay.get(owner):original.get(owner);if(target==null)throw new AssertionError("Missing target class "+owner);changed.put(owner,target);}
   MethodNode before=method(target,name,desc),replacement=method(compiled.get(owner),name,desc);
   if(before==null||replacement==null||name.startsWith("<")||(before.access&(Opcodes.ACC_NATIVE|Opcodes.ACC_ABSTRACT))!=0||((before.access^replacement.access)&Opcodes.ACC_STATIC)!=0)throw new AssertionError("Illegal dependency patch: "+owner+" "+name+desc);
   boolean identical=code(before).equals(code(replacement));if(expectIdentical&&!identical)throw new AssertionError("Recovered method changed bytecode: "+owner+" "+name+desc);
   // Access, signature, annotations, declared exceptions, fields and class initializers
   // belong to the retained class, never to the compilation stand-in.
   before.instructions=replacement.instructions;before.tryCatchBlocks=replacement.tryCatchBlocks;before.localVariables=null;before.visibleLocalVariableAnnotations=null;before.invisibleLocalVariableAnnotations=null;before.maxStack=replacement.maxStack;before.maxLocals=replacement.maxLocals;
   records.add(owner+"\t"+name+"\t"+desc+"\t"+identical+"\ttrue");methods++;
  }
  int bridges=0;
  for(ClassNode node:changed.values()){
   ClassNode comp=compiled.get(node.name);
   for(int at=0;at<node.methods.size();at++)for(AbstractInsnNode ins:node.methods.get(at).instructions){
    if(ins instanceof InvokeDynamicInsnNode){
     InvokeDynamicInsnNode indy=(InvokeDynamicInsnNode)ins;
     if(indy.bsmArgs!=null)for(Object arg:indy.bsmArgs)if(arg instanceof Handle){
      Handle h=(Handle)arg;
      if(h.getOwner().equals(node.name)&&h.getName().startsWith("lambda$")&&method(node,h.getName(),h.getDesc())==null){
       MethodNode lm=method(comp,h.getName(),h.getDesc());if(lm!=null)node.methods.add(lm);
      }
     }
    }else if(ins instanceof MethodInsnNode){
     MethodInsnNode call=(MethodInsnNode)ins;
     if(!call.owner.equals(node.name)||!(call.name.matches("recovery\\$indy\\$[0-9]+")||call.name.matches("recovery\\$constant\\$[0-9]+"))||method(node,call.name,call.desc)!=null)continue;
     MethodNode bridge=method(comp,call.name,call.desc);
     if(bridge==null||(bridge.access&Opcodes.ACC_NATIVE)!=0)bridge=method(templates.get(node.name),call.name,call.desc);
     if(bridge==null||(bridge.access&(Opcodes.ACC_PRIVATE|Opcodes.ACC_STATIC|Opcodes.ACC_NATIVE|Opcodes.ACC_ABSTRACT))!=(Opcodes.ACC_PRIVATE|Opcodes.ACC_STATIC))throw new AssertionError("Missing executable retained Java bridge: "+node.name+" "+call.name+call.desc);
     node.methods.add(bridge);bridges++;
    }
   }
  }
  try(JarOutputStream out=new JarOutputStream(new java.io.BufferedOutputStream(Files.newOutputStream(Paths.get(args[4])), 65536))){for(ClassNode node:changed.values()){ClassWriter writer=new ClassWriter(0);node.accept(writer);out.putNextEntry(new JarEntry(node.name+".class"));out.write(writer.toByteArray());out.closeEntry();}}
  Files.write(Paths.get(args[5]),records,StandardCharsets.UTF_8);System.out.println("DEPENDENCY_METHODS_INSTALLED classes="+changed.size()+" methods="+methods+" retained_java_bridges="+bridges+" original_contracts_preserved=true require_identical="+expectIdentical);
 }
}
