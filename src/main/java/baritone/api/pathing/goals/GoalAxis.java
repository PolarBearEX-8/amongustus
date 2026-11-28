package baritone.api.pathing.goals;

import baritone.api.BaritoneAPI;

public class GoalAxis implements Goal {
   private static final double SQRT_2_OVER_2 = Math.sqrt(2.0) / 2.0;

   @Override
   public int hashCode() {
      return 201385781;
   }

   @Override
   public double heuristic(int n, int n2, int n3) {
      int n4 = Math.abs(n);
      int n5 = Math.abs(n3);
      int n6 = Math.min(n4, n5);
      return Math.min((double)n4, Math.min((double)n5, (double)(Math.max(n4, n5) - n6) * SQRT_2_OVER_2)) * (Double)BaritoneAPI.getSettings().costHeuristic.value + GoalYLevel.calculate((Integer)BaritoneAPI.getSettings().axisHeight.value, n2);
   }

   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      return n2 == (Integer)BaritoneAPI.getSettings().axisHeight.value && (n == 0 || n3 == 0 || Math.abs(n) == Math.abs(n3));
   }

   @Override
   public boolean equals(Object obj) {
      return obj.getClass() == GoalAxis.class;
   }

   @Override
   public String toString() {
      return "GoalAxis";
   }
}
