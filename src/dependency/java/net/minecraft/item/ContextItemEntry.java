package net.minecraft.item;

import client.setting.AdapterOptionType;
import client.util.BuildManager;
import client.util.IBlockState;
import client.util.ImmutableSetEntry2Entry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAcaciaFence;
import net.minecraft.block.BlockAcaciaFenceGate;
import net.minecraft.block.BlockCobblestoneWall;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlowerPot;
import net.minecraft.block.BlockGlassPane;
import net.minecraft.block.BlockLever;
import net.minecraft.block.BlockRedMushroom;
import net.minecraft.block.BlockRedstoneTorch;
import net.minecraft.block.BlockReeds;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockSnowLayer;
import net.minecraft.block.BlockTallgrass;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.Blocks;
import net.minecraft.block.SecondaryBlockAdapter;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;

public class ContextItemEntry {
   public static IBakedModel iBakedModel = null;

   private static boolean matchesBlock(Block block, IBlockState blockState) {
      if (block.processAction14()) {
         return false;
      } else if (block.processAction15()) {
         return false;
      } else if (block instanceof BlockSnowLayer) {
         return false;
      } else if (!(block instanceof SecondaryBlockAdapter)
         || !(block instanceof BlockDoublePlant)
            && !(block instanceof ImmutableSetEntry2Entry)
            && !(block instanceof BlockRedMushroom)
            && !(block instanceof BlockSapling)
            && !(block instanceof BlockTallgrass)) {
         if (block instanceof BlockAcaciaFence
            || block instanceof BlockAcaciaFenceGate
            || block instanceof BlockFlowerPot
            || block instanceof BlockGlassPane
            || block instanceof BlockReeds
            || block instanceof BlockCobblestoneWall) {
            return true;
         } else if (block instanceof BlockRedstoneTorch && blockState.getComparable(BlockTorch.autoWalkPath) == EnumFacing.UP) {
            return true;
         } else {
            if (block instanceof BlockLever) {
               Comparable comparable = blockState.getComparable(BlockLever.immutableSetEntry);
               if (comparable == AdapterOptionType.UP_X || comparable == AdapterOptionType.UP_Z) {
                  return true;
               }
            }

            return false;
         }
      } else {
         return true;
      }
   }

   private static boolean matchesIBlockAccess(IBlockAccess iBlockAccess, BlockPos blockPos) {
      Block block = Blocks.block74;
      return iBlockAccess.getBlockState(blockPos.getBlockPosAsBlockPos()).getBlock() != block
            && iBlockAccess.getBlockState(blockPos.getBlockPos2()).getBlock() != block
            && iBlockAccess.getBlockState(blockPos.getBlockPos()).getBlock() != block
            && iBlockAccess.getBlockState(blockPos.getBlockPos3()).getBlock() != block
         ? false
         : iBlockAccess.getBlockState(blockPos.down()).getBlock().processAction15();
   }

   public static void updateState() {
      iBakedModel = BuildManager.getMc().getGoldenCrystal().getCommonMapEntry().resolveIBakedModel(Blocks.block74.getDefaultState());
   }

   public static IBakedModel getIBakedModel() {
      return iBakedModel;
   }

   public static IBlockState getIBlockState() {
      return Blocks.block74.getDefaultState();
   }

   public static boolean matchesIBlockAccess(IBlockAccess iBlockAccess, IBlockState blockState, BlockPos blockPos) {
      return !matchesBlock(blockState.getBlock(), blockState) ? false : matchesIBlockAccess(iBlockAccess, blockPos);
   }
}
