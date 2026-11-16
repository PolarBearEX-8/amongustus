package client.util;

import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCavespider;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySpider;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationEntryBridge extends EnhancedResourceLocationEntry {
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/spider/cave_spider.png");

   public void applyEntityCavespider(EntityCavespider entityCavespider, float factor) {
      GlStateManager.translate3f3(0.7F, 0.7F, 0.7F);
   }

   @Override
   public ResourceLocation resolveResourceLocationByEntitySpider(EntitySpider entitySpider) {
      return this.resolveResourceLocation((EntityCavespider)entitySpider);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntityCavespider((EntityCavespider)livingEntity, factor);
   }

   public ResourceLocation resolveResourceLocation(EntityCavespider entityCavespider) {
      return resourceLocation4;
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityCavespider)entity);
   }

   public ResourceLocationEntryBridge(RenderManager renderManager) {
      super(renderManager);
      this.factor *= 0.7F;
   }
}
