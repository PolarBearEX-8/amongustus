package javazoom.jl.converter;

public class WaveFormatChunkData {
   public short wFormatTag;
   public short nChannels;
   public int nSamplesPerSec;
   public int nAvgBytesPerSec;
   public short nBlockAlign;
   public short nBitsPerSample;
   public final WaveFile waveFile;

   public void Config(int n, short s, short s2) {
      this.nSamplesPerSec = n;
      this.nChannels = s2;
      this.nBitsPerSample = s;
      this.nAvgBytesPerSec = this.nChannels * this.nSamplesPerSec * this.nBitsPerSample / 8;
      this.nBlockAlign = (short)(this.nChannels * this.nBitsPerSample / 8);
   }

   public WaveFormatChunkData(WaveFile waveFile2) {
      this.waveFile = waveFile2;
      this.wFormatTag = 0;
      this.nChannels = 0;
      this.nSamplesPerSec = 0;
      this.nAvgBytesPerSec = 0;
      this.nBlockAlign = 0;
      this.nBitsPerSample = 0;
      this.wFormatTag = 1;
      this.Config(44100, (short)16, (short)1);
   }
}
