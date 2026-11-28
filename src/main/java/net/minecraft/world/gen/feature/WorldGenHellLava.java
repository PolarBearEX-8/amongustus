package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenHellLava extends WorldGenerator {
   private final Block block;
   private final boolean insideRock;

   public WorldGenHellLava(Block block2, boolean flag) {
      this.block = block2;
      this.insideRock = flag;
   }

   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      if (world.getBlockState(pos.up()).getBlock() != Blocks.netherrack) {
         return false;
      } else if (world.getBlockState(pos).getBlock().getMaterial() != Material.air && world.getBlockState(pos).getBlock() != Blocks.netherrack) {
         return false;
      } else {
         int n = 0;
         if (world.getBlockState(pos.west()).getBlock() == Blocks.netherrack) {
            n++;
         }

         if (world.getBlockState(pos.east()).getBlock() == Blocks.netherrack) {
            n++;
         }

         if (world.getBlockState(pos.north()).getBlock() == Blocks.netherrack) {
            n++;
         }

         if (world.getBlockState(pos.south()).getBlock() == Blocks.netherrack) {
            n++;
         }

         if (world.getBlockState(pos.down()).getBlock() == Blocks.netherrack) {
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

         if (world.isAirBlock(pos.down())) {
            n2++;
         }

         if (!this.insideRock && n == 4 && n2 == 1 || n == 5) {
            world.setBlockState(pos, this.block.getDefaultState(), 2);
            world.forceBlockUpdateTick(this.block, pos, rand);
         }

         return true;
      }
   }
}
