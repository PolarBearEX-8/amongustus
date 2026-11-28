package net.minecraft.world.gen.layer;

public class GenLayerRiverInit extends GenLayer {
   @Override
   public int[] getInts(int n, int n2, int n3, int n4) {
      int[] ints = this.parent.getInts(n, n2, n3, n4);
      int[] intCache = IntCache.getIntCache(n3 * n4);

      for (int i = 0; i < n4; i++) {
         for (int j = 0; j < n3; j++) {
            this.initChunkSeed((long)(j + n), (long)(i + n2));
            intCache[j + i * n3] = ints[j + i * n3] > 0 ? this.nextInt(299999) + 2 : 0;
         }
      }

      return intCache;
   }

   public GenLayerRiverInit(long l, GenLayer genLayer) {
      super(l);
      this.parent = genLayer;
   }
}
