package javazoom.jl.decoder;

public class LayerIIIScaleFactorBandIndex {
   public int[] l;
   public int[] s;

   public LayerIIIScaleFactorBandIndex(int[] ints, int[] ints2) {
      this.l = ints;
      this.s = ints2;
   }

   public LayerIIIScaleFactorBandIndex() {
      this.l = new int[23];
      this.s = new int[14];
   }
}
