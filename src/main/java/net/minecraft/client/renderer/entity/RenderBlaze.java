package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBlaze;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.ResourceLocation;

public class RenderBlaze extends RenderLiving {
   private static final ResourceLocation blazeTextures = new ResourceLocation("textures/entity/blaze.png");

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityBlaze)entity);
   }

   public ResourceLocation getEntityTexture(EntityBlaze entityBlaze) {
      return blazeTextures;
   }

   public RenderBlaze(RenderManager renderManager2) {
      super(renderManager2, new ModelBlaze(), 0.5F);
   }
}
