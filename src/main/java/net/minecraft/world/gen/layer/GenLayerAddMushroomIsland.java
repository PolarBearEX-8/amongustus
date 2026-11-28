package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerAddMushroomIsland extends GenLayer {
   public GenLayerAddMushroomIsland(long l, GenLayer genLayer) {
      super(l);
      this.parent = genLayer;
   }

   @Override
   public int[] getInts(int n, int n2, int n3, int n4) {
      int n5 = n - 1;
      int n6 = n2 - 1;
      int n7 = n3 + 2;
      int n8 = n4 + 2;
      int[] ints = this.parent.getInts(n5, n6, n7, n8);
      int[] intCache = IntCache.getIntCache(n3 * n4);

      for (int i = 0; i < n4; i++) {
         for (int j = 0; j < n3; j++) {
            int n9 = ints[j + 0 + (i + 0) * n7];
            int n10 = ints[j + 2 + (i + 0) * n7];
            int n11 = ints[j + 0 + (i + 2) * n7];
            int n12 = ints[j + 2 + (i + 2) * n7];
            int n13 = ints[j + 1 + (i + 1) * n7];
            this.initChunkSeed((long)(j + n), (long)(i + n2));
            if (n13 == 0 && n9 == 0 && n10 == 0 && n11 == 0 && n12 == 0 && this.nextInt(100) == 0) {
               intCache[j + i * n3] = BiomeGenBase.mushroomIsland.biomeID;
            } else {
               intCache[j + i * n3] = n13;
            }
         }
      }

      return intCache;
   }
}
