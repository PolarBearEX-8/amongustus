package net.augustus.cosmetics.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class CosmeticOverlayDrawer {

   public static void drawTexturedQuad(float f, float f2, float f3, float f4) {
      float f5 = 0.0F;
      float f6 = 0.0F;
      float f7 = f3 - f;
      float f8 = f4 - f2;
      float f9 = 0.00390625F;
      float f10 = 0.00390625F;
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos((double)f + 0.0, (double)(f2 + f8), 0.0)
         .tex((double)((float)((double)f5 + 0.0) * f9), (double)((f6 + f8) * f10))
         .endVertex();
      worldRenderer.pos((double)(f + f7), (double)(f2 + f8), 0.0).tex((double)((f5 + f7) * f9), (double)((f6 + f8) * f10)).endVertex();
      worldRenderer.pos((double)(f + f7), (double)f2 + 0.0, 0.0).tex((double)((f5 + f7) * f9), (double)((float)((double)f6 + 0.0) * f10)).endVertex();
      worldRenderer.pos((double)f + 0.0, (double)f2 + 0.0, 0.0)
         .tex((double)((float)((double)f5 + 0.0) * f9), (double)((float)((double)f6 + 0.0) * f10))
         .endVertex();
      instance.draw();
   }

   public static void drawScaledOverlay(float f, float f2, float f3, float f4, float f5, float f6, float f7, int n) {
      float f8 = f5 / f3;
      float f9 = f6 / f4;
      GlStateManager.pushMatrix();
      GlStateManager.scale((double)f8, (double)f9, 0.0);
      if (f7 < 1.0F || n != -1) {
         GlStateManager.enableAlpha();
         GlStateManager.enableBlend();
         if (n != -1) {
            GlStateManager.color((float)(n >> 16 & 0xFF) / 255.0F, (float)(n >> 8 & 0xFF) / 255.0F, (float)(n & 0xFF) / 255.0F, f7);
         } else {
            GlStateManager.color(1.0F, 1.0F, 1.0F, f7);
         }
      }

      drawTexturedQuad(f / f8, f2 / f9, f / f8 + f3, f2 / f9 + f4);
      if (f7 < 1.0F || n != -1) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      }

      GlStateManager.popMatrix();
   }
}
