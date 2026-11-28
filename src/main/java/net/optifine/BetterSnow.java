package net.optifine;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockMushroom;
import net.minecraft.block.BlockPane;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.BlockReed;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.BlockWall;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.optifine.config.Config;

public class BetterSnow {
   private static IBakedModel modelSnowLayer = null;

   public static IBlockState getStateSnowLayer() {
      return Blocks.snow_layer.getDefaultState();
   }

   public static void update() {
      modelSnowLayer = Config.getMc().getBlockRendererDispatcher().getBlockModelShapes().getModelForState(Blocks.snow_layer.getDefaultState());
   }

   private static boolean checkBlock(Block block, IBlockState state) {
      if (block.isFullCube()) {
         return false;
      } else if (block.isOpaqueCube()) {
         return false;
      } else if (block instanceof BlockSnow) {
         return false;
      } else if (!(block instanceof BlockBush)
         || !(block instanceof BlockDoublePlant)
            && !(block instanceof BlockFlower)
            && !(block instanceof BlockMushroom)
            && !(block instanceof BlockSapling)
            && !(block instanceof BlockTallGrass)) {
         if (block instanceof BlockFence
            || block instanceof BlockFenceGate
            || block instanceof BlockFlowerPot
            || block instanceof BlockPane
            || block instanceof BlockReed
            || block instanceof BlockWall) {
            return true;
         } else if (block instanceof BlockRedstoneTorch && state.getValue(BlockTorch.FACING) == EnumFacing.UP) {
            return true;
         } else {
            if (block instanceof BlockLever) {
               Comparable value = state.getValue(BlockLever.FACING);
               if (value == BlockLever.EnumOrientation.UP_X || value == BlockLever.EnumOrientation.UP_Z) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }

   public static IBakedModel getModelSnowLayer() {
      return modelSnowLayer;
   }

   private static boolean hasSnowNeighbours(IBlockAccess world, BlockPos pos) {
      Block snowLayer = Blocks.snow_layer;
      return world.getBlockState(pos.north()).getBlock() != snowLayer
            && world.getBlockState(pos.south()).getBlock() != snowLayer
            && world.getBlockState(pos.west()).getBlock() != snowLayer
            && world.getBlockState(pos.east()).getBlock() != snowLayer
         ? false
         : world.getBlockState(pos.down()).getBlock().isOpaqueCube();
   }

   public static boolean shouldRender(IBlockAccess world, IBlockState state, BlockPos pos) {
      return !checkBlock(state.getBlock(), state) ? false : hasSnowNeighbours(world, pos);
   }
}
