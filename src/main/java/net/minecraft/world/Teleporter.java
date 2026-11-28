package net.minecraft.world;

import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.util.EnumFacing;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;

public class Teleporter {
   private final WorldServer worldServerInstance;
   private final Random random;
   private final LongHashMap destinationCoordinateCache = new LongHashMap();
   private final List destinationCoordinateKeys = Lists.newArrayList();

   public boolean makePortal(Entity entity) {
      byte b = 16;
      double d = -1.0;
      int n = MathHelper.floor_double(entity.posX);
      int n2 = MathHelper.floor_double(entity.posY);
      int n3 = MathHelper.floor_double(entity.posZ);
      int n4 = n;
      int n5 = n2;
      int n6 = n3;
      int n7 = 0;
      int n8 = this.random.nextInt(4);
      BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

      for (int i = n - b; i <= n + b; i++) {
         double d2 = (double)i + 0.5 - entity.posX;

         for (int j = n3 - b; j <= n3 + b; j++) {
            double d3 = (double)j + 0.5 - entity.posZ;

            outerLoop:
            for (int k = this.worldServerInstance.getActualHeight() - 1; k >= 0; k--) {
               if (this.worldServerInstance.isAirBlock(pos.set(i, k, j))) {
                  while (k > 0 && this.worldServerInstance.isAirBlock(pos.set(i, k - 1, j))) {
                     k--;
                  }

                  for (int m = n8; m < n8 + 4; m++) {
                     int n9 = m % 2;
                     int n10 = 1 - n9;
                     if (m % 4 >= 2) {
                        n9 = -n9;
                        n10 = -n10;
                     }

                     for (int idx = 0; idx < 3; idx++) {
                        for (int index = 0; index < 4; index++) {
                           for (int i2 = -1; i2 < 4; i2++) {
                              pos.set(i + (index - 1) * n9 + idx * n10, k + i2, j + (index - 1) * n10 - idx * n9);
                              if (i2 < 0 && !this.worldServerInstance.getBlockState(pos).getBlock().getMaterial().isSolid()
                                 || i2 >= 0 && !this.worldServerInstance.isAirBlock(pos)) {
                                 continue outerLoop;
                              }
                           }
                        }
                     }

                     double d4 = (double)k + 0.5 - entity.posY;
                     double d5 = d2 * d2 + d4 * d4 + d3 * d3;
                     if (d < 0.0 || d5 < d) {
                        d = d5;
                        n4 = i;
                        n5 = k;
                        n6 = j;
                        n7 = m % 4;
                     }
                  }
               }
            }
         }
      }

      if (d < 0.0) {
         for (int i3 = n - b; i3 <= n + b; i3++) {
            double d6 = (double)i3 + 0.5 - entity.posX;

            for (int i4 = n3 - b; i4 <= n3 + b; i4++) {
               double d7 = (double)i4 + 0.5 - entity.posZ;

               outerLoop2:
               for (int i5 = this.worldServerInstance.getActualHeight() - 1; i5 >= 0; i5--) {
                  if (this.worldServerInstance.isAirBlock(pos.set(i3, i5, i4))) {
                     while (i5 > 0 && this.worldServerInstance.isAirBlock(pos.set(i3, i5 - 1, i4))) {
                        i5--;
                     }

                     for (int i6 = n8; i6 < n8 + 2; i6++) {
                        int n14 = i6 % 2;
                        int n15 = 1 - n14;

                        for (int i7 = 0; i7 < 4; i7++) {
                           for (int i8 = -1; i8 < 4; i8++) {
                              pos.set(i3 + (i7 - 1) * n14, i5 + i8, i4 + (i7 - 1) * n15);
                              if (i8 < 0 && !this.worldServerInstance.getBlockState(pos).getBlock().getMaterial().isSolid()
                                 || i8 >= 0 && !this.worldServerInstance.isAirBlock(pos)) {
                                 continue outerLoop2;
                              }
                           }
                        }

                        double d8 = (double)i5 + 0.5 - entity.posY;
                        double d9 = d6 * d6 + d8 * d8 + d7 * d7;
                        if (d < 0.0 || d9 < d) {
                           d = d9;
                           n4 = i3;
                           n5 = i5;
                           n6 = i4;
                           n7 = i6 % 2;
                        }
                     }
                  }
               }
            }
         }
      }

      int n19 = n4;
      int n20 = n5;
      int n21 = n6;
      int n22 = n7 % 2;
      int n23 = 1 - n22;
      if (n7 % 4 >= 2) {
         n22 = -n22;
         n23 = -n23;
      }

      if (d < 0.0) {
         n5 = MathHelper.clamp_int(n5, 70, this.worldServerInstance.getActualHeight() - 10);
         n20 = n5;

         for (int i9 = -1; i9 <= 1; i9++) {
            for (int i10 = 1; i10 < 3; i10++) {
               for (int i11 = -1; i11 < 3; i11++) {
                  int n24 = n19 + (i10 - 1) * n22 + i9 * n23;
                  int n25 = n20 + i11;
                  int n26 = n21 + (i10 - 1) * n23 - i9 * n22;
                  boolean flag = i11 < 0;
                  this.worldServerInstance
                     .setBlockState(new BlockPos(n24, n25, n26), flag ? Blocks.obsidian.getDefaultState() : Blocks.air.getDefaultState());
               }
            }
         }
      }

      IBlockState state = Blocks.portal.getDefaultState().withProperty(BlockPortal.AXIS, n22 != 0 ? EnumFacing.Axis.X : EnumFacing.Axis.Z);

      for (int i12 = 0; i12 < 4; i12++) {
         for (int i13 = 0; i13 < 4; i13++) {
            for (int i14 = -1; i14 < 4; i14++) {
               int n27 = n19 + (i13 - 1) * n22;
               int n28 = n20 + i14;
               int n29 = n21 + (i13 - 1) * n23;
               boolean flag2 = i13 == 0 || i13 == 3 || i14 == -1 || i14 == 3;
               this.worldServerInstance.setBlockState(new BlockPos(n27, n28, n29), flag2 ? Blocks.obsidian.getDefaultState() : state, 2);
            }
         }

         for (int i15 = 0; i15 < 4; i15++) {
            for (int i16 = -1; i16 < 4; i16++) {
               int n30 = n19 + (i15 - 1) * n22;
               int n31 = n20 + i16;
               int n32 = n21 + (i15 - 1) * n23;
               BlockPos pos2 = new BlockPos(n30, n31, n32);
               this.worldServerInstance.notifyNeighborsOfStateChange(pos2, this.worldServerInstance.getBlockState(pos2).getBlock());
            }
         }
      }

      return true;
   }

