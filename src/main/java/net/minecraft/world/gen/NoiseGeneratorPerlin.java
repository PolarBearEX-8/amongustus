package net.minecraft.world.gen;

import java.util.Random;

public class NoiseGeneratorPerlin extends NoiseGenerator {
   private NoiseGeneratorSimplex[] noiseLevels;
   private int levels;

   public double[] getRegion(double[] doubles, double d, double d2, int n, int n2, double d3, double d4, double d5) {
      return this.getRegion(doubles, d, d2, n, n2, d3, d4, d5, 0.5);
   }

   public double getValue(double d, double d2) {
      double d3 = 0.0;
      double d4 = 1.0;

      for (int i = 0; i < this.levels; i++) {
         d3 += this.noiseLevels[i].getValue(d * d4, d2 * d4) / d4;
         d4 /= 2.0;
      }

      return d3;
   }

   public NoiseGeneratorPerlin(Random rand, int n) {
      this.levels = n;
      this.noiseLevels = new NoiseGeneratorSimplex[n];

      for (int i = 0; i < n; i++) {
         this.noiseLevels[i] = new NoiseGeneratorSimplex(rand);
      }
   }

   public double[] getRegion(double[] doubles, double d, double d2, int n, int n2, double d3, double d4, double d5, double d6) {
      if (doubles != null && doubles.length >= n * n2) {
         for (int i = 0; i < doubles.length; i++) {
            doubles[i] = 0.0;
         }
      } else {
         doubles = new double[n * n2];
      }

      double d7 = 1.0;
      double d8 = 1.0;

      for (int j = 0; j < this.levels; j++) {
         this.noiseLevels[j].add(doubles, d, d2, n, n2, d3 * d8 * d7, d4 * d8 * d7, 0.55 / d7);
         d8 *= d5;
         d7 *= d6;
      }

      return doubles;
   }
}
