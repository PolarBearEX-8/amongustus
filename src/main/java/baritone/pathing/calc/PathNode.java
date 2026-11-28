package baritone.pathing.calc;

import baritone.api.pathing.goals.Goal;
import baritone.api.utils.BetterBlockPos;

public final class PathNode {
   public final int x;
   public final int y;
   public final int z;
   public final double estimatedCostToGoal;
   public double cost;
   public double combinedCost;
   public PathNode previous = null;
   public int heapPosition;

   @Override
   public int hashCode() {
      return (int)BetterBlockPos.longHash(this.x, this.y, this.z);
   }

   public PathNode(int n, int n2, int n3, Goal goal) {
      this.cost = 1000000.0;
      this.estimatedCostToGoal = goal.heuristic(n, n2, n3);
      if (Double.isNaN(this.estimatedCostToGoal)) {
         throw new IllegalStateException(goal + " calculated implausible heuristic");
      } else {
         this.heapPosition = -1;
         this.x = n;
         this.y = n2;
         this.z = n3;
      }
   }

   public boolean isOpen() {
      return this.heapPosition != -1;
   }

   @Override
   public boolean equals(Object obj) {
      PathNode pathNode = (PathNode)obj;
      return this.x == pathNode.x && this.y == pathNode.y && this.z == pathNode.z;
   }
}
