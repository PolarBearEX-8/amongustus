package baritone.api.pathing.goals;

import java.util.Objects;

public class GoalInverted implements Goal {
   public final Goal origin;

   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      return false;
   }

   public GoalInverted(Goal goal) {
      this.origin = goal;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         GoalInverted goalInverted = (GoalInverted)obj;
         return Objects.equals(this.origin, goalInverted.origin);
      } else {
         return false;
      }
   }

   @Override
   public double heuristic() {
      return Double.NEGATIVE_INFINITY;
   }

   @Override
   public String toString() {
      return String.format("GoalInverted{%s}", this.origin.toString());
   }

   @Override
   public double heuristic(int n, int n2, int n3) {
      return -this.origin.heuristic(n, n2, n3);
   }

   @Override
   public int hashCode() {
      return this.origin.hashCode() * 495796690;
   }
}
