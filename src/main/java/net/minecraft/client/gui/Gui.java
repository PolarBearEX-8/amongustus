package net.minecraft.client.gui;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class Gui {
   public static final ResourceLocation optionsBackground = new ResourceLocation("textures/gui/options_background.png");
   public static final ResourceLocation statIcons = new ResourceLocation("textures/gui/container/stats_icons.png");
   public static final ResourceLocation icons = new ResourceLocation("textures/gui/icons.png");
   public float zLevel;

   public void drawTexturedModalRect(int n, int n2, TextureAtlasSprite textureAtlasSprite, int n3, int n4) {
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos((double)(n + 0), (double)(n2 + n4), (double)this.zLevel).tex((double)textureAtlasSprite.getMinU(), (double)textureAtlasSprite.getMaxV()).endVertex();
      worldRenderer.pos((double)(n + n3), (double)(n2 + n4), (double)this.zLevel).tex((double)textureAtlasSprite.getMaxU(), (double)textureAtlasSprite.getMaxV()).endVertex();
      worldRenderer.pos((double)(n + n3), (double)(n2 + 0), (double)this.zLevel).tex((double)textureAtlasSprite.getMaxU(), (double)textureAtlasSprite.getMinV()).endVertex();
      worldRenderer.pos((double)(n + 0), (double)(n2 + 0), (double)this.zLevel).tex((double)textureAtlasSprite.getMinU(), (double)textureAtlasSprite.getMinV()).endVertex();
      instance.draw();
   }

   public static void drawCenteredStringRaw(FontRenderer fontRenderer, String str, int n, int n2, int n3) {
      fontRenderer.renderString(str, (float)(n - fontRenderer.getStringWidth(str) / 2), (float)n2, n3, true);
   }

   public static void drawModalRectWithCustomSizedTexture(int n, int n2, float f, float f2, int n3, int n4, float f3, float f4) {
      float f5 = 1.0F / f3;
      float f6 = 1.0F / f4;
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos((double)n, (double)(n2 + n4), 0.0).tex((double)(f * f5), (double)((f2 + (float)n4) * f6)).endVertex();
      worldRenderer.pos((double)(n + n3), (double)(n2 + n4), 0.0)
         .tex((double)((f + (float)n3) * f5), (double)((f2 + (float)n4) * f6))
         .endVertex();
      worldRenderer.pos((double)(n + n3), (double)n2, 0.0).tex((double)((f + (float)n3) * f5), (double)(f2 * f6)).endVertex();
      worldRenderer.pos((double)n, (double)n2, 0.0).tex((double)(f * f5), (double)(f2 * f6)).endVertex();
      instance.draw();
   }

   public static void drawScaledCustomSizeModalRect(int n, int n2, float f, float f2, int n3, int n4, int n5, int n6, float f3, float f4) {
      float f5 = 1.0F / f3;
      float f6 = 1.0F / f4;
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos((double)n, (double)(n2 + n6), 0.0).tex((double)(f * f5), (double)((f2 + (float)n4) * f6)).endVertex();
      worldRenderer.pos((double)(n + n5), (double)(n2 + n6), 0.0)
         .tex((double)((f + (float)n3) * f5), (double)((f2 + (float)n4) * f6))
         .endVertex();
      worldRenderer.pos((double)(n + n5), (double)n2, 0.0).tex((double)((f + (float)n3) * f5), (double)(f2 * f6)).endVertex();
      worldRenderer.pos((double)n, (double)n2, 0.0).tex((double)(f * f5), (double)(f2 * f6)).endVertex();
      instance.draw();
   }

   public void drawVerticalLine(int n, int n2, int n3, int n4) {
      if (n3 < n2) {
         int n5 = n2;
         n2 = n3;
         n3 = n5;
      }

      drawRect(n, n2 + 1, n + 1, n3, n4);
   }

   public void drawTexturedModalRect(int n, int n2, int n3, int n4, int n5, int n6) {
      float f = 0.00390625F;
      float f2 = 0.00390625F;
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos((double)(n + 0), (double)(n2 + n6), (double)this.zLevel)
         .tex((double)((float)(n3 + 0) * f), (double)((float)(n4 + n6) * f2))
         .endVertex();
      worldRenderer.pos((double)(n + n5), (double)(n2 + n6), (double)this.zLevel)
         .tex((double)((float)(n3 + n5) * f), (double)((float)(n4 + n6) * f2))
         .endVertex();
      worldRenderer.pos((double)(n + n5), (double)(n2 + 0), (double)this.zLevel)
         .tex((double)((float)(n3 + n5) * f), (double)((float)(n4 + 0) * f2))
         .endVertex();
      worldRenderer.pos((double)(n + 0), (double)(n2 + 0), (double)this.zLevel)
         .tex((double)((float)(n3 + 0) * f), (double)((float)(n4 + 0) * f2))
         .endVertex();
      instance.draw();
   }

   public void drawHorizontalLine(int n, int n2, int n3, int n4) {
      if (n2 < n) {
         int n5 = n;
         n = n2;
         n2 = n5;
      }

      drawRect(n, n3, n2 + 1, n3 + 1, n4);
   }

   public static void drawCenteredString(FontRenderer fontRenderer, String str, int n, int n2, int n3) {
      fontRenderer.drawStringWithShadow(str, (float)(n - fontRenderer.getStringWidth(str) / 2), (float)n2, n3);
   }

   public void drawString(FontRenderer fontRenderer, String str, int n, int n2, int n3) {
      fontRenderer.drawStringWithShadow(str, (float)n, (float)n2, n3);
   }

   public static void drawRect(int n, int n2, int n3, int n4, int n5) {
      if (n < n3) {
         int n6 = n;
         n = n3;
         n3 = n6;
      }

      if (n2 < n4) {
         int n7 = n2;
         n2 = n4;
         n4 = n7;
      }

      float f = (float)(n5 >> 24 & 0xFF) / 255.0F;
      float f2 = (float)(n5 >> 16 & 0xFF) / 255.0F;
      float f3 = (float)(n5 >> 8 & 0xFF) / 255.0F;
      float f4 = (float)(n5 & 0xFF) / 255.0F;
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(f2, f3, f4, f);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION);
      worldRenderer.pos((double)n, (double)n4, 0.0).endVertex();
      worldRenderer.pos((double)n3, (double)n4, 0.0).endVertex();
      worldRenderer.pos((double)n3, (double)n2, 0.0).endVertex();
      worldRenderer.pos((double)n, (double)n2, 0.0).endVertex();
      instance.draw();
      GlStateManager.enableTexture2D();
      GlStateManager.disableBlend();
   }

   public void drawTexturedModalRect(float f, float f2, int n, int n2, int n3, int n4) {
      float f3 = 0.00390625F;
      float f4 = 0.00390625F;
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos((double)(f + 0.0F), (double)(f2 + (float)n4), (double)this.zLevel)
         .tex((double)((float)(n + 0) * f3), (double)((float)(n2 + n4) * f4))
         .endVertex();
      worldRenderer.pos((double)(f + (float)n3), (double)(f2 + (float)n4), (double)this.zLevel)
         .tex((double)((float)(n + n3) * f3), (double)((float)(n2 + n4) * f4))
         .endVertex();
      worldRenderer.pos((double)(f + (float)n3), (double)(f2 + 0.0F), (double)this.zLevel)
         .tex((double)((float)(n + n3) * f3), (double)((float)(n2 + 0) * f4))
         .endVertex();
      worldRenderer.pos((double)(f + 0.0F), (double)(f2 + 0.0F), (double)this.zLevel)
         .tex((double)((float)(n + 0) * f3), (double)((float)(n2 + 0) * f4))
         .endVertex();
      instance.draw();
   }

   public void drawGradientRect(int n, int n2, int n3, int n4, int n5, int n6) {
      float f = (float)(n5 >> 24 & 0xFF) / 255.0F;
      float f2 = (float)(n5 >> 16 & 0xFF) / 255.0F;
      float f3 = (float)(n5 >> 8 & 0xFF) / 255.0F;
      float f4 = (float)(n5 & 0xFF) / 255.0F;
      float f5 = (float)(n6 >> 24 & 0xFF) / 255.0F;
      float f6 = (float)(n6 >> 16 & 0xFF) / 255.0F;
      float f7 = (float)(n6 >> 8 & 0xFF) / 255.0F;
      float f8 = (float)(n6 & 0xFF) / 255.0F;
      GlStateManager.disableTexture2D();
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.shadeModel(7425);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
      worldRenderer.pos((double)n3, (double)n2, (double)this.zLevel).color(f2, f3, f4, f).endVertex();
      worldRenderer.pos((double)n, (double)n2, (double)this.zLevel).color(f2, f3, f4, f).endVertex();
      worldRenderer.pos((double)n, (double)n4, (double)this.zLevel).color(f6, f7, f8, f5).endVertex();
      worldRenderer.pos((double)n3, (double)n4, (double)this.zLevel).color(f6, f7, f8, f5).endVertex();
      instance.draw();
      GlStateManager.shadeModel(7424);
      GlStateManager.disableBlend();
      GlStateManager.enableAlpha();
      GlStateManager.enableTexture2D();
   }
}
