package client.util;

import client.render.WorldRenderer;
import java.util.List;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class CustomResourceLocationArrayHandler extends ResourceLocationArrayHolder {
   public static final ResourceLocation resourceLocation2 = new ResourceLocation("textures/entity/beacon_beam.png");

   public void applyTileEntityBeacon(TileEntityBeacon tileEntityBeacon, double posX, double posY, double posZ, float factor, int count) {
      float factor2 = tileEntityBeacon.getFactor();
      if ((double)factor2 > 0.0) {
         if (BuildManager.canExecute()) {
            PreEndHandManager.updateState36();
         }

         GlStateManager.alphaFunc(516, 0.1F);
         if (factor2 > 0.0F) {
            Tessellator tessellator = Tessellator.getTessellator();
            WorldRenderer worldRenderer = tessellator.getWorldRenderer();
            GlStateManager.applyState20();
            List list = tileEntityBeacon.getList();
            int count2 = 0;

            for (int index = 0; index < list.size(); index++) {
               AimPicker aimPicker = (AimPicker)list.get(index);
               int count3 = count2 + aimPicker.getCount();
               this.applyResourceLocation2(resourceLocation2);
               GL11.glTexParameterf(3553, 10242, 10497.0F);
               GL11.glTexParameterf(3553, 10243, 10497.0F);
               GlStateManager.applyState3();
               GlStateManager.disableAlpha();
               GlStateManager.enableBlend();
               GlStateManager.depthMask(true);
               GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
               double offset = (double)tileEntityBeacon.getWorld().getTimestamp2() + (double)factor;
               double offset2 = MathHelper.wrapAngleTo180_double(-offset * 0.2 - (double)MathHelper.ceiling_double_int(-offset * 0.1));
               float factor3 = aimPicker.getFactorArray()[0];
               float factor4 = aimPicker.getFactorArray()[1];
               float factor5 = aimPicker.getFactorArray()[2];
               double offset3 = offset * 0.025 * -1.5;
               double offset4 = 0.5 + Math.cos(offset3 + (Math.PI * 3.0 / 4.0)) * 0.2;
               double offset5 = 0.5 + Math.sin(offset3 + (Math.PI * 3.0 / 4.0)) * 0.2;
               double offset6 = 0.5 + Math.cos(offset3 + (Math.PI / 4)) * 0.2;
               double offset7 = 0.5 + Math.sin(offset3 + (Math.PI / 4)) * 0.2;
               double offset8 = 0.5 + Math.cos(offset3 + (Math.PI * 5.0 / 4.0)) * 0.2;
               double offset9 = 0.5 + Math.sin(offset3 + (Math.PI * 5.0 / 4.0)) * 0.2;
               double offset10 = 0.5 + Math.cos(offset3 + (Math.PI * 7.0 / 4.0)) * 0.2;
               double offset11 = 0.5 + Math.sin(offset3 + (Math.PI * 7.0 / 4.0)) * 0.2;
               double offset12 = -1.0 + offset2;
               double offset13 = (double)((float)aimPicker.getCount() * factor2) * 2.5 + offset12;
               worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
               worldRenderer.getWorldRenderer(posX + offset4, posY + (double)count3, posZ + offset5)
                  .getWorldRenderer6(1.0, offset13)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset4, posY + (double)count2, posZ + offset5)
                  .getWorldRenderer6(1.0, offset12)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset6, posY + (double)count2, posZ + offset7)
                  .getWorldRenderer6(0.0, offset12)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset6, posY + (double)count3, posZ + offset7)
                  .getWorldRenderer6(0.0, offset13)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset10, posY + (double)count3, posZ + offset11)
                  .getWorldRenderer6(1.0, offset13)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset10, posY + (double)count2, posZ + offset11)
                  .getWorldRenderer6(1.0, offset12)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset8, posY + (double)count2, posZ + offset9)
                  .getWorldRenderer6(0.0, offset12)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset8, posY + (double)count3, posZ + offset9)
                  .getWorldRenderer6(0.0, offset13)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset6, posY + (double)count3, posZ + offset7)
                  .getWorldRenderer6(1.0, offset13)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset6, posY + (double)count2, posZ + offset7)
                  .getWorldRenderer6(1.0, offset12)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset10, posY + (double)count2, posZ + offset11)
                  .getWorldRenderer6(0.0, offset12)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset10, posY + (double)count3, posZ + offset11)
                  .getWorldRenderer6(0.0, offset13)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset8, posY + (double)count3, posZ + offset9)
                  .getWorldRenderer6(1.0, offset13)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset8, posY + (double)count2, posZ + offset9)
                  .getWorldRenderer6(1.0, offset12)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset4, posY + (double)count2, posZ + offset5)
                  .getWorldRenderer6(0.0, offset12)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + offset4, posY + (double)count3, posZ + offset5)
                  .getWorldRenderer6(0.0, offset13)
                  .resolveWorldRenderer(factor3, factor4, factor5, 1.0F)
                  .updateState();
               tessellator.updateState();
               GlStateManager.disableBlend();
               GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
               GlStateManager.depthMask(false);
               double offset14 = -1.0 + offset2;
               double offset15 = (double)((float)aimPicker.getCount() * factor2) + offset14;
               worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
               worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)count3, posZ + 0.2)
                  .getWorldRenderer6(1.0, offset15)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)count2, posZ + 0.2)
                  .getWorldRenderer6(1.0, offset14)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)count2, posZ + 0.2)
                  .getWorldRenderer6(0.0, offset14)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)count3, posZ + 0.2)
                  .getWorldRenderer6(0.0, offset15)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)count3, posZ + 0.8)
                  .getWorldRenderer6(1.0, offset15)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)count2, posZ + 0.8)
                  .getWorldRenderer6(1.0, offset14)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)count2, posZ + 0.8)
                  .getWorldRenderer6(0.0, offset14)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)count3, posZ + 0.8)
                  .getWorldRenderer6(0.0, offset15)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)count3, posZ + 0.2)
                  .getWorldRenderer6(1.0, offset15)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)count2, posZ + 0.2)
                  .getWorldRenderer6(1.0, offset14)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)count2, posZ + 0.8)
                  .getWorldRenderer6(0.0, offset14)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)count3, posZ + 0.8)
                  .getWorldRenderer6(0.0, offset15)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)count3, posZ + 0.8)
                  .getWorldRenderer6(1.0, offset15)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)count2, posZ + 0.8)
                  .getWorldRenderer6(1.0, offset14)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)count2, posZ + 0.2)
                  .getWorldRenderer6(0.0, offset14)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)count3, posZ + 0.2)
                  .getWorldRenderer6(0.0, offset15)
                  .resolveWorldRenderer(factor3, factor4, factor5, 0.125F)
                  .updateState();
               tessellator.updateState();
               GlStateManager.applyState13();
               GlStateManager.disableTexture2D();
               GlStateManager.depthMask(true);
               count2 = count3;
            }

            GlStateManager.applyState19();
         }

         if (BuildManager.canExecute()) {
            PreEndHandManager.updateState66();
         }
      }
   }

   @Override
   public void applyTileEntity(TileEntity tileEntity, double posX, double posY, double posZ, float factor, int count) {
      this.applyTileEntityBeacon((TileEntityBeacon)tileEntity, posX, posY, posZ, factor, count);
   }

   public static void applyFloatArray(double posX, double posY, double posZ, float factor, long value, int count, float[] factorArray) {
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState36();
      }

      GlStateManager.alphaFunc(516, 0.1F);
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      GlStateManager.applyState20();
      int count2 = 0 + count;
      StandardMapEntry.standardMapEntry.textureManager.applyResourceLocation3(resourceLocation2);
      GL11.glTexParameterf(3553, 10242, 10497.0F);
      GL11.glTexParameterf(3553, 10243, 10497.0F);
      GlStateManager.applyState3();
      GlStateManager.disableAlpha();
      GlStateManager.enableBlend();
      GlStateManager.depthMask(true);
      GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
      double offset = (double)value + (double)factor;
      double offset2 = MathHelper.wrapAngleTo180_double(-offset * 0.2 - (double)MathHelper.ceiling_double_int(-offset * 0.1));
      float factor2 = factorArray[0];
      float factor3 = factorArray[1];
      float factor4 = factorArray[2];
      double offset3 = offset * 0.025 * -1.5;
      double offset4 = 0.5 + Math.cos(offset3 + (Math.PI * 3.0 / 4.0)) * 0.2;
      double offset5 = 0.5 + Math.sin(offset3 + (Math.PI * 3.0 / 4.0)) * 0.2;
      double offset6 = 0.5 + Math.cos(offset3 + (Math.PI / 4)) * 0.2;
      double offset7 = 0.5 + Math.sin(offset3 + (Math.PI / 4)) * 0.2;
      double offset8 = 0.5 + Math.cos(offset3 + (Math.PI * 5.0 / 4.0)) * 0.2;
      double offset9 = 0.5 + Math.sin(offset3 + (Math.PI * 5.0 / 4.0)) * 0.2;
      double offset10 = 0.5 + Math.cos(offset3 + (Math.PI * 7.0 / 4.0)) * 0.2;
      double offset11 = 0.5 + Math.sin(offset3 + (Math.PI * 7.0 / 4.0)) * 0.2;
      double offset12 = -1.0 + offset2;
      double offset13 = (double)((float)count) * 2.5 + offset12;
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      worldRenderer.getWorldRenderer(posX + offset4, posY + (double)count2, posZ + offset5)
         .getWorldRenderer6(1.0, offset13)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset4, posY + (double)0, posZ + offset5)
         .getWorldRenderer6(1.0, offset12)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset6, posY + (double)0, posZ + offset7)
         .getWorldRenderer6(0.0, offset12)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset6, posY + (double)count2, posZ + offset7)
         .getWorldRenderer6(0.0, offset13)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset10, posY + (double)count2, posZ + offset11)
         .getWorldRenderer6(1.0, offset13)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset10, posY + (double)0, posZ + offset11)
         .getWorldRenderer6(1.0, offset12)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset8, posY + (double)0, posZ + offset9)
         .getWorldRenderer6(0.0, offset12)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset8, posY + (double)count2, posZ + offset9)
         .getWorldRenderer6(0.0, offset13)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset6, posY + (double)count2, posZ + offset7)
         .getWorldRenderer6(1.0, offset13)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset6, posY + (double)0, posZ + offset7)
         .getWorldRenderer6(1.0, offset12)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset10, posY + (double)0, posZ + offset11)
         .getWorldRenderer6(0.0, offset12)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset10, posY + (double)count2, posZ + offset11)
         .getWorldRenderer6(0.0, offset13)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset8, posY + (double)count2, posZ + offset9)
         .getWorldRenderer6(1.0, offset13)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset8, posY + (double)0, posZ + offset9)
         .getWorldRenderer6(1.0, offset12)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset4, posY + (double)0, posZ + offset5)
         .getWorldRenderer6(0.0, offset12)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + offset4, posY + (double)count2, posZ + offset5)
         .getWorldRenderer6(0.0, offset13)
         .resolveWorldRenderer(factor2, factor3, factor4, 1.0F)
         .updateState();
      tessellator.updateState();
      GlStateManager.disableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.depthMask(false);
      double offset14 = -1.0 + offset2;
      double offset15 = (double)((float)count) + offset14;
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry6);
      worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)count2, posZ + 0.2)
         .getWorldRenderer6(1.0, offset15)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)0, posZ + 0.2)
         .getWorldRenderer6(1.0, offset14)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)0, posZ + 0.2)
         .getWorldRenderer6(0.0, offset14)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)count2, posZ + 0.2)
         .getWorldRenderer6(0.0, offset15)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)count2, posZ + 0.8)
         .getWorldRenderer6(1.0, offset15)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)0, posZ + 0.8)
         .getWorldRenderer6(1.0, offset14)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)0, posZ + 0.8)
         .getWorldRenderer6(0.0, offset14)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)count2, posZ + 0.8)
         .getWorldRenderer6(0.0, offset15)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)count2, posZ + 0.2)
         .getWorldRenderer6(1.0, offset15)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)0, posZ + 0.2)
         .getWorldRenderer6(1.0, offset14)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)0, posZ + 0.8)
         .getWorldRenderer6(0.0, offset14)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.8, posY + (double)count2, posZ + 0.8)
         .getWorldRenderer6(0.0, offset15)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)count2, posZ + 0.8)
         .getWorldRenderer6(1.0, offset15)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)0, posZ + 0.8)
         .getWorldRenderer6(1.0, offset14)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)0, posZ + 0.2)
         .getWorldRenderer6(0.0, offset14)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      worldRenderer.getWorldRenderer(posX + 0.2, posY + (double)count2, posZ + 0.2)
         .getWorldRenderer6(0.0, offset15)
         .resolveWorldRenderer(factor2, factor3, factor4, 0.125F)
         .updateState();
      tessellator.updateState();
      GlStateManager.applyState13();
      GlStateManager.disableTexture2D();
      GlStateManager.depthMask(true);
      GlStateManager.applyState19();
      if (BuildManager.canExecute()) {
         PreEndHandManager.updateState66();
      }
   }

   @Override
   public boolean isActive() {
      return true;
   }
}
