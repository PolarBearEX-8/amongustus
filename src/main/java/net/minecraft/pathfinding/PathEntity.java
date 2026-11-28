package net.minecraft.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;

public class PathEntity {
   private final PathPoint[] points;
   private int currentPathIndex;
   private int pathLength;

   public PathPoint getFinalPathPoint() {
      return this.pathLength > 0 ? this.points[this.pathLength - 1] : null;
   }

   public int getCurrentPathIndex() {
      return this.currentPathIndex;
   }

   public boolean isDestinationSame(Vec3 vec) {
      PathPoint finalPathPoint = this.getFinalPathPoint();
      return finalPathPoint == null ? false : finalPathPoint.xCoord == (int)vec.xCoord && finalPathPoint.zCoord == (int)vec.zCoord;
   }

   public PathPoint getPathPointFromIndex(int n) {
      return this.points[n];
   }

   public PathEntity(PathPoint[] pathPoints) {
      this.points = pathPoints;
      this.pathLength = pathPoints.length;
   }

   public boolean isFinished() {
      return this.currentPathIndex >= this.pathLength;
   }

   public void setCurrentPathIndex(int n) {
      this.currentPathIndex = n;
   }

   public Vec3 getVectorFromIndex(Entity entity, int n) {
      double d = (double)this.points[n].xCoord + (double)((int)(entity.width + 1.0F)) * 0.5;
      double yCoord = (double)this.points[n].yCoord;
      double d2 = (double)this.points[n].zCoord + (double)((int)(entity.width + 1.0F)) * 0.5;
      return new Vec3(d, yCoord, d2);
   }

   public void setCurrentPathLength(int n) {
      this.pathLength = n;
   }

   public void incrementPathIndex() {
      this.currentPathIndex++;
   }

   public Vec3 getPosition(Entity entity) {
      return this.getVectorFromIndex(entity, this.currentPathIndex);
   }

   public int getCurrentPathLength() {
      return this.pathLength;
   }

   public boolean isSamePath(PathEntity pathEntity) {
      if (pathEntity == null) {
         return false;
      } else if (pathEntity.points.length != this.points.length) {
         return false;
      } else {
         for (int i = 0; i < this.points.length; i++) {
            if (this.points[i].xCoord != pathEntity.points[i].xCoord
               || this.points[i].yCoord != pathEntity.points[i].yCoord
               || this.points[i].zCoord != pathEntity.points[i].zCoord) {
               return false;
            }
         }

         return true;
      }
   }
}
