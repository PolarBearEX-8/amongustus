package cc.baritone.utils;

import cc.SettingsUtil;
import net.minecraft.util.BlockPos;

public class DynamicCustom extends HeuristicWatcher {
   @Override
   public boolean equals(Object other) {
      return super.equals(other);
   }

   @Override
   public int hashCode() {
      return super.hashCode() * 393857768;
   }

   public DynamicCustom(BlockPos blockPos) {
      super(blockPos);
   }

   @Override
   public double computeValue(int count, int count2, int count3) {
      int count4 = count - this.count;
      int count5 = count2 - this.count2;
      int count6 = count3 - this.count3;
      return DepthFirstResolver.computeValue((double)count4, count5 < -1 ? count5 + 2 : (count5 == -1 ? 0 : count5), (double)count6);
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) {
      return count == this.count && (count2 == this.count2 || count2 == this.count2 - 1 || count2 == this.count2 - 2) && count3 == this.count3;
   }

   @Override
   public String toString() {
      return String.format(
         "GoalThreeBlocks{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.count), SettingsUtil.maybeCensor(this.count2), SettingsUtil.maybeCensor(this.count3)
      );
   }
}
