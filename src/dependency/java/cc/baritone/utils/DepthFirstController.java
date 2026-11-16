package cc.baritone.utils;

import cc.SettingsUtil;
import net.minecraft.util.BlockPos;

public class DepthFirstController extends DepthFirstResolver {
   @Override
   public int hashCode() {
      return super.hashCode() * 1910811835;
   }

   @Override
   public double computeValue(int count, int count2, int count3) {
      return (double)(this.count2 * 100) + super.computeValue(count, count2, count3);
   }

   public DepthFirstController(BlockPos blockPos) {
      super(blockPos.up());
   }

   @Override
   public String toString() {
      return String.format(
         "GoalPlace{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.count), SettingsUtil.maybeCensor(this.count2), SettingsUtil.maybeCensor(this.count3)
      );
   }
}
