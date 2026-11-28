package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerBiomeEdge extends GenLayer {
   public GenLayerBiomeEdge(long l, GenLayer genLayer) {
      super(l);
      this.parent = genLayer;
   }

   @Override
   public int[] getInts(int n, int n2, int n3, int n4) {
      int[] ints = this.parent.getInts(n - 1, n2 - 1, n3 + 2, n4 + 2);
      int[] intCache = IntCache.getIntCache(n3 * n4);

      for (int i = 0; i < n4; i++) {
         for (int j = 0; j < n3; j++) {
            this.initChunkSeed((long)(j + n), (long)(i + n2));
            int n5 = ints[j + 1 + (i + 1) * (n3 + 2)];
            if (!this.replaceBiomeEdgeIfNecessary(ints, intCache, j, i, n3, n5, BiomeGenBase.extremeHills.biomeID, BiomeGenBase.extremeHillsEdge.biomeID)
               && !this.replaceBiomeEdge(ints, intCache, j, i, n3, n5, BiomeGenBase.mesaPlateau_F.biomeID, BiomeGenBase.mesa.biomeID)
               && !this.replaceBiomeEdge(ints, intCache, j, i, n3, n5, BiomeGenBase.mesaPlateau.biomeID, BiomeGenBase.mesa.biomeID)
               && !this.replaceBiomeEdge(ints, intCache, j, i, n3, n5, BiomeGenBase.megaTaiga.biomeID, BiomeGenBase.taiga.biomeID)) {
               if (n5 == BiomeGenBase.desert.biomeID) {
                  int n6 = ints[j + 1 + (i + 1 - 1) * (n3 + 2)];
                  int n7 = ints[j + 1 + 1 + (i + 1) * (n3 + 2)];
                  int n8 = ints[j + 1 - 1 + (i + 1) * (n3 + 2)];
                  int n9 = ints[j + 1 + (i + 1 + 1) * (n3 + 2)];
                  if (n6 != BiomeGenBase.icePlains.biomeID
                     && n7 != BiomeGenBase.icePlains.biomeID
                     && n8 != BiomeGenBase.icePlains.biomeID
                     && n9 != BiomeGenBase.icePlains.biomeID) {
                     intCache[j + i * n3] = n5;
                  } else {
                     intCache[j + i * n3] = BiomeGenBase.extremeHillsPlus.biomeID;
                  }
               } else if (n5 == BiomeGenBase.swampland.biomeID) {
                  int n10 = ints[j + 1 + (i + 1 - 1) * (n3 + 2)];
                  int n11 = ints[j + 1 + 1 + (i + 1) * (n3 + 2)];
                  int n12 = ints[j + 1 - 1 + (i + 1) * (n3 + 2)];
                  int n13 = ints[j + 1 + (i + 1 + 1) * (n3 + 2)];
                  if (n10 == BiomeGenBase.desert.biomeID
                     || n11 == BiomeGenBase.desert.biomeID
                     || n12 == BiomeGenBase.desert.biomeID
                     || n13 == BiomeGenBase.desert.biomeID
                     || n10 == BiomeGenBase.coldTaiga.biomeID
                     || n11 == BiomeGenBase.coldTaiga.biomeID
                     || n12 == BiomeGenBase.coldTaiga.biomeID
                     || n13 == BiomeGenBase.coldTaiga.biomeID
                     || n10 == BiomeGenBase.icePlains.biomeID
                     || n11 == BiomeGenBase.icePlains.biomeID
                     || n12 == BiomeGenBase.icePlains.biomeID
                     || n13 == BiomeGenBase.icePlains.biomeID) {
                     intCache[j + i * n3] = BiomeGenBase.plains.biomeID;
                  } else if (n10 != BiomeGenBase.jungle.biomeID
                     && n13 != BiomeGenBase.jungle.biomeID
                     && n11 != BiomeGenBase.jungle.biomeID
                     && n12 != BiomeGenBase.jungle.biomeID) {
                     intCache[j + i * n3] = n5;
                  } else {
                     intCache[j + i * n3] = BiomeGenBase.jungleEdge.biomeID;
                  }
               } else {
                  intCache[j + i * n3] = n5;
               }
            }
         }
      }

      return intCache;
   }

   private boolean canBiomesBeNeighbors(int n, int n2) {
      if (biomesEqualOrMesaPlateau(n, n2)) {
         return true;
      } else {
         BiomeGenBase biome = BiomeGenBase.getBiome(n);
         BiomeGenBase biome2 = BiomeGenBase.getBiome(n2);
         if (biome != null && biome2 != null) {
            BiomeGenBase.TempCategory tempCategory = biome.getTempCategory();
            BiomeGenBase.TempCategory tempCategory2 = biome2.getTempCategory();
            return tempCategory == tempCategory2 || tempCategory == BiomeGenBase.TempCategory.MEDIUM || tempCategory2 == BiomeGenBase.TempCategory.MEDIUM;
         } else {
            return false;
         }
      }
   }

   private boolean replaceBiomeEdgeIfNecessary(int[] ints, int[] ints2, int n, int n2, int n3, int n4, int n5, int n6) {
      if (!biomesEqualOrMesaPlateau(n4, n5)) {
         return false;
      } else {
         int n7 = ints[n + 1 + (n2 + 1 - 1) * (n3 + 2)];
         int n8 = ints[n + 1 + 1 + (n2 + 1) * (n3 + 2)];
         int n9 = ints[n + 1 - 1 + (n2 + 1) * (n3 + 2)];
         int n10 = ints[n + 1 + (n2 + 1 + 1) * (n3 + 2)];
         if (this.canBiomesBeNeighbors(n7, n5)
            && this.canBiomesBeNeighbors(n8, n5)
            && this.canBiomesBeNeighbors(n9, n5)
            && this.canBiomesBeNeighbors(n10, n5)) {
            ints2[n + n2 * n3] = n4;
         } else {
            ints2[n + n2 * n3] = n6;
         }

         return true;
      }
   }

   private boolean replaceBiomeEdge(int[] ints, int[] ints2, int n, int n2, int n3, int n4, int n5, int n6) {
      if (n4 != n5) {
         return false;
      } else {
         int n7 = ints[n + 1 + (n2 + 1 - 1) * (n3 + 2)];
         int n8 = ints[n + 1 + 1 + (n2 + 1) * (n3 + 2)];
         int n9 = ints[n + 1 - 1 + (n2 + 1) * (n3 + 2)];
         int n10 = ints[n + 1 + (n2 + 1 + 1) * (n3 + 2)];
         if (biomesEqualOrMesaPlateau(n7, n5)
            && biomesEqualOrMesaPlateau(n8, n5)
            && biomesEqualOrMesaPlateau(n9, n5)
            && biomesEqualOrMesaPlateau(n10, n5)) {
            ints2[n + n2 * n3] = n4;
         } else {
            ints2[n + n2 * n3] = n6;
         }

         return true;
      }
   }
}
