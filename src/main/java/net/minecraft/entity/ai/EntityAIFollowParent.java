package net.minecraft.entity.ai;

import java.util.List;
import net.minecraft.entity.passive.EntityAnimal;

public class EntityAIFollowParent extends EntityAIBase {
   public EntityAnimal childAnimal;
   public EntityAnimal parentAnimal;
   public double moveSpeed;
   private int delayCounter;

   @Override
   public void updateTask() {
      if (--this.delayCounter <= 0) {
         this.delayCounter = 10;
         this.childAnimal.getNavigator().tryMoveToEntityLiving(this.parentAnimal, this.moveSpeed);
      }
   }

   @Override
   public void startExecuting() {
      this.delayCounter = 0;
   }

   public EntityAIFollowParent(EntityAnimal entityAnimal, double d) {
      this.childAnimal = entityAnimal;
      this.moveSpeed = d;
   }

   @Override
   public void resetTask() {
      this.parentAnimal = null;
   }

   @Override
   public boolean shouldExecute() {
      if (this.childAnimal.getGrowingAge() >= 0) {
         return false;
      } else {
         List entitiesWithinAABB = this.childAnimal.entityWorld.getEntitiesWithinAABB(this.childAnimal.getClass(), this.childAnimal.getEntityBoundingBox().expand(8.0, 4.0, 8.0));
         EntityAnimal entityAnimal = null;
         double maxvalue = Double.MAX_VALUE;

         for (EntityAnimal entityAnimal2 : (Iterable<EntityAnimal>)(entitiesWithinAABB)) {
            if (entityAnimal2.getGrowingAge() >= 0) {
               double distanceSqToEntity = this.childAnimal.getDistanceSqToEntity(entityAnimal2);
               if (distanceSqToEntity <= maxvalue) {
                  maxvalue = distanceSqToEntity;
                  entityAnimal = entityAnimal2;
               }
            }
         }

         if (entityAnimal == null) {
            return false;
         } else if (maxvalue < 9.0) {
            return false;
         } else {
            this.parentAnimal = entityAnimal;
            return true;
         }
      }
   }

   @Override
   public boolean continueExecuting() {
      if (this.childAnimal.getGrowingAge() >= 0) {
         return false;
      } else if (!this.parentAnimal.isEntityAlive()) {
         return false;
      } else {
         double distanceSqToEntity = this.childAnimal.getDistanceSqToEntity(this.parentAnimal);
         return distanceSqToEntity >= 9.0 && distanceSqToEntity <= 256.0;
      }
   }
}
