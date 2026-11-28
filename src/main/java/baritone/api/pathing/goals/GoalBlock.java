package baritone.api.pathing.goals;

import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.SettingsUtil;
import baritone.api.utils.interfaces.IGoalRenderPos;
import net.minecraft.util.BlockPos;

public class GoalBlock implements Goal, IGoalRenderPos {
   public final int x;
   public final int y;
   public final int z;

   public static double calculate(double d, int n, double d2) {
      double d3 = 0.0;
      d3 += GoalYLevel.calculate(0, n);
      return d3 + GoalXZ.calculate(d, d2);
   }

   @Override
   public BlockPos getGoalPos() {
      return new BlockPos(this.x, this.y, this.z);
   }

   @Override
   public int hashCode() {
      return (int)BetterBlockPos.longHash(this.x, this.y, this.z) * 905165533;
   }

   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      return n == this.x && n2 == this.y && n3 == this.z;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         GoalBlock goalBlock = (GoalBlock)obj;
         return this.x == goalBlock.x && this.y == goalBlock.y && this.z == goalBlock.z;
      } else {
         return false;
      }
   }

   @Override
   public double heuristic(int n, int n2, int n3) {
      return calculate((double)(n - this.x), n2 - this.y, (double)(n3 - this.z));
   }

   public GoalBlock(BlockPos pos) {
      this(pos.getX(), pos.getY(), pos.getZ());
   }

   public GoalBlock(int n, int n2, int n3) {
      this.x = n;
      this.y = n2;
      this.z = n3;
   }

   @Override
   public String toString() {
      return String.format(
         "GoalBlock{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z)
      );
   }
}
