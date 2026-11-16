package client.util;

import client.event.PreMotionDispatcher;
import client.render.WorldRenderer;
import java.util.Random;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityEnderdragon;
import net.minecraft.entity.EntityLivingBase;

public class AimSynchronizer implements PreMotionDispatcher {
   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      this.applyEntityEnderdragon((EntityEnderdragon)livingEntity, posX, posX2, posX3, posX4, posX5, posY, posZ);
   }

   @Override
   public boolean isActive() {
      return false;
   }

   public void applyEntityEnderdragon(EntityEnderdragon entityEnderdragon, float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ) {
      if (entityEnderdragon.count28 > 0) {
         Tessellator tessellator = Tessellator.getTessellator();
         WorldRenderer worldRenderer = tessellator.getWorldRenderer();
         RenderHelper.disableStandardItemLighting();
         float factor = ((float)entityEnderdragon.count28 + posX3) / 200.0F;
         float factor2 = 0.0F;
         if (factor > 0.8F) {
            factor2 = (factor - 0.8F) / 0.2F;
         }

         Random random = new Random(432L);
         GlStateManager.enableTexture2D();
         GlStateManager.glShadeModel(7425);
         GlStateManager.disableBlend();
         GlStateManager.glStateAction3(770, 1);
         GlStateManager.applyState10();
         GlStateManager.enableLighting();
         GlStateManager.depthMask(false);
         GlStateManager.pushMatrix();
         GlStateManager.translate(0.0F, -1.0F, -2.0F);

         for (int index = 0; (float)index < (factor + factor * factor) / 2.0F * 60.0F; index++) {
            GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(random.nextFloat() * 360.0F + factor * 90.0F, 0.0F, 0.0F, 1.0F);
            float factor3 = random.nextFloat() * 20.0F + 5.0F + factor2 * 10.0F;
            float factor4 = random.nextFloat() * 2.0F + 1.0F + factor2 * 2.0F;
            worldRenderer.updateAuxiliaryLoggerEntry(6, DefaultVertexFormats.auxiliaryLoggerEntry3);
            worldRenderer.getWorldRenderer(0.0, 0.0, 0.0).resolveWorldRenderer(255, 255, 255, (int)(255.0F * (1.0F - factor2))).updateState();
            worldRenderer.getWorldRenderer(-0.866 * (double)factor4, (double)factor3, (double)(-0.5F * factor4)).resolveWorldRenderer(255, 0, 255, 0).updateState();
            worldRenderer.getWorldRenderer(0.866 * (double)factor4, (double)factor3, (double)(-0.5F * factor4)).resolveWorldRenderer(255, 0, 255, 0).updateState();
            worldRenderer.getWorldRenderer(0.0, (double)factor3, (double)(1.0F * factor4)).resolveWorldRenderer(255, 0, 255, 0).updateState();
            worldRenderer.getWorldRenderer(-0.866 * (double)factor4, (double)factor3, (double)(-0.5F * factor4)).resolveWorldRenderer(255, 0, 255, 0).updateState();
            tessellator.updateState();
         }

         GlStateManager.popMatrix();
         GlStateManager.depthMask(true);
         GlStateManager.disableAlpha();
         GlStateManager.enableBlend();
         GlStateManager.glShadeModel(7424);
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableTexture2D();
         GlStateManager.applyState14();
         RenderHelper.enableStandardItemLighting();
      }
   }
}
