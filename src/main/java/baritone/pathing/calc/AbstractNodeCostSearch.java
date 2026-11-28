package baritone.pathing.calc;

import baritone.Baritone;
import baritone.api.pathing.calc.IPath;
import baritone.api.pathing.calc.IPathFinder;
import baritone.api.pathing.goals.Goal;
import baritone.api.utils.BetterBlockPos;
import baritone.api.utils.Helper;
import baritone.api.utils.PathCalculationResult;
import baritone.api.utils.PathCalculationResultType;
import baritone.pathing.movement.CalculationContext;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.Optional;

public abstract class AbstractNodeCostSearch implements Helper, IPathFinder {
   public final int startX;
   public final int startY;
   public final int startZ;
   public final Goal goal;
   private final CalculationContext context;
   private final Long2ObjectOpenHashMap map;
   public PathNode startNode;
   public PathNode mostRecentConsidered;
   public final PathNode[] bestSoFar;
   private volatile boolean isFinished;
   public boolean cancelRequested;
   public static final double[] COEFFICIENTS = new double[]{1.5, 2.0, 2.5, 3.0, 4.0, 5.0, 10.0};
   public static final double MIN_DIST_PATH = 5.0;
   public static final double MIN_IMPROVEMENT = 0.01;

   public PathNode getNodeAtPosition(int n, int n2, int n3, long l) {
      PathNode pathNode = (PathNode)this.map.get(l);
      if (pathNode == null) {
         pathNode = new PathNode(n, n2, n3, this.goal);
         this.map.put(l, pathNode);
      }

      return pathNode;
   }

   public int mapSize() {
      return this.map.size();
   }

   @Override
   public synchronized PathCalculationResult calculate(long l, long l2) {
      if (this.isFinished) {
         throw new IllegalStateException("Path finder cannot be reused!");
      } else {
         this.cancelRequested = false;

         PathCalculationResult pathCalculationResult;
         try {
            IPath path = (IPath)this.calculate0(l, l2).map(obj -> ((IPath)obj).postProcess()).orElse(null);
            if (this.cancelRequested) {
               return new PathCalculationResult(PathCalculationResultType.CANCELLATION);
            }

            if (path == null) {
               return new PathCalculationResult(PathCalculationResultType.FAILURE);
            }

            int length = path.length();
            path = path.cutoffAtLoadedChunks(this.context.bsi);
            if (path.length() < length) {
               Helper.HELPER.logDebug("Cutting off path at edge of loaded chunks");
               Helper.HELPER.logDebug("Length decreased by " + (length - path.length()));
            } else {
               Helper.HELPER.logDebug("Path ends within loaded chunks");
            }

            length = path.length();
            path = path.staticCutoff(this.goal);
            if (path.length() < length) {
               Helper.HELPER.logDebug("Static cutoff " + length + " to " + path.length());
            }

            if (!this.goal.isInGoal(path.getDest())) {
               return new PathCalculationResult(PathCalculationResultType.SUCCESS_SEGMENT, path);
            }

            pathCalculationResult = new PathCalculationResult(PathCalculationResultType.SUCCESS_TO_GOAL, path);
         } catch (Exception e) {
            Helper.HELPER.logDirect2("Pathing exception: " + e);
            e.printStackTrace();
            return new PathCalculationResult(PathCalculationResultType.EXCEPTION);
         } finally {
            this.isFinished = true;
         }

         return pathCalculationResult;
      }
   }

   public Optional bestSoFar(boolean flag, int n) {
      if (this.startNode == null) {
         return Optional.empty();
      } else {
         double d = 0.0;

         for (int j = 0; j < COEFFICIENTS.length; j++) {
            if (this.bestSoFar[j] != null) {
               double distFromStartSq = this.getDistFromStartSq(this.bestSoFar[j]);
               if (distFromStartSq > d) {
                  d = distFromStartSq;
               }

               if (distFromStartSq > 25.0) {
                  if (flag) {
                     if (COEFFICIENTS[j] >= 3.0) {
                        System.out.println("Warning: cost coefficient is greater than three! Probably means that");
                        System.out.println("the path I found is pretty terrible (like sneak-bridging for dozens of blocks)");
                        System.out.println("But I'm going to do it anyway, because yolo");
                     }

                     System.out.println("Path goes for " + Math.sqrt(distFromStartSq) + " blocks");
                     this.logDebug("A* cost coefficient " + COEFFICIENTS[j]);
                  }

                  return Optional.of(new Path(this.startNode, this.bestSoFar[j], n, this.goal, this.context));
               }
            }
         }

         if (flag) {
            this.logDebug("Even with a cost coefficient of " + COEFFICIENTS[COEFFICIENTS.length - 1] + ", I couldn't get more than " + Math.sqrt(d) + " blocks");
            this.logDebug("No path found =(");
            this.logNotification("No path found =(", true);
         }

         return Optional.empty();
      }
   }

   @Override
   public final boolean isFinished() {
      return this.isFinished;
   }

   public void cancel() {
      this.cancelRequested = true;
   }

   @Override
   public Optional pathToMostRecentNodeConsidered() {
      return Optional.ofNullable(this.mostRecentConsidered).map(pathNode -> new Path(this.startNode, pathNode, 0, this.goal, this.context));
   }

   public BetterBlockPos getStart() {
      return new BetterBlockPos(this.startX, this.startY, this.startZ);
   }

   @Override
   public Optional bestPathSoFar() {
      return this.bestSoFar(false, 0);
   }

   @Override
   public final Goal getGoal() {
      return this.goal;
   }

   public abstract Optional calculate0(long l, long l2);

   public double getDistFromStartSq(PathNode pathNode) {
      int n = pathNode.x - this.startX;
      int n2 = pathNode.y - this.startY;
      int n3 = pathNode.z - this.startZ;
      return (double)(n * n + n2 * n2 + n3 * n3);
   }

   public AbstractNodeCostSearch(int n, int n2, int n3, Goal goal2, CalculationContext calculationContext) {
      this.bestSoFar = new PathNode[COEFFICIENTS.length];
      this.startX = n;
      this.startY = n2;
      this.startZ = n3;
      this.goal = goal2;
      this.context = calculationContext;
      this.map = new Long2ObjectOpenHashMap((Integer)Baritone.settings().pathingMapDefaultSize.value, (Float)Baritone.settings().pathingMapLoadFactor.value);
   }

}
