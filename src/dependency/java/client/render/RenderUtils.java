package client.render;

import client.ClientGlobals;
import client.module.Module;
import client.setting.ColorSetting;
import client.util.BuildManager;
import client.util.DefaultVertexFormats;
import client.util.MinecraftAccessor;
import client.util.PreEndHandManager;
import client.util.RandomUtil;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderUtils implements MinecraftAccessor {
   public static boolean state = false;

   public static void applyItemStack(ItemStack stack, int count, int count2, boolean flag) {
      if (stack != null) {
         minecraft.getRenderItem().factor += 50.0F;
         applyItemStack2(stack, count, count2, flag);
         minecraft.getRenderItem().factor -= 50.0F;
      }
   }

   public static void processAction(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      processAction((float)posX, (float)posX2, (float)posX3, (float)posX4, (float)posY, (float)posZ);
   }

   public static boolean checkCondition(int count, int count2, double posX, double posX2, double posY, double posZ) {
      return (double)count >= posX && (double)count2 >= posX2 && (double)count < posY && (double)count2 < posZ;
   }

   public static void processAction(Color color) {
      GlStateManager.color4f2((float)color.getRed() / 255.0F, (float)color.getGreen() / 255.0F, (float)color.getBlue() / 255.0F, (float)color.getAlpha() / 255.0F);
   }

   public static void processAction(int count, int count2, int count3, int count4, float posX, float posY, float posZ) {
      processAction(count, count2, count3, count4, posX, posY, posZ, 1.0F);
   }

   public static void updateState() {
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GlStateManager.alphaFunc(516, 0.0F);
      GL11.glEnable(2848);
      GL11.glDisable(2929);
      GL11.glDisable(3553);
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState();
      }

      GlStateManager.disableAlpha();
      GL11.glDepthMask(false);
   }

   public static void processAction(int count, int count2, int count3, int count4, int count5, int count6, int count7, int count8) {
      processAction(count, count2, count3, count4, (float)count5 / 255.0F, (float)count6 / 255.0F, (float)count7 / 255.0F, (float)count8 / 255.0F);
   }

   public static void applyEntityLivingBase(EntityLivingBase livingEntity, int count) {
      float factor = (float)(
         livingEntity.lastTickPosX + (livingEntity.posX - livingEntity.lastTickPosX) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosX()
      );
      float factor2 = (float)(
         livingEntity.lastTickPosY + (livingEntity.posY - livingEntity.lastTickPosY) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosY()
      );
      float factor3 = (float)(
         livingEntity.lastTickPosZ + (livingEntity.posZ - livingEntity.lastTickPosZ) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosZ()
      );
      GlStateManager.pushMatrix();
      GlStateManager.translate(factor, factor2 + (livingEntity.isActive16() ? livingEntity.height / 3.5F : livingEntity.height / 2.0F), factor3);
      GlStateManager.rotate(-minecraft.getRenderManager().scaleFactor, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate3d(-0.098, -0.098, 0.098);
      float factor4 = (float)(26.6 * (double)livingEntity.width / 2.0);
      float factor5 = livingEntity instanceof EntityPlayer
         ? (livingEntity.isActive16() ? 8.0F : 12.0F)
         : (float)(11.98 * (double)(livingEntity.isActive16() ? livingEntity.height / 4.0F : livingEntity.height / 2.0F));
      processAction(count, 1.0F);
      drawRect(factor4, factor5 - 1.0F, factor4 - 4.0F, factor5);
      drawRect(-factor4, factor5 - 1.0F, -factor4 + 4.0F, factor5);
      drawRect(-factor4, factor5, -factor4 + 1.0F, factor5 - 4.0F);
      drawRect(factor4, factor5, factor4 - 1.0F, factor5 - 4.0F);
      drawRect(factor4, -factor5, factor4 - 4.0F, -factor5 + 1.0F);
      drawRect(-factor4, -factor5, -factor4 + 4.0F, -factor5 + 1.0F);
      drawRect(-factor4, -factor5 + 1.0F, -factor4 + 1.0F, -factor5 + 4.0F);
      drawRect(factor4, -factor5 + 1.0F, factor4 - 1.0F, -factor5 + 4.0F);
      GlStateManager.translate3f2(0.0F, 0.0F, 0.0F);
      drawRect(factor4, factor5, factor4 - 4.0F, factor5 + 0.2F);
      drawRect(-factor4, factor5, -factor4 + 4.0F, factor5 + 0.2F);
      drawRect(-factor4 - 0.2F, factor5 + 0.2F, -factor4, factor5 - 4.0F);
      drawRect(factor4 + 0.2F, factor5 + 0.2F, factor4, factor5 - 4.0F);
      drawRect(factor4 + 0.2F, -factor5, factor4 - 4.0F, -factor5 - 0.2F);
      drawRect(-factor4 - 0.2F, -factor5, -factor4 + 4.0F, -factor5 - 0.2F);
      drawRect(-factor4 - 0.2F, -factor5, -factor4, -factor5 + 4.0F);
      drawRect(factor4 + 0.2F, -factor5, factor4, -factor5 + 4.0F);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   public static void applyColor2(float posX, float posX2, float posX3, float posY, float posZ, int count, Color color) {
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      processAction(color);
      GL11.glBegin(9);
      float factor = Math.min(Math.min(Math.abs(posY - posX2) / 2.0F, Math.abs(posX3 - posX) / 2.0F), posZ);
      int count2 = Math.max(1, count - count % 3);

      for (int index = 90; index <= 180; index += count2) {
         GL11.glVertex2d(
            (double)(posX3 - factor) + Math.sin(Math.toRadians((double)index)) * (double)factor,
            (double)(posX2 + factor) + Math.cos(Math.toRadians((double)index)) * (double)factor
         );
      }

      GL11.glVertex2d((double)posX, (double)posX2);
      GL11.glVertex2d((double)posX, (double)posY);
      GL11.glVertex2d((double)posX3, (double)posY);
      GL11.glEnd();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
   }

   public static void processFloat(float posX, float posX2, float posX3, float posY, float posZ, int count, int count2, int count3) {
      GL11.glPushMatrix();
      boolean flag = GlStateManager.obtainState9();
      boolean flag2 = GlStateManager.obtainState8();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      setColor(count3);
      GL11.glLineWidth((float)count2);
      GL11.glBegin(3);
      float factor = Math.min(Math.min(Math.abs(posY - posX2) / 2.0F, Math.abs(posX3 - posX) / 2.0F), posZ);
      float factor2 = posX + factor;
      float factor3 = posX2 + factor;
      float factor4 = posX3 - factor;
      float factor5 = posY - factor;
      int count4 = Math.max(1, count - count % 3);

      for (int index = 0; index <= 90; index += count4) {
         GL11.glVertex2d(
            (double)factor4 + Math.sin(Math.toRadians((double)index)) * (double)factor, (double)factor5 + Math.cos(Math.toRadians((double)index)) * (double)factor
         );
      }

      for (int index2 = 90; index2 <= 180; index2 += count4) {
         GL11.glVertex2d(
            (double)factor4 + Math.sin(Math.toRadians((double)index2)) * (double)factor, (double)factor3 + Math.cos(Math.toRadians((double)index2)) * (double)factor
         );
      }

      for (int index3 = 180; index3 <= 270; index3 += count4) {
         GL11.glVertex2d(
            (double)factor2 + Math.sin(Math.toRadians((double)index3)) * (double)factor, (double)factor3 + Math.cos(Math.toRadians((double)index3)) * (double)factor
         );
      }

      for (int index4 = 270; index4 <= 360; index4 += count4) {
         GL11.glVertex2d(
            (double)factor2 + Math.sin(Math.toRadians((double)index4)) * (double)factor, (double)factor5 + Math.cos(Math.toRadians((double)index4)) * (double)factor
         );
      }

      GL11.glVertex2d((double)factor4 + Math.sin(Math.toRadians(0.0)) * (double)factor, (double)factor5 + Math.cos(Math.toRadians(0.0)) * (double)factor);
      GL11.glEnd();
      if (flag2) {
         GlStateManager.disableTexture2D();
      }

      if (!flag) {
         GlStateManager.enableBlend();
      }

      GL11.glPopMatrix();
   }

   public static void applyEntity(Entity entity, float posX, float posY, float posZ, double offset, double offset2) {
      double offset3 = offset * 1.5;
      float factor = MathHelper.sqrt_float(entity.getScaleFactor12()) + 45.0F;
      float factor2;
      if (factor < 0.0F) {
         factor2 = 0.0F + (360.0F - Math.abs(factor));
      } else {
         factor2 = factor;
      }

      float factor3 = (float)((double)(factor2 * -1.0F) * (Math.PI / 180.0));
      float factor4 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 135.0F;
      float factor5;
      if (factor4 < 0.0F) {
         factor5 = 0.0F + (360.0F - Math.abs(factor4));
      } else {
         factor5 = factor4;
      }

      float factor6 = (float)((double)(factor5 * -1.0F) * (Math.PI / 180.0));
      float factor7 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 225.0F;
      float factor8;
      if (factor7 < 0.0F) {
         factor8 = 0.0F + (360.0F - Math.abs(factor7));
      } else {
         factor8 = factor7;
      }

      float factor9 = (float)((double)(factor8 * -1.0F) * (Math.PI / 180.0));
      float factor10 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 315.0F;
      float factor11;
      if (factor10 < 0.0F) {
         factor11 = 0.0F + (360.0F - Math.abs(factor10));
      } else {
         factor11 = factor10;
      }

      float factor12 = (float)((double)(factor11 * -1.0F) * (Math.PI / 180.0));
      double offset4 = Math.sin((double)factor3) * offset3 + (double)posX;
      double offset5 = Math.cos((double)factor3) * offset3 + (double)posZ;
      double offset6 = Math.sin((double)factor6) * offset3 + (double)posX;
      double offset7 = Math.cos((double)factor6) * offset3 + (double)posZ;
      double offset8 = Math.sin((double)factor9) * offset3 + (double)posX;
      double offset9 = Math.cos((double)factor9) * offset3 + (double)posZ;
      double offset10 = Math.sin((double)factor12) * offset3 + (double)posX;
      double offset11 = Math.cos((double)factor12) * offset3 + (double)posZ;
      double offset12 = (double)posY + offset2;
      GL11.glBegin(1);
      GL11.glVertex3d(offset4, (double)posY, offset5);
      GL11.glVertex3d(offset6, (double)posY, offset7);
      GL11.glVertex3d(offset6, (double)posY, offset7);
      GL11.glVertex3d(offset8, (double)posY, offset9);
      GL11.glVertex3d(offset8, (double)posY, offset9);
      GL11.glVertex3d(offset10, (double)posY, offset11);
      GL11.glVertex3d(offset10, (double)posY, offset11);
      GL11.glVertex3d(offset4, (double)posY, offset5);
      GL11.glVertex3d(offset4, offset12, offset5);
      GL11.glVertex3d(offset6, offset12, offset7);
      GL11.glVertex3d(offset6, offset12, offset7);
      GL11.glVertex3d(offset8, offset12, offset9);
      GL11.glVertex3d(offset8, offset12, offset9);
      GL11.glVertex3d(offset10, offset12, offset11);
      GL11.glVertex3d(offset10, offset12, offset11);
      GL11.glVertex3d(offset4, offset12, offset5);
      GL11.glVertex3d(offset4, (double)posY, offset5);
      GL11.glVertex3d(offset4, offset12, offset5);
      GL11.glVertex3d(offset6, (double)posY, offset7);
      GL11.glVertex3d(offset6, offset12, offset7);
      GL11.glVertex3d(offset8, (double)posY, offset9);
      GL11.glVertex3d(offset8, offset12, offset9);
      GL11.glVertex3d(offset10, (double)posY, offset11);
      GL11.glVertex3d(offset10, offset12, offset11);
      GL11.glEnd();
   }

   public static void processDouble2(double offset, int count, int count2, float posX, float posY, float posZ, boolean flag) {
      for (int index = 0; index <= count; index++) {
         double offset2 = Math.PI * (-0.5 + (double)(index - 1) / (double)count);
         double offset3 = Math.sin(offset2);
         double offset4 = Math.cos(offset2);
         double offset5 = Math.PI * (-0.5 + (double)index / (double)count);
         double offset6 = Math.sin(offset5);
         double offset7 = Math.cos(offset5);
         GL11.glBegin(flag ? 3 : 8);

         for (int index2 = 0; index2 <= count2; index2++) {
            double offset8 = (Math.PI * 2) * (double)(index2 - 1) / (double)count2;
            double offset9 = Math.cos(offset8);
            double offset10 = Math.sin(offset8);
            GL11.glNormal3d((double)posX + offset9 * offset4, (double)posY + offset10 * offset4, (double)posZ + offset3);
            GL11.glVertex3d((double)posX + offset * offset9 * offset4, (double)posY + offset * offset10 * offset4, (double)posZ + offset * offset3);
            GL11.glNormal3d((double)posX + offset9 * offset7, (double)posY + offset10 * offset7, (double)posZ + offset6);
            GL11.glVertex3d((double)posX + offset * offset9 * offset7, (double)posY + offset * offset10 * offset7, (double)posZ + offset * offset6);
         }

         GL11.glEnd();
      }
   }

   public static void processAction(int count, int count2, int count3, int count4, int count5, int count6, int count7) {
      processAction(count, count2, count3, count4, (float)count5 / 255.0F, (float)count6 / 255.0F, (float)count7 / 255.0F, 1.0F);
   }

   public static void processAction() {
      GlStateManager.applyState10();
      GlStateManager.applyState13();
      GlStateManager.applyState();
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.applyState3();
   }

   public static void applyEntity2(Entity entity, float posX, float posY, float posZ, double offset, double offset2) {
      float factor = (float) (Math.PI / 180.0);
      double offset3 = offset * 1.5;
      float factor2 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 45.0F;
      float factor3;
      if (factor2 < 0.0F) {
         factor3 = 0.0F + (360.0F - Math.abs(factor2));
      } else {
         factor3 = factor2;
      }

      float factor4 = factor3 * -1.0F * factor;
      float factor5 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 135.0F;
      float factor6;
      if (factor5 < 0.0F) {
         factor6 = 0.0F + (360.0F - Math.abs(factor5));
      } else {
         factor6 = factor5;
      }

      float factor7 = factor6 * -1.0F * factor;
      float factor8 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 225.0F;
      float factor9;
      if (factor8 < 0.0F) {
         factor9 = 0.0F + (360.0F - Math.abs(factor8));
      } else {
         factor9 = factor8;
      }

      float factor10 = factor9 * -1.0F * factor;
      float factor11 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 315.0F;
      float factor12;
      if (factor11 < 0.0F) {
         factor12 = 0.0F + (360.0F - Math.abs(factor11));
      } else {
         factor12 = factor11;
      }

      float factor13 = factor12 * -1.0F * factor;
      float factor14 = (float)(Math.sin((double)factor4) * offset3 + (double)posX);
      float factor15 = (float)(Math.cos((double)factor4) * offset3 + (double)posZ);
      float factor16 = (float)(Math.sin((double)factor7) * offset3 + (double)posX);
      float factor17 = (float)(Math.cos((double)factor7) * offset3 + (double)posZ);
      float factor18 = (float)(Math.sin((double)factor10) * offset3 + (double)posX);
      float factor19 = (float)(Math.cos((double)factor10) * offset3 + (double)posZ);
      float factor20 = (float)(Math.sin((double)factor13) * offset3 + (double)posX);
      float factor21 = (float)(Math.cos((double)factor13) * offset3 + (double)posZ);
      float factor22 = (float)((double)posY + offset2);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry1);
      worldRenderer.getWorldRenderer((double)factor14, (double)posY, (double)factor15).updateState();
      worldRenderer.getWorldRenderer((double)factor14, (double)factor22, (double)factor15).updateState();
      worldRenderer.getWorldRenderer((double)factor16, (double)factor22, (double)factor17).updateState();
      worldRenderer.getWorldRenderer((double)factor16, (double)posY, (double)factor17).updateState();
      worldRenderer.getWorldRenderer((double)factor14, (double)posY, (double)factor15).updateState();
      worldRenderer.getWorldRenderer((double)factor20, (double)posY, (double)factor21).updateState();
      worldRenderer.getWorldRenderer((double)factor18, (double)posY, (double)factor19).updateState();
      worldRenderer.getWorldRenderer((double)factor18, (double)factor22, (double)factor19).updateState();
      worldRenderer.getWorldRenderer((double)factor20, (double)factor22, (double)factor21).updateState();
      worldRenderer.getWorldRenderer((double)factor20, (double)posY, (double)factor21).updateState();
      worldRenderer.getWorldRenderer((double)factor20, (double)factor22, (double)factor21).updateState();
      worldRenderer.getWorldRenderer((double)factor18, (double)factor22, (double)factor19).updateState();
      worldRenderer.getWorldRenderer((double)factor16, (double)factor22, (double)factor17).updateState();
      worldRenderer.getWorldRenderer((double)factor16, (double)posY, (double)factor17).updateState();
      worldRenderer.getWorldRenderer((double)factor18, (double)posY, (double)factor19).updateState();
      worldRenderer.getWorldRenderer((double)factor20, (double)posY, (double)factor21).updateState();
      worldRenderer.getWorldRenderer((double)factor20, (double)factor22, (double)factor21).updateState();
      worldRenderer.getWorldRenderer((double)factor14, (double)factor22, (double)factor15).updateState();
      worldRenderer.getWorldRenderer((double)factor14, (double)posY, (double)factor15).updateState();
      worldRenderer.updateState();
      tessellator.updateState();
   }

   public static void processAction(AxisAlignedBB boundingBox) {
      processAction5(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
   }

   public static void processAction(double posX, double posX2, double posY, double posZ) {
      double offset = posX;
      double offset2 = posX2;
      double offset3 = posY;
      double offset4 = posZ;
      if (posX < posY) {
         offset = posY;
         offset3 = posX;
      }

      if (posX2 < posZ) {
         offset2 = posZ;
         offset4 = posX2;
      }

      GL11.glVertex2d(offset, offset4);
      GL11.glVertex2d(offset3, offset4);
      GL11.glVertex2d(offset3, offset2);
      GL11.glVertex2d(offset, offset2);
   }

   public static void processAction(float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      worldRenderer.updateAuxiliaryLoggerEntry(3, DefaultVertexFormats.auxiliaryLoggerEntry1);
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posX3).updateState();
      worldRenderer.updateState();
      tessellator.updateState();
   }

   public static void processAction(float posX, float posX2, float posX3, float posY, float posZ, int count, Color color) {
      processFloat9(posX, posX2, posX3, posY, posZ, count, color.getRGB());
   }

   public static void processAction(double posX, double posX2, double posY, double posZ, float factor, Color color) {
      processDouble8(
         posX,
         posX2,
         posY,
         posZ,
         factor,
         (float)color.getRed() / 255.0F,
         (float)color.getGreen() / 255.0F,
         (float)color.getBlue() / 255.0F,
         (float)color.getAlpha() / 255.0F
      );
   }

   public static void processAction(float posX, float posX2, float posY, float posZ, int count, int count2, int count3, int count4) {
      processFloat13(posX, posX2, posY, posZ, (float)count / 255.0F, (float)count2 / 255.0F, (float)count3 / 255.0F, (float)count4 / 255.0F);
   }

   public static void processFloat4(float posX, float posX2, float posY, float posZ, double offset) {
      int indexOffset = minecraft.getFramebuffer().indexOffset1;
      float factor = (float)offset;
      GL11.glScissor((int)(posX * factor), (int)(((float)indexOffset - posZ) * factor), (int)((posY - posX) * factor), (int)((posZ - posX2) * factor));
   }

   public static void processDouble4(double posX, double posX2, double posY, double posZ, int count) {
      double offset = posX;
      double offset2 = posX2;
      double offset3 = posY;
      double offset4 = posZ;
      if (posX < posY) {
         offset = posY;
         offset3 = posX;
      }

      if (posX2 < posZ) {
         offset2 = posZ;
         offset4 = posX2;
      }

      float factor = (float)(count >> 24 & 0xFF) / 255.0F;
      float factor2 = (float)(count >> 16 & 0xFF) / 255.0F;
      float factor3 = (float)(count >> 8 & 0xFF) / 255.0F;
      float factor4 = (float)(count & 0xFF) / 255.0F;
      GL11.glEnable(3042);
      GL11.glDisable(3553);
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState();
      }

      GL11.glBlendFunc(770, 771);
      GL11.glEnable(2848);
      GlStateManager.color4f2(factor2, factor3, factor4, factor);
      GL11.glBegin(7);
      GL11.glVertex2d(offset, offset4);
      GL11.glVertex2d(offset3, offset4);
      GL11.glVertex2d(offset3, offset2);
      GL11.glVertex2d(offset, offset2);
      GL11.glEnd();
      GL11.glEnable(3553);
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState2();
      }

      GL11.glDisable(3042);
      GL11.glDisable(2848);
   }

   public static void processAction(int count, int count2, int count3, int count4, float posX, float posX2, float posY, float posZ) {
      int count5 = count;
      int count6 = count2;
      int count7 = count3;
      int count8 = count4;
      if (count < count3) {
         count5 = count3;
         count7 = count;
      }

      if (count2 < count4) {
         count6 = count4;
         count8 = count2;
      }

      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color4f2(posX, posX2, posY, posZ);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry1);
      worldRenderer.getWorldRenderer((double)count5, (double)count8, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)count7, (double)count8, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)count7, (double)count6, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)count5, (double)count6, 0.0).updateState();
      tessellator.updateState();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
   }

   public static void applyColor5(float posX, float posX2, float posX3, float posY, float posZ, int count, Color color) {
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      processAction(color);
      GL11.glBegin(9);
      float factor = Math.min(Math.min(Math.abs(posY - posX2) / 2.0F, Math.abs(posX3 - posX) / 2.0F), posZ);
      int count2 = Math.max(1, count - count % 3);

      for (int index = 0; index <= 90; index += count2) {
         GL11.glVertex2d(
            (double)(posX3 - factor) + Math.sin(Math.toRadians((double)index)) * (double)factor,
            (double)(posY - factor) + Math.cos(Math.toRadians((double)index)) * (double)factor
         );
      }

      GL11.glVertex2d((double)posX3, (double)posX2);
      GL11.glVertex2d((double)posX, (double)posY);
      GL11.glEnd();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
   }

   public static void processAction(int count, float factor) {
      GlStateManager.color4f2((float)(count >> 16 & 0xFF) / 255.0F, (float)(count >> 8 & 0xFF) / 255.0F, (float)(count & 0xFF) / 255.0F, factor);
   }

   public static void processAction(float posX, float posY, float posZ, int count) {
      GL11.glPushMatrix();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      setColor(count);
      GL11.glBegin(4);
      float factor = posZ / 2.0F;
      float factor2 = posX + factor;
      float factor3 = posX - factor;
      float factor4 = posY + factor;
      float factor5 = posY - factor;
      GL11.glVertex2f(factor2, factor5);
      GL11.glVertex2f(factor3, factor5);
      GL11.glVertex2f(posX, factor4);
      GL11.glEnd();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GL11.glPopMatrix();
   }

   public static void updateEntityItem(EntityItem entityItem, float posX, float posX2, float posY, float posZ) {
      float factor = (float)(
         entityItem.lastTickPosX + (entityItem.posX - entityItem.lastTickPosX) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosX()
      );
      float factor2 = (float)(
         entityItem.lastTickPosY + (entityItem.posY - entityItem.lastTickPosY) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosY()
      );
      float factor3 = (float)(
         entityItem.lastTickPosZ + (entityItem.posZ - entityItem.lastTickPosZ) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosZ()
      );
      GlStateManager.color4f2(posX, posX2, posY, posZ);
      processAction3(factor - 0.25F, factor2 + 0.05F, factor3 - 0.25F, factor + 0.25F, factor2 + 0.65F, factor3 + 0.253F);
   }

   public static void processAction(int count, int count2, int count3, int count4, int count5) {
      float factor = (float)(count5 >> 24 & 0xFF) / 255.0F;
      float factor2 = (float)(count5 >> 16 & 0xFF) / 255.0F;
      float factor3 = (float)(count5 >> 8 & 0xFF) / 255.0F;
      float factor4 = (float)(count5 & 0xFF) / 255.0F;
      processAction(count, count2, count3, count4, factor2, factor3, factor4, factor);
   }

   public static void applyEntityLivingBase2(EntityLivingBase livingEntity, int count, String text) {
      float factor = (float)(
         livingEntity.lastTickPosX + (livingEntity.posX - livingEntity.lastTickPosX) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosX()
      );
      float factor2 = (float)(
         livingEntity.lastTickPosY + (livingEntity.posY - livingEntity.lastTickPosY) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosY()
      );
      float factor3 = (float)(
         livingEntity.lastTickPosZ + (livingEntity.posZ - livingEntity.lastTickPosZ) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosZ()
      );
      setColor(count);
      applyString(factor, factor2, factor3, text);
   }

   public static void drawBoundingBox(AxisAlignedBB boundingBox) {
      processAction4(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
   }

   private static void applyEntity3(Entity entity, float posX, float posY, float posZ, double offset, double offset2) {
      double offset3 = offset * 1.5;
      float factor = MathHelper.sqrt_float(entity.getScaleFactor12()) + 45.0F;
      float factor2;
      if (factor < 0.0F) {
         factor2 = 0.0F + (360.0F - Math.abs(factor));
      } else {
         factor2 = factor;
      }

      float factor3 = (float)((double)(factor2 * -1.0F) * (Math.PI / 180.0));
      float factor4 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 135.0F;
      float factor5;
      if (factor4 < 0.0F) {
         factor5 = 0.0F + (360.0F - Math.abs(factor4));
      } else {
         factor5 = factor4;
      }

      float factor6 = (float)((double)(factor5 * -1.0F) * (Math.PI / 180.0));
      float factor7 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 225.0F;
      float factor8;
      if (factor7 < 0.0F) {
         factor8 = 0.0F + (360.0F - Math.abs(factor7));
      } else {
         factor8 = factor7;
      }

      float factor9 = (float)((double)(factor8 * -1.0F) * (Math.PI / 180.0));
      float factor10 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 315.0F;
      float factor11;
      if (factor10 < 0.0F) {
         factor11 = 0.0F + (360.0F - Math.abs(factor10));
      } else {
         factor11 = factor10;
      }

      float factor12 = (float)((double)(factor11 * -1.0F) * (Math.PI / 180.0));
      double offset4 = Math.sin((double)factor3) * offset3 + (double)posX;
      double offset5 = Math.cos((double)factor3) * offset3 + (double)posZ;
      double offset6 = Math.sin((double)factor6) * offset3 + (double)posX;
      double offset7 = Math.cos((double)factor6) * offset3 + (double)posZ;
      double offset8 = Math.sin((double)factor9) * offset3 + (double)posX;
      double offset9 = Math.cos((double)factor9) * offset3 + (double)posZ;
      double offset10 = Math.sin((double)factor12) * offset3 + (double)posX;
      double offset11 = Math.cos((double)factor12) * offset3 + (double)posZ;
      double offset12 = (double)posY + offset2;
      GL11.glBegin(7);
      GL11.glVertex3d(offset4, (double)posY, offset5);
      GL11.glVertex3d(offset4, offset12, offset5);
      GL11.glVertex3d(offset6, offset12, offset7);
      GL11.glVertex3d(offset6, (double)posY, offset7);
      GL11.glVertex3d(offset6, (double)posY, offset7);
      GL11.glVertex3d(offset6, offset12, offset7);
      GL11.glVertex3d(offset8, offset12, offset9);
      GL11.glVertex3d(offset8, (double)posY, offset9);
      GL11.glVertex3d(offset8, (double)posY, offset9);
      GL11.glVertex3d(offset8, offset12, offset9);
      GL11.glVertex3d(offset10, offset12, offset11);
      GL11.glVertex3d(offset10, (double)posY, offset11);
      GL11.glVertex3d(offset10, (double)posY, offset11);
      GL11.glVertex3d(offset10, offset12, offset11);
      GL11.glVertex3d(offset4, offset12, offset5);
      GL11.glVertex3d(offset4, (double)posY, offset5);
      GL11.glVertex3d(offset4, (double)posY, offset5);
      GL11.glVertex3d(offset6, (double)posY, offset7);
      GL11.glVertex3d(offset8, (double)posY, offset9);
      GL11.glVertex3d(offset10, (double)posY, offset11);
      GL11.glVertex3d(offset4, offset12, offset5);
      GL11.glVertex3d(offset6, offset12, offset7);
      GL11.glVertex3d(offset8, offset12, offset9);
      GL11.glVertex3d(offset10, offset12, offset11);
      GL11.glEnd();
   }

   public static void processAction2(float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      Tessellator tessellator = Tessellator.getTessellator();
      tessellator.getWorldRenderer().updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry1);
      processAction4(posX, posX2, posX3, posX4, posY, posZ);
      tessellator.updateState();
   }

   public static void processAction2(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      GL11.glBegin(7);
      GL11.glVertex3d(posX, posX2, posX3);
      GL11.glVertex3d(posX, posX2, posZ);
      GL11.glVertex3d(posX, posY, posZ);
      GL11.glVertex3d(posX, posY, posX3);
      GL11.glVertex3d(posX, posX2, posZ);
      GL11.glVertex3d(posX4, posX2, posZ);
      GL11.glVertex3d(posX4, posY, posZ);
      GL11.glVertex3d(posX, posY, posZ);
      GL11.glVertex3d(posX4, posX2, posZ);
      GL11.glVertex3d(posX4, posX2, posX3);
      GL11.glVertex3d(posX4, posY, posX3);
      GL11.glVertex3d(posX4, posY, posZ);
      GL11.glVertex3d(posX4, posX2, posX3);
      GL11.glVertex3d(posX, posX2, posX3);
      GL11.glVertex3d(posX, posY, posX3);
      GL11.glVertex3d(posX4, posY, posX3);
      GL11.glVertex3d(posX, posX2, posX3);
      GL11.glVertex3d(posX, posX2, posZ);
      GL11.glVertex3d(posX4, posX2, posZ);
      GL11.glVertex3d(posX4, posX2, posX3);
      GL11.glVertex3d(posX, posY, posX3);
      GL11.glVertex3d(posX, posY, posZ);
      GL11.glVertex3d(posX4, posY, posZ);
      GL11.glVertex3d(posX4, posY, posX3);
      GL11.glEnd();
   }

   public static void processAction(int count, int count2, int count3, int count4) {
      GlStateManager.color4f2((float)count / 255.0F, (float)count2 / 255.0F, (float)count3 / 255.0F, (float)count4 / 255.0F);
   }

   public static void applyBlockPos(BlockPos blockPos, int count, float factor, float factor2) {
      setColor(count);
      float factor3 = (float)((double)blockPos.getX() - minecraft.getRenderManager().getRenderPosX());
      float factor4 = (float)((double)blockPos.getY() - minecraft.getRenderManager().getRenderPosY());
      float factor5 = (float)((double)blockPos.getZ() - minecraft.getRenderManager().getRenderPosZ());
      Block block = minecraft.theWorld.getBlockState(blockPos).getBlock();
      processAction4(
         (double)factor3,
         (double)factor4,
         (double)factor5,
         (double)factor3 + block.getCoordOffset9(),
         (double)factor4 + block.getCoordOffset6(),
         (double)factor5 + block.getCoordOffset15()
      );
      if (factor2 > 0.0F) {
         GL11.glLineWidth(factor2);
         processAction(count, factor);
         processAction(
            (double)factor3,
            (double)factor4,
            (double)factor5,
            (double)factor3 + block.getCoordOffset9(),
            (double)factor4 + block.getCoordOffset6(),
            (double)factor5 + block.getCoordOffset15()
         );
      }

      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void applyEntityLivingBase3(EntityLivingBase livingEntity, ColorSetting setting, float factor, int count, boolean flag) {
      float factor2 = factor;
      if (minecraft.thePlayer.getDistanceSqToEntity(livingEntity) > 1.0F) {
         double offset = (double)(1.0F - minecraft.thePlayer.getDistanceSqToEntity(livingEntity) / 20.0F);
         if (offset < 0.3) {
            offset = 0.3;
         }

         factor2 = (float)((double)factor * offset);
      }

      double offset2;
      double offset3;
      double offset4;
      if (livingEntity instanceof EntityPlayerSP) {
         if (flag) {
            offset4 = minecraft.thePlayer.getPositionVec11().xCoord
               + (minecraft.thePlayer.getPositionVec6().xCoord - minecraft.thePlayer.getPositionVec11().xCoord) * (double)minecraft.getTimer().scaleFactor6;
            offset2 = minecraft.thePlayer.getPositionVec11().yCoord
               + (minecraft.thePlayer.getPositionVec6().yCoord - minecraft.thePlayer.getPositionVec11().yCoord) * (double)minecraft.getTimer().scaleFactor6;
            offset3 = minecraft.thePlayer.getPositionVec11().zCoord
               + (minecraft.thePlayer.getPositionVec6().zCoord - minecraft.thePlayer.getPositionVec11().zCoord) * (double)minecraft.getTimer().scaleFactor6;
         } else {
            offset4 = minecraft.thePlayer.getPositionVec8().xCoord;
            offset2 = minecraft.thePlayer.getPositionVec8().yCoord;
            offset3 = minecraft.thePlayer.getPositionVec8().zCoord;
         }
      } else if (flag && livingEntity.positionVec3 != null && livingEntity.positionVec != null) {
         offset4 = livingEntity.positionVec.xCoord + (livingEntity.positionVec3.xCoord - livingEntity.positionVec.xCoord) * (double)minecraft.getTimer().scaleFactor6;
         offset2 = livingEntity.positionVec.yCoord + (livingEntity.positionVec3.yCoord - livingEntity.positionVec.yCoord) * (double)minecraft.getTimer().scaleFactor6;
         offset3 = livingEntity.positionVec.zCoord + (livingEntity.positionVec3.zCoord - livingEntity.positionVec.zCoord) * (double)minecraft.getTimer().scaleFactor6;
      } else {
         offset4 = livingEntity.positionVec5.xCoord + (livingEntity.positionVec4.xCoord - livingEntity.positionVec5.xCoord) * (double)minecraft.getTimer().scaleFactor6;
         offset2 = livingEntity.positionVec5.yCoord + (livingEntity.positionVec4.yCoord - livingEntity.positionVec5.yCoord) * (double)minecraft.getTimer().scaleFactor6;
         offset3 = livingEntity.positionVec5.zCoord + (livingEntity.positionVec4.zCoord - livingEntity.positionVec5.zCoord) * (double)minecraft.getTimer().scaleFactor6;
      }

      float factor3 = (float)(offset4 - minecraft.getRenderManager().getRenderPosX());
      float factor4 = (float)(offset2 - minecraft.getRenderManager().getRenderPosY());
      float factor5 = (float)(offset3 - minecraft.getRenderManager().getRenderPosZ());
      int intItem = setting.getInt12(0);
      setColor(intItem);
      float factor6 = ((Module)ClientGlobals.moduleManager.child).isToggled() && livingEntity instanceof EntityPlayer ? livingEntity.height / 1.7F : livingEntity.height;
      applyEntity6(livingEntity, factor3, factor4, factor5, (double)(livingEntity.width - 0.2F), (double)(factor6 + 0.1F));
      if (factor2 > 0.0F) {
         GL11.glLineWidth(factor2);
         drawGradientRect(intItem, count);
         applyEntity2(livingEntity, factor3, factor4, factor5, (double)(livingEntity.width - 0.2F), (double)(factor6 + 0.1F));
      }
   }

   public static void processAction(float posX, float posX2, float posX3, float posY, float posZ, int count, int count2, Color color) {
      processFloat(posX, posX2, posX3, posY, posZ, count, count2, color.getRGB());
   }

   public static void drawOutlinedBoundingBox(AxisAlignedBB boundingBox) {
      processAction2(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
   }

   public static void drawRect(float posX, float posX2, float posY, float posZ) {
      GL11.glBegin(7);
      GL11.glVertex2d((double)posY, (double)posX2);
      GL11.glVertex2d((double)posX, (double)posX2);
      GL11.glVertex2d((double)posX, (double)posZ);
      GL11.glVertex2d((double)posY, (double)posZ);
      GL11.glEnd();
   }

   public static int processAction(Color color, double offset, int count, int count2, int count3, int count4, int count5, int count6) {
      int count7;
      if (checkCondition((float)count3, (float)count4, (float)count5, (float)count6, count, count2)) {
         float[] hsbValues = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
         float factor = (float)((double)hsbValues[2] + offset);
         if ((double)hsbValues[2] + offset > 1.0) {
            factor = 1.0F;
         } else if ((double)hsbValues[2] + offset < 0.0) {
            factor = 0.0F;
         }

         count7 = Color.HSBtoRGB(hsbValues[0], hsbValues[1], factor);
      } else {
         count7 = color.getRGB();
      }

      return count7;
   }

   public static void processAction3(float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry1);
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posX3).updateState();
      worldRenderer.updateState();
      tessellator.updateState();
   }

   public static void processAction3(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      processAction4((float)posX, (float)posX2, (float)posX3, (float)posX4, (float)posY, (float)posZ);
   }

   public static void processFloat9(float posX, float posX2, float posX3, float posY, float posZ, int count, int count2) {
      GL11.glPushMatrix();
      boolean flag = GlStateManager.obtainState9();
      boolean flag2 = GlStateManager.obtainState8();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      setColor(count2);
      GL11.glBegin(9);
      float factor = Math.min(Math.min(Math.abs(posY - posX2) / 2.0F, Math.abs(posX3 - posX) / 2.0F), posZ);
      float factor2 = posX + factor;
      float factor3 = posX2 + factor;
      float factor4 = posX3 - factor;
      float factor5 = posY - factor;
      int count3 = Math.max(1, count - count % 3);

      for (int index = 0; index <= 90; index += count3) {
         GL11.glVertex2d(
            (double)factor4 + Math.sin(Math.toRadians((double)index)) * (double)factor, (double)factor5 + Math.cos(Math.toRadians((double)index)) * (double)factor
         );
      }

      for (int index2 = 90; index2 <= 180; index2 += count3) {
         GL11.glVertex2d(
            (double)factor4 + Math.sin(Math.toRadians((double)index2)) * (double)factor, (double)factor3 + Math.cos(Math.toRadians((double)index2)) * (double)factor
         );
      }

      for (int index3 = 180; index3 <= 270; index3 += count3) {
         GL11.glVertex2d(
            (double)factor2 + Math.sin(Math.toRadians((double)index3)) * (double)factor, (double)factor3 + Math.cos(Math.toRadians((double)index3)) * (double)factor
         );
      }

      for (int index4 = 270; index4 <= 360; index4 += count3) {
         GL11.glVertex2d(
            (double)factor2 + Math.sin(Math.toRadians((double)index4)) * (double)factor, (double)factor5 + Math.cos(Math.toRadians((double)index4)) * (double)factor
         );
      }

      GL11.glEnd();
      if (flag2) {
         GlStateManager.disableTexture2D();
      }

      if (!flag) {
         GlStateManager.enableBlend();
      }

      GL11.glPopMatrix();
   }

   public static void processAction(
      float posX, float posX2, float posX3, float posY, float posZ, int count, boolean flag, boolean flag2, boolean flag3, boolean flag4, Color color
   ) {
      processFloat14(posX, posX2, posX3, posY, posZ, count, flag, flag2, flag3, flag4, color.getRGB());
   }

   public static void applyColor8(double posX, double posX2, double posX3, double posY, double posZ, Color color) {
      GL11.glPushMatrix();
      GL11.glDisable(3553);
      GL11.glEnable(2848);
      GL11.glEnable(3042);
      GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
      processAction(color);
      GL11.glBegin(9);
      double offset = posX + posX3 - posZ;
      double offset2 = posX2 + posY - posZ;

      for (int byteValue = 0; byteValue <= 90; byteValue += 30) {
         GL11.glVertex2d(offset + Math.sin((double)byteValue * Math.PI / 180.0) * posZ, offset2 + Math.cos((double)byteValue * Math.PI / 180.0) * posZ);
      }

      double offset3 = posX + posX3 - posZ;
      double offset4 = posX2 + posZ;

      for (int byteValue2 = 90; byteValue2 <= 180; byteValue2 += 30) {
         GL11.glVertex2d(offset3 + Math.sin((double)byteValue2 * Math.PI / 180.0) * posZ, offset4 + Math.cos((double)byteValue2 * Math.PI / 180.0) * posZ);
      }

      double offset5 = posX + posZ;
      double offset6 = posX2 + posZ;

      for (int shortValue = 180; shortValue <= 270; shortValue += 30) {
         GL11.glVertex2d(offset5 + Math.sin((double)shortValue * Math.PI / 180.0) * posZ, offset6 + Math.cos((double)shortValue * Math.PI / 180.0) * posZ);
      }

      double offset7 = posX + posZ;
      double offset8 = posX2 + posY - posZ;

      for (int shortValue2 = 270; shortValue2 <= 360; shortValue2 += 30) {
         GL11.glVertex2d(offset7 + Math.sin((double)shortValue2 * Math.PI / 180.0) * posZ, offset8 + Math.cos((double)shortValue2 * Math.PI / 180.0) * posZ);
      }

      GL11.glEnd();
      GL11.glDisable(3042);
      GL11.glDisable(2848);
      GL11.glEnable(3553);
      GL11.glPopMatrix();
   }

   public static void processAction2(float posX, float posX2, float posX3, float posY, float posZ, int count, Color color) {
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      processAction(color);
      GL11.glBegin(9);
      float factor = Math.min(Math.min(Math.abs(posY - posX2) / 2.0F, Math.abs(posX3 - posX) / 2.0F), posZ);
      int count2 = Math.max(1, count - count % 3);

      for (int index = 90; index <= 180; index += count2) {
         GL11.glVertex2d(
            (double)(posX3 - factor) + Math.sin(Math.toRadians((double)index)) * (double)factor,
            (double)(posX2 + factor) + Math.cos(Math.toRadians((double)index)) * (double)factor
         );
      }

      for (int index2 = 180; index2 <= 270; index2 += count2) {
         GL11.glVertex2d(
            (double)(posX + factor) + Math.sin(Math.toRadians((double)index2)) * (double)factor,
            (double)(posX2 + factor) + Math.cos(Math.toRadians((double)index2)) * (double)factor
         );
      }

      GL11.glVertex2d((double)posX, (double)posY);
      GL11.glVertex2d((double)posX3, (double)posY);
      GL11.glEnd();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
   }

   public static void processAction4(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      processAction2((float)posX, (float)posX2, (float)posX3, (float)posX4, (float)posY, (float)posZ);
   }

   public static void processAction(float posX, float posX2, float posY, float posZ, int count, int count2, int count3) {
      processFloat13(posX, posX2, posY, posZ, (float)count / 255.0F, (float)count2 / 255.0F, (float)count3 / 255.0F, 1.0F);
   }

   public static void drawGradientRect(int count, int count2) {
      GlStateManager.color4f2((float)(count >> 16 & 0xFF) / 255.0F, (float)(count >> 8 & 0xFF) / 255.0F, (float)(count & 0xFF) / 255.0F, (float)count2 / 255.0F);
   }

   public static void applyEntity4(Entity entity, int count, float factor, float factor2) {
      float factor3 = (float)(
         entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosX()
      );
      float factor4 = (float)(
         entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosY()
      );
      float factor5 = (float)(
         entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosZ()
      );
      setColor(count);
      float factor6 = ((Module)ClientGlobals.moduleManager.child).isToggled() && entity instanceof EntityPlayer ? entity.height / 1.7F : entity.height;
      applyEntity6(entity, factor3, factor4, factor5, (double)(entity.width - 0.2F), (double)(factor6 + 0.1F));
      if (factor2 > 0.0F) {
         GL11.glLineWidth(factor2);
         processAction(count, factor);
         applyEntity2(entity, factor3, factor4, factor5, (double)(entity.width - 0.2F), (double)(factor6 + 0.1F));
      }
   }

   public static void applyBlockPos2(BlockPos blockPos, int count, float factor, float factor2) {
      setColor(count);
      float factor3 = (float)((double)blockPos.getX() - minecraft.getRenderManager().getRenderPosX());
      float factor4 = (float)((double)blockPos.getY() - minecraft.getRenderManager().getRenderPosY());
      float factor5 = (float)((double)blockPos.getZ() - minecraft.getRenderManager().getRenderPosZ());
      Block block = minecraft.theWorld.getBlockState(blockPos).getBlock();
      processAction3(
         (double)factor3,
         (double)factor4,
         (double)factor5,
         (double)factor3 + block.getCoordOffset9(),
         (double)factor4 + block.getCoordOffset6(),
         (double)factor5 + block.getCoordOffset15()
      );
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void applyTileEntity(TileEntity tileEntity, int count, float factor, float factor2, double offset, boolean flag) {
      BlockPos blockPos = tileEntity.getBlockPos();
      double offset2 = (double)((float)blockPos.getX() + 0.0625F);
      double coordY = (double)blockPos.getY();
      double offset3 = (double)((float)blockPos.getZ() + 0.0625F);
      double offset4 = (double)((float)blockPos.getX() + 0.9375F);
      double offset5 = (double)((float)blockPos.getY() + 0.875F);
      double offset6 = (double)((float)blockPos.getZ() + 0.9375F);
      if (tileEntity instanceof TileEntityChest) {
         TileEntityChest tileEntityChest = (TileEntityChest)tileEntity;
         TileEntityChest tileEntityChest2 = null;
         if (tileEntityChest.tileEntityChest3 != null) {
            tileEntityChest2 = tileEntityChest.tileEntityChest3;
         } else if (tileEntityChest.tileEntityChest1 != null) {
            tileEntityChest2 = tileEntityChest.tileEntityChest1;
         }

         if (tileEntityChest2 != null) {
            BlockPos blockPos2 = tileEntityChest2.getBlockPos();
            offset2 = Math.min(offset2, (double)((float)blockPos2.getX() + 0.0625F));
            coordY = Math.min(coordY, (double)blockPos2.getY());
            offset3 = Math.min(offset3, (double)((float)blockPos2.getZ() + 0.0625F));
            offset4 = Math.max(offset4, (double)((float)blockPos2.getX() + 0.9375F));
            offset5 = Math.max(offset5, (double)((float)blockPos2.getY() + 0.875F));
            offset6 = Math.max(offset6, (double)((float)blockPos2.getZ() + 0.9375F));
         }
      }

      double offset7 = offset2 - minecraft.getRenderManager().getRenderPosX();
      double offset8 = coordY - minecraft.getRenderManager().getRenderPosY();
      double offset9 = offset3 - minecraft.getRenderManager().getRenderPosZ();
      double offset10 = offset4 - minecraft.getRenderManager().getRenderPosX();
      double offset11 = offset5 - minecraft.getRenderManager().getRenderPosY();
      double offset12 = offset6 - minecraft.getRenderManager().getRenderPosZ();
      setColor(count);
      if (flag) {
         processAction4(offset7 - 0.0625, offset8, offset9 - 0.0625, offset10 + 0.0625, offset11 + 0.125, offset12 + 0.0625);
      } else {
         processAction4(offset7 - offset, offset8 - offset, offset9 - offset, offset10 + offset, offset11 + offset, offset12 + offset);
      }

      if (factor2 > 0.0F) {
         GL11.glLineWidth(factor2);
         processAction(count, factor);
         if (flag) {
            processAction(offset7 - 0.0625, offset8, offset9 - 0.0625, offset10 + 0.0625, offset11 + 0.125, offset12 + 0.0625);
         } else {
            processAction(offset7 - offset, offset8 - offset, offset9 - offset, offset10 + offset, offset11 + offset, offset12 + offset);
         }
      }

      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void processDouble8(double posX, double posX2, double posY, double posZ, float posX3, float posX4, float posX5, float posY2, float posZ2) {
      if (posZ2 != 0.0F) {
         GlStateManager.disableBlend();
         GlStateManager.enableTexture2D();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         GlStateManager.color4f2(posX4, posX5, posY2, posZ2);
         GL11.glLineWidth(posX3);
         GL11.glBegin(3);
         GL11.glVertex2d(posX, posX2);
         GL11.glVertex2d(posY, posX2);
         GL11.glVertex2d(posY, posZ);
         GL11.glVertex2d(posX, posZ);
         GL11.glVertex2d(posX, posX2);
         GL11.glEnd();
         GlStateManager.disableTexture2D();
         GlStateManager.enableBlend();
      }
   }

   public static void processAction2(int count, int count2, int count3, int count4, float posX, float posX2, float posY, float posZ) {
      int count5 = count;
      int count6 = count2;
      int count7 = count3;
      int count8 = count4;
      if (count < count3) {
         count5 = count3;
         count7 = count;
      }

      if (count2 < count4) {
         count6 = count4;
         count8 = count2;
      }

      GL11.glEnable(3042);
      GL11.glDisable(3553);
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState();
      }

      GL11.glBlendFunc(770, 771);
      boolean flag = GL11.glIsEnabled(3008);
      if (flag) {
         GL11.glDisable(3008);
      }

      GL11.glEnable(2848);
      GlStateManager.color4f2(posX, posX2, posY, posZ);
      GL11.glBegin(7);
      GL11.glVertex2d((double)count5, (double)count8);
      GL11.glVertex2d((double)count7, (double)count8);
      GL11.glVertex2d((double)count7, (double)count6);
      GL11.glVertex2d((double)count5, (double)count6);
      GL11.glEnd();
      GL11.glEnable(3553);
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState2();
      }

      if (flag) {
         GL11.glEnable(3008);
      }

      GL11.glDisable(3042);
      GL11.glDisable(2848);
   }

   public static void processAction5(double posX, double posX2, double posX3, double posX4, double posY, double posZ) {
      GL11.glBegin(1);
      GL11.glVertex3d(posX, posX2, posX3);
      GL11.glVertex3d(posX, posX2, posZ);
      GL11.glVertex3d(posX, posX2, posZ);
      GL11.glVertex3d(posX4, posX2, posZ);
      GL11.glVertex3d(posX4, posX2, posZ);
      GL11.glVertex3d(posX4, posX2, posX3);
      GL11.glVertex3d(posX4, posX2, posX3);
      GL11.glVertex3d(posX, posX2, posX3);
      GL11.glVertex3d(posX, posY, posX3);
      GL11.glVertex3d(posX, posY, posZ);
      GL11.glVertex3d(posX, posY, posZ);
      GL11.glVertex3d(posX4, posY, posZ);
      GL11.glVertex3d(posX4, posY, posZ);
      GL11.glVertex3d(posX4, posY, posX3);
      GL11.glVertex3d(posX4, posY, posX3);
      GL11.glVertex3d(posX, posY, posX3);
      GL11.glVertex3d(posX, posX2, posX3);
      GL11.glVertex3d(posX, posY, posX3);
      GL11.glVertex3d(posX4, posX2, posX3);
      GL11.glVertex3d(posX4, posY, posX3);
      GL11.glVertex3d(posX4, posX2, posZ);
      GL11.glVertex3d(posX4, posY, posZ);
      GL11.glVertex3d(posX, posX2, posZ);
      GL11.glVertex3d(posX, posY, posZ);
      GL11.glEnd();
   }

   public static void applyItemStack2(ItemStack stack, int count, int count2, boolean flag) {
      if (stack != null) {
         boolean flag2 = GlStateManager.obtainState9();
         if (flag2) {
            GlStateManager.enableBlend();
         }

         if (flag) {
            minecraft.getRenderItem().renderItemAndEffectIntoGUI(stack, count, count2);
            minecraft.getRenderItem().renderItemOverlays(minecraft.fontRendererObj, stack, count, count2);
            GlStateManager.applyState3();
         } else {
            minecraft.getRenderItem().renderItemAndEffectIntoGUI(stack, count, count2);
         }

         if (flag2) {
            GlStateManager.disableBlend();
         }
      }
   }

   public static void processAction(float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      processFloat13(posX, posX2, posX3, posX4, posX5, posY, posZ, 1.0F);
   }

   public static void processAction(int count, int count2, int count3, int count4, float posX, float posX2, float posY, float posZ, boolean flag) {
      int count5 = count;
      int count6 = count2;
      int count7 = count3;
      int count8 = count4;
      if (count < count3) {
         count5 = count3;
         count7 = count;
      }

      if (count2 < count4) {
         count6 = count4;
         count8 = count2;
      }

      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      boolean flag2 = GlStateManager.obtainState9();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState();
      }

      boolean flag3 = GL11.glIsEnabled(3008);
      if (flag3) {
         GL11.glDisable(3008);
      }

      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      if (!worldRenderer.isEnabled3()) {
         worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry3);
      }

      worldRenderer.getWorldRenderer((double)count5, (double)count8, 0.0).resolveWorldRenderer(posX, posX2, posY, posZ).updateState();
      worldRenderer.getWorldRenderer((double)count7, (double)count8, 0.0).resolveWorldRenderer(posX, posX2, posY, posZ).updateState();
      worldRenderer.getWorldRenderer((double)count7, (double)count6, 0.0).resolveWorldRenderer(posX, posX2, posY, posZ).updateState();
      worldRenderer.getWorldRenderer((double)count5, (double)count6, 0.0).resolveWorldRenderer(posX, posX2, posY, posZ).updateState();
      if (flag) {
         tessellator.updateState();
         GlStateManager.disableTexture2D();
         if (flag3) {
            GL11.glEnable(3008);
         }

         if (BuildManager.canExecute()) {
            PreEndHandManager.updateState2();
         }

         if (!flag2) {
            GlStateManager.enableBlend();
         }
      }
   }

   public static void processAction4(float posX, float posX2, float posX3, float posX4, float posY, float posZ) {
      WorldRenderer worldRenderer = Tessellator.getTessellator().getWorldRenderer();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posY, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posX3).updateState();
      worldRenderer.getWorldRenderer((double)posX4, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posZ).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posX2, (double)posX3).updateState();
      worldRenderer.updateState();
   }

   public static void applyEntity5(Entity entity, int count, float factor, float factor2, boolean flag) {
      if (!(factor2 <= 0.0F)) {
         GL11.glPushMatrix();
         float factor3 = (float)(
            entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosX()
         );
         float factor4 = (float)(
            entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosY()
         );
         float factor5 = (float)(
            entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)minecraft.getTimer().scaleFactor6 - minecraft.getRenderManager().getRenderPosZ()
         );
         setColor(count);
         float factor6 = flag ? 0.0F : entity.getHeight();
         float factor7 = entity.width / 2.0F;
         float factor8 = ((Module)ClientGlobals.moduleManager.child).isToggled() && entity instanceof EntityPlayer ? entity.height / 1.7F : entity.height;
         float factor9 = factor3 - factor7 - factor6;
         float factor10 = factor4 - factor6;
         float factor11 = factor5 - factor7 - factor6;
         float factor12 = factor3 + factor7 + factor6;
         float factor13 = factor4 + factor8 + factor6;
         float factor14 = factor5 + factor7 + factor6;
         if (((Module)ClientGlobals.moduleManager.hitBox).isToggled()) {
            float factor15 = (float)ClientGlobals.moduleManager.hitBox.settingExpandXZ.getValue() - factor6;
            float factor16 = (float)ClientGlobals.moduleManager.hitBox.settingExpandY.getValue() - factor6;
            if (ClientGlobals.moduleManager.hitBox.settingServerHitBox.isEnabled()) {
               double offset = (double)entity.indexOffset2 / 32.0;
               double offset2 = (double)entity.indexOffset3 / 32.0;
               double offset3 = (double)entity.indexOffset / 32.0;
               factor9 = (float)(Math.min(offset - (double)factor7, (double)factor9) - (double)factor15);
               factor10 = (float)(Math.min(offset2, (double)factor10) - (double)factor16);
               factor11 = (float)(Math.min(offset3 - (double)factor7, (double)factor11) - (double)factor15);
               factor12 = (float)(Math.max(offset + (double)factor7, (double)factor12) + (double)factor15);
               factor13 = (float)(Math.max(offset2 + (double)entity.height, (double)factor13) + (double)factor16);
               factor14 = (float)(Math.max(offset3 + (double)factor7, (double)factor14) + (double)factor15);
            } else {
               factor9 -= factor15;
               factor10 -= factor16;
               factor11 -= factor15;
               factor12 += factor15;
               factor13 += factor16;
               factor14 += factor15;
            }
         }

         GL11.glLineWidth(factor2);
         processAction(count, factor);
         processAction5((double)factor9, (double)factor10, (double)factor11, (double)factor12, (double)factor13, (double)factor14);
         GL11.glPopMatrix();
      }
   }

   public static void applyTileEntity2(TileEntity tileEntity, int count) {
      double offset = 0.0;
      double offset2 = 0.0;
      if (tileEntity instanceof TileEntityChest) {
         TileEntityChest tileEntityChest = (TileEntityChest)tileEntity;
         if (tileEntityChest.tileEntityChest3 != null) {
            offset += 0.5;
         } else if (tileEntityChest.tileEntityChest1 != null) {
            offset2 += 0.5;
         }
      }

      float factor = (float)((double)tileEntity.getBlockPos().getX() + offset - minecraft.getRenderManager().getRenderPosX());
      float factor2 = (float)((double)tileEntity.getBlockPos().getY() - minecraft.getRenderManager().getRenderPosY());
      float factor3 = (float)((double)tileEntity.getBlockPos().getZ() + offset2 - minecraft.getRenderManager().getRenderPosZ());
      GlStateManager.pushMatrix();
      GlStateManager.translate3d2((double)factor + 0.5, (double)factor2 + 0.47, (double)factor3 + 0.5);
      GlStateManager.rotate(-minecraft.getRenderManager().scaleFactor, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(minecraft.getRenderManager().scaleFactor7, 1.0F, 0.0F, 0.0F);
      GlStateManager.translate3d(-0.098, -0.098, 0.098);
      float factor4 = offset == 0.0 && offset2 == 0.0 ? 8.0F : 14.0F;
      float factor5 = 7.5F;
      processAction(count, 1.0F);
      drawRect(factor4, factor5 - 1.0F, factor4 - 4.0F, factor5);
      drawRect(-factor4, factor5 - 1.0F, -factor4 + 4.0F, factor5);
      drawRect(-factor4, factor5, -factor4 + 1.0F, factor5 - 4.0F);
      drawRect(factor4, factor5, factor4 - 1.0F, factor5 - 4.0F);
      drawRect(factor4, -factor5, factor4 - 4.0F, -factor5 + 1.0F);
      drawRect(-factor4, -factor5, -factor4 + 4.0F, -factor5 + 1.0F);
      drawRect(-factor4, -factor5 + 1.0F, -factor4 + 1.0F, -factor5 + 4.0F);
      drawRect(factor4, -factor5 + 1.0F, factor4 - 1.0F, -factor5 + 4.0F);
      GlStateManager.translate3f2(0.0F, 0.0F, 0.0F);
      drawRect(factor4, factor5, factor4 - 4.0F, factor5 + 0.2F);
      drawRect(-factor4, factor5, -factor4 + 4.0F, factor5 + 0.2F);
      drawRect(-factor4 - 0.2F, factor5 + 0.2F, -factor4, factor5 - 4.0F);
      drawRect(factor4 + 0.2F, factor5 + 0.2F, factor4, factor5 - 4.0F);
      drawRect(factor4 + 0.2F, -factor5, factor4 - 4.0F, -factor5 - 0.2F);
      drawRect(-factor4 - 0.2F, -factor5, -factor4 + 4.0F, -factor5 - 0.2F);
      drawRect(-factor4 - 0.2F, -factor5, -factor4, -factor5 + 4.0F);
      drawRect(factor4 + 0.2F, -factor5, factor4, -factor5 + 4.0F);
      GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.popMatrix();
   }

   public static void processAction2(double posX, double posX2, double posY, double posZ, float factor, Color color) {
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GL11.glLineWidth(factor);
      processAction(color);
      GL11.glBegin(1);
      GL11.glVertex2d(posX, posX2);
      GL11.glVertex2d(posY, posZ);
      GL11.glEnd();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
   }

   public static void setColor(int color) {
      GlStateManager.color4f2(
         (float)(color >> 16 & 0xFF) / 255.0F, (float)(color >> 8 & 0xFF) / 255.0F, (float)(color & 0xFF) / 255.0F, (float)(color >> 24 & 0xFF) / 255.0F
      );
   }

   public static void processDouble10(double posX, double posX2, double posY, double posZ, float factor, int count) {
      processDouble8(
         posX,
         posX2,
         posY,
         posZ,
         factor,
         RandomUtil.processAction5(count),
         RandomUtil.processAction4(count),
         RandomUtil.processAction7(count),
         RandomUtil.processAction2(count)
      );
   }

   public static void processFloat13(float posX, float posX2, float posX3, float posX4, float posX5, float posX6, float posY, float posZ) {
      float factor = posX;
      float factor2 = posX2;
      float factor3 = posX3;
      float factor4 = posX4;
      if (posX < posX3) {
         factor = posX3;
         factor3 = posX;
      }

      if (posX2 < posX4) {
         factor2 = posX4;
         factor4 = posX2;
      }

      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      boolean flag = GlStateManager.obtainState9();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState();
      }

      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color4f2(posX5, posX6, posY, posZ);
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry1);
      worldRenderer.getWorldRenderer((double)factor, (double)factor4, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)factor3, (double)factor4, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)factor3, (double)factor2, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)factor, (double)factor2, 0.0).updateState();
      tessellator.updateState();
      GlStateManager.disableTexture2D();
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState2();
      }

      if (!flag) {
         GlStateManager.enableBlend();
      }
   }

   public static void processFloat14(
      float posX, float posX2, float posX3, float posY, float posZ, int count, boolean flag, boolean flag2, boolean flag3, boolean flag4, int count2
   ) {
      GL11.glPushMatrix();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      setColor(count2);
      GL11.glBegin(9);
      float factor = Math.min(Math.min(Math.abs(posY - posX2) / 2.0F, Math.abs(posX3 - posX) / 2.0F), posZ);
      int count3 = Math.max(1, count - count % 3);
      if (flag4) {
         for (int index = 0; index <= 90; index += count3) {
            GL11.glVertex2d(
               (double)(posX3 - factor) + Math.sin(Math.toRadians((double)index)) * (double)factor,
               (double)(posY - factor) + Math.cos(Math.toRadians((double)index)) * (double)factor
            );
         }
      } else {
         GL11.glVertex2d((double)posX3, (double)posY);
      }

      if (flag2) {
         for (int index2 = 90; index2 <= 180; index2 += count3) {
            GL11.glVertex2d(
               (double)(posX3 - factor) + Math.sin(Math.toRadians((double)index2)) * (double)factor,
               (double)(posX2 + factor) + Math.cos(Math.toRadians((double)index2)) * (double)factor
            );
         }
      } else {
         GL11.glVertex2d((double)posX3, (double)posX2);
      }

      if (flag) {
         for (int index3 = 180; index3 <= 270; index3 += count3) {
            GL11.glVertex2d(
               (double)(posX + factor) + Math.sin(Math.toRadians((double)index3)) * (double)factor,
               (double)(posX2 + factor) + Math.cos(Math.toRadians((double)index3)) * (double)factor
            );
         }
      } else {
         GL11.glVertex2d((double)posX, (double)posX2);
      }

      if (flag3) {
         for (int index4 = 270; index4 <= 360; index4 += count3) {
            GL11.glVertex2d(
               (double)(posX + factor) + Math.sin(Math.toRadians((double)index4)) * (double)factor,
               (double)(posY - factor) + Math.cos(Math.toRadians((double)index4)) * (double)factor
            );
         }
      } else {
         GL11.glVertex2d((double)posX, (double)posY);
      }

      GL11.glEnd();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GL11.glPopMatrix();
   }

   public static void processAction(double posX, double posX2, double posY, double posZ, float factor, double offset, double offset2, int count) {
      double offset3 = Math.abs(posX - posY) / 2.0 / (100.0 / offset);
      double offset4 = Math.abs(posX2 - posZ) / 2.0 / (100.0 / offset2);
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      setColor(count);
      GL11.glLineWidth(factor);
      GL11.glBegin(3);
      GL11.glVertex2d(posX, posX2 + offset4);
      GL11.glVertex2d(posX, posX2);
      GL11.glVertex2d(posX + offset3, posX2);
      GL11.glEnd();
      GL11.glBegin(3);
      GL11.glVertex2d(posY, posX2 + offset4);
      GL11.glVertex2d(posY, posX2);
      GL11.glVertex2d(posY - offset3, posX2);
      GL11.glEnd();
      GL11.glBegin(3);
      GL11.glVertex2d(posY, posZ - offset4);
      GL11.glVertex2d(posY, posZ);
      GL11.glVertex2d(posY - offset3, posZ);
      GL11.glEnd();
      GL11.glBegin(3);
      GL11.glVertex2d(posX, posZ - offset4);
      GL11.glVertex2d(posX, posZ);
      GL11.glVertex2d(posX + offset3, posZ);
      GL11.glEnd();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
   }

   public static void processAction(float posX, float posX2, float posY, float posZ, int count) {
      float factor = (float)(count >> 24 & 0xFF) / 255.0F;
      float factor2 = (float)(count >> 16 & 0xFF) / 255.0F;
      float factor3 = (float)(count >> 8 & 0xFF) / 255.0F;
      float factor4 = (float)(count & 0xFF) / 255.0F;
      processFloat13(posX, posX2, posY, posZ, factor2, factor3, factor4, factor);
   }

   public static void processAction4(AxisAlignedBB boundingBox) {
      processAction(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
   }

   public static void prepareScissor() {
      GL11.glDepthMask(true);
      GlStateManager.enableLighting();
      GL11.glEnable(3553);
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState2();
      }

      GL11.glEnable(2929);
      GlStateManager.alphaFunc(516, 0.2F);
      GL11.glDisable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2848);
   }

   public static void drawBorderedRect(float posX, float posX2, float posY, float posZ) {
      ScaledResolution scaledResolution = new ScaledResolution(minecraft);
      float scaleFactor = (float)scaledResolution.getScaleFactor();
      GL11.glScissor(
         (int)(posX * scaleFactor), (int)((scaledResolution.getCoordOffset3() - (double)posZ) * (double)scaleFactor), (int)((posY - posX) * scaleFactor), (int)((posZ - posX2) * scaleFactor)
      );
   }

   public static void processAction(int count, int count2, int count3, int count4, float factor, Color color) {
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GL11.glLineWidth(factor);
      processAction(color);
      GL11.glBegin(1);
      GL11.glVertex2i(count, count2);
      GL11.glVertex2i(count3, count4);
      GL11.glEnd();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
   }

   public static boolean checkCondition(float posX, float posX2, float posY, float posZ, int count, int count2) {
      return (float)count >= posX && (float)count2 >= posX2 && (float)count < posX + posY && (float)count2 < posX2 + posZ;
   }

   public static void processAction2(float posX, float posY, float posZ, int count) {
      GL11.glPushMatrix();
      GlStateManager.disableBlend();
      GlStateManager.enableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      setColor(count);
      GL11.glBegin(3);
      float factor = posZ / 2.0F;
      float factor2 = posX + factor;
      float factor3 = posX - factor;
      float factor4 = posY + factor;
      float factor5 = posY - factor;
      GL11.glVertex2f(factor2, factor5);
      GL11.glVertex2f(factor3, factor5);
      GL11.glVertex2f(posX, factor4);
      GL11.glVertex2f(factor2, factor5);
      GL11.glEnd();
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GL11.glPopMatrix();
   }

   public static void applyEntity6(Entity entity, float posX, float posY, float posZ, double offset, double offset2) {
      float factor = (float) (Math.PI / 180.0);
      double offset3 = offset * 1.5;
      float factor2 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 45.0F;
      float factor3;
      if (factor2 < 0.0F) {
         factor3 = 0.0F + (360.0F - Math.abs(factor2));
      } else {
         factor3 = factor2;
      }

      float factor4 = factor3 * -1.0F * factor;
      float factor5 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 135.0F;
      float factor6;
      if (factor5 < 0.0F) {
         factor6 = 0.0F + (360.0F - Math.abs(factor5));
      } else {
         factor6 = factor5;
      }

      float factor7 = factor6 * -1.0F * factor;
      float factor8 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 225.0F;
      float factor9;
      if (factor8 < 0.0F) {
         factor9 = 0.0F + (360.0F - Math.abs(factor8));
      } else {
         factor9 = factor8;
      }

      float factor10 = factor9 * -1.0F * factor;
      float factor11 = MathHelper.sqrt_float(entity.getScaleFactor12()) + 315.0F;
      float factor12;
      if (factor11 < 0.0F) {
         factor12 = 0.0F + (360.0F - Math.abs(factor11));
      } else {
         factor12 = factor11;
      }

      float factor13 = factor12 * -1.0F * factor;
      float factor14 = (float)(Math.sin((double)factor4) * offset3 + (double)posX);
      float factor15 = (float)(Math.cos((double)factor4) * offset3 + (double)posZ);
      float factor16 = (float)(Math.sin((double)factor7) * offset3 + (double)posX);
      float factor17 = (float)(Math.cos((double)factor7) * offset3 + (double)posZ);
      float factor18 = (float)(Math.sin((double)factor10) * offset3 + (double)posX);
      float factor19 = (float)(Math.cos((double)factor10) * offset3 + (double)posZ);
      float factor20 = (float)(Math.sin((double)factor13) * offset3 + (double)posX);
      float factor21 = (float)(Math.cos((double)factor13) * offset3 + (double)posZ);
      float factor22 = (float)((double)posY + offset2);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry1);
      worldRenderer.getWorldRenderer((double)factor14, (double)posY, (double)factor15).updateState();
      worldRenderer.getWorldRenderer((double)factor14, (double)factor22, (double)factor15).updateState();
      worldRenderer.getWorldRenderer((double)factor16, (double)factor22, (double)factor17).updateState();
      worldRenderer.getWorldRenderer((double)factor16, (double)posY, (double)factor17).updateState();
      worldRenderer.getWorldRenderer((double)factor16, (double)posY, (double)factor17).updateState();
      worldRenderer.getWorldRenderer((double)factor16, (double)factor22, (double)factor17).updateState();
      worldRenderer.getWorldRenderer((double)factor18, (double)factor22, (double)factor19).updateState();
      worldRenderer.getWorldRenderer((double)factor18, (double)posY, (double)factor19).updateState();
      worldRenderer.getWorldRenderer((double)factor18, (double)posY, (double)factor19).updateState();
      worldRenderer.getWorldRenderer((double)factor18, (double)factor22, (double)factor19).updateState();
      worldRenderer.getWorldRenderer((double)factor20, (double)factor22, (double)factor21).updateState();
      worldRenderer.getWorldRenderer((double)factor20, (double)posY, (double)factor21).updateState();
      worldRenderer.getWorldRenderer((double)factor20, (double)posY, (double)factor21).updateState();
      worldRenderer.getWorldRenderer((double)factor20, (double)factor22, (double)factor21).updateState();
      worldRenderer.getWorldRenderer((double)factor14, (double)factor22, (double)factor15).updateState();
      worldRenderer.getWorldRenderer((double)factor14, (double)posY, (double)factor15).updateState();
      worldRenderer.getWorldRenderer((double)factor14, (double)posY, (double)factor15).updateState();
      worldRenderer.getWorldRenderer((double)factor16, (double)posY, (double)factor17).updateState();
      worldRenderer.getWorldRenderer((double)factor18, (double)posY, (double)factor19).updateState();
      worldRenderer.getWorldRenderer((double)factor20, (double)posY, (double)factor21).updateState();
      worldRenderer.getWorldRenderer((double)factor14, (double)factor22, (double)factor15).updateState();
      worldRenderer.getWorldRenderer((double)factor16, (double)factor22, (double)factor17).updateState();
      worldRenderer.getWorldRenderer((double)factor18, (double)factor22, (double)factor19).updateState();
      worldRenderer.getWorldRenderer((double)factor20, (double)factor22, (double)factor21).updateState();
      worldRenderer.updateState();
      tessellator.updateState();
   }

   private static void applyString(float posX, float posY, float posZ, String text) {
      float factor = text.equals("Feet") ? 0.0F : ClientGlobals.moduleManager.tracers.motionMultiplier;
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(1, DefaultVertexFormats.auxiliaryLoggerEntry1);
      worldRenderer.getWorldRenderer(0.0, (double)factor, 0.0).updateState();
      worldRenderer.getWorldRenderer((double)posX, (double)posY, (double)posZ).updateState();
      worldRenderer.updateState();
      tessellator.updateState();
   }
}
