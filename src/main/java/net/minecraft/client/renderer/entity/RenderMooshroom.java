package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.layers.LayerMooshroomMushroom;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.util.ResourceLocation;

public class RenderMooshroom extends RenderLiving {
   private static final ResourceLocation mooshroomTextures = new ResourceLocation("textures/entity/cow/mooshroom.png");

   public ResourceLocation getEntityTexture(EntityMooshroom entityMooshroom) {
      return mooshroomTextures;
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityMooshroom)entity);
   }

   public RenderMooshroom(RenderManager renderManager2, ModelBase modelBase, float f) {
      super(renderManager2, modelBase, f);
      this.addLayer(new LayerMooshroomMushroom(this));
   }
}
