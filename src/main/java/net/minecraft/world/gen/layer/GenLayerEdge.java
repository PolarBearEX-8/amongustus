package net.minecraft.world.gen.layer;

public class GenLayerEdge extends GenLayer {
   private final GenLayerEdge.Mode mode;

   public GenLayerEdge(long l, GenLayer genLayer, GenLayerEdge.Mode mode2) {
      super(l);
      this.parent = genLayer;
      this.mode = mode2;
   }

   private int[] getIntsCoolWarm(int n, int n2, int n3, int n4) {
      int n5 = n - 1;
      int n6 = n2 - 1;
      int n7 = 1 + n3 + 1;
      int n8 = 1 + n4 + 1;
      int[] ints = this.parent.getInts(n5, n6, n7, n8);
      int[] intCache = IntCache.getIntCache(n3 * n4);

      for (int i = 0; i < n4; i++) {
         for (int j = 0; j < n3; j++) {
            this.initChunkSeed((long)(j + n), (long)(i + n2));
            int n9 = ints[j + 1 + (i + 1) * n7];
            if (n9 == 1) {
               int n10 = ints[j + 1 + (i + 1 - 1) * n7];
               int n11 = ints[j + 1 + 1 + (i + 1) * n7];
               int n12 = ints[j + 1 - 1 + (i + 1) * n7];
               int n13 = ints[j + 1 + (i + 1 + 1) * n7];
               boolean flag = n10 == 3 || n11 == 3 || n12 == 3 || n13 == 3;
               boolean flag2 = n10 == 4 || n11 == 4 || n12 == 4 || n13 == 4;
               if (flag || flag2) {
                  n9 = 2;
               }
            }

            intCache[j + i * n3] = n9;
         }
      }

      return intCache;
   }

   private int[] getIntsHeatIce(int n, int n2, int n3, int n4) {
      int n5 = n - 1;
      int n6 = n2 - 1;
      int n7 = 1 + n3 + 1;
      int n8 = 1 + n4 + 1;
      int[] ints = this.parent.getInts(n5, n6, n7, n8);
      int[] intCache = IntCache.getIntCache(n3 * n4);

      for (int i = 0; i < n4; i++) {
         for (int j = 0; j < n3; j++) {
            int n9 = ints[j + 1 + (i + 1) * n7];
            if (n9 == 4) {
               int n10 = ints[j + 1 + (i + 1 - 1) * n7];
               int n11 = ints[j + 1 + 1 + (i + 1) * n7];
               int n12 = ints[j + 1 - 1 + (i + 1) * n7];
               int n13 = ints[j + 1 + (i + 1 + 1) * n7];
               boolean flag = n10 == 2 || n11 == 2 || n12 == 2 || n13 == 2;
               if ((n10 == 1 || n11 == 1 || n12 == 1 || n13 == 1) || flag) {
                  n9 = 3;
               }
            }

            intCache[j + i * n3] = n9;
         }
      }

      return intCache;
   }

   @Override
   public int[] getInts(int n, int n2, int n3, int n4) {
      switch (this.mode) {
         case COOL_WARM:
         default:
            return this.getIntsCoolWarm(n, n2, n3, n4);
         case HEAT_ICE:
            return this.getIntsHeatIce(n, n2, n3, n4);
         case SPECIAL:
            return this.getIntsSpecial(n, n2, n3, n4);
      }
   }

   private int[] getIntsSpecial(int n, int n2, int n3, int n4) {
      int[] ints = this.parent.getInts(n, n2, n3, n4);
      int[] intCache = IntCache.getIntCache(n3 * n4);

      for (int i = 0; i < n4; i++) {
         for (int j = 0; j < n3; j++) {
            this.initChunkSeed((long)(j + n), (long)(i + n2));
            int n5 = ints[j + i * n3];
            if (n5 != 0 && this.nextInt(13) == 0) {
               n5 |= 1 + this.nextInt(15) << 8 & 3840;
            }

            intCache[j + i * n3] = n5;
         }
      }

      return intCache;
   }

public enum Mode {
      COOL_WARM,
      HEAT_ICE,
      SPECIAL;
   }
}
