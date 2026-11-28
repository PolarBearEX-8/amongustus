package net.augustus.utils;


public class ActionRateAccumulator {
   private double accumulator;
   private final double interval;

   public boolean shouldTrigger(int n) {
      return this.accumulate(n) > 0;
   }

   public ActionRateAccumulator(int n, double d) {
      this.interval = (double)n;
      this.accumulator = d % (double)n;
   }

   public int accumulate(int n) {
      int accumulator2 = (int)this.accumulator;
      this.accumulator = this.accumulator + (double)Math.max(0, n) / this.interval;
      int accumulator3 = (int)this.accumulator;
      if (this.accumulator >= (double)Math.max(1, n)) {
         this.accumulator = 0.0;
      }

      return accumulator3 - accumulator2;
   }
}
