package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAISit extends EntityAIBase {
   private EntityTameable theEntity;
   private boolean isSitting;

   @Override
   public void startExecuting() {
      this.theEntity.getNavigator().clearPathEntity();
      this.theEntity.setSitting(true);
   }

   public EntityAISit(EntityTameable entityTameable) {
      this.theEntity = entityTameable;
      this.setMutexBits(5);
   }

   @Override
   public void resetTask() {
      this.theEntity.setSitting(false);
   }

   public void setSitting(boolean flag) {
      this.isSitting = flag;
   }

   @Override
   public boolean shouldExecute() {
      if (!this.theEntity.isTamed()) {
         return false;
      } else if (this.theEntity.isInWater()) {
         return false;
      } else if (!this.theEntity.onGround) {
         return false;
      } else {
         EntityLivingBase ownerEntity = this.theEntity.getOwnerEntity();
         return ownerEntity == null ? true : (this.theEntity.getDistanceSqToEntity(ownerEntity) < 144.0 && ownerEntity.getAITarget() != null ? false : this.isSitting);
      }
   }
}
