package baritone.pathing.path;

import baritone.api.pathing.calc.IPath;
import baritone.api.pathing.goals.Goal;
import baritone.utils.pathing.PathBase;
import java.util.Collections;
import java.util.List;

public class CutoffPath extends PathBase {
   private final List path;
   private final List movements;
   private final int numNodes;
   private final Goal goal;

   public CutoffPath(IPath path2, int n) {
      this(path2, 0, n);
   }

   @Override
   public int getNumNodesConsidered() {
      return this.numNodes;
   }

   @Override
   public Goal getGoal() {
      return this.goal;
   }

   @Override
   public List positions() {
      return Collections.unmodifiableList(this.path);
   }

   public CutoffPath(IPath path2, int n, int n2) {
      this.path = path2.positions().subList(n, n2 + 1);
      this.movements = path2.movements().subList(n, n2);
      this.numNodes = path2.getNumNodesConsidered();
      this.goal = path2.getGoal();
      this.sanityCheck();
   }

   @Override
   public List movements() {
      return Collections.unmodifiableList(this.movements);
   }
}
