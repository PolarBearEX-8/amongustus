package client.util;

import java.util.Random;
import net.minecraft.block.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class AuxiliaryRandomEntryAdapter extends TertiaryRandomEntry {
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
      double offset5 = (double)(count * 16 + 8);
      double offset6 = (double)(count2 * 16 + 8);
      float factor3 = 0.0F;
      float factor4 = 0.0F;
      Random random = new Random(value);
      if (count4 <= 0) {
         int count7 = this.count * 16 - 16;
         count6 = count7 - random.nextInt(count7 / 4);
      }

      boolean flag = false;
      if (count3 == -1) {
         count5 = count6 / 2;
         flag = true;
      }

      int count8 = random.nextInt(count6 / 2) + count6 / 4;

      for (boolean flag2 = random.nextInt(6) == 0; count5 < count6; count5++) {
         double offset7 = 1.5 + (double)(MathHelper.abs((float)count5 * (float) Math.PI / (float)count6) * posX2 * 1.0F);
         double offset8 = offset7 * offset;
         float factor5 = MathHelper.cos(factor2);
         float factor6 = MathHelper.abs(factor2);
         offset2 += (double)(MathHelper.cos(factor) * factor5);
         offset3 += (double)factor6;
         offset4 += (double)(MathHelper.abs(factor) * factor5);
         if (flag2) {
            factor2 *= 0.92F;
         } else {
            factor2 *= 0.7F;
         }

         factor2 += factor4 * 0.1F;
         factor += factor3 * 0.1F;
         float factor7 = factor4 * 0.9F;
         float factor8 = factor3 * 0.75F;
         factor4 = factor7 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
         factor3 = factor8 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
         if (!flag && count5 == count8 && posX2 > 1.0F) {
            this.applyShortArrayEntry(
               random.nextLong(),
               count,
               count2,
               shortArrayEntry,
               offset2,
               offset3,
               offset4,
               random.nextFloat() * 0.5F + 0.5F,
               factor - (float) (Math.PI / 2),
               factor2 / 3.0F,
               count5,
               count6,
               1.0
            );
            this.applyShortArrayEntry(
               random.nextLong(),
               count,
               count2,
               shortArrayEntry,
               offset2,
               offset3,
               offset4,
               random.nextFloat() * 0.5F + 0.5F,
               factor + (float) (Math.PI / 2),
               factor2 / 3.0F,
               count5,
               count6,
               1.0
            );
            return;
         }

         if (flag || random.nextInt(4) != 0) {
            double offset9 = offset2 - offset5;
            double offset10 = offset4 - offset6;
            double offset11 = (double)(count6 - count5);
            double offset12 = (double)(posX2 + 2.0F + 16.0F);
            if (offset9 * offset9 + offset10 * offset10 - offset11 * offset11 > offset12 * offset12) {
               return;
            }

            if (offset2 >= offset5 - 16.0 - offset7 * 2.0
               && offset4 >= offset6 - 16.0 - offset7 * 2.0
               && offset2 <= offset5 + 16.0 + offset7 * 2.0
               && offset4 <= offset6 + 16.0 + offset7 * 2.0) {
               int count9 = MathHelper.ceiling_double_int(offset2 - offset7) - count * 16 - 1;
               int count10 = MathHelper.ceiling_double_int(offset2 + offset7) - count * 16 + 1;
               int count11 = MathHelper.ceiling_double_int(offset3 - offset8) - 1;
               int count12 = MathHelper.ceiling_double_int(offset3 + offset8) + 1;
               int count13 = MathHelper.ceiling_double_int(offset4 - offset7) - count2 * 16 - 1;
               int count14 = MathHelper.ceiling_double_int(offset4 + offset7) - count2 * 16 + 1;
               if (count9 < 0) {
                  count9 = 0;
               }

               if (count10 > 16) {
                  count10 = 16;
               }

               if (count11 < 1) {
                  count11 = 1;
               }

               if (count12 > 120) {
                  count12 = 120;
               }

               if (count13 < 0) {
                  count13 = 0;
               }

               if (count14 > 16) {
                  count14 = 16;
               }

               boolean flag3 = false;

               for (int index = count9; !flag3 && index < count10; index++) {
                  for (int index2 = count13; !flag3 && index2 < count14; index2++) {
                     for (int index3 = count12 + 1; !flag3 && index3 >= count11 - 1; index3--) {
                        if (index3 >= 0 && index3 < 128) {
                           IBlockState blockState = shortArrayEntry.resolveIBlockState(index, index3, index2);
                           if (blockState.getBlock() == Blocks.blockFlowingLava2 || blockState.getBlock() == Blocks.blockLava) {
                              flag3 = true;
                           }

                           if (index3 != count11 - 1 && index != count9 && index != count10 - 1 && index2 != count13 && index2 != count14 - 1) {
                              index3 = count11;
                           }
                        }
                     }
                  }
               }

               if (!flag3) {
                  for (int index4 = count9; index4 < count10; index4++) {
                     double offset13 = ((double)(index4 + count * 16) + 0.5 - offset2) / offset7;

                     for (int index5 = count13; index5 < count14; index5++) {
                        double offset14 = ((double)(index5 + count2 * 16) + 0.5 - offset4) / offset7;

                        for (int index6 = count12; index6 > count11; index6--) {
                           double offset15 = ((double)(index6 - 1) + 0.5 - offset3) / offset8;
                           if (offset15 > -0.7 && offset13 * offset13 + offset15 * offset15 + offset14 * offset14 < 1.0) {
                              IBlockState blockState2 = shortArrayEntry.resolveIBlockState(index4, index6, index5);
                              if (blockState2.getBlock() == Blocks.block96 || blockState2.getBlock() == Blocks.block115 || blockState2.getBlock() == Blocks.blockGrass) {
                                 shortArrayEntry.applyIBlockState(index4, index6, index5, Blocks.air.getDefaultState());
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

   @Override
   public void applyWorld(World world, int count, int count2, int count3, int count4, ShortArrayEntry shortArrayEntry) {
      int count5 = this.random.nextInt(this.random.nextInt(this.random.nextInt(10) + 1) + 1);
      if (this.random.nextInt(5) != 0) {
         count5 = 0;
      }

      for (int index = 0; index < count5; index++) {
         double offset = (double)(count * 16 + this.random.nextInt(16));
         double offset2 = (double)this.random.nextInt(128);
         double offset3 = (double)(count2 * 16 + this.random.nextInt(16));
         int count6 = 1;
         if (this.random.nextInt(4) == 0) {
            this.applyShortArrayEntry(this.random.nextLong(), count3, count4, shortArrayEntry, offset, offset2, offset3);
            count6 += this.random.nextInt(4);
         }

         for (int index2 = 0; index2 < count6; index2++) {
            float factor = this.random.nextFloat() * (float) Math.PI * 2.0F;
            float factor2 = (this.random.nextFloat() - 0.5F) * 2.0F / 8.0F;
            float factor3 = this.random.nextFloat() * 2.0F + this.random.nextFloat();
            this.applyShortArrayEntry(this.random.nextLong(), count3, count4, shortArrayEntry, offset, offset2, offset3, factor3 * 2.0F, factor, factor2, 0, 0, 0.5);
         }
      }
   }

   public void applyShortArrayEntry(long value, int count, int count2, ShortArrayEntry shortArrayEntry, double posX, double posY, double posZ) {
      this.applyShortArrayEntry(value, count, count2, shortArrayEntry, posX, posY, posZ, 1.0F + this.random.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5);
   }
}
