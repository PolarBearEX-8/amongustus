package net.optifine.config;


public class RangeInt {
   private int min;
   private int max;

   public RangeInt(int n, int n2) {
      this.min = Math.min(n, n2);
      this.max = Math.max(n, n2);
   }

   public int getMax() {
      return this.max;
   }

   public boolean isInRange(int n) {
      return n < this.min ? false : n <= this.max;
   }

   @Override
   public String toString() {
      return "min: " + this.min + ", max: " + this.max;
   }

   public int getMin() {
      return this.min;
   }
}
