package javazoom.jl.decoder;

public class LayerIIIScaleFactorTable {
   public int[] l;
   public int[] s;
   public final LayerIIIDecoder decoder;

   public LayerIIIScaleFactorTable(LayerIIIDecoder layerIIIDecoder) {
      this.decoder = layerIIIDecoder;
      this.l = new int[5];
      this.s = new int[3];
   }

   public LayerIIIScaleFactorTable(LayerIIIDecoder layerIIIDecoder, int[] ints, int[] ints2) {
      this.decoder = layerIIIDecoder;
      this.l = ints;
      this.s = ints2;
   }
}
