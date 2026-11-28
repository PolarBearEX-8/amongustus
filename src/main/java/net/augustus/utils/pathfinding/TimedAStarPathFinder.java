package net.augustus.utils.pathfinding;

import net.augustus.Augustus;
import java.util.ArrayList;

public class TimedAStarPathFinder extends AStarPathFinder {
   public int iterations;

   public TimedAStarPathFinder(PathBlockPos pathBlockPos, PathBlockPos pathBlockPos2, boolean flag, boolean flag2, boolean flag3) {
       super(pathBlockPos, pathBlockPos2, flag, flag2, flag3);
   }

   public ArrayList computePathWithTimeout(long l) {
      this.iterations++;
         if (!mc.theWorld.isChunkLoaded(this.goalPos.x >> 4, this.goalPos.z >> 4, false)) {
            return new ArrayList();
         } else {
            long currentTimeMs = System.currentTimeMillis();
            int n = this.startPos.x;
            int n2 = this.startPos.y;
            int n3 = this.startPos.z;
            long l3 = AStarPathFinder.packBlockKey(this.startPos.x, this.startPos.y, this.startPos.z);
            this.createAndQueueNode((PathNode)null, n, n2, n3, l3, false);

            while (System.currentTimeMillis() - currentTimeMs <= l) {
               if (this.openSet.isEmpty()) {
                  return new ArrayList();
               }

               PathNode pathNode = (PathNode)this.openSet.poll();
               if (this.closestHeuristic > pathNode.getHCost()) {
                  this.closestHeuristic = pathNode.getHCost();
               }

               if (pathNode.getHCost() < this.goalTolerance) {
                  ArrayList list = this.reconstructPath(pathNode);
                  if (this.smoothPath) {
                     boolean walkOnWater2 = this.walkOnWater;
                     list = this.simplifyPath(list, this.allowSwimming, walkOnWater2);
                  }

                  return list;
               }

               this.expandNode(pathNode);
            }

            return null;
         }
   }
}
