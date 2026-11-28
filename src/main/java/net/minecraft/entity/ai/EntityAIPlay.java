package net.minecraft.entity.ai;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.Vec3;

public class EntityAIPlay extends EntityAIBase {
   private EntityVillager villagerObj;
   private EntityLivingBase targetVillager;
   private double speed;
   private int playTime;

   @Override
   public void updateTask() {
      this.playTime--;
      if (this.targetVillager != null) {
         if (this.villagerObj.getDistanceSqToEntity(this.targetVillager) > 4.0) {
            this.villagerObj.getNavigator().tryMoveToEntityLiving(this.targetVillager, this.speed);
         }
      } else if (this.villagerObj.getNavigator().noPath()) {
         Vec3 vec = RandomPositionGenerator.findRandomTarget(this.villagerObj, 16, 3);
         if (vec == null) {
            return;
         }

         this.villagerObj.getNavigator().tryMoveToXYZ(vec.xCoord, vec.yCoord, vec.zCoord, this.speed);
      }
   }

   @Override
   public boolean shouldExecute() {
      if (this.villagerObj.getGrowingAge() >= 0) {
         return false;
      } else if (this.villagerObj.getRNG().nextInt(400) != 0) {
         return false;
      } else {
         List entitiesWithinAABB = this.villagerObj.entityWorld.getEntitiesWithinAABB(EntityVillager.class, this.villagerObj.getEntityBoundingBox().expand(6.0, 3.0, 6.0));
         double maxvalue = Double.MAX_VALUE;

         for (EntityVillager entityVillager : (Iterable<EntityVillager>)(entitiesWithinAABB)) {
            if (entityVillager != this.villagerObj && !entityVillager.isPlaying() && entityVillager.getGrowingAge() < 0) {
               double distanceSqToEntity = entityVillager.getDistanceSqToEntity(this.villagerObj);
               if (distanceSqToEntity <= maxvalue) {
                  maxvalue = distanceSqToEntity;
                  this.targetVillager = entityVillager;
               }
            }
         }

         if (this.targetVillager == null) {
            if (RandomPositionGenerator.findRandomTarget(this.villagerObj, 16, 3) == null) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public void startExecuting() {
      if (this.targetVillager != null) {
         this.villagerObj.setPlaying(true);
      }

      this.playTime = 1000;
   }

   @Override
   public boolean continueExecuting() {
      return this.playTime > 0;
   }

   public EntityAIPlay(EntityVillager entityVillager, double d) {
      this.villagerObj = entityVillager;
      this.speed = d;
      this.setMutexBits(1);
   }

   @Override
   public void resetTask() {
      this.villagerObj.setPlaying(false);
      this.targetVillager = null;
   }
}
