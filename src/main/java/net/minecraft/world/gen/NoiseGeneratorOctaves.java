package net.minecraft.world.gen;

import java.util.Random;
import net.minecraft.util.MathHelper;

public class NoiseGeneratorOctaves extends NoiseGenerator {
   private NoiseGeneratorImproved[] generatorCollection;
   private int octaves;

   public double[] generateNoiseOctaves(double[] doubles, int n, int n2, int n3, int n4, double d, double d2, double d3) {
      return this.generateNoiseOctaves(doubles, n, 10, n2, n3, 1, n4, d, 1.0, d2);
   }

   public double[] generateNoiseOctaves(double[] doubles, int n, int n2, int n3, int n4, int n5, int n6, double d, double d2, double d3) {
      if (doubles == null) {
         doubles = new double[n4 * n5 * n6];
      } else {
         for (int i = 0; i < doubles.length; i++) {
            doubles[i] = 0.0;
         }
      }

      double d4 = 1.0;

      for (int j = 0; j < this.octaves; j++) {
         double d5 = (double)n * d4 * d;
         double d6 = (double)n2 * d4 * d2;
         double d7 = (double)n3 * d4 * d3;
         long l = MathHelper.floor_double_long(d5);
         long l2 = MathHelper.floor_double_long(d7);
         d5 -= (double)l;
         d7 -= (double)l2;
         l %= 16777216L;
         l2 %= 16777216L;
         d5 += (double)l;
         d7 += (double)l2;
         this.generatorCollection[j].populateNoiseArray(doubles, d5, d6, d7, n4, n5, n6, d * d4, d2 * d4, d3 * d4, d4);
         d4 /= 2.0;
      }

      return doubles;
   }

   public NoiseGeneratorOctaves(Random rand, int n) {
      this.octaves = n;
      this.generatorCollection = new NoiseGeneratorImproved[n];

      for (int i = 0; i < n; i++) {
         this.generatorCollection[i] = new NoiseGeneratorImproved(rand);
      }
   }
}
