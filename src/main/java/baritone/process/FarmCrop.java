package baritone.process;

import baritone.Baritone;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.BlockReed;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockNetherWart;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public enum FarmCrop {
   WHEAT((BlockCrops)Blocks.wheat),
   CARROTS((BlockCrops)Blocks.carrots),
   POTATOES((BlockCrops)Blocks.potatoes),
   PUMPKIN(Blocks.pumpkin, obj -> true),
   MELON(Blocks.melon_block, obj -> true),
   NETHERWART(Blocks.nether_wart, obj -> (Integer)((IBlockState)obj).getValue(BlockNetherWart.AGE) >= 3),
   COCOA(Blocks.cocoa, obj -> (Integer)((IBlockState)obj).getValue(BlockCocoa.AGE) >= 2),
   SUGARCANE(Blocks.reeds, null) {
      @Override
      public boolean readyToHarvest(World world, BlockPos pos, IBlockState state) {
         return (Boolean)Baritone.settings().replantCrops.value ? world.getBlockState(pos.down()).getBlock() instanceof BlockReed : true;
      }
   },
   CACTUS(Blocks.cactus, null) {
      @Override
      public boolean readyToHarvest(World world, BlockPos pos, IBlockState state) {
         return (Boolean)Baritone.settings().replantCrops.value ? world.getBlockState(pos.down()).getBlock() instanceof BlockCactus : true;
      }
   };
   public final Block block;
   public final Predicate readyToHarvest;

   private FarmCrop(Block block2, Predicate predicate) {
      this.block = block2;
      this.readyToHarvest = predicate;
   }

   private FarmCrop(BlockCrops blockCrops) {
      this(blockCrops, obj -> (Integer)((IBlockState)obj).getValue(BlockCrops.AGE) >= 7);
   }

   public boolean readyToHarvest(World world, BlockPos pos, IBlockState state) {
      return this.readyToHarvest.test(state);
   }

}
