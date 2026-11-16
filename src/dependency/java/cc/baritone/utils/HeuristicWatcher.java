package cc.baritone.utils;

import cc.SettingsUtil;
import net.minecraft.util.BlockPos;

public class HeuristicWatcher implements VirtualCustom, SecondaryListener {
   public final int count;
   public final int count2;
   public final int count3;

   public HeuristicWatcher(BlockPos blockPos) {
      this(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   @Override
   public BlockPos getBlockPos() {
      return new BlockPos(this.count, this.count2, this.count3);
   }

   @Override
   public double computeValue(int count4, int count5, int count6) {
      int count7 = count4 - this.count;
      int count8 = count5 - this.count2;
      int count9 = count6 - this.count3;
      return DepthFirstResolver.computeValue((double)count7, count8 < 0 ? count8 + 1 : count8, (double)count9);
   }

   @Override
   public String toString() {
      return String.format(
         "GoalTwoBlocks{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.count), SettingsUtil.maybeCensor(this.count2), SettingsUtil.maybeCensor(this.count3)
      );
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         HeuristicWatcher heuristicWatcher = (HeuristicWatcher)other;
         return this.count == heuristicWatcher.count && this.count2 == heuristicWatcher.count2 && this.count3 == heuristicWatcher.count3;
      } else {
         return false;
      }
   }

   public HeuristicWatcher(int count4, int count5, int count6) {
      this.count = count4;
      this.count2 = count5;
      this.count3 = count6;
   }

   @Override
   public int hashCode() {
      return (int)BetterBlockPosXSYSZSBlockPos.computeTimestampByIndex(this.count, this.count2, this.count3) * 516508351;
   }

   @Override
   public boolean checkIndex(int count4, int count5, int count6) {
      return count4 == this.count && (count5 == this.count2 || count5 == this.count2 - 1) && count6 == this.count3;
   }
}
