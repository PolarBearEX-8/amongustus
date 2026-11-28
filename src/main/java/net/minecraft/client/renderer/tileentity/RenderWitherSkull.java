package net.minecraft.client.renderer.tileentity;

import net.minecraft.client.model.ModelSkeletonHead;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.util.ResourceLocation;

public class RenderWitherSkull extends Render {
   private static final ResourceLocation invulnerableWitherTextures = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
   private static final ResourceLocation witherTextures = new ResourceLocation("textures/entity/wither/wither.png");
   public ModelSkeletonHead skeletonHeadModel = new ModelSkeletonHead();

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityWitherSkull)entity);
   }

   public ResourceLocation getEntityTexture(EntityWitherSkull entityWitherSkull) {
      return entityWitherSkull.isInvulnerable() ? invulnerableWitherTextures : witherTextures;
   }

   public RenderWitherSkull(RenderManager renderManager2) {
      super(renderManager2);
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityWitherSkull)entity, d, d2, d3, f, f2);
   }

   public void doRender(EntityWitherSkull entityWitherSkull, double d, double d2, double d3, float f, float f2) {
      GlStateManager.pushMatrix();
      GlStateManager.disableCull();
      float f3 = this.interpolateRotation(entityWitherSkull.prevRotationYaw, entityWitherSkull.rotationYaw, f2);
      float f4 = entityWitherSkull.prevRotationPitch + (entityWitherSkull.rotationPitch - entityWitherSkull.prevRotationPitch) * f2;
      GlStateManager.translate((float)d, (float)d2, (float)d3);
      float f5 = 0.0625F;
      GlStateManager.enableRescaleNormal();
      GlStateManager.scale(-1.0F, -1.0F, 1.0F);
      GlStateManager.enableAlpha();
      this.bindEntityTexture(entityWitherSkull);
      this.skeletonHeadModel.render(entityWitherSkull, 0.0F, 0.0F, 0.0F, f3, f4, f5, true);
      GlStateManager.popMatrix();
      super.doRender(entityWitherSkull, d, d2, d3, f, f2);
   }

   private float interpolateRotation(float f, float f2, float f3) {
      float f4 = f2 - f;

      while (f4 < -180.0F) {
         f4 += 360.0F;
      }

      while (f4 >= 180.0F) {
         f4 -= 360.0F;
      }

      return f + f3 * f4;
   }
}
