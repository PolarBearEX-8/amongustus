package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderArrow extends Render {
   private static final ResourceLocation arrowTextures = new ResourceLocation("textures/entity/arrow.png");

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityArrow)entity);
   }

   public void doRender(EntityArrow entityArrow, double d, double d2, double d3, float f, float f2) {
      this.bindEntityTexture(entityArrow);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)d, (float)d2, (float)d3);
      GlStateManager.rotate(entityArrow.prevRotationYaw + (entityArrow.rotationYaw - entityArrow.prevRotationYaw) * f2 - 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(entityArrow.prevRotationPitch + (entityArrow.rotationPitch - entityArrow.prevRotationPitch) * f2, 0.0F, 0.0F, 1.0F);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      byte b = 0;
      float f3 = 0.0F;
      float f4 = 0.5F;
      float f5 = (float)(0 + b * 10) / 32.0F;
      float f6 = (float)(5 + b * 10) / 32.0F;
      float f7 = 0.0F;
      float f8 = 0.15625F;
      float f9 = (float)(5 + b * 10) / 32.0F;
      float f10 = (float)(10 + b * 10) / 32.0F;
      float f11 = 0.05625F;
      GlStateManager.enableRescaleNormal();
      float f12 = (float)entityArrow.arrowShake - f2;
      if (f12 > 0.0F) {
         GlStateManager.rotate(-MathHelper.sin(f12 * 3.0F) * f12, 0.0F, 0.0F, 1.0F);
      }

      GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.scale(f11, f11, f11);
      GlStateManager.translate(-4.0F, 0.0F, 0.0F);
      GL11.glNormal3f(f11, 0.0F, 0.0F);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos(-7.0, -2.0, -2.0).tex((double)f7, (double)f9).endVertex();
      worldRenderer.pos(-7.0, -2.0, 2.0).tex((double)f8, (double)f9).endVertex();
      worldRenderer.pos(-7.0, 2.0, 2.0).tex((double)f8, (double)f10).endVertex();
      worldRenderer.pos(-7.0, 2.0, -2.0).tex((double)f7, (double)f10).endVertex();
      instance.draw();
      GL11.glNormal3f(-f11, 0.0F, 0.0F);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
      worldRenderer.pos(-7.0, 2.0, -2.0).tex((double)f7, (double)f9).endVertex();
      worldRenderer.pos(-7.0, 2.0, 2.0).tex((double)f8, (double)f9).endVertex();
      worldRenderer.pos(-7.0, -2.0, 2.0).tex((double)f8, (double)f10).endVertex();
      worldRenderer.pos(-7.0, -2.0, -2.0).tex((double)f7, (double)f10).endVertex();
      instance.draw();

      for (int i = 0; i < 4; i++) {
         GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glNormal3f(0.0F, 0.0F, f11);
         worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
         worldRenderer.pos(-8.0, -2.0, 0.0).tex((double)f3, (double)f5).endVertex();
         worldRenderer.pos(8.0, -2.0, 0.0).tex((double)f4, (double)f5).endVertex();
         worldRenderer.pos(8.0, 2.0, 0.0).tex((double)f4, (double)f6).endVertex();
         worldRenderer.pos(-8.0, 2.0, 0.0).tex((double)f3, (double)f6).endVertex();
         instance.draw();
      }

      GlStateManager.disableRescaleNormal();
      GlStateManager.popMatrix();
      super.doRender(entityArrow, d, d2, d3, f, f2);
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityArrow)entity, d, d2, d3, f, f2);
   }

   public RenderArrow(RenderManager renderManager2) {
      super(renderManager2);
   }

   public ResourceLocation getEntityTexture(EntityArrow entityArrow) {
      return arrowTextures;
   }
}
