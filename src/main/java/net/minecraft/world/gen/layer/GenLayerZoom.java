package net.minecraft.world.gen.layer;

public class GenLayerZoom extends GenLayer {
   public int selectRandom(int n, int n2) {
      return this.nextInt(2) == 0 ? n : n2;
   }

   @Override
   public int[] getInts(int n, int n2, int n3, int n4) {
      int n5 = n >> 1;
      int n6 = n2 >> 1;
      int n7 = (n3 >> 1) + 2;
      int n8 = (n4 >> 1) + 2;
      int[] ints = this.parent.getInts(n5, n6, n7, n8);
      int n9 = n7 - 1 << 1;
      int[] intCache = IntCache.getIntCache(n9 * (n8 - 1 << 1));

      for (int i = 0; i < n8 - 1; i++) {
         int n11 = (i << 1) * n9;
         int n12 = 0;
         int n13 = ints[n12 + i * n7];

         for (int j = ints[n12 + (i + 1) * n7]; n12 < n7 - 1; n12++) {
            this.initChunkSeed((long)n12 + (long)n5 << 1, (long)i + (long)n6 << 1);
            int n14 = ints[n12 + 1 + i * n7];
            int n15 = ints[n12 + 1 + (i + 1) * n7];
            intCache[n11] = n13;
            intCache[n11++ + n9] = this.selectRandom(n13, j);
            intCache[n11] = this.selectRandom(n13, n14);
            intCache[n11++ + n9] = this.selectModeOrRandom(n13, n14, j, n15);
            n13 = n14;
            j = n15;
         }
      }

      int[] intCache2 = IntCache.getIntCache(n3 * n4);

      for (int k = 0; k < n4; k++) {
         System.arraycopy(intCache, (k + (n2 & 1)) * n9 + (n & 1), intCache2, k * n3, n3);
      }

      return intCache2;
   }

   public GenLayerZoom(long l, GenLayer genLayer) {
      super(l);
      super.parent = genLayer;
   }

   public static GenLayer magnify(long l, GenLayer genLayer, int n) {
      Object obj = genLayer;

      for (int i = 0; i < n; i++) {
         obj = new GenLayerZoom(l + (long)i, (GenLayer)obj);
      }

      return (GenLayer)obj;
   }
}
