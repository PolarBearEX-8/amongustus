package net.minecraft.world.gen.feature;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenCactus extends WorldGenerator {
   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      for (int i = 0; i < 10; i++) {
         BlockPos pos2 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (world.isAirBlock(pos2)) {
            int n = 1 + rand.nextInt(rand.nextInt(3) + 1);

            for (int j = 0; j < n; j++) {
               if (Blocks.cactus.canBlockStay(world, pos2)) {
                  world.setBlockState(pos2.up(j), Blocks.cactus.getDefaultState(), 2);
               }
            }
         }
      }

      return true;
   }
}
