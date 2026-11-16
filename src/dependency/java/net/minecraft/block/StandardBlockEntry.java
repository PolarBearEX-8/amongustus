package net.minecraft.block;

import client.setting.CombatConfigProperty;
import client.util.CurveParser;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class StandardBlockEntry extends CurveParser {
   public Block block;

   public StandardBlockEntry(Block block2) {
      this.block = block2;
   }

   @Override
   public boolean matchesWorld(World world, Random random, BlockPos blockPos) {
      BlockPos blockPos2 = blockPos.add(-8, 0, -8);

      while (blockPos2.getY() > 5 && world.isAirBlock(blockPos2)) {
         blockPos2 = blockPos2.down();
      }

      if (blockPos2.getY() <= 4) {
         return false;
      } else {
         blockPos2 = blockPos2.resolveBlockPos4(4);
         boolean[] flagArray = new boolean[2048];
         int count = random.nextInt(4) + 4;

         for (int index = 0; index < count; index++) {
            double offset = random.nextDouble() * 6.0 + 3.0;
            double offset2 = random.nextDouble() * 4.0 + 2.0;
            double offset3 = random.nextDouble() * 6.0 + 3.0;
            double offset4 = random.nextDouble() * (16.0 - offset - 2.0) + 1.0 + offset / 2.0;
            double offset5 = random.nextDouble() * (8.0 - offset2 - 4.0) + 2.0 + offset2 / 2.0;
            double offset6 = random.nextDouble() * (16.0 - offset3 - 2.0) + 1.0 + offset3 / 2.0;

            for (int index2 = 1; index2 < 15; index2++) {
               for (int index3 = 1; index3 < 15; index3++) {
                  for (int index4 = 1; index4 < 7; index4++) {
                     double offset7 = ((double)index2 - offset4) / (offset / 2.0);
                     double offset8 = ((double)index4 - offset5) / (offset2 / 2.0);
                     double offset9 = ((double)index3 - offset6) / (offset3 / 2.0);
                     if (offset7 * offset7 + offset8 * offset8 + offset9 * offset9 < 1.0) {
                        flagArray[(index2 * 16 + index3) * 8 + index4] = true;
                     }
                  }
               }
            }
         }

         for (int index5 = 0; index5 < 16; index5++) {
            for (int index6 = 0; index6 < 16; index6++) {
               for (int index7 = 0; index7 < 8; index7++) {
                  if (!flagArray[(index5 * 16 + index6) * 8 + index7]
                     && (
                        index5 < 15 && flagArray[((index5 + 1) * 16 + index6) * 8 + index7]
                           || index5 > 0 && flagArray[((index5 - 1) * 16 + index6) * 8 + index7]
                           || index6 < 15 && flagArray[(index5 * 16 + index6 + 1) * 8 + index7]
                           || index6 > 0 && flagArray[(index5 * 16 + (index6 - 1)) * 8 + index7]
                           || index7 < 7 && flagArray[(index5 * 16 + index6) * 8 + index7 + 1]
                           || index7 > 0 && flagArray[(index5 * 16 + index6) * 8 + (index7 - 1)]
                     )) {
                     Material material = world.getBlockState(blockPos2.add(index5, index7, index6)).getBlock().getMaterial();
                     if (index7 >= 4 && material.isActive2()) {
                        return false;
                     }

                     if (index7 < 4 && !material.canExecute2() && world.getBlockState(blockPos2.add(index5, index7, index6)).getBlock() != this.block) {
                        return false;
                     }
                  }
               }
            }
         }

         for (int index8 = 0; index8 < 16; index8++) {
            for (int index9 = 0; index9 < 16; index9++) {
               for (int index10 = 0; index10 < 8; index10++) {
                  if (flagArray[(index8 * 16 + index9) * 8 + index10]) {
                     world.matchesBlockPos(blockPos2.add(index8, index10, index9), index10 >= 4 ? Blocks.air.getDefaultState() : this.block.getDefaultState(), 2);
                  }
               }
            }
         }

         for (int index11 = 0; index11 < 16; index11++) {
            for (int index12 = 0; index12 < 16; index12++) {
               for (int index13 = 4; index13 < 8; index13++) {
                  if (flagArray[(index11 * 16 + index12) * 8 + index13]) {
                     BlockPos blockPos3 = blockPos2.add(index11, index13 - 1, index12);
                     if (world.getBlockState(blockPos3).getBlock() == Blocks.block115
                        && world.computeCount(CombatConfigProperty.SKY, blockPos2.getX() + index11, blockPos2.getY() + index13, blockPos2.getZ() + index12) > 0) {
                        if (world.resolveBiomeIDIsOutOfBoundsManager(blockPos3).iBlockState.getBlock() == Blocks.blockMycelium) {
                           world.matchesBlockPos(blockPos3, Blocks.blockMycelium.getDefaultState(), 2);
                        } else {
                           world.matchesBlockPos(blockPos3, Blocks.blockGrass.getDefaultState(), 2);
                        }
                     }
                  }
               }
            }
         }

         if (this.block.getMaterial() == Material.material29) {
            for (int index14 = 0; index14 < 16; index14++) {
               for (int index15 = 0; index15 < 16; index15++) {
                  for (int index16 = 0; index16 < 8; index16++) {
                     if (!flagArray[(index14 * 16 + index15) * 8 + index16]
                        && (
                           index14 < 15 && flagArray[((index14 + 1) * 16 + index15) * 8 + index16]
                              || index14 > 0 && flagArray[((index14 - 1) * 16 + index15) * 8 + index16]
                              || index15 < 15 && flagArray[(index14 * 16 + index15 + 1) * 8 + index16]
                              || index15 > 0 && flagArray[(index14 * 16 + (index15 - 1)) * 8 + index16]
                              || index16 < 7 && flagArray[(index14 * 16 + index15) * 8 + index16 + 1]
                              || index16 > 0 && flagArray[(index14 * 16 + index15) * 8 + (index16 - 1)]
                        )
                        && (index16 < 4 || random.nextInt(2) != 0)
                        && world.getBlockState(blockPos2.add(index14, index16, index15)).getBlock().getMaterial().canExecute2()) {
                        world.matchesBlockPos(blockPos2.add(index14, index16, index15), Blocks.stone.getDefaultState(), 2);
                     }
                  }
               }
            }
         }

         if (this.block.getMaterial() == Material.coreItemEntry1) {
            for (int index17 = 0; index17 < 16; index17++) {
               for (int index18 = 0; index18 < 16; index18++) {
                  byte byteValue = 4;
                  if (world.matchesBlockPos3(blockPos2.add(index17, 4, index18))) {
                     world.matchesBlockPos(blockPos2.add(index17, byteValue, index18), Blocks.block53.getDefaultState(), 2);
                  }
               }
            }
         }

         return true;
      }
   }
}
