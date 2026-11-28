package javazoom.jl.decoder;

public class LayerIIISideInfo {
   public int main_data_begin = 0;
   public int private_bits = 0;
   public LayerIIIChannelSideInfo[] ch = new LayerIIIChannelSideInfo[2];

   public LayerIIISideInfo() {
      this.ch[0] = new LayerIIIChannelSideInfo();
      this.ch[1] = new LayerIIIChannelSideInfo();
   }
}
