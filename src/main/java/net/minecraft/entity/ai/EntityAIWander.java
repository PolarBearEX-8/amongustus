package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.Vec3;

public class EntityAIWander extends EntityAIBase {
   private EntityCreature entity;
   private double xPosition;
   private double yPosition;
   private double zPosition;
   private double speed;
   private int executionChance;
   private boolean mustUpdate;

   public EntityAIWander(EntityCreature entityCreature, double d) {
      this(entityCreature, d, 120);
   }

   public void setExecutionChance(int n) {
      this.executionChance = n;
   }

   public void makeUpdate() {
      this.mustUpdate = true;
   }

   @Override
   public boolean continueExecuting() {
      return !this.entity.getNavigator().noPath();
   }

   @Override
   public void startExecuting() {
      this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
   }

   @Override
   public boolean shouldExecute() {
      if (!this.mustUpdate) {
         if (this.entity.getAge() >= 100) {
            return false;
         }

         if (this.entity.getRNG().nextInt(this.executionChance) != 0) {
            return false;
         }
      }

      Vec3 vec = RandomPositionGenerator.findRandomTarget(this.entity, 10, 7);
      if (vec == null) {
         return false;
      } else {
         this.xPosition = vec.xCoord;
         this.yPosition = vec.yCoord;
         this.zPosition = vec.zCoord;
         this.mustUpdate = false;
         return true;
      }
   }

   public EntityAIWander(EntityCreature entityCreature, double d, int n) {
      this.entity = entityCreature;
      this.speed = d;
      this.executionChance = n;
      this.setMutexBits(1);
   }
}
