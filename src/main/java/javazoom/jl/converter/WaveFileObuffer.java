package javazoom.jl.converter;


import javazoom.jl.decoder.Obuffer;
public class WaveFileObuffer extends Obuffer {
   private short[] buffer;
   private short[] bufferp;
   private int channels;
   private WaveFile outWave;
   public short[] myBuffer = new short[2];

   @Override
   public void close() {
      this.outWave.Close();
   }

   @Override
   public void write_buffer(int n) {
      this.outWave.WriteData(this.buffer, this.bufferp[0]);

      for (int i = 0; i < this.channels; i++) {
         this.bufferp[i] = (short)i;
      }
   }

   @Override
   public void append(int n, short s) {
      this.buffer[this.bufferp[n]] = s;
      this.bufferp[n] = (short)(this.bufferp[n] + this.channels);
   }

   @Override
   public void set_stop_flag() {
   }

   public WaveFileObuffer(int n, int n2, String str) {
      if (str == null) {
         throw new NullPointerException("FileName");
      } else {
         this.buffer = new short[2304];
         this.bufferp = new short[2];
         this.channels = n;

         for (int i = 0; i < n; i++) {
            this.bufferp[i] = (short)i;
         }

         this.outWave = new WaveFile();
         int n3 = this.outWave.OpenForWrite(str, n2, (short)16, (short)this.channels);
      }
   }

   @Override
   public void clear_buffer() {
   }
}
