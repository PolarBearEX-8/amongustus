import java.io.*;
import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.jar.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

/** Explicit user-requested changes layered over audited recovered business source. */
public final class CustomizeClient implements Opcodes {
 static final String MODULE="recovery/custom/ModuleCustomization",ALAN="recovery/custom/AlanVisuals";
 static InsnList objectCall(String owner,String name,String desc){InsnList out=new InsnList();out.add(new VarInsnNode(ALOAD,0));out.add(new MethodInsnNode(INVOKESTATIC,owner,name,desc,false));return out;}
 static MethodNode method(ClassNode c,String name,String... descs){for(String desc:descs)for(MethodNode m:c.methods)if(m.name.equals(name)&&m.desc.equals(desc))return m;return null;}
 static void replace(MethodNode m,InsnList code){m.instructions=code;m.tryCatchBlocks.clear();m.localVariables=null;m.visibleLocalVariableAnnotations=null;m.invisibleLocalVariableAnnotations=null;m.access&=~(ACC_NATIVE|ACC_ABSTRACT);}
 static void gl(InsnList code,String name,String desc){code.add(new MethodInsnNode(INVOKESTATIC,"org/lwjgl/opengl/GL11",name,desc,false));}
 static void state(InsnList code,String name,String desc){code.add(new MethodInsnNode(INVOKESTATIC,"net/47d1",name,desc,false));}
 static int findParamSlot(MethodNode m,String paramDesc){
  int slot=(m.access&ACC_STATIC)==0?1:0;
  for(Type arg:Type.getArgumentTypes(m.desc)){if(arg.getDescriptor().equals(paramDesc))return slot;slot+=arg.getSize();}
  throw new IllegalStateException("Parameter "+paramDesc+" missing in "+m.name+m.desc);
 }
 static int[] paramSlots(MethodNode m){
  Type[] args=Type.getArgumentTypes(m.desc);int[] slots=new int[args.length];int slot=(m.access&ACC_STATIC)==0?1:0;
  for(int i=0;i<args.length;i++){slots[i]=slot;slot+=args[i].getSize();}
  return slots;
 }
 static boolean hasEmptyRotationGuard(ClassNode c){
  if(!c.name.equals("net/47gg"))return false;
  MethodNode m=method(c,"48","(Ljava/util/ArrayList;FF)Lnet/44g;");if(m==null)return false;
  for(AbstractInsnNode ins:m.instructions)if(ins instanceof MethodInsnNode){MethodInsnNode call=(MethodInsnNode)ins;if(call.owner.equals("java/util/ArrayList")&&call.name.equals("isEmpty"))return true;}
  return false;
 }
 static AbstractInsnNode nextReal(AbstractInsnNode ins){
  for(AbstractInsnNode cur=ins==null?null:ins.getNext();cur!=null;cur=cur.getNext())if(cur.getOpcode()>=0)return cur;
  return null;
 }
 static boolean patchCapeSelection(MethodNode m){
  if(m==null)return false;
  // Primary structural anchor: right after GlStateManager.color(1,1,1,1), LayerCape loads the customCape boolean local.
  for(AbstractInsnNode ins:m.instructions.toArray()){
   if(ins instanceof MethodInsnNode){
    MethodInsnNode call=(MethodInsnNode)ins;
    if(call.getOpcode()==INVOKESTATIC&&call.owner.equals("net/47d1")&&call.name.equals("47")&&call.desc.equals("(FFFF)V")){
     AbstractInsnNode next=nextReal(ins);
     if(next instanceof VarInsnNode&&next.getOpcode()==ILOAD){
      InsnList code=new InsnList();code.add(new VarInsnNode(ALOAD,1));code.add(new MethodInsnNode(INVOKESTATIC,ALAN,"legacyCape","(ZLjava/lang/Object;)Z",false));
      m.instructions.insert(next,code);return true;
     }
    }
   }
  }
  // Fallback structural anchor: find boolean local stored from customCape check (GETFIELD net/47R3.46Q -> ... -> ISTORE <slot>).
  int capeSlot=-1;
  for(AbstractInsnNode ins:m.instructions.toArray()){
   if(ins instanceof FieldInsnNode){
    FieldInsnNode f=(FieldInsnNode)ins;
    if(f.getOpcode()==GETFIELD&&f.name.equals("46Q")){
     for(AbstractInsnNode cur=ins.getNext();cur!=null;cur=cur.getNext()){
      if(cur instanceof VarInsnNode&&cur.getOpcode()==ISTORE){capeSlot=((VarInsnNode)cur).var;break;}
     }
     if(capeSlot>=0)break;
    }
   }
  }
  if(capeSlot<0)capeSlot=15;
  int reads=0;
  for(AbstractInsnNode ins:m.instructions.toArray()){
   if(ins instanceof VarInsnNode&&ins.getOpcode()==ILOAD&&((VarInsnNode)ins).var==capeSlot&&++reads==2){
    InsnList code=new InsnList();code.add(new VarInsnNode(ALOAD,1));code.add(new MethodInsnNode(INVOKESTATIC,ALAN,"legacyCape","(ZLjava/lang/Object;)Z",false));
    m.instructions.insert(ins,code);return true;
   }
  }
  return false;
 }
 static int patchMainMenuCaptions(MethodNode m){
  if(m==null)throw new IllegalStateException("Main menu drawScreen missing");
  List<MethodInsnNode> toStrings=new ArrayList<MethodInsnNode>();
  for(AbstractInsnNode ins:m.instructions.toArray()){
   if(ins instanceof MethodInsnNode){
    MethodInsnNode call=(MethodInsnNode)ins;
    if(call.owner.equals("java/lang/StringBuilder")&&call.name.equals("toString")&&call.desc.equals("()Ljava/lang/String;"))toStrings.add(call);
   }
  }
  if(toStrings.size()==3){
   m.instructions.insert(toStrings.get(1),new MethodInsnNode(INVOKESTATIC,MODULE,"mainMenuName","(Ljava/lang/String;)Ljava/lang/String;",false));
   InsnList caption=new InsnList();caption.add(new InsnNode(POP));caption.add(new LdcInsnNode("Created by Alan78 ChatGPT Codex"));
   m.instructions.insert(toStrings.get(2),caption);
   return 2;
  }
  // Fallback if javac constant-folded any caption into an LDC string literal or changed StringBuilder count.
  boolean patchedName=false,patchedCreated=false;
  for(AbstractInsnNode ins:m.instructions.toArray()){
   if(ins instanceof MethodInsnNode){
    MethodInsnNode call=(MethodInsnNode)ins;
    if(call.owner.equals("java/lang/StringBuilder")&&call.name.equals("toString")&&call.desc.equals("()Ljava/lang/String;")){
     boolean hasCreatedBy=false,hasBracket=false;
     for(AbstractInsnNode p=call.getPrevious();p!=null;p=p.getPrevious()){
      if(p instanceof TypeInsnNode&&p.getOpcode()==NEW&&"java/lang/StringBuilder".equals(((TypeInsnNode)p).desc))break;
      if(p instanceof LdcInsnNode&&((LdcInsnNode)p).cst instanceof String){
       String s=(String)((LdcInsnNode)p).cst;
       if(s.startsWith("Created by "))hasCreatedBy=true;
       if(s.contains("[")||s.contains("\u00a7e"))hasBracket=true;
      }
     }
     if(!patchedCreated&&hasCreatedBy){
      InsnList caption=new InsnList();caption.add(new InsnNode(POP));caption.add(new LdcInsnNode("Created by Alan78 ChatGPT Codex"));
      m.instructions.insert(call,caption);patchedCreated=true;
     }else if(!patchedName&&hasBracket){
      m.instructions.insert(call,new MethodInsnNode(INVOKESTATIC,MODULE,"mainMenuName","(Ljava/lang/String;)Ljava/lang/String;",false));
      patchedName=true;
     }
    }
   }else if(ins instanceof LdcInsnNode&&((LdcInsnNode)ins).cst instanceof String){
    String s=(String)((LdcInsnNode)ins).cst;
    AbstractInsnNode next=nextReal(ins);
    boolean partOfBuilder=next instanceof MethodInsnNode&&"java/lang/StringBuilder".equals(((MethodInsnNode)next).owner);
    if(!partOfBuilder){
     if(!patchedCreated&&s.startsWith("Created by ")){
      ((LdcInsnNode)ins).cst="Created by Alan78 ChatGPT Codex";patchedCreated=true;
     }else if(!patchedName&&(s.contains(" \u00a77[")||s.contains(" ["))){
      m.instructions.insert(ins,new MethodInsnNode(INVOKESTATIC,MODULE,"mainMenuName","(Ljava/lang/String;)Ljava/lang/String;",false));
      patchedName=true;
     }
    }
   }
  }
  if(!patchedName||!patchedCreated)throw new IllegalStateException("Main menu captions changed: builders="+toStrings.size()+" name="+patchedName+" created="+patchedCreated);
  return 2;
 }
 // The callers depend on both actual GL state and GlStateManager's cached state.
 // Preserve the legacy setup/teardown; only the geometry implementation changes.
 static InsnList roundedBegin(boolean conditional,int colorSlot){
  InsnList code=new InsnList();gl(code,"glPushMatrix","()V");
  if(conditional){state(code,"47","()Z");code.add(new VarInsnNode(ISTORE,11));state(code,"45","()Z");code.add(new VarInsnNode(ISTORE,12));}
  state(code,"423","()V");state(code,"421","()V");for(int value:new int[]{770,771,1,0})code.add(new LdcInsnNode(value));state(code,"44","(IIII)V");code.add(new VarInsnNode(ILOAD,colorSlot));code.add(new MethodInsnNode(INVOKESTATIC,"net/47rs","43","(I)V",false));return code;
 }
 static void roundedEnd(InsnList code,boolean conditional){
  if(conditional){LabelNode textureDone=new LabelNode(),blendDone=new LabelNode();code.add(new VarInsnNode(ILOAD,12));code.add(new JumpInsnNode(IFEQ,textureDone));state(code,"42y","()V");code.add(textureDone);code.add(new VarInsnNode(ILOAD,11));code.add(new JumpInsnNode(IFNE,blendDone));state(code,"42z","()V");code.add(blendDone);}
  else{state(code,"42y","()V");state(code,"42z","()V");}
  gl(code,"glPopMatrix","()V");code.add(new InsnNode(RETURN));
 }
 static void inert(MethodNode m){Type t=Type.getReturnType(m.desc);InsnList code=new InsnList();if(t.getSort()!=Type.VOID){code.add(new InsnNode(t.getSort()==Type.LONG?LCONST_0:t.getSort()==Type.DOUBLE?DCONST_0:t.getSort()==Type.FLOAT?FCONST_0:t.getSort()>=Type.ARRAY?ACONST_NULL:ICONST_0));}code.add(new InsnNode(t.getOpcode(IRETURN)));replace(m,code);}
 static void conditionalReturn(MethodNode m,String helper,String name,String desc,boolean resolution){
  LabelNode original=new LabelNode();InsnList code=new InsnList();code.add(new VarInsnNode(ALOAD,0));if(resolution)code.add(new VarInsnNode(ALOAD,findParamSlot(m,"Lnet/477t;")));code.add(new MethodInsnNode(INVOKESTATIC,helper,name,desc,false));code.add(new JumpInsnNode(IFEQ,original));code.add(new InsnNode(RETURN));code.add(original);m.instructions.insert(code);
 }
 static int patches;
 static byte[] transform(byte[] bytes,boolean module,Set<String> changed)throws Exception{
  ClassNode c=new ClassNode();new ClassReader(bytes).accept(c,0);int before=patches;
  if(!hasEmptyRotationGuard(c))patches+=GuardEmptyRotationSamples.patch(c);
  if(c.name.equals("net/46I"))for(MethodNode m:c.methods)for(AbstractInsnNode ins:m.instructions.toArray())if(ins instanceof MethodInsnNode){MethodInsnNode call=(MethodInsnNode)ins;if(call.owner.equals("net/47Lv")&&call.name.equals("47")&&call.desc.equals("(IIF)V")){m.instructions.insertBefore(ins,new MethodInsnNode(INVOKESTATIC,"recovery/custom/MenuBackground","beginFrame","()V",false));patches++;}}
  if(c.name.equals("net/493")){MethodNode m=method(c,"48","(I)V");boolean found=false;for(AbstractInsnNode ins:m.instructions.toArray())if(ins instanceof VarInsnNode&&ins.getOpcode()==ILOAD&&((VarInsnNode)ins).var==1){m.instructions.insertBefore(ins,objectCall("recovery/custom/MenuBackground","loading","(Ljava/lang/Object;)V"));patches++;found=true;break;}if(!found)throw new IllegalStateException("Loading progress branch missing");}
  if(c.name.equals("net/47P8")){MethodNode m=method(c,"46","(Lnet/47Bh;FFFFFFF)V");if(!patchCapeSelection(m))throw new IllegalStateException("Cape texture selection missing");patches++;}
  if(module&&(c.name.equals("net/47H2")||c.name.equals("net/47He")||c.name.equals("net/47kr")))for(MethodNode m:c.methods)if(m.name.equals("<init>"))for(AbstractInsnNode ins:m.instructions.toArray())if(ins.getOpcode()==RETURN){m.instructions.insertBefore(ins,objectCall(MODULE,"onModuleCreated","(Ljava/lang/Object;)V"));patches++;}
  if(c.name.equals("net/47H2")){MethodNode m=method(c,"44","(IFCILnet/477t;)V","(FLnet/477t;)V");if(m==null)throw new IllegalStateException("HUD onRender2D missing");conditionalReturn(m,ALAN,"watermark","(Ljava/lang/Object;Ljava/lang/Object;)Z",true);patches++;
   MethodNode imageMode=method(c,"44Z","()Z");if(imageMode==null)throw new IllegalStateException("HUD image-mode predicate missing");
   for(AbstractInsnNode ins:imageMode.instructions.toArray())if(ins.getOpcode()==IRETURN){InsnList code=new InsnList();code.add(new VarInsnNode(ALOAD,0));code.add(new MethodInsnNode(INVOKESTATIC,ALAN,"imageWatermark","(ZLjava/lang/Object;)Z",false));imageMode.instructions.insertBefore(ins,code);patches++;}
  }
  if(c.name.equals("net/47Bh")){
   MethodNode m=method(c,"44","()Lnet/47or;");LabelNode original=new LabelNode();InsnList code=objectCall(ALAN,"cape","(Ljava/lang/Object;)Ljava/lang/Object;");code.add(new InsnNode(DUP));code.add(new JumpInsnNode(IFNULL,original));code.add(new TypeInsnNode(CHECKCAST,"net/47or"));code.add(new InsnNode(ARETURN));code.add(original);code.add(new InsnNode(POP));m.instructions.insert(code);patches++;
  }
  if(c.name.equals("net/47T2"))for(String[] gate:new String[][]{{"49","40","0"},{"47","46","1"}}){MethodNode m=method(c,gate[0],"(J)Z","()Z");if(m==null)throw new IllegalStateException("ShaderOptions gate missing: "+gate[0]);InsnList code=new InsnList();code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,c.name,gate[1],"Lnet/47TV;"));code.add(new InsnNode(gate[2].equals("1")?ICONST_1:ICONST_0));code.add(new MethodInsnNode(INVOKESTATIC,MODULE,"shaderEnabled","(Ljava/lang/Object;Z)Z",false));code.add(new InsnNode(IRETURN));replace(m,code);patches++;}
  if(c.name.equals("net/47KP"))for(MethodNode m:c.methods)if(!m.name.startsWith("<")&&(m.access&(ACC_NATIVE|ACC_ABSTRACT))==0){Type[] args=Type.getArgumentTypes(m.desc);if(args.length>0&&args[0].equals(Type.getType(String.class))){int slot=(m.access&ACC_STATIC)==0?1:0;InsnList filter=new InsnList();filter.add(new VarInsnNode(ALOAD,slot));filter.add(new MethodInsnNode(INVOKESTATIC,"recovery/custom/MenuNotice","filter","(Ljava/lang/String;)Ljava/lang/String;",false));filter.add(new VarInsnNode(ASTORE,slot));m.instructions.insert(filter);patches++;}}
  if(c.name.equals("net/47rs")){
   // Color-object overloads use the same corner geometry as integer-color masks.
   for(String name:new String[]{"42","49"}){
    MethodNode colored=method(c,name,"(FFFFFILjava/awt/Color;)V");if(colored==null)throw new IllegalStateException("Colored rounded primitive missing");
    InsnList colorCode=new InsnList();state(colorCode,"423","()V");state(colorCode,"421","()V");for(int value:new int[]{770,771,1,0})colorCode.add(new LdcInsnNode(value));state(colorCode,"44","(IIII)V");
    colorCode.add(new VarInsnNode(ALOAD,6));colorCode.add(new MethodInsnNode(INVOKESTATIC,"net/47rs","42","(Ljava/awt/Color;)V",false));
    for(int i=0;i<5;i++)colorCode.add(new VarInsnNode(FLOAD,i));colorCode.add(new VarInsnNode(ALOAD,6));colorCode.add(new MethodInsnNode(INVOKEVIRTUAL,"java/awt/Color","getRGB","()I",false));
    colorCode.add(new InsnNode(name.equals("49")?ICONST_1:ICONST_0));colorCode.add(new InsnNode(ICONST_1));colorCode.add(new InsnNode(ICONST_0));colorCode.add(new InsnNode(ICONST_0));
    colorCode.add(new MethodInsnNode(INVOKESTATIC,"recovery/custom/RoundedRect","drawCorners","(FFFFFIZZZZ)V",false));state(colorCode,"42y","()V");state(colorCode,"42z","()V");colorCode.add(new InsnNode(RETURN));replace(colored,colorCode);patches++;
   }

   MethodNode fill=method(c,"49","(FFFFFII)V");if(fill==null)throw new IllegalStateException("Shared rounded fill missing");
   InsnList code=roundedBegin(true,6);for(int i=0;i<5;i++)code.add(new VarInsnNode(FLOAD,i));code.add(new VarInsnNode(ILOAD,6));code.add(new MethodInsnNode(INVOKESTATIC,"recovery/custom/RoundedRect","draw","(FFFFFI)V",false));roundedEnd(code,true);replace(fill,code);patches++;
   MethodNode corners=method(c,"43","(FFFFFIZZZZI)V");if(corners==null)throw new IllegalStateException("Selective rounded fill missing");
   code=roundedBegin(false,10);for(int i=0;i<5;i++)code.add(new VarInsnNode(FLOAD,i));code.add(new VarInsnNode(ILOAD,10));for(int i=6;i<10;i++)code.add(new VarInsnNode(ILOAD,i));code.add(new MethodInsnNode(INVOKESTATIC,"recovery/custom/RoundedRect","drawCorners","(FFFFFIZZZZ)V",false));roundedEnd(code,false);replace(corners,code);patches++;
   MethodNode size=method(c,"49","(DDDDDLjava/awt/Color;)V");if(size==null)throw new IllegalStateException("Sized rounded fill missing");
   code=new InsnList();gl(code,"glPushMatrix","()V");code.add(new LdcInsnNode(3553));gl(code,"glDisable","(I)V");code.add(new LdcInsnNode(2848));gl(code,"glEnable","(I)V");code.add(new LdcInsnNode(3042));gl(code,"glEnable","(I)V");for(int value:new int[]{770,771,0,1})code.add(new LdcInsnNode(value));state(code,"44","(IIII)V");code.add(new VarInsnNode(ALOAD,10));code.add(new MethodInsnNode(INVOKESTATIC,"net/47rs","42","(Ljava/awt/Color;)V",false));for(int i=0;i<10;i+=2)code.add(new VarInsnNode(DLOAD,i));code.add(new VarInsnNode(ALOAD,10));code.add(new MethodInsnNode(INVOKESTATIC,"recovery/custom/RoundedRect","drawSize","(DDDDDLjava/awt/Color;)V",false));code.add(new LdcInsnNode(3042));gl(code,"glDisable","(I)V");code.add(new LdcInsnNode(2848));gl(code,"glDisable","(I)V");code.add(new LdcInsnNode(3553));gl(code,"glEnable","(I)V");gl(code,"glPopMatrix","()V");code.add(new InsnNode(RETURN));replace(size,code);patches++;
  }
  if(c.name.equals("net/47jk")){
   MethodNode m=method(c,"45","(Ljava/lang/String;FJFI)F","(Ljava/lang/String;FFI)F");if(m==null)throw new IllegalStateException("SlickFontRenderer draw method missing");
   int[] slots=paramSlots(m);int textSlot=slots[0],xSlot=slots[1],ySlot=slots.length==5?slots[3]:slots[2],colorSlot=slots.length==5?slots[4]:slots[3];
   InsnList code=new InsnList();code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,c.name,"43","Lorg/newdawn/slick/UnicodeFont;"));code.add(new VarInsnNode(ALOAD,0));code.add(new FieldInsnNode(GETFIELD,c.name,"42","F"));code.add(new VarInsnNode(ALOAD,textSlot));code.add(new VarInsnNode(FLOAD,xSlot));code.add(new VarInsnNode(FLOAD,ySlot));code.add(new VarInsnNode(ILOAD,colorSlot));code.add(new MethodInsnNode(INVOKESTATIC,"recovery/custom/FontRepair","draw","(Ljava/lang/Object;FLjava/lang/String;FFI)F",false));code.add(new InsnNode(FRETURN));replace(m,code);patches++;
   int fonts=0;for(MethodNode constructor:c.methods)if(constructor.name.equals("<init>"))for(AbstractInsnNode ins:constructor.instructions.toArray())if(ins instanceof FieldInsnNode){FieldInsnNode field=(FieldInsnNode)ins;if(field.getOpcode()==PUTFIELD&&field.owner.equals(c.name)&&field.name.equals("43")&&field.desc.equals("Lorg/newdawn/slick/UnicodeFont;")){InsnList prepare=new InsnList();prepare.add(new InsnNode(DUP));prepare.add(new MethodInsnNode(INVOKESTATIC,"recovery/custom/FontRepair","prepare","(Ljava/lang/Object;)V",false));constructor.instructions.insertBefore(ins,prepare);fonts++;patches++;}}
   if(fonts<1)throw new IllegalStateException("Unexpected font constructor count "+fonts);
  }
  if(c.name.equals("net/47L6")){MethodNode m=method(c,"49","(IIF)V");if(m!=null){conditionalReturn(m,ALAN,"background","(Ljava/lang/Object;)Z",false);patches++;}}
  if(c.name.equals("net/47Lv"))for(String[] entry:new String[][]{{"45","()V"},{"45","(I)V"},{"42","(I)V"}}){MethodNode m=method(c,entry[0],entry[1]);if(m!=null){LabelNode original=new LabelNode();InsnList code=objectCall("recovery/custom/MenuBackground","draw","(Ljava/lang/Object;)Z");code.add(new JumpInsnNode(IFEQ,original));code.add(new InsnNode(RETURN));code.add(original);m.instructions.insert(code);patches++;}}
  if(c.name.equals("net/47uI")){MethodNode m=method(c,"48","(Lnet/477t;ZJ)V","(Lnet/477t;Z)V");if(m==null)throw new IllegalStateException("Missing shared custom background entry");LabelNode original=new LabelNode();InsnList code=new InsnList();code.add(new MethodInsnNode(INVOKESTATIC,"recovery/custom/MenuBackground","drawCurrent","()Z",false));code.add(new JumpInsnNode(IFEQ,original));code.add(new InsnNode(RETURN));code.add(original);m.instructions.insert(code);patches++;}
  if(c.name.equals("net/47Lw")){MethodNode m=method(c,"47","(IIF)V");patches+=patchMainMenuCaptions(m);}
  // Apply the name only to the displayed label; authentication/session values are untouched.
  if(c.name.equals("net/47kr"))for(MethodNode m:c.methods)for(AbstractInsnNode ins:m.instructions.toArray())if(ins instanceof MethodInsnNode){MethodInsnNode call=(MethodInsnNode)ins;if(call.owner.equals("java/lang/StringBuilder")&&call.name.equals("toString")&&call.desc.equals("()Ljava/lang/String;")){m.instructions.insert(ins,new MethodInsnNode(INVOKESTATIC,MODULE,"accountLabel","(Ljava/lang/String;)Ljava/lang/String;",false));patches++;}}
  boolean moduleCollections=module||c.name.equals("net/47OQ")||c.name.equals("net/47OC")||c.name.equals("net/47R3")||c.name.matches("net/47L[A-Za-z0-9_]");
  for(MethodNode m:c.methods){
   if(!moduleCollections)continue;
   for(AbstractInsnNode ins:m.instructions.toArray()){
    String desc=null;if(ins instanceof MethodInsnNode){MethodInsnNode call=(MethodInsnNode)ins;if(call.owner.startsWith("recovery/custom/"))continue;desc=call.desc;}
    if(ins instanceof InvokeDynamicInsnNode)desc=((InvokeDynamicInsnNode)ins).desc;if(desc==null)continue;
    Type ret=Type.getReturnType(desc);if(ret.getSort()!=Type.OBJECT)continue;String name=ret.getInternalName();
    if(!(name.equals("java/util/List")||name.equals("java/util/Collection")||name.equals("java/util/ArrayList")||name.equals("java/util/concurrent/CopyOnWriteArrayList")))continue;
    InsnList filter=new InsnList();filter.add(new MethodInsnNode(INVOKESTATIC,MODULE,"filter","(Ljava/util/Collection;)Ljava/util/Collection;",false));filter.add(new TypeInsnNode(CHECKCAST,name));m.instructions.insert(ins,filter);patches++;
   }
  }
  if(patches==before)return bytes;
  for(MethodNode m:c.methods)for(AbstractInsnNode ins:m.instructions.toArray())if(ins instanceof FrameNode)m.instructions.remove(ins);
  changed.add(c.name+"\tpatches="+(patches-before));ClassWriter writer=new ClassWriter(ClassWriter.COMPUTE_MAXS);c.accept(writer);return writer.toByteArray();
 }
 public static void main(String[] args)throws Exception{
  // baseline raw core; audited source overlay; helper classes; output overlay; module names
  Set<String> modules=new HashSet<String>(Files.readAllLines(Paths.get(args[4]),StandardCharsets.UTF_8));Map<String,byte[]> entries=new LinkedHashMap<String,byte[]>();
  try(JarFile raw=new JarFile(args[0])){Enumeration<JarEntry> es=raw.entries();while(es.hasMoreElements()){JarEntry e=es.nextElement();if(e.getName().endsWith(".class"))entries.put(e.getName(),GuardEarlyChatClear.read(raw,e.getName()));}}
  try(JarFile source=new JarFile(args[1])){Enumeration<JarEntry> es=source.entries();while(es.hasMoreElements()){JarEntry e=es.nextElement();if(e.getName().endsWith(".class"))entries.put(e.getName(),GuardEarlyChatClear.read(source,e.getName()));}}
  SortedSet<String> changed=new TreeSet<String>();int classes=0;
  try(JarOutputStream out=new JarOutputStream(new java.io.BufferedOutputStream(Files.newOutputStream(Paths.get(args[3])), 65536))){
   for(Map.Entry<String,byte[]> entry:entries.entrySet()){
    String name=entry.getKey().substring(0,entry.getKey().length()-6);byte[] original=entry.getValue(),result=transform(original,modules.contains(name),changed);
    // Only changed classes are emitted; unchanged originals remain in the source/base overlay.
    if(result==original)continue;out.putNextEntry(new JarEntry(entry.getKey()));out.write(result);out.closeEntry();classes++;
   }
   try(JarFile helpers=new JarFile(args[2])){Enumeration<JarEntry> es=helpers.entries();while(es.hasMoreElements()){JarEntry e=es.nextElement();if(!e.getName().endsWith(".class"))continue;out.putNextEntry(new JarEntry(e.getName()));out.write(GuardEarlyChatClear.read(helpers,e.getName()));out.closeEntry();}}
  }
  Files.write(Paths.get(args[3]+".changes.tsv"),changed,StandardCharsets.UTF_8);System.out.println("CUSTOMIZED_CLASSES="+classes+" sites="+patches+" mouse_keyboard_operations=0");
 }
}
