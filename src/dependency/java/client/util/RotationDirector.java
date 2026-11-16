package client.util;

import client.setting.MovementFactorProperty;
import java.util.Random;
import net.minecraft.block.BlockLitPumpkin;
import net.minecraft.block.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RotationDirector extends CurveParser {
   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      for (int index = 0; index < 64; index++) {
         BlockPos blockPos2 = blockPos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
         if (world.isAirBlock(blockPos2) && world.getBlockState(blockPos2.down()).getBlock() == Blocks.blockGrass && Blocks.block146.processAction7(world, blockPos2)) {
            world.matchesBlockPos(
               blockPos2,
               Blocks.block146.getDefaultState().resolveIBlockState(BlockLitPumpkin.autoWalkPath, MovementFactorProperty.HORIZONTAL.resolveEnumFacing(random)),
               2
            );
         }
      }

      return true;
   }
}
