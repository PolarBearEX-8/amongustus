package net.minecraft.world.gen.layer;

public class GenLayerSmooth extends GenLayer {
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
            int n9 = ints[j + 0 + (i + 1) * n7];
            int n10 = ints[j + 2 + (i + 1) * n7];
            int n11 = ints[j + 1 + (i + 0) * n7];
            int n12 = ints[j + 1 + (i + 2) * n7];
            int n13 = ints[j + 1 + (i + 1) * n7];
            if (n9 == n10 && n11 == n12) {
               this.initChunkSeed((long)(j + n), (long)(i + n2));
               if (this.nextInt(2) == 0) {
                  n13 = n9;
               } else {
                  n13 = n11;
               }
            } else {
               if (n9 == n10) {
                  n13 = n9;
               }

               if (n11 == n12) {
                  n13 = n11;
               }
            }

            intCache[j + i * n3] = n13;
         }
      }

      return intCache;
   }

   public GenLayerSmooth(long l, GenLayer genLayer) {
      super(l);
      super.parent = genLayer;
   }
}
