package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.util.MathHelper;

public class EntityAIArrowAttack extends EntityAIBase {
   private final EntityLiving entityHost;
   private final IRangedAttackMob rangedAttackEntityHost;
   private EntityLivingBase attackTarget;
   private int rangedAttackTime = -1;
   private double entityMoveSpeed;
   private int seeTime;
   private int attackIntervalMin;
   private int maxRangedAttackTime;
   private float attackRadius;
   private float maxAttackDistance;

   @Override
   public void updateTask() {
      double distanceSq = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.getEntityBoundingBox().minY, this.attackTarget.posZ);
      boolean flag = this.entityHost.getEntitySenses().canSee(this.attackTarget);
      if (flag) {
         this.seeTime++;
      } else {
         this.seeTime = 0;
      }

      if (distanceSq <= (double)this.maxAttackDistance && this.seeTime >= 20) {
         this.entityHost.getNavigator().clearPathEntity();
      } else {
         this.entityHost.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
      }

      this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
      if (--this.rangedAttackTime == 0) {
         if (distanceSq > (double)this.maxAttackDistance || !flag) {
            return;
         }

         float f = MathHelper.sqrt_double(distanceSq) / this.attackRadius;
         float f2 = MathHelper.clamp_float(f, 0.1F, 1.0F);
         this.rangedAttackEntityHost.attackEntityWithRangedAttack(this.attackTarget, f2);
         this.rangedAttackTime = MathHelper.floor_float(f * (float)(this.maxRangedAttackTime - this.attackIntervalMin) + (float)this.attackIntervalMin);
      } else if (this.rangedAttackTime < 0) {
         this.rangedAttackTime = MathHelper.floor_float((MathHelper.sqrt_double(distanceSq) / this.attackRadius) * (float)(this.maxRangedAttackTime - this.attackIntervalMin) + (float)this.attackIntervalMin);
      }
   }

   public EntityAIArrowAttack(IRangedAttackMob rangedAttackMob, double d, int n, float f) {
      this(rangedAttackMob, d, n, n, f);
   }

   @Override
   public void resetTask() {
      this.attackTarget = null;
      this.seeTime = 0;
      this.rangedAttackTime = -1;
   }

   public EntityAIArrowAttack(IRangedAttackMob rangedAttackMob, double d, int n, int n2, float f) {
      if (!(rangedAttackMob instanceof EntityLivingBase)) {
         throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
      } else {
         this.rangedAttackEntityHost = rangedAttackMob;
         this.entityHost = (EntityLiving)rangedAttackMob;
         this.entityMoveSpeed = d;
         this.attackIntervalMin = n;
         this.maxRangedAttackTime = n2;
         this.attackRadius = f;
         this.maxAttackDistance = f * f;
         this.setMutexBits(3);
      }
   }

   @Override
   public boolean continueExecuting() {
      return this.shouldExecute() || !this.entityHost.getNavigator().noPath();
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase attackTarget2 = this.entityHost.getAttackTarget();
      if (attackTarget2 == null) {
         return false;
      } else {
         this.attackTarget = attackTarget2;
         return true;
      }
   }
}
