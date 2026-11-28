package javazoom.jl.decoder;

public class SubbandLayer2IntensityStereo extends SubbandLayer2 {
   public int channel2_scfsi;
   public float channel2_scalefactor1;
   public float channel2_scalefactor2;
   public float channel2_scalefactor3;

   @Override
   public boolean read_sampledata(Bitstream bitstream) {
      return super.read_sampledata(bitstream);
   }

   @Override
   public boolean put_next_sample(int n, SynthesisFilter synthesisFilter, SynthesisFilter synthesisFilter2) {
      if (this.allocation != 0) {
         float f = this.samples[this.samplenumber];
         if (this.groupingtable[0] == null) {
            f = (f + this.d[0]) * this.c[0];
         }

         if (n == 0) {
            float f2;
            if (this.groupnumber <= 4) {
               f *= this.scalefactor1;
               f2 = f * this.channel2_scalefactor1;
            } else if (this.groupnumber <= 8) {
               f *= this.scalefactor2;
               f2 = f * this.channel2_scalefactor2;
            } else {
               f *= this.scalefactor3;
               f2 = f * this.channel2_scalefactor3;
            }

            synthesisFilter.input_sample(f, this.subbandnumber);
            synthesisFilter2.input_sample(f2, this.subbandnumber);
         } else if (n == 1) {
            if (this.groupnumber <= 4) {
               f *= this.scalefactor1;
            } else if (this.groupnumber <= 8) {
               f *= this.scalefactor2;
            } else {
               f *= this.scalefactor3;
            }

            synthesisFilter.input_sample(f, this.subbandnumber);
         } else {
            if (this.groupnumber <= 4) {
               f *= this.channel2_scalefactor1;
            } else if (this.groupnumber <= 8) {
               f *= this.channel2_scalefactor2;
            } else {
               f *= this.channel2_scalefactor3;
            }

            synthesisFilter.input_sample(f, this.subbandnumber);
         }
      }

      return ++this.samplenumber == 3;
   }

   @Override
   public void read_scalefactor(Bitstream bitstream, Header header) {
      if (this.allocation != 0) {
         super.read_scalefactor(bitstream, header);
         switch (this.channel2_scfsi) {
            case 0:
               this.channel2_scalefactor1 = scalefactors[bitstream.get_bits(6)];
               this.channel2_scalefactor2 = scalefactors[bitstream.get_bits(6)];
               this.channel2_scalefactor3 = scalefactors[bitstream.get_bits(6)];
               break;
            case 1:
               this.channel2_scalefactor1 = this.channel2_scalefactor2 = scalefactors[bitstream.get_bits(6)];
               this.channel2_scalefactor3 = scalefactors[bitstream.get_bits(6)];
               break;
            case 2:
               this.channel2_scalefactor1 = this.channel2_scalefactor2 = this.channel2_scalefactor3 = scalefactors[bitstream.get_bits(6)];
               break;
            case 3:
               this.channel2_scalefactor1 = scalefactors[bitstream.get_bits(6)];
               this.channel2_scalefactor2 = this.channel2_scalefactor3 = scalefactors[bitstream.get_bits(6)];
         }
      }
   }

   public SubbandLayer2IntensityStereo(int n) {
      super(n);
   }

   @Override
   public void read_allocation(Bitstream bitstream, Header header, Crc16 crc16) {
      super.read_allocation(bitstream, header, crc16);
   }

   @Override
   public void read_scalefactor_selection(Bitstream bitstream, Crc16 crc16) {
      if (this.allocation != 0) {
         this.scfsi = bitstream.get_bits(2);
         this.channel2_scfsi = bitstream.get_bits(2);
         if (crc16 != null) {
            crc16.add_bits(this.scfsi, 2);
            crc16.add_bits(this.channel2_scfsi, 2);
         }
      }
   }
}
