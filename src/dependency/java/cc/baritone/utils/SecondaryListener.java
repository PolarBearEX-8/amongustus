package cc.baritone.utils;

import net.minecraft.util.BlockPos;

public interface SecondaryListener {
   double computeValue(int count, int count2, int count3);

   boolean checkIndex(int count, int count2, int count3);

   default double computeValue(BlockPos blockPos) {
      return this.computeValue(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   default double getValue() {
      return 0.0;
   }

   default boolean matchesBlockPos(BlockPos blockPos) {
      return this.checkIndex(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }
}
