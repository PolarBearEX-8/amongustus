package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;

public class RenderCow extends RenderLiving {
   private static final ResourceLocation cowTextures = new ResourceLocation("textures/entity/cow/cow.png");

   public RenderCow(RenderManager renderManager2, ModelBase modelBase, float f) {
      super(renderManager2, modelBase, f);
   }

   public ResourceLocation getEntityTexture(EntityCow entityCow) {
      return cowTextures;
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityCow)entity);
   }
}
