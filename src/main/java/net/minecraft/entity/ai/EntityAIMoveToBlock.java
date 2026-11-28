package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class EntityAIMoveToBlock extends EntityAIBase {
   private final EntityCreature theEntity;
   private final double movementSpeed;
   public int runDelay;
   private int timeoutCounter;
   private int maxStayTicks;
   public BlockPos destinationBlock = BlockPos.ORIGIN;
   private boolean isAboveDestination;
   private int searchLength;

   @Override
   public void startExecuting() {
      this.theEntity
         .getNavigator()
         .tryMoveToXYZ(
            (double)((float)this.destinationBlock.getX()) + 0.5,
            (double)(this.destinationBlock.getY() + 1),
            (double)((float)this.destinationBlock.getZ()) + 0.5,
            this.movementSpeed
         );
      this.timeoutCounter = 0;
      this.maxStayTicks = this.theEntity.getRNG().nextInt(this.theEntity.getRNG().nextInt(1200) + 1200) + 1200;
   }

   @Override
   public void updateTask() {
      if (this.theEntity.getDistanceSqToCenter(this.destinationBlock.up()) > 1.0) {
         this.isAboveDestination = false;
         this.timeoutCounter++;
         if (this.timeoutCounter % 40 == 0) {
            this.theEntity
               .getNavigator()
               .tryMoveToXYZ(
                  (double)((float)this.destinationBlock.getX()) + 0.5,
                  (double)(this.destinationBlock.getY() + 1),
                  (double)((float)this.destinationBlock.getZ()) + 0.5,
                  this.movementSpeed
               );
         }
      } else {
         this.isAboveDestination = true;
         this.timeoutCounter--;
      }
   }

   public boolean getIsAboveDestination() {
      return this.isAboveDestination;
   }

   public abstract boolean shouldMoveTo(World world, BlockPos pos);

   @Override
   public boolean shouldExecute() {
      if (this.runDelay > 0) {
         this.runDelay--;
         return false;
      } else {
         this.runDelay = 200 + this.theEntity.getRNG().nextInt(200);
         return this.searchForDestination();
      }
   }

   @Override
   public void resetTask() {
   }

   public EntityAIMoveToBlock(EntityCreature entityCreature, double d, int n) {
      this.theEntity = entityCreature;
      this.movementSpeed = d;
      this.searchLength = n;
      this.setMutexBits(5);
   }

   private boolean searchForDestination() {
      int searchLength2 = this.searchLength;
      BlockPos pos = new BlockPos(this.theEntity);

      for (int i = 0; i <= 1; i = i > 0 ? -i : 1 - i) {
         for (int j = 0; j < searchLength2; j++) {
            for (int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
               for (int m = k < j && k > -j ? j : 0; m <= j; m = m > 0 ? -m : 1 - m) {
                  BlockPos pos2 = pos.add(k, i - 1, m);
                  if (this.theEntity.isWithinHomeDistanceFromPosition(pos2) && this.shouldMoveTo(this.theEntity.entityWorld, pos2)) {
                     this.destinationBlock = pos2;
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   @Override
   public boolean continueExecuting() {
      return this.timeoutCounter >= -this.maxStayTicks && this.timeoutCounter <= 1200 && this.shouldMoveTo(this.theEntity.entityWorld, this.destinationBlock);
   }
}
