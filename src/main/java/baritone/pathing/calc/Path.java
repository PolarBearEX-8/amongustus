package baritone.pathing.calc;

import baritone.api.pathing.calc.IPath;
import baritone.api.pathing.goals.Goal;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.Helper;
import baritone.utils.pathing.PathBase;
import baritone.pathing.movement.CalculationContext;
import baritone.pathing.movement.Movement;
import baritone.pathing.movement.Moves;
import baritone.pathing.path.CutoffPath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Path extends PathBase {
   private final BetterBlockPos start;
   private final BetterBlockPos end;
   private final List path;
   private final List movements;
   private final List nodes;
   private final Goal goal;
   private final int numNodes;
   private final CalculationContext context;
   private volatile boolean verified;

   @Override
   public BetterBlockPos getSrc() {
      return this.start;
   }

   @Override
   public IPath postProcess() {
      if (this.verified) {
         throw new IllegalStateException();
      } else {
         this.verified = true;
         boolean flag = this.assembleMovements();
         this.movements.forEach(obj -> ((Movement)obj).checkLoadedChunk(this.context));
         if (flag) {
            CutoffPath cutoffPath = new CutoffPath(this, this.movements().size());
            if (cutoffPath.movements().size() != this.movements.size()) {
               throw new IllegalStateException();
            } else {
               return cutoffPath;
            }
         } else {
            this.sanityCheck();
            return this;
         }
      }
   }

   public Path(PathNode pathNode, PathNode pathNode2, int n, Goal goal2, CalculationContext calculationContext) {
      this.start = new BetterBlockPos(pathNode.x, pathNode.y, pathNode.z);
      this.end = new BetterBlockPos(pathNode2.x, pathNode2.y, pathNode2.z);
      this.numNodes = n;
      this.movements = new ArrayList();
      this.goal = goal2;
      this.context = calculationContext;
      PathNode pathNode3 = pathNode2;
      LinkedList list = new LinkedList();

      LinkedList list2;
      for (list2 = new LinkedList(); pathNode3 != null; pathNode3 = pathNode3.previous) {
         list2.addFirst(pathNode3);
         list.addFirst(new BetterBlockPos(pathNode3.x, pathNode3.y, pathNode3.z));
      }

      this.path = new ArrayList(list);
      this.nodes = new ArrayList(list2);
   }

   private Movement runBackwards(BetterBlockPos betterBlockPos, BetterBlockPos betterBlockPos2, double d) {
      for (Moves moves : Moves.values()) {
         Movement movement = moves.apply0(this.context, betterBlockPos);
         if (movement.getDest().equals(betterBlockPos2)) {
            movement.override(
               Math.min(
                  movement.calculateCost(
                     this.context
                  ),
                  d
               )
            );
            return movement;
         }
      }

      Helper.HELPER.logDebug("Movement became impossible during calculation " + betterBlockPos + " " + betterBlockPos2 + " " + betterBlockPos2.subtract(betterBlockPos));
      return null;
   }

   @Override
   public int getNumNodesConsidered() {
      return this.numNodes;
   }

   @Override
   public List positions() {
      return Collections.unmodifiableList(this.path);
   }

   private boolean assembleMovements() {
      if (!this.path.isEmpty() && this.movements.isEmpty()) {
         for (int i = 0; i < this.path.size() - 1; i++) {
            double d = ((PathNode)this.nodes.get(i + 1)).cost - ((PathNode)this.nodes.get(i)).cost;
            Movement movement = this.runBackwards((BetterBlockPos)this.path.get(i), (BetterBlockPos)this.path.get(i + 1), d);
            if (movement == null) {
               return true;
            }

            this.movements.add(movement);
         }

         return false;
      } else {
         throw new IllegalStateException();
      }
   }

   @Override
   public BetterBlockPos getDest() {
      return this.end;
   }

   @Override
   public List movements() {
      if (!this.verified) {
         throw new IllegalStateException();
      } else {
         return Collections.unmodifiableList(this.movements);
      }
   }

   @Override
   public Goal getGoal() {
      return this.goal;
   }

}
