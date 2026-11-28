package net.minecraft.util;

public class FrameTimer {
   private final long[] frames = new long[240];
   private int lastIndex;
   private int counter;
   private int index;

   public int parseIndex(int n) {
      return n % 240;
   }

   public int getLagometerValue(long l, int n) {
      return (int)(((double)l / 1.6666666E7) * (double)n);
   }

   public long[] getFrames() {
      return this.frames;
   }

   public int getIndex() {
      return this.index;
   }

   public void addFrame(long l) {
      this.frames[this.index] = l;
      this.index++;
      if (this.index == 240) {
         this.index = 0;
      }

      if (this.counter < 240) {
         this.lastIndex = 0;
         this.counter++;
      } else {
         this.lastIndex = this.parseIndex(this.index + 1);
      }
   }

   public int getLastIndex() {
      return this.lastIndex;
   }
}
