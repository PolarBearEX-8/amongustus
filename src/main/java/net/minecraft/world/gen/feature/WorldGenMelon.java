package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenMelon extends WorldGenerator {
   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      for (int i = 0; i < 64; i++) {
         BlockPos pos2 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (Blocks.melon_block.canPlaceBlockAt(world, pos2) && world.getBlockState(pos2.down()).getBlock() == Blocks.grass) {
            world.setBlockState(pos2, Blocks.melon_block.getDefaultState(), 2);
         }
      }

      return true;
   }
}
