package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelGhast;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.util.ResourceLocation;

public class RenderGhast extends RenderLiving {
   private static final ResourceLocation ghastTextures = new ResourceLocation("textures/entity/ghast/ghast.png");
   private static final ResourceLocation ghastShootingTextures = new ResourceLocation("textures/entity/ghast/ghast_shooting.png");

   public void preRenderCallback(EntityGhast entityGhast, float f) {
      float f2 = 1.0F;
      float f3 = (8.0F + f2) / 2.0F;
      float f4 = (8.0F + 1.0F / f2) / 2.0F;
      GlStateManager.scale(f4, f3, f4);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntityGhast)entity, f);
   }

   public ResourceLocation getEntityTexture(EntityGhast entityGhast) {
      return entityGhast.isAttacking() ? ghastShootingTextures : ghastTextures;
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityGhast)entity);
   }

   public RenderGhast(RenderManager renderManager2) {
      super(renderManager2, new ModelGhast(), 0.5F);
   }
}
