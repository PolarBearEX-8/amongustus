package cc.baritone.utils;

import net.minecraft.util.BlockPos;

public final class SecondaryCustom {
   public final BlockPos blockPos;
   public final HeuristicSelector heuristicSelector;

   public final BlockPos getBlockPos() {
      return this.blockPos;
   }

   public SecondaryCustom(BlockPos blockPos2, HeuristicSelector heuristicSelector2) {
      this.blockPos = blockPos2;
      this.heuristicSelector = heuristicSelector2;
   }

   public final HeuristicSelector getHeuristicSelector() {
      return this.heuristicSelector;
   }
}
