package baritone.api.pathing.goals;

import java.util.Arrays;

public class GoalComposite implements Goal {
   private final Goal[] goals;

   public Goal[] getGoals() {
      return this.goals;
   }

   @Override
   public String toString() {
      return "GoalComposite" + Arrays.toString((Object[])this.goals);
   }

   @Override
   public int hashCode() {
      return Arrays.hashCode((Object[])this.goals);
   }

   public GoalComposite(Goal[] goals2) {
      this.goals = goals2;
   }

   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      for (Goal goal : this.goals) {
         if (goal.isInGoal(n, n2, n3)) {
            return true;
         }
      }

      return false;
   }

   @Override
   public double heuristic(int n, int n2, int n3) {
      double maxvalue = Double.MAX_VALUE;

      for (Goal goal : this.goals) {
         maxvalue = Math.min(maxvalue, goal.heuristic(n, n2, n3));
      }

      return maxvalue;
   }

   @Override
   public double heuristic() {
      double maxvalue = Double.MAX_VALUE;

      for (Goal goal : this.goals) {
         maxvalue = Math.min(maxvalue, goal.heuristic());
      }

      return maxvalue;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         GoalComposite goalComposite = (GoalComposite)obj;
         return Arrays.equals((Object[])this.goals, (Object[])goalComposite.goals);
      } else {
         return false;
      }
   }
}
