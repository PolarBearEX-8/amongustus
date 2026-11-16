package client.util;

import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RotationEngine extends CurveParser {
   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      for (int index = 0; index < 64; index++) {
         BlockPos blockPos2 = blockPos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
         if (Blocks.block67.processAction7(world, blockPos2) && world.getBlockState(blockPos2.down()).getBlock() == Blocks.blockGrass) {
            world.matchesBlockPos(blockPos2, Blocks.block67.getDefaultState(), 2);
         }
      }

      return true;
   }
}
