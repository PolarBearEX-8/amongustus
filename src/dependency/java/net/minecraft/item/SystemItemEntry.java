package net.minecraft.item;

import client.util.BaseFloatEntry;
import client.util.InterpolationConverter;
import client.util.PathfindManagerAdapter;
import client.util.RotationPool;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDarkOakDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityMob;
import net.minecraft.util.BlockPos;

public abstract class SystemItemEntry extends RotationPool {
   public EntityMob entityMob;
   public BlockPos blockPos = BlockPos.origin;
   public BlockDarkOakDoor blockDarkOakDoor;
   public boolean enabled;
   public float factor;
   public float factor2;

   private BlockDarkOakDoor resolveBlockDarkOakDoor(BlockPos blockPos2) {
      Block block = this.entityMob.worldObj.getBlockState(blockPos2).getBlock();
      return block instanceof BlockDarkOakDoor && block.getMaterial() == Material.material ? (BlockDarkOakDoor)block : null;
   }

   @Override
   public void updateState3() {
      this.enabled = false;
      this.factor = (float)((double)((float)this.blockPos.getX() + 0.5F) - this.entityMob.posX);
      this.factor2 = (float)((double)((float)this.blockPos.getZ() + 0.5F) - this.entityMob.posZ);
   }

   @Override
   public void updateState2() {
      float factor3 = (float)((double)((float)this.blockPos.getX() + 0.5F) - this.entityMob.posX);
      float factor4 = (float)((double)((float)this.blockPos.getZ() + 0.5F) - this.entityMob.posZ);
      if (this.factor * factor3 + this.factor2 * factor4 < 0.0F) {
         this.enabled = true;
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return !this.enabled;
   }

   @Override
   public boolean isActive() {
      if (!this.entityMob.isCollidedHorizontally) {
         return false;
      } else {
         PathfindManagerAdapter pathfindManager = (PathfindManagerAdapter)this.entityMob.getPathfindManager();
         InterpolationConverter interpolationConverter = pathfindManager.getInterpolationConverter();
         if (interpolationConverter != null && !interpolationConverter.isActive() && pathfindManager.isActive3()) {
            for (int index = 0; index < Math.min(interpolationConverter.getCount() + 2, interpolationConverter.getCount2()); index++) {
               BaseFloatEntry baseFloatEntry = interpolationConverter.resolveBaseFloatEntry(index);
               this.blockPos = new BlockPos(baseFloatEntry.count, baseFloatEntry.count5 + 1, baseFloatEntry.count3);
               if (this.entityMob.getDouble3((double)this.blockPos.getX(), this.entityMob.posY, (double)this.blockPos.getZ()) <= 2.25) {
                  this.blockDarkOakDoor = this.resolveBlockDarkOakDoor(this.blockPos);
                  if (this.blockDarkOakDoor != null) {
                     return true;
                  }
               }
            }

            this.blockPos = new BlockPos(this.entityMob).up();
            this.blockDarkOakDoor = this.resolveBlockDarkOakDoor(this.blockPos);
            return this.blockDarkOakDoor != null;
         } else {
            return false;
         }
      }
   }

   public SystemItemEntry(EntityMob entityMob2) {
      this.entityMob = entityMob2;
      if (!(entityMob2.getPathfindManager() instanceof PathfindManagerAdapter)) {
         throw new IllegalArgumentException("Unsupported mob type for DoorInteractGoal");
      }
   }
}
