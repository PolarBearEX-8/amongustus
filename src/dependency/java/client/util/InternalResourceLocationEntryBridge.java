package client.util;

import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.EntitySkeleton;
import net.minecraft.util.ResourceLocation;

public class InternalResourceLocationEntryBridge extends CoreResourceLocationEntry {
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/entity/skeleton/skeleton.png");
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");

   public void applyEntitySkeleton(EntitySkeleton entitySkeleton, float factor) {
      if (entitySkeleton.getCount9() == 1) {
         GlStateManager.translate3f3(1.2F, 1.2F, 1.2F);
      }
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntitySkeleton((EntitySkeleton)livingEntity, factor);
   }

   public ResourceLocation resolveResourceLocation(EntitySkeleton entitySkeleton) {
      return entitySkeleton.getCount9() == 1 ? resourceLocation4 : resourceLocation5;
   }

   @Override
   public ResourceLocation resolveResourceLocationByEntityMob(EntityMob entityMob) {
      return this.resolveResourceLocation((EntitySkeleton)entityMob);
   }

   @Override
   public void updateState() {
      GlStateManager.translate(0.09375F, 0.1875F, 0.0F);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntitySkeleton)entity);
   }

   public InternalResourceLocationEntryBridge(RenderManager renderManager) {
      super(renderManager, new AimMonitor(), 0.5F);
      this.add(new TertiaryListenerBridge(this));
      this.add(new ClientManagerDispatcher(this, this));
   }
}
