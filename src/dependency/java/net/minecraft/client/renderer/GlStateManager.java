package net.minecraft.client.renderer;

import client.setting.GlStateManagerSType;
import client.util.ArmorScoreCalculator;
import client.util.BitSetHolder;
import client.util.BuildManager;
import client.util.DampenerAccumulator;
import client.util.EnabledManager;
import client.util.HotbarItemSelector;
import client.util.InventorySorter;
import client.util.ItemFilter;
import client.util.ItemWeightCalculator;
import client.util.LockCountManager;
import client.util.PreEndHandManager;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.inventory.SlotCraftingResult;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class GlStateManager {
   public static HotbarItemSelector glState = new HotbarItemSelector(null);
   public static SlotCraftingResult glState12 = new SlotCraftingResult(2896);
   public static SlotCraftingResult[] glState17 = new SlotCraftingResult[8];
   public static GlColorMaterialState glState21 = new GlColorMaterialState(null);
   public static GlBlendState glState11 = new GlBlendState(null);
   public static ItemFilter glState14 = new ItemFilter(null);
   public static GlFogState glState3 = new GlFogState(null);
   public static GlCullState glState6 = new GlCullState(null);
   public static ArmorScoreCalculator glState9 = new ArmorScoreCalculator(null);
   public static GlColorLogicState glState22 = new GlColorLogicState(null);
   public static GlTexGenState glState8 = new GlTexGenState(null);
   public static GlClearState glState15 = new GlClearState(null);
   public static GlStencilState glState13 = new GlStencilState(null);
   public static SlotCraftingResult glState2 = new SlotCraftingResult(2977);
   public static int stateInt2 = 0;
   public static ItemWeightCalculator[] glState20 = new ItemWeightCalculator[32];
   public static int stateInt = 7425;
   public static SlotCraftingResult glState5 = new SlotCraftingResult(32826);
   public static GlColorMask glState4 = new GlColorMask(null);
   public static GlColor4f glState16 = new GlColor4f();
   public static boolean stateFlag2 = true;
   public static LockCountManager glState18 = new LockCountManager();
   public static GlAlphaState glState10 = new GlAlphaState();
   public static LockCountManager glState19 = new LockCountManager();
   public static EnabledManager glState7 = new EnabledManager();
   public static boolean stateFlag = false;

   public static void deleteTexture(int count) {
      if (count != 0) {
         GL11.glDeleteTextures(count);

         for (ItemWeightCalculator itemWeightCalculator : glState20) {
            if (itemWeightCalculator.indexOffset == count) {
               itemWeightCalculator.indexOffset = 0;
            }
         }
      }
   }

   public static void glStateAction(int count, int count2, int count3, int count4, int count5, int count6, int count7, int count8, IntBuffer buffer) {
      GL11.glTexImage2D(count, count2, count3, count4, count5, count6, count7, count8, buffer);
   }

   public static int getTexLevelParameteri(int count, int count2, int count3) {
      return GL11.glGetTexLevelParameteri(count, count2, count3);
   }

   public static void alphaFunc(int count, float factor) {
      if (glState18.isActive2()) {
         glState10.applyIndex(count, factor);
      } else if (count != glState.indexOffset || factor != glState.scaleFactor) {
         glState.indexOffset = count;
         glState.scaleFactor = factor;
         GL11.glAlphaFunc(count, factor);
      }
   }

   public static void applyState() {
      glState16.red = glState16.green = glState16.blue = glState16.alpha = -1.0F;
   }

   public static int getStateInt() {
      return glState22.logicOp;
   }

   public static void colorMask(boolean flag) {
      glState3.slotCraftingResult.setState(flag);
   }

   public static boolean obtainState() {
      return SlotCraftingResult.checkSlotCraftingResult(glState21.slotCraftingResult);
   }

   public static void glStateAction(IntBuffer buffer) {
      ((Buffer)buffer).rewind();

      while (buffer.position() < buffer.limit()) {
         deleteTexture(buffer.get());
      }

      ((Buffer)buffer).rewind();
   }

   public static void applyIntBuffer(int count, IntBuffer buffer, IntBuffer buffer2) {
      GL14.glMultiDrawArrays(count, buffer, buffer2);
      if (BuildManager.canExecute() && !stateFlag) {
         int count2 = PreEndHandManager.noneManager7.getCount2();
         if (count2 > 1) {
            for (int index = 1; index < count2; index++) {
               PreEndHandManager.stringHandler34.applyIndex2(index);
               GL14.glMultiDrawArrays(count, buffer, buffer2);
            }

            PreEndHandManager.stringHandler34.applyIndex2(0);
         }
      }
   }

   public static void deleteLists(int count, int count2) {
      GL11.glDeleteLists(count, count2);
   }

   public static void translate(float posX, float posY, float posZ) {
      GL11.glTranslatef(posX, posY, posZ);
   }

   public static float getStateFloat() {
      return glState.scaleFactor;
   }

   public static boolean obtainState2() {
      return SlotCraftingResult.checkSlotCraftingResult(glState5);
   }

   public static void applyGlAlphaState(GlAlphaState glAlphaState) {
      if (!glState18.isActive2()) {
         applyGlAlphaStateByGlAlphaState(glState10);
         applyGlAlphaState2(glAlphaState);
         glState18.isActive();
      }
   }

   public static void applyFloat(float factor) {
      float factor2 = factor;
      if (factor < 0.0F) {
         factor2 = 0.0F;
      }

      if (factor2 != glState3.density) {
         glState3.density = factor2;
         GL11.glFogf(2914, factor2);
         if (BuildManager.canExecute()) {
            PreEndHandManager.applyFloat(factor2);
         }
      }
   }

   public static void applyIndexByIndex(int count) {
      GL11.glCallList(count);
      if (BuildManager.canExecute() && !stateFlag) {
         int count2 = PreEndHandManager.noneManager7.getCount2();
         if (count2 > 1) {
            for (int index = 1; index < count2; index++) {
               PreEndHandManager.stringHandler34.applyIndex2(index);
               GL11.glCallList(count);
            }

            PreEndHandManager.stringHandler34.applyIndex2(0);
         }
      }
   }

   public static void disableDepth() {
      glState14.slotCraftingResult.updateState();
   }

   public static void rotate(float posX, float posX2, float posY, float posZ) {
      GL11.glRotatef(posX, posX2, posY, posZ);
   }

   public static void glStateAction2(int count, int count2, int count3, int count4, int count5, int count6, int count7, int count8, IntBuffer buffer) {
      GL11.glTexSubImage2D(count, count2, count3, count4, count5, count6, count7, count8, buffer);
   }

   public static void viewport(int count, int count2, int count3, int count4) {
      GL11.glViewport(count, count2, count3, count4);
   }

   private static InventorySorter obtainState(GlStateManagerSType glStateManagerSType) {
      switch (DampenerAccumulator.countArray[glStateManagerSType.ordinal()]) {
         case 1:
            return glState8.inventorySorter;
         case 2:
            return glState8.inventorySorter4;
         case 3:
            return glState8.inventorySorter3;
         case 4:
            return glState8.inventorySorter2;
         default:
            return glState8.inventorySorter;
      }
   }

   public static int getStateInt2() {
      return glState6.mode;
   }

   public static void depthMask(boolean flag) {
      if (flag != glState14.state) {
         glState14.state = flag;
         GL11.glDepthMask(flag);
      }
   }

   public static void glLogicOp(int count) {
      if (count != glState22.logicOp) {
         glState22.logicOp = count;
         GL11.glLogicOp(count);
      }
   }

   public static int getStateInt3() {
      return glState3.mode;
   }

   public static void applyEnabledManager(EnabledManager enabledManager) {
      if (glState19.isActive2()) {
         enabledManager.applyEnabledManager(glState7);
      } else {
         enabledManager.applyFlag(
            SlotCraftingResult.checkSlotCraftingResult(glState11.slotCraftingResult),
            glState11.dstFactor,
            glState11.srcFactor,
            glState11.count,
            glState11.count2
         );
      }
   }

   public static void applyState3() {
      glState12.updateState2();
   }

   public static float getStateFloat2() {
      return glState3.end;
   }

   public static void glStateAction2(int count, int count2, int count3, int count4) {
      GL11.glTexCoordPointer(count, count2, count3, (long)count4);
   }

   public static boolean obtainState3() {
      return SlotCraftingResult.checkSlotCraftingResult(glState22.slotCraftingResult);
   }

   public static void applyGlStateManagerSType(GlStateManagerSType glStateManagerSType, int count) {
      InventorySorter inventorySorter = obtainState(glStateManagerSType);
      if (count != inventorySorter.count) {
         inventorySorter.count = count;
         GL11.glTexGeni(inventorySorter.indexOffset, 9472, count);
      }
   }

   public static int getStateInt4() {
      return glState20[stateInt2].indexOffset;
   }

   public static void applyIndex(int count) {
      if (count != glState3.mode) {
         glState3.mode = count;
         GL11.glFogi(2917, count);
         if (BuildManager.canExecute()) {
            PreEndHandManager.applyIndex7(count);
         }
      }
   }

   public static void glStateAction(FloatBuffer buffer) {
      GL11.glMultMatrix(buffer);
   }

   public static void blendFunc(int count, int count2) {
      GL11.glFogi(count, count2);
   }

   public static void glFogf(float factor) {
      if (factor != glState3.start) {
         glState3.start = factor;
         GL11.glFogf(2915, factor);
      }
   }

   public static void glStateAction(GlStateManagerSType glStateManagerSType) {
      obtainState(glStateManagerSType).slotCraftingResult.updateState2();
   }

   public static void glStateAction3(int count, int count2, int count3, int count4) {
      GL11.glVertexPointer(count, count2, count3, (long)count4);
   }

   public static boolean obtainState4() {
      return glState14.state;
   }

   public static float getStateFloat3() {
      return glState3.density;
   }

   public static void glStateAction(int count, int count2, int count3, ByteBuffer buffer) {
      GL11.glTexCoordPointer(count, count2, count3, buffer);
   }

   public static void applyState4() {
      glState9.slotCraftingResult.updateState();
   }

   public static void setActiveTexture(int activeTexture) {
      if (activeTexture != glState20[stateInt2].indexOffset) {
         glState20[stateInt2].indexOffset = activeTexture;
         GL11.glBindTexture(3553, activeTexture);
         if (BitSetHolder.isActive()) {
            BitSetHolder.setByIndex(activeTexture);
         }
      }
   }

   public static void disableLight(int count) {
      glState20[count].slotCraftingResult.updateState();
   }

   public static void glStateAction(int count, FloatBuffer buffer) {
      GL11.glFog(count, buffer);
   }

   public static void glStateAction(int count, int count2, int count3, int count4, int count5, int count6, int count7, int count8) {
      GL11.glCopyTexSubImage2D(count, count2, count3, count4, count5, count6, count7, count8);
   }

   public static void applyIntBuffer(IntBuffer buffer) {
      GL11.glCallLists(buffer);
      if (BuildManager.canExecute() && !stateFlag) {
         int count = PreEndHandManager.noneManager7.getCount2();
         if (count > 1) {
            for (int index = 1; index < count; index++) {
               PreEndHandManager.stringHandler34.applyIndex2(index);
               GL11.glCallLists(buffer);
            }

            PreEndHandManager.stringHandler34.applyIndex2(0);
         }
      }
   }

   public static void enableState4(int count) {
      glState17[count].updateState();
   }

   public static int getStateInt(int count) {
      return glState20[count].indexOffset;
   }

   public static void glStateAction(int count, int count2, int count3, int count4, IntBuffer buffer) {
      GL11.glGetTexImage(count, count2, count3, count4, buffer);
   }

   public static void applyGlAlphaStateByGlAlphaState(GlAlphaState glAlphaState) {
      if (glState18.isActive2()) {
         glAlphaState.applyGlAlphaState(glState10);
      } else {
         glAlphaState.applyFlag(SlotCraftingResult.checkSlotCraftingResult(glState.slotCraftingResult), glState.indexOffset, glState.scaleFactor);
      }
   }

   public static void glColorMaterial(int count, int count2) {
      if (count != glState21.face || count2 != glState21.mode) {
         glState21.face = count;
         glState21.mode = count2;
         GL11.glColorMaterial(count, count2);
      }
   }

   public static int getStateInt5() {
      return glState11.dstFactor;
   }

   public static void tryBlendFuncSeparate(int count, int count2, int count3, int count4) {
      if (glState19.isActive2()) {
         glState7.applyIndex(count, count2, count3, count4);
      } else if (count != glState11.dstFactor || count2 != glState11.srcFactor || count3 != glState11.count || count4 != glState11.count2) {
         glState11.dstFactor = count;
         glState11.srcFactor = count2;
         glState11.count = count3;
         glState11.count2 = count4;
         if (BuildManager.canExecute()) {
            PreEndHandManager.internalStringHandler.applyIndex(count, count2, count3, count4);
         }

         OpenGlHelper.applyIndex2(count, count2, count3, count4);
      }
   }

   public static void applyState5() {
      GL11.glEndList();
      stateFlag = false;
   }

   public static boolean obtainState5() {
      return SlotCraftingResult.checkSlotCraftingResult(glState2);
   }

   public static float getStateFloat4() {
      return glState9.scaleFactor1;
   }

   public static int getStateInt6() {
      return GL11.glGenTextures();
   }

   public static boolean obtainState6() {
      return SlotCraftingResult.checkSlotCraftingResult(glState6.slotCraftingResult);
   }

   public static void updateState3() {
      if (glState19.isActiveAsFlag()) {
         applyEnabledManagerByEnabledManager(glState7);
      }
   }

   public static void glClear(int count) {
      if (stateFlag2) {
         GL11.glClear(count);
      }
   }

   public static void applyEnabledManagerByEnabledManager(EnabledManager enabledManager) {
      if (glState19.isActive2()) {
         glState7.applyEnabledManager(enabledManager);
      } else {
         glState11.slotCraftingResult.setState(enabledManager.isEnabled());
         if (!enabledManager.isActive()) {
            glStateAction3(enabledManager.getCount(), enabledManager.getCount3());
         } else {
            tryBlendFuncSeparate(enabledManager.getCount(), enabledManager.getCount3(), enabledManager.getCount4(), enabledManager.getCount2());
         }
      }
   }

   public static void translate3d(double posX, double posY, double posZ) {
      GL11.glScaled(posX, posY, posZ);
   }

   public static void glClearDepth(double offset) {
      if (offset != glState15.value) {
         glState15.value = offset;
         GL11.glClearDepth(offset);
      }
   }

   public static void glStateAction(int count, int count2, float factor) {
      GL11.glTexParameterf(count, count2, factor);
   }

   public static void applyIndex(int count, int count2, int count3) {
      GL11.glDrawArrays(count, count2, count3);
      if (BuildManager.canExecute() && !stateFlag) {
         int count4 = PreEndHandManager.noneManager7.getCount2();
         if (count4 > 1) {
            for (int index = 1; index < count4; index++) {
               PreEndHandManager.stringHandler34.applyIndex2(index);
               GL11.glDrawArrays(count, count2, count3);
            }

            PreEndHandManager.stringHandler34.applyIndex2(0);
         }
      }
   }

   public static void glStateAction(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      GL11.glOrtho(posX, posX2, posX3, posX4, posY, posZ);
   }

   public static void glStateAction2(int count, int count2, int count3, ByteBuffer buffer) {
      GL11.glColorPointer(count, count2, count3, buffer);
   }

   public static float getStateFloat5() {
      return glState9.scaleFactor;
   }

   public static void translate3d2(double posX, double posY, double posZ) {
      GL11.glTranslated(posX, posY, posZ);
   }

   public static void glFogfByFloat(float factor) {
      if (factor != glState3.end) {
         glState3.end = factor;
         GL11.glFogf(2916, factor);
      }
   }

   public static void glShadeModel(int count) {
      if (count != stateInt) {
         stateInt = count;
         GL11.glShadeModel(count);
      }
   }

   public static void applyState6() {
      glState21.slotCraftingResult.updateState();
   }

   public static void applyEnabledManager2(EnabledManager enabledManager) {
      if (!glState19.isActive2()) {
         applyEnabledManager(glState7);
         applyEnabledManagerByEnabledManager(enabledManager);
         glState19.isActive();
      }
   }

   public static boolean obtainState7() {
      return SlotCraftingResult.checkSlotCraftingResult(glState12);
   }

   public static int getStateInt7() {
      return glState14.indexOffset;
   }

   public static int getStateInt8() {
      return glState21.mode;
   }

   public static void glStateAction(GlStateManagerSType glStateManagerSType, int count, FloatBuffer buffer) {
      GL11.glTexGen(obtainState(glStateManagerSType).indexOffset, count, buffer);
   }

   public static void applyGlAlphaState2(GlAlphaState glAlphaState) {
      if (glState18.isActive2()) {
         glState10.applyGlAlphaState(glAlphaState);
      } else {
         glState.slotCraftingResult.setState(glAlphaState.isEnabled());
         alphaFunc(glAlphaState.getCount(), glAlphaState.getFactor());
      }
   }

   public static void translate3f2(float posX, float posY, float posZ) {
      color4f2(posX, posY, posZ, 1.0F);
   }

   public static void applyState7() {
      glState21.slotCraftingResult.updateState2();
   }

   public static void glStateAction5(int count, int count2, int count3, int count4) {
      GL11.glColorPointer(count, count2, count3, (long)count4);
   }

   public static boolean obtainState8() {
      return SlotCraftingResult.checkSlotCraftingResult(glState20[stateInt2].slotCraftingResult);
   }

   public static void glStateAction(int count, int count2, ByteBuffer buffer) {
      GL11.glNormalPointer(count, count2, buffer);
   }

   public static float getStateFloat6() {
      return glState3.start;
   }

   public static void enableState5(int count) {
      if (stateInt2 != count - OpenGlHelper.lightmapTexUnit) {
         stateInt2 = count - OpenGlHelper.lightmapTexUnit;
         OpenGlHelper.applyIndex6(count);
      }
   }

   public static void applyFloat(float posX, float posX2, float posY, float posZ) {
      if (posX != glState15.glColor4f.red || posX2 != glState15.glColor4f.green || posY != glState15.glColor4f.blue || posZ != glState15.glColor4f.alpha) {
         glState15.glColor4f.red = posX;
         glState15.glColor4f.green = posX2;
         glState15.glColor4f.blue = posY;
         glState15.glColor4f.alpha = posZ;
         GL11.glClearColor(posX, posX2, posY, posZ);
      }
   }

   public static void glDepthFunc(int count) {
      if (count != glState14.indexOffset) {
         glState14.indexOffset = count;
         GL11.glDepthFunc(count);
      }
   }

   public static void glStateAction3(int count, int count2, int count3, ByteBuffer buffer) {
      GL11.glVertexPointer(count, count2, count3, buffer);
   }

   public static void color4f2(float posX, float posX2, float posY, float posZ) {
      if (posX != glState16.red || posX2 != glState16.green || posY != glState16.blue || posZ != glState16.alpha) {
         glState16.red = posX;
         glState16.green = posX2;
         glState16.blue = posY;
         glState16.alpha = posZ;
         GL11.glColor4f(posX, posX2, posY, posZ);
      }
   }

   public static void glStateAction2(GlStateManagerSType glStateManagerSType) {
      obtainState(glStateManagerSType).slotCraftingResult.updateState();
   }

   public static int getStateInt9() {
      return glState21.face;
   }

   public static void translate3f3(float posX, float posY, float posZ) {
      GL11.glScalef(posX, posY, posZ);
   }

   public static boolean obtainState(int count) {
      return SlotCraftingResult.checkSlotCraftingResult(glState17[count]);
   }

   public static void applyState8() {
      glState5.updateState2();
   }

   public static boolean obtainState9() {
      return SlotCraftingResult.checkSlotCraftingResult(glState11.slotCraftingResult);
   }

   public static void glStateAction3(int count, int count2) {
      if (glState19.isActive2()) {
         glState7.applyIndex(count, count2);
      } else if (count != glState11.dstFactor || count2 != glState11.srcFactor || count != glState11.count || count2 != glState11.count2) {
         glState11.dstFactor = count;
         glState11.srcFactor = count2;
         glState11.count = count;
         glState11.count2 = count2;
         if (BuildManager.canExecute()) {
            PreEndHandManager.internalStringHandler.applyIndex(count, count2, count, count2);
         }

         GL11.glBlendFunc(count, count2);
      }
   }

   public static void glStateAction(int count, int count2, int count3) {
      GL11.glTexParameteri(count, count2, count3);
   }

   public static void glStateAction4(int count, int count2) {
      GL11.glNewList(count, count2);
      stateFlag = true;
   }

   public static void applyState9() {
      glState22.slotCraftingResult.updateState();
   }

   public static void glStateAction2(int count, FloatBuffer buffer) {
      GL11.glGetFloat(count, buffer);
   }

   public static int getStateInt10() {
      return OpenGlHelper.lightmapTexUnit + stateInt2;
   }

   public static void applyFloat(float factor, float factor2) {
      if (factor != glState9.scaleFactor || factor2 != glState9.scaleFactor1) {
         glState9.scaleFactor = factor;
         glState9.scaleFactor1 = factor2;
         GL11.glPolygonOffset(factor, factor2);
      }
   }

   public static void applyFlag(boolean flag, boolean flag2, boolean flag3, boolean flag4) {
      if (flag != glState4.enabled || flag2 != glState4.enabled3 || flag3 != glState4.enabled2 || flag4 != glState4.enabled4) {
         glState4.enabled = flag;
         glState4.enabled3 = flag2;
         glState4.enabled2 = flag3;
         glState4.enabled4 = flag4;
         GL11.glColorMask(flag, flag2, flag3, flag4);
      }
   }

   public static void glCullFace(int count) {
      if (count != glState6.mode) {
         glState6.mode = count;
         GL11.glCullFace(count);
      }
   }

   public static boolean obtainState2(int count) {
      return SlotCraftingResult.checkSlotCraftingResult(glState20[count].slotCraftingResult);
   }

   public static boolean obtainState10() {
      return SlotCraftingResult.checkSlotCraftingResult(glState3.slotCraftingResult);
   }

   public static int getStateInt11() {
      return glState.indexOffset;
   }

   public static int getStateInt12() {
      return glState11.srcFactor;
   }

   public static void applyState10() {
      if (glState18.isActive2()) {
         glState10.updateState();
      } else {
         glState.slotCraftingResult.updateState2();
      }
   }

   public static void updateState() {
      if (glState18.isActiveAsFlag()) {
         applyGlAlphaState2(glState10);
      }
   }

   public static void popMatrix() {
      GL11.glPopMatrix();
   }

   public static void enableDepth() {
      glState14.slotCraftingResult.updateState2();
   }

   public static void applyState13() {
      glState12.updateState();
   }

   public static void applyState14() {
      if (glState18.isActive2()) {
         glState10.updateState2();
      } else {
         glState.slotCraftingResult.updateState();
      }
   }

   public static void bindTexture() {
      GL11.glBindTexture(3553, glState20[stateInt2].indexOffset);
   }

   public static void loadIdentity() {
      GL11.glLoadIdentity();
   }

   public static void pushAttrib() {
      GL11.glPushAttrib(8256);
   }

   public static void popAttrib() {
      GL11.glPopAttrib();
   }

   public static void applyState19() {
      glState3.slotCraftingResult.updateState();
   }

   public static void applyState20() {
      glState3.slotCraftingResult.updateState2();
   }

   public static void pushMatrix() {
      GL11.glPushMatrix();
   }

   public static void disableBlend() {
      if (glState19.isActive2()) {
         glState7.updateState2();
      } else {
         glState11.slotCraftingResult.updateState();
      }
   }

   public static void disableLighting() {
      glState5.updateState();
   }

   public static void end() {
      GL11.glEnd();
   }

   public static void enableBlend() {
      if (glState19.isActive2()) {
         glState7.updateState();
      } else {
         glState11.slotCraftingResult.updateState2();
      }
   }

   public static void disableCull() {
      glState2.updateState2();
   }

   public static void enableAlpha() {
      glState9.slotCraftingResult.updateState2();
   }

   public static void disableAlpha() {
      glState6.slotCraftingResult.updateState2();
   }

   public static void enableLighting() {
      glState6.slotCraftingResult.updateState();
   }

   public static void enableCull() {
      glState2.updateState();
   }

   public static void applyState31() {
      glState22.slotCraftingResult.updateState2();
   }

   public static void updateState2() {
      if (BuildManager.currentThread()) {
         int count = GL11.glGetInteger(34016);
         int count2 = GL11.glGetInteger(32873);
         int stateInt3 = getStateInt10();
         int stateInt4 = getStateInt4();
         if (stateInt4 > 0 && (count != stateInt3 || count2 != stateInt4)) {
            BuildManager.applyText4("checkTexture: act: " + stateInt3 + ", glAct: " + count + ", tex: " + stateInt4 + ", glTex: " + count2);
         }
      }
   }

   public static int getStateInt13() {
      return stateInt;
   }

   public static void enableTexture2D() {
      glState20[stateInt2].slotCraftingResult.updateState2();
   }

   public static void disableTexture2D() {
      glState20[stateInt2].slotCraftingResult.updateState();
   }

   public static boolean obtainState11() {
      return SlotCraftingResult.checkSlotCraftingResult(glState.slotCraftingResult);
   }

   public static boolean obtainState12() {
      return SlotCraftingResult.checkSlotCraftingResult(glState14.slotCraftingResult);
   }

   public static boolean obtainState13() {
      return SlotCraftingResult.checkSlotCraftingResult(glState9.slotCraftingResult);
   }

   public static void enableClientState(int count) {
      GL11.glEnableClientState(count);
   }

   public static void disableClientState(int count) {
      GL11.glDisableClientState(count);
   }

   public static int getError() {
      return GL11.glGetError();
   }

   public static void glBegin(int count) {
      GL11.glBegin(count);
   }

   public static void enableLight(int count) {
      glState17[count].updateState2();
   }

   public static void bindCurrentTexture(int count) {
      glState20[count].slotCraftingResult.updateState2();
   }

   public static void matrixMode(int count) {
      GL11.glMatrixMode(count);
   }

   static {
      for (int index = 0; index < 8; index++) {
         glState17[index] = new SlotCraftingResult(16384 + index);
      }

      for (int index2 = 0; index2 < glState20.length; index2++) {
         glState20[index2] = new ItemWeightCalculator(null);
      }
   }
}
