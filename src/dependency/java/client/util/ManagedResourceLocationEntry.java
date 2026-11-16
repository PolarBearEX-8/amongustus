package client.util;

import client.render.InternalRendererLivingEntityAdapter;
import client.render.RenderManager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.EntityWolf;
import net.minecraft.util.ResourceLocation;

public class ManagedResourceLocationEntry extends InternalRendererLivingEntityAdapter {
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/wolf/wolf.png");
   public static final ResourceLocation resourceLocation3 = new ResourceLocation("textures/entity/wolf/wolf_tame.png");
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/entity/wolf/wolf_angry.png");

   @Override
   public float computeFactor(EntityLivingBase livingEntity, float factor) {
      return this.computeFactor((EntityWolf)livingEntity, factor);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityWolf((EntityWolf)livingEntity, posX, posY, posZ, factor, factor2);
   }

   public float computeFactor(EntityWolf entityWolf, float factor) {
      return entityWolf.getFactor3();
   }

   @Override
   public void applyEntityMob(EntityMob entityMob, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityWolf((EntityWolf)entityMob, posX, posY, posZ, factor, factor2);
   }

   public void applyEntityWolf(EntityWolf entityWolf, double posX, double posY, double posZ, float factor, float factor2) {
      if (entityWolf.isEnabled29()) {
         float factor3 = entityWolf.computeFactor(factor2) * entityWolf.computeFactor4(factor2);
         GlStateManager.translate3f2(factor3, factor3, factor3);
      }

      super.applyEntityMob(entityWolf, posX, posY, posZ, factor, factor2);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityWolf((EntityWolf)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocationByEntityWolf((EntityWolf)entity);
   }

   public ResourceLocation resolveResourceLocationByEntityWolf(EntityWolf entityWolf) {
      return entityWolf.isActive32() ? resourceLocation3 : (entityWolf.isActive35() ? resourceLocation5 : resourceLocation4);
   }

   public ManagedResourceLocationEntry(RenderManager renderManager, PassiveFloatEntry passiveFloatEntry, float factor) {
      super(renderManager, passiveFloatEntry, factor);
      this.add(new ExtendedResourceLocationEntry(this));
   }
}
