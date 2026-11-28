package javazoom.jl.converter;

public class WaveFile extends RiffFile {
   public static final int MAX_WAVE_CHANNELS = 2;
   private WaveFormatChunk wave_format;
   private RiffChunkHeader pcm_data;
   private long pcm_data_offset = 0L;
   private int num_samples = 0;

   public int OpenForWrite(String str, int n, short s, short s2) {
      if (str != null && (s == 8 || s == 16) && s2 >= 1 && s2 <= 2) {
         this.wave_format.data.Config(n, s, s2);
         int n2 = this.Open(str, 1);
         if (n2 == 0) {
            n2 = this.Write2(new byte[]{87, 65, 86, 69}, 4);
            if (n2 == 0) {
               n2 = this.Write(this.wave_format.header, 8);
               n2 = this.Write5(this.wave_format.data.wFormatTag, 2);
               n2 = this.Write5(this.wave_format.data.nChannels, 2);
               n2 = this.Write4(this.wave_format.data.nSamplesPerSec, 4);
               n2 = this.Write4(this.wave_format.data.nAvgBytesPerSec, 4);
               n2 = this.Write5(this.wave_format.data.nBlockAlign, 2);
               n2 = this.Write5(this.wave_format.data.nBitsPerSample, 2);
               if (n2 == 0) {
                  this.pcm_data_offset = this.CurrentFilePosition();
                  n2 = this.Write(this.pcm_data, 8);
               }
            }
         }

         return n2;
      } else {
         return 4;
      }
   }

   public short BitsPerSample() {
      return this.wave_format.data.nBitsPerSample;
   }

   @Override
   public long CurrentFilePosition() {
      return super.CurrentFilePosition();
   }

   public short NumChannels() {
      return this.wave_format.data.nChannels;
   }

   public WaveFile() {
      this.pcm_data = new RiffChunkHeader(this);
      this.wave_format = new WaveFormatChunk(this);
      this.pcm_data.ckID = FourCC("data");
      this.pcm_data.ckSize = 0;
      this.num_samples = 0;
   }

   public int getNumSamples() {
      return this.num_samples;
   }

   public int SamplingRate() {
      return this.wave_format.data.nSamplesPerSec;
   }

   @Override
   public int Close() {
      int n = 0;
      if (this.fmode == 1) {
         n = this.Backpatch2(this.pcm_data_offset, this.pcm_data, 8);
      }

      if (n == 0) {
         n = super.Close();
      }

      return n;
   }

   public int OpenForWrite(String str, WaveFile waveFile) {
      return this.OpenForWrite(str, waveFile.SamplingRate(), waveFile.BitsPerSample(), waveFile.NumChannels());
   }

   public int WriteData(short[] sArr, int n) {
      int n2 = n * 2;
      this.pcm_data.ckSize += n2;
      return super.Write3(sArr, n2);
   }
}
