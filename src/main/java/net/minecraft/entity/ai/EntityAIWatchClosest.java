package net.minecraft.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIWatchClosest extends EntityAIBase {
   public EntityLiving theWatcher;
   public Entity closestEntity;
   public float maxDistanceForPlayer;
   private int lookTime;
   private float chance;
   public Class watchedClass;

   @Override
   public void startExecuting() {
      this.lookTime = 40 + this.theWatcher.getRNG().nextInt(40);
   }

   @Override
   public boolean continueExecuting() {
      return !this.closestEntity.isEntityAlive()
         ? false
         : (
            this.theWatcher.getDistanceSqToEntity(this.closestEntity) > (double)(this.maxDistanceForPlayer * this.maxDistanceForPlayer)
               ? false
               : this.lookTime > 0
         );
   }

   public EntityAIWatchClosest(EntityLiving entityLiving, Class clazz, float f, float f2) {
      this.theWatcher = entityLiving;
      this.watchedClass = clazz;
      this.maxDistanceForPlayer = f;
      this.chance = f2;
      this.setMutexBits(2);
   }

   public EntityAIWatchClosest(EntityLiving entityLiving, Class clazz, float f) {
      this.theWatcher = entityLiving;
      this.watchedClass = clazz;
      this.maxDistanceForPlayer = f;
      this.chance = 0.02F;
      this.setMutexBits(2);
   }

   @Override
   public void updateTask() {
      this.theWatcher
         .getLookHelper()
         .setLookPosition(
            this.closestEntity.posX,
            this.closestEntity.posY + (double)this.closestEntity.getEyeHeight(),
            this.closestEntity.posZ,
            10.0F,
            (float)this.theWatcher.getVerticalFaceSpeed()
         );
      this.lookTime--;
   }

   @Override
   public boolean shouldExecute() {
      if (this.theWatcher.getRNG().nextFloat() >= this.chance) {
         return false;
      } else {
         if (this.theWatcher.getAttackTarget() != null) {
            this.closestEntity = this.theWatcher.getAttackTarget();
         }

         if (this.watchedClass == EntityPlayer.class) {
            this.closestEntity = this.theWatcher.entityWorld.getClosestPlayerToEntity(this.theWatcher, (double)this.maxDistanceForPlayer);
         } else {
            this.closestEntity = this.theWatcher
               .entityWorld
               .findNearestEntityWithinAABB(
                  this.watchedClass,
                  this.theWatcher.getEntityBoundingBox().expand((double)this.maxDistanceForPlayer, 3.0, (double)this.maxDistanceForPlayer),
                  this.theWatcher
               );
         }

         return this.closestEntity != null;
      }
   }

   @Override
   public void resetTask() {
      this.closestEntity = null;
   }
}
