package net.minecraft.world.gen.feature;

import net.minecraft.util.EnumFacing;
import java.util.Random;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenPumpkin extends WorldGenerator {
   @Override
   public boolean generate(World world, Random rand, BlockPos pos) {
      for (int i = 0; i < 64; i++) {
         BlockPos pos2 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
         if (world.isAirBlock(pos2) && world.getBlockState(pos2.down()).getBlock() == Blocks.grass && Blocks.pumpkin.canPlaceBlockAt(world, pos2)) {
            world.setBlockState(pos2, Blocks.pumpkin.getDefaultState().withProperty(BlockPumpkin.FACING, EnumFacing.Plane.HORIZONTAL.random(rand)), 2);
         }
      }

      return true;
   }
}
