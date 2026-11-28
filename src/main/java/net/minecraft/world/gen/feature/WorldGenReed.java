package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenReed extends WorldGenerator {
   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      for (int i = 0; i < 20; i++) {
         BlockPos pos2 = pos.add(rand.nextInt(4) - rand.nextInt(4), 0, rand.nextInt(4) - rand.nextInt(4));
         if (world.isAirBlock(pos2)) {
            BlockPos pos3 = pos2.down();
            if (world.getBlockState(pos3.west()).getBlock().getMaterial() == Material.water
               || world.getBlockState(pos3.east()).getBlock().getMaterial() == Material.water
               || world.getBlockState(pos3.north()).getBlock().getMaterial() == Material.water
               || world.getBlockState(pos3.south()).getBlock().getMaterial() == Material.water) {
               int n = 2 + rand.nextInt(rand.nextInt(3) + 1);

               for (int j = 0; j < n; j++) {
                  if (Blocks.reeds.canBlockStay(world, pos2)) {
                     world.setBlockState(pos2.up(j), Blocks.reeds.getDefaultState(), 2);
                  }
               }
            }
         }
      }

      return true;
   }
}
