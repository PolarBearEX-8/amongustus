package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.layers.LayerSheepWool;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

public class RenderSheep extends RenderLiving {
   private static final ResourceLocation shearedSheepTextures = new ResourceLocation("textures/entity/sheep/sheep.png");

   public RenderSheep(RenderManager renderManager2, ModelBase modelBase, float f) {
      super(renderManager2, modelBase, f);
      this.addLayer(new LayerSheepWool(this));
   }

   public ResourceLocation getEntityTexture(EntitySheep entitySheep) {
      return shearedSheepTextures;
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntitySheep)entity);
   }
}
