package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;

public class RenderCaveSpider extends RenderSpider {
   private static final ResourceLocation caveSpiderTextures = new ResourceLocation("textures/entity/spider/cave_spider.png");

   @Override
   public ResourceLocation getEntityTexture(Entity entity) {
      return this.getEntityTexture((EntityCaveSpider)entity);
   }

   public RenderCaveSpider(RenderManager renderManager2) {
      super(renderManager2);
      this.shadowSize *= 0.7F;
   }

   public void preRenderCallback(EntityCaveSpider entityCaveSpider, float f) {
      GlStateManager.scale(0.7F, 0.7F, 0.7F);
   }

   public ResourceLocation getEntityTexture(EntityCaveSpider entityCaveSpider) {
      return caveSpiderTextures;
   }

   @Override
   public ResourceLocation getEntityTexture(EntitySpider entitySpider) {
      return this.getEntityTexture((EntityCaveSpider)entitySpider);
   }

   @Override
   public void preRenderCallback(EntityLivingBase entity, float f) {
      this.preRenderCallback((EntityCaveSpider)entity, f);
   }
}
