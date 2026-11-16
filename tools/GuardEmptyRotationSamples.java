import java.util.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

/** Use the same neutral sample that the original rotation sample initializer installs. */
public final class GuardEmptyRotationSamples implements Opcodes {
 public static int patch(ClassNode host){
  if(!host.name.equals("net/47gg"))return 0;
  for(MethodNode method:host.methods)if(method.name.equals("48")&&method.desc.equals("(Ljava/util/ArrayList;FF)Lnet/44g;")){
   int reads=0;for(AbstractInsnNode ins:method.instructions)if(ins instanceof MethodInsnNode){MethodInsnNode call=(MethodInsnNode)ins;if(call.owner.equals("java/util/ArrayList")&&call.name.equals("get"))reads++;}
   if(reads!=1)throw new IllegalStateException("Rotation sample selector changed; expected one list access");
   LabelNode populated=new LabelNode();InsnList guard=new InsnList();guard.add(new VarInsnNode(ALOAD,1));guard.add(new MethodInsnNode(INVOKEVIRTUAL,"java/util/ArrayList","isEmpty","()Z",false));guard.add(new JumpInsnNode(IFEQ,populated));
   guard.add(new TypeInsnNode(NEW,"net/44g"));guard.add(new InsnNode(DUP));guard.add(new InsnNode(FCONST_0));guard.add(new InsnNode(FCONST_0));guard.add(new InsnNode(FCONST_0));guard.add(new MethodInsnNode(INVOKESPECIAL,"net/44g","<init>","(FFF)V",false));guard.add(new InsnNode(ARETURN));guard.add(populated);guard.add(new FrameNode(F_SAME,0,null,0,null));method.instructions.insert(guard);method.maxStack=Math.max(method.maxStack,5);return 1;
  }
  throw new IllegalStateException("Rotation sample selector not found");
 }
}
