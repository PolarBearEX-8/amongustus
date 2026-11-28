package net.minecraft.world.gen.layer;

public class GenLayerFuzzyZoom extends GenLayerZoom {
   @Override
   public int selectModeOrRandom(int n, int n2, int n3, int n4) {
      return this.selectRandom(new int[]{n, n2, n3, n4});
   }

   public GenLayerFuzzyZoom(long l, GenLayer genLayer) {
      super(l, genLayer);
   }
}
