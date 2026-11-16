package client.util;

import client.event.PreMotionDispatcher;
import client.render.RenderManager;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.entity.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class ResourceLocationEntryHandler extends CoreResourceLocationEntry {
   public static final ResourceLocation resourceLocation4 = new ResourceLocation("textures/entity/zombie/zombie.png");
   public static final ResourceLocation resourceLocation5 = new ResourceLocation("textures/entity/zombie/zombie_villager.png");
   public final FloatEntryRegistry floatEntryRegistry2;
   public final AimEstimator aimEstimator;
   public final List list2;
   public final List list3;

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityZombie((EntityZombie)livingEntity, posX, posY, posZ, factor, factor2);
   }

   public ResourceLocation resolveResourceLocation(EntityZombie entityZombie) {
      return entityZombie.isActive32() ? resourceLocation5 : resourceLocation4;
   }

   private void applyEntityZombie(EntityZombie entityZombie) {
      if (entityZombie.isActive32()) {
         this.passiveFloatEntry = this.aimEstimator;
         this.list = this.list2;
      } else {
         this.passiveFloatEntry = this.floatEntryRegistry2;
         this.list = this.list3;
      }

      this.floatEntryRegistry = (FloatEntryRegistry)this.passiveFloatEntry;
   }

   public void applyEntityZombie(EntityZombie entityZombie, float posX, float posY, float posZ) {
      float factor = posY;
      if (entityZombie.isActive33()) {
         factor = posY + (float)(Math.cos((double)entityZombie.ticksExisted * 3.25) * Math.PI * 0.25);
      }

      super.applyEntityLivingBase(entityZombie, posX, factor, posZ);
   }

   @Override
   public void applyEntityMob(EntityMob entityMob, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityZombie((EntityZombie)entityMob, posX, posY, posZ, factor, factor2);
   }

   public void applyEntityZombie(EntityZombie entityZombie, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityZombie(entityZombie);
      super.applyEntityMob(entityZombie, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocationByEntityMob(EntityMob entityMob) {
      return this.resolveResourceLocation((EntityZombie)entityMob);
   }

   @Override
   public void applyEntityLivingBase(EntityLivingBase livingEntity, float posX, float posY, float posZ) {
      this.applyEntityZombie((EntityZombie)livingEntity, posX, posY, posZ);
   }

   @Override
   public void applyEntity(Entity entity, double posX, double posY, double posZ, float factor, float factor2) {
      this.applyEntityZombie((EntityZombie)entity, posX, posY, posZ, factor, factor2);
   }

   @Override
   public ResourceLocation resolveResourceLocation(Entity entity) {
      return this.resolveResourceLocation((EntityZombie)entity);
   }

   public ResourceLocationEntryHandler(RenderManager renderManager) {
      super(renderManager, new InternalFloatEntryBridge(), 0.5F, 1.0F);
      PreMotionDispatcher preMotionDispatcher = (PreMotionDispatcher)this.list.get(0);
      this.floatEntryRegistry2 = this.floatEntryRegistry;
      this.aimEstimator = new AimEstimator();
      this.add(new TertiaryListenerBridge(this));
      InternalClientManagerBridge internalClientManagerBridge = new InternalClientManagerBridge(this, this);
      this.add(internalClientManagerBridge);
      this.list3 = Lists.newArrayList(this.list);
      if (preMotionDispatcher instanceof ListenerAdapter5) {
         this.remove(preMotionDispatcher);
         this.add(new ListenerAdapter5(this.aimEstimator.boxesManager3));
      }

      this.remove(internalClientManagerBridge);
      this.add(new GeometryReader(this));
      this.list2 = Lists.newArrayList(this.list);
   }
}
