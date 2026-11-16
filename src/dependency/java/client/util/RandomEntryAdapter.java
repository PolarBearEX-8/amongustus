package client.util;

import java.util.Random;
import net.minecraft.block.AuxiliaryBlockPosAdapter;
import net.minecraft.block.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class RandomEntryAdapter extends TertiaryRandomEntry {
   public float[] factorArray = new float[1024];

   @Override
   public void applyWorld(World world, int count, int count2, int count3, int count4, ShortArrayEntry shortArrayEntry) {
      if (this.random.nextInt(50) == 0) {
         double offset = (double)(count * 16 + this.random.nextInt(16));
         double offset2 = (double)(this.random.nextInt(this.random.nextInt(40) + 8) + 20);
         double offset3 = (double)(count2 * 16 + this.random.nextInt(16));

         for (int index = 0; index < 1; index++) {
            float factor = this.random.nextFloat() * (float) Math.PI * 2.0F;
            float factor2 = (this.random.nextFloat() - 0.5F) * 2.0F / 8.0F;
            float factor3 = (this.random.nextFloat() * 2.0F + this.random.nextFloat()) * 2.0F;
            this.applyShortArrayEntry(this.random.nextLong(), count3, count4, shortArrayEntry, offset, offset2, offset3, factor3, factor, factor2, 0, 0, 3.0);
         }
      }
   }

   public void applyShortArrayEntry(
      long value,
      int count,
      int count2,
      ShortArrayEntry shortArrayEntry,
      double posX,
      double posY,
      double posZ,
      float posX2,
      float posY2,
      float posZ2,
      int count3,
      int count4,
      double offset
   ) {
      double offset2 = posX;
      double offset3 = posY;
      double offset4 = posZ;
      float factor = posY2;
      float factor2 = posZ2;
      int count5 = count3;
      int count6 = count4;
      Random random = new Random(value);
      double offset5 = (double)(count * 16 + 8);
      double offset6 = (double)(count2 * 16 + 8);
      float factor3 = 0.0F;
      float factor4 = 0.0F;
      if (count4 <= 0) {
         int count7 = this.count * 16 - 16;
         count6 = count7 - random.nextInt(count7 / 4);
      }

      boolean flag = false;
      if (count3 == -1) {
         count5 = count6 / 2;
         flag = true;
      }

      float factor5 = 1.0F;

      for (int index = 0; index < 256; index++) {
         if (index == 0 || random.nextInt(3) == 0) {
            factor5 = 1.0F + random.nextFloat() * random.nextFloat() * 1.0F;
         }

         this.factorArray[index] = factor5 * factor5;
      }

      for (; count5 < count6; count5++) {
         double offset7 = 1.5 + (double)(MathHelper.abs((float)count5 * (float) Math.PI / (float)count6) * posX2 * 1.0F);
         double offset8 = offset7 * offset;
         double offset9 = offset7 * ((double)random.nextFloat() * 0.25 + 0.75);
         double offset10 = offset8 * ((double)random.nextFloat() * 0.25 + 0.75);
         float factor6 = MathHelper.cos(factor2);
         float factor7 = MathHelper.abs(factor2);
         offset2 += (double)(MathHelper.cos(factor) * factor6);
         offset3 += (double)factor7;
         offset4 += (double)(MathHelper.abs(factor) * factor6);
         factor2 *= 0.7F;
         factor2 += factor4 * 0.05F;
         factor += factor3 * 0.05F;
         float factor8 = factor4 * 0.8F;
         float factor9 = factor3 * 0.5F;
         factor4 = factor8 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
         factor3 = factor9 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
         if (flag || random.nextInt(4) != 0) {
            double offset11 = offset2 - offset5;
            double offset12 = offset4 - offset6;
            double offset13 = (double)(count6 - count5);
            double offset14 = (double)(posX2 + 2.0F + 16.0F);
            if (offset11 * offset11 + offset12 * offset12 - offset13 * offset13 > offset14 * offset14) {
               return;
            }

            if (offset2 >= offset5 - 16.0 - offset9 * 2.0
               && offset4 >= offset6 - 16.0 - offset9 * 2.0
               && offset2 <= offset5 + 16.0 + offset9 * 2.0
               && offset4 <= offset6 + 16.0 + offset9 * 2.0) {
               int count8 = MathHelper.ceiling_double_int(offset2 - offset9) - count * 16 - 1;
               int count9 = MathHelper.ceiling_double_int(offset2 + offset9) - count * 16 + 1;
               int count10 = MathHelper.ceiling_double_int(offset3 - offset10) - 1;
               int count11 = MathHelper.ceiling_double_int(offset3 + offset10) + 1;
               int count12 = MathHelper.ceiling_double_int(offset4 - offset9) - count2 * 16 - 1;
               int count13 = MathHelper.ceiling_double_int(offset4 + offset9) - count2 * 16 + 1;
               if (count8 < 0) {
                  count8 = 0;
               }

               if (count9 > 16) {
                  count9 = 16;
               }

               if (count10 < 1) {
                  count10 = 1;
               }

               if (count11 > 248) {
                  count11 = 248;
               }

               if (count12 < 0) {
                  count12 = 0;
               }

               if (count13 > 16) {
                  count13 = 16;
               }

               boolean flag2 = false;

               for (int index2 = count8; !flag2 && index2 < count9; index2++) {
                  for (int index3 = count12; !flag2 && index3 < count13; index3++) {
                     for (int index4 = count11 + 1; !flag2 && index4 >= count10 - 1; index4--) {
                        if (index4 >= 0 && index4 < 256) {
                           IBlockState blockState = shortArrayEntry.resolveIBlockState(index2, index4, index3);
                           if (blockState.getBlock() == Blocks.blockFlowingLava || blockState.getBlock() == Blocks.blockLava2) {
                              flag2 = true;
                           }

                           if (index4 != count10 - 1 && index2 != count8 && index2 != count9 - 1 && index3 != count12 && index3 != count13 - 1) {
                              index4 = count10;
                           }
                        }
                     }
                  }
               }

               if (!flag2) {
                  AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter = new AuxiliaryBlockPosAdapter();

                  for (int index5 = count8; index5 < count9; index5++) {
                     double offset15 = ((double)(index5 + count * 16) + 0.5 - offset2) / offset9;

                     for (int index6 = count12; index6 < count13; index6++) {
                        double offset16 = ((double)(index6 + count2 * 16) + 0.5 - offset4) / offset9;
                        boolean flag3 = false;
                        if (offset15 * offset15 + offset16 * offset16 < 1.0) {
                           for (int index7 = count11; index7 > count10; index7--) {
                              double offset17 = ((double)(index7 - 1) + 0.5 - offset3) / offset10;
                              if ((offset15 * offset15 + offset16 * offset16) * (double)this.factorArray[index7 - 1] + offset17 * offset17 / 6.0 < 1.0) {
                                 IBlockState blockState2 = shortArrayEntry.resolveIBlockState(index5, index7, index6);
                                 if (blockState2.getBlock() == Blocks.blockGrass) {
                                    flag3 = true;
                                 }

                                 if (blockState2.getBlock() == Blocks.stone || blockState2.getBlock() == Blocks.block115 || blockState2.getBlock() == Blocks.blockGrass) {
                                    if (index7 - 1 < 10) {
                                       shortArrayEntry.applyIBlockState(index5, index7, index6, Blocks.blockFlowingLava2.getDefaultState());
                                    } else {
                                       shortArrayEntry.applyIBlockState(index5, index7, index6, Blocks.air.getDefaultState());
                                       if (flag3 && shortArrayEntry.resolveIBlockState(index5, index7 - 1, index6).getBlock() == Blocks.block115) {
                                          auxiliaryBlockPosAdapter.resolveAuxiliaryBlockPosAdapter(index5 + count * 16, 0, index6 + count2 * 16);
                                          shortArrayEntry.applyIBlockState(index5, index7 - 1, index6, this.world.resolveBiomeIDIsOutOfBoundsManager(auxiliaryBlockPosAdapter).iBlockState);
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }

                  if (flag) {
                     break;
                  }
               }
            }
         }
      }
   }
}
