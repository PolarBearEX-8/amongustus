package javazoom.jl.decoder;

public class SampleBuffer extends Obuffer {
   private short[] buffer = new short[2304];
   private int[] bufferp = new int[2];
   private int channels;
   private int frequency;

   public SampleBuffer(int n, int n2) {
      this.channels = n2;
      this.frequency = n;

      for (int i = 0; i < n2; i++) {
         this.bufferp[i] = (short)i;
      }
   }

   @Override
   public void close() {
   }

   public short[] getBuffer() {
      return this.buffer;
   }

   public int getChannels() {
      return this.channels;
   }

   @Override
   public void set_stop_flag() {
   }

   @Override
   public void append(int n, short s) {
      this.buffer[this.bufferp[n]] = s;
      this.bufferp[n] = this.bufferp[n] + this.channels;
   }

   public int getBufferLength() {
      return this.bufferp[0];
   }

   public int getFrequency() {
      return this.frequency;
   }

   @Override
   public void clear_buffer() {
      for (int i = 0; i < this.channels; i++) {
         this.bufferp[i] = (short)i;
      }
   }

   @Override
   public void appendSamples(int n, float[] floats) {
      int n2 = this.bufferp[n];

      for (int i = 0; i < 32; n2 += this.channels) {
         float f = floats[i++];
         f = f > 32767.0F ? 32767.0F : (f < -32767.0F ? -32767.0F : f);
         short s = (short)((int)f);
         this.buffer[n2] = s;
      }

      this.bufferp[n] = n2;
   }

   @Override
   public void write_buffer(int n) {
   }
}
