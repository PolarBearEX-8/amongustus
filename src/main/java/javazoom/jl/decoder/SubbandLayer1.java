package javazoom.jl.decoder;

public class SubbandLayer1 extends Subband {
   public static final float[] table_factor = new float[]{
      0.0F,
      0.6666667F,
      0.2857143F,
      0.13333334F,
      0.06451613F,
      0.031746034F,
      0.015748031F,
      0.007843138F,
      0.0039138943F,
      0.0019550342F,
      9.770396E-4F,
      4.884005E-4F,
      2.4417043E-4F,
      1.2207776E-4F,
      6.103702E-5F
   };
   public static final float[] table_offset = new float[]{
      0.0F,
      -0.6666667F,
      -0.8571429F,
      -0.9333334F,
      -0.9677419F,
      -0.98412704F,
      -0.992126F,
      -0.9960785F,
      -0.99804306F,
      -0.9990225F,
      -0.9995115F,
      -0.99975586F,
      -0.9998779F,
      -0.99993896F,
      -0.9999695F
   };
   public int subbandnumber;
   public int samplenumber;
   public int allocation;
   public float scalefactor;
   public int samplelength;
   public float sample;
   public float factor;
   public float offset;

   @Override
   public void read_allocation(Bitstream bitstream, Header header, Crc16 crc16) throws DecoderException {
      if ((this.allocation = bitstream.get_bits(4)) == 15) {
         throw new DecoderException(514, null);
      } else {
         if (crc16 != null) {
            crc16.add_bits(this.allocation, 4);
         }

         if (this.allocation != 0) {
            this.samplelength = this.allocation + 1;
            this.factor = table_factor[this.allocation];
            this.offset = table_offset[this.allocation];
         }
      }
   }

   @Override
   public boolean put_next_sample(int n, SynthesisFilter synthesisFilter, SynthesisFilter synthesisFilter2) {
      if (this.allocation != 0 && n != 2) {
         synthesisFilter.input_sample((this.sample * this.factor + this.offset) * this.scalefactor, this.subbandnumber);
      }

      return true;
   }

   @Override
   public void read_scalefactor(Bitstream bitstream, Header header) {
      if (this.allocation != 0) {
         this.scalefactor = scalefactors[bitstream.get_bits(6)];
      }
   }

   @Override
   public boolean read_sampledata(Bitstream bitstream) {
      if (this.allocation != 0) {
         this.sample = (float)bitstream.get_bits(this.samplelength);
      }

      if (++this.samplenumber == 12) {
         this.samplenumber = 0;
         return true;
      } else {
         return false;
      }
   }

   public SubbandLayer1(int n) {
      this.subbandnumber = n;
      this.samplenumber = 0;
   }
}
