package net.minecraft.world.gen;

import net.minecraft.util.BlockPos;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class MapGenRavine extends MapGenBase {
   private float[] rs = new float[1024];

   public void addTunnel(
      long l,
      int n,
      int n2,
      ChunkPrimer chunkPrimer,
      double d,
      double d2,
      double d3,
      float f,
      float f2,
      float f3,
      int n3,
      int n4,
      double d4
   ) {
      Random rand2 = new Random(l);
      double d5 = (double)(n * 16 + 8);
      double d6 = (double)(n2 * 16 + 8);
      float f4 = 0.0F;
      float f5 = 0.0F;
      if (n4 <= 0) {
         int n5 = this.range * 16 - 16;
         n4 = n5 - rand2.nextInt(n5 / 4);
      }

      boolean flag = false;
      if (n3 == -1) {
         n3 = n4 / 2;
         flag = true;
      }

      float f6 = 1.0F;

      for (int i = 0; i < 256; i++) {
         if (i == 0 || rand2.nextInt(3) == 0) {
            f6 = 1.0F + rand2.nextFloat() * rand2.nextFloat() * 1.0F;
         }

         this.rs[i] = f6 * f6;
      }

      for (; n3 < n4; n3++) {
         double d7 = 1.5 + (double)(MathHelper.sin((float)n3 * (float) Math.PI / (float)n4) * f * 1.0F);
         double d8 = d7 * d4;
         d7 *= (double)rand2.nextFloat() * 0.25 + 0.75;
         d8 *= (double)rand2.nextFloat() * 0.25 + 0.75;
         float f7 = MathHelper.cos(f3);
         float f8 = MathHelper.sin(f3);
         d += (double)(MathHelper.cos(f2) * f7);
         d2 += (double)f8;
         d3 += (double)(MathHelper.sin(f2) * f7);
         f3 *= 0.7F;
         f3 += f5 * 0.05F;
         f2 += f4 * 0.05F;
         f5 *= 0.8F;
         f4 *= 0.5F;
         f5 += (rand2.nextFloat() - rand2.nextFloat()) * rand2.nextFloat() * 2.0F;
         f4 += (rand2.nextFloat() - rand2.nextFloat()) * rand2.nextFloat() * 4.0F;
         if (flag || rand2.nextInt(4) != 0) {
            double d9 = d - d5;
            double d10 = d3 - d6;
            double d11 = (double)(n4 - n3);
            double d12 = (double)(f + 2.0F + 16.0F);
            if (d9 * d9 + d10 * d10 - d11 * d11 > d12 * d12) {
               return;
            }

            if (d >= d5 - 16.0 - d7 * 2.0
               && d3 >= d6 - 16.0 - d7 * 2.0
               && d <= d5 + 16.0 + d7 * 2.0
               && d3 <= d6 + 16.0 + d7 * 2.0) {
               int n6 = MathHelper.floor_double(d - d7) - n * 16 - 1;
               int n7 = MathHelper.floor_double(d + d7) - n * 16 + 1;
               int n8 = MathHelper.floor_double(d2 - d8) - 1;
               int n9 = MathHelper.floor_double(d2 + d8) + 1;
               int n10 = MathHelper.floor_double(d3 - d7) - n2 * 16 - 1;
               int n11 = MathHelper.floor_double(d3 + d7) - n2 * 16 + 1;
               if (n6 < 0) {
                  n6 = 0;
               }

               if (n7 > 16) {
                  n7 = 16;
               }

               if (n8 < 1) {
                  n8 = 1;
               }

               if (n9 > 248) {
                  n9 = 248;
               }

               if (n10 < 0) {
                  n10 = 0;
               }

               if (n11 > 16) {
                  n11 = 16;
               }

               boolean flag2 = false;

               for (int j = n6; !flag2 && j < n7; j++) {
                  for (int k = n10; !flag2 && k < n11; k++) {
                     for (int m = n9 + 1; !flag2 && m >= n8 - 1; m--) {
                        if (m >= 0 && m < 256) {
                           IBlockState blockState = chunkPrimer.getBlockState(j, m, k);
                           if (blockState.getBlock() == Blocks.flowing_water || blockState.getBlock() == Blocks.water) {
                              flag2 = true;
                           }

                           if (m != n8 - 1 && j != n6 && j != n7 - 1 && k != n10 && k != n11 - 1) {
                              m = n8;
                           }
                        }
                     }
                  }
               }

               if (!flag2) {
                  BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

                  for (int idx = n6; idx < n7; idx++) {
                     double d13 = ((double)(idx + n * 16) + 0.5 - d) / d7;

                     for (int index = n10; index < n11; index++) {
                        double d14 = ((double)(index + n2 * 16) + 0.5 - d3) / d7;
                        boolean flag3 = false;
                        if (d13 * d13 + d14 * d14 < 1.0) {
                           for (int i2 = n9; i2 > n8; i2--) {
                              double d15 = ((double)(i2 - 1) + 0.5 - d2) / d8;
                              if ((d13 * d13 + d14 * d14) * (double)this.rs[i2 - 1] + d15 * d15 / 6.0 < 1.0) {
                                 IBlockState blockState2 = chunkPrimer.getBlockState(idx, i2, index);
                                 if (blockState2.getBlock() == Blocks.grass) {
                                    flag3 = true;
                                 }

                                 if (blockState2.getBlock() == Blocks.stone || blockState2.getBlock() == Blocks.dirt || blockState2.getBlock() == Blocks.grass) {
                                    if (i2 - 1 < 10) {
                                       chunkPrimer.setBlockState(idx, i2, index, Blocks.flowing_lava.getDefaultState());
                                    } else {
                                       chunkPrimer.setBlockState(idx, i2, index, Blocks.air.getDefaultState());
                                       if (flag3 && chunkPrimer.getBlockState(idx, i2 - 1, index).getBlock() == Blocks.dirt) {
                                          pos.set(idx + n * 16, 0, index + n2 * 16);
                                          chunkPrimer.setBlockState(idx, i2 - 1, index, this.worldObj.getBiomeGenForCoords(pos).topBlock);
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

   @Override
   public void recursiveGenerate(World world, int n, int n2, int n3, int n4, ChunkPrimer chunkPrimer) {
      if (this.rand.nextInt(50) == 0) {
         double d = (double)(n * 16 + this.rand.nextInt(16));
         double d2 = (double)(this.rand.nextInt(this.rand.nextInt(40) + 8) + 20);
         double d3 = (double)(n2 * 16 + this.rand.nextInt(16));
         byte b = 1;

         for (int i = 0; i < b; i++) {
            float f = this.rand.nextFloat() * (float) Math.PI * 2.0F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
            float f3 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 2.0F;
            this.addTunnel(this.rand.nextLong(), n3, n4, chunkPrimer, d, d2, d3, f3, f, f2, 0, 0, 3.0);
         }
      }
   }
}
