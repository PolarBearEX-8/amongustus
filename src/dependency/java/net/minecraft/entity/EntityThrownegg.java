package net.minecraft.entity;

import client.setting.AttackEffectsExplosionNormalType;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityThrownegg extends EntityThrowable {
   @Override
   public void applyMovingObjectPosition(MovingObjectPosition hitResult) {
      if (hitResult.entityHit != null) {
         hitResult.entityHit.matchesDamageSource(DamageSource.resolveDamageSource2(this, this.getEntityLivingBase()), 0.0F);
      }

      if (!this.worldObj.state && this.random.nextInt(8) == 0) {
         byte byteValue = 1;
         if (this.random.nextInt(32) == 0) {
            byteValue = 4;
         }

         for (int index = 0; index < byteValue; index++) {
            EntityChicken entityChicken = new EntityChicken(this.worldObj);
            entityChicken.applyIndex8(-24000);
            entityChicken.applyDoubleByDouble(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
            this.worldObj.matchesEntity(entityChicken);
         }
      }

      for (int index2 = 0; index2 < 8; index2++) {
         this.worldObj
            .applyAttackEffectsExplosionNorm(
               AttackEffectsExplosionNormalType.ITEM_CRACK,
               this.posX,
               this.posY,
               this.posZ,
               ((double)this.random.nextFloat() - 0.5) * 0.08,
               ((double)this.random.nextFloat() - 0.5) * 0.08,
               ((double)this.random.nextFloat() - 0.5) * 0.08,
               new int[]{Item.getIndex(Items.egg)}
            );
      }

      if (!this.worldObj.state) {
         this.updateState9();
      }
   }

   public EntityThrownegg(World world) {
      super(world);
   }

   public EntityThrownegg(World world, EntityLivingBase livingEntity) {
      super(world, livingEntity);
   }

   public EntityThrownegg(World world, double posX, double posY, double posZ) {
      super(world, posX, posY, posZ);
   }
}
