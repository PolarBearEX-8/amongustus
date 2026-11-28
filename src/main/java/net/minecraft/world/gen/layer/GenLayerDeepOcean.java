package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerDeepOcean extends GenLayer {
   public GenLayerDeepOcean(long l, GenLayer genLayer) {
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
            int n9 = ints[j + 1 + (i + 1 - 1) * (n3 + 2)];
            int n10 = ints[j + 1 + 1 + (i + 1) * (n3 + 2)];
            int n11 = ints[j + 1 - 1 + (i + 1) * (n3 + 2)];
            int n12 = ints[j + 1 + (i + 1 + 1) * (n3 + 2)];
            int n13 = ints[j + 1 + (i + 1) * n7];
            int n14 = 0;
            if (n9 == 0) {
               n14++;
            }

            if (n10 == 0) {
               n14++;
            }

            if (n11 == 0) {
               n14++;
            }

            if (n12 == 0) {
               n14++;
            }

            if (n13 == 0 && n14 > 3) {
               intCache[j + i * n3] = BiomeGenBase.deepOcean.biomeID;
            } else {
               intCache[j + i * n3] = n13;
            }
         }
      }

      return intCache;
   }
}
