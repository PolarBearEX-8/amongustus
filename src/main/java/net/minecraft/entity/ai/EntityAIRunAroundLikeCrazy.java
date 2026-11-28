package net.minecraft.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class EntityAIRunAroundLikeCrazy extends EntityAIBase {
   private EntityHorse horseHost;
   private double speed;
   private double targetX;
   private double targetY;
   private double targetZ;

   public EntityAIRunAroundLikeCrazy(EntityHorse entityHorse, double d) {
      this.horseHost = entityHorse;
      this.speed = d;
      this.setMutexBits(1);
   }

   @Override
   public boolean continueExecuting() {
      return !this.horseHost.getNavigator().noPath() && this.horseHost.riddenByEntity != null;
   }

   @Override
   public boolean shouldExecute() {
      if (!this.horseHost.isTame() && this.horseHost.riddenByEntity != null) {
         Vec3 vec = RandomPositionGenerator.findRandomTarget(this.horseHost, 5, 4);
         if (vec == null) {
            return false;
         } else {
            this.targetX = vec.xCoord;
            this.targetY = vec.yCoord;
            this.targetZ = vec.zCoord;
            return true;
         }
      } else {
         return false;
      }
   }

   @Override
   public void updateTask() {
      if (this.horseHost.getRNG().nextInt(50) == 0) {
         if (this.horseHost.riddenByEntity instanceof EntityPlayer) {
            int temper = this.horseHost.getTemper();
            int maxTemper = this.horseHost.getMaxTemper();
            if (maxTemper > 0 && this.horseHost.getRNG().nextInt(maxTemper) < temper) {
               this.horseHost.setTamedBy((EntityPlayer)this.horseHost.riddenByEntity);
               this.horseHost.entityWorld.setEntityState(this.horseHost, (byte)7);
               return;
            }

            this.horseHost.increaseTemper(5);
         }

         this.horseHost.riddenByEntity.mountEntity((Entity)null);
         this.horseHost.riddenByEntity = null;
         this.horseHost.makeHorseRearWithSound();
         this.horseHost.entityWorld.setEntityState(this.horseHost, (byte)6);
      }
   }

   @Override
   public void startExecuting() {
      this.horseHost.getNavigator().tryMoveToXYZ(this.targetX, this.targetY, this.targetZ, this.speed);
   }
}
