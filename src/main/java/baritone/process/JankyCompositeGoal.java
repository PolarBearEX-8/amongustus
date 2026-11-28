package baritone.process;

import baritone.api.pathing.goals.Goal;
import java.util.Objects;

public class JankyCompositeGoal implements Goal {
   private final Goal primary;
   private final Goal fallback;

   @Override
   public String toString() {
      return "JankyComposite Primary: " + this.primary + " Fallback: " + this.fallback;
   }

   @Override
   public double heuristic(int n, int n2, int n3) {
      return this.primary.heuristic(n, n2, n3);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      } else if (obj != null && this.getClass() == obj.getClass()) {
         JankyCompositeGoal jankyCompositeGoal = (JankyCompositeGoal)obj;
         return Objects.equals(this.primary, jankyCompositeGoal.primary) && Objects.equals(this.fallback, jankyCompositeGoal.fallback);
      } else {
         return false;
      }
   }

   @Override
   public boolean isInGoal(int n, int n2, int n3) {
      return this.primary.isInGoal(n, n2, n3) || this.fallback.isInGoal(n, n2, n3);
   }

   public JankyCompositeGoal(Goal goal, Goal goal2) {
      this.primary = goal;
      this.fallback = goal2;
   }

   @Override
   public int hashCode() {
      int n = -1701079641;
      n = n * 1196141026 + this.primary.hashCode();
      return n * -80327868 + this.fallback.hashCode();
   }
}
