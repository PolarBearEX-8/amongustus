package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;

public class EntityAIMoveTowardsTarget extends EntityAIBase {
   private EntityCreature theEntity;
   private EntityLivingBase targetEntity;
   private double movePosX;
   private double movePosY;
   private double movePosZ;
   private double speed;
   private float maxTargetDistance;

   @Override
   public boolean continueExecuting() {
      return !this.theEntity.getNavigator().noPath()
         && this.targetEntity.isEntityAlive()
         && this.targetEntity.getDistanceSqToEntity(this.theEntity) < (double)(this.maxTargetDistance * this.maxTargetDistance);
   }

   @Override
   public void resetTask() {
      this.targetEntity = null;
   }

   public EntityAIMoveTowardsTarget(EntityCreature entityCreature, double d, float f) {
      this.theEntity = entityCreature;
      this.speed = d;
      this.maxTargetDistance = f;
      this.setMutexBits(1);
   }

   @Override
   public void startExecuting() {
      this.theEntity.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.speed);
   }

   @Override
   public boolean shouldExecute() {
      this.targetEntity = this.theEntity.getAttackTarget();
      if (this.targetEntity == null) {
         return false;
      } else if (this.targetEntity.getDistanceSqToEntity(this.theEntity) > (double)(this.maxTargetDistance * this.maxTargetDistance)) {
         return false;
      } else {
         Vec3 vec = RandomPositionGenerator.findRandomTargetBlockTowards(
            this.theEntity, 16, 7, new Vec3(this.targetEntity.posX, this.targetEntity.posY, this.targetEntity.posZ)
         );
         if (vec == null) {
            return false;
         } else {
            this.movePosX = vec.xCoord;
            this.movePosY = vec.yCoord;
            this.movePosZ = vec.zCoord;
            return true;
         }
      }
   }
}
