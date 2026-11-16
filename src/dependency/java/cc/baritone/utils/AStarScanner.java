package cc.baritone.utils;

import cc.SettingsUtil;
import net.minecraft.util.BlockPos;

public class AStarScanner extends AStarManager {
   public AStarScanner(BlockPos blockPos) {
      super(blockPos);
   }

   @Override
   public int hashCode() {
      return super.hashCode() * 1636324008;
   }

   @Override
   public String toString() {
      return String.format(
         "GoalBreak{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.count2), SettingsUtil.maybeCensor(this.count), SettingsUtil.maybeCensor(this.count3)
      );
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) {
      return count2 > this.count ? false : super.checkIndex(count, count2, count3);
   }
}
