package baritone.api.pathing.goals;

import baritone.api.pathing.movement.ActionCosts;
import baritone.api.utils.SettingsUtil;

public class GoalYLevel implements Goal, ActionCosts {
   public final int level;

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         GoalYLevel goalYLevel = (GoalYLevel)obj;
         return this.level == goalYLevel.level;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return this.level * 1271009915;
   }

   public static double calculate(int n, int n2) {
      if (n2 > n) {
         return FALL_N_BLOCKS_COST[2] / 2.0 * (double)(n2 - n);
      } else {
         return n2 < n ? (double)(n - n2) * JUMP_ONE_BLOCK_COST : 0.0;
      }
   }

   public GoalYLevel(int n) {
      this.level = n;
   }

   @Override
   public double heuristic(int n, int n2, int n3) {
      return calculate(this.level, n2);
   }

   @Override
   public String toString() {
      return String.format("GoalYLevel{y=%s}", SettingsUtil.maybeCensor(this.level));
   }

   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      return n2 == this.level;
   }
}
