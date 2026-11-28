package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.Vec3;

public class EntityAIPanic extends EntityAIBase {
   private EntityCreature theEntityCreature;
   public double speed;
   private double randPosX;
   private double randPosY;
   private double randPosZ;

   @Override
   public boolean shouldExecute() {
      if (this.theEntityCreature.getAITarget() == null && !this.theEntityCreature.isBurning()) {
         return false;
      } else {
         Vec3 vec = RandomPositionGenerator.findRandomTarget(this.theEntityCreature, 5, 4);
         if (vec == null) {
            return false;
         } else {
            this.randPosX = vec.xCoord;
            this.randPosY = vec.yCoord;
            this.randPosZ = vec.zCoord;
            return true;
         }
      }
   }

   @Override
   public void startExecuting() {
      this.theEntityCreature.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
   }

   @Override
   public boolean continueExecuting() {
      return !this.theEntityCreature.getNavigator().noPath();
   }

   public EntityAIPanic(EntityCreature entityCreature, double d) {
      this.theEntityCreature = entityCreature;
      this.speed = d;
      this.setMutexBits(1);
   }
}
