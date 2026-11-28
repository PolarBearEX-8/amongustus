package net.minecraft.client.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;

public class RenderFireball extends Render {
   private float scale;

   public ResourceLocation getEntityTexture(EntityFireball entityFireball) {
      return TextureMap.locationBlocksTexture;
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityFireball)entity);
   }

   public void doRender(EntityFireball entityFireball, double d, double d2, double d3, float f, float f2) {
      GlStateManager.pushMatrix();
      this.bindEntityTexture(entityFireball);
      GlStateManager.translate((float)d, (float)d2, (float)d3);
      GlStateManager.enableRescaleNormal();
      GlStateManager.scale(this.scale, this.scale, this.scale);
      TextureAtlasSprite particleIcon = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(Items.fire_charge);
      Tessellator instance = Tessellator.getInstance();
      WorldRenderer worldRenderer = instance.getWorldRenderer();
      float minU = particleIcon.getMinU();
      float maxU = particleIcon.getMaxU();
      float minV = particleIcon.getMinV();
      float maxV = particleIcon.getMaxV();
      float f3 = 1.0F;
      float f4 = 0.5F;
      float f5 = 0.25F;
      GlStateManager.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
      worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
      worldRenderer.pos(-0.5, -0.25, 0.0).tex((double)minU, (double)maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
      worldRenderer.pos(0.5, -0.25, 0.0).tex((double)maxU, (double)maxV).normal(0.0F, 1.0F, 0.0F).endVertex();
      worldRenderer.pos(0.5, 0.75, 0.0).tex((double)maxU, (double)minV).normal(0.0F, 1.0F, 0.0F).endVertex();
      worldRenderer.pos(-0.5, 0.75, 0.0).tex((double)minU, (double)minV).normal(0.0F, 1.0F, 0.0F).endVertex();
      instance.draw();
      GlStateManager.disableRescaleNormal();
      GlStateManager.popMatrix();
      super.doRender(entityFireball, d, d2, d3, f, f2);
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityFireball)entity, d, d2, d3, f, f2);
   }

   public RenderFireball(RenderManager renderManager2, float f) {
      super(renderManager2);
      this.scale = f;
   }
}
