package net.minecraft.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.pathfinder.NodeProcessor;

public class PathFinder {
   private Path path = new Path();
   private PathPoint[] pathOptions = new PathPoint[32];
   private NodeProcessor nodeProcessor;

   public PathEntity createEntityPathTo(IBlockAccess world, Entity entity, BlockPos pos, float f) {
      return this.createEntityPathTo(
         world, entity, (double)((float)pos.getX() + 0.5F), (double)((float)pos.getY() + 0.5F), (double)((float)pos.getZ() + 0.5F), f
      );
   }

   private PathEntity createEntityPathTo(IBlockAccess world, Entity entity, double d, double d2, double d3, float f) {
      this.path.clearPath();
      this.nodeProcessor.initProcessor(world, entity);
      PathEntity pathEntity = this.addToPath(entity, this.nodeProcessor.getPathPointTo(entity), this.nodeProcessor.getPathPointToCoords(entity, d, d2, d3), f);
      this.nodeProcessor.postProcess();
      return pathEntity;
   }

   public PathEntity createEntityPathTo(IBlockAccess world, Entity entity, Entity entity2, float f) {
      return this.createEntityPathTo(world, entity, entity2.posX, entity2.getEntityBoundingBox().minY, entity2.posZ, f);
   }

   public PathFinder(NodeProcessor nodeProcessor2) {
      this.nodeProcessor = nodeProcessor2;
   }

   private PathEntity addToPath(Entity entity, PathPoint pathPoint, PathPoint pathPoint2, float f) {
      pathPoint.totalPathDistance = 0.0F;
      pathPoint.distanceToNext = pathPoint.distanceToSquared(pathPoint2);
      pathPoint.distanceToTarget = pathPoint.distanceToNext;
      this.path.clearPath();
      this.path.addPoint(pathPoint);
      PathPoint pathPoint3 = pathPoint;

      while (!this.path.isPathEmpty()) {
         PathPoint pathPoint4 = this.path.dequeue();
         if (pathPoint4.equals(pathPoint2)) {
            return this.createEntityPath(pathPoint, pathPoint2);
         }

         if (pathPoint4.distanceToSquared(pathPoint2) < pathPoint3.distanceToSquared(pathPoint2)) {
            pathPoint3 = pathPoint4;
         }

         pathPoint4.visited = true;
         int n = this.nodeProcessor.findPathOptions(this.pathOptions, entity, pathPoint4, pathPoint2, f);

         for (int i = 0; i < n; i++) {
            PathPoint pathPoint5 = this.pathOptions[i];
            float f2 = pathPoint4.totalPathDistance + pathPoint4.distanceToSquared(pathPoint5);
            if (f2 < f * 2.0F && (!pathPoint5.isAssigned() || f2 < pathPoint5.totalPathDistance)) {
               pathPoint5.previous = pathPoint4;
               pathPoint5.totalPathDistance = f2;
               pathPoint5.distanceToNext = pathPoint5.distanceToSquared(pathPoint2);
               if (pathPoint5.isAssigned()) {
                  this.path.changeDistance(pathPoint5, pathPoint5.totalPathDistance + pathPoint5.distanceToNext);
               } else {
                  pathPoint5.distanceToTarget = pathPoint5.totalPathDistance + pathPoint5.distanceToNext;
                  this.path.addPoint(pathPoint5);
               }
            }
         }
      }

      return pathPoint3 == pathPoint ? null : this.createEntityPath(pathPoint, pathPoint3);
   }

   private PathEntity createEntityPath(PathPoint pathPoint, PathPoint pathPoint2) {
      int n = 1;

      for (PathPoint i = pathPoint2; i.previous != null; i = i.previous) {
         n++;
      }

      PathPoint[] pathPoints = new PathPoint[n];
      PathPoint pathPoint3 = pathPoint2;
      n--;

      for (pathPoints[n] = pathPoint2; pathPoint3.previous != null; pathPoints[n] = pathPoint3) {
         pathPoint3 = pathPoint3.previous;
         n--;
      }

      return new PathEntity(pathPoints);
   }
}
