import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;
import org.objectweb.asm.tree.analysis.*;

/** Keep the normal menu initialization reachable before optional client modules exist. */
public final class GuardEarlyMenuInitialization implements Opcodes {
 static final String HOST="net/47L6";
 static MethodNode target(ClassNode host){for(MethodNode m:host.methods)if(m.name.equals("46")&&m.desc.equals("()V"))return m;throw new IllegalStateException("Missing menu initializer");}
 static LabelNode baseTarget(MethodNode m){
  for(AbstractInsnNode n:m.instructions)if(n instanceof JumpInsnNode&&n.getOpcode()==IFNE){
   LabelNode label=((JumpInsnNode)n).label;AbstractInsnNode first=label;
   while(first!=null&&first.getOpcode()<0)first=first.getNext();
   AbstractInsnNode second=first==null?null:first.getNext();while(second!=null&&second.getOpcode()<0)second=second.getNext();
   if(first instanceof VarInsnNode&&first.getOpcode()==ALOAD&&((VarInsnNode)first).var==0&&second instanceof TypeInsnNode&&second.getOpcode()==NEW&&((TypeInsnNode)second).desc.equals("net/47xe"))return label;
  }throw new IllegalStateException("Missing original normal-menu initialization path");
 }
 static byte[] transform(byte[] bytes)throws Exception{
  ClassNode node=new ClassNode();new ClassReader(bytes).accept(node,0);if(!node.name.equals(HOST))throw new IllegalArgumentException("Wrong class");
  MethodNode m=target(node);LabelNode base=baseTarget(m);FieldInsnNode first=null;int modules=0;
  for(AbstractInsnNode n:m.instructions){if(n instanceof FieldInsnNode){FieldInsnNode f=(FieldInsnNode)n;
   if(first==null&&f.getOpcode()==GETSTATIC&&f.owner.equals("net/47rF")&&f.name.equals("48")&&f.desc.equals("Lnet/47R3;"))first=f;
   if(f.owner.equals("net/47R3")&&f.name.equals("41B")&&f.desc.equals("Lnet/47H8;"))modules++;
  }}if(first==null||modules!=2)throw new IllegalStateException("Unexpected menu-selection shape");
  InsnList guard=new InsnList();guard.add(new FieldInsnNode(GETSTATIC,"net/47rF","48","Lnet/47R3;"));guard.add(new JumpInsnNode(IFNULL,base));
  guard.add(new FieldInsnNode(GETSTATIC,"net/47rF","48","Lnet/47R3;"));guard.add(new FieldInsnNode(GETFIELD,"net/47R3","41B","Lnet/47H8;"));guard.add(new JumpInsnNode(IFNULL,base));
  m.instructions.insertBefore(first,guard);ClassWriter w=new ClassWriter(ClassWriter.COMPUTE_MAXS);node.accept(w);byte[] patched=w.toByteArray();
  ClassNode checked=new ClassNode();new ClassReader(patched).accept(checked,0);new Analyzer<BasicValue>(new BasicVerifier()).analyze(HOST,target(checked));return patched;
 }
}
