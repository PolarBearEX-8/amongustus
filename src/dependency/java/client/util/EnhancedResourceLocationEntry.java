package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntitySpider;
import net.minecraft.item.DiamondArmor;
import net.minecraft.util.ResourceLocation;

public class EnhancedResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/spider/spider.png");

   public float computeFactor(EntitySpider entitySpider) {
      return 180.0F;
   }

   public ResourceLocation resolveResourceLocationByEntitySpider(EntitySpider entitySpider) {
      return resourceLocation3;
   }

   @Override
   public float computeFactor(EntityLivingBase livingEntity) {
      return this.computeFactor((EntitySpider)livingEntity);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntitySpider((EntitySpider)entity);
   }

   public EnhancedResourceLocationEntry(RenderManager renderManager) {
      super(renderManager, new DiamondArmor(), 1.0F);
      this.add(new InternalListenerHandler(this));
   }
}
