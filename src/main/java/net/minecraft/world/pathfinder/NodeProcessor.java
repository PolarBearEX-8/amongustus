package net.minecraft.world.pathfinder;

import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public abstract class NodeProcessor {
   public IBlockAccess blockaccess;
   public IntHashMap pointMap = new IntHashMap();
   public int entitySizeX;
   public int entitySizeY;
   public int entitySizeZ;

   public PathPoint openPoint(int n, int n2, int n3) {
      int n4 = PathPoint.makeHash(n, n2, n3);
      PathPoint pathPoint = (PathPoint)this.pointMap.lookup(n4);
      if (pathPoint == null) {
         pathPoint = new PathPoint(n, n2, n3);
         this.pointMap.addKey(n4, pathPoint);
      }

      return pathPoint;
   }

   public void initProcessor(IBlockAccess world, Entity entity) {
      this.blockaccess = world;
      this.pointMap.clearMap();
      this.entitySizeX = MathHelper.floor_float(entity.width + 1.0F);
      this.entitySizeY = MathHelper.floor_float(entity.height + 1.0F);
      this.entitySizeZ = MathHelper.floor_float(entity.width + 1.0F);
   }

   public abstract PathPoint getPathPointToCoords(Entity entity, double d, double d2, double d3);

   public void postProcess() {
      this.blockaccess = null;
   }

   public abstract PathPoint getPathPointTo(Entity entity);

   public abstract int findPathOptions(PathPoint[] pathPoints, Entity entity, PathPoint pathPoint, PathPoint pathPoint2, float f);
}
