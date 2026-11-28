package net.augustus.utils.pathfinding;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCarpet;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockEndPortal;
import net.minecraft.block.BlockIce;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSkull;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class PathBlockValidator {

   public static boolean canStandAt(
      World world,
      int n,
      int n2,
      int n3,
      IBlockState state,
      Chunk chunk,
      PathfinderResultRef pathfinderResultRef,
      PathfinderResultRef pathfinderResultRef2,
      int n4,
      int n5,
      int n6,
      boolean flag,
      boolean flag2,
      boolean flag3
   ) {
      boolean isBlockPassable = isBlockPassable(world, n, n2, n3, state, n4, n5, n6, 1.0, flag, flag3);
      if (!isBlockPassable) {
         Block block = state.getBlock();
         if (block instanceof BlockStairs) {
            if (n4 == 2147483647) {
               return true;
            }

            if (AStarPathFinder.isStandingGroundCached(pathfinderResultRef, pathfinderResultRef2, n4, n5, n6, flag, flag2, world.getBlockState2(n4, n5, n6))) {
               Vec3i directionVec = ((EnumFacing)state.getValue(BlockStairs.FACING)).getDirectionVec();
               int n7 = n - n4;
               int n8 = n2 - n5;
               int n9 = n3 - n6;
               if ((directionVec.getY() == n8 ? (directionVec.getZ() == n9 ? directionVec.getX() - n7 : directionVec.getZ() - n9) : directionVec.getY() - n8) == 0) {
                  isBlockPassable = true;
               }
            }
         }

         if (block instanceof BlockSnow) {
            isBlockPassable = (Integer)state.getValue(BlockSnow.LAYERS) < 5;
         }
      }

      if (isBlockPassable) {
         if (isBlockPassable(world, n, n2 + 1, n3, chunk.getBlockState(n, n2 + 1, n3), n4, n5 + 1, n6, 0.8, flag, flag3)) {
            return true;
         }
      }

      return false;
   }

   public static boolean isFlowingLiquid(IBlockState state) {
      return state.getBlock() instanceof BlockLiquid && (Integer)state.getValue(BlockLiquid.LEVEL) != 0;
   }

   public static boolean canPassThroughTwoTall(
      World world,
      int n,
      int n2,
      int n3,
      IBlockState state,
      int n4,
      int n5,
      int n6,
      boolean flag,
      boolean flag2,
      boolean flag3,
      boolean flag4
   ) {
      boolean isBlockPassable = isBlockPassable(world, n, n2, n3, state, n4, n5, n6, 1.0, flag2, flag3, flag4);
      if (!isBlockPassable) {
         Block block = state.getBlock();
         if (block instanceof BlockStairs) {
            if (n4 == 2147483647) {
               return true;
            }

            if (flag) {
               Vec3i directionVec = ((EnumFacing)state.getValue(BlockStairs.FACING)).getDirectionVec();
               int n7 = n - n4;
               int n8 = n2 - n5;
               int n9 = n3 - n6;
               if ((directionVec.getY() == n8 ? (directionVec.getZ() == n9 ? directionVec.getX() - n7 : directionVec.getZ() - n9) : directionVec.getY() - n8) == 0) {
                  isBlockPassable = true;
               }
            }
         } else if (block instanceof BlockSnow) {
            isBlockPassable = (Integer)state.getValue(BlockSnow.LAYERS) < 5;
         }
      }

      if (isBlockPassable) {
         if (isBlockPassable(world, n, n2 + 1, n3, world.getBlockState2(n, n2 + 1, n3), n4, n5 + 1, n6, 0.8, flag2, flag3, flag4)) {
            return true;
         }
      }

      return false;
   }

   public static boolean isBlockPassable(
      World world,
      int n,
      int n2,
      int n3,
      IBlockState state,
      int n4,
      int n5,
      int n6,
      double d,
      boolean flag,
      boolean flag2,
      boolean flag3
   ) {
      Block block = state.getBlock();
      if (block == Blocks.air) {
         return true;
      } else if (d == 0.8 && block instanceof BlockCarpet) {
         return false;
      } else if (!(block instanceof BlockTrapDoor) && !(block instanceof BlockDoor)) {
         if (block == Blocks.web) {
            return !flag3;
         } else if (block == Blocks.fire || block == Blocks.tripwire || block == Blocks.cocoa || block instanceof BlockEndPortal || block instanceof BlockSkull
            )
          {
            return false;
         } else if (flag3 && block instanceof BlockLiquid) {
            return false;
         } else if (block == Blocks.ladder) {
            Vec3i directionVec = ((EnumFacing)state.getValue(BlockLadder.FACING)).getDirectionVec();
            return Math.signum((float)directionVec.getX()) != Math.signum((float)(n - n4))
               || Math.signum((float)directionVec.getZ()) != Math.signum((float)(n3 - n6));
         } else {
            return block.isPassable(world, n, n2, n3) || block.getMaterial() == Material.lava;
         }
      } else if (flag2) {
         return false;
      } else if (n4 == 2147483647) {
         return true;
      } else {
         AxisAlignedBB collisionBoundingBox = state.getBlock().getCollisionBoundingBox(world, state, n, n2, n3);
         AxisAlignedBB box = new AxisAlignedBB(
            (double)n4 + 0.5 - 0.3,
            (double)n5 + d == 1.0 ? 0.06260000169277191 : 0.0,
            (double)n6 + 0.5 - 0.3,
            (double)n4 + 0.5 + 0.3,
            (double)n5 + d,
            (double)n6 + 0.5 + 0.3
         );
         return collisionBoundingBox.calculateXOffset(box, (double)((n - n4) * 2)) == (double)((n - n4) * 2)
            && collisionBoundingBox.calculateZOffset(box, (double)((n3 - n6) * 2)) == (double)((n3 - n6) * 2);
      }
   }

   public static boolean isStandableBlock(IBlockAccess world, int n, int n2, int n3, boolean flag, boolean flag2, IBlockState state) {
      Block block = state.getBlock();
      if (block == Blocks.air) {
         return false;
      } else if (block.isNormalCube()) {
         return true;
      } else if (block == Blocks.ladder) {
         return true;
      } else if (block == Blocks.farmland) {
         return true;
      } else if (block == Blocks.ender_chest || block == Blocks.chest || block == Blocks.trapped_chest) {
         return true;
      } else if (block instanceof BlockTrapDoor) {
         return !(Boolean)state.getValue(BlockTrapDoor.OPEN);
      } else if (!isWater(block)) {
         if (block.getMaterial() == Material.glass || block == Blocks.tnt || block.getMaterial() == Material.ice || block == Blocks.cactus) {
            return true;
         } else if (block instanceof BlockLeaves) {
            return true;
         } else if (block instanceof BlockSlab) {
            return true;
         } else {
            return block instanceof BlockIce ? true : block instanceof BlockStairs;
         }
      } else {
         Block block2 = world.getBlockState2(n, n2 + 1, n3).getBlock();
         if (block2 == Blocks.waterlily || block2 == Blocks.carpet) {
            return true;
         } else if (flag2) {
            return isWater(block2);
         } else {
            return !hasAdjacentFlowingLiquid(n, n2, n3, state, world) && block != Blocks.flowing_water ? isWater(block2) ^ flag : isWater(block2) && !flag;
         }
      }
   }

   public static boolean isBlockPassable(
      World world, int n, int n2, int n3, IBlockState state, int n4, int n5, int n6, double d, boolean flag, boolean flag2
   ) {
      return isBlockPassable(world, n, n2, n3, state, n4, n5, n6, d, flag, flag2, false);
   }

   public static boolean isLiquidAt(World world, BlockPos pos) {
      return world.getBlockState(pos) instanceof BlockLiquid;
   }

   public static boolean isLava(Block block) {
      return block == Blocks.flowing_lava || block == Blocks.lava;
   }

   public static boolean isStandableBlock(IBlockAccess world, int n, int n2, int n3, boolean flag, boolean flag2) {
      return isStandableBlock(world, n, n2, n3, flag, flag2, world.getBlockState2(n, n2, n3));
   }

   public static boolean isWater(Block block) {
      return block == Blocks.flowing_water || block == Blocks.water;
   }

   public static boolean hasAdjacentFlowingLiquid(int n, int n2, int n3, IBlockState state, IBlockAccess world) {
      if (!(state.getBlock() instanceof BlockLiquid)) {
         return false;
      } else {
         return (Integer)state.getValue(BlockLiquid.LEVEL) != 0
            ? true
            : isFlowingLiquid(world.getBlockState2(n + 1, n2, n3))
               || isFlowingLiquid(world.getBlockState2(n - 1, n2, n3))
               || isFlowingLiquid(world.getBlockState2(n, n2, n3 + 1))
               || isFlowingLiquid(world.getBlockState2(n, n2, n3 - 1));
      }
   }

   public static boolean canPassThroughCorner(
      World world, int n, int n2, int n3, int n4, int n5, int n6, boolean flag, boolean flag2, boolean flag3, boolean flag4
   ) {
      return canPassThroughTwoTall(world, n, n2, n3, world.getBlockState2(n, n2, n3), n4, n5, n6, flag, flag2, flag3, flag4);
   }
}
