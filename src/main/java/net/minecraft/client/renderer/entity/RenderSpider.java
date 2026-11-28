package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.entity.layers.LayerSpiderEyes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;

public class RenderSpider extends RenderLiving {
   private static final ResourceLocation spiderTextures = new ResourceLocation("textures/entity/spider/spider.png");

   public RenderSpider(RenderManager renderManager2) {
      super(renderManager2, new ModelSpider(), 1.0F);
      this.addLayer(new LayerSpiderEyes(this));
   }

   @Override
   public float getDeathMaxRotation(EntityLivingBase entity) {
      return this.getDeathMaxRotation((EntitySpider)entity);
   }

   public float getDeathMaxRotation(EntitySpider entitySpider) {
      return 180.0F;
   }

   public ResourceLocation getEntityTexture(EntitySpider entitySpider) {
      return spiderTextures;
   }

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntitySpider)entity);
   }
}
