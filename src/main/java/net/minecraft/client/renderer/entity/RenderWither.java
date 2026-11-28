package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelWither;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerWitherAura;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.util.ResourceLocation;

public class RenderWither extends RenderLiving {
   private static final ResourceLocation invulnerableWitherTextures = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
   private static final ResourceLocation witherTextures = new ResourceLocation("textures/entity/wither/wither.png");

   public void preRenderCallback(EntityWither entityWither, float f) {
      float f2 = 2.0F;
      int invulTime = entityWither.getInvulTime();
      if (invulTime > 0) {
         f2 -= ((float)invulTime - f) / 220.0F * 0.5F;
      }

      GlStateManager.scale(f2, f2, f2);
   }

   public RenderWither(RenderManager renderManager2) {
      super(renderManager2, new ModelWither(0.0F), 1.0F);
      this.addLayer(new LayerWitherAura(this));
   }

   public ResourceLocation getEntityTexture(EntityWither entityWither) {
      int invulTime = entityWither.getInvulTime();
      return invulTime <= 0 || invulTime <= 80 && invulTime / 5 % 2 == 1 ? witherTextures : invulnerableWitherTextures;
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityWither)entity, d, d2, d3, f, f2);
   }

   @Override
   public void doRender(EntityLiving entityLiving, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityWither)entityLiving, d, d2, d3, f, f2);
   }

   public void doRender(EntityWither entityWither, double d, double d2, double d3, float f, float f2) {
      BossStatus.setBossStatus(entityWither, true);
      super.doRender((EntityLiving)entityWither, d, d2, d3, f, f2);
   }

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntityWither)entity, f);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityWither)entity);
   }

   @Override
   public void doRender(EntityLivingBase entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityWither)entity, d, d2, d3, f, f2);
   }
}
