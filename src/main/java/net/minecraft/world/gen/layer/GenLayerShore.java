package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenJungle;
import net.minecraft.world.biome.BiomeGenMesa;

public class GenLayerShore extends GenLayer {
   @Override
   public int[] getInts(int n, int n2, int n3, int n4) {
      int[] ints = this.parent.getInts(n - 1, n2 - 1, n3 + 2, n4 + 2);
      int[] intCache = IntCache.getIntCache(n3 * n4);

      for (int i = 0; i < n4; i++) {
         for (int j = 0; j < n3; j++) {
            this.initChunkSeed((long)(j + n), (long)(i + n2));
            int n5 = ints[j + 1 + (i + 1) * (n3 + 2)];
            BiomeGenBase biome = BiomeGenBase.getBiome(n5);
            if (n5 == BiomeGenBase.mushroomIsland.biomeID) {
               int n6 = ints[j + 1 + (i + 1 - 1) * (n3 + 2)];
               int n7 = ints[j + 1 + 1 + (i + 1) * (n3 + 2)];
               int n8 = ints[j + 1 - 1 + (i + 1) * (n3 + 2)];
               int n9 = ints[j + 1 + (i + 1 + 1) * (n3 + 2)];
               if (n6 != BiomeGenBase.ocean.biomeID
                  && n7 != BiomeGenBase.ocean.biomeID
                  && n8 != BiomeGenBase.ocean.biomeID
                  && n9 != BiomeGenBase.ocean.biomeID) {
                  intCache[j + i * n3] = n5;
               } else {
                  intCache[j + i * n3] = BiomeGenBase.mushroomIslandShore.biomeID;
               }
            } else if (biome != null && biome.getBiomeClass() == BiomeGenJungle.class) {
               int n10 = ints[j + 1 + (i + 1 - 1) * (n3 + 2)];
               int n11 = ints[j + 1 + 1 + (i + 1) * (n3 + 2)];
               int n12 = ints[j + 1 - 1 + (i + 1) * (n3 + 2)];
               int n13 = ints[j + 1 + (i + 1 + 1) * (n3 + 2)];
               if (!this.isJungleCompatible(n10) || !this.isJungleCompatible(n11) || !this.isJungleCompatible(n12) || !this.isJungleCompatible(n13)) {
                  intCache[j + i * n3] = BiomeGenBase.jungleEdge.biomeID;
               } else if (!isBiomeOceanic(n10) && !isBiomeOceanic(n11) && !isBiomeOceanic(n12) && !isBiomeOceanic(n13)) {
                  intCache[j + i * n3] = n5;
               } else {
                  intCache[j + i * n3] = BiomeGenBase.beach.biomeID;
               }
            } else if (n5 == BiomeGenBase.extremeHills.biomeID
               || n5 == BiomeGenBase.extremeHillsPlus.biomeID
               || n5 == BiomeGenBase.extremeHillsEdge.biomeID) {
               this.replaceIfNeighborOcean(ints, intCache, j, i, n3, n5, BiomeGenBase.stoneBeach.biomeID);
            } else if (biome != null && biome.isSnowyBiome()) {
               this.replaceIfNeighborOcean(ints, intCache, j, i, n3, n5, BiomeGenBase.coldBeach.biomeID);
            } else if (n5 == BiomeGenBase.mesa.biomeID || n5 == BiomeGenBase.mesaPlateau_F.biomeID) {
               int n14 = ints[j + 1 + (i + 1 - 1) * (n3 + 2)];
               int n15 = ints[j + 1 + 1 + (i + 1) * (n3 + 2)];
               int n16 = ints[j + 1 - 1 + (i + 1) * (n3 + 2)];
               int n17 = ints[j + 1 + (i + 1 + 1) * (n3 + 2)];
               if (isBiomeOceanic(n14) || isBiomeOceanic(n15) || isBiomeOceanic(n16) || isBiomeOceanic(n17)) {
                  intCache[j + i * n3] = n5;
               } else if (this.isMesa(n14) && this.isMesa(n15) && this.isMesa(n16) && this.isMesa(n17)) {
                  intCache[j + i * n3] = n5;
               } else {
                  intCache[j + i * n3] = BiomeGenBase.desert.biomeID;
               }
            } else if (n5 != BiomeGenBase.ocean.biomeID
               && n5 != BiomeGenBase.deepOcean.biomeID
               && n5 != BiomeGenBase.river.biomeID
               && n5 != BiomeGenBase.swampland.biomeID) {
               int n18 = ints[j + 1 + (i + 1 - 1) * (n3 + 2)];
               int n19 = ints[j + 1 + 1 + (i + 1) * (n3 + 2)];
               int n20 = ints[j + 1 - 1 + (i + 1) * (n3 + 2)];
               int n21 = ints[j + 1 + (i + 1 + 1) * (n3 + 2)];
               if (!isBiomeOceanic(n18) && !isBiomeOceanic(n19) && !isBiomeOceanic(n20) && !isBiomeOceanic(n21)) {
                  intCache[j + i * n3] = n5;
               } else {
                  intCache[j + i * n3] = BiomeGenBase.beach.biomeID;
               }
            } else {
               intCache[j + i * n3] = n5;
            }
         }
      }

      return intCache;
   }

   private void replaceIfNeighborOcean(int[] ints, int[] ints2, int n, int n2, int n3, int n4, int n5) {
      if (isBiomeOceanic(n4)) {
         ints2[n + n2 * n3] = n4;
      } else {
         int n6 = ints[n + 1 + (n2 + 1 - 1) * (n3 + 2)];
         int n7 = ints[n + 1 + 1 + (n2 + 1) * (n3 + 2)];
         int n8 = ints[n + 1 - 1 + (n2 + 1) * (n3 + 2)];
         int n9 = ints[n + 1 + (n2 + 1 + 1) * (n3 + 2)];
         if (!isBiomeOceanic(n6) && !isBiomeOceanic(n7) && !isBiomeOceanic(n8) && !isBiomeOceanic(n9)) {
            ints2[n + n2 * n3] = n4;
         } else {
            ints2[n + n2 * n3] = n5;
         }
      }
   }

   private boolean isMesa(int n) {
      return BiomeGenBase.getBiome(n) instanceof BiomeGenMesa;
   }

   private boolean isJungleCompatible(int n) {
      return BiomeGenBase.getBiome(n) != null && BiomeGenBase.getBiome(n).getBiomeClass() == BiomeGenJungle.class
         ? true
         : n == BiomeGenBase.jungleEdge.biomeID
            || n == BiomeGenBase.jungle.biomeID
            || n == BiomeGenBase.jungleHills.biomeID
            || n == BiomeGenBase.forest.biomeID
            || n == BiomeGenBase.taiga.biomeID
            || isBiomeOceanic(n);
   }

   public GenLayerShore(long l, GenLayer genLayer) {
      super(l);
      this.parent = genLayer;
   }
}
