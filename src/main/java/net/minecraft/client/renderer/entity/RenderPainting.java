package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderPainting extends Render {
   private static final ResourceLocation KRISTOFFER_PAINTING_TEXTURE = new ResourceLocation("textures/painting/paintings_kristoffer_zetterstrand.png");

   private void setLightmap(EntityPainting entityPainting, float f, float f2) {
      int n = MathHelper.floor_double(entityPainting.posX);
      int n2 = MathHelper.floor_double(entityPainting.posY + (double)(f2 / 16.0F));
      int n3 = MathHelper.floor_double(entityPainting.posZ);
      EnumFacing facingDirection = entityPainting.facingDirection;
      if (facingDirection == EnumFacing.NORTH) {
         n = MathHelper.floor_double(entityPainting.posX + (double)(f / 16.0F));
      }

      if (facingDirection == EnumFacing.WEST) {
         n3 = MathHelper.floor_double(entityPainting.posZ - (double)(f / 16.0F));
      }

      if (facingDirection == EnumFacing.SOUTH) {
         n = MathHelper.floor_double(entityPainting.posX - (double)(f / 16.0F));
      }

      if (facingDirection == EnumFacing.EAST) {
         n3 = MathHelper.floor_double(entityPainting.posZ + (double)(f / 16.0F));
      }

      int combinedLight = this.renderManager.worldObj.getCombinedLight(n, n2, n3, 0);
      int n4 = combinedLight % 65536;
      int n5 = combinedLight / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)n4, (float)n5);
      GlStateManager.color(1.0F, 1.0F, 1.0F);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityPainting)entity);
   }

   public RenderPainting(RenderManager renderManager2) {
      super(renderManager2);
   }

   public void doRender(EntityPainting entityPainting, double d, double d2, double d3, float f, float f2) {
      GlStateManager.pushMatrix();
      GlStateManager.translate(d, d2, d3);
      GlStateManager.rotate(180.0F - f, 0.0F, 1.0F, 0.0F);
      GlStateManager.enableRescaleNormal();
      this.bindEntityTexture(entityPainting);
      EntityPainting.EnumArt art = entityPainting.art;
      float f3 = 0.0625F;
      GlStateManager.scale(f3, f3, f3);
      this.renderPainting(entityPainting, art.sizeX, art.sizeY, art.offsetX, art.offsetY);
      GlStateManager.disableRescaleNormal();
      GlStateManager.popMatrix();
      super.doRender(entityPainting, d, d2, d3, f, f2);
   }

   private void renderPainting(EntityPainting entityPainting, int n, int n2, int n3, int n4) {
      float f = (float)(-n) / 2.0F;
      float f2 = (float)(-n2) / 2.0F;
      float f3 = 0.5F;
      float f4 = 0.75F;
      float f5 = 0.8125F;
      float f6 = 0.0F;
      float f7 = 0.0625F;
      float f8 = 0.75F;
      float f9 = 0.8125F;
      float f10 = 0.001953125F;
      float f11 = 0.001953125F;
      float f12 = 0.7519531F;
      float f13 = 0.7519531F;
      float f14 = 0.0F;
      float f15 = 0.0625F;

      for (int i = 0; i < n / 16; i++) {
         for (int j = 0; j < n2 / 16; j++) {
            float f16 = f + (float)((i + 1) * 16);
            float f17 = f + (float)(i * 16);
            float f18 = f2 + (float)((j + 1) * 16);
            float f19 = f2 + (float)(j * 16);
            this.setLightmap(entityPainting, (f16 + f17) / 2.0F, (f18 + f19) / 2.0F);
            float f20 = (float)(n3 + n - i * 16) / 256.0F;
            float f21 = (float)(n3 + n - (i + 1) * 16) / 256.0F;
            float f22 = (float)(n4 + n2 - j * 16) / 256.0F;
            float f23 = (float)(n4 + n2 - (j + 1) * 16) / 256.0F;
            Tessellator instance = Tessellator.getInstance();
            WorldRenderer worldRenderer = instance.getWorldRenderer();
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
            worldRenderer.pos((double)f16, (double)f19, (double)(-f3)).tex((double)f21, (double)f22).normal(0.0F, 0.0F, -1.0F).endVertex();
            worldRenderer.pos((double)f17, (double)f19, (double)(-f3)).tex((double)f20, (double)f22).normal(0.0F, 0.0F, -1.0F).endVertex();
            worldRenderer.pos((double)f17, (double)f18, (double)(-f3)).tex((double)f20, (double)f23).normal(0.0F, 0.0F, -1.0F).endVertex();
            worldRenderer.pos((double)f16, (double)f18, (double)(-f3)).tex((double)f21, (double)f23).normal(0.0F, 0.0F, -1.0F).endVertex();
            worldRenderer.pos((double)f16, (double)f18, (double)f3).tex((double)f4, (double)f6).normal(0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)f17, (double)f18, (double)f3).tex((double)f5, (double)f6).normal(0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)f17, (double)f19, (double)f3).tex((double)f5, (double)f7).normal(0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)f16, (double)f19, (double)f3).tex((double)f4, (double)f7).normal(0.0F, 0.0F, 1.0F).endVertex();
            worldRenderer.pos((double)f16, (double)f18, (double)(-f3)).tex((double)f8, (double)f10).normal(0.0F, 1.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f17, (double)f18, (double)(-f3)).tex((double)f9, (double)f10).normal(0.0F, 1.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f17, (double)f18, (double)f3).tex((double)f9, (double)f11).normal(0.0F, 1.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f16, (double)f18, (double)f3).tex((double)f8, (double)f11).normal(0.0F, 1.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f16, (double)f19, (double)f3).tex((double)f8, (double)f10).normal(0.0F, -1.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f17, (double)f19, (double)f3).tex((double)f9, (double)f10).normal(0.0F, -1.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f17, (double)f19, (double)(-f3)).tex((double)f9, (double)f11).normal(0.0F, -1.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f16, (double)f19, (double)(-f3)).tex((double)f8, (double)f11).normal(0.0F, -1.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f16, (double)f18, (double)f3).tex((double)f13, (double)f14).normal(-1.0F, 0.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f16, (double)f19, (double)f3).tex((double)f13, (double)f15).normal(-1.0F, 0.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f16, (double)f19, (double)(-f3)).tex((double)f12, (double)f15).normal(-1.0F, 0.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f16, (double)f18, (double)(-f3)).tex((double)f12, (double)f14).normal(-1.0F, 0.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f17, (double)f18, (double)(-f3)).tex((double)f13, (double)f14).normal(1.0F, 0.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f17, (double)f19, (double)(-f3)).tex((double)f13, (double)f15).normal(1.0F, 0.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f17, (double)f19, (double)f3).tex((double)f12, (double)f15).normal(1.0F, 0.0F, 0.0F).endVertex();
            worldRenderer.pos((double)f17, (double)f18, (double)f3).tex((double)f12, (double)f14).normal(1.0F, 0.0F, 0.0F).endVertex();
            instance.draw();
         }
      }
   }

   public ResourceLocation getEntityTexture(EntityPainting entityPainting) {
      return KRISTOFFER_PAINTING_TEXTURE;
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityPainting)entity, d, d2, d3, f, f2);
   }
}
