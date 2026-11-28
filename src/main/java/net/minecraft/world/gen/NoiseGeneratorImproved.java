package net.minecraft.world.gen;

import java.util.Random;

public class NoiseGeneratorImproved extends NoiseGenerator {
   private int[] permutations = new int[512];
   public double xCoord;
   public double yCoord;
   public double zCoord;
   private static final double[] GRAD_X = new double[]{1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0};
   private static final double[] GRAD_Y = new double[]{1.0, 1.0, -1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0};
   private static final double[] GRAD_Z = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 0.0, 1.0, 0.0, -1.0};
   private static final double[] GRAD_2X = new double[]{1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 1.0, -1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, -1.0, 0.0};
   private static final double[] GRAD_2Z = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, -1.0, -1.0, 1.0, 1.0, -1.0, -1.0, 0.0, 1.0, 0.0, -1.0};

   public NoiseGeneratorImproved() {
      this(new Random());
   }

   public void populateNoiseArray(
      double[] doubles, double d, double d2, double d3, int n, int n2, int n3, double d4, double d5, double d6, double d7
   ) {
      if (n2 == 1) {
         int n4 = 0;
         int n5 = 0;
         int n6 = 0;
         int n7 = 0;
         double d8 = 0.0;
         double d9 = 0.0;
         int n8 = 0;
         double d10 = 1.0 / d7;

         for (int i = 0; i < n; i++) {
            double d11 = d + (double)i * d4 + this.xCoord;
            int n9 = (int)d11;
            if (d11 < (double)n9) {
               n9--;
            }

            int n10 = n9 & 0xFF;
            d11 -= (double)n9;
            double d12 = d11 * d11 * d11 * (d11 * (d11 * 6.0 - 15.0) + 10.0);

            for (int j = 0; j < n3; j++) {
               double d13 = d3 + (double)j * d6 + this.zCoord;
               int n11 = (int)d13;
               if (d13 < (double)n11) {
                  n11--;
               }

               int n12 = n11 & 0xFF;
               d13 -= (double)n11;
               double d14 = d13 * d13 * d13 * (d13 * (d13 * 6.0 - 15.0) + 10.0);
               n4 = this.permutations[n10] + 0;
               n5 = this.permutations[n4] + n12;
               n6 = this.permutations[n10 + 1] + 0;
               n7 = this.permutations[n6] + n12;
               d8 = this.lerp(d12, this.grad2(this.permutations[n5], d11, d13), this.grad(this.permutations[n7], d11 - 1.0, 0.0, d13));
               d9 = this.lerp(
                  d12,
                  this.grad(this.permutations[n5 + 1], d11, 0.0, d13 - 1.0),
                  this.grad(this.permutations[n7 + 1], d11 - 1.0, 0.0, d13 - 1.0)
               );
               double d15 = this.lerp(d14, d8, d9);
               int n13 = n8++;
               doubles[n13] += d15 * d10;
            }
         }
      } else {
         int n14 = 0;
         double d16 = 1.0 / d7;
         int n15 = -1;
         int n16 = 0;
         int n17 = 0;
         int n18 = 0;
         int n19 = 0;
         int n20 = 0;
         int n21 = 0;
         double d17 = 0.0;
         double d18 = 0.0;
         double d19 = 0.0;
         double d20 = 0.0;

         for (int k = 0; k < n; k++) {
            double d21 = d + (double)k * d4 + this.xCoord;
            int n22 = (int)d21;
            if (d21 < (double)n22) {
               n22--;
            }

            int n23 = n22 & 0xFF;
            d21 -= (double)n22;
            double d22 = d21 * d21 * d21 * (d21 * (d21 * 6.0 - 15.0) + 10.0);

            for (int m = 0; m < n3; m++) {
               double d23 = d3 + (double)m * d6 + this.zCoord;
               int n24 = (int)d23;
               if (d23 < (double)n24) {
                  n24--;
               }

               int n25 = n24 & 0xFF;
               d23 -= (double)n24;
               double d24 = d23 * d23 * d23 * (d23 * (d23 * 6.0 - 15.0) + 10.0);

               for (int idx = 0; idx < n2; idx++) {
                  double d25 = d2 + (double)idx * d5 + this.yCoord;
                  int n26 = (int)d25;
                  if (d25 < (double)n26) {
                     n26--;
                  }

                  int n27 = n26 & 0xFF;
                  d25 -= (double)n26;
                  double d26 = d25 * d25 * d25 * (d25 * (d25 * 6.0 - 15.0) + 10.0);
                  if (idx == 0 || n27 != n15) {
                     n15 = n27;
                     n16 = this.permutations[n23] + n27;
                     n17 = this.permutations[n16] + n25;
                     n18 = this.permutations[n16 + 1] + n25;
                     n19 = this.permutations[n23 + 1] + n27;
                     n20 = this.permutations[n19] + n25;
                     n21 = this.permutations[n19 + 1] + n25;
                     d17 = this.lerp(
                        d22, this.grad(this.permutations[n17], d21, d25, d23), this.grad(this.permutations[n20], d21 - 1.0, d25, d23)
                     );
                     d18 = this.lerp(
                        d22,
                        this.grad(this.permutations[n18], d21, d25 - 1.0, d23),
                        this.grad(this.permutations[n21], d21 - 1.0, d25 - 1.0, d23)
                     );
                     d19 = this.lerp(
                        d22,
                        this.grad(this.permutations[n17 + 1], d21, d25, d23 - 1.0),
                        this.grad(this.permutations[n20 + 1], d21 - 1.0, d25, d23 - 1.0)
                     );
                     d20 = this.lerp(
                        d22,
                        this.grad(this.permutations[n18 + 1], d21, d25 - 1.0, d23 - 1.0),
                        this.grad(this.permutations[n21 + 1], d21 - 1.0, d25 - 1.0, d23 - 1.0)
                     );
                  }

                  double d29 = this.lerp(d24, this.lerp(d26, d17, d18), this.lerp(d26, d19, d20));
                  int n28 = n14++;
                  doubles[n28] += d29 * d16;
               }
            }
         }
      }
   }

   public final double grad(int n, double d, double d2, double d3) {
      int n2 = n & 15;
      return GRAD_X[n2] * d + GRAD_Y[n2] * d2 + GRAD_Z[n2] * d3;
   }

   public final double lerp(double d, double d2, double d3) {
      return d2 + d * (d3 - d2);
   }

   public NoiseGeneratorImproved(Random rand) {
      this.xCoord = rand.nextDouble() * 256.0;
      this.yCoord = rand.nextDouble() * 256.0;
      this.zCoord = rand.nextDouble() * 256.0;
      int n = 0;

      while (n < 256) {
         this.permutations[n] = n++;
      }

      for (int i = 0; i < 256; i++) {
         int n2 = rand.nextInt(256 - i) + i;
         int n3 = this.permutations[i];
         this.permutations[i] = this.permutations[n2];
         this.permutations[n2] = n3;
         this.permutations[i + 256] = this.permutations[i];
      }
   }

   public final double grad2(int n, double d, double d2) {
      int n2 = n & 15;
      return GRAD_2X[n2] * d + GRAD_2Z[n2] * d2;
   }
}
