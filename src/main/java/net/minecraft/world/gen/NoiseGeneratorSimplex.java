package net.minecraft.world.gen;

import java.util.Random;

public class NoiseGeneratorSimplex {
   private static int[][] GRAD3 = new int[][]{
      {1, 1, 0}, {-1, 1, 0}, {1, -1, 0}, {-1, -1, 0}, {1, 0, 1}, {-1, 0, 1}, {1, 0, -1}, {-1, 0, -1}, {0, 1, 1}, {0, -1, 1}, {0, 1, -1}, {0, -1, -1}
   };
   public static final double SQRT_3 = Math.sqrt(3.0);
   private int[] permutations = new int[512];
   public double xo;
   public double yo;
   public double zo;
   private static final double F2 = 0.5 * (SQRT_3 - 1.0);
   private static final double G2 = (3.0 - SQRT_3) / 6.0;

   private static double dot(int[] ints, double d, double d2) {
      return (double)ints[0] * d + (double)ints[1] * d2;
   }

   private static int fastFloor(double d) {
      return d > 0.0 ? (int)d : (int)d - 1;
   }

   public NoiseGeneratorSimplex(Random rand) {
      this.xo = rand.nextDouble() * 256.0;
      this.yo = rand.nextDouble() * 256.0;
      this.zo = rand.nextDouble() * 256.0;
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

   public NoiseGeneratorSimplex() {
      this(new Random());
   }

   public double getValue(double d, double d2) {
      double d3 = 0.5 * (SQRT_3 - 1.0);
      double d4 = (d + d2) * d3;
      int n = fastFloor(d + d4);
      int n2 = fastFloor(d2 + d4);
      double d5 = (3.0 - SQRT_3) / 6.0;
      double d6 = (double)(n + n2) * d5;
      double d7 = (double)n - d6;
      double d8 = (double)n2 - d6;
      double d9 = d - d7;
      double d10 = d2 - d8;
      byte b;
      byte b2;
      if (d9 > d10) {
         b = 1;
         b2 = 0;
      } else {
         b = 0;
         b2 = 1;
      }

      double d11 = d9 - (double)b + d5;
      double d12 = d10 - (double)b2 + d5;
      double d13 = d9 - 1.0 + 2.0 * d5;
      double d14 = d10 - 1.0 + 2.0 * d5;
      int n3 = n & 0xFF;
      int n4 = n2 & 0xFF;
      int n5 = this.permutations[n3 + this.permutations[n4]] % 12;
      int n6 = this.permutations[n3 + b + this.permutations[n4 + b2]] % 12;
      int n7 = this.permutations[n3 + 1 + this.permutations[n4 + 1]] % 12;
      double d15 = 0.5 - d9 * d9 - d10 * d10;
      double d16;
      if (d15 < 0.0) {
         d16 = 0.0;
      } else {
         d15 *= d15;
         d16 = d15 * d15 * dot(GRAD3[n5], d9, d10);
      }

      double d17 = 0.5 - d11 * d11 - d12 * d12;
      double d18;
      if (d17 < 0.0) {
         d18 = 0.0;
      } else {
         d17 *= d17;
         d18 = d17 * d17 * dot(GRAD3[n6], d11, d12);
      }

      double d19 = 0.5 - d13 * d13 - d14 * d14;
      double d20;
      if (d19 < 0.0) {
         d20 = 0.0;
      } else {
         d19 *= d19;
         d20 = d19 * d19 * dot(GRAD3[n7], d13, d14);
      }

      return 70.0 * (d16 + d18 + d20);
   }

   public void add(double[] doubles, double d, double d2, int n, int n2, double d3, double d4, double d5) {
      int n3 = 0;

      for (int i = 0; i < n2; i++) {
         double d6 = (d2 + (double)i) * d4 + this.yo;

         for (int j = 0; j < n; j++) {
            double d7 = (d + (double)j) * d3 + this.xo;
            double d8 = (d7 + d6) * F2;
            int n4 = fastFloor(d7 + d8);
            int n5 = fastFloor(d6 + d8);
            double d9 = (double)(n4 + n5) * G2;
            double d10 = (double)n4 - d9;
            double d11 = (double)n5 - d9;
            double d12 = d7 - d10;
            double d13 = d6 - d11;
            byte b;
            byte b2;
            if (d12 > d13) {
               b = 1;
               b2 = 0;
            } else {
               b = 0;
               b2 = 1;
            }

            double d14 = d12 - (double)b + G2;
            double d15 = d13 - (double)b2 + G2;
            double d16 = d12 - 1.0 + 2.0 * G2;
            double d17 = d13 - 1.0 + 2.0 * G2;
            int n6 = n4 & 0xFF;
            int n7 = n5 & 0xFF;
            int n8 = this.permutations[n6 + this.permutations[n7]] % 12;
            int n9 = this.permutations[n6 + b + this.permutations[n7 + b2]] % 12;
            int n10 = this.permutations[n6 + 1 + this.permutations[n7 + 1]] % 12;
            double d18 = 0.5 - d12 * d12 - d13 * d13;
            double d19;
            if (d18 < 0.0) {
               d19 = 0.0;
            } else {
               d18 *= d18;
               d19 = d18 * d18 * dot(GRAD3[n8], d12, d13);
            }

            double d20 = 0.5 - d14 * d14 - d15 * d15;
            double d21;
            if (d20 < 0.0) {
               d21 = 0.0;
            } else {
               d20 *= d20;
               d21 = d20 * d20 * dot(GRAD3[n9], d14, d15);
            }

            double d22 = 0.5 - d16 * d16 - d17 * d17;
            double d23;
            if (d22 < 0.0) {
               d23 = 0.0;
            } else {
               d22 *= d22;
               d23 = d22 * d22 * dot(GRAD3[n10], d16, d17);
            }

            int n11 = n3++;
            doubles[n11] += 70.0 * (d19 + d21 + d23) * d5;
         }
      }
   }
}
