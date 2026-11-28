package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.util.ResourceLocation;

public class RenderMagmaCube extends RenderLiving {
   private static final ResourceLocation magmaCubeTextures = new ResourceLocation("textures/entity/slime/magmacube.png");

   public RenderMagmaCube(RenderManager renderManager2) {
      super(renderManager2, new ModelMagmaCube(), 0.25F);
   }

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntityMagmaCube)entity, f);
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityMagmaCube)entity);
   }

   public ResourceLocation getEntityTexture(EntityMagmaCube entityMagmaCube) {
      return magmaCubeTextures;
   }

   public void preRenderCallback(EntityMagmaCube entityMagmaCube, float f) {
      int slimeSize = entityMagmaCube.getSlimeSize();
      float f2 = (entityMagmaCube.prevSquishFactor + (entityMagmaCube.squishFactor - entityMagmaCube.prevSquishFactor) * f) / ((float)slimeSize * 0.5F + 1.0F);
      float f3 = 1.0F / (f2 + 1.0F);
      float f4 = (float)slimeSize;
      GlStateManager.scale(f3 * f4, 1.0F / f3 * f4, f3 * f4);
   }
}
