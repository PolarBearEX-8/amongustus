package net.raphimc.vialegacy.protocol.release.r1_1tor1_2_1_3.biome.beta;

import java.util.Random;

public class NoiseGeneratorOctaves2 {
   private final NoiseGenerator2[] generatorCollection;
   private final int octaves;

   public NoiseGeneratorOctaves2(Random random, int i) {
      this.octaves = i;
      this.generatorCollection = new NoiseGenerator2[i];

      for (int j = 0; j < i; j++) {
         this.generatorCollection[j] = new NoiseGenerator2(random);
      }
   }

   public double[] generateNoiseOctaves(double[] ad, double d, double d1, int i, int j, double d2, double d3, double d4) {
      return this.generateNoiseOctaves(ad, d, d1, i, j, d2, d3, d4, 0.5);
   }

   public double[] generateNoiseOctaves(double[] ad, double d, double d1, int i, int j, double d2, double d3, double d4, double d5) {
      d2 /= 1.5;
      d3 /= 1.5;
      if (ad != null && ad.length >= i * j) {
         for (int k = 0; k < ad.length; k++) {
            ad[k] = 0.0;
         }
      } else {
         ad = new double[i * j];
      }

      double d6 = 1.0;
      double d7 = 1.0;

      for (int l = 0; l < this.octaves; l++) {
         this.generatorCollection[l].addNoise(ad, d, d1, i, j, d2 * d7, d3 * d7, 0.55 / d6);
         d7 *= d4;
         d6 *= d5;
      }

      return ad;
   }
}
