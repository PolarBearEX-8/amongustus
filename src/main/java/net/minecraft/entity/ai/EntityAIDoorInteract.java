package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.BlockPos;

public abstract class EntityAIDoorInteract extends EntityAIBase {
   public EntityLiving theEntity;
   public BlockPos doorPosition = BlockPos.ORIGIN;
   public BlockDoor doorBlock;
   public boolean hasStoppedDoorInteraction;
   public float entityPositionX;
   public float entityPositionZ;

   @Override
   public void startExecuting() {
      this.hasStoppedDoorInteraction = false;
      this.entityPositionX = (float)((double)((float)this.doorPosition.getX() + 0.5F) - this.theEntity.posX);
      this.entityPositionZ = (float)((double)((float)this.doorPosition.getZ() + 0.5F) - this.theEntity.posZ);
   }

   @Override
   public void updateTask() {
      float f = (float)((double)((float)this.doorPosition.getX() + 0.5F) - this.theEntity.posX);
      float f2 = (float)((double)((float)this.doorPosition.getZ() + 0.5F) - this.theEntity.posZ);
      if ((this.entityPositionX * f + this.entityPositionZ * f2) < 0.0F) {
         this.hasStoppedDoorInteraction = true;
      }
   }

   @Override
   public boolean continueExecuting() {
      return !this.hasStoppedDoorInteraction;
   }

   public EntityAIDoorInteract(EntityLiving entityLiving) {
      this.theEntity = entityLiving;
      if (!(entityLiving.getNavigator() instanceof PathNavigateGround)) {
         throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
      }
   }

   private BlockDoor getBlockDoor(BlockPos pos) {
      Block block = this.theEntity.entityWorld.getBlockState(pos).getBlock();
      return block instanceof BlockDoor && block.getMaterial() == Material.wood ? (BlockDoor)block : null;
   }

   @Override
   public boolean shouldExecute() {
      if (!this.theEntity.isCollidedHorizontally) {
         return false;
      } else {
         PathNavigateGround navigator = (PathNavigateGround)this.theEntity.getNavigator();
         PathEntity path = navigator.getPath();
         if (path != null && !path.isFinished() && navigator.getEnterDoors()) {
            for (int i = 0; i < Math.min(path.getCurrentPathIndex() + 2, path.getCurrentPathLength()); i++) {
               PathPoint pathPointFromIndex = path.getPathPointFromIndex(i);
               this.doorPosition = new BlockPos(pathPointFromIndex.xCoord, pathPointFromIndex.yCoord + 1, pathPointFromIndex.zCoord);
               if (this.theEntity.getDistanceSq((double)this.doorPosition.getX(), this.theEntity.posY, (double)this.doorPosition.getZ()) <= 2.25) {
                  this.doorBlock = this.getBlockDoor(this.doorPosition);
                  if (this.doorBlock != null) {
                     return true;
                  }
               }
            }

            this.doorPosition = new BlockPos(this.theEntity).up();
            this.doorBlock = this.getBlockDoor(this.doorPosition);
            return this.doorBlock != null;
         } else {
            return false;
         }
      }
   }
}
