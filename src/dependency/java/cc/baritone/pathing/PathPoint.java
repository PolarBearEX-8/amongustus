package cc.baritone.pathing;

import net.minecraft.util.BlockPos;

public class PathPoint {
   public final BlockPos blockPos;

   public PathPoint(BlockPos blockPos2) {
      this.blockPos = blockPos2;
   }

   public BlockPos getBlockPos() {
      return this.blockPos;
   }
}
