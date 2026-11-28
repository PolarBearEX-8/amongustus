package baritone.process;

import baritone.api.pathing.goals.GoalGetToBlock;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.SettingsUtil;
import java.util.Objects;
import net.minecraft.util.BlockPos;

public class GoalAdjacent extends GoalGetToBlock {
   private boolean noRightSideUpAir;
   private BlockPos noStand;

   @Override
   public double heuristic(int n, int n2, int n3) {
      return (double)(this.y * 100) + super.heuristic(n, n2, n3);
   }

   @Override
   public int hashCode() {
      int n = 806368046;
      n = n * 1412661222 + super.hashCode();
      n = n * 1730799370 + (int)BetterBlockPos.longHash(this.noStand.getX(), this.noStand.getY(), this.noStand.getZ());
      return n * 260592149 + (this.noRightSideUpAir ? -1314802005 : 1565710265);
   }

   @Override
   public boolean equals(Object obj) {
      if (!super.equals(obj)) {
         return false;
      } else {
         GoalAdjacent goalAdjacent = (GoalAdjacent)obj;
         return this.noRightSideUpAir == goalAdjacent.noRightSideUpAir && Objects.equals(this.noStand, goalAdjacent.noStand);
      }
   }

   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      if (n == this.x && n2 == this.y && n3 == this.z) {
         return false;
      } else if (n == this.noStand.getX() && n2 == this.noStand.getY() && n3 == this.noStand.getZ()) {
         return false;
      } else if (!this.noRightSideUpAir && n2 == this.y - 1) {
         return false;
      } else {
         return n2 < this.y - 1 ? false : super.isInGoal(n, n2, n3);
      }
   }

   @Override
   public String toString() {
      return String.format(
         "GoalAdjacent{x=%s,y=%s,z=%s}", SettingsUtil.maybeCensor(this.x), SettingsUtil.maybeCensor(this.y), SettingsUtil.maybeCensor(this.z)
      );
   }

   public GoalAdjacent(BlockPos pos, BlockPos pos2, boolean flag) {
      super(pos);
      this.noStand = pos2;
      this.noRightSideUpAir = flag;
   }
}
