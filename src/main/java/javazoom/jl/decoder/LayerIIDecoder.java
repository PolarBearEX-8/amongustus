package javazoom.jl.decoder;

public class LayerIIDecoder extends LayerIDecoder implements FrameDecoder {
   @Override
   public void createSubbands() {
      if (this.mode == 3) {
         for (int i = 0; i < this.num_subbands; i++) {
            this.subbands[i] = new SubbandLayer2(i);
         }
      } else if (this.mode == 1) {
         int n;
         for (n = 0; n < this.header.getHIntensityStereoBound(); n++) {
            this.subbands[n] = new SubbandLayer2Stereo(n);
         }

         while (n < this.num_subbands) {
            this.subbands[n] = new SubbandLayer2IntensityStereo(n);
            n++;
         }
      } else {
         for (int j = 0; j < this.num_subbands; j++) {
            this.subbands[j] = new SubbandLayer2Stereo(j);
         }
      }
   }

   @Override
   public void readScaleFactorSelection() {
      for (int i = 0; i < this.num_subbands; i++) {
         ((SubbandLayer2)this.subbands[i]).read_scalefactor_selection(this.stream, this.crc);
      }
   }
}
