package net.minecraft.block;

import client.render.PathRenderer;
import client.render.RecordNode;
import client.util.AlertNotificationSound;
import client.util.DecayableManager;
import client.util.IBlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class WorldHelper {
   public static boolean matchesWorld(
      World world,
      int count,
      int count2,
      int count3,
      IBlockState blockState,
      AlertNotificationSound alertNotificationSound,
      RecordNode recordNode,
      RecordNode recordNode2,
      int count4,
      int count5,
      int count6,
      boolean flag,
      boolean flag2,
      boolean flag3
   ) {
      double offset = 1.0;
      boolean flag4 = matchesWorld(world, count, count2, count3, blockState, count4, count5, count6, offset, flag, flag3);
      if (!flag4) {
         Block block = blockState.getBlock();
         if (block instanceof BlockRedSandstoneStairs) {
            if (count4 == Integer.MAX_VALUE) {
               return true;
            }

            IBlockState iBlockState = world.getIBlockState(count4, count5, count6);
            if (PathRenderer.matchesRecordNode(recordNode, recordNode2, count4, count5, count6, flag, flag2, iBlockState)) {
               Vec3i vec3i = ((EnumFacing)blockState.getComparable(BlockRedSandstoneStairs.autoWalkPath)).getVec3i();
               int count7 = count - count4;
               int count8 = count2 - count5;
               int count9 = count3 - count6;
               if ((vec3i.getY() == count8 ? (vec3i.getZ() == count9 ? vec3i.getX() - count7 : vec3i.getZ() - count9) : vec3i.getY() - count8) == 0) {
                  flag4 = true;
               }
            }
         }

         if (block instanceof BlockSnowLayer) {
            flag4 = (Integer)blockState.getComparable(BlockSnowLayer.auxiliaryImmutableSetEntry) < 5;
         }
      }

      if (flag4) {
         int count10 = count2 + 1;
         IBlockState blockState2 = alertNotificationSound.resolveIBlockState(count, count2 + 1, count3);
         int count11 = count5 + 1;
         double offset2 = 0.8;
         int count12 = count11;
         IBlockState blockState3 = blockState2;
         int count13 = count10;
         if (matchesWorld(world, count, count13, count3, blockState3, count4, count12, count6, offset2, flag, flag3)) {
            return true;
         }
      }

      return false;
   }

   public static boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, boolean flag, boolean flag2, IBlockState blockState) {
      Block block = blockState.getBlock();
      if (block == Blocks.air) {
         return false;
      } else if (block.isActive2()) {
         return true;
      } else if (block == Blocks.block81) {
         return true;
      } else if (block == Blocks.block7) {
         return true;
      } else if (block == Blocks.block60 || block == Blocks.blockTrappedChest || block == Blocks.block128) {
         return true;
      } else if (block instanceof BlockIronTrapdoor) {
         return !(Boolean)blockState.getComparable(BlockIronTrapdoor.internalImmutableSetEntry);
      } else if (!matchesBlockByBlock(block)) {
         if (block.getMaterial() == Material.material26
            || block == Blocks.block135
            || block.getMaterial() == Material.material12
            || block == Blocks.blockCactus) {
            return true;
         } else if (block instanceof DecayableManager) {
            return true;
         } else if (block instanceof ExtraBlockAdapter) {
            return true;
         } else {
            return block instanceof BlockIce ? true : block instanceof BlockRedSandstoneStairs;
         }
      } else {
         Block block2 = iBlockAccess.getIBlockState(count, count2 + 1, count3).getBlock();
         if (block2 == Blocks.block33 || block2 == Blocks.block141) {
            return true;
         } else if (flag2) {
            return matchesBlockByBlock(block2);
         } else {
            return !matchesIBlockState(count, count2, count3, blockState, iBlockAccess) && block != Blocks.blockFlowingLava
               ? matchesBlockByBlock(block2) ^ flag
               : matchesBlockByBlock(block2) && !flag;
         }
      }
   }

   public static boolean matchesWorld(
      World world, int count, int count2, int count3, IBlockState blockState, int count4, int count5, int count6, boolean flag, boolean flag2, boolean flag3, boolean flag4
   ) {
      double offset = 1.0;
      boolean flag5 = matchesWorld(world, count, count2, count3, blockState, count4, count5, count6, offset, flag2, flag3, flag4);
      if (!flag5) {
         Block block = blockState.getBlock();
         if (block instanceof BlockRedSandstoneStairs) {
            if (count4 == Integer.MAX_VALUE) {
               return true;
            }

            if (flag) {
               Vec3i vec3i = ((EnumFacing)blockState.getComparable(BlockRedSandstoneStairs.autoWalkPath)).getVec3i();
               int count7 = count - count4;
               int count8 = count2 - count5;
               int count9 = count3 - count6;
               if ((vec3i.getY() == count8 ? (vec3i.getZ() == count9 ? vec3i.getX() - count7 : vec3i.getZ() - count9) : vec3i.getY() - count8) == 0) {
                  flag5 = true;
               }
            }
         } else if (block instanceof BlockSnowLayer) {
            flag5 = (Integer)blockState.getComparable(BlockSnowLayer.auxiliaryImmutableSetEntry) < 5;
         }
      }

      if (flag5) {
         int count10 = count2 + 1;
         IBlockState iBlockState = world.getIBlockState(count, count2 + 1, count3);
         int count11 = count5 + 1;
         double offset2 = 0.8;
         int count12 = count11;
         IBlockState blockState2 = iBlockState;
         int count13 = count10;
         if (matchesWorld(world, count, count13, count3, blockState2, count4, count12, count6, offset2, flag2, flag3, flag4)) {
            return true;
         }
      }

      return false;
   }

   public static boolean matchesWorld(
      int count,
      World world,
      int count2,
      int count3,
      int count4,
      int count5,
      int count6,
      short shortValue,
      int count7,
      boolean flag,
      boolean flag2,
      boolean flag3,
      short shortValue2,
      boolean flag4
   ) {
      IBlockState iBlockState = world.getIBlockState(count2, count3, count4);
      return matchesWorld(world, count2, count3, count4, iBlockState, count5, count6, count7, flag, flag2, flag3, flag4);
   }

   public static boolean matchesIBlockState(IBlockState blockState) {
      return blockState.getBlock() instanceof BlockLiquid && (Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) != 0;
   }

   public static boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, boolean flag, boolean flag2) {
      byte byteValue = 0;
      short shortValue = 26776;
      int count4 = 1062060912;
      IBlockState iBlockState = iBlockAccess.getIBlockState(count, count2, count3);
      return matchesIBlockAccess(iBlockAccess, count, count2, count3, flag, flag2, iBlockState);
   }

   public static boolean matchesBlockByBlock(Block block) {
      return block == Blocks.blockFlowingLava || block == Blocks.blockLava2;
   }

   public static boolean matchesBlock(Block block) {
      return block == Blocks.blockFlowingLava2 || block == Blocks.blockLava;
   }

   public static boolean matchesIBlockState(int count, int count2, int count3, IBlockState blockState, IBlockAccess iBlockAccess) {
      if (!(blockState.getBlock() instanceof BlockLiquid)) {
         return false;
      } else {
         return (Integer)blockState.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) != 0
            ? true
            : matchesIBlockState(iBlockAccess.getIBlockState(count + 1, count2, count3))
               || matchesIBlockState(iBlockAccess.getIBlockState(count - 1, count2, count3))
               || matchesIBlockState(iBlockAccess.getIBlockState(count, count2, count3 + 1))
               || matchesIBlockState(iBlockAccess.getIBlockState(count, count2, count3 - 1));
      }
   }

   public static boolean matchesWorld(
      World world, int count, int count2, int count3, IBlockState blockState, int count4, int count5, int count6, double offset, boolean flag, boolean flag2
   ) {
      return matchesWorld(world, count, count2, count3, blockState, count4, count5, count6, offset, flag, flag2, false);
   }

   public static boolean matchesWorld(
      World world, int count, int count2, int count3, IBlockState blockState, int count4, int count5, int count6, double offset, boolean flag, boolean flag2, boolean flag3
   ) {
      Block block = blockState.getBlock();
      if (block == Blocks.air) {
         return true;
      } else if (offset == 0.8 && block instanceof BlockCarpet) {
         return false;
      } else if (!(block instanceof BlockIronTrapdoor) && !(block instanceof BlockDarkOakDoor)) {
         if (block == Blocks.web) {
            return !flag3;
         } else if (block == Blocks.blockFire
            || block == Blocks.block6
            || block == Blocks.block90
            || block instanceof BlockEndPortal
            || block instanceof BlockSkull) {
            return false;
         } else if (flag3 && block instanceof BlockLiquid) {
            return false;
         } else if (block == Blocks.block81) {
            Vec3i vec3i = ((EnumFacing)blockState.getComparable(BlockLadder.autoWalkPath)).getVec3i();
            return Math.signum((float)vec3i.getX()) != Math.signum((float)(count - count4))
               || Math.signum((float)vec3i.getZ()) != Math.signum((float)(count3 - count6));
         } else {
            return block.matchesIBlockAccess(world, count, count2, count3) || block.getMaterial() == Material.material29;
         }
      } else if (flag2) {
         return false;
      } else if (count4 == Integer.MAX_VALUE) {
         return true;
      } else {
         AxisAlignedBB boundingBox = blockState.getBlock().processAction(world, blockState, count, count2, count3);
         AxisAlignedBB boundingBox2 = new AxisAlignedBB(
            (double)count4 + 0.5 - 0.3,
            (double)count5 + offset == 1.0 ? 0.0626F : 0.0,
            (double)count6 + 0.5 - 0.3,
            (double)count4 + 0.5 + 0.3,
            (double)count5 + offset,
            (double)count6 + 0.5 + 0.3
         );
         return boundingBox.getCoordinate2(boundingBox2, (double)((count - count4) * 2)) == (double)((count - count4) * 2)
            && boundingBox.getCoordinate(boundingBox2, (double)((count3 - count6) * 2)) == (double)((count3 - count6) * 2);
      }
   }

   public static boolean matchesWorld(World world, BlockPos blockPos) {
      return world.getBlockState(blockPos) instanceof BlockLiquid;
   }
}
