package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.EntityWitherboss;
import net.minecraft.item.CustomItemArrayEntry;
import net.minecraft.util.ResourceLocation;

public class ExtraResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/wither/wither_invulnerable.png");
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/wither/wither.png");

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityWitherboss((EntityWitherboss)livingEntity, posX, posY, posZ, factor, factor2);
   }

   public ResourceLocation resolveResourceLocation(EntityWitherboss entityWitherboss) {
      int count = entityWitherboss.getCount9();
      return count <= 0 || count <= 80 && count / 5 % 2 == 1 ? resourceLocation4 : resourceLocation3;
   }

   public void applyEntityWitherboss(EntityWitherboss entityWitherboss, float factor) {
      float factor2 = 2.0F;
      int count = entityWitherboss.getCount9();
      if (count > 0) {
         factor2 -= ((float)count - factor) / 220.0F * 0.5F;
      }

      GlStateManager.translate3f3(factor2, factor2, factor2);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntityWitherboss((EntityWitherboss)livingEntity, factor);
   }

   @Override
   public void applyEntityMob(EntityMob entityMob, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityWitherboss((EntityWitherboss)entityMob, posX, posY, posZ, factor, factor2);
   }

   public void applyEntityWitherboss(EntityWitherboss entityWitherboss, double posX, double posY, double posZ, float factor, float factor2) {
      DefaultFloatEntry.applyPreRenderSubscriber(entityWitherboss, true);
      super.applyEntityMob(entityWitherboss, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityWitherboss((EntityWitherboss)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityWitherboss)entity);
   }

   public ExtraResourceLocationEntry(RenderManager renderManager) {
      super(renderManager, new CustomItemArrayEntry(0.0F), 1.0F);
      this.add(new ExtendedListenerHandler(this));
   }
}
