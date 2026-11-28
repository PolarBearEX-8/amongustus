package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerWolfCollar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;

public class RenderWolf extends RenderLiving {
   private static final ResourceLocation wolfTextures = new ResourceLocation("textures/entity/wolf/wolf.png");
   private static final ResourceLocation tamedWolfTextures = new ResourceLocation("textures/entity/wolf/wolf_tame.png");
   private static final ResourceLocation anrgyWolfTextures = new ResourceLocation("textures/entity/wolf/wolf_angry.png");

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityWolf)entity);
   }

   public void doRender(EntityWolf entityWolf, double d, double d2, double d3, float f, float f2) {
      if (entityWolf.isWolfWet()) {
         float f3 = entityWolf.getBrightness(f2) * entityWolf.getShadingWhileWet(f2);
         GlStateManager.color(f3, f3, f3);
      }

      super.doRender((EntityLiving)entityWolf, d, d2, d3, f, f2);
   }

   @Override
   public void doRender(EntityLiving entityLiving, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityWolf)entityLiving, d, d2, d3, f, f2);
   }

   public float handleRotationFloat(EntityWolf entityWolf, float f) {
      return entityWolf.getTailRotation();
   }

   @Override
   public void doRender(EntityLivingBase entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityWolf)entity, d, d2, d3, f, f2);
   }

   @Override
   public float handleRotationFloat(EntityLivingBase entity, float f) {
      return this.handleRotationFloat((EntityWolf)entity, f);
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityWolf)entity, d, d2, d3, f, f2);
   }

   public RenderWolf(RenderManager renderManager2, ModelBase modelBase, float f) {
      super(renderManager2, modelBase, f);
      this.addLayer(new LayerWolfCollar(this));
   }

   public ResourceLocation getEntityTexture(EntityWolf entityWolf) {
      return entityWolf.isTamed() ? tamedWolfTextures : (entityWolf.isAngry() ? anrgyWolfTextures : wolfTextures);
   }
}
