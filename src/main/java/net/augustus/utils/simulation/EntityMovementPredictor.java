package net.augustus.utils.simulation;

import net.augustus.utils.MoveUtil;
import net.augustus.viaversion.ViaAugustus;
import java.util.ArrayList;
import java.util.List;
import net.augustus.utils.interfaces.MC;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class EntityMovementPredictor implements MC {

   public static ArrayList predictTrajectoryFromSnapshot(EntityLivingBase entity, EntityMotionSnapshot entityMotionSnapshot, int n, double d, double d2, double d3, boolean flag) {
      if (hasInvalidPositionHistory(entity)) {
         ArrayList list = new ArrayList();
         list.add(new Vec3(entity.posX, entity.posY, entity.posZ));
         return list;
      } else {
         int[] ints = estimateStrafeForwardFromSnapshot(entity, entityMotionSnapshot);
         return simulateTrajectoryFromSnapshot(entity, entityMotionSnapshot, ints[0], ints[1], n, false, d, d2, d3, flag);
      }
   }

   private static double[] simulateStateFromServerPos(EntityLivingBase entity, int n, int n2, int n3) {
      double realPosX = entity.realPosX;
      double realPosY = entity.realPosY;
      double realPosZ = entity.realPosZ;
      double d = MathHelper.clamp_double((entity.realPosX - entity.predictedPosX) / 2.0, -10.0, 10.0);
      double d2 = MathHelper.clamp_double((entity.realPosY - entity.predictedPosY) / 2.0, -10.0, 10.0);
      double d3 = MathHelper.clamp_double((entity.realPosZ - entity.predictedPosZ) / 2.0, -10.0, 10.0);
      double prevPosX = entity.prevPosX;
      double prevPosY = entity.prevPosY;
      double prevPosZ = entity.prevPosZ;
      boolean onGround = entity.onGround;
      float f = (float)n2;
      float f2 = (float)n;
      float f3 = entity.width / 2.0F;
      AxisAlignedBB box = new AxisAlignedBB(
         realPosX - (double)f3, realPosY, realPosZ - (double)f3, realPosX + (double)f3, realPosY + (double)entity.height, realPosZ + (double)f3
      );
      AxisAlignedBB box2 = new AxisAlignedBB(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
      boolean isCollidedHorizontally = entity.isCollidedHorizontally;
      boolean isCollidedVertically = entity.isCollidedVertically;
      float f4 = 0.91F;
      if (onGround) {
         f4 = entity.entityWorld.getBlockState(realPosX, realPosY - 1.0, realPosZ).getBlock().slipperiness * 0.91F;
      }

      if (entity.entityWorld.isRemote
         && (
            !entity.entityWorld.isBlockLoaded(new BlockPos((int)realPosX, 0, (int)realPosZ))
               || !entity.entityWorld.getChunkFromBlockCoords(new BlockPos((int)realPosX, 0, (int)realPosZ)).isLoaded()
         )) {
         if (realPosY > 0.0) {
            d2 = -0.1;
         } else {
            d2 = 0.0;
         }
      } else {
         d2 -= 0.08;
      }

      d2 *= 0.9800000190734863;
      d *= (double)f4;
      d3 *= (double)f4;

      for (int i = 0; i < n3; i++) {
         f = (float)n2;
         f2 = (float)n;
         if (entity instanceof EntityPlayer) {
            if (((EntityPlayer)entity).isUsingItem() && !entity.isRiding()) {
               f *= 0.2F;
               f2 *= 0.2F;
            }
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(d) < motionZeroThreshold) {
            d = 0.0;
         }

         if (Math.abs(d2) < motionZeroThreshold) {
            d2 = 0.0;
         }

         if (Math.abs(d3) < motionZeroThreshold) {
            d3 = 0.0;
         }

         f *= 0.98F;
         f2 *= 0.98F;
         float f5 = 0.91F;
         if (onGround) {
            f5 = entity.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(realPosX), MathHelper.floor_double(realPosY) - 1, MathHelper.floor_double(realPosZ)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f6 = 0.16277136F / (f5 * f5 * f5);
         float f7;
         if (onGround) {
            f7 = (float)(mc.thePlayer.getBaseMoveSpeed() * (double)f6);
         } else {
            f7 = entity.jumpMovementFactor;
         }

         float f8 = f * f + f2 * f2;
         if (f8 >= 1.0E-4F) {
            f8 = MathHelper.sqrt_float(f8);
            if (f8 < 1.0F) {
               f8 = 1.0F;
            }

            f8 = f7 / f8;
            f *= f8;
            f2 *= f8;
            float f9 = MathHelper.sin(entity.rotationYawHead * 3.1415927F / 180.0F);
            float f10 = MathHelper.cos(entity.rotationYawHead * 3.1415927F / 180.0F);
            d += (double)(f * f10 - f2 * f9);
            d3 += (double)(f2 * f10 + f * f9);
         }

         f5 = 0.91F;
         if (onGround) {
            f5 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(realPosX), MathHelper.floor_double(realPosY) - 1, MathHelper.floor_double(realPosZ)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d4 = d;
         double d5 = d2;
         double d6 = d3;
         double d7 = d;
         double d8 = d3;
         boolean isSneaking = entity.isSneaking();
         if (entity.onGround && isSneaking) {
            double d9;
            for (d9 = 0.05; d4 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d4, -1.0, 0.0)).isEmpty(); d7 = d4) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }
            }

            for (; d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(0.0, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }

            for (; d4 != 0.0 && d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d4, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }

               d7 = d4;
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }
         }

         List list = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d4, d2, d6));
         AxisAlignedBB box3 = box2;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d5 = box4.calculateYOffset(box2, d5);
         }

         box2 = box2.offset(0.0, d5, 0.0);
         boolean flag2 = onGround || d2 != d5 && d2 < 0.0;

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
            d4 = box5.calculateXOffset(box2, d4);
         }

         box2 = box2.offset(d4, 0.0, 0.0);

         for (AxisAlignedBB box6 : (Iterable<AxisAlignedBB>)(list)) {
            d6 = box6.calculateZOffset(box2, d6);
         }

         box2 = box2.offset(0.0, 0.0, d6);
         if (entity.stepHeight > 0.0F && flag2 && (d7 != d4 || d8 != d6)) {
            double d10 = d4;
            double d11 = d5;
            double d12 = d6;
            AxisAlignedBB box7 = box2;
            d5 = (double)entity.stepHeight;
            List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box3.addCoord(d7, d5, d8));
            AxisAlignedBB box8 = box3.addCoord(d7, 0.0, d8);
            double d13 = d5;

            for (AxisAlignedBB box9 : (Iterable<AxisAlignedBB>)(list2)) {
               d13 = box9.calculateYOffset(box8, d13);
            }

            AxisAlignedBB box10 = box3.offset(0.0, d13, 0.0);
            double d14 = d7;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box11.calculateXOffset(box10, d14);
            }

            box10 = box10.offset(d14, 0.0, 0.0);
            double d15 = d8;

            for (AxisAlignedBB box12 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box12.calculateZOffset(box10, d15);
            }

            box10 = box10.offset(0.0, 0.0, d15);
            AxisAlignedBB box13 = box3;
            double d16 = d5;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
               d16 = box14.calculateYOffset(box13, d16);
            }

            box13 = box13.offset(0.0, d16, 0.0);
            double d17 = d7;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
               d17 = box15.calculateXOffset(box13, d17);
            }

            box13 = box13.offset(d17, 0.0, 0.0);
            double d18 = d8;

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
               d18 = box16.calculateZOffset(box13, d18);
            }

            box13 = box13.offset(0.0, 0.0, d18);
            if ((d14 * d14 + d15 * d15) > (d17 * d17 + d18 * d18)) {
               d4 = d14;
               d6 = d15;
               d5 = -d13;
               box2 = box10;
            } else {
               d4 = d17;
               d6 = d18;
               d5 = -d16;
               box2 = box13;
            }

            for (AxisAlignedBB box17 : (Iterable<AxisAlignedBB>)(list2)) {
               d5 = box17.calculateYOffset(box2, d5);
            }

            box2 = box2.offset(0.0, d5, 0.0);
            if (d10 * d10 + d12 * d12 >= d4 * d4 + d6 * d6) {
               d4 = d10;
               d5 = d11;
               d6 = d12;
               box2 = box7;
            }
         }

         realPosX = (box2.minX + box2.maxX) / 2.0;
         realPosY = box2.minY;
         realPosZ = (box2.minZ + box2.maxZ) / 2.0;
         isCollidedHorizontally = d7 != d4 || d8 != d6;
         isCollidedVertically = d2 != d5;
         onGround = isCollidedVertically && d2 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag3 = false;
         } else {
         }

         int n4 = MathHelper.floor_double(realPosX);
         int n5 = MathHelper.floor_double(realPosY - 0.20000000298023224);
         int n6 = MathHelper.floor_double(realPosZ);
         BlockPos pos = new BlockPos(n4, n5, n6);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d7 != d4) {
            d = 0.0;
         }

         if (d8 != d6) {
            d3 = 0.0;
         }

         if (d2 != d5) {
            if (block instanceof BlockSlime) {
               if (entity.isSneaking()) {
                  d2 = 0.0;
               } else if (d2 < 0.0) {
                  d2 = -d2;
               }
            } else {
               d2 = 0.0;
            }
         }

         if (entity.entityWorld.isRemote
            && (
               !entity.entityWorld.isBlockLoaded(new BlockPos((int)realPosX, 0, (int)realPosZ))
                  || !entity.entityWorld.getChunkFromBlockCoords(new BlockPos((int)realPosX, 0, (int)realPosZ)).isLoaded()
            )) {
            if (realPosY > 0.0) {
               d2 = -0.1;
            } else {
               d2 = 0.0;
            }
         } else {
            d2 -= 0.08;
         }

         d2 *= 0.9800000190734863;
         d *= (double)f5;
         d3 *= (double)f5;
      }

      return new double[]{realPosX, realPosY, realPosZ, prevPosX, prevPosY, prevPosZ, d, d2, d3, onGround ? 1.0 : 0.0, isCollidedHorizontally ? 1.0 : 0.0};
   }

   public static Vec3 predictConstantHorizontalPosition(EntityLivingBase entity, int n) {
      if (hasInvalidPositionHistory(entity)) {
         return new Vec3(entity.posX, entity.posY, entity.posZ);
      } else {
         int[] ints = estimateStrafeForwardFromHistory(entity);
         return simulateConstantHorizontalFromHistory(entity, ints[0], ints[1], n, false);
      }
   }

   public static int[] estimateStrafeForwardFromHistory(EntityLivingBase entity) {
      if (hasInvalidPositionHistory(entity)) {
         return new int[]{0, 0};
      } else {
         double d = 1000.0;
         int[] ints = new int[2];

         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               double d2 = simulateFromHistory(entity, i, j, (byte)1, true).squareDistanceTo(entity.posX, entity.posY, entity.posZ);
               if (d > d2) {
                  d = d2;
                  ints = new int[]{i, j};
               }

               if (d2 < 0.01 && i == 0 && j == 0) {
                  ints = new int[2];
                  d = 0.0;
               }
            }
         }

         return ints;
      }
   }

   public static double[] predictFullState(EntityLivingBase entity, int n) {
      if (hasInvalidPositionHistory(entity)) {
         return new double[]{
            entity.posX,
            entity.posY,
            entity.posZ,
            entity.prevPosX,
            entity.prevPosY,
            entity.prevPosZ,
            entity.motionX,
            entity.motionY,
            entity.motionZ,
            entity.onGround ? 1.0 : 0.0,
            entity.isCollidedHorizontally ? 1.0 : 0.0
         };
      } else {
         int[] ints = estimateStrafeForwardFromServerPos(entity);
         return simulateStateFromServerPos(entity, ints[0], ints[1], n);
      }
   }

   public static ArrayList predictTrajectoryFromClientPos(EntityLivingBase entity, int n) {
      if (hasInvalidPositionHistory(entity)) {
         return new ArrayList();
      } else {
         int[] ints = estimateStrafeForwardFromServerPos(entity);
         return simulateTrajectoryFromClientPos(entity, ints[0], ints[1], n);
      }
   }

   private static Vec3 simulateFromSnapshot(EntityLivingBase entity, EntityMotionSnapshot entityMotionSnapshot, int n, int n2, int n3, boolean flag) {
      Vec3 vec = flag ? entityMotionSnapshot.prevPos : entityMotionSnapshot.currentPos;
      Vec3 vec2 = flag ? entityMotionSnapshot.prevPrevPos : entityMotionSnapshot.prevPos;
      double xCoord = vec.xCoord;
      double yCoord = vec.yCoord;
      double zCoord = vec.zCoord;
      double d = vec.xCoord - vec2.xCoord;
      double d2 = vec.yCoord - vec2.yCoord;
      double d3 = vec.zCoord - vec2.zCoord;
      boolean onGround = entityMotionSnapshot.onGround;
      float f = (float)n2;
      float f2 = (float)n;
      float f3 = entity.width / 2.0F;
      AxisAlignedBB box = new AxisAlignedBB(
         xCoord - (double)f3, yCoord, zCoord - (double)f3, xCoord + (double)f3, yCoord + (double)entity.height, zCoord + (double)f3
      );
      AxisAlignedBB box2 = new AxisAlignedBB(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
      boolean isCollidedHorizontally = entity.isCollidedHorizontally;
      boolean isCollidedVertically = entity.isCollidedVertically;
      entity.isSprinting();

      for (int i = 0; i < n3; i++) {
         d = vec.xCoord - vec2.xCoord;
         d3 = vec.zCoord - vec2.zCoord;
         f = (float)n2;
         f2 = (float)n;
         if (entity instanceof EntityPlayer) {
            if (((EntityPlayer)entity).isUsingItem() && !entity.isRiding()) {
               f *= 0.2F;
               f2 *= 0.2F;
            }
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(d) < motionZeroThreshold) {
            d = 0.0;
         }

         if (Math.abs(d2) < motionZeroThreshold) {
            d2 = 0.0;
         }

         if (Math.abs(d3) < motionZeroThreshold) {
            d3 = 0.0;
         }

         f *= 0.98F;
         f2 *= 0.98F;
         float f4 = 0.91F;
         if (onGround) {
            f4 = entity.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(xCoord), MathHelper.floor_double(yCoord) - 1, MathHelper.floor_double(zCoord)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f5 = 0.16277136F / (f4 * f4 * f4);
         if (onGround) {
            float f6 = entity.getAIMoveSpeed() * f5;
         } else {
            float jumpMovementFactor = entity.jumpMovementFactor;
         }

         float f7 = f * f + f2 * f2;
         f4 = 0.91F;
         if (onGround) {
            f4 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(xCoord), MathHelper.floor_double(yCoord) - 1, MathHelper.floor_double(zCoord)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d4 = d;
         double d5 = d2;
         double d6 = d3;
         double d7 = d;
         double d8 = d3;
         if (d > 100.0 || d2 > 100.0 || d3 > 100.0) {
            return new Vec3(xCoord, yCoord, zCoord);
         }

         boolean sneaking = entityMotionSnapshot.sneaking;
         if (entityMotionSnapshot.onGround && sneaking) {
            double d9;
            for (d9 = 0.05; d4 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d4, -1.0, 0.0)).isEmpty(); d7 = d4) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }
            }

            for (; d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(0.0, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }

            for (; d4 != 0.0 && d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d4, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }

               d7 = d4;
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }
         }

         List list = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d4, d2, d6));
         AxisAlignedBB box3 = box2;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d5 = box4.calculateYOffset(box2, d5);
         }

         box2 = box2.offset(0.0, d5, 0.0);
         boolean flag3 = onGround || d2 != d5 && d2 < 0.0;

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
            d4 = box5.calculateXOffset(box2, d4);
         }

         box2 = box2.offset(d4, 0.0, 0.0);

         for (AxisAlignedBB box6 : (Iterable<AxisAlignedBB>)(list)) {
            d6 = box6.calculateZOffset(box2, d6);
         }

         box2 = box2.offset(0.0, 0.0, d6);
         if (entity.stepHeight > 0.0F && flag3 && (d7 != d4 || d8 != d6)) {
            double d10 = d4;
            double d11 = d5;
            double d12 = d6;
            AxisAlignedBB box7 = box2;
            d5 = (double)entity.stepHeight;
            List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box3.addCoord(d7, d5, d8));
            AxisAlignedBB box8 = box3.addCoord(d7, 0.0, d8);
            double d13 = d5;

            for (AxisAlignedBB box9 : (Iterable<AxisAlignedBB>)(list2)) {
               d13 = box9.calculateYOffset(box8, d13);
            }

            AxisAlignedBB box10 = box3.offset(0.0, d13, 0.0);
            double d14 = d7;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box11.calculateXOffset(box10, d14);
            }

            box10 = box10.offset(d14, 0.0, 0.0);
            double d15 = d8;

            for (AxisAlignedBB box12 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box12.calculateZOffset(box10, d15);
            }

            box10 = box10.offset(0.0, 0.0, d15);
            AxisAlignedBB box13 = box3;
            double d16 = d5;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
               d16 = box14.calculateYOffset(box13, d16);
            }

            box13 = box13.offset(0.0, d16, 0.0);
            double d17 = d7;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
               d17 = box15.calculateXOffset(box13, d17);
            }

            box13 = box13.offset(d17, 0.0, 0.0);
            double d18 = d8;

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
               d18 = box16.calculateZOffset(box13, d18);
            }

            box13 = box13.offset(0.0, 0.0, d18);
            if ((d14 * d14 + d15 * d15) > (d17 * d17 + d18 * d18)) {
               d4 = d14;
               d6 = d15;
               d5 = -d13;
               box2 = box10;
            } else {
               d4 = d17;
               d6 = d18;
               d5 = -d16;
               box2 = box13;
            }

            for (AxisAlignedBB box17 : (Iterable<AxisAlignedBB>)(list2)) {
               d5 = box17.calculateYOffset(box2, d5);
            }

            box2 = box2.offset(0.0, d5, 0.0);
            if (d10 * d10 + d12 * d12 >= d4 * d4 + d6 * d6) {
               d4 = d10;
               d5 = d11;
               d6 = d12;
               box2 = box7;
            }
         }

         xCoord = (box2.minX + box2.maxX) / 2.0;
         yCoord = box2.minY;
         zCoord = (box2.minZ + box2.maxZ) / 2.0;
         isCollidedHorizontally = d7 != d4 || d8 != d6;
         isCollidedVertically = d2 != d5;
         onGround = isCollidedVertically && d2 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag4 = false;
         } else {
         }

         int n4 = MathHelper.floor_double(xCoord);
         int n5 = MathHelper.floor_double(yCoord - 0.20000000298023224);
         int n6 = MathHelper.floor_double(zCoord);
         BlockPos pos = new BlockPos(n4, n5, n6);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d7 != d4) {
            d = 0.0;
         }

         if (d8 != d6) {
            d3 = 0.0;
         }

         if (d2 != d5) {
            if (block instanceof BlockSlime) {
               if (entity.isSneaking()) {
                  d2 = 0.0;
               } else if (d2 < 0.0) {
                  d2 = -d2;
               }
            } else {
               d2 = 0.0;
            }
         }

         if (entity.entityWorld.isRemote
            && (
               !entity.entityWorld.isBlockLoaded(new BlockPos((int)xCoord, 0, (int)zCoord))
                  || !entity.entityWorld.getChunkFromBlockCoords(new BlockPos((int)xCoord, 0, (int)zCoord)).isLoaded()
            )) {
            if (yCoord > 0.0) {
               d2 = -0.1;
            } else {
               d2 = 0.0;
            }
         } else {
            d2 -= 0.08;
         }

         d2 *= 0.9800000190734863;
         d *= (double)f4;
         d3 *= (double)f4;
      }

      return new Vec3(xCoord, yCoord, zCoord);
   }

   public static int[] estimateStrafeForwardFromServerPos(EntityLivingBase entity) {
      if (hasInvalidPositionHistory(entity)) {
         return new int[]{0, 0};
      } else {
         double d = 1000.0;
         int[] ints = new int[2];

         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               double d2 = simulateFromPrevServerPos(entity, i, j, (byte)2).squareDistanceTo(entity.realPosX, entity.realPosY, entity.realPosZ);
               if (d > d2) {
                  d = d2;
                  ints = new int[]{i, j};
               }

               if (d2 < 0.01 && i == 0 && j == 0) {
                  ints = new int[2];
                  d = 0.0;
               }
            }
         }

         return ints;
      }
   }

   public static Vec3 predictPositionFromSnapshot(EntityLivingBase entity, EntityMotionSnapshot entityMotionSnapshot, int n) {
      if (hasInvalidPositionHistory(entity)) {
         return new Vec3(entity.posX, entity.posY, entity.posZ);
      } else {
         int[] ints = estimateStrafeForwardFromSnapshot(entity, entityMotionSnapshot);
         return simulateFromSnapshot(entity, entityMotionSnapshot, ints[0], ints[1], n, false);
      }
   }

   public static Vec3 predictPositionFromServerPos(EntityLivingBase entity, int n) {
      if (hasInvalidPositionHistory(entity)) {
         return new Vec3(entity.posX, entity.posY, entity.posZ);
      } else {
         int[] ints = estimateStrafeForwardFromServerPos(entity);
         double[] doubles = simulateStateFromServerPos(entity, ints[0], ints[1], n);
         return new Vec3(doubles[0], doubles[1], doubles[2]);
      }
   }

   private static Vec3 simulateFromHistory(EntityLivingBase entity, int n, int n2, int n3, boolean flag) {
      int n4 = flag ? 1 : 0;
      Vec3 fromEnd = (Vec3)entity.positionHistory.getFromEnd(n4);
      int n5 = 1 + n4;
      Vec3 fromEnd2 = (Vec3)entity.positionHistory.getFromEnd(n5);
      double xCoord = fromEnd.xCoord;
      double yCoord = fromEnd.yCoord;
      double zCoord = fromEnd.zCoord;
      double d = fromEnd.xCoord - fromEnd2.xCoord;
      double d2 = fromEnd.yCoord - fromEnd2.yCoord;
      double d3 = fromEnd.zCoord - fromEnd2.zCoord;
      boolean onGround = entity.onGround;
      float f = (float)n2;
      float f2 = (float)n;
      float f3 = entity.width / 2.0F;
      AxisAlignedBB box = new AxisAlignedBB(
         xCoord - (double)f3, yCoord, zCoord - (double)f3, xCoord + (double)f3, yCoord + (double)entity.height, zCoord + (double)f3
      );
      AxisAlignedBB box2 = new AxisAlignedBB(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
      boolean isCollidedHorizontally = entity.isCollidedHorizontally;
      boolean isCollidedVertically = entity.isCollidedVertically;
      entity.isSprinting();

      for (int i = 0; i < n3; i++) {
         f = (float)n2;
         f2 = (float)n;
         if (entity instanceof EntityPlayer) {
            if (((EntityPlayer)entity).isUsingItem() && !entity.isRiding()) {
               f *= 0.2F;
               f2 *= 0.2F;
            }
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(d) < motionZeroThreshold) {
            d = 0.0;
         }

         if (Math.abs(d2) < motionZeroThreshold) {
            d2 = 0.0;
         }

         if (Math.abs(d3) < motionZeroThreshold) {
            d3 = 0.0;
         }

         f *= 0.98F;
         f2 *= 0.98F;
         float f4 = 0.91F;
         if (onGround) {
            f4 = entity.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(xCoord), MathHelper.floor_double(yCoord) - 1, MathHelper.floor_double(zCoord)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f5 = 0.16277136F / (f4 * f4 * f4);
         float f6;
         if (onGround) {
            f6 = entity.getAIMoveSpeed() * f5;
         } else {
            f6 = entity.jumpMovementFactor;
         }

         float f7 = f * f + f2 * f2;
         if (f7 >= 1.0E-4F) {
            f7 = MathHelper.sqrt_float(f7);
            if (f7 < 1.0F) {
               f7 = 1.0F;
            }

            f7 = f6 / f7;
            f *= f7;
            f2 *= f7;
            float f8 = MathHelper.sin(entity.prevRotationYawHead * 3.1415927F / 180.0F);
            float f9 = MathHelper.cos(entity.prevRotationYawHead * 3.1415927F / 180.0F);
            d += (double)(f * f9 - f2 * f8);
            d3 += (double)(f2 * f9 + f * f8);
         }

         f4 = 0.91F;
         if (onGround) {
            f4 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(xCoord), MathHelper.floor_double(yCoord) - 1, MathHelper.floor_double(zCoord)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d4 = d;
         double d5 = d2;
         double d6 = d3;
         double d7 = d;
         double d8 = d3;
         if (d > 100.0 || d2 > 100.0 || d3 > 100.0) {
            return new Vec3(xCoord, yCoord, zCoord);
         }

         boolean isSneaking = entity.isSneaking();
         if (entity.onGround && isSneaking) {
            double d9;
            for (d9 = 0.05; d4 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d4, -1.0, 0.0)).isEmpty(); d7 = d4) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }
            }

            for (; d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(0.0, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }

            for (; d4 != 0.0 && d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d4, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }

               d7 = d4;
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }
         }

         List list = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d4, d2, d6));
         AxisAlignedBB box3 = box2;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d5 = box4.calculateYOffset(box2, d5);
         }

         box2 = box2.offset(0.0, d5, 0.0);
         boolean flag3 = onGround || d2 != d5 && d2 < 0.0;

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
            d4 = box5.calculateXOffset(box2, d4);
         }

         box2 = box2.offset(d4, 0.0, 0.0);

         for (AxisAlignedBB box6 : (Iterable<AxisAlignedBB>)(list)) {
            d6 = box6.calculateZOffset(box2, d6);
         }

         box2 = box2.offset(0.0, 0.0, d6);
         if (entity.stepHeight > 0.0F && flag3 && (d7 != d4 || d8 != d6)) {
            double d10 = d4;
            double d11 = d5;
            double d12 = d6;
            AxisAlignedBB box7 = box2;
            d5 = (double)entity.stepHeight;
            List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box3.addCoord(d7, d5, d8));
            AxisAlignedBB box8 = box3.addCoord(d7, 0.0, d8);
            double d13 = d5;

            for (AxisAlignedBB box9 : (Iterable<AxisAlignedBB>)(list2)) {
               d13 = box9.calculateYOffset(box8, d13);
            }

            AxisAlignedBB box10 = box3.offset(0.0, d13, 0.0);
            double d14 = d7;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box11.calculateXOffset(box10, d14);
            }

            box10 = box10.offset(d14, 0.0, 0.0);
            double d15 = d8;

            for (AxisAlignedBB box12 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box12.calculateZOffset(box10, d15);
            }

            box10 = box10.offset(0.0, 0.0, d15);
            AxisAlignedBB box13 = box3;
            double d16 = d5;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
               d16 = box14.calculateYOffset(box13, d16);
            }

            box13 = box13.offset(0.0, d16, 0.0);
            double d17 = d7;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
               d17 = box15.calculateXOffset(box13, d17);
            }

            box13 = box13.offset(d17, 0.0, 0.0);
            double d18 = d8;

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
               d18 = box16.calculateZOffset(box13, d18);
            }

            box13 = box13.offset(0.0, 0.0, d18);
            if ((d14 * d14 + d15 * d15) > (d17 * d17 + d18 * d18)) {
               d4 = d14;
               d6 = d15;
               d5 = -d13;
               box2 = box10;
            } else {
               d4 = d17;
               d6 = d18;
               d5 = -d16;
               box2 = box13;
            }

            for (AxisAlignedBB box17 : (Iterable<AxisAlignedBB>)(list2)) {
               d5 = box17.calculateYOffset(box2, d5);
            }

            box2 = box2.offset(0.0, d5, 0.0);
            if (d10 * d10 + d12 * d12 >= d4 * d4 + d6 * d6) {
               d4 = d10;
               d5 = d11;
               d6 = d12;
               box2 = box7;
            }
         }

         xCoord = (box2.minX + box2.maxX) / 2.0;
         yCoord = box2.minY;
         zCoord = (box2.minZ + box2.maxZ) / 2.0;
         isCollidedHorizontally = d7 != d4 || d8 != d6;
         isCollidedVertically = d2 != d5;
         onGround = isCollidedVertically && d2 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag4 = false;
         } else {
         }

         int n6 = MathHelper.floor_double(xCoord);
         int n7 = MathHelper.floor_double(yCoord - 0.20000000298023224);
         int n8 = MathHelper.floor_double(zCoord);
         BlockPos pos = new BlockPos(n6, n7, n8);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d7 != d4) {
            d = 0.0;
         }

         if (d8 != d6) {
            d3 = 0.0;
         }

         if (d2 != d5) {
            if (block instanceof BlockSlime) {
               if (entity.isSneaking()) {
                  d2 = 0.0;
               } else if (d2 < 0.0) {
                  d2 = -d2;
               }
            } else {
               d2 = 0.0;
            }
         }

         if (entity.entityWorld.isRemote
            && (
               !entity.entityWorld.isBlockLoaded(new BlockPos((int)xCoord, 0, (int)zCoord))
                  || !entity.entityWorld.getChunkFromBlockCoords(new BlockPos((int)xCoord, 0, (int)zCoord)).isLoaded()
            )) {
            if (yCoord > 0.0) {
               d2 = -0.1;
            } else {
               d2 = 0.0;
            }
         } else {
            d2 -= 0.08;
         }

         d2 *= 0.9800000190734863;
         d *= (double)f4;
         d3 *= (double)f4;
      }

      return new Vec3(xCoord, yCoord, zCoord);
   }

   private static Vec3 simulateConstantHorizontalFromHistory(EntityLivingBase entity, int n, int n2, int n3, boolean flag) {
      int n4 = flag ? 1 : 0;
      Vec3 fromEnd = (Vec3)entity.positionHistory.getFromEnd(n4);
      int n5 = 1 + n4;
      Vec3 fromEnd2 = (Vec3)entity.positionHistory.getFromEnd(n5);
      double xCoord = fromEnd.xCoord;
      double yCoord = fromEnd.yCoord;
      double zCoord = fromEnd.zCoord;
      double d = fromEnd.xCoord - fromEnd2.xCoord;
      double d2 = fromEnd.yCoord - fromEnd2.yCoord;
      double d3 = fromEnd.zCoord - fromEnd2.zCoord;
      boolean onGround = entity.onGround;
      float f = (float)n2;
      float f2 = (float)n;
      float f3 = entity.width / 2.0F;
      AxisAlignedBB box = new AxisAlignedBB(
         xCoord - (double)f3, yCoord, zCoord - (double)f3, xCoord + (double)f3, yCoord + (double)entity.height, zCoord + (double)f3
      );
      AxisAlignedBB box2 = new AxisAlignedBB(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
      boolean isCollidedHorizontally = entity.isCollidedHorizontally;
      boolean isCollidedVertically = entity.isCollidedVertically;
      entity.isSprinting();

      for (int i = 0; i < n3; i++) {
         d = fromEnd.xCoord - fromEnd2.xCoord;
         d3 = fromEnd.zCoord - fromEnd2.zCoord;
         f = (float)n2;
         f2 = (float)n;
         if (entity instanceof EntityPlayer) {
            if (((EntityPlayer)entity).isUsingItem() && !entity.isRiding()) {
               f *= 0.2F;
               f2 *= 0.2F;
            }
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(d) < motionZeroThreshold) {
            d = 0.0;
         }

         if (Math.abs(d2) < motionZeroThreshold) {
            d2 = 0.0;
         }

         if (Math.abs(d3) < motionZeroThreshold) {
            d3 = 0.0;
         }

         f *= 0.98F;
         f2 *= 0.98F;
         float f4 = 0.91F;
         if (onGround) {
            f4 = entity.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(xCoord), MathHelper.floor_double(yCoord) - 1, MathHelper.floor_double(zCoord)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f5 = 0.16277136F / (f4 * f4 * f4);
         float f6;
         if (onGround) {
            f6 = entity.getAIMoveSpeed() * f5;
         } else {
            f6 = entity.jumpMovementFactor;
         }

         float f7 = f * f + f2 * f2;
         if (f7 >= 1.0E-4F) {
            f7 = MathHelper.sqrt_float(f7);
            if (f7 < 1.0F) {
               f7 = 1.0F;
            }

            f7 = f6 / f7;
            f *= f7;
            f2 *= f7;
            float f8 = MathHelper.sin(entity.prevRotationYawHead * 3.1415927F / 180.0F);
            float f9 = MathHelper.cos(entity.prevRotationYawHead * 3.1415927F / 180.0F);
            d += (double)(f * f9 - f2 * f8);
            d3 += (double)(f2 * f9 + f * f8);
         }

         f4 = 0.91F;
         if (onGround) {
            f4 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(xCoord), MathHelper.floor_double(yCoord) - 1, MathHelper.floor_double(zCoord)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d4 = d;
         double d5 = d2;
         double d6 = d3;
         double d7 = d;
         double d8 = d3;
         if (d > 100.0 || d2 > 100.0 || d3 > 100.0) {
            return new Vec3(xCoord, yCoord, zCoord);
         }

         boolean isSneaking = entity.isSneaking();
         if (entity.onGround && isSneaking) {
            double d9;
            for (d9 = 0.05; d4 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d4, -1.0, 0.0)).isEmpty(); d7 = d4) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }
            }

            for (; d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(0.0, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }

            for (; d4 != 0.0 && d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d4, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }

               d7 = d4;
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }
         }

         List list = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d4, d2, d6));
         AxisAlignedBB box3 = box2;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d5 = box4.calculateYOffset(box2, d5);
         }

         box2 = box2.offset(0.0, d5, 0.0);
         boolean flag3 = onGround || d2 != d5 && d2 < 0.0;

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
            d4 = box5.calculateXOffset(box2, d4);
         }

         box2 = box2.offset(d4, 0.0, 0.0);

         for (AxisAlignedBB box6 : (Iterable<AxisAlignedBB>)(list)) {
            d6 = box6.calculateZOffset(box2, d6);
         }

         box2 = box2.offset(0.0, 0.0, d6);
         if (entity.stepHeight > 0.0F && flag3 && (d7 != d4 || d8 != d6)) {
            double d10 = d4;
            double d11 = d5;
            double d12 = d6;
            AxisAlignedBB box7 = box2;
            d5 = (double)entity.stepHeight;
            List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box3.addCoord(d7, d5, d8));
            AxisAlignedBB box8 = box3.addCoord(d7, 0.0, d8);
            double d13 = d5;

            for (AxisAlignedBB box9 : (Iterable<AxisAlignedBB>)(list2)) {
               d13 = box9.calculateYOffset(box8, d13);
            }

            AxisAlignedBB box10 = box3.offset(0.0, d13, 0.0);
            double d14 = d7;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box11.calculateXOffset(box10, d14);
            }

            box10 = box10.offset(d14, 0.0, 0.0);
            double d15 = d8;

            for (AxisAlignedBB box12 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box12.calculateZOffset(box10, d15);
            }

            box10 = box10.offset(0.0, 0.0, d15);
            AxisAlignedBB box13 = box3;
            double d16 = d5;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
               d16 = box14.calculateYOffset(box13, d16);
            }

            box13 = box13.offset(0.0, d16, 0.0);
            double d17 = d7;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
               d17 = box15.calculateXOffset(box13, d17);
            }

            box13 = box13.offset(d17, 0.0, 0.0);
            double d18 = d8;

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
               d18 = box16.calculateZOffset(box13, d18);
            }

            box13 = box13.offset(0.0, 0.0, d18);
            if ((d14 * d14 + d15 * d15) > (d17 * d17 + d18 * d18)) {
               d4 = d14;
               d6 = d15;
               d5 = -d13;
               box2 = box10;
            } else {
               d4 = d17;
               d6 = d18;
               d5 = -d16;
               box2 = box13;
            }

            for (AxisAlignedBB box17 : (Iterable<AxisAlignedBB>)(list2)) {
               d5 = box17.calculateYOffset(box2, d5);
            }

            box2 = box2.offset(0.0, d5, 0.0);
            if (d10 * d10 + d12 * d12 >= d4 * d4 + d6 * d6) {
               d4 = d10;
               d5 = d11;
               d6 = d12;
               box2 = box7;
            }
         }

         xCoord = (box2.minX + box2.maxX) / 2.0;
         yCoord = box2.minY;
         zCoord = (box2.minZ + box2.maxZ) / 2.0;
         isCollidedHorizontally = d7 != d4 || d8 != d6;
         isCollidedVertically = d2 != d5;
         onGround = isCollidedVertically && d2 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag4 = false;
         } else {
         }

         int n6 = MathHelper.floor_double(xCoord);
         int n7 = MathHelper.floor_double(yCoord - 0.20000000298023224);
         int n8 = MathHelper.floor_double(zCoord);
         BlockPos pos = new BlockPos(n6, n7, n8);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d7 != d4) {
            d = 0.0;
         }

         if (d8 != d6) {
            d3 = 0.0;
         }

         if (d2 != d5) {
            if (block instanceof BlockSlime) {
               if (entity.isSneaking()) {
                  d2 = 0.0;
               } else if (d2 < 0.0) {
                  d2 = -d2;
               }
            } else {
               d2 = 0.0;
            }
         }

         if (entity.entityWorld.isRemote
            && (
               !entity.entityWorld.isBlockLoaded(new BlockPos((int)xCoord, 0, (int)zCoord))
                  || !entity.entityWorld.getChunkFromBlockCoords(new BlockPos((int)xCoord, 0, (int)zCoord)).isLoaded()
            )) {
            if (yCoord > 0.0) {
               d2 = -0.1;
            } else {
               d2 = 0.0;
            }
         } else {
            d2 -= 0.08;
         }

         d2 *= 0.9800000190734863;
         d *= (double)f4;
         d3 *= (double)f4;
      }

      return new Vec3(xCoord, yCoord, zCoord);
   }

   public static Vec3 predictPositionFromHistory(EntityLivingBase entity, int n) {
      if (hasInvalidPositionHistory(entity)) {
         return new Vec3(entity.posX, entity.posY, entity.posZ);
      } else {
         int[] ints = estimateStrafeForwardFromHistory(entity);
         return simulateFromHistory(entity, ints[0], ints[1], n, false);
      }
   }

   public static boolean hasInvalidPositionHistory(EntityLivingBase entity) {
      return entity == null
         || entity.trackedPosX == 1.7976931348623157E308
         || entity.trackedPosY == 1.7976931348623157E308
         || entity.trackedPosZ == 1.7976931348623157E308
         || entity.realPosX == 1.7976931348623157E308
         || entity.realPosY == 1.7976931348623157E308
         || entity.realPosZ == 1.7976931348623157E308
         || entity.predictedPosX == 1.7976931348623157E308
         || entity.predictedPosY == 1.7976931348623157E308
         || entity.predictedPosZ == 1.7976931348623157E308;
   }

   public static Vec3 extrapolateLinearHorizontal(EntityLivingBase entity, int n) {
      if (entity == null) {
         return new Vec3(0.0, 0.0, 0.0);
      } else {
         double[] doubles = MoveUtil.calculateMotion(0.15, 0.0F, 1.0F, entity.rotationYaw);
         double d2 = entity.posX + (entity.posX - entity.lastTickPosX);
         double d3 = entity.posZ + (entity.posZ - entity.lastTickPosZ);

         for (int i = 0; i < n; i++) {
            d2 += doubles[0];
            d3 += doubles[1];
         }

         return new Vec3(d2, entity.posY, d3);
      }
   }

   public static ArrayList predictTrajectoryFromServerPos(EntityLivingBase entity, int n) {
      if (hasInvalidPositionHistory(entity)) {
         return new ArrayList();
      } else {
         int[] ints = estimateStrafeForwardFromServerPos(entity);
         return simulateTrajectoryFromServerPos(entity, ints[0], ints[1], n);
      }
   }

   public static int[] estimateStrafeForwardFromSnapshot(EntityLivingBase entity, EntityMotionSnapshot entityMotionSnapshot) {
      if (hasInvalidPositionHistory(entity)) {
         return new int[]{0, 0};
      } else {
         double d = 1000.0;
         int[] ints = new int[2];

         for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
               double d2 = simulateFromSnapshot(entity, entityMotionSnapshot, i, j, (byte)1, true).squareDistanceTo(entityMotionSnapshot.currentPos);
               if (d > d2) {
                  d = d2;
                  ints = new int[]{i, j};
               }

               if (d2 < 0.01 && i == 0 && j == 0) {
                  ints = new int[2];
                  d = 0.0;
               }
            }
         }

         return ints;
      }
   }

   private static ArrayList simulateTrajectoryFromClientPos(EntityLivingBase entity, int n, int n2, int n3) {
      ArrayList list = new ArrayList();
      double posX = entity.posX;
      double posY = entity.posY;
      double posZ = entity.posZ;
      double lastTickPosX = entity.lastTickPosX;
      double lastTickPosY = entity.lastTickPosY;
      double lastTickPosZ = entity.lastTickPosZ;
      double d = posX - lastTickPosX;
      double d2 = posY - lastTickPosY;
      double d3 = posZ - lastTickPosZ;
      boolean onGround = entity.onGround;
      float f = (float)n2;
      float f2 = (float)n;
      float f3 = entity.width / 2.0F;
      AxisAlignedBB box = new AxisAlignedBB(
         posX - (double)f3, posY, posZ - (double)f3, posX + (double)f3, posY + (double)entity.height, posZ + (double)f3
      );
      AxisAlignedBB box2 = new AxisAlignedBB(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
      boolean isCollidedHorizontally = entity.isCollidedHorizontally;
      boolean isCollidedVertically = entity.isCollidedVertically;
      float f4 = 0.91F;
      if (onGround) {
         f4 = entity.entityWorld
               .getBlockState(new BlockPos(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)))
               .getBlock()
               .slipperiness
            * 0.91F;
      }

      if (entity.entityWorld.isRemote
         && (
            !entity.entityWorld.isBlockLoaded(new BlockPos((int)posX, 0, (int)posZ))
               || !entity.entityWorld.getChunkFromBlockCoords(new BlockPos((int)posX, 0, (int)posZ)).isLoaded()
         )) {
         if (posY > 0.0) {
            d2 = -0.1;
         } else {
            d2 = 0.0;
         }
      } else {
         d2 -= 0.08;
      }

      d2 *= 0.9800000190734863;
      d *= (double)f4;
      d3 *= (double)f4;

      for (int i = 0; i < n3; i++) {
         d = posX - lastTickPosX;
         d3 = posZ - lastTickPosZ;
         f = (float)n2;
         f2 = (float)n;
         if (entity instanceof EntityPlayer) {
            if (((EntityPlayer)entity).isUsingItem() && !entity.isRiding()) {
               f *= 0.2F;
               f2 *= 0.2F;
            }
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(d) < motionZeroThreshold) {
            d = 0.0;
         }

         if (Math.abs(d2) < motionZeroThreshold) {
            d2 = 0.0;
         }

         if (Math.abs(d3) < motionZeroThreshold) {
            d3 = 0.0;
         }

         f *= 0.98F;
         f2 *= 0.98F;
         float f5 = 0.91F;
         if (onGround) {
            f5 = entity.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f6 = 0.16277136F / (f5 * f5 * f5);
         float f7;
         if (onGround) {
            f7 = (float)(mc.thePlayer.getBaseMoveSpeed() * (double)f6);
         } else {
            f7 = entity.jumpMovementFactor;
         }

         float f8 = f * f + f2 * f2;
         if (f8 >= 1.0E-4F) {
            f8 = MathHelper.sqrt_float(f8);
            if (f8 < 1.0F) {
               f8 = 1.0F;
            }

            f8 = f7 / f8;
            f *= f8;
            f2 *= f8;
            float f9 = MathHelper.sin(entity.rotationYawHead * 3.1415927F / 180.0F);
            float f10 = MathHelper.cos(entity.rotationYawHead * 3.1415927F / 180.0F);
            d += (double)(f * f10 - f2 * f9);
            d3 += (double)(f2 * f10 + f * f9);
         }

         f5 = 0.91F;
         if (onGround) {
            f5 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         if (d > 10.0 || d2 > 10.0 || d3 > 10.0) {
            return list;
         }

         double d4 = d;
         double d5 = d2;
         double d6 = d3;
         double d7 = d;
         double d8 = d3;
         boolean isSneaking = entity.isSneaking();
         if (entity.onGround && isSneaking) {
            double d9;
            for (d9 = 0.05; d4 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d4, -1.0, 0.0)).isEmpty(); d7 = d4) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }
            }

            for (; d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(0.0, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }

            for (; d4 != 0.0 && d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d4, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }

               d7 = d4;
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }
         }

         List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d4, d2, d6));
         AxisAlignedBB box3 = box2;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list2)) {
            d5 = box4.calculateYOffset(box2, d5);
         }

         box2 = box2.offset(0.0, d5, 0.0);
         boolean flag2 = onGround || d2 != d5 && d2 < 0.0;

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list2)) {
            d4 = box5.calculateXOffset(box2, d4);
         }

         box2 = box2.offset(d4, 0.0, 0.0);

         for (AxisAlignedBB box6 : (Iterable<AxisAlignedBB>)(list2)) {
            d6 = box6.calculateZOffset(box2, d6);
         }

         box2 = box2.offset(0.0, 0.0, d6);
         if (entity.stepHeight > 0.0F && flag2 && (d7 != d4 || d8 != d6)) {
            double d10 = d4;
            double d11 = d5;
            double d12 = d6;
            AxisAlignedBB box7 = box2;
            d5 = (double)entity.stepHeight;
            List list3 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box3.addCoord(d7, d5, d8));
            AxisAlignedBB box8 = box3.addCoord(d7, 0.0, d8);
            double d13 = d5;

            for (AxisAlignedBB box9 : (Iterable<AxisAlignedBB>)(list3)) {
               d13 = box9.calculateYOffset(box8, d13);
            }

            AxisAlignedBB box10 = box3.offset(0.0, d13, 0.0);
            double d14 = d7;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list3)) {
               d14 = box11.calculateXOffset(box10, d14);
            }

            box10 = box10.offset(d14, 0.0, 0.0);
            double d15 = d8;

            for (AxisAlignedBB box12 : (Iterable<AxisAlignedBB>)(list3)) {
               d15 = box12.calculateZOffset(box10, d15);
            }

            box10 = box10.offset(0.0, 0.0, d15);
            AxisAlignedBB box13 = box3;
            double d16 = d5;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list3)) {
               d16 = box14.calculateYOffset(box13, d16);
            }

            box13 = box13.offset(0.0, d16, 0.0);
            double d17 = d7;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list3)) {
               d17 = box15.calculateXOffset(box13, d17);
            }

            box13 = box13.offset(d17, 0.0, 0.0);
            double d18 = d8;

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list3)) {
               d18 = box16.calculateZOffset(box13, d18);
            }

            box13 = box13.offset(0.0, 0.0, d18);
            if ((d14 * d14 + d15 * d15) > (d17 * d17 + d18 * d18)) {
               d4 = d14;
               d6 = d15;
               d5 = -d13;
               box2 = box10;
            } else {
               d4 = d17;
               d6 = d18;
               d5 = -d16;
               box2 = box13;
            }

            for (AxisAlignedBB box17 : (Iterable<AxisAlignedBB>)(list3)) {
               d5 = box17.calculateYOffset(box2, d5);
            }

            box2 = box2.offset(0.0, d5, 0.0);
            if (d10 * d10 + d12 * d12 >= d4 * d4 + d6 * d6) {
               d4 = d10;
               d5 = d11;
               d6 = d12;
               box2 = box7;
            }
         }

         posX = (box2.minX + box2.maxX) / 2.0;
         posY = box2.minY;
         posZ = (box2.minZ + box2.maxZ) / 2.0;
         isCollidedHorizontally = d7 != d4 || d8 != d6;
         isCollidedVertically = d2 != d5;
         onGround = isCollidedVertically && d2 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag3 = false;
         } else {
         }

         int n4 = MathHelper.floor_double(posX);
         int n5 = MathHelper.floor_double(posY - 0.20000000298023224);
         int n6 = MathHelper.floor_double(posZ);
         BlockPos pos = new BlockPos(n4, n5, n6);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d7 != d4) {
            d = 0.0;
         }

         if (d8 != d6) {
            d3 = 0.0;
         }

         if (d2 != d5) {
            if (block instanceof BlockSlime) {
               if (entity.isSneaking()) {
                  d2 = 0.0;
               } else if (d2 < 0.0) {
                  d2 = -d2;
               }
            } else {
               d2 = 0.0;
            }
         }

         if (entity.entityWorld.isRemote
            && (
               !entity.entityWorld.isBlockLoaded(new BlockPos((int)posX, 0, (int)posZ))
                  || !entity.entityWorld.getChunkFromBlockCoords(new BlockPos((int)posX, 0, (int)posZ)).isLoaded()
            )) {
            if (posY > 0.0) {
               d2 = -0.1;
            } else {
               d2 = 0.0;
            }
         } else {
            d2 -= 0.08;
         }

         d2 *= 0.9800000190734863;
         d *= (double)f5;
         d3 *= (double)f5;
         list.add(new Vec3(posX, posY, posZ));
      }

      return list;
   }

   private static ArrayList simulateTrajectoryFromSnapshot(
      EntityLivingBase entity, EntityMotionSnapshot entityMotionSnapshot, int n, int n2, int n3, boolean flag, double d, double d2, double d3, boolean flag2
   ) {
      Vec3 vec = flag ? entityMotionSnapshot.prevPos : entityMotionSnapshot.currentPos;
      Vec3 vec2 = flag ? entityMotionSnapshot.prevPrevPos : entityMotionSnapshot.prevPos;
      double xCoord = vec.xCoord;
      double yCoord = vec.yCoord;
      double zCoord = vec.zCoord;
      if (flag2) {
         xCoord = (double)entity.realServerPosX / 32.0;
         yCoord = (double)entity.realServerPosY / 32.0;
         zCoord = (double)entity.realServerPosZ / 32.0;
      }

      double d4 = vec.xCoord - vec2.xCoord;
      double d5 = d2;
      double d6 = vec.zCoord - vec2.zCoord;
      boolean onGround = entityMotionSnapshot.onGround;
      float f = (float)n2;
      float f2 = (float)n;
      float f3 = entity.width / 2.0F;
      AxisAlignedBB box = new AxisAlignedBB(
         xCoord - (double)f3, yCoord, zCoord - (double)f3, xCoord + (double)f3, yCoord + (double)entity.height, zCoord + (double)f3
      );
      AxisAlignedBB box2 = new AxisAlignedBB(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
      boolean isCollidedHorizontally = entity.isCollidedHorizontally;
      boolean isCollidedVertically = entity.isCollidedVertically;
      entity.isSprinting();
      ArrayList list = new ArrayList();

      for (int i = 0; i < n3; i++) {
         d4 = d;
         d6 = d3;
         f = (float)n2;
         f2 = (float)n;
         if (entity instanceof EntityPlayer) {
            if (((EntityPlayer)entity).isUsingItem() && !entity.isRiding()) {
               f *= 0.2F;
               f2 *= 0.2F;
            }
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(d) < motionZeroThreshold) {
            d4 = 0.0;
         }

         if (Math.abs(d5) < motionZeroThreshold) {
            d5 = 0.0;
         }

         if (Math.abs(d3) < motionZeroThreshold) {
            d6 = 0.0;
         }

         f *= 0.98F;
         f2 *= 0.98F;
         float f4 = 0.91F;
         if (onGround) {
            f4 = entity.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(xCoord), MathHelper.floor_double(yCoord) - 1, MathHelper.floor_double(zCoord)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f5 = 0.16277136F / (f4 * f4 * f4);
         if (onGround) {
            float f6 = entity.getAIMoveSpeed() * f5;
         } else {
            float jumpMovementFactor = entity.jumpMovementFactor;
         }

         float f7 = f * f + f2 * f2;
         f4 = 0.91F;
         if (onGround) {
            f4 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(xCoord), MathHelper.floor_double(yCoord) - 1, MathHelper.floor_double(zCoord)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d7 = d4;
         double d8 = d5;
         double d9 = d6;
         double d10 = d4;
         double d11 = d6;
         if (Math.abs(d4) > 100.0 || Math.abs(d5) > 100.0 || Math.abs(d6) > 100.0) {
            list.add(new Vec3(xCoord, yCoord, zCoord));
            break;
         }

         boolean sneaking = entityMotionSnapshot.sneaking;
         if (entityMotionSnapshot.onGround && sneaking) {
            double d12;
            for (d12 = 0.05; d7 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d7, -1.0, 0.0)).isEmpty(); d10 = d7) {
               if (d7 < d12 && d7 >= -d12) {
                  d7 = 0.0;
               } else if (d7 > 0.0) {
                  d7 -= d12;
               } else {
                  d7 += d12;
               }
            }

            for (; d9 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(0.0, -1.0, d9)).isEmpty(); d11 = d9) {
               if (d9 < d12 && d9 >= -d12) {
                  d9 = 0.0;
               } else if (d9 > 0.0) {
                  d9 -= d12;
               } else {
                  d9 += d12;
               }
            }

            for (; d7 != 0.0 && d9 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d7, -1.0, d9)).isEmpty(); d11 = d9) {
               if (d7 < d12 && d7 >= -d12) {
                  d7 = 0.0;
               } else if (d7 > 0.0) {
                  d7 -= d12;
               } else {
                  d7 += d12;
               }

               d10 = d7;
               if (d9 < d12 && d9 >= -d12) {
                  d9 = 0.0;
               } else if (d9 > 0.0) {
                  d9 -= d12;
               } else {
                  d9 += d12;
               }
            }
         }

         List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d7, d5, d9));
         AxisAlignedBB box3 = box2;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list2)) {
            d8 = box4.calculateYOffset(box2, d8);
         }

         box2 = box2.offset(0.0, d8, 0.0);
         boolean flag4 = onGround || d5 != d8 && d5 < 0.0;

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list2)) {
            d7 = box5.calculateXOffset(box2, d7);
         }

         box2 = box2.offset(d7, 0.0, 0.0);

         for (AxisAlignedBB box6 : (Iterable<AxisAlignedBB>)(list2)) {
            d9 = box6.calculateZOffset(box2, d9);
         }

         box2 = box2.offset(0.0, 0.0, d9);
         if (entity.stepHeight > 0.0F && flag4 && (d10 != d7 || d11 != d9)) {
            double d13 = d7;
            double d14 = d8;
            double d15 = d9;
            AxisAlignedBB box7 = box2;
            d8 = (double)entity.stepHeight;
            List list3 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box3.addCoord(d10, d8, d11));
            AxisAlignedBB box8 = box3.addCoord(d10, 0.0, d11);
            double d16 = d8;

            for (AxisAlignedBB box9 : (Iterable<AxisAlignedBB>)(list3)) {
               d16 = box9.calculateYOffset(box8, d16);
            }

            AxisAlignedBB box10 = box3.offset(0.0, d16, 0.0);
            double d17 = d10;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list3)) {
               d17 = box11.calculateXOffset(box10, d17);
            }

            box10 = box10.offset(d17, 0.0, 0.0);
            double d18 = d11;

            for (AxisAlignedBB box12 : (Iterable<AxisAlignedBB>)(list3)) {
               d18 = box12.calculateZOffset(box10, d18);
            }

            box10 = box10.offset(0.0, 0.0, d18);
            AxisAlignedBB box13 = box3;
            double d19 = d8;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list3)) {
               d19 = box14.calculateYOffset(box13, d19);
            }

            box13 = box13.offset(0.0, d19, 0.0);
            double d20 = d10;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list3)) {
               d20 = box15.calculateXOffset(box13, d20);
            }

            box13 = box13.offset(d20, 0.0, 0.0);
            double d21 = d11;

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list3)) {
               d21 = box16.calculateZOffset(box13, d21);
            }

            box13 = box13.offset(0.0, 0.0, d21);
            if ((d17 * d17 + d18 * d18) > (d20 * d20 + d21 * d21)) {
               d7 = d17;
               d9 = d18;
               d8 = -d16;
               box2 = box10;
            } else {
               d7 = d20;
               d9 = d21;
               d8 = -d19;
               box2 = box13;
            }

            for (AxisAlignedBB box17 : (Iterable<AxisAlignedBB>)(list3)) {
               d8 = box17.calculateYOffset(box2, d8);
            }

            box2 = box2.offset(0.0, d8, 0.0);
            if (d13 * d13 + d15 * d15 >= d7 * d7 + d9 * d9) {
               d7 = d13;
               d8 = d14;
               d9 = d15;
               box2 = box7;
            }
         }

         xCoord = (box2.minX + box2.maxX) / 2.0;
         yCoord = box2.minY;
         zCoord = (box2.minZ + box2.maxZ) / 2.0;
         isCollidedHorizontally = d10 != d7 || d11 != d9;
         isCollidedVertically = d5 != d8;
         onGround = isCollidedVertically && d5 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag5 = false;
         } else {
         }

         int n4 = MathHelper.floor_double(xCoord);
         int n5 = MathHelper.floor_double(yCoord - 0.20000000298023224);
         int n6 = MathHelper.floor_double(zCoord);
         BlockPos pos = new BlockPos(n4, n5, n6);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d10 != d7) {
            d4 = 0.0;
         }

         if (d11 != d9) {
            d6 = 0.0;
         }

         if (d5 != d8) {
            if (block instanceof BlockSlime) {
               if (entity.isSneaking()) {
                  d5 = 0.0;
               } else if (d5 < 0.0) {
                  d5 = -d5;
               }
            } else {
               d5 = 0.0;
            }
         }

         if (entity.entityWorld.isRemote
            && (
               !entity.entityWorld.isBlockLoaded(new BlockPos((int)xCoord, 0, (int)zCoord))
                  || !entity.entityWorld.getChunkFromBlockCoords(new BlockPos((int)xCoord, 0, (int)zCoord)).isLoaded()
            )) {
            if (yCoord > 0.0) {
               d5 = -0.1;
            } else {
               d5 = 0.0;
            }
         } else {
            d5 -= 0.08;
         }

         d5 *= 0.9800000190734863;
         d4 *= (double)f4;
         d6 *= (double)f4;
         list.add(new Vec3(xCoord, yCoord, zCoord));
      }

      return list;
   }

   public static boolean isOnGroundAtBox(EntityPlayerSP player, AxisAlignedBB box) {

      for (AxisAlignedBB box2 : (Iterable<AxisAlignedBB>)(mc.theWorld.getCollidingBoundingBoxesCapped(player, box))) {
         if (box2.calculateYOffset(box, -0.078) != -0.078) {
            return true;
         }
      }

      return false;
   }

   public static ArrayList predictTrajectoryFromSnapshot(EntityLivingBase entity, EntityMotionSnapshot entityMotionSnapshot, int n, double d, double d2, double d3) {
      return predictTrajectoryFromSnapshot(entity, entityMotionSnapshot, n, d, d2, d3, false);
   }

   private static ArrayList simulateTrajectoryFromServerPos(EntityLivingBase entity, int n, int n2, int n3) {
      ArrayList list = new ArrayList();
      double d = (double)entity.serverPosX / 32.0;
      double d2 = (double)entity.serverPosY / 32.0;
      double d3 = (double)entity.serverPosZ / 32.0;
      double d4 = (entity.realPosX - entity.predictedPosX) / 2.0;
      double d5 = (entity.realPosY - entity.predictedPosY) / 2.0;
      double d6 = (entity.realPosZ - entity.predictedPosZ) / 2.0;
      boolean onGround = entity.onGround;
      float f = (float)n2;
      float f2 = (float)n;
      float f3 = entity.width / 2.0F;
      AxisAlignedBB box = new AxisAlignedBB(
         d - (double)f3, d2, d3 - (double)f3, d + (double)f3, d2 + (double)entity.height, d3 + (double)f3
      );
      AxisAlignedBB box2 = new AxisAlignedBB(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
      boolean isCollidedHorizontally = entity.isCollidedHorizontally;
      boolean isCollidedVertically = entity.isCollidedVertically;
      float f4 = 0.91F;
      if (onGround) {
         f4 = entity.entityWorld
               .getBlockState(new BlockPos(MathHelper.floor_double(d), MathHelper.floor_double(d2) - 1, MathHelper.floor_double(d3)))
               .getBlock()
               .slipperiness
            * 0.91F;
      }

      if (entity.entityWorld.isRemote
         && (
            !entity.entityWorld.isBlockLoaded(new BlockPos((int)d, 0, (int)d3))
               || !entity.entityWorld.getChunkFromBlockCoords(new BlockPos((int)d, 0, (int)d3)).isLoaded()
         )) {
         if (d2 > 0.0) {
            d5 = -0.1;
         } else {
            d5 = 0.0;
         }
      } else {
         d5 -= 0.08;
      }

      d5 *= 0.9800000190734863;
      d4 *= (double)f4;
      d6 *= (double)f4;

      for (int i = 0; i < n3; i++) {
         f = (float)n2;
         f2 = (float)n;
         if (entity instanceof EntityPlayer) {
            if (((EntityPlayer)entity).isUsingItem() && !entity.isRiding()) {
               f *= 0.2F;
               f2 *= 0.2F;
            }
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(d4) < motionZeroThreshold) {
            d4 = 0.0;
         }

         if (Math.abs(d5) < motionZeroThreshold) {
            d5 = 0.0;
         }

         if (Math.abs(d6) < motionZeroThreshold) {
            d6 = 0.0;
         }

         f *= 0.98F;
         f2 *= 0.98F;
         float f5 = 0.91F;
         if (onGround) {
            f5 = entity.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(d), MathHelper.floor_double(d2) - 1, MathHelper.floor_double(d3)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f6 = 0.16277136F / (f5 * f5 * f5);
         float f7;
         if (onGround) {
            f7 = (float)(mc.thePlayer.getBaseMoveSpeed() * (double)f6);
         } else {
            f7 = entity.jumpMovementFactor;
         }

         float f8 = f * f + f2 * f2;
         if (f8 >= 1.0E-4F) {
            f8 = MathHelper.sqrt_float(f8);
            if (f8 < 1.0F) {
               f8 = 1.0F;
            }

            f8 = f7 / f8;
            f *= f8;
            f2 *= f8;
            float f9 = MathHelper.sin(entity.rotationYawHead * 3.1415927F / 180.0F);
            float f10 = MathHelper.cos(entity.rotationYawHead * 3.1415927F / 180.0F);
            d4 += (double)(f * f10 - f2 * f9);
            d6 += (double)(f2 * f10 + f * f9);
         }

         f5 = 0.91F;
         if (onGround) {
            f5 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(d), MathHelper.floor_double(d2) - 1, MathHelper.floor_double(d3)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         if (d4 > 10.0 || d5 > 10.0 || d6 > 10.0) {
            return list;
         }

         double d7 = d4;
         double d8 = d5;
         double d9 = d6;
         double d10 = d4;
         double d11 = d6;
         boolean isSneaking = entity.isSneaking();
         if (entity.onGround && isSneaking) {
            double d12;
            for (d12 = 0.05; d7 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d7, -1.0, 0.0)).isEmpty(); d10 = d7) {
               if (d7 < d12 && d7 >= -d12) {
                  d7 = 0.0;
               } else if (d7 > 0.0) {
                  d7 -= d12;
               } else {
                  d7 += d12;
               }
            }

            for (; d9 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(0.0, -1.0, d9)).isEmpty(); d11 = d9) {
               if (d9 < d12 && d9 >= -d12) {
                  d9 = 0.0;
               } else if (d9 > 0.0) {
                  d9 -= d12;
               } else {
                  d9 += d12;
               }
            }

            for (; d7 != 0.0 && d9 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d7, -1.0, d9)).isEmpty(); d11 = d9) {
               if (d7 < d12 && d7 >= -d12) {
                  d7 = 0.0;
               } else if (d7 > 0.0) {
                  d7 -= d12;
               } else {
                  d7 += d12;
               }

               d10 = d7;
               if (d9 < d12 && d9 >= -d12) {
                  d9 = 0.0;
               } else if (d9 > 0.0) {
                  d9 -= d12;
               } else {
                  d9 += d12;
               }
            }
         }

         List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d7, d5, d9));
         AxisAlignedBB box3 = box2;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list2)) {
            d8 = box4.calculateYOffset(box2, d8);
         }

         box2 = box2.offset(0.0, d8, 0.0);
         boolean flag2 = onGround || d5 != d8 && d5 < 0.0;

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list2)) {
            d7 = box5.calculateXOffset(box2, d7);
         }

         box2 = box2.offset(d7, 0.0, 0.0);

         for (AxisAlignedBB box6 : (Iterable<AxisAlignedBB>)(list2)) {
            d9 = box6.calculateZOffset(box2, d9);
         }

         box2 = box2.offset(0.0, 0.0, d9);
         if (entity.stepHeight > 0.0F && flag2 && (d10 != d7 || d11 != d9)) {
            double d13 = d7;
            double d14 = d8;
            double d15 = d9;
            AxisAlignedBB box7 = box2;
            d8 = (double)entity.stepHeight;
            List list3 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box3.addCoord(d10, d8, d11));
            AxisAlignedBB box8 = box3.addCoord(d10, 0.0, d11);
            double d16 = d8;

            for (AxisAlignedBB box9 : (Iterable<AxisAlignedBB>)(list3)) {
               d16 = box9.calculateYOffset(box8, d16);
            }

            AxisAlignedBB box10 = box3.offset(0.0, d16, 0.0);
            double d17 = d10;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list3)) {
               d17 = box11.calculateXOffset(box10, d17);
            }

            box10 = box10.offset(d17, 0.0, 0.0);
            double d18 = d11;

            for (AxisAlignedBB box12 : (Iterable<AxisAlignedBB>)(list3)) {
               d18 = box12.calculateZOffset(box10, d18);
            }

            box10 = box10.offset(0.0, 0.0, d18);
            AxisAlignedBB box13 = box3;
            double d19 = d8;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list3)) {
               d19 = box14.calculateYOffset(box13, d19);
            }

            box13 = box13.offset(0.0, d19, 0.0);
            double d20 = d10;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list3)) {
               d20 = box15.calculateXOffset(box13, d20);
            }

            box13 = box13.offset(d20, 0.0, 0.0);
            double d21 = d11;

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list3)) {
               d21 = box16.calculateZOffset(box13, d21);
            }

            box13 = box13.offset(0.0, 0.0, d21);
            if ((d17 * d17 + d18 * d18) > (d20 * d20 + d21 * d21)) {
               d7 = d17;
               d9 = d18;
               d8 = -d16;
               box2 = box10;
            } else {
               d7 = d20;
               d9 = d21;
               d8 = -d19;
               box2 = box13;
            }

            for (AxisAlignedBB box17 : (Iterable<AxisAlignedBB>)(list3)) {
               d8 = box17.calculateYOffset(box2, d8);
            }

            box2 = box2.offset(0.0, d8, 0.0);
            if (d13 * d13 + d15 * d15 >= d7 * d7 + d9 * d9) {
               d7 = d13;
               d8 = d14;
               d9 = d15;
               box2 = box7;
            }
         }

         d = (box2.minX + box2.maxX) / 2.0;
         d2 = box2.minY;
         d3 = (box2.minZ + box2.maxZ) / 2.0;
         isCollidedHorizontally = d10 != d7 || d11 != d9;
         isCollidedVertically = d5 != d8;
         onGround = isCollidedVertically && d5 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag3 = false;
         } else {
         }

         int n4 = MathHelper.floor_double(d);
         int n5 = MathHelper.floor_double(d2 - 0.20000000298023224);
         int n6 = MathHelper.floor_double(d3);
         BlockPos pos = new BlockPos(n4, n5, n6);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d10 != d7) {
            d4 = 0.0;
         }

         if (d11 != d9) {
            d6 = 0.0;
         }

         if (d5 != d8) {
            if (block instanceof BlockSlime) {
               if (entity.isSneaking()) {
                  d5 = 0.0;
               } else if (d5 < 0.0) {
                  d5 = -d5;
               }
            } else {
               d5 = 0.0;
            }
         }

         if (entity.entityWorld.isRemote
            && (
               !entity.entityWorld.isBlockLoaded(new BlockPos((int)d, 0, (int)d3))
                  || !entity.entityWorld.getChunkFromBlockCoords(new BlockPos((int)d, 0, (int)d3)).isLoaded()
            )) {
            if (d2 > 0.0) {
               d5 = -0.1;
            } else {
               d5 = 0.0;
            }
         } else {
            d5 -= 0.08;
         }

         d5 *= 0.9800000190734863;
         d4 *= (double)f5;
         d6 *= (double)f5;
         list.add(new Vec3(d, d2, d3));
      }

      return list;
   }

   private static Vec3 simulateFromPrevServerPos(EntityLivingBase entity, int n, int n2, int n3) {
      double predictedPosX = entity.predictedPosX;
      double predictedPosY = entity.predictedPosY;
      double predictedPosZ = entity.predictedPosZ;
      double d = MathHelper.clamp_double((entity.predictedPosX - entity.trackedPosX) / 2.0, -10.0, 10.0);
      double d2 = MathHelper.clamp_double((entity.predictedPosY - entity.trackedPosY) / 2.0, -10.0, 10.0);
      double d3 = MathHelper.clamp_double((entity.predictedPosZ - entity.trackedPosZ) / 2.0, -10.0, 10.0);
      boolean onGround = entity.onGround;
      float f = (float)n2;
      float f2 = (float)n;
      float f3 = entity.width / 2.0F;
      AxisAlignedBB box = new AxisAlignedBB(
         predictedPosX - (double)f3, predictedPosY, predictedPosZ - (double)f3, predictedPosX + (double)f3, predictedPosY + (double)entity.height, predictedPosZ + (double)f3
      );
      AxisAlignedBB box2 = new AxisAlignedBB(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
      boolean isCollidedHorizontally = entity.isCollidedHorizontally;
      boolean isCollidedVertically = entity.isCollidedVertically;
      entity.isSprinting();

      for (int i = 0; i < n3; i++) {
         f = (float)n2;
         f2 = (float)n;
         if (entity instanceof EntityPlayer) {
            if (((EntityPlayer)entity).isUsingItem() && !entity.isRiding()) {
               f *= 0.2F;
               f2 *= 0.2F;
            }
         }

         double motionZeroThreshold = ViaAugustus.getMotionZeroThreshold();
         if (Math.abs(d) < motionZeroThreshold) {
            d = 0.0;
         }

         if (Math.abs(d2) < motionZeroThreshold) {
            d2 = 0.0;
         }

         if (Math.abs(d3) < motionZeroThreshold) {
            d3 = 0.0;
         }

         f *= 0.98F;
         f2 *= 0.98F;
         float f4 = 0.91F;
         if (onGround) {
            f4 = entity.entityWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(predictedPosX), MathHelper.floor_double(predictedPosY) - 1, MathHelper.floor_double(predictedPosZ)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         float f5 = 0.16277136F / (f4 * f4 * f4);
         float f6;
         if (onGround) {
            f6 = entity.getAIMoveSpeed() * f5;
         } else {
            f6 = entity.jumpMovementFactor;
         }

         float f7 = f * f + f2 * f2;
         if (f7 >= 1.0E-4F) {
            f7 = MathHelper.sqrt_float(f7);
            if (f7 < 1.0F) {
               f7 = 1.0F;
            }

            f7 = f6 / f7;
            f *= f7;
            f2 *= f7;
            float f8 = MathHelper.sin(entity.prevRotationYawHead * 3.1415927F / 180.0F);
            float f9 = MathHelper.cos(entity.prevRotationYawHead * 3.1415927F / 180.0F);
            d += (double)(f * f9 - f2 * f8);
            d3 += (double)(f2 * f9 + f * f8);
         }

         f4 = 0.91F;
         if (onGround) {
            f4 = mc.theWorld
                  .getBlockState(new BlockPos(MathHelper.floor_double(predictedPosX), MathHelper.floor_double(predictedPosY) - 1, MathHelper.floor_double(predictedPosZ)))
                  .getBlock()
                  .slipperiness
               * 0.91F;
         }

         double d4 = d;
         double d5 = d2;
         double d6 = d3;
         double d7 = d;
         double d8 = d3;
         if (d > 100.0 || d2 > 100.0 || d3 > 100.0) {
            return new Vec3(predictedPosX, predictedPosY, predictedPosZ);
         }

         boolean isSneaking = entity.isSneaking();
         if (entity.onGround && isSneaking) {
            double d9;
            for (d9 = 0.05; d4 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d4, -1.0, 0.0)).isEmpty(); d7 = d4) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }
            }

            for (; d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(0.0, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }

            for (; d4 != 0.0 && d6 != 0.0 && mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.offset(d4, -1.0, d6)).isEmpty(); d8 = d6) {
               if (d4 < d9 && d4 >= -d9) {
                  d4 = 0.0;
               } else if (d4 > 0.0) {
                  d4 -= d9;
               } else {
                  d4 += d9;
               }

               d7 = d4;
               if (d6 < d9 && d6 >= -d9) {
                  d6 = 0.0;
               } else if (d6 > 0.0) {
                  d6 -= d9;
               } else {
                  d6 += d9;
               }
            }
         }

         List list = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box2.addCoord(d4, d2, d6));
         AxisAlignedBB box3 = box2;

         for (AxisAlignedBB box4 : (Iterable<AxisAlignedBB>)(list)) {
            d5 = box4.calculateYOffset(box2, d5);
         }

         box2 = box2.offset(0.0, d5, 0.0);
         boolean flag2 = onGround || d2 != d5 && d2 < 0.0;

         for (AxisAlignedBB box5 : (Iterable<AxisAlignedBB>)(list)) {
            d4 = box5.calculateXOffset(box2, d4);
         }

         box2 = box2.offset(d4, 0.0, 0.0);

         for (AxisAlignedBB box6 : (Iterable<AxisAlignedBB>)(list)) {
            d6 = box6.calculateZOffset(box2, d6);
         }

         box2 = box2.offset(0.0, 0.0, d6);
         if (entity.stepHeight > 0.0F && flag2 && (d7 != d4 || d8 != d6)) {
            double d10 = d4;
            double d11 = d5;
            double d12 = d6;
            AxisAlignedBB box7 = box2;
            d5 = (double)entity.stepHeight;
            List list2 = mc.theWorld.getCollidingBoundingBoxesCapped(entity, box3.addCoord(d7, d5, d8));
            AxisAlignedBB box8 = box3.addCoord(d7, 0.0, d8);
            double d13 = d5;

            for (AxisAlignedBB box9 : (Iterable<AxisAlignedBB>)(list2)) {
               d13 = box9.calculateYOffset(box8, d13);
            }

            AxisAlignedBB box10 = box3.offset(0.0, d13, 0.0);
            double d14 = d7;

            for (AxisAlignedBB box11 : (Iterable<AxisAlignedBB>)(list2)) {
               d14 = box11.calculateXOffset(box10, d14);
            }

            box10 = box10.offset(d14, 0.0, 0.0);
            double d15 = d8;

            for (AxisAlignedBB box12 : (Iterable<AxisAlignedBB>)(list2)) {
               d15 = box12.calculateZOffset(box10, d15);
            }

            box10 = box10.offset(0.0, 0.0, d15);
            AxisAlignedBB box13 = box3;
            double d16 = d5;

            for (AxisAlignedBB box14 : (Iterable<AxisAlignedBB>)(list2)) {
               d16 = box14.calculateYOffset(box13, d16);
            }

            box13 = box13.offset(0.0, d16, 0.0);
            double d17 = d7;

            for (AxisAlignedBB box15 : (Iterable<AxisAlignedBB>)(list2)) {
               d17 = box15.calculateXOffset(box13, d17);
            }

            box13 = box13.offset(d17, 0.0, 0.0);
            double d18 = d8;

            for (AxisAlignedBB box16 : (Iterable<AxisAlignedBB>)(list2)) {
               d18 = box16.calculateZOffset(box13, d18);
            }

            box13 = box13.offset(0.0, 0.0, d18);
            if ((d14 * d14 + d15 * d15) > (d17 * d17 + d18 * d18)) {
               d4 = d14;
               d6 = d15;
               d5 = -d13;
               box2 = box10;
            } else {
               d4 = d17;
               d6 = d18;
               d5 = -d16;
               box2 = box13;
            }

            for (AxisAlignedBB box17 : (Iterable<AxisAlignedBB>)(list2)) {
               d5 = box17.calculateYOffset(box2, d5);
            }

            box2 = box2.offset(0.0, d5, 0.0);
            if (d10 * d10 + d12 * d12 >= d4 * d4 + d6 * d6) {
               d4 = d10;
               d5 = d11;
               d6 = d12;
               box2 = box7;
            }
         }

         predictedPosX = (box2.minX + box2.maxX) / 2.0;
         predictedPosY = box2.minY;
         predictedPosZ = (box2.minZ + box2.maxZ) / 2.0;
         isCollidedHorizontally = d7 != d4 || d8 != d6;
         isCollidedVertically = d2 != d5;
         onGround = isCollidedVertically && d2 < 0.0;
         if (!isCollidedHorizontally && !isCollidedVertically) {
            boolean flag3 = false;
         } else {
         }

         int n4 = MathHelper.floor_double(predictedPosX);
         int n5 = MathHelper.floor_double(predictedPosY - 0.20000000298023224);
         int n6 = MathHelper.floor_double(predictedPosZ);
         BlockPos pos = new BlockPos(n4, n5, n6);
         Block block = mc.theWorld.getBlockState(pos).getBlock();
         if (block.getMaterial() == Material.air) {
            Block block2 = mc.theWorld.getBlockState(pos.down()).getBlock();
            if (block2 instanceof BlockFence || block2 instanceof BlockWall || block2 instanceof BlockFenceGate) {
               block = block2;
               pos = pos.down();
            }
         }

         if (d7 != d4) {
            d = 0.0;
         }

         if (d8 != d6) {
            d3 = 0.0;
         }

         if (d2 != d5) {
            if (block instanceof BlockSlime) {
               if (entity.isSneaking()) {
                  d2 = 0.0;
               } else if (d2 < 0.0) {
                  d2 = -d2;
               }
            } else {
               d2 = 0.0;
            }
         }

         if (entity.entityWorld.isRemote
            && (
               !entity.entityWorld.isBlockLoaded(new BlockPos((int)predictedPosX, 0, (int)predictedPosZ))
                  || !entity.entityWorld.getChunkFromBlockCoords(new BlockPos((int)predictedPosX, 0, (int)predictedPosZ)).isLoaded()
            )) {
            if (predictedPosY > 0.0) {
               d2 = -0.1;
            } else {
               d2 = 0.0;
            }
         } else {
            d2 -= 0.08;
         }

         d2 *= 0.9800000190734863;
         d *= (double)f4;
         d3 *= (double)f4;
      }

      return new Vec3(predictedPosX, predictedPosY, predictedPosZ);
   }




}
