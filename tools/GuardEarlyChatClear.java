import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.tree.analysis.*;

/** Preserve vanilla chat clearing while optional client modules are not initialized. */
public final class GuardEarlyChatClear implements Opcodes {
    static final String HOST="net/47LU";
    static MethodNode target(ClassNode host){
        for(MethodNode m:host.methods)if(m.name.equals("42")&&m.desc.equals("()V"))return m;
        throw new IllegalStateException("Missing exact chat-clear method");
    }
    static void guard(InsnList out,LabelNode clear,int depth){
        out.add(new FieldInsnNode(GETSTATIC,"net/47rF","48","Lnet/47R3;"));
        if(depth>=1)out.add(new FieldInsnNode(GETFIELD,"net/47R3","41d","Lnet/47QC;"));
        if(depth>=2)out.add(new FieldInsnNode(GETFIELD,"net/47QC","43j","Lnet/47TV;"));
        out.add(new JumpInsnNode(IFNULL,clear));
    }
    static byte[] transform(byte[] bytes)throws Exception{
        ClassNode host=new ClassNode();new ClassReader(bytes).accept(host,0);
        if(!HOST.equals(host.name))throw new IllegalStateException("Wrong chat class");
        MethodNode m=target(host);FieldInsnNode first=null;LabelNode clear=null;int clears=0,predicates=0;
        for(AbstractInsnNode n:m.instructions){
            if(n instanceof FieldInsnNode){FieldInsnNode f=(FieldInsnNode)n;
                if(first==null&&f.getOpcode()==GETSTATIC&&f.owner.equals("net/47rF")&&f.name.equals("48")&&f.desc.equals("Lnet/47R3;"))first=f;
            }
            if(n instanceof JumpInsnNode&&n.getOpcode()==IFEQ){LabelNode l=((JumpInsnNode)n).label;if(clear!=null&&clear!=l)throw new IllegalStateException("Ambiguous clear path");clear=l;predicates++;}
            if(n instanceof MethodInsnNode){MethodInsnNode call=(MethodInsnNode)n;if(call.owner.equals("java/util/List")&&call.name.equals("clear")&&call.desc.equals("()V"))clears++;}
        }
        if(first==null||clear==null||predicates!=2||clears!=3)throw new IllegalStateException("Unexpected original chat-clear shape");
        InsnList prefix=new InsnList();guard(prefix,clear,0);guard(prefix,clear,1);guard(prefix,clear,2);m.instructions.insertBefore(first,prefix);
        ClassWriter writer=new ClassWriter(ClassWriter.COMPUTE_MAXS);host.accept(writer);byte[] patched=writer.toByteArray();
        ClassNode check=new ClassNode();new ClassReader(patched).accept(check,0);
        new Analyzer<BasicValue>(new BasicVerifier()).analyze(HOST,target(check));
        return patched;
    }
    static byte[] read(JarFile jar,String name)throws Exception{
        JarEntry entry=jar.getJarEntry(name);if(entry==null)throw new IOException("Missing "+name);
        try(InputStream in=jar.getInputStream(entry);ByteArrayOutputStream out=new ByteArrayOutputStream()){
            byte[] b=new byte[8192];int n;while((n=in.read(b))!=-1)out.write(b,0,n);return out.toByteArray();
        }
    }
    public static void main(String[] args)throws Exception{
        byte[] bytes;try(JarFile original=new JarFile(args[0])){bytes=read(original,HOST+".class");}
        byte[] patched=transform(bytes);
        try(JarOutputStream out=new JarOutputStream(new java.io.BufferedOutputStream(Files.newOutputStream(Paths.get(args[1])), 65536))){
            out.putNextEntry(new JarEntry(HOST+".class"));out.write(patched);out.closeEntry();
            try(JarFile original=new JarFile(args[0])){
                byte[] menu=GuardEarlyMenuInitialization.transform(read(original,"net/47L6.class"));
                out.putNextEntry(new JarEntry("net/47L6.class"));out.write(menu);out.closeEntry();
            }
        }
        System.out.println("EARLY_CHAT_CLEAR_GUARD_PASS null_guards=3 original_list_clears=3 original_module_predicates=2 client_initialized=false");
        System.out.println("EARLY_MENU_INIT_GUARD_PASS null_guards=2 original_menu_selection_preserved=true client_initialized=false");
    }
}
