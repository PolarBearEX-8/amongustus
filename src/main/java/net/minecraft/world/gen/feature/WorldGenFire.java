package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenFire extends WorldGenerator {
   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      for (int i = 0; i < 64; i++) {
         BlockPos pos2 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (world.isAirBlock(pos2) && world.getBlockState(pos2.down()).getBlock() == Blocks.netherrack) {
            world.setBlockState(pos2, Blocks.fire.getDefaultState(), 2);
         }
      }

      return true;
   }
}
