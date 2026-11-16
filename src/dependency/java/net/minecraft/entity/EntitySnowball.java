package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntitySnowball extends EntityThrowable {
   @Override
   public void applyMovingObjectPosition(MovingObjectPosition hitResult) {
      if (hitResult.entityHit != null) {
         byte byteValue = 0;
         if (hitResult.entityHit instanceof EntityBlaze) {
            byteValue = 3;
         }

         hitResult.entityHit.matchesDamageSource(DamageSource.resolveDamageSource2(this, this.getEntityLivingBase()), (float)byteValue);
      }

      for (int index = 0; index < 8; index++) {
         this.worldObj
            .applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SNOWBALL, this.posX, this.posY, this.posZ, 0.0, 0.0, 0.0, new int[0]);
      }

      if (!this.worldObj.state) {
         this.updateState9();
      }
   }

   public EntitySnowball(World world, EntityLivingBase livingEntity) {
      super(world, livingEntity);
   }

   public EntitySnowball(World world) {
      super(world);
   }

   public EntitySnowball(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ);
   }
}
