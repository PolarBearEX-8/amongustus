package client.util;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class RotationService extends CurveParser {
   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      BlockPos blockPos2 = blockPos;

      while (world.isAirBlock(blockPos2) && blockPos2.getY() > 2) {
         blockPos2 = blockPos2.down();
      }

      if (world.getBlockState(blockPos2).getBlock() != Blocks.block61) {
         return false;
      } else {
         blockPos2 = blockPos2.resolveBlockPos2(random.nextInt(4));
         int count = random.nextInt(4) + 7;
         int count2 = count / 4 + random.nextInt(2);
         if (count2 > 1 && random.nextInt(60) == 0) {
            blockPos2 = blockPos2.resolveBlockPos2(10 + random.nextInt(30));
         }

         for (int index = 0; index < count; index++) {
            float factor = (1.0F - (float)index / (float)count) * (float)count2;
            int count3 = MathHelper.computeCount(factor);

            for (int index2 = -count3; index2 <= count3; index2++) {
               float factor2 = (float)MathHelper.computeCount(index2) - 0.25F;

               for (int index3 = -count3; index3 <= count3; index3++) {
                  float factor3 = (float)MathHelper.computeCount(index3) - 0.25F;
                  if ((index2 == 0 && index3 == 0 || factor2 * factor2 + factor3 * factor3 <= factor * factor)
                     && (index2 != -count3 && index2 != count3 && index3 != -count3 && index3 != count3 || random.nextFloat() <= 0.75F)) {
                     Block block = world.getBlockState(blockPos2.add(index2, index, index3)).getBlock();
                     if (block.getMaterial() == Material.coreItemEntry || block == Blocks.block115 || block == Blocks.block61 || block == Blocks.block53) {
                        this.applyWorld(world, blockPos2.add(index2, index, index3), Blocks.block105.getDefaultState());
                     }

                     if (index != 0 && count3 > 1) {
                        Block block2 = world.getBlockState(blockPos2.add(index2, -index, index3)).getBlock();
                        if (block2.getMaterial() == Material.coreItemEntry || block2 == Blocks.block115 || block2 == Blocks.block61 || block2 == Blocks.block53) {
                           this.applyWorld(world, blockPos2.add(index2, -index, index3), Blocks.block105.getDefaultState());
                        }
                     }
                  }
               }
            }
         }

         int count4 = count2 - 1;
         if (count4 < 0) {
            count4 = 0;
         } else if (count4 > 1) {
            count4 = 1;
         }

         for (int index4 = -count4; index4 <= count4; index4++) {
            for (int index5 = -count4; index5 <= count4; index5++) {
               BlockPos blockPos3 = blockPos2.add(index4, -1, index5);
               int count5 = 50;
               if (Math.abs(index4) == 1 && Math.abs(index5) == 1) {
                  count5 = random.nextInt(5);
               }

               while (blockPos3.getY() > 50) {
                  Block block3 = world.getBlockState(blockPos3).getBlock();
                  if (block3.getMaterial() != Material.coreItemEntry
                     && block3 != Blocks.block115
                     && block3 != Blocks.block61
                     && block3 != Blocks.block53
                     && block3 != Blocks.block105) {
                     break;
                  }

                  this.applyWorld(world, blockPos3, Blocks.block105.getDefaultState());
                  blockPos3 = blockPos3.down();
                  if (--count5 <= 0) {
                     blockPos3 = blockPos3.resolveBlockPos4(random.nextInt(5) + 1);
                     count5 = random.nextInt(5);
                  }
               }
            }
         }

         return true;
      }
   }
}
