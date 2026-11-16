package net.minecraft.block;

import client.util.CurveParser;
import java.util.Random;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CustomBlockEntry extends CurveParser {
   public Block block = Blocks.block105;
   public int count;

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      BlockPos blockPos2 = blockPos;

      while (world.isAirBlock(blockPos2) && blockPos2.getY() > 2) {
         blockPos2 = blockPos2.down();
      }

      if (world.getBlockState(blockPos2).getBlock() != Blocks.block61) {
         return false;
      } else {
         int count2 = random.nextInt(this.count - 2) + 2;

         for (int index = blockPos2.getX() - count2; index <= blockPos2.getX() + count2; index++) {
            for (int index2 = blockPos2.getZ() - count2; index2 <= blockPos2.getZ() + count2; index2++) {
               int count3 = index - blockPos2.getX();
               int count4 = index2 - blockPos2.getZ();
               if (count3 * count3 + count4 * count4 <= count2 * count2) {
                  for (int index3 = blockPos2.getY() - 1; index3 <= blockPos2.getY() + 1; index3++) {
                     BlockPos blockPos3 = new BlockPos(index, index3, index2);
                     Block block2 = world.getBlockState(blockPos3).getBlock();
                     if (block2 == Blocks.block115 || block2 == Blocks.block61 || block2 == Blocks.block53) {
                        world.matchesBlockPos(blockPos3, this.block.getDefaultState(), 2);
                     }
                  }
               }
            }
         }

         return true;
      }
   }

   public CustomBlockEntry(int count2) {
      this.count = count2;
   }
}
