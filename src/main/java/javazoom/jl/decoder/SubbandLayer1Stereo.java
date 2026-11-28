package javazoom.jl.decoder;

public class SubbandLayer1Stereo extends SubbandLayer1 {
   public int channel2_allocation;
   public float channel2_scalefactor;
   public int channel2_samplelength;
   public float channel2_sample;
   public float channel2_factor;
   public float channel2_offset;

   @Override
   public void read_scalefactor(Bitstream bitstream, Header header) {
      if (this.allocation != 0) {
         this.scalefactor = scalefactors[bitstream.get_bits(6)];
      }

      if (this.channel2_allocation != 0) {
         this.channel2_scalefactor = scalefactors[bitstream.get_bits(6)];
      }
   }

   @Override
   public boolean read_sampledata(Bitstream bitstream) {
      boolean flag = super.read_sampledata(bitstream);
      if (this.channel2_allocation != 0) {
         this.channel2_sample = (float)bitstream.get_bits(this.channel2_samplelength);
      }

      return flag;
   }

   @Override
   public boolean put_next_sample(int n, SynthesisFilter synthesisFilter, SynthesisFilter synthesisFilter2) {
      super.put_next_sample(n, synthesisFilter, synthesisFilter2);
      if (this.channel2_allocation != 0 && n != 1) {
         float f = (this.channel2_sample * this.channel2_factor + this.channel2_offset) * this.channel2_scalefactor;
         if (n == 0) {
            synthesisFilter2.input_sample(f, this.subbandnumber);
         } else {
            synthesisFilter.input_sample(f, this.subbandnumber);
         }
      }

      return true;
   }

   @Override
   public void read_allocation(Bitstream bitstream, Header header, Crc16 crc16) throws DecoderException {
      this.allocation = bitstream.get_bits(4);
      this.channel2_allocation = bitstream.get_bits(4);
      if (crc16 != null) {
         crc16.add_bits(this.allocation, 4);
         crc16.add_bits(this.channel2_allocation, 4);
      }

      if (this.allocation != 0) {
         this.samplelength = this.allocation + 1;
         this.factor = table_factor[this.allocation];
         this.offset = table_offset[this.allocation];
      }

      if (this.channel2_allocation != 0) {
         this.channel2_samplelength = this.channel2_allocation + 1;
         this.channel2_factor = table_factor[this.channel2_allocation];
         this.channel2_offset = table_offset[this.channel2_allocation];
      }
   }

   public SubbandLayer1Stereo(int n) {
      super(n);
   }
}
