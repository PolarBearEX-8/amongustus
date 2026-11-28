package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderBat extends RenderLiving {
   private static final ResourceLocation batTextures = new ResourceLocation("textures/entity/bat.png");

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityBat)entity);
   }

   public RenderBat(RenderManager renderManager2) {
      super(renderManager2, new ModelBat(), 0.25F);
   }

   public ResourceLocation getEntityTexture(EntityBat entityBat) {
      return batTextures;
   }

   public void rotateCorpse(EntityBat entityBat, float f, float f2, float f3) {
      if (!entityBat.getIsBatHanging()) {
         GlStateManager.translate(0.0F, MathHelper.cos(f * 0.3F) * 0.1F, 0.0F);
      } else {
         GlStateManager.translate(0.0F, -0.1F, 0.0F);
      }

      super.rotateCorpse(entityBat, f, f2, f3);
   }

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntityBat)entity, f);
   }

   @Override
   public void rotateCorpse(EntityLivingBase entity, float f, float f2, float f3) {
      this.rotateCorpse((EntityBat)entity, f, f2, f3);
   }

   public void preRenderCallback(EntityBat entityBat, float f) {
      GlStateManager.scale(0.35F, 0.35F, 0.35F);
   }
}
