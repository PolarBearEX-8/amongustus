package net.minecraft.world.pathfinder;

import net.minecraft.util.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class SwimNodeProcessor extends NodeProcessor {
   @Override
   public void initProcessor(IBlockAccess world, Entity entity) {
      super.initProcessor(world, entity);
   }

   @Override
   public int findPathOptions(PathPoint[] pathPoints, Entity entity, PathPoint pathPoint, PathPoint pathPoint2, float f) {
      int n = 0;

      for (EnumFacing facing : EnumFacing.getValues()) {
         PathPoint safePoint = this.getSafePoint(
            entity, pathPoint.xCoord + facing.getFrontOffsetX(), pathPoint.yCoord + facing.getFrontOffsetY(), pathPoint.zCoord + facing.getFrontOffsetZ()
         );
         if (safePoint != null && !safePoint.visited && safePoint.distanceTo(pathPoint2) < f) {
            pathPoints[n++] = safePoint;
         }
      }

      return n;
   }

   @Override
   public PathPoint getPathPointTo(Entity entity) {
      return this.openPoint(
         MathHelper.floor_double(entity.getEntityBoundingBox().minX),
         MathHelper.floor_double(entity.getEntityBoundingBox().minY + 0.5),
         MathHelper.floor_double(entity.getEntityBoundingBox().minZ)
      );
   }

   @Override
   public PathPoint getPathPointToCoords(Entity entity, double d, double d2, double d3) {
      return this.openPoint(
         MathHelper.floor_double(d - (double)(entity.width / 2.0F)),
         MathHelper.floor_double(d2 + 0.5),
         MathHelper.floor_double(d3 - (double)(entity.width / 2.0F))
      );
   }

   private PathPoint getSafePoint(Entity entity, int n, int n2, int n3) {
      return this.isFree(entity, n, n2, n3) == -1 ? this.openPoint(n, n2, n3) : null;
   }

   @Override
   public void postProcess() {
      super.postProcess();
   }

   private int isFree(Entity entity, int n, int n2, int n3) {
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

      for (int i = n; i < n + this.entitySizeX; i++) {
         for (int j = n2; j < n2 + this.entitySizeY; j++) {
            for (int k = n3; k < n3 + this.entitySizeZ; k++) {
               if (this.blockaccess.getBlockState(pos.set(i, j, k)).getBlock().getMaterial() != Material.water) {
                  return 0;
               }
            }
         }
      }

      return -1;
   }
}
