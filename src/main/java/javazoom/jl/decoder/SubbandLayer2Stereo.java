package javazoom.jl.decoder;

public class SubbandLayer2Stereo extends SubbandLayer2 {
   public int channel2_allocation;
   public int channel2_scfsi;
   public float channel2_scalefactor1;
   public float channel2_scalefactor2;
   public float channel2_scalefactor3;
   public int[] channel2_codelength = new int[]{0};
   public float[] channel2_factor = new float[]{0.0F};
   public float[] channel2_samples;
   public float[] channel2_c = new float[]{0.0F};
   public float[] channel2_d = new float[]{0.0F};

   @Override
   public void read_scalefactor_selection(Bitstream bitstream, Crc16 crc16) {
      if (this.allocation != 0) {
         this.scfsi = bitstream.get_bits(2);
         if (crc16 != null) {
            crc16.add_bits(this.scfsi, 2);
         }
      }

      if (this.channel2_allocation != 0) {
         this.channel2_scfsi = bitstream.get_bits(2);
         if (crc16 != null) {
            crc16.add_bits(this.channel2_scfsi, 2);
         }
      }
   }

   @Override
   public boolean read_sampledata(Bitstream bitstream) {
      boolean flag = super.read_sampledata(bitstream);
      if (this.channel2_allocation != 0) {
         if (this.groupingtable[1] != null) {
            int n = bitstream.get_bits(this.channel2_codelength[0]);
            n += n << 1;
            float[] channel2Samples = this.channel2_samples;
            float[] floats = this.groupingtable[1];
            int n2 = 0;
            channel2Samples[n2] = floats[n];
            int n3 = n + 1;
            n2++;
            channel2Samples[n2] = floats[n3];
            n3++;
            n2++;
            channel2Samples[n2] = floats[n3];
         } else {
            this.channel2_samples[0] = (float)((double)((float)bitstream.get_bits(this.channel2_codelength[0]) * this.channel2_factor[0]) - 1.0);
            this.channel2_samples[1] = (float)((double)((float)bitstream.get_bits(this.channel2_codelength[0]) * this.channel2_factor[0]) - 1.0);
            this.channel2_samples[2] = (float)((double)((float)bitstream.get_bits(this.channel2_codelength[0]) * this.channel2_factor[0]) - 1.0);
         }
      }

      return flag;
   }

   @Override
   public void read_scalefactor(Bitstream bitstream, Header header) {
      super.read_scalefactor(bitstream, header);
      if (this.channel2_allocation != 0) {
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

         this.prepare_sample_reading(header, this.channel2_allocation, 1, this.channel2_factor, this.channel2_codelength, this.channel2_c, this.channel2_d);
      }
   }

   @Override
   public void read_allocation(Bitstream bitstream, Header header, Crc16 crc16) {
      int n = this.get_allocationlength(header);
      this.allocation = bitstream.get_bits(n);
      this.channel2_allocation = bitstream.get_bits(n);
      if (crc16 != null) {
         crc16.add_bits(this.allocation, n);
         crc16.add_bits(this.channel2_allocation, n);
      }
   }

   @Override
   public boolean put_next_sample(int n, SynthesisFilter synthesisFilter, SynthesisFilter synthesisFilter2) {
      boolean flag = super.put_next_sample(n, synthesisFilter, synthesisFilter2);
      if (this.channel2_allocation != 0 && n != 1) {
         float f = this.channel2_samples[this.samplenumber - 1];
         if (this.groupingtable[1] == null) {
            f = (f + this.channel2_d[0]) * this.channel2_c[0];
         }

         if (this.groupnumber <= 4) {
            f *= this.channel2_scalefactor1;
         } else if (this.groupnumber <= 8) {
            f *= this.channel2_scalefactor2;
         } else {
            f *= this.channel2_scalefactor3;
         }

         if (n == 0) {
            synthesisFilter2.input_sample(f, this.subbandnumber);
         } else {
            synthesisFilter.input_sample(f, this.subbandnumber);
         }
      }

      return flag;
   }

   public SubbandLayer2Stereo(int n) {
      super(n);
      this.channel2_samples = new float[3];
   }
}
