package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerSlimeGel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;

public class RenderSlime extends RenderLiving {
   private static final ResourceLocation slimeTextures = new ResourceLocation("textures/entity/slime/slime.png");

   @Override
   public void doRender(EntityLiving entityLiving, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntitySlime)entityLiving, d, d2, d3, f, f2);
   }

   public void doRender(EntitySlime entitySlime, double d, double d2, double d3, float f, float f2) {
      this.shadowSize = 0.25F * (float)entitySlime.getSlimeSize();
      super.doRender((EntityLiving)entitySlime, d, d2, d3, f, f2);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntitySlime)entity);
   }

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntitySlime)entity, f);
   }

   @Override
   public void doRender(EntityLivingBase entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntitySlime)entity, d, d2, d3, f, f2);
   }

   public void preRenderCallback(EntitySlime entitySlime, float f) {
      float slimeSize = (float)entitySlime.getSlimeSize();
      float f2 = (entitySlime.prevSquishFactor + (entitySlime.squishFactor - entitySlime.prevSquishFactor) * f) / (slimeSize * 0.5F + 1.0F);
      float f3 = 1.0F / (f2 + 1.0F);
      GlStateManager.scale(f3 * slimeSize, 1.0F / f3 * slimeSize, f3 * slimeSize);
   }

   public RenderSlime(RenderManager renderManager2, ModelBase modelBase, float f) {
      super(renderManager2, modelBase, f);
      this.addLayer(new LayerSlimeGel(this));
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntitySlime)entity, d, d2, d3, f, f2);
   }

   public ResourceLocation getEntityTexture(EntitySlime entitySlime) {
      return slimeTextures;
   }
}
