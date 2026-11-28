package javazoom.jl.decoder;

public class LayerIDecoder implements FrameDecoder {
   public Bitstream stream;
   public Header header;
   public SynthesisFilter filter1;
   public SynthesisFilter filter2;
   public Obuffer buffer;
   public int which_channels;
   public int mode;
   public int num_subbands;
   public Subband[] subbands;
   public Crc16 crc = null;

   public void readScaleFactors() {
      for (int i = 0; i < this.num_subbands; i++) {
         this.subbands[i].read_scalefactor(this.stream, this.header);
      }
   }

   public void createSubbands() {
      if (this.mode == 3) {
         for (int i = 0; i < this.num_subbands; i++) {
            this.subbands[i] = new SubbandLayer1(i);
         }
      } else if (this.mode == 1) {
         int n;
         for (n = 0; n < this.header.getHIntensityStereoBound(); n++) {
            this.subbands[n] = new SubbandLayer1Stereo(n);
         }

         while (n < this.num_subbands) {
            this.subbands[n] = new SubbandLayer1IntensityStereo(n);
            n++;
         }
      } else {
         for (int j = 0; j < this.num_subbands; j++) {
            this.subbands[j] = new SubbandLayer1Stereo(j);
         }
      }
   }

   public void readScaleFactorSelection() {
   }

   @Override
   public void decodeFrame() throws DecoderException {
      this.num_subbands = this.header.getHNumberOfSubbands();
      this.subbands = new Subband[32];
      this.mode = this.header.getHMode();
      this.createSubbands();
      this.readAllocation();
      this.readScaleFactorSelection();
      if (this.crc != null || this.header.checksum_ok()) {
         this.readScaleFactors();
         this.readSampleData();
      }
   }

   public void readAllocation() throws DecoderException {
      for (int i = 0; i < this.num_subbands; i++) {
         this.subbands[i].read_allocation(this.stream, this.header, this.crc);
      }
   }

   public void create(Bitstream bitstream, Header header2, SynthesisFilter synthesisFilter, SynthesisFilter synthesisFilter2, Obuffer obuffer, int n) {
      this.stream = bitstream;
      this.header = header2;
      this.filter1 = synthesisFilter;
      this.filter2 = synthesisFilter2;
      this.buffer = obuffer;
      this.which_channels = n;
   }

   public LayerIDecoder() {
      this.crc = new Crc16();
   }

   public void readSampleData() {
      boolean flag = false;
      boolean flag2 = false;
      int hMode = this.header.getHMode();

      do {
         for (int i = 0; i < this.num_subbands; i++) {
            flag = this.subbands[i].read_sampledata(this.stream);
         }

         do {
            for (int j = 0; j < this.num_subbands; j++) {
               flag2 = this.subbands[j].put_next_sample(this.which_channels, this.filter1, this.filter2);
            }

            this.filter1.calculate_pcm_samples(this.buffer);
            if (this.which_channels == 0 && hMode != 3) {
               this.filter2.calculate_pcm_samples(this.buffer);
            }
         } while (!flag2);
      } while (!flag);
   }
}
