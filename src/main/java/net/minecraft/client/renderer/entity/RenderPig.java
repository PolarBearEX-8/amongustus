package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.layers.LayerSaddle;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;

public class RenderPig extends RenderLiving {
   private static final ResourceLocation pigTextures = new ResourceLocation("textures/entity/pig/pig.png");

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityPig)entity);
   }

   public ResourceLocation getEntityTexture(EntityPig entityPig) {
      return pigTextures;
   }

   public RenderPig(RenderManager renderManager2, ModelBase modelBase, float f) {
      super(renderManager2, modelBase, f);
      this.addLayer(new LayerSaddle(this));
   }
}
