package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenLayerHills extends GenLayer {
   private static final Logger LOGGER = LogManager.getLogger();
   private GenLayer riverLayer;

   public GenLayerHills(long l, GenLayer genLayer, GenLayer genLayer2) {
      super(l);
      this.parent = genLayer;
      this.riverLayer = genLayer2;
   }

   @Override
   public int[] getInts(int n, int n2, int n3, int n4) {
      int[] ints = this.parent.getInts(n - 1, n2 - 1, n3 + 2, n4 + 2);
      int[] ints2 = this.riverLayer.getInts(n - 1, n2 - 1, n3 + 2, n4 + 2);
      int[] intCache = IntCache.getIntCache(n3 * n4);

      for (int i = 0; i < n4; i++) {
         for (int j = 0; j < n3; j++) {
            this.initChunkSeed((long)(j + n), (long)(i + n2));
            int n5 = ints[j + 1 + (i + 1) * (n3 + 2)];
            int n6 = ints2[j + 1 + (i + 1) * (n3 + 2)];
            boolean flag = (n6 - 2) % 29 == 0;
            if (n5 > 255) {
               LOGGER.debug("old! " + n5);
            }

            if (n5 != 0 && n6 >= 2 && (n6 - 2) % 29 == 1 && n5 < 128) {
               if (BiomeGenBase.getBiome(n5 + 128) != null) {
                  intCache[j + i * n3] = n5 + 128;
               } else {
                  intCache[j + i * n3] = n5;
               }
            } else if (this.nextInt(3) != 0 && !flag) {
               intCache[j + i * n3] = n5;
            } else {
               int n7 = n5;
               if (n5 == BiomeGenBase.desert.biomeID) {
                  n7 = BiomeGenBase.desertHills.biomeID;
               } else if (n5 == BiomeGenBase.forest.biomeID) {
                  n7 = BiomeGenBase.forestHills.biomeID;
               } else if (n5 == BiomeGenBase.birchForest.biomeID) {
                  n7 = BiomeGenBase.birchForestHills.biomeID;
               } else if (n5 == BiomeGenBase.roofedForest.biomeID) {
                  n7 = BiomeGenBase.plains.biomeID;
               } else if (n5 == BiomeGenBase.taiga.biomeID) {
                  n7 = BiomeGenBase.taigaHills.biomeID;
               } else if (n5 == BiomeGenBase.megaTaiga.biomeID) {
                  n7 = BiomeGenBase.megaTaigaHills.biomeID;
               } else if (n5 == BiomeGenBase.coldTaiga.biomeID) {
                  n7 = BiomeGenBase.coldTaigaHills.biomeID;
               } else if (n5 == BiomeGenBase.plains.biomeID) {
                  if (this.nextInt(3) == 0) {
                     n7 = BiomeGenBase.forestHills.biomeID;
                  } else {
                     n7 = BiomeGenBase.forest.biomeID;
                  }
               } else if (n5 == BiomeGenBase.icePlains.biomeID) {
                  n7 = BiomeGenBase.iceMountains.biomeID;
               } else if (n5 == BiomeGenBase.jungle.biomeID) {
                  n7 = BiomeGenBase.jungleHills.biomeID;
               } else if (n5 == BiomeGenBase.ocean.biomeID) {
                  n7 = BiomeGenBase.deepOcean.biomeID;
               } else if (n5 == BiomeGenBase.extremeHills.biomeID) {
                  n7 = BiomeGenBase.extremeHillsPlus.biomeID;
               } else if (n5 == BiomeGenBase.savanna.biomeID) {
                  n7 = BiomeGenBase.savannaPlateau.biomeID;
               } else if (biomesEqualOrMesaPlateau(n5, BiomeGenBase.mesaPlateau_F.biomeID)) {
                  n7 = BiomeGenBase.mesa.biomeID;
               } else if (n5 == BiomeGenBase.deepOcean.biomeID && this.nextInt(3) == 0) {
                  if (this.nextInt(2) == 0) {
                     n7 = BiomeGenBase.plains.biomeID;
                  } else {
                     n7 = BiomeGenBase.forest.biomeID;
                  }
               }

               if (flag && n7 != n5) {
                  if (BiomeGenBase.getBiome(n7 + 128) != null) {
                     n7 += 128;
                  } else {
                     n7 = n5;
                  }
               }

               if (n7 == n5) {
                  intCache[j + i * n3] = n5;
               } else {
                  int n9 = ints[j + 1 + (i + 1 - 1) * (n3 + 2)];
                  int n10 = ints[j + 1 + 1 + (i + 1) * (n3 + 2)];
                  int n11 = ints[j + 1 - 1 + (i + 1) * (n3 + 2)];
                  int n12 = ints[j + 1 + (i + 1 + 1) * (n3 + 2)];
                  int n13 = 0;
                  if (biomesEqualOrMesaPlateau(n9, n5)) {
                     n13++;
                  }

                  if (biomesEqualOrMesaPlateau(n10, n5)) {
                     n13++;
                  }

                  if (biomesEqualOrMesaPlateau(n11, n5)) {
                     n13++;
                  }

                  if (biomesEqualOrMesaPlateau(n12, n5)) {
                     n13++;
                  }

                  if (n13 >= 3) {
                     intCache[j + i * n3] = n7;
                  } else {
                     intCache[j + i * n3] = n5;
                  }
               }
            }
         }
      }

      return intCache;
   }
}
