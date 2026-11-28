package net.minecraft.world.pathfinder;

import net.minecraft.util.BlockPos;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class WalkNodeProcessor extends NodeProcessor {
   private boolean canEnterDoors;
   private boolean canBreakDoors;
   private boolean avoidsWater;
   private boolean canSwim;
   private boolean shouldAvoidWater;

   @Override
   public void initProcessor(IBlockAccess world, Entity entity) {
      super.initProcessor(world, entity);
      this.shouldAvoidWater = this.avoidsWater;
   }

   public boolean getEnterDoors() {
      return this.canEnterDoors;
   }

   public boolean getAvoidsWater() {
      return this.avoidsWater;
   }

   public void setBreakDoors(boolean flag) {
      this.canBreakDoors = flag;
   }

   public static int evaluateNode(
      IBlockAccess world, Entity entity, int n, int n2, int n3, int n4, int n5, int n6, boolean flag, boolean flag2, boolean flag3
   ) {
      boolean flag4 = false;
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

      for (int i = n; i < n + n4; i++) {
         for (int j = n2; j < n2 + n5; j++) {
            for (int k = n3; k < n3 + n6; k++) {
               pos.set(i, j, k);
               Block block = world.getBlockState(pos).getBlock();
               if (block.getMaterial() != Material.air) {
                  if (block == Blocks.trapdoor || block == Blocks.iron_trapdoor) {
                     flag4 = true;
                  } else if (block != Blocks.flowing_water && block != Blocks.water) {
                     if (!flag3 && block instanceof BlockDoor && block.getMaterial() == Material.wood) {
                        return 0;
                     }
                  } else {
                     if (flag) {
                        return -1;
                     }

                     flag4 = true;
                  }

                  if (entity.entityWorld.getBlockState(pos).getBlock() instanceof BlockRailBase) {
                     if (!(entity.entityWorld.getBlockState(entity.posX, entity.posY, entity.posZ).getBlock() instanceof BlockRailBase)
                        && !(entity.entityWorld.getBlockState(entity.posX, entity.posY - 1.0, entity.posZ).getBlock() instanceof BlockRailBase)) {
                        return -3;
                     }
                  } else if (!block.isPassable(world, pos) && (!flag2 || !(block instanceof BlockDoor) || block.getMaterial() != Material.wood)) {
                     if (block instanceof BlockFence || block instanceof BlockFenceGate || block instanceof BlockWall) {
                        return -3;
                     }

                     if (block == Blocks.trapdoor || block == Blocks.iron_trapdoor) {
                        return -4;
                     }

                     if (block.getMaterial() != Material.lava) {
                        return 0;
                     }

                     if (!entity.isInLava()) {
                        return -2;
                     }
                  }
               }
            }
         }
      }

      return flag4 ? 2 : 1;
   }

   @Override
   public void postProcess() {
      super.postProcess();
      this.avoidsWater = this.shouldAvoidWater;
   }

   public void setEnterDoors(boolean flag) {
      this.canEnterDoors = flag;
   }

   @Override
   public int findPathOptions(PathPoint[] pathPoints, Entity entity, PathPoint pathPoint, PathPoint pathPoint2, float f) {
      int n = 0;
      byte b = 0;
      if (this.getVerticalOffset(entity, pathPoint.xCoord, pathPoint.yCoord + 1, pathPoint.zCoord) == 1) {
         b = 1;
      }

      PathPoint safePoint = this.getSafePoint(entity, pathPoint.xCoord, pathPoint.yCoord, pathPoint.zCoord + 1, b);
      PathPoint safePoint2 = this.getSafePoint(entity, pathPoint.xCoord - 1, pathPoint.yCoord, pathPoint.zCoord, b);
      PathPoint safePoint3 = this.getSafePoint(entity, pathPoint.xCoord + 1, pathPoint.yCoord, pathPoint.zCoord, b);
      PathPoint safePoint4 = this.getSafePoint(entity, pathPoint.xCoord, pathPoint.yCoord, pathPoint.zCoord - 1, b);
      if (safePoint != null && !safePoint.visited && safePoint.distanceTo(pathPoint2) < f) {
         pathPoints[n++] = safePoint;
      }

      if (safePoint2 != null && !safePoint2.visited && safePoint2.distanceTo(pathPoint2) < f) {
         pathPoints[n++] = safePoint2;
      }

      if (safePoint3 != null && !safePoint3.visited && safePoint3.distanceTo(pathPoint2) < f) {
         pathPoints[n++] = safePoint3;
      }

      if (safePoint4 != null && !safePoint4.visited && safePoint4.distanceTo(pathPoint2) < f) {
         pathPoints[n++] = safePoint4;
      }

      return n;
   }

   @Override
   public PathPoint getPathPointToCoords(Entity entity, double d, double d2, double d3) {
      return this.openPoint(
         MathHelper.floor_double(d - (double)(entity.width / 2.0F)),
         MathHelper.floor_double(d2),
         MathHelper.floor_double(d3 - (double)(entity.width / 2.0F))
      );
   }

   public void setCanSwim(boolean flag) {
      this.canSwim = flag;
   }

   private int getVerticalOffset(Entity entity, int n, int n2, int n3) {
      return evaluateNode(
         this.blockaccess,
         entity,
         n,
         n2,
         n3,
         this.entitySizeX,
         this.entitySizeY,
         this.entitySizeZ,
         this.avoidsWater,
         this.canBreakDoors,
         this.canEnterDoors
      );
   }

   public boolean getCanSwim() {
      return this.canSwim;
   }

   private PathPoint getSafePoint(Entity entity, int n, int n2, int n3, int n4) {
      PathPoint pathPoint = null;
      int verticalOffset = this.getVerticalOffset(entity, n, n2, n3);
      if (verticalOffset == 2) {
         return this.openPoint(n, n2, n3);
      } else {
         if (verticalOffset == 1) {
            pathPoint = this.openPoint(n, n2, n3);
         }

         if (pathPoint == null && n4 > 0 && verticalOffset != -3 && verticalOffset != -4 && this.getVerticalOffset(entity, n, n2 + n4, n3) == 1) {
            pathPoint = this.openPoint(n, n2 + n4, n3);
            n2 += n4;
         }

         if (pathPoint != null) {
            int n5 = 0;

            int n6;
            for (n6 = 0; n2 > 0; pathPoint = this.openPoint(n, n2, n3)) {
               n6 = this.getVerticalOffset(entity, n, n2 - 1, n3);
               if (this.avoidsWater && n6 == -1) {
                  return null;
               }

               if (n6 != 1) {
                  break;
               }

               if (n5++ >= entity.getMaxFallHeight()) {
                  return null;
               }

               if (--n2 <= 0) {
                  return null;
               }
            }

            if (n6 == -2) {
               return null;
            }
         }

         return pathPoint;
      }
   }

   public void setAvoidsWater(boolean flag) {
      this.avoidsWater = flag;
   }

   @Override
   public PathPoint getPathPointTo(Entity entity) {
      int n;
      if (this.canSwim && entity.isInWater()) {
         n = (int)entity.getEntityBoundingBox().minY;
         BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(MathHelper.floor_double(entity.posX), n, MathHelper.floor_double(entity.posZ));

         for (Block i = this.blockaccess.getBlockState(pos).getBlock();
            i == Blocks.flowing_water || i == Blocks.water;
            i = this.blockaccess.getBlockState(pos).getBlock()
         ) {
            pos.set(MathHelper.floor_double(entity.posX), ++n, MathHelper.floor_double(entity.posZ));
         }

         this.avoidsWater = false;
      } else {
         n = MathHelper.floor_double(entity.getEntityBoundingBox().minY + 0.5);
      }

      return this.openPoint(MathHelper.floor_double(entity.getEntityBoundingBox().minX), n, MathHelper.floor_double(entity.getEntityBoundingBox().minZ));
   }
}
