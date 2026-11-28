package net.raphimc.vialegacy.protocol.release.r1_1tor1_2_1_3.biome.beta;

import java.util.Random;

public class NoiseGenerator2 {
   private static final int[][] GRADIENTS = new int[][]{
      {1, 1, 0}, {-1, 1, 0}, {1, -1, 0}, {-1, -1, 0}, {1, 0, 1}, {-1, 0, 1}, {1, 0, -1}, {-1, 0, -1}, {0, 1, 1}, {0, -1, 1}, {0, 1, -1}, {0, -1, -1}
   };
   private final int[] permutations = new int[512];
   public double xo;
   public double yo;
   public double zo;
   private static final double F2 = 0.5 * (Math.sqrt(3.0) - 1.0);
   private static final double G2 = (3.0 - Math.sqrt(3.0)) / 6.0;

   public NoiseGenerator2() {
      this(new Random());
   }

   public NoiseGenerator2(Random random) {
      this.xo = random.nextDouble() * 256.0;
      this.yo = random.nextDouble() * 256.0;
      this.zo = random.nextDouble() * 256.0;
      int i = 0;

      while (i < 256) {
         this.permutations[i] = i++;
      }

      for (int j = 0; j < 256; j++) {
         int k = random.nextInt(256 - j) + j;
         int l = this.permutations[j];
         this.permutations[j] = this.permutations[k];
         this.permutations[k] = l;
         this.permutations[j + 256] = this.permutations[j];
      }
   }

   private static int wrap(double d) {
      return d <= 0.0 ? (int)d - 1 : (int)d;
   }

   private static double dot(int[] ai, double d, double d1) {
      return (double)ai[0] * d + (double)ai[1] * d1;
   }

   public void addNoise(double[] ad, double d, double d1, int i, int j, double d2, double d3, double d4) {
      int k = 0;

      for (int l = 0; l < i; l++) {
         double d5 = (d + (double)l) * d2 + this.xo;

         for (int i1 = 0; i1 < j; i1++) {
            double d6 = (d1 + (double)i1) * d3 + this.yo;
            double d10 = (d5 + d6) * F2;
            int j1 = wrap(d5 + d10);
            int k1 = wrap(d6 + d10);
            double d11 = (double)(j1 + k1) * G2;
            double d12 = (double)j1 - d11;
            double d13 = (double)k1 - d11;
            double d14 = d5 - d12;
            double d15 = d6 - d13;
            int l1;
            int i2;
            if (d14 > d15) {
               l1 = 1;
               i2 = 0;
            } else {
               l1 = 0;
               i2 = 1;
            }

            double d16 = d14 - (double)l1 + G2;
            double d17 = d15 - (double)i2 + G2;
            double d18 = d14 - 1.0 + 2.0 * G2;
            double d19 = d15 - 1.0 + 2.0 * G2;
            int j2 = j1 & 0xFF;
            int k2 = k1 & 0xFF;
            int l2 = this.permutations[j2 + this.permutations[k2]] % 12;
            int i3 = this.permutations[j2 + l1 + this.permutations[k2 + i2]] % 12;
            int j3 = this.permutations[j2 + 1 + this.permutations[k2 + 1]] % 12;
            double d20 = 0.5 - d14 * d14 - d15 * d15;
            double d7;
            if (d20 < 0.0) {
               d7 = 0.0;
            } else {
               d20 *= d20;
               d7 = d20 * d20 * dot(GRADIENTS[l2], d14, d15);
            }

            double d21 = 0.5 - d16 * d16 - d17 * d17;
            double d8;
            if (d21 < 0.0) {
               d8 = 0.0;
            } else {
               d21 *= d21;
               d8 = d21 * d21 * dot(GRADIENTS[i3], d16, d17);
            }

            double d22 = 0.5 - d18 * d18 - d19 * d19;
            double d9;
            if (d22 < 0.0) {
               d9 = 0.0;
            } else {
               d22 *= d22;
               d9 = d22 * d22 * dot(GRADIENTS[j3], d18, d19);
            }

            ad[k++] += 70.0 * (d7 + d8 + d9) * d4;
         }
      }
   }
}
