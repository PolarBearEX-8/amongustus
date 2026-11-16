package client.util;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RotationBuilder extends CurveParser {
   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      BlockPos blockPos2 = blockPos;

      Block block;
      while (
         ((block = world.getBlockState(blockPos2).getBlock()).getMaterial() == Material.coreItemEntry || block.getMaterial() == Material.material9) && blockPos2.getY() > 0
      ) {
         blockPos2 = blockPos2.down();
      }

      for (int index = 0; index < 4; index++) {
         BlockPos blockPos3 = blockPos2.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
         if (world.isAirBlock(blockPos3) && Blocks.blockDeadbush.matchesWorld(world, blockPos3, Blocks.blockDeadbush.getDefaultState())) {
            world.matchesBlockPos(blockPos3, Blocks.blockDeadbush.getDefaultState(), 2);
         }
      }

      return true;
   }
}
