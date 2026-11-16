package client.util;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;

public class RotationWatcher {
   public BlockPos blockPos;
   public Block block;
   public int count;
   public int count2;

   public int getCount() {
      return this.count;
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public String toString() {
      return "TE(" + this.blockPos + ")," + this.count + "," + this.count2 + "," + this.block;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }

   public Block getBlock() {
      return this.block;
   }

   public RotationWatcher(BlockPos blockPos2, Block block2, int count3, int count4) {
      this.blockPos = blockPos2;
      this.count = count3;
      this.count2 = count4;
      this.block = block2;
   }

   @Override
   public boolean equals(Object other) {
      if (!(other instanceof RotationWatcher)) {
         return false;
      } else {
         RotationWatcher rotationWatcher = (RotationWatcher)other;
         return this.blockPos.equals(rotationWatcher.blockPos) && this.count == rotationWatcher.count && this.count2 == rotationWatcher.count2 && this.block == rotationWatcher.block;
      }
   }
}
