package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSnowMan;
import net.minecraft.client.renderer.entity.layers.LayerSnowmanHead;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.util.ResourceLocation;

public class RenderSnowMan extends RenderLiving {
   private static final ResourceLocation snowManTextures = new ResourceLocation("textures/entity/snowman.png");

   public ResourceLocation getEntityTexture(EntitySnowman entitySnowman) {
      return snowManTextures;
   }

   public ModelSnowMan getSnowManModel() {
      return (ModelSnowMan)super.getMainModel();
   }

   public RenderSnowMan(RenderManager renderManager2) {
      super(renderManager2, new ModelSnowMan(), 0.5F);
      this.addLayer(new LayerSnowmanHead(this));
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntitySnowman)entity);
   }

   @Override
   public ModelBase getMainModel() {
      return this.getSnowManModel();
   }
}
