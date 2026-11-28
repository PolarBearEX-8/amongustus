package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.EnumDifficulty;

public class EntityAIBreakDoor extends EntityAIDoorInteract {
   private int breakingTime;
   private int previousBreakProgress = -1;

   public EntityAIBreakDoor(EntityLiving entityLiving) {
      super(entityLiving);
   }

   @Override
   public void startExecuting() {
      super.startExecuting();
      this.breakingTime = 0;
   }

   @Override
   public boolean continueExecuting() {
      double distanceSq = this.theEntity.getDistanceSq(this.doorPosition);
      if (this.breakingTime <= 240) {
         BlockDoor doorBlock2 = this.doorBlock;
         if (!BlockDoor.isOpen(this.theEntity.entityWorld, this.doorPosition) && distanceSq < 4.0) {
            return true;
         }
      }

      return false;
   }

   @Override
   public void updateTask() {
      super.updateTask();
      if (this.theEntity.getRNG().nextInt(20) == 0) {
         this.theEntity.entityWorld.playAuxSFX(1010, this.doorPosition, 0);
      }

      this.breakingTime++;
      int n = (int)((float)this.breakingTime / 240.0F * 10.0F);
      if (n != this.previousBreakProgress) {
         this.theEntity.entityWorld.sendBlockBreakProgress(this.theEntity.getEntityId(), this.doorPosition, n);
         this.previousBreakProgress = n;
      }

      if (this.breakingTime == 240 && this.theEntity.entityWorld.getDifficulty() == EnumDifficulty.HARD) {
         this.theEntity.entityWorld.setBlockToAir(this.doorPosition);
         this.theEntity.entityWorld.playAuxSFX(1012, this.doorPosition, 0);
         this.theEntity.entityWorld.playAuxSFX(2001, this.doorPosition, Block.getIdFromBlock(this.doorBlock));
      }
   }

   @Override
   public void resetTask() {
      super.resetTask();
      this.theEntity.entityWorld.sendBlockBreakProgress(this.theEntity.getEntityId(), this.doorPosition, -1);
   }

   @Override
   public boolean shouldExecute() {
      if (!super.shouldExecute()) {
         return false;
      } else if (!this.theEntity.entityWorld.getGameRules().getBoolean("mobGriefing")) {
         return false;
      } else {
         BlockDoor doorBlock2 = this.doorBlock;
         return !BlockDoor.isOpen(this.theEntity.entityWorld, this.doorPosition);
      }
   }
}
