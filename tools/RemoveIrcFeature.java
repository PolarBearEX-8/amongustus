import java.nio.file.*;
import java.util.*;
import java.util.jar.*;
import java.io.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.tree.analysis.*;

/** Explicit feature deletion; plans each use before rewriting any class. */
public final class RemoveIrcFeature implements Opcodes {
    static final Set<String> REMOVED=new HashSet<String>(Arrays.asList("net/47kf","net/47k8","net/47Lu","net/47y9","net/47yp","net/47yu","net/47k3","net/474j"));
    static boolean descriptor(String d){for(String n:REMOVED)if(d.contains("L"+n+";"))return true;return false;}
    static boolean root(FieldInsnNode f){return descriptor(f.desc)||REMOVED.contains(f.owner);}
    static final class V extends BasicValue {
        final boolean irc;
        V(Type type,boolean irc){super(type);this.irc=irc;}
        public boolean equals(Object o){return o instanceof V&&irc==((V)o).irc&&super.equals(o);}
        public int hashCode(){return 31*super.hashCode()+(irc?1:0);}
    }
    static final class Flow extends BasicInterpreter {
        Flow(){super(ASM9);}
        V wrap(BasicValue b,boolean irc){return b==null?null:new V(b.getType(),irc);}
        boolean tagged(BasicValue b){return b instanceof V&&((V)b).irc;}
        public BasicValue newValue(Type t){return wrap(super.newValue(t),false);}
        public BasicValue newOperation(AbstractInsnNode i)throws AnalyzerException{return wrap(super.newOperation(i),i instanceof FieldInsnNode&&root((FieldInsnNode)i)||i instanceof TypeInsnNode&&REMOVED.contains(((TypeInsnNode)i).desc));}
        public BasicValue copyOperation(AbstractInsnNode i,BasicValue v){return v;}
        public BasicValue unaryOperation(AbstractInsnNode i,BasicValue v)throws AnalyzerException {
            return wrap(super.unaryOperation(i,v),i instanceof FieldInsnNode&&root((FieldInsnNode)i)||tagged(v));
        }
        public BasicValue binaryOperation(AbstractInsnNode i,BasicValue a,BasicValue b)throws AnalyzerException{return wrap(super.binaryOperation(i,a,b),tagged(a)||tagged(b));}
        public BasicValue ternaryOperation(AbstractInsnNode i,BasicValue a,BasicValue b,BasicValue c)throws AnalyzerException{return wrap(super.ternaryOperation(i,a,b,c),false);}
        // Preserve feature provenance through reference-valued expressions, including
        // command-prefix -> lowercase -> startsWith. Boolean boundaries become false.
        public BasicValue naryOperation(AbstractInsnNode i,List<? extends BasicValue> v)throws AnalyzerException{
            BasicValue result=super.naryOperation(i,v);boolean irc=false;for(BasicValue value:v)irc|=tagged(value);
            return wrap(result,irc&&result!=null&&result.isReference());
        }
        public BasicValue merge(BasicValue a,BasicValue b){if(a.equals(b))return a;return wrap(super.merge(a,b),tagged(a)||tagged(b));}
    }
    static void pop(InsnList code,Type t){code.add(new InsnNode(t.getSize()==2?POP2:POP));}
    static void value(InsnList code,Type t){switch(t.getSort()){
        case Type.VOID:return;
        case Type.LONG:code.add(new InsnNode(LCONST_0));return;
        case Type.DOUBLE:code.add(new InsnNode(DCONST_0));return;
        case Type.FLOAT:code.add(new InsnNode(FCONST_0));return;
        case Type.OBJECT:case Type.ARRAY:code.add(new InsnNode(ACONST_NULL));return;
        default:code.add(new InsnNode(ICONST_0));
    }}
    static int run(ClassNode c,boolean mutate,List<String> plan)throws Exception{
        int count=0;
        for(MethodNode m:c.methods){
            if(m.instructions.size()==0)continue;
            if(c.name.equals("net/43o")&&m.name.equals("45")&&m.desc.equals("(Ljava/lang/String;J)Ljava/lang/String;")){
                plan.add(c.name+"\t"+m.name+m.desc+"\tREMOVE_IRC_NAME_FORMATTING_RETURN_INPUT");count++;
                if(mutate){m.instructions.clear();m.instructions.add(new VarInsnNode(ALOAD,(m.access&ACC_STATIC)==0?1:0));m.instructions.add(new InsnNode(ARETURN));m.tryCatchBlocks.clear();m.localVariables=null;m.visibleLocalVariableAnnotations=null;m.invisibleLocalVariableAnnotations=null;}
                continue;
            }
            Flow flow=new Flow();Frame<BasicValue>[] frames=new Analyzer<BasicValue>(flow).analyze(c.name,m);
            AbstractInsnNode[] insns=m.instructions.toArray();
            for(int index=0;index<insns.length;index++){
                AbstractInsnNode ins=insns[index];Frame<BasicValue> frame=frames[index];if(frame==null)continue;
                String desc=null;boolean receiver=false;
                if(ins instanceof MethodInsnNode){MethodInsnNode call=(MethodInsnNode)ins;desc=call.desc;receiver=call.getOpcode()!=INVOKESTATIC;if(call.name.equals("<init>"))continue;}
                if(ins instanceof InvokeDynamicInsnNode)desc=((InvokeDynamicInsnNode)ins).desc;
                if(desc==null)continue;
                Type[] args=Type.getArgumentTypes(desc);int size=args.length+(receiver?1:0),start=frame.getStackSize()-size;
                boolean irc=false;for(int a=start;a<frame.getStackSize();a++)irc|=flow.tagged(frame.getStack(a));if(!irc)continue;
                Type ret=Type.getReturnType(desc);
                plan.add(c.name+"\t"+m.name+m.desc+"\t"+index+"\tCALL\t"+desc+"\t"+ret.getClassName());count++;
                if(mutate){InsnList code=new InsnList();for(int a=args.length-1;a>=0;a--)pop(code,args[a]);if(receiver)code.add(new InsnNode(POP));value(code,ret);m.instructions.insertBefore(ins,code);m.instructions.remove(ins);}
            }
            for(AbstractInsnNode ins:insns){
                if(ins instanceof FieldInsnNode&&root((FieldInsnNode)ins)){
                    FieldInsnNode f=(FieldInsnNode)ins;plan.add(c.name+"\t"+m.name+m.desc+"\tFIELD\t"+f.owner+"."+f.name+f.desc);count++;
                    if(mutate){InsnList code=new InsnList();if(f.getOpcode()==GETFIELD){code.add(new InsnNode(POP));value(code,Type.getType(f.desc));}else if(f.getOpcode()==GETSTATIC)value(code,Type.getType(f.desc));else {pop(code,Type.getType(f.desc));if(f.getOpcode()==PUTFIELD)code.add(new InsnNode(POP));}m.instructions.insertBefore(ins,code);m.instructions.remove(ins);}
                }
                if(ins instanceof TypeInsnNode&&REMOVED.contains(((TypeInsnNode)ins).desc)){
                    TypeInsnNode t=(TypeInsnNode)ins;plan.add(c.name+"\t"+m.name+m.desc+"\tTYPE\t"+t.getOpcode()+"\t"+t.desc);count++;
                    if(mutate){InsnList code=new InsnList();if(t.getOpcode()==NEW)code.add(new InsnNode(ACONST_NULL));else if(t.getOpcode()==INSTANCEOF){code.add(new InsnNode(POP));code.add(new InsnNode(ICONST_0));}else if(t.getOpcode()!=CHECKCAST)throw new IllegalStateException("Unexpected feature type opcode");m.instructions.insertBefore(ins,code);m.instructions.remove(ins);}
                }
                if(ins instanceof MethodInsnNode){MethodInsnNode call=(MethodInsnNode)ins;if(call.name.equals("<init>")&&REMOVED.contains(call.owner)){
                    plan.add(c.name+"\t"+m.name+m.desc+"\tCONSTRUCTOR\t"+call.owner+call.desc);count++;
                    if(mutate){InsnList code=new InsnList();Type[] args=Type.getArgumentTypes(call.desc);for(int a=args.length-1;a>=0;a--)pop(code,args[a]);code.add(new InsnNode(POP));m.instructions.insertBefore(ins,code);m.instructions.remove(ins);}
                }}
            }
            if(mutate){m.localVariables=null;m.visibleLocalVariableAnnotations=null;m.invisibleLocalVariableAnnotations=null;for(AbstractInsnNode ins:m.instructions.toArray())if(ins instanceof FrameNode)m.instructions.remove(ins);}
        }
        if(mutate)c.fields.removeIf(f->descriptor(f.desc));
        return count;
    }
    public static void main(String[] args)throws Exception{
        Map<String,byte[]> entries=new TreeMap<String,byte[]>();
        for(int a:new int[]{0,4,1}){
            if(a>=args.length)continue;
            try(JarFile jar=new JarFile(args[a])){Enumeration<JarEntry> es=jar.entries();while(es.hasMoreElements()){
                JarEntry e=es.nextElement();if(!e.getName().endsWith(".class"))continue;
                if(a==4&&!Arrays.asList("472g.class","47eJ.class","47Ua.class","47cq.class").contains(e.getName()))continue;
                try(InputStream in=jar.getInputStream(e)){ByteArrayOutputStream out=new ByteArrayOutputStream();byte[] b=new byte[8192];int n;while((n=in.read(b))!=-1)out.write(b,0,n);byte[] data=out.toByteArray();String name=new ClassReader(data).getClassName()+".class";entries.put(name,data);}
            }}
        }
        boolean mutate=args.length>3&&args[3].equals("apply");List<String> plan=new ArrayList<String>();int classes=0;
        try(JarOutputStream out=new JarOutputStream(new java.io.BufferedOutputStream(Files.newOutputStream(Paths.get(args[2]+".jar")), 65536))){
            for(Map.Entry<String,byte[]> entry:entries.entrySet()){
                String name=entry.getKey().substring(0,entry.getKey().length()-6);if(REMOVED.contains(name)){plan.add("DELETE\t"+name);continue;}
                boolean relevant=false;String raw=new String(entry.getValue(),java.nio.charset.StandardCharsets.ISO_8859_1);for(String target:REMOVED)relevant|=raw.contains(target);if(!relevant)continue;
                ClassNode c=new ClassNode();new ClassReader(entry.getValue()).accept(c,0);int count=run(c,mutate,plan);if(count==0)continue;classes++;
                if(mutate){ClassWriter writer=new ClassWriter(ClassWriter.COMPUTE_MAXS);c.accept(writer);byte[] data=writer.toByteArray();String text=new String(data,java.nio.charset.StandardCharsets.ISO_8859_1);for(String target:REMOVED)if(text.contains(target))throw new IllegalStateException("Remaining direct reference "+name+" -> "+target);out.putNextEntry(new JarEntry(entry.getKey()));out.write(data);out.closeEntry();}
            }
        }
        Files.write(Paths.get(args[2]+".tsv"),plan,java.nio.charset.StandardCharsets.UTF_8);System.out.println("IRC_FEATURE_PLAN classes="+classes+" operations="+plan.size()+" apply="+mutate);
    }
}
