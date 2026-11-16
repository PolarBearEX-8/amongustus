package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.EntitySlime;
import net.minecraft.item.GoldenBoots;
import net.minecraft.util.ResourceLocation;

public class DefaultResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/slime/slime.png");

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntitySlime((EntitySlime)livingEntity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float factor) {
      this.applyEntitySlime((EntitySlime)livingEntity, factor);
   }

   public ResourceLocation resolveResourceLocation(EntitySlime entitySlime) {
      return resourceLocation3;
   }

   public void applyEntitySlime(EntitySlime entitySlime, double posX, double posY, double posZ, float factor, float factor2) {
      this.factor = 0.25F * (float)entitySlime.getCount10();
      super.applyEntityMob(entitySlime, posX, posY, posZ, factor, factor2);
   }

   public void applyEntitySlime(EntitySlime entitySlime, float factor) {
      float count = (float)entitySlime.getCount10();
      float factor2 = (entitySlime.factor40 + (entitySlime.factor44 - entitySlime.factor40) * factor) / (count * 0.5F + 1.0F);
      float factor3 = 1.0F / (factor2 + 1.0F);
      GlStateManager.translate3f3(factor3 * count, 1.0F / factor3 * count, factor3 * count);
   }

   @Override
   public void applyEntityMob(EntityMob entityMob, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntitySlime((EntitySlime)entityMob, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntitySlime((EntitySlime)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntitySlime)entity);
   }

   public DefaultResourceLocationEntry(RenderManager renderManager, PassiveFloatEntry passiveFloatEntry, float factor) {
      super(renderManager, passiveFloatEntry, factor);
      this.add(new GoldenBoots(this));
   }
}
