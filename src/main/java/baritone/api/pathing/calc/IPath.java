package baritone.api.pathing.calc;

import baritone.api.pathing.goals.Goal;
import baritone.api.pathing.movement.IMovement;
import baritone.api.utils.BetterBlockPos;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public interface IPath {

   default int length() {
      return this.positions().size();
   }

   List positions();

   int getNumNodesConsidered();

   default IPath postProcess() {
      throw new UnsupportedOperationException();
   }

   default IPath staticCutoff(Goal goal) {
      throw new UnsupportedOperationException();
   }

   default double ticksRemainingFrom(int n) {
      double d = 0.0;
      List list = this.movements();

      for (int i = n; i < list.size(); i++) {
         d += ((IMovement)list.get(i)).getCost();
      }

      return d;
   }

   default BetterBlockPos getDest() {
      List list = this.positions();
      return (BetterBlockPos)list.get(list.size() - 1);
   }

   List movements();

   default void sanityCheck() {
      List list = this.positions();
      List list2 = this.movements();
      if (!this.getSrc().equals(list.get(0))) {
         throw new IllegalStateException("Start node does not equal first path element");
      } else if (!this.getDest().equals(list.get(list.size() - 1))) {
         throw new IllegalStateException("End node does not equal last path element");
      } else if (list.size() != list2.size() + 1) {
         throw new IllegalStateException("Size of path array is unexpected");
      } else {
         HashSet set = new HashSet();

         for (int i = 0; i < list.size() - 1; i++) {
            BetterBlockPos betterBlockPos = (BetterBlockPos)list.get(i);
            BetterBlockPos betterBlockPos2 = (BetterBlockPos)list.get(i + 1);
            IMovement movement = (IMovement)list2.get(i);
            if (!betterBlockPos.equals(movement.getSrc())) {
               throw new IllegalStateException("Path source is not equal to the movement source");
            }

            if (!betterBlockPos2.equals(movement.getDest())) {
               throw new IllegalStateException("Path destination is not equal to the movement destination");
            }

            if (set.contains(betterBlockPos)) {
               throw new IllegalStateException("Path doubles back on itself, making a loop");
            }

            set.add(betterBlockPos);
         }
      }
   }

   Goal getGoal();

   default IPath cutoffAtLoadedChunks(Object obj) {
      throw new UnsupportedOperationException();
   }

   default BetterBlockPos getSrc() {
      return (BetterBlockPos)this.positions().get(0);
   }
}
