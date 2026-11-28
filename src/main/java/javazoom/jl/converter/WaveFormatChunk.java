package javazoom.jl.converter;


public class WaveFormatChunk {
   public RiffChunkHeader header;
   public WaveFormatChunkData data;
   public final WaveFile waveFile;

   public WaveFormatChunk(WaveFile waveFile2) {
      this.waveFile = waveFile2;
      this.header = new RiffChunkHeader(waveFile2);
      this.data = new WaveFormatChunkData(waveFile2);
      this.header.ckID = RiffFile.FourCC("fmt ");
      this.header.ckSize = 16;
   }

   public int VerifyValidity() {
      boolean flag = this.header.ckID == RiffFile.FourCC("fmt ")
         && (this.data.nChannels == 1 || this.data.nChannels == 2)
         && this.data.nAvgBytesPerSec == this.data.nChannels * this.data.nSamplesPerSec * this.data.nBitsPerSample / 8
         && this.data.nBlockAlign == this.data.nChannels * this.data.nBitsPerSample / 8;
      return flag ? 1 : 0;
   }
}
