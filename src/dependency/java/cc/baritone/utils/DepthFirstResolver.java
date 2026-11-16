package cc.baritone.utils;

import cc.SettingsUtil;
import net.minecraft.util.BlockPos;

public class DepthFirstResolver implements VirtualCustom, SecondaryListener {
   public final int count;
   public final int count2;
   public final int count3;

   @Override
   public double computeValue(int count4, int count5, int count6) {
      int count7 = count4 - this.count;
      int count8 = count5 - this.count2;
      int count9 = count6 - this.count3;
      return computeValue((double)count7, count8, (double)count9);
   }

   @Override
   public boolean equals(Object other) {
      if (this == other) {
         return true;
      } else if (other != null && this.getClass() == other.getClass()) {
         DepthFirstResolver depthFirstResolver = (DepthFirstResolver)other;
         return this.count == depthFirstResolver.count && this.count2 == depthFirstResolver.count2 && this.count3 == depthFirstResolver.count3;
      } else {
         return false;
      }
   }

   public static double computeValue(double offset, int count4, double offset2) {
      return 0.0 + BreadthFirstInspector.computeValue(0, count4) + DiagonalEvaluator.computeValue(offset, offset2);
   }

   @Override
   public int hashCode() {
      return (int)BetterBlockPosXSYSZSBlockPos.computeTimestampByIndex(this.count, this.count2, this.count3) * 905165533;
   }

   @Override
   public BlockPos getBlockPos() {
      return new BlockPos(this.count, this.count2, this.count3);
   }

   public DepthFirstResolver(BlockPos blockPos) {
      this(blockPos.getX(), blockPos.getY(), blockPos.getZ());
   }

   public DepthFirstResolver(int count4, int count5, int count6) {
      this.count = count4;
      this.count2 = count5;
      this.count3 = count6;
   }

   @Override
   public boolean checkIndex(int count4, int count5, int count6) {
      return count4 == this.count && count5 == this.count2 && count6 == this.count3;
   }

   @Override
   public String toString() {
      return String.format(
         "GoalBlock{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.count), SettingsUtil.maybeCensor(this.count2), SettingsUtil.maybeCensor(this.count3)
      );
   }
}
