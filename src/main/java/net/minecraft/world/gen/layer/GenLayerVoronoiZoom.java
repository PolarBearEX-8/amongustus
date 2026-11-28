package net.minecraft.world.gen.layer;

public class GenLayerVoronoiZoom extends GenLayer {
   public GenLayerVoronoiZoom(long l, GenLayer genLayer) {
      super(l);
      super.parent = genLayer;
   }

   @Override
   public int[] getInts(int n, int n2, int n3, int n4) {
      n -= 2;
      n2 -= 2;
      int n5 = n >> 2;
      int n6 = n2 >> 2;
      int n7 = (n3 >> 2) + 2;
      int n8 = (n4 >> 2) + 2;
      int[] ints = this.parent.getInts(n5, n6, n7, n8);
      int n9 = n7 - 1 << 2;
      int[] intCache = IntCache.getIntCache(n9 * (n8 - 1 << 2));

      for (int i = 0; i < n8 - 1; i++) {
         int n11 = 0;
         int n12 = ints[n11 + 0 + (i + 0) * n7];

         for (int j = ints[n11 + 0 + (i + 1) * n7]; n11 < n7 - 1; n11++) {
            double d = 3.6;
            this.initChunkSeed((long)(n11 + n5 << 2), (long)(i + n6 << 2));
            double d2 = ((double)this.nextInt(1024) / 1024.0 - 0.5) * 3.6;
            double d3 = ((double)this.nextInt(1024) / 1024.0 - 0.5) * 3.6;
            this.initChunkSeed((long)(n11 + n5 + 1 << 2), (long)(i + n6 << 2));
            double d4 = ((double)this.nextInt(1024) / 1024.0 - 0.5) * 3.6 + 4.0;
            double d5 = ((double)this.nextInt(1024) / 1024.0 - 0.5) * 3.6;
            this.initChunkSeed((long)(n11 + n5 << 2), (long)(i + n6 + 1 << 2));
            double d6 = ((double)this.nextInt(1024) / 1024.0 - 0.5) * 3.6;
            double d7 = ((double)this.nextInt(1024) / 1024.0 - 0.5) * 3.6 + 4.0;
            this.initChunkSeed((long)(n11 + n5 + 1 << 2), (long)(i + n6 + 1 << 2));
            double d8 = ((double)this.nextInt(1024) / 1024.0 - 0.5) * 3.6 + 4.0;
            double d9 = ((double)this.nextInt(1024) / 1024.0 - 0.5) * 3.6 + 4.0;
            int n13 = ints[n11 + 1 + (i + 0) * n7] & 0xFF;
            int n14 = ints[n11 + 1 + (i + 1) * n7] & 0xFF;

            for (int k = 0; k < 4; k++) {
               int n15 = ((i << 2) + k) * n9 + (n11 << 2);

               for (int m = 0; m < 4; m++) {
                  double d10 = ((double)k - d3) * ((double)k - d3) + ((double)m - d2) * ((double)m - d2);
                  double d11 = ((double)k - d5) * ((double)k - d5) + ((double)m - d4) * ((double)m - d4);
                  double d12 = ((double)k - d7) * ((double)k - d7) + ((double)m - d6) * ((double)m - d6);
                  double d13 = ((double)k - d9) * ((double)k - d9) + ((double)m - d8) * ((double)m - d8);
                  if (d10 < d11 && d10 < d12 && d10 < d13) {
                     intCache[n15++] = n12;
                  } else if (d11 < d10 && d11 < d12 && d11 < d13) {
                     intCache[n15++] = n13;
                  } else if (d12 < d10 && d12 < d11 && d12 < d13) {
                     intCache[n15++] = j;
                  } else {
                     intCache[n15++] = n14;
                  }
               }
            }

            n12 = n13;
            j = n14;
         }
      }

      int[] intCache2 = IntCache.getIntCache(n3 * n4);

      for (int idx = 0; idx < n4; idx++) {
         System.arraycopy(intCache, (idx + (n2 & 3)) * n9 + (n & 3), intCache2, idx * n3, n3);
      }

      return intCache2;
   }
}
