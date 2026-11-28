package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenWaterlily extends WorldGenerator {
   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      for (int i = 0; i < 10; i++) {
         int n = pos.getX() + rand.nextInt(8) - rand.nextInt(8);
         int n2 = pos.getY() + rand.nextInt(4) - rand.nextInt(4);
         int n3 = pos.getZ() + rand.nextInt(8) - rand.nextInt(8);
         if (world.isAirBlock(new BlockPos(n, n2, n3)) && Blocks.waterlily.canPlaceBlockAt(world, new BlockPos(n, n2, n3))) {
            world.setBlockState(new BlockPos(n, n2, n3), Blocks.waterlily.getDefaultState(), 2);
         }
      }

      return true;
   }
}
