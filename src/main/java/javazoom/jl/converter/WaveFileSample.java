package javazoom.jl.converter;

public class WaveFileSample {
   public short[] chan;
   public final WaveFile waveFile;

   public WaveFileSample(WaveFile waveFile2) {
      this.waveFile = waveFile2;
      this.chan = new short[2];
   }
}
