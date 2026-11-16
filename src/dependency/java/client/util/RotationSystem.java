package client.util;

import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class RotationSystem extends CurveParser {
   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      for (int index = 0; index < 20; index++) {
         BlockPos blockPos2 = blockPos.add(random.nextInt(4) - random.nextInt(4), 0, random.nextInt(4) - random.nextInt(4));
         if (world.isAirBlock(blockPos2)) {
            BlockPos blockPos3 = blockPos2.down();
            if (world.getBlockState(blockPos3.getBlockPos()).getBlock().getMaterial() == Material.coreItemEntry1
               || world.getBlockState(blockPos3.getBlockPos3()).getBlock().getMaterial() == Material.coreItemEntry1
               || world.getBlockState(blockPos3.getBlockPosAsBlockPos()).getBlock().getMaterial() == Material.coreItemEntry1
               || world.getBlockState(blockPos3.getBlockPos2()).getBlock().getMaterial() == Material.coreItemEntry1) {
               int count = 2 + random.nextInt(random.nextInt(3) + 1);

               for (int index2 = 0; index2 < count; index2++) {
                  if (Blocks.blockReeds.matchesWorld3(world, blockPos2)) {
                     world.matchesBlockPos(blockPos2.resolveBlockPos2(index2), Blocks.blockReeds.getDefaultState(), 2);
                  }
               }
            }
         }
      }

      return true;
   }
}
