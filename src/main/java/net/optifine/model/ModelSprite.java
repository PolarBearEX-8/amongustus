package net.optifine.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelSprite {
   private ModelRenderer modelRenderer = null;
   private int textureOffsetX = 0;
   private int textureOffsetY = 0;
   private float posX = 0.0F;
   private float posY = 0.0F;
   private float posZ = 0.0F;
   private int sizeX = 0;
   private int sizeY = 0;
   private int sizeZ = 0;
   private float sizeAdd = 0.0F;
   private float minU = 0.0F;
   private float minV = 0.0F;
   private float maxU = 0.0F;
   private float maxV = 0.0F;

   public void render(Tessellator tessellator, float f) {
      GlStateManager.translate(this.posX * f, this.posY * f, this.posZ * f);
      float minU2 = this.minU;
      float maxU2 = this.maxU;
      float minV2 = this.minV;
      float maxV2 = this.maxV;
      if (this.modelRenderer.mirror) {
         minU2 = this.maxU;
         maxU2 = this.minU;
      }

      if (this.modelRenderer.mirrorV) {
         minV2 = this.maxV;
         maxV2 = this.minV;
      }

      renderItemIn2D(tessellator, minU2, minV2, maxU2, maxV2, this.sizeX, this.sizeY, f * (float)this.sizeZ, this.modelRenderer.textureWidth, this.modelRenderer.textureHeight);
      GlStateManager.translate(-this.posX * f, -this.posY * f, -this.posZ * f);
   }

   public static void renderItemIn2D(Tessellator tessellator, float f, float f2, float f3, float f4, int n, int n2, float f5, float f6, float f7) {
      if (f5 < 6.25E-4F) {
         f5 = 6.25E-4F;
      }

      float f8 = f3 - f;
      float f9 = f4 - f2;
      double d = (double)(MathHelper.abs(f8) * (f6 / 16.0F));
      double d2 = (double)(MathHelper.abs(f9) * (f7 / 16.0F));
      WorldRenderer worldRenderer = tessellator.getWorldRenderer();
      GL11.glNormal3f(0.0F, 0.0F, -1.0F);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos(0.0, d2, 0.0).tex((double)f, (double)f4).endVertex();
      worldRenderer.pos(d, d2, 0.0).tex((double)f3, (double)f4).endVertex();
      worldRenderer.pos(d, 0.0, 0.0).tex((double)f3, (double)f2).endVertex();
      worldRenderer.pos(0.0, 0.0, 0.0).tex((double)f, (double)f2).endVertex();
      tessellator.draw();
      GL11.glNormal3f(0.0F, 0.0F, 1.0F);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos(0.0, 0.0, (double)f5).tex((double)f, (double)f2).endVertex();
      worldRenderer.pos(d, 0.0, (double)f5).tex((double)f3, (double)f2).endVertex();
      worldRenderer.pos(d, d2, (double)f5).tex((double)f3, (double)f4).endVertex();
      worldRenderer.pos(0.0, d2, (double)f5).tex((double)f, (double)f4).endVertex();
      tessellator.draw();
      float f10 = 0.5F * f8 / (float)n;
      float f11 = 0.5F * f9 / (float)n2;
      GL11.glNormal3f(-1.0F, 0.0F, 0.0F);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);

      for (int i = 0; i < n; i++) {
         float f12 = (float)i / (float)n;
         float f13 = f + f8 * f12 + f10;
         worldRenderer.pos((double)f12 * d, d2, (double)f5).tex((double)f13, (double)f4).endVertex();
         worldRenderer.pos((double)f12 * d, d2, 0.0).tex((double)f13, (double)f4).endVertex();
         worldRenderer.pos((double)f12 * d, 0.0, 0.0).tex((double)f13, (double)f2).endVertex();
         worldRenderer.pos((double)f12 * d, 0.0, (double)f5).tex((double)f13, (double)f2).endVertex();
      }

      tessellator.draw();
      GL11.glNormal3f(1.0F, 0.0F, 0.0F);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);

      for (int j = 0; j < n; j++) {
         float f14 = (float)j / (float)n;
         float f15 = f + f8 * f14 + f10;
         float f16 = f14 + 1.0F / (float)n;
         worldRenderer.pos((double)f16 * d, 0.0, (double)f5).tex((double)f15, (double)f2).endVertex();
         worldRenderer.pos((double)f16 * d, 0.0, 0.0).tex((double)f15, (double)f2).endVertex();
         worldRenderer.pos((double)f16 * d, d2, 0.0).tex((double)f15, (double)f4).endVertex();
         worldRenderer.pos((double)f16 * d, d2, (double)f5).tex((double)f15, (double)f4).endVertex();
      }

      tessellator.draw();
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);

      for (int k = 0; k < n2; k++) {
         float f17 = (float)k / (float)n2;
         float f18 = f2 + f9 * f17 + f11;
         float f19 = f17 + 1.0F / (float)n2;
         worldRenderer.pos(0.0, (double)f19 * d2, (double)f5).tex((double)f, (double)f18).endVertex();
         worldRenderer.pos(d, (double)f19 * d2, (double)f5).tex((double)f3, (double)f18).endVertex();
         worldRenderer.pos(d, (double)f19 * d2, 0.0).tex((double)f3, (double)f18).endVertex();
         worldRenderer.pos(0.0, (double)f19 * d2, 0.0).tex((double)f, (double)f18).endVertex();
      }

      tessellator.draw();
      GL11.glNormal3f(0.0F, -1.0F, 0.0F);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);

      for (int m = 0; m < n2; m++) {
         float f20 = (float)m / (float)n2;
         float f21 = f2 + f9 * f20 + f11;
         worldRenderer.pos(d, (double)f20 * d2, (double)f5).tex((double)f3, (double)f21).endVertex();
         worldRenderer.pos(0.0, (double)f20 * d2, (double)f5).tex((double)f, (double)f21).endVertex();
         worldRenderer.pos(0.0, (double)f20 * d2, 0.0).tex((double)f, (double)f21).endVertex();
         worldRenderer.pos(d, (double)f20 * d2, 0.0).tex((double)f3, (double)f21).endVertex();
      }

      tessellator.draw();
   }

   public ModelSprite(ModelRenderer modelRenderer2, int n, int n2, float f, float f2, float f3, int n3, int n4, int n5, float f4) {
      this.modelRenderer = modelRenderer2;
      this.textureOffsetX = n;
      this.textureOffsetY = n2;
      this.posX = f;
      this.posY = f2;
      this.posZ = f3;
      this.sizeX = n3;
      this.sizeY = n4;
      this.sizeZ = n5;
      this.sizeAdd = f4;
      this.minU = (float)n / modelRenderer2.textureWidth;
      this.minV = (float)n2 / modelRenderer2.textureHeight;
      this.maxU = (float)(n + n3) / modelRenderer2.textureWidth;
      this.maxV = (float)(n2 + n4) / modelRenderer2.textureHeight;
   }
}
