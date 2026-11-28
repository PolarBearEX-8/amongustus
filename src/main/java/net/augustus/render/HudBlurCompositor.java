package net.augustus.render;

import net.augustus.utils.render.BlurShadowManager;
import net.augustus.utils.interfaces.MC;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;

public final class HudBlurCompositor implements MC {

   public static void beginHudCapture() {
      if (BlurShadowManager.isShadersCompatible()) {
         BlurShadowManager.beginHudComposition();
         GlStateManager.clearColor(0.0F, 0.0F, 0.0F, 0.0F);
         mc.getFramebuffer().bindFramebuffer(true);
         GlStateManager.clear(16640);
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 771);
      }
   }

   private static void drawHudFramebufferOverlay(ScaledResolution sr) {
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(1, 771);
      GlStateManager.disableDepth();
      GlStateManager.disableAlpha();
      GlStateManager.disableTexture2D();
      GlStateManager.enableTexture2D();
      Framebuffer blurFramebuffer = mc.blurFramebuffer;
      blurFramebuffer.bindFramebufferTexture();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      float f = (float)blurFramebuffer.framebufferWidth / (float)blurFramebuffer.framebufferTextureWidth;
      float f2 = (float)blurFramebuffer.framebufferHeight / (float)blurFramebuffer.framebufferTextureHeight;
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      double scaledWidthDouble = sr.getScaledWidth_double();
      double scaledHeightDouble = sr.getScaledHeight_double();
      worldRenderer.pos(0.0, scaledHeightDouble, 0.0).tex(0.0, 0.0).endVertex();
      worldRenderer.pos(scaledWidthDouble, scaledHeightDouble, 0.0).tex((double)f, 0.0).endVertex();
      worldRenderer.pos(scaledWidthDouble, 0.0, 0.0).tex((double)f, (double)f2).endVertex();
      worldRenderer.pos(0.0, 0.0, 0.0).tex(0.0, (double)f2).endVertex();
      instance.draw();
      blurFramebuffer.unbindFramebufferTexture();
      GlStateManager.enableDepth();
      GlStateManager.enableAlpha();
   }

   private HudBlurCompositor() {
   }

   public static void compositeHud(ScaledResolution sr, boolean flag, Runnable runnable) {
      BlurShadowManager.endHudComposition();
      if (!BlurShadowManager.isShadersCompatible()) {
         if (runnable != null) {
            runnable.run();
         }

         GlStateManager.enableDepth();
         GlStateManager.enableAlpha();
         GlStateManager.disableBlend();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      } else {
         BlurShadowManager.endHudComposition();
         mc.getFramebuffer().bindFramebuffer(true);
         BlurShadowManager.flushRenderQueues(sr, flag);
         mc.entityRenderer.setupOverlayRendering(sr);
         if (runnable != null) {
            runnable.run();
         }

         drawHudFramebufferOverlay(sr);
      }
   }
}
