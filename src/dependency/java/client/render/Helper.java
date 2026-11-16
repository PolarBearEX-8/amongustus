package client.render;

import client.util.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;

public class Helper {
   public static void applyFloat(float posX, float posX2, float posY, float posZ) {
      float factor = 0.0F;
      float factor2 = 0.0F;
      float factor3 = posY - posX;
      float factor4 = posZ - posX2;
      float factor5 = 0.00390625F;
      float factor6 = 0.00390625F;
      Tessellator tessellator = Tessellator.getTessellator();
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      worldRenderer.updateAuxiliaryLoggerEntry(7, DefaultVertexFormats.auxiliaryLoggerEntry);
      worldRenderer.getWorldRenderer((double)posX + 0.0, (double)(posX2 + factor4), 0.0)
         .getWorldRenderer6((double)((float)((double)factor + 0.0) * factor5), (double)((factor2 + factor4) * factor6))
         .updateState();
      worldRenderer.getWorldRenderer((double)(posX + factor3), (double)(posX2 + factor4), 0.0)
         .getWorldRenderer6((double)((factor + factor3) * factor5), (double)((factor2 + factor4) * factor6))
         .updateState();
      worldRenderer.getWorldRenderer((double)(posX + factor3), (double)posX2 + 0.0, 0.0)
         .getWorldRenderer6((double)((factor + factor3) * factor5), (double)((float)((double)factor2 + 0.0) * factor6))
         .updateState();
      worldRenderer.getWorldRenderer((double)posX + 0.0, (double)posX2 + 0.0, 0.0)
         .getWorldRenderer6((double)((float)((double)factor + 0.0) * factor5), (double)((float)((double)factor2 + 0.0) * factor6))
         .updateState();
      tessellator.updateState();
   }

   public static void applyFloat(float posX, float posX2, float posX3, float posX4, float posX5, float posY, float posZ, int count) {
      float factor = posX5 / posX3;
      float factor2 = posY / posX4;
      GlStateManager.pushMatrix();
      GlStateManager.translate3d((double)factor, (double)factor2, 0.0);
      if (posZ < 1.0F || count != -1) {
         GlStateManager.applyState14();
         GlStateManager.disableBlend();
         if (count != -1) {
            GlStateManager.color4f2((float)(count >> 16 & 0xFF) / 255.0F, (float)(count >> 8 & 0xFF) / 255.0F, (float)(count & 0xFF) / 255.0F, posZ);
         } else {
            GlStateManager.color4f2(1.0F, 1.0F, 1.0F, posZ);
         }
      }

      applyFloat(posX / factor, posX2 / factor2, posX / factor + posX3, posX2 / factor2 + posX4);
      if (posZ < 1.0F || count != -1) {
         GlStateManager.color4f2(1.0F, 1.0F, 1.0F, 1.0F);
      }

      GlStateManager.popMatrix();
   }
}
