package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAIOwnerHurtByTarget extends EntityAITarget {
   public EntityTameable theDefendingTameable;
   public EntityLivingBase theOwnerAttacker;
   private int timestamp;

   @Override
   public boolean shouldExecute() {
      if (!this.theDefendingTameable.isTamed()) {
         return false;
      } else {
         EntityLivingBase ownerEntity = this.theDefendingTameable.getOwnerEntity();
         if (ownerEntity == null) {
            return false;
         } else {
            this.theOwnerAttacker = ownerEntity.getAITarget();
            return ownerEntity.getRevengeTimer() != this.timestamp
               && this.isSuitableTarget(this.theOwnerAttacker, false)
               && this.theDefendingTameable.shouldAttackEntity(this.theOwnerAttacker, ownerEntity);
         }
      }
   }

   public EntityAIOwnerHurtByTarget(EntityTameable entityTameable) {
      super(entityTameable, false);
      this.theDefendingTameable = entityTameable;
      this.setMutexBits(1);
   }

   @Override
   public void startExecuting() {
      this.taskOwner.setAttackTarget(this.theOwnerAttacker);
      EntityLivingBase ownerEntity = this.theDefendingTameable.getOwnerEntity();
      if (ownerEntity != null) {
         this.timestamp = ownerEntity.getRevengeTimer();
      }

      super.startExecuting();
   }
}
