import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

/** Static compatibility audit, not a class loader or proof of gameplay equivalence. */
public final class AuditSourceCandidate {
    static Map<String,ClassNode> headers=new HashMap<String,ClassNode>();
    static void load(String path)throws Exception{
        try(JarFile jar=new JarFile(path)){
            Enumeration<JarEntry> entries=jar.entries();
            while(entries.hasMoreElements()){
                JarEntry entry=entries.nextElement();if(!entry.getName().endsWith(".class"))continue;
                ClassNode node=new ClassNode();new ClassReader(RestoreSourceBridges.read(jar.getInputStream(entry))).accept(node,ClassReader.SKIP_CODE|ClassReader.SKIP_DEBUG|ClassReader.SKIP_FRAMES);headers.put(node.name,node);
            }
        }
    }
    static boolean member(String owner,String name,String desc,boolean field,Set<String> visited){
        if(!visited.add(owner))return false;
        ClassNode host=headers.get(owner);if(host==null)return false;
        if(field){for(FieldNode node:host.fields)if(node.name.equals(name)&&node.desc.equals(desc))return true;}
        else{for(MethodNode node:host.methods)if(node.name.equals(name)&&node.desc.equals(desc))return true;}
        if(name.equals("<init>"))return false;
        if(host.superName!=null&&member(host.superName,name,desc,field,visited))return true;
        for(String parent:host.interfaces)if(member(parent,name,desc,field,visited))return true;
        return false;
    }
    static void reference(String owner,String name,String desc,boolean field,Set<String> missing){
        if(!headers.containsKey(owner)){
            if(owner.startsWith("net/")||owner.startsWith("cc/")||owner.startsWith("augustus/recovered/"))missing.add("CLASS\t"+owner);
            return;
        }
        if(!member(owner,name,desc,field,new HashSet<String>()))missing.add((field?"FIELD":"METHOD")+"\t"+owner+"\t"+name+"\t"+desc);
    }
    static void handle(Handle handle,Set<String> missing){
        reference(handle.getOwner(),handle.getName(),handle.getDesc(),handle.getTag()<=Opcodes.H_PUTSTATIC,missing);
    }
    static Set<String> references(ClassNode host){
        Set<String> missing=new TreeSet<String>();
        for(MethodNode method:host.methods)for(AbstractInsnNode instruction:method.instructions.toArray()){
            if(instruction instanceof MethodInsnNode){MethodInsnNode n=(MethodInsnNode)instruction;reference(n.owner,n.name,n.desc,false,missing);}
            else if(instruction instanceof FieldInsnNode){FieldInsnNode n=(FieldInsnNode)instruction;reference(n.owner,n.name,n.desc,true,missing);}
            else if(instruction instanceof InvokeDynamicInsnNode){InvokeDynamicInsnNode n=(InvokeDynamicInsnNode)instruction;handle(n.bsm,missing);for(Object argument:n.bsmArgs)if(argument instanceof Handle)handle((Handle)argument,missing);}
            else if(instruction instanceof LdcInsnNode&&((LdcInsnNode)instruction).cst instanceof Handle)handle((Handle)((LdcInsnNode)instruction).cst,missing);
        }
        return missing;
    }
    static ClassNode read(JarFile jar,String name)throws Exception{
        ClassNode node=new ClassNode();new ClassReader(RestoreSourceBridges.read(jar.getInputStream(jar.getJarEntry(name+".class")))).accept(node,0);return node;
    }
    public static void main(String[] args)throws Exception{
        load(args[0]);load(args[1]);
        List<String> report=new ArrayList<String>();int audited=0;
        try(JarFile original=new JarFile(args[1]);JarFile candidate=new JarFile(args[2])){
            Enumeration<JarEntry> entries=candidate.entries();
            while(entries.hasMoreElements()){
                JarEntry entry=entries.nextElement();if(!entry.getName().endsWith(".class"))continue;
                String name=entry.getName().substring(0,entry.getName().length()-6);
                ClassNode before=read(original,name),after=read(candidate,name);
                if(!Objects.equals(before.superName,after.superName)||!before.interfaces.equals(after.interfaces))throw new AssertionError("Hierarchy changed");
                Set<String> oldFields=new TreeSet<String>(),newFields=new TreeSet<String>(),oldMethods=new TreeSet<String>(),newMethods=new TreeSet<String>();
                for(FieldNode field:before.fields)oldFields.add(field.name+field.desc+":"+field.access);
                for(FieldNode field:after.fields)newFields.add(field.name+field.desc+":"+field.access);
                if(!oldFields.equals(newFields))throw new AssertionError("Field ABI changed: "+oldFields+" / "+newFields);
                int nativeCount=0;
                for(MethodNode method:before.methods)oldMethods.add(method.name+method.desc);
                for(MethodNode method:after.methods){
                    if(!RestoreSourceBridges.bridge(method.name))newMethods.add(method.name+method.desc);
                    if((method.access&Opcodes.ACC_NATIVE)!=0)nativeCount++;
                }
                if(!oldMethods.equals(newMethods)){
                    Set<String> missing=new TreeSet<String>(oldMethods);missing.removeAll(newMethods);
                    Set<String> added=new TreeSet<String>(newMethods);added.removeAll(oldMethods);
                    throw new AssertionError("Method ABI changed missing="+missing+" added="+added);
                }
                headers.put(name,before);Set<String> oldMissing=references(before);
                headers.put(name,after);Set<String> newMissing=references(after);newMissing.removeAll(oldMissing);
                if(!newMissing.isEmpty())throw new AssertionError("New unresolved links: "+newMissing);
                if(nativeCount!=0)throw new AssertionError("Unrestored native declarations "+nativeCount);
                report.add("PASS "+name+" fields="+oldFields.size()+" original_method_signatures="+oldMethods.size()+" added_bridges="+(after.methods.size()-before.methods.size())+" new_unresolved_links=0 remaining_native_declarations=0");audited++;
            }
        }
        report.add("audited="+audited+" original_client_executed=false gameplay_verified=false");
        Files.write(Paths.get(args[3]),report,StandardCharsets.UTF_8);for(String line:report)System.out.println(line);
    }
}
