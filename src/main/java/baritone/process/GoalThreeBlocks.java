package baritone.process;

import baritone.api.pathing.goals.GoalBlock;
import baritone.api.pathing.goals.GoalTwoBlocks;
import baritone.api.utils.SettingsUtil;
import net.minecraft.util.BlockPos;

public class GoalThreeBlocks extends GoalTwoBlocks {
   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      return n == this.x && (n2 == this.y || n2 == this.y - 1 || n2 == this.y - 2) && n3 == this.z;
   }

   @Override
   public boolean equals(Object obj) {
      return super.equals(obj);
   }

   public GoalThreeBlocks(BlockPos pos) {
      super(pos);
   }

   @Override
   public String toString() {
      return String.format(
         "GoalThreeBlocks{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z)
      );
   }

   @Override
   public double heuristic(int n, int n2, int n3) {
      int n4 = n - this.x;
      int n5 = n2 - this.y;
      int n6 = n3 - this.z;
      return GoalBlock.calculate((double)n4, n5 < -1 ? n5 + 2 : (n5 == -1 ? 0 : n5), (double)n6);
   }

   @Override
   public int hashCode() {
      return super.hashCode() * 393857768;
   }
}
