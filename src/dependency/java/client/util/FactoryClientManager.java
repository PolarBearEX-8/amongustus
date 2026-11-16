package client.util;

import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class FactoryClientManager extends CurveParser {
   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      for (int index = 0; index < 10; index++) {
         int count = blockPos.getX() + random.nextInt(8) - random.nextInt(8);
         int count2 = blockPos.getY() + random.nextInt(4) - random.nextInt(4);
         int count3 = blockPos.getZ() + random.nextInt(8) - random.nextInt(8);
         if (world.isAirBlock(new BlockPos(count, count2, count3)) && Blocks.block33.processAction7(world, new BlockPos(count, count2, count3))) {
            world.matchesBlockPos(new BlockPos(count, count2, count3), Blocks.block33.getDefaultState(), 2);
         }
      }

      return true;
   }
}
