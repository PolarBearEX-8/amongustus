package javazoom.jl.decoder;

public class SubbandLayer1IntensityStereo extends SubbandLayer1 {
   public float channel2_scalefactor;

   @Override
   public boolean read_sampledata(Bitstream bitstream) {
      return super.read_sampledata(bitstream);
   }

   public SubbandLayer1IntensityStereo(int n) {
      super(n);
   }

   @Override
   public void read_allocation(Bitstream bitstream, Header header, Crc16 crc16) throws DecoderException {
      super.read_allocation(bitstream, header, crc16);
   }

   @Override
   public void read_scalefactor(Bitstream bitstream, Header header) {
      if (this.allocation != 0) {
         this.scalefactor = scalefactors[bitstream.get_bits(6)];
         this.channel2_scalefactor = scalefactors[bitstream.get_bits(6)];
      }
   }

   @Override
   public boolean put_next_sample(int n, SynthesisFilter synthesisFilter, SynthesisFilter synthesisFilter2) {
      if (this.allocation != 0) {
         this.sample = this.sample * this.factor + this.offset;
         if (n == 0) {
            float f = this.sample * this.scalefactor;
            float f2 = this.sample * this.channel2_scalefactor;
            synthesisFilter.input_sample(f, this.subbandnumber);
            synthesisFilter2.input_sample(f2, this.subbandnumber);
         } else if (n == 1) {
            synthesisFilter.input_sample(this.sample * this.scalefactor, this.subbandnumber);
         } else {
            synthesisFilter.input_sample(this.sample * this.channel2_scalefactor, this.subbandnumber);
         }
      }

      return true;
   }
}
