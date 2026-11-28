package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class EntityAIMoveTowardsRestriction extends EntityAIBase {
   private EntityCreature theEntity;
   private double movePosX;
   private double movePosY;
   private double movePosZ;
   private double movementSpeed;

   @Override
   public boolean continueExecuting() {
      return !this.theEntity.getNavigator().noPath();
   }

   @Override
   public boolean shouldExecute() {
      if (this.theEntity.isWithinHomeDistanceCurrentPosition()) {
         return false;
      } else {
         BlockPos homePosition = this.theEntity.getHomePosition();
         Vec3 vec = RandomPositionGenerator.findRandomTargetBlockTowards(
            this.theEntity, 16, 7, new Vec3((double)homePosition.getX(), (double)homePosition.getY(), (double)homePosition.getZ())
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

   @Override
   public void startExecuting() {
      this.theEntity.getNavigator().tryMoveToXYZ(this.movePosX, this.movePosY, this.movePosZ, this.movementSpeed);
   }

   public EntityAIMoveTowardsRestriction(EntityCreature entityCreature, double d) {
      this.theEntity = entityCreature;
      this.movementSpeed = d;
      this.setMutexBits(1);
   }
}
