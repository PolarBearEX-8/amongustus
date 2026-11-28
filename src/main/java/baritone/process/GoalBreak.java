package baritone.process;

import baritone.api.pathing.goals.GoalGetToBlock;
import baritone.api.utils.SettingsUtil;
import net.minecraft.util.BlockPos;

public class GoalBreak extends GoalGetToBlock {
   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      return n2 > this.y ? false : super.isInGoal(n, n2, n3);
   }

   @Override
   public String toString() {
      return String.format(
         "GoalBreak{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z)
      );
   }

   public GoalBreak(BlockPos pos) {
      super(pos);
   }

   @Override
   public int hashCode() {
      return super.hashCode() * 1636324008;
   }
}
