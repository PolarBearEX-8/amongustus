package net.minecraft.world.gen;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class MapGenCavesHell extends MapGenBase {
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
      double d5 = (double)(n * 16 + 8);
      double d6 = (double)(n2 * 16 + 8);
      float f4 = 0.0F;
      float f5 = 0.0F;
      Random rand2 = new Random(l);
      if (n4 <= 0) {
         int n5 = this.range * 16 - 16;
         n4 = n5 - rand2.nextInt(n5 / 4);
      }

      boolean flag = false;
      if (n3 == -1) {
         n3 = n4 / 2;
         flag = true;
      }

      int n6 = rand2.nextInt(n4 / 2) + n4 / 4;

      for (boolean i = rand2.nextInt(6) == 0; n3 < n4; n3++) {
         double d7 = 1.5 + (double)(MathHelper.sin((float)n3 * (float) Math.PI / (float)n4) * f * 1.0F);
         double d8 = d7 * d4;
         float f6 = MathHelper.cos(f3);
         float f7 = MathHelper.sin(f3);
         d += (double)(MathHelper.cos(f2) * f6);
         d2 += (double)f7;
         d3 += (double)(MathHelper.sin(f2) * f6);
         if (i) {
            f3 *= 0.92F;
         } else {
            f3 *= 0.7F;
         }

         f3 += f5 * 0.1F;
         f2 += f4 * 0.1F;
         f5 *= 0.9F;
         f4 *= 0.75F;
         f5 += (rand2.nextFloat() - rand2.nextFloat()) * rand2.nextFloat() * 2.0F;
         f4 += (rand2.nextFloat() - rand2.nextFloat()) * rand2.nextFloat() * 4.0F;
         if (!flag && n3 == n6 && f > 1.0F) {
            this.addTunnel(
               rand2.nextLong(),
               n,
               n2,
               chunkPrimer,
               d,
               d2,
               d3,
               rand2.nextFloat() * 0.5F + 0.5F,
               f2 - (float) (Math.PI / 2),
               f3 / 3.0F,
               n3,
               n4,
               1.0
            );
            this.addTunnel(
               rand2.nextLong(),
               n,
               n2,
               chunkPrimer,
               d,
               d2,
               d3,
               rand2.nextFloat() * 0.5F + 0.5F,
               f2 + (float) (Math.PI / 2),
               f3 / 3.0F,
               n3,
               n4,
               1.0
            );
            return;
         }

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
               int n7 = MathHelper.floor_double(d - d7) - n * 16 - 1;
               int n8 = MathHelper.floor_double(d + d7) - n * 16 + 1;
               int n9 = MathHelper.floor_double(d2 - d8) - 1;
               int n10 = MathHelper.floor_double(d2 + d8) + 1;
               int n11 = MathHelper.floor_double(d3 - d7) - n2 * 16 - 1;
               int n12 = MathHelper.floor_double(d3 + d7) - n2 * 16 + 1;
               if (n7 < 0) {
                  n7 = 0;
               }

               if (n8 > 16) {
                  n8 = 16;
               }

               if (n9 < 1) {
                  n9 = 1;
               }

               if (n10 > 120) {
                  n10 = 120;
               }

               if (n11 < 0) {
                  n11 = 0;
               }

               if (n12 > 16) {
                  n12 = 16;
               }

               boolean flag2 = false;

               for (int j = n7; !flag2 && j < n8; j++) {
                  for (int k = n11; !flag2 && k < n12; k++) {
                     for (int m = n10 + 1; !flag2 && m >= n9 - 1; m--) {
                        if (m >= 0 && m < 128) {
                           IBlockState blockState = chunkPrimer.getBlockState(j, m, k);
                           if (blockState.getBlock() == Blocks.flowing_lava || blockState.getBlock() == Blocks.lava) {
                              flag2 = true;
                           }

                           if (m != n9 - 1 && j != n7 && j != n8 - 1 && k != n11 && k != n12 - 1) {
                              m = n9;
                           }
                        }
                     }
                  }
               }

               if (!flag2) {
                  for (int idx = n7; idx < n8; idx++) {
                     double d13 = ((double)(idx + n * 16) + 0.5 - d) / d7;

                     for (int index = n11; index < n12; index++) {
                        double d14 = ((double)(index + n2 * 16) + 0.5 - d3) / d7;

                        for (int i2 = n10; i2 > n9; i2--) {
                           double d15 = ((double)(i2 - 1) + 0.5 - d2) / d8;
                           if (d15 > -0.7 && d13 * d13 + d15 * d15 + d14 * d14 < 1.0) {
                              IBlockState blockState2 = chunkPrimer.getBlockState(idx, i2, index);
                              if (blockState2.getBlock() == Blocks.netherrack || blockState2.getBlock() == Blocks.dirt || blockState2.getBlock() == Blocks.grass) {
                                 chunkPrimer.setBlockState(idx, i2, index, Blocks.air.getDefaultState());
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

   public void addRoom(long l, int n, int n2, ChunkPrimer chunkPrimer, double d, double d2, double d3) {
      this.addTunnel(l, n, n2, chunkPrimer, d, d2, d3, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5);
   }

   @Override
   public void recursiveGenerate(World world, int n, int n2, int n3, int n4, ChunkPrimer chunkPrimer) {
      int n5 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(10) + 1) + 1);
      if (this.rand.nextInt(5) != 0) {
         n5 = 0;
      }

      for (int i = 0; i < n5; i++) {
         double d = (double)(n * 16 + this.rand.nextInt(16));
         double d2 = (double)this.rand.nextInt(128);
         double d3 = (double)(n2 * 16 + this.rand.nextInt(16));
         int n6 = 1;
         if (this.rand.nextInt(4) == 0) {
            this.addRoom(this.rand.nextLong(), n3, n4, chunkPrimer, d, d2, d3);
            n6 += this.rand.nextInt(4);
         }

         for (int j = 0; j < n6; j++) {
            float f = this.rand.nextFloat() * (float) Math.PI * 2.0F;
            float f2 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
            float f3 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
            this.addTunnel(this.rand.nextLong(), n3, n4, chunkPrimer, d, d2, d3, f3 * 2.0F, f, f2, 0, 0, 0.5);
         }
      }
   }
}
