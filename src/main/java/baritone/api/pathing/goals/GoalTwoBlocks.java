package baritone.api.pathing.goals;

import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.SettingsUtil;
import baritone.api.utils.interfaces.IGoalRenderPos;
import net.minecraft.util.BlockPos;

public class GoalTwoBlocks implements IGoalRenderPos, Goal {
   public final int x;
   public final int y;
   public final int z;

   public GoalTwoBlocks(BlockPos pos) {
      this(pos.getX(), pos.getY(), pos.getZ());
   }

   @Override
   public BlockPos getGoalPos() {
      return new BlockPos(this.x, this.y, this.z);
   }

   @Override
   public double heuristic(int n, int n2, int n3) {
      int n4 = n - this.x;
      int n5 = n2 - this.y;
      int n6 = n3 - this.z;
      return GoalBlock.calculate((double)n4, n5 < 0 ? n5 + 1 : n5, (double)n6);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         GoalTwoBlocks goalTwoBlocks = (GoalTwoBlocks)obj;
         return this.x == goalTwoBlocks.x && this.y == goalTwoBlocks.y && this.z == goalTwoBlocks.z;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return (int)BetterBlockPos.longHash(this.x, this.y, this.z) * 516508351;
   }

   public GoalTwoBlocks(int n, int n2, int n3) {
      this.x = n;
      this.y = n2;
      this.z = n3;
   }

   @Override
   public String toString() {
      return String.format(
         "GoalTwoBlocks{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z)
      );
   }

   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      return n == this.x && (n2 == this.y || n2 == this.y - 1) && n3 == this.z;
   }
}
