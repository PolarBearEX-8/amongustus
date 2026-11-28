package baritone.process;

import baritone.api.pathing.goals.GoalBlock;
import baritone.api.utils.SettingsUtil;
import net.minecraft.util.BlockPos;

public class GoalPlace extends GoalBlock {
   @Override
   public double heuristic(int n, int n2, int n3) {
      return (double)(this.y * 100) + super.heuristic(n, n2, n3);
   }

   @Override
   public String toString() {
      return String.format(
         "GoalPlace{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z)
      );
   }

   @Override
   public int hashCode() {
      return super.hashCode() * 1910811835;
   }

   public GoalPlace(BlockPos pos) {
      super(pos.up());
   }
}
