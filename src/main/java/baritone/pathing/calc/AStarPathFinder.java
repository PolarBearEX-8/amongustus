package baritone.pathing.calc;

import baritone.Baritone;
import baritone.api.pathing.goals.Goal;
import baritone.api.utils.BetterBlockPos;
import baritone.pathing.calc.openset.BinaryHeapOpenSet;
import baritone.pathing.movement.CalculationContext;
import baritone.pathing.movement.Moves;
import baritone.utils.pathing.BetterWorldBorder;
import baritone.utils.pathing.Favoring;
import baritone.utils.pathing.MutableMoveResult;
import java.util.Optional;

public final class AStarPathFinder extends AbstractNodeCostSearch {
   private final Favoring favoring;
   private final CalculationContext calcContext;

   @Override
   public Optional calculate0(long l, long l2) {
      this.startNode = this.getNodeAtPosition(this.startX, this.startY, this.startZ, BetterBlockPos.longHash(this.startX, this.startY, this.startZ));
      this.startNode.cost = 0.0;
      this.startNode.combinedCost = this.startNode.estimatedCostToGoal;
      BinaryHeapOpenSet binaryHeapOpenSet = new BinaryHeapOpenSet();
      binaryHeapOpenSet.insert(this.startNode);
      double[] doubles = new double[COEFFICIENTS.length];

      for (int j = 0; j < doubles.length; j++) {
         doubles[j] = this.startNode.estimatedCostToGoal;
         this.bestSoFar[j] = this.startNode;
      }

      MutableMoveResult mutableMoveResult = new MutableMoveResult();
      BetterWorldBorder betterWorldBorder = new BetterWorldBorder(this.calcContext.world.getWorldBorder());
      long l3 = System.currentTimeMillis();
      boolean value = (Boolean)Baritone.settings().slowPath.value;
      if (value) {
         this.logDebug("slowPath is on, path timeout will be " + Baritone.settings().slowPathTimeoutMS.value + "ms instead of " + l + "ms");
      }

      long l4 = l3 + (value ? (Long)Baritone.settings().slowPathTimeoutMS.value : l);
      long l5 = l3 + (value ? (Long)Baritone.settings().slowPathTimeoutMS.value : l2);
      boolean flag = true;
      int n = 0;
      int n2 = 0;
      int n3 = 0;
      boolean flag2 = !this.favoring.isEmpty();
      int value2 = (Integer)Baritone.settings().pathingMaxChunkBorderFetch.value;
      double d = (Boolean)Baritone.settings().minimumImprovementRepropagation.value ? 0.01 : 0.0;
      Moves[] values = Moves.values();

      while (!binaryHeapOpenSet.isEmpty() && n3 < value2 && !this.cancelRequested) {
         if ((n & ((byte)64) - 1) == 0) {
            long l6 = System.currentTimeMillis();
            if (l6 - l5 >= 0L || !flag && l6 - l4 >= 0L) {
               break;
            }
         }

         if (value) {
            try {
               Thread.sleep((Long)Baritone.settings().slowPathTimeDelayMS.value);
            } catch (InterruptedException e) {
            }
         }

         PathNode pathNode = binaryHeapOpenSet.removeLowest();
         this.mostRecentConsidered = pathNode;
         n++;
         if (this.goal.isInGoal(pathNode.x, pathNode.y, pathNode.z)) {
            this.logDebug("Took " + (System.currentTimeMillis() - l3) + "ms, " + n2 + " movements considered");
            return Optional.of(new Path(this.startNode, pathNode, n, this.goal, this.calcContext));
         }

         for (Moves moves : values) {
            int n4 = pathNode.x + moves.xOffset;
            int n5 = pathNode.z + moves.zOffset;
            if ((n4 >> 4 != pathNode.x >> 4 || n5 >> 4 != pathNode.z >> 4) && !this.calcContext.isLoaded(n4, n5)) {
               if (!moves.dynamicXZ) {
                  n3++;
               }
            } else if ((moves.dynamicXZ || betterWorldBorder.entirelyContains(n4, n5)) && pathNode.y + moves.yOffset <= 256 && pathNode.y + moves.yOffset >= 0) {
               mutableMoveResult.reset();
               moves.apply(this.calcContext, pathNode.x, pathNode.y, pathNode.z, mutableMoveResult);
               n2++;
               double cost = mutableMoveResult.cost;
               if (!(cost >= 1000000.0)) {
                  if (cost <= 0.0 || Double.isNaN(cost)) {
                     throw new IllegalStateException(moves + " calculated implausible cost " + cost);
                  }

                  if (!moves.dynamicXZ || betterWorldBorder.entirelyContains(mutableMoveResult.x, mutableMoveResult.z)) {
                     if (!moves.dynamicXZ && (mutableMoveResult.x != n4 || mutableMoveResult.z != n5)) {
                        throw new IllegalStateException(moves + " " + mutableMoveResult.x + " " + n4 + " " + mutableMoveResult.z + " " + n5);
                     }

                     if (!moves.dynamicY && mutableMoveResult.y != pathNode.y + moves.yOffset) {
                        throw new IllegalStateException(moves + " " + mutableMoveResult.y + " " + (pathNode.y + moves.yOffset));
                     }

                     long l7 = BetterBlockPos.longHash(mutableMoveResult.x, mutableMoveResult.y, mutableMoveResult.z);
                     if (flag2) {
                        cost *= this.favoring.calculate(l7);
                     }

                     PathNode nodeAtPosition = this.getNodeAtPosition(mutableMoveResult.x, mutableMoveResult.y, mutableMoveResult.z, l7);
                     double d2 = pathNode.cost + cost;
                     if (nodeAtPosition.cost - d2 > d) {
                        nodeAtPosition.previous = pathNode;
                        nodeAtPosition.cost = d2;
                        nodeAtPosition.combinedCost = d2 + nodeAtPosition.estimatedCostToGoal;
                        if (nodeAtPosition.isOpen()) {
                           binaryHeapOpenSet.update(nodeAtPosition);
                        } else {
                           binaryHeapOpenSet.insert(nodeAtPosition);
                        }

                        for (int k = 0; k < COEFFICIENTS.length; k++) {
                           double d3 = nodeAtPosition.estimatedCostToGoal + nodeAtPosition.cost / COEFFICIENTS[k];
                           if (doubles[k] - d3 > d) {
                              doubles[k] = d3;
                              this.bestSoFar[k] = nodeAtPosition;
                              if (flag && this.getDistFromStartSq(nodeAtPosition) > 25.0) {
                                 flag = false;
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      if (this.cancelRequested) {
         return Optional.empty();
      } else {
         System.out.println(n2 + " movements considered");
         System.out.println("Open set size: " + binaryHeapOpenSet.getSize());
         System.out.println("PathNode map size: " + this.mapSize());
         System.out.println((int)((double)n * 1.0 / (double)((float)(System.currentTimeMillis() - l3) / 1000.0F)) + " nodes per second");
         Optional optional = this.bestSoFar(true, n);
         if (optional.isPresent()) {
            this.logDebug("Took " + (System.currentTimeMillis() - l3) + "ms, " + n2 + " movements considered");
         }

         return optional;
      }
   }

   public AStarPathFinder(int n, int n2, int n3, Goal goal2, Favoring favoring2, CalculationContext calculationContext) {
      super(n, n2, n3, goal2, calculationContext);
      this.favoring = favoring2;
      this.calcContext = calculationContext;
   }
}
