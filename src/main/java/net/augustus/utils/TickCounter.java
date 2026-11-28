package net.augustus.utils;


public class TickCounter {
   private int ticks = 0;

   public void reset() {
      this.ticks = 0;
   }

   public boolean hasReached(double d) {
      return this.ticks >= (int)d;
   }

   public void increment() {
      this.ticks++;
   }

   public boolean hasReached(int n) {
      return this.ticks >= n;
   }

   public long getTicks() {
      return (long)this.ticks;
   }
}
