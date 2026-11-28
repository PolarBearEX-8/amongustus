package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.ResourceLocation;

public class RenderSquid extends RenderLiving {
   private static final ResourceLocation squidTextures = new ResourceLocation("textures/entity/squid.png");

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntitySquid)entity);
   }

   public RenderSquid(RenderManager renderManager2, ModelBase modelBase, float f) {
      super(renderManager2, modelBase, f);
   }

   public void rotateCorpse(EntitySquid entitySquid, float f, float f2, float f3) {
      float f4 = entitySquid.prevSquidPitch + (entitySquid.squidPitch - entitySquid.prevSquidPitch) * f3;
      float f5 = entitySquid.prevSquidYaw + (entitySquid.squidYaw - entitySquid.prevSquidYaw) * f3;
      GlStateManager.translate(0.0F, 0.5F, 0.0F);
      GlStateManager.rotate(180.0F - f2, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(f4, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(f5, 0.0F, 1.0F, 0.0F);
      GlStateManager.translate(0.0F, -1.2F, 0.0F);
   }

   @Override
   public float handleRotationFloat(EntityLivingBase entity, float f) {
      return this.handleRotationFloat((EntitySquid)entity, f);
   }

   public ResourceLocation getEntityTexture(EntitySquid entitySquid) {
      return squidTextures;
   }

   public float handleRotationFloat(EntitySquid entitySquid, float f) {
      return entitySquid.lastTentacleAngle + (entitySquid.tentacleAngle - entitySquid.lastTentacleAngle) * f;
   }

   @Override
   public void rotateCorpse(EntityLivingBase entity, float f, float f2, float f3) {
      this.rotateCorpse((EntitySquid)entity, f, f2, f3);
   }
}
