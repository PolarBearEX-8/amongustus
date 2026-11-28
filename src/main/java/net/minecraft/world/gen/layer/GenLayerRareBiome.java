package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerRareBiome extends GenLayer {
   @Override
   public int[] getInts(int n, int n2, int n3, int n4) {
      int[] ints = this.parent.getInts(n - 1, n2 - 1, n3 + 2, n4 + 2);
      int[] intCache = IntCache.getIntCache(n3 * n4);

      for (int i = 0; i < n4; i++) {
         for (int j = 0; j < n3; j++) {
            this.initChunkSeed((long)(j + n), (long)(i + n2));
            int n5 = ints[j + 1 + (i + 1) * (n3 + 2)];
            if (this.nextInt(57) == 0) {
               if (n5 == BiomeGenBase.plains.biomeID) {
                  intCache[j + i * n3] = BiomeGenBase.plains.biomeID + 128;
               } else {
                  intCache[j + i * n3] = n5;
               }
            } else {
               intCache[j + i * n3] = n5;
            }
         }
      }

      return intCache;
   }

   public GenLayerRareBiome(long l, GenLayer genLayer) {
      super(l);
      this.parent = genLayer;
   }
}
