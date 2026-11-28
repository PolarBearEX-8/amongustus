package baritone.pathing.path;

import baritone.api.pathing.calc.IPath;
import baritone.api.pathing.goals.Goal;
import baritone.utils.pathing.PathBase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class SplicedPath extends PathBase {
   private final List path;
   private final List movements;
   private final int numNodes;
   private final Goal goal;

   public static Optional trySplice(IPath path2, IPath path3, boolean flag) {
      if (path3 != null && path2 != null) {
         if (!path2.getDest().equals(path3.getSrc())) {
            return Optional.empty();
         } else {
            HashSet set = new HashSet(path3.positions());
            int n = -1;

            for (int i = 0; i < path2.length() - 1; i++) {
               if (set.contains(path2.positions().get(i))) {
                  n = i;
                  break;
               }
            }

            if (n != -1) {
               if (!flag) {
                  return Optional.empty();
               }
            } else {
               n = path2.length() - 1;
            }

            int n2 = path3.positions().indexOf(path2.positions().get(n));
            if (!flag && n2 != 0) {
               throw new IllegalStateException();
            } else {
               ArrayList list = new ArrayList();
               ArrayList list2 = new ArrayList();
               list.addAll(path2.positions().subList(0, n + 1));
               list2.addAll(path2.movements().subList(0, n));
               list.addAll(path3.positions().subList(n2 + 1, path3.length()));
               list2.addAll(path3.movements().subList(n2, path3.length() - 1));
               return Optional.of(new SplicedPath(list, list2, path2.getNumNodesConsidered() + path3.getNumNodesConsidered(), path2.getGoal()));
            }
         }
      } else {
         return Optional.empty();
      }
   }

   @Override
   public List movements() {
      return Collections.unmodifiableList(this.movements);
   }

   @Override
   public List positions() {
      return Collections.unmodifiableList(this.path);
   }

   @Override
   public int length() {
      return this.path.size();
   }

   @Override
   public Goal getGoal() {
      return this.goal;
   }

   @Override
   public int getNumNodesConsidered() {
      return this.numNodes;
   }

   private SplicedPath(List list, List list2, int n, Goal goal2) {
      this.path = list;
      this.movements = list2;
      this.numNodes = n;
      this.goal = goal2;
      this.sanityCheck();
   }
}
