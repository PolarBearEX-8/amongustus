package javazoom.jl.decoder;

public class LayerIIIChannelSideInfo {
   public int[] scfsi = new int[4];
   public LayerIIIGranuleInfo[] gr = new LayerIIIGranuleInfo[2];

   public LayerIIIChannelSideInfo() {
      this.gr[0] = new LayerIIIGranuleInfo();
      this.gr[1] = new LayerIIIGranuleInfo();
   }
}
