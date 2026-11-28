package net.minecraft.world.gen.layer;

public class GenLayerIsland extends GenLayer {
   public GenLayerIsland(long l) {
      super(l);
   }

   @Override
   public int[] getInts(int n, int n2, int n3, int n4) {
      int[] intCache = IntCache.getIntCache(n3 * n4);

      for (int i = 0; i < n4; i++) {
         for (int j = 0; j < n3; j++) {
            this.initChunkSeed((long)(n + j), (long)(n2 + i));
            intCache[j + i * n3] = this.nextInt(10) == 0 ? 1 : 0;
         }
      }

      if (n > -n3 && n <= 0 && n2 > -n4 && n2 <= 0) {
         intCache[-n + -n2 * n3] = 1;
      }

      return intCache;
   }
}
