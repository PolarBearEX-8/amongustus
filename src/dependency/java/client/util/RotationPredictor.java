package client.util;

import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class RotationPredictor extends CurveParser {
   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      if (!world.isAirBlock(blockPos)) {
         return false;
      } else if (world.getBlockState(blockPos.up()).getBlock() != Blocks.block96) {
         return false;
      } else {
         world.matchesBlockPos(blockPos, Blocks.block77.getDefaultState(), 2);

         for (int index = 0; index < 1500; index++) {
            BlockPos blockPos2 = blockPos.add(random.nextInt(8) - random.nextInt(8), -random.nextInt(12), random.nextInt(8) - random.nextInt(8));
            if (world.getBlockState(blockPos2).getBlock().getMaterial() == Material.coreItemEntry) {
               int count = 0;

               for (EnumFacing enumFacing : EnumFacing.getEntries()) {
                  if (world.getBlockState(blockPos2.offset(enumFacing)).getBlock() == Blocks.block77) {
                     count++;
                  }

                  if (count > 1) {
                     break;
                  }
               }

               if (count == 1) {
                  world.matchesBlockPos(blockPos2, Blocks.block77.getDefaultState(), 2);
               }
            }
         }

         return true;
      }
   }
}
