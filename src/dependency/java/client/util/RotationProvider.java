package client.util;

import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RotationProvider extends CurveParser {
   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      for (int index = 0; index < 10; index++) {
         BlockPos blockPos2 = blockPos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
         if (world.isAirBlock(blockPos2)) {
            int count = 1 + random.nextInt(random.nextInt(3) + 1);

            for (int index2 = 0; index2 < count; index2++) {
               if (Blocks.blockCactus.matchesWorld3(world, blockPos2)) {
                  world.matchesBlockPos(blockPos2.resolveBlockPos2(index2), Blocks.blockCactus.getDefaultState(), 2);
               }
            }
         }
      }

      return true;
   }
}
