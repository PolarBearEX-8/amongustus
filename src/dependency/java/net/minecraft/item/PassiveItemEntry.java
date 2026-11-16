package net.minecraft.item;

import client.event.SystemListener;
import client.util.RotationPool;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityMob;
import net.minecraft.util.MathHelper;

public class PassiveItemEntry extends RotationPool {
   public final EntityMob entityMob;
   public final SystemListener systemListener;
   public EntityLivingBase entityLivingBase;
   public int count2 = -1;
   public double value;
   public int count3;
   public int count4;
   public int count5;
   public float factor;
   public float factor2;

   @Override
   public void updateState2() {
      double doubleItem = this.entityMob.getDouble3(this.entityLivingBase.posX, this.entityLivingBase.getBoundingBox2().minY, this.entityLivingBase.posZ);
      boolean flag = this.entityMob.getCanSeeManager().matchesEntityByEntity(this.entityLivingBase);
      if (flag) {
         this.count3++;
      } else {
         this.count3 = 0;
      }

      if (doubleItem <= (double)this.factor2 && this.count3 >= 20) {
         this.entityMob.getPathfindManager().updateState4();
      } else {
         this.entityMob.getPathfindManager().matchesEntityByEntity(this.entityLivingBase, this.value);
      }

      this.entityMob.getEntityRavagerAdapter().applyEntityByEntity(this.entityLivingBase, 30.0F, 30.0F);
      if (--this.count2 == 0) {
         if (doubleItem > (double)this.factor2 || !flag) {
            return;
         }

         float factor3 = MathHelper.sqrt_double(doubleItem) / this.factor;
         float factor4 = MathHelper.clamp_float(factor3, 0.1F, 1.0F);
         this.systemListener.applyEntityLivingBaseByEntityLivingBase(this.entityLivingBase, factor4);
         this.count2 = MathHelper.floor_float(factor3 * (float)(this.count5 - this.count4) + (float)this.count4);
      } else if (this.count2 < 0) {
         float factor5 = MathHelper.sqrt_double(doubleItem) / this.factor;
         this.count2 = MathHelper.floor_float(factor5 * (float)(this.count5 - this.count4) + (float)this.count4);
      }
   }

   public PassiveItemEntry(SystemListener systemListener2, double offset, int count, float factor3) {
      this(systemListener2, offset, count, count, factor3);
   }

   @Override
   public boolean isActive() {
      EntityLivingBase entityLivingBase2 = this.entityMob.getEntityLivingBase4();
      if (entityLivingBase2 == null) {
         return false;
      } else {
         this.entityLivingBase = entityLivingBase2;
         return true;
      }
   }

   @Override
   public void updateState() {
      this.entityLivingBase = null;
      this.count3 = 0;
      this.count2 = -1;
   }

   public PassiveItemEntry(SystemListener systemListener2, double offset, int count, int count6, float factor3) {
      if (!(systemListener2 instanceof EntityLivingBase)) {
         throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
      } else {
         this.systemListener = systemListener2;
         this.entityMob = (EntityMob)systemListener2;
         this.value = offset;
         this.count4 = count;
         this.count5 = count6;
         this.factor = factor3;
         this.factor2 = factor3 * factor3;
         this.setCount(3);
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.isActive() || !this.entityMob.getPathfindManager().isActive2();
   }
}
