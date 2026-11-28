package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItemWitch;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.ResourceLocation;

public class RenderWitch extends RenderLiving {
   private static final ResourceLocation witchTextures = new ResourceLocation("textures/entity/witch.png");

   public void doRender(EntityWitch entityWitch, double d, double d2, double d3, float f, float f2) {
      ((ModelWitch)this.mainModel).holdingItem = entityWitch.getHeldItem() != null;
      super.doRender((EntityLiving)entityWitch, d, d2, d3, f, f2);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityWitch)entity);
   }

   @Override
   public void transformHeldFull3DItemLayer() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   public RenderWitch(RenderManager renderManager2) {
      super(renderManager2, new ModelWitch(0.0F), 0.5F);
      this.addLayer(new LayerHeldItemWitch(this));
   }

   public void preRenderCallback(EntityWitch entityWitch, float f) {
      float f2 = 0.9375F;
      GlStateManager.scale(f2, f2, f2);
   }

   public ResourceLocation getEntityTexture(EntityWitch entityWitch) {
      return witchTextures;
   }

   @Override
   public void doRender(Entity entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityWitch)entity, d, d2, d3, f, f2);
   }

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntityWitch)entity, f);
   }

   @Override
   public void doRender(EntityLiving entityLiving, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityWitch)entityLiving, d, d2, d3, f, f2);
   }

   @Override
   public void doRender(EntityLivingBase entity, double d, double d2, double d3, float f, float f2) {
      this.doRender((EntityWitch)entity, d, d2, d3, f, f2);
   }
}