   public void removeStalePortalLocations(long l) {
      if (l % 100L == 0L) {
         Iterator it = this.destinationCoordinateKeys.iterator();
         long l2 = l - 300L;

         while (it.hasNext()) {
            Long val = (Long)it.next();
            Teleporter.PortalPosition valueByKey = (Teleporter.PortalPosition)this.destinationCoordinateCache.getValueByKey(val);
            if (valueByKey == null || valueByKey.lastUpdateTime < l2) {
               it.remove();
               this.destinationCoordinateCache.remove(val);
            }
         }
      }
   }

   public boolean placeInExistingPortal(Entity entity, float f) {
      double d = -1.0;
      int n = MathHelper.floor_double(entity.posX);
      int n2 = MathHelper.floor_double(entity.posZ);
      boolean flag = true;
      Object origin = BlockPos.ORIGIN;
      long l = ChunkCoordIntPair.chunkXZ2Int(n, n2);
      if (this.destinationCoordinateCache.containsItem(l)) {
         Teleporter.PortalPosition valueByKey = (Teleporter.PortalPosition)this.destinationCoordinateCache.getValueByKey(l);
         d = 0.0;
         origin = valueByKey;
         valueByKey.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
         flag = false;
      } else {
         BlockPos pos = new BlockPos(entity);

         for (int i = -128; i <= 128; i++) {
            for (int j = -128; j <= 128; j++) {
               BlockPos pos2 = pos.add(i, this.worldServerInstance.getActualHeight() - 1 - pos.getY(), j);

               while (pos2.getY() >= 0) {
                  BlockPos pos3 = pos2.down();
                  if (this.worldServerInstance.getBlockState(pos2).getBlock() == Blocks.portal) {
                     while (this.worldServerInstance.getBlockState(pos3 = pos2.down()).getBlock() == Blocks.portal) {
                        pos2 = pos3;
                     }

                     double d2 = pos2.distanceSq(pos);
                     if (d < 0.0 || d2 < d) {
                        d = d2;
                        origin = pos2;
                     }
                  }

                  pos2 = pos3;
               }
            }
         }
      }

      if (d >= 0.0) {
         if (flag) {
            this.destinationCoordinateCache.add(l, new Teleporter.PortalPosition(this, (BlockPos)origin, this.worldServerInstance.getTotalWorldTime()));
            this.destinationCoordinateKeys.add(l);
         }

         double d3 = (double)((BlockPos)origin).getX() + 0.5;
         double d4 = (double)((BlockPos)origin).getY() + 0.5;
         double d5 = (double)((BlockPos)origin).getZ() + 0.5;
         BlockPattern.PatternHelper patternHelper = Blocks.portal.createPatternHelper(this.worldServerInstance, (BlockPos)origin);
         boolean flag2 = patternHelper.getFinger().rotateY().getAxisDirection() == EnumFacing.AxisDirection.NEGATIVE;
         double d6 = patternHelper.getFinger().getAxis() == EnumFacing.Axis.X ? (double)patternHelper.getPos().getZ() : (double)patternHelper.getPos().getX();
         d4 = (double)(patternHelper.getPos().getY() + 1) - entity.getLastPortalVec().yCoord * (double)patternHelper.getHeight();
         if (flag2) {
            d6++;
         }

         if (patternHelper.getFinger().getAxis() == EnumFacing.Axis.X) {
            d5 = d6
               + (1.0 - entity.getLastPortalVec().xCoord) * (double)patternHelper.getWidth() * (double)patternHelper.getFinger().rotateY().getAxisDirection().getOffset();
         } else {
            d3 = d6
               + (1.0 - entity.getLastPortalVec().xCoord) * (double)patternHelper.getWidth() * (double)patternHelper.getFinger().rotateY().getAxisDirection().getOffset();
         }

         float f2 = 0.0F;
         float f3 = 0.0F;
         float f4 = 0.0F;
         float f5 = 0.0F;
         if (patternHelper.getFinger().getOpposite() == entity.getTeleportDirection()) {
            f2 = 1.0F;
            f3 = 1.0F;
         } else if (patternHelper.getFinger().getOpposite() == entity.getTeleportDirection().getOpposite()) {
            f2 = -1.0F;
            f3 = -1.0F;
         } else if (patternHelper.getFinger().getOpposite() == entity.getTeleportDirection().rotateY()) {
            f4 = 1.0F;
            f5 = -1.0F;
         } else {
            f4 = -1.0F;
            f5 = 1.0F;
         }

         double motionX = entity.motionX;
         double motionZ = entity.motionZ;
         entity.motionX = motionX * (double)f2 + motionZ * (double)f5;
         entity.motionZ = motionX * (double)f4 + motionZ * (double)f3;
         entity.rotationYaw = f
            - (float)(entity.getTeleportDirection().getOpposite().getHorizontalIndex() * 90)
            + (float)(patternHelper.getFinger().getHorizontalIndex() * 90);
         entity.setLocationAndAngles(d3, d4, d5, entity.rotationYaw, entity.rotationPitch);
         return true;
      } else {
         return false;
      }
   }

