package cc.baritone.utils;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class AStarEstimator {
   public final int count;
   public final BlockPos blockPos;
   public final EnumFacing enumFacing;
   public final DefaultCustom defaultCustom;

   public AStarEstimator(int count2, BlockPos blockPos2, EnumFacing enumFacing2, DefaultCustom defaultCustom2) {
      this.count = count2;
      this.blockPos = blockPos2;
      this.enumFacing = enumFacing2;
      this.defaultCustom = defaultCustom2;
   }

   public static int computeCount(AStarEstimator aStarEstimator) {
      return aStarEstimator.count;
   }

   public static DefaultCustom resolveDefaultCustom(AStarEstimator aStarEstimator) {
      return aStarEstimator.defaultCustom;
   }

   public static EnumFacing resolveEnumFacing(AStarEstimator aStarEstimator) {
      return aStarEstimator.enumFacing;
   }

   public static BlockPos resolveBlockPos(AStarEstimator aStarEstimator) {
      return aStarEstimator.blockPos;
   }
}
