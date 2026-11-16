package client.util;

public class SmoothDamper {
   public double value;
   public final double value2;

   public boolean checkCondition(int count) {
      return this.getInt(count) > 0;
   }

   public int getInt(int count) {
      int value3 = (int)this.value;
      this.value = this.value + (double)Math.max(0, count) / this.value2;
      int value4 = (int)this.value;
      if (this.value >= (double)Math.max(1, count)) {
         this.value = 0.0;
      }

      return value4 - value3;
   }

   public SmoothDamper(int count, double offset) {
      this.value2 = (double)count;
      this.value = offset % (double)count;
   }
}
