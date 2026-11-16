package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerAdapter;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrownenderpearl extends EntityThrowable {
   public EntityLivingBase entityLivingBase2;

   @Override
   public void applyMovingObjectPosition(MovingObjectPosition hitResult) {
      EntityLivingBase entityLivingBase = this.getEntityLivingBase();
      if (hitResult.entityHit != null) {
         if (hitResult.entityHit == this.entityLivingBase2) {
            return;
         }

         hitResult.entityHit.matchesDamageSource(DamageSource.resolveDamageSource2(this, entityLivingBase), 0.0F);
      }

      for (int index = 0; index < 32; index++) {
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.PORTAL,
               this.posX,
               this.posY + this.random.nextDouble() * 2.0,
               this.posZ,
               this.random.nextGaussian(),
               0.0,
               this.random.nextGaussian(),
               new int[0]
            );
      }

      if (!this.worldObj.state) {
         if (entityLivingBase instanceof EntityPlayerAdapter) {
            EntityPlayerAdapter entityPlayerAdapter = (EntityPlayerAdapter)entityLivingBase;
            if (entityPlayerAdapter.auxiliaryListenerAdapter4.getNetworkManager().isOpen() && entityPlayerAdapter.worldObj == this.worldObj && !entityPlayerAdapter.isState5()) {
               if (this.random.nextFloat() < 0.05F && this.worldObj.getDoFireTickManager().matchesText("doMobSpawning")) {
                  EntityEndermite entityEndermite = new EntityEndermite(this.worldObj);
                  entityEndermite.setEnabled28(true);
                  entityEndermite.applyDoubleByDouble(entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ, entityLivingBase.rotationYaw, entityLivingBase.rotationPitch);
                  this.worldObj.matchesEntity(entityEndermite);
               }

               if (entityLivingBase.isSneaking()) {
                  entityLivingBase.setEntity8((Entity)null);
               }

               entityLivingBase.applyDoubleByDouble(this.posX, this.posY, this.posZ);
               entityLivingBase.fallDistance = 0.0F;
               entityLivingBase.matchesDamageSource(DamageSource.damageSource11, 5.0F);
            }
         } else if (entityLivingBase != null) {
            entityLivingBase.applyDoubleByDouble(this.posX, this.posY, this.posZ);
            entityLivingBase.fallDistance = 0.0F;
         }

         this.updateState9();
      }
   }

   @Override
   public void updateState() {
      EntityLivingBase entityLivingBase = this.getEntityLivingBase();
      if (entityLivingBase instanceof EntityPlayer && !entityLivingBase.canExecute()) {
         this.updateState9();
      } else {
         super.updateState();
      }
   }

   public EntityThrownenderpearl(World world, EntityLivingBase livingEntity) {
      super(world, livingEntity);
      this.entityLivingBase2 = livingEntity;
   }

   public EntityThrownenderpearl(World world) {
      super(world);
   }

   public EntityThrownenderpearl(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ);
   }
}
