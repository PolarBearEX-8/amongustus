package net.minecraft.pathfinding;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.pathfinder.WalkNodeProcessor;

public class PathNavigateGround extends PathNavigate {
   public WalkNodeProcessor nodeProcessor;
   private boolean shouldAvoidSun;

   private boolean isPositionClear(int n, int n2, int n3, int n4, int n5, int n6, Vec3 vec, double d, double d2) {
      for (BlockPos pos : (Iterable<BlockPos>)(BlockPos.getAllInBox(new BlockPos(n, n2, n3), new BlockPos(n + n4 - 1, n2 + n5 - 1, n3 + n6 - 1)))) {
         double d3 = (double)pos.getX() + 0.5 - vec.xCoord;
         double d4 = (double)pos.getZ() + 0.5 - vec.zCoord;
         if (d3 * d + d4 * d2 >= 0.0) {
            if (!this.worldObj.getBlockState(pos).getBlock().isPassable(this.worldObj, pos)) {
               return false;
            }
         }
      }

      return true;
   }

   public boolean getCanSwim() {
      return this.nodeProcessor.getCanSwim();
   }

   public void setAvoidsWater(boolean flag) {
      this.nodeProcessor.setAvoidsWater(flag);
   }

   public void setEnterDoors(boolean flag) {
      this.nodeProcessor.setEnterDoors(flag);
   }

   public boolean getEnterDoors() {
      return this.nodeProcessor.getEnterDoors();
   }

   public void setCanSwim(boolean flag) {
      this.nodeProcessor.setCanSwim(flag);
   }

   public boolean getAvoidsWater() {
      return this.nodeProcessor.getAvoidsWater();
   }

   @Override
   public Vec3 getEntityPosition() {
      return new Vec3(this.theEntity.posX, (double)this.getPathablePosY(), this.theEntity.posZ);
   }

   @Override
   public void removeSunnyPath() {
      super.removeSunnyPath();
      if (this.shouldAvoidSun) {
         if (this.worldObj
            .canSeeSky(
               MathHelper.floor_double(this.theEntity.posX),
               MathHelper.floor_double((double)((int)(this.theEntity.getEntityBoundingBox().minY + 0.5))),
               MathHelper.floor_double(this.theEntity.posZ)
            )) {
            return;
         }

         for (int i = 0; i < this.currentPath.getCurrentPathLength(); i++) {
            PathPoint pathPointFromIndex = this.currentPath.getPathPointFromIndex(i);
            if (this.worldObj.canSeeSky(pathPointFromIndex.xCoord, pathPointFromIndex.yCoord, pathPointFromIndex.zCoord)) {
               this.currentPath.setCurrentPathLength(i - 1);
               return;
            }
         }
      }
   }

   private int getPathablePosY() {
      if (this.theEntity.isInWater() && this.getCanSwim()) {
         int minY = (int)this.theEntity.getEntityBoundingBox().minY;
         Block block = this.worldObj
            .getBlockState(new BlockPos(MathHelper.floor_double(this.theEntity.posX), minY, MathHelper.floor_double(this.theEntity.posZ)))
            .getBlock();
         int n = 0;

         while (block == Blocks.flowing_water || block == Blocks.water) {
            block = this.worldObj
               .getBlockState(new BlockPos(MathHelper.floor_double(this.theEntity.posX), ++minY, MathHelper.floor_double(this.theEntity.posZ)))
               .getBlock();
            if (++n > 16) {
               return (int)this.theEntity.getEntityBoundingBox().minY;
            }
         }

         return minY;
      } else {
         return (int)(this.theEntity.getEntityBoundingBox().minY + 0.5);
      }
   }

   @Override
   public boolean isDirectPathBetweenPoints(Vec3 vec, Vec3 vec2, int n, int n2, int n3) {
      int n4 = MathHelper.floor_double(vec.xCoord);
      int n5 = MathHelper.floor_double(vec.zCoord);
      double d = vec2.xCoord - vec.xCoord;
      double d2 = vec2.zCoord - vec.zCoord;
      double d3 = d * d + d2 * d2;
      if (d3 < 1.0E-8) {
         return false;
      } else {
         double d4 = 1.0 / Math.sqrt(d3);
         d *= d4;
         d2 *= d4;
         n += 2;
         n3 += 2;
         if (!this.isSafeToStandAt(n4, (int)vec.yCoord, n5, n, n2, n3, vec, d, d2)) {
            return false;
         } else {
            n -= 2;
            n3 -= 2;
            double d5 = 1.0 / Math.abs(d);
            double d6 = 1.0 / Math.abs(d2);
            double d7 = (double)(n4 * 1) - vec.xCoord;
            double d8 = (double)(n5 * 1) - vec.zCoord;
            if (d >= 0.0) {
               d7++;
            }

            if (d2 >= 0.0) {
               d8++;
            }

            d7 /= d;
            d8 /= d2;
            int n6 = d < 0.0 ? -1 : 1;
            int n7 = d2 < 0.0 ? -1 : 1;
            int n8 = MathHelper.floor_double(vec2.xCoord);
            int n9 = MathHelper.floor_double(vec2.zCoord);
            int n10 = n8 - n4;
            int n11 = n9 - n5;

            while (n10 * n6 > 0 || n11 * n7 > 0) {
               if (d7 < d8) {
                  d7 += d5;
                  n4 += n6;
                  n10 = n8 - n4;
               } else {
                  d8 += d6;
                  n5 += n7;
                  n11 = n9 - n5;
               }

               if (!this.isSafeToStandAt(n4, (int)vec.yCoord, n5, n, n2, n3, vec, d, d2)) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public PathNavigateGround(EntityLiving entityLiving, World world) {
      super(entityLiving, world);
   }

   @Override
   public PathFinder getPathFinder() {
      this.nodeProcessor = new WalkNodeProcessor();
      this.nodeProcessor.setEnterDoors(true);
      return new PathFinder(this.nodeProcessor);
   }

   public void setBreakDoors(boolean flag) {
      this.nodeProcessor.setBreakDoors(flag);
   }

   private boolean isSafeToStandAt(int n, int n2, int n3, int n4, int n5, int n6, Vec3 vec, double d, double d2) {
      int n7 = n - n4 / 2;
      int n8 = n3 - n6 / 2;
      if (!this.isPositionClear(n7, n2, n8, n4, n5, n6, vec, d, d2)) {
         return false;
      } else {
         for (int i = n7; i < n7 + n4; i++) {
            for (int j = n8; j < n8 + n6; j++) {
               double d3 = (double)i + 0.5 - vec.xCoord;
               double d4 = (double)j + 0.5 - vec.zCoord;
               if (d3 * d + d4 * d2 >= 0.0) {
                  Material material = this.worldObj.getBlockState(new BlockPos(i, n2 - 1, j)).getBlock().getMaterial();
                  if (material == Material.air) {
                     return false;
                  }

                  if (material == Material.water && !this.theEntity.isInWater()) {
                     return false;
                  }

                  if (material == Material.lava) {
                     return false;
                  }
               }
            }
         }

         return true;
      }
   }

   public void setAvoidSun(boolean flag) {
      this.shouldAvoidSun = flag;
   }

   @Override
   public boolean canNavigate() {
      return this.theEntity.onGround
         || this.getCanSwim() && this.isInLiquid()
         || this.theEntity.isRiding() && this.theEntity instanceof EntityZombie && this.theEntity.ridingEntity instanceof EntityChicken;
   }
}