   public void placeInPortal(Entity entity, float f) {
      if (this.worldServerInstance.provider.getDimensionId() != 1) {
         if (!this.placeInExistingPortal(entity, f)) {
            this.makePortal(entity);
            this.placeInExistingPortal(entity, f);
         }
      } else {
         int n = MathHelper.floor_double(entity.posX);
         int n2 = MathHelper.floor_double(entity.posY) - 1;
         int n3 = MathHelper.floor_double(entity.posZ);
         byte b = 1;
         byte b2 = 0;

         for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
               for (int k = -1; k < 3; k++) {
                  int n4 = n + j * b + i * b2;
                  int n5 = n2 + k;
                  int n6 = n3 + j * b2 - i * b;
                  boolean flag = k < 0;
                  this.worldServerInstance
                     .setBlockState(new BlockPos(n4, n5, n6), flag ? Blocks.obsidian.getDefaultState() : Blocks.air.getDefaultState());
               }
            }
         }

         entity.setLocationAndAngles((double)n, (double)n2, (double)n3, entity.rotationYaw, 0.0F);
         entity.motionX = entity.motionY = entity.motionZ = 0.0;
      }
   }

   public Teleporter(WorldServer world) {
      this.worldServerInstance = world;
      this.random = new Random(world.getSeed());
   }


   public static class PortalPosition extends BlockPos {
      public long lastUpdateTime;
      public final Teleporter teleporter;

      public PortalPosition(Teleporter teleporter2, BlockPos pos, long l) {
         super(pos.getX(), pos.getY(), pos.getZ());
         this.teleporter = teleporter2;
         this.lastUpdateTime = l;
      }
   }
}
