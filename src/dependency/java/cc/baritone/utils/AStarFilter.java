package cc.baritone.utils;

import client.util.IBlockState;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockReeds;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.BlockWheat;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public enum AStarFilter {
   WHEAT((BlockWheat)Blocks.block92),
   CARROTS((BlockWheat)Blocks.block66),
   POTATOES((BlockWheat)Blocks.block20),
   PUMPKIN(Blocks.block146, (item -> AStarFilter.matchesIBlockState3((IBlockState)item))),
   MELON(Blocks.block67, (item -> AStarFilter.matchesIBlockStateByIBlockState((IBlockState)item))),
   NETHERWART(Blocks.block78, (item -> AStarFilter.matchesIBlockState((IBlockState)item))),
   COCOA(Blocks.block90, (item -> AStarFilter.matchesIBlockState2((IBlockState)item))),
   SUGARCANE(Blocks.blockReeds, null) {
      @Override
      public boolean test(World world, BlockPos blockPos, IBlockState blockState) {
         return (Boolean)BaritoneManager.getSettings().replantCrops.dataRef ? world.getBlockState(blockPos.down()).getBlock() instanceof BlockReeds : true;
      }
   },
   CACTUS(Blocks.blockCactus, null) {
      @Override
      public boolean test(World world, BlockPos blockPos, IBlockState blockState) {
         return (Boolean)BaritoneManager.getSettings().replantCrops.dataRef ? world.getBlockState(blockPos.down()).getBlock() instanceof BlockCactus : true;
      }
   };

   public final Block block;
   public final Predicate readyToHarvest;
   public static final AStarFilter[] ENUM_VALUES = values();

   private static boolean matchesIBlockStateByIBlockState(IBlockState blockState) {
      return true;
   }

   private AStarFilter(Block block2, Predicate predicate) {
      this.block = block2;
      this.readyToHarvest = predicate;
   }

   private static boolean matchesIBlockState(IBlockState blockState) {
      return (Integer)blockState.getComparable(BlockNetherWart.auxiliaryImmutableSetEntry) >= 3;
   }

   private static boolean matchesBlockWheat(BlockWheat blockWheat, IBlockState blockState) {
      return (Integer)blockState.getComparable(BlockWheat.auxiliaryImmutableSetEntry) >= 7;
   }

   private static boolean matchesIBlockState2(IBlockState blockState) {
      return (Integer)blockState.getComparable(BlockCocoa.auxiliaryImmutableSetEntry) >= 2;
   }

   public boolean test(World world, BlockPos blockPos, IBlockState blockState) {
      return this.readyToHarvest.test(blockState);
   }



   private AStarFilter(BlockWheat blockWheat) {
      this(blockWheat, state -> AStarFilter.matchesBlockWheat(blockWheat, (IBlockState)state));
   }

   private AStarFilter(Block block2, Predicate predicate, Controller3 controller) {
      this(block2, predicate);
   }

   private static boolean matchesIBlockState3(IBlockState blockState) {
      return true;
   }

}
