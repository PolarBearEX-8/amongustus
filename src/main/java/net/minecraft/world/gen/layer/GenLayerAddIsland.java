package net.minecraft.world.gen.layer;

public class GenLayerAddIsland extends GenLayer {
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
            if (n13 != 0 || n9 == 0 && n10 == 0 && n11 == 0 && n12 == 0) {
               if (n13 > 0 && (n9 == 0 || n10 == 0 || n11 == 0 || n12 == 0)) {
                  if (this.nextInt(5) == 0) {
                     if (n13 == 4) {
                        intCache[j + i * n3] = 4;
                     } else {
                        intCache[j + i * n3] = 0;
                     }
                  } else {
                     intCache[j + i * n3] = n13;
                  }
               } else {
                  intCache[j + i * n3] = n13;
               }
            } else {
               int n14 = 1;
               int n15 = 1;
               if (n9 != 0 && this.nextInt(n14++) == 0) {
                  n15 = n9;
               }

               if (n10 != 0 && this.nextInt(n14++) == 0) {
                  n15 = n10;
               }

               if (n11 != 0 && this.nextInt(n14++) == 0) {
                  n15 = n11;
               }

               if (n12 != 0 && this.nextInt(n14++) == 0) {
                  n15 = n12;
               }

               if (this.nextInt(3) == 0) {
                  intCache[j + i * n3] = n15;
               } else if (n15 == 4) {
                  intCache[j + i * n3] = 4;
               } else {
                  intCache[j + i * n3] = 0;
               }
            }
         }
      }

      return intCache;
   }

   public GenLayerAddIsland(long l, GenLayer genLayer) {
      super(l);
      this.parent = genLayer;
   }
}
