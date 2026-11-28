package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenLiquids extends WorldGenerator {
   private Block block;

   public WorldGenLiquids(Block block2) {
      this.block = block2;
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      if (world.getBlockState(pos.up()).getBlock() != Blocks.stone) {
         return false;
      } else if (world.getBlockState(pos.down()).getBlock() != Blocks.stone) {
         return false;
      } else if (world.getBlockState(pos).getBlock().getMaterial() != Material.air && world.getBlockState(pos).getBlock() != Blocks.stone) {
         return false;
      } else {
         int n = 0;
         if (world.getBlockState(pos.west()).getBlock() == Blocks.stone) {
            n++;
         }

         if (world.getBlockState(pos.east()).getBlock() == Blocks.stone) {
            n++;
         }

         if (world.getBlockState(pos.north()).getBlock() == Blocks.stone) {
            n++;
         }

         if (world.getBlockState(pos.south()).getBlock() == Blocks.stone) {
            n++;
         }

         int n2 = 0;
         if (world.isAirBlock(pos.west())) {
            n2++;
         }

         if (world.isAirBlock(pos.east())) {
            n2++;
         }

         if (world.isAirBlock(pos.north())) {
            n2++;
         }

         if (world.isAirBlock(pos.south())) {
            n2++;
         }

         if (n == 3 && n2 == 1) {
            world.setBlockState(pos, this.block.getDefaultState(), 2);
            world.forceBlockUpdateTick(this.block, pos, rand);
         }

         return true;
      }
   }
}
