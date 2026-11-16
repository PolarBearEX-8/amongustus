package net.minecraft.profiler;

public class Profiler {
   public final long[] timestampArray = new long[240];
   public int count2;
   public int count;
   public int count3;

   public void applyTime(long value) {
      this.timestampArray[this.count3] = value;
      this.count3++;
      if (this.count3 == 240) {
         this.count3 = 0;
      }

      if (this.count < 240) {
         this.count2 = 0;
         this.count++;
      } else {
         this.count2 = this.computeCount(this.count3 + 1);
      }
   }

   public long[] getTimestampArray() {
      return this.timestampArray;
   }

   public int computeCount(long value, int count4) {
      return (int)((double)value / 1.6666666E7 * (double)count4);
   }

   public int getCount3() {
      return this.count3;
   }

   public int computeCount(int count4) {
      return count4 % 240;
   }

   public int getCount2() {
      return this.count2;
   }
}
