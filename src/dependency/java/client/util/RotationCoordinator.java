package client.util;

import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RotationCoordinator extends CurveParser {
   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      for (int index = 0; index < 64; index++) {
         BlockPos blockPos2 = blockPos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
         if (world.isAirBlock(blockPos2) && world.getBlockState(blockPos2.down()).getBlock() == Blocks.block96) {
            world.matchesBlockPos(blockPos2, Blocks.blockFire.getDefaultState(), 2);
         }
      }

      return true;
   }
}
