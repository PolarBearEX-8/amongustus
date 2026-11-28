package net.minecraft.entity.ai;

import net.minecraft.entity.EntityLiving;

public class EntityAIOpenDoor extends EntityAIDoorInteract {
   public boolean closeDoor;
   public int closeDoorTemporisation;

   @Override
   public void updateTask() {
      this.closeDoorTemporisation--;
      super.updateTask();
   }

   @Override
   public void resetTask() {
      if (this.closeDoor) {
         this.doorBlock.toggleDoor(this.theEntity.entityWorld, this.doorPosition, false);
      }
   }

   public EntityAIOpenDoor(EntityLiving entityLiving, boolean flag) {
      super(entityLiving);
      this.theEntity = entityLiving;
      this.closeDoor = flag;
   }

   @Override
   public void startExecuting() {
      this.closeDoorTemporisation = 20;
      this.doorBlock.toggleDoor(this.theEntity.entityWorld, this.doorPosition, true);
   }

   @Override
   public boolean continueExecuting() {
      return this.closeDoor && this.closeDoorTemporisation > 0 && super.continueExecuting();
   }
}
