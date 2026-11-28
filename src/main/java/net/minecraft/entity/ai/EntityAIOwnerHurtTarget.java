package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAIOwnerHurtTarget extends EntityAITarget {
   public EntityTameable theEntityTameable;
   public EntityLivingBase theTarget;
   private int timestamp;

   @Override
   public void startExecuting() {
      this.taskOwner.setAttackTarget(this.theTarget);
      EntityLivingBase ownerEntity = this.theEntityTameable.getOwnerEntity();
      if (ownerEntity != null) {
         this.timestamp = ownerEntity.getLastAttackerTime();
      }

      super.startExecuting();
   }

   public EntityAIOwnerHurtTarget(EntityTameable entityTameable) {
      super(entityTameable, false);
      this.theEntityTameable = entityTameable;
      this.setMutexBits(1);
   }

   @Override
   public boolean shouldExecute() {
      if (!this.theEntityTameable.isTamed()) {
         return false;
      } else {
         EntityLivingBase ownerEntity = this.theEntityTameable.getOwnerEntity();
         if (ownerEntity == null) {
            return false;
         } else {
            this.theTarget = ownerEntity.getLastAttacker();
            return ownerEntity.getLastAttackerTime() != this.timestamp && this.isSuitableTarget(this.theTarget, false) && this.theEntityTameable.shouldAttackEntity(this.theTarget, ownerEntity);
         }
      }
   }
}
