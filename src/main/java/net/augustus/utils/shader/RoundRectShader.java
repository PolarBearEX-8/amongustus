package net.augustus.utils.shader;

import net.augustus.utils.render.RenderSystemAssetLoader;
import java.awt.Color;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class RoundRectShader {
   private boolean wasBlendEnabled;
   private boolean wasTexture2DEnabled;
   private final ShaderProgram shaderProgram;
   private boolean wasAlphaEnabled;
   private static RoundRectShader instance;

   public void drawRoundedRect(float f, float f2, float f3, float f4, float f5, int n) {
      this.pushGlState();
      this.shaderProgram.bind();
      String str = "size";
      this.shaderProgram.setUniform2f(str, f3, f4);
      String str2 = "radius";
      this.shaderProgram.setUniform1f(str2, f5);
      float f6 = 0.0F;
      float f7 = 0.0F;
      float f8 = 0.0F;
      float f9 = 0.0F;
      String str3 = "cornerRadius";
      this.shaderProgram.setUniform4f(str3, f9, f8, f7, f6);
      this.setColorUniform(n);
      String str4 = "position";
      this.shaderProgram.setUniform2f(str4, f, f2);
      String str5 = "outline";
      this.shaderProgram.setUniform1i(str5, (byte)0);
      float f10 = 0.0F;
      String str6 = "lineWidth";
      this.shaderProgram.setUniform1f(str6, f10);
      this.drawQuad(f, f2, f3, f4);
      this.shaderProgram.unbind();
      this.popGlState();
   }

   public void drawRoundedRect(
      float f, float f2, float f3, float f4, float f5, boolean flag, boolean flag2, boolean flag3, boolean flag4, Color color
   ) {
      this.drawRoundedRect(f, f2, f3, f4, f5, flag, flag2, flag3, flag4, color.getRGB());
   }

   private void pushGlState() {
      this.wasBlendEnabled = GlStateManager.isBlendEnabled();
      this.wasTexture2DEnabled = GlStateManager.isTexture2DEnabled();
      this.wasAlphaEnabled = GlStateManager.isAlphaEnabled();
      GlStateManager.enableAlpha();
      GlStateManager.enableBlend();
      GlStateManager.disableTexture2D();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 1);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public void drawRoundedOutline(
      float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, int n
   ) {
      this.pushGlState();
      this.shaderProgram.bind();
      String str = "size";
      this.shaderProgram.setUniform2f(str, f3, f4);
      float f10 = 0.0F;
      String str2 = "radius";
      this.shaderProgram.setUniform1f(str2, f10);
      String str3 = "cornerRadius";
      this.shaderProgram.setUniform4f(str3, f6, f7, f8, f9);
      this.setColorUniform(n);
      String str4 = "position";
      this.shaderProgram.setUniform2f(str4, f, f2);
      String str5 = "outline";
      this.shaderProgram.setUniform1i(str5, (byte)1);
      String str6 = "lineWidth";
      this.shaderProgram.setUniform1f(str6, f5);
      this.drawQuad(f, f2, f3, f4);
      this.shaderProgram.unbind();
      this.popGlState();
   }

   public void drawRoundedOutline(
      float f, float f2, float f3, float f4, float f5, float f6, boolean flag, boolean flag2, boolean flag3, boolean flag4, int n
   ) {
      this.pushGlState();
      this.shaderProgram.bind();
      String str = "size";
      this.shaderProgram.setUniform2f(str, f3, f4);
      float f7 = 0.0F;
      String str2 = "radius";
      this.shaderProgram.setUniform1f(str2, f7);
      float f8 = flag ? f5 : 0.0F;
      float f9 = flag2 ? f5 : 0.0F;
      float f10 = flag3 ? f5 : 0.0F;
      float f11 = flag4 ? f5 : 0.0F;
      String str3 = "cornerRadius";
      this.shaderProgram.setUniform4f(str3, f8, f9, f10, f11);
      this.setColorUniform(n);
      String str4 = "position";
      this.shaderProgram.setUniform2f(str4, f, f2);
      String str5 = "outline";
      this.shaderProgram.setUniform1i(str5, (byte)1);
      String str6 = "lineWidth";
      this.shaderProgram.setUniform1f(str6, f6);
      this.drawQuad(f, f2, f3, f4);
      this.shaderProgram.unbind();
      this.popGlState();
   }

   public void drawRoundedRect(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, int n) {
      this.pushGlState();
      this.shaderProgram.bind();
      String str = "size";
      this.shaderProgram.setUniform2f(str, f3, f4);
      float f9 = 0.0F;
      String str2 = "radius";
      this.shaderProgram.setUniform1f(str2, f9);
      String str3 = "cornerRadius";
      this.shaderProgram.setUniform4f(str3, f5, f6, f7, f8);
      this.setColorUniform(n);
      String str4 = "position";
      this.shaderProgram.setUniform2f(str4, f, f2);
      String str5 = "outline";
      this.shaderProgram.setUniform1i(str5, (byte)0);
      float f10 = 0.0F;
      String str6 = "lineWidth";
      this.shaderProgram.setUniform1f(str6, f10);
      this.drawQuad(f, f2, f3, f4);
      this.shaderProgram.unbind();
      this.popGlState();
   }

   public static RoundRectShader getInstance() {
      return instance;
   }

   private RoundRectShader() {
       super();

      try {
         this.shaderProgram = new ShaderProgram(RenderSystemAssetLoader.loadAssetString("roundrect.vert"), RenderSystemAssetLoader.loadAssetString("roundrect.frag"));
         System.out.println("[RoundRectShader] Shader loaded successfully!");
      } catch (Exception e) {
         System.err.println("[RoundRectShader] Failed to load shader:");
         e.printStackTrace();
         throw new RuntimeException("Failed to initialize RoundRectShader", e);
      }
   }

   private void popGlState() {
      if (this.wasTexture2DEnabled) {
         GlStateManager.enableTexture2D();
      }

      if (!this.wasAlphaEnabled) {
         GlStateManager.disableAlpha();
      }

      if (!this.wasBlendEnabled) {
         GlStateManager.disableBlend();
      }
   }

   static {
      instance = new RoundRectShader();
   }

   public void drawRoundedOutline(
      float f, float f2, float f3, float f4, float f5, float f6, boolean flag, boolean flag2, boolean flag3, boolean flag4, Color color
   ) {
      this.drawRoundedOutline(f, f2, f3, f4, f5, f6, flag, flag2, flag3, flag4, color.getRGB());
   }

   public void drawRoundedRect(
      float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, Color color
   ) {
      this.drawRoundedRect(f, f2, f3, f4, f5, f6, f7, f8, color.getRGB());
   }

   private void setColorUniform(int colorUniform) {
      float f = (float)(colorUniform >> 24 & 0xFF) / 255.0F;
      float f2 = (float)(colorUniform >> 16 & 0xFF) / 255.0F;
      float f3 = (float)(colorUniform >> 8 & 0xFF) / 255.0F;
      float f4 = (float)(colorUniform & 0xFF) / 255.0F;
      if (f == 0.0F && colorUniform != 0) {
         f = 1.0F;
      }

      String str = "color_rg";
      this.shaderProgram.setUniform2f(str, f2, f3);
      String str2 = "color_ba";
      this.shaderProgram.setUniform2f(str2, f4, f);
   }

   private void drawQuad(float f, float f2, float f3, float f4) {
      Tessellator instance2 = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance2.getWorldRenderer();
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos((double)f, (double)(f2 + f4), 0.0).tex(0.0, 1.0).endVertex();
      worldRenderer.pos((double)(f + f3), (double)(f2 + f4), 0.0).tex(1.0, 1.0).endVertex();
      worldRenderer.pos((double)(f + f3), (double)f2, 0.0).tex(1.0, 0.0).endVertex();
      worldRenderer.pos((double)f, (double)f2, 0.0).tex(0.0, 0.0).endVertex();
      instance2.draw();
   }

   public void drawRoundedOutline(float f, float f2, float f3, float f4, float f5, float f6, int n) {
      this.pushGlState();
      this.shaderProgram.bind();
      String str = "size";
      this.shaderProgram.setUniform2f(str, f3, f4);
      String str2 = "radius";
      this.shaderProgram.setUniform1f(str2, f5);
      float f7 = 0.0F;
      float f8 = 0.0F;
      float f9 = 0.0F;
      float f10 = 0.0F;
      String str3 = "cornerRadius";
      this.shaderProgram.setUniform4f(str3, f10, f9, f8, f7);
      this.setColorUniform(n);
      String str4 = "position";
      this.shaderProgram.setUniform2f(str4, f, f2);
      String str5 = "outline";
      this.shaderProgram.setUniform1i(str5, (byte)1);
      String str6 = "lineWidth";
      this.shaderProgram.setUniform1f(str6, f6);
      this.drawQuad(f, f2, f3, f4);
      this.shaderProgram.unbind();
      this.popGlState();
   }

   public void drawRoundedOutline(float f, float f2, float f3, float f4, float f5, float f6, Color color) {
      this.drawRoundedOutline(f, f2, f3, f4, f5, f6, color.getRGB());
   }

   public void drawRoundedOutline(float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, Color color) {
      this.drawRoundedOutline(f, f2, f3, f4, f5, f6, f7, f8, f9, color.getRGB());
   }

   public void drawRoundedRect(float f, float f2, float f3, float f4, float f5, Color color) {
      this.drawRoundedRect(f, f2, f3, f4, f5, color.getRGB());
   }

   public void drawRoundedRect(float f, float f2, float f3, float f4, float f5, boolean flag, boolean flag2, boolean flag3, boolean flag4, int n) {
      this.pushGlState();
      this.shaderProgram.bind();
      String str = "size";
      this.shaderProgram.setUniform2f(str, f3, f4);
      float f6 = 0.0F;
      String str2 = "radius";
      this.shaderProgram.setUniform1f(str2, f6);
      float f7 = flag ? f5 : 0.0F;
      float f8 = flag2 ? f5 : 0.0F;
      float f9 = flag3 ? f5 : 0.0F;
      float f10 = flag4 ? f5 : 0.0F;
      String str3 = "cornerRadius";
      this.shaderProgram.setUniform4f(str3, f7, f8, f9, f10);
      this.setColorUniform(n);
      String str4 = "position";
      this.shaderProgram.setUniform2f(str4, f, f2);
      String str5 = "outline";
      this.shaderProgram.setUniform1i(str5, (byte)0);
      float f14 = 0.0F;
      String str6 = "lineWidth";
      this.shaderProgram.setUniform1f(str6, f14);
      this.drawQuad(f, f2, f3, f4);
      this.shaderProgram.unbind();
      this.popGlState();
   }



}
