package net.optifine.util;

public class CounterInt {
   private int startValue;
   private int value;

   public synchronized int nextValue() {
      return this.value++;
   }

   public int getValue() {
      return this.value;
   }

   public CounterInt(int n) {
      this.startValue = n;
      this.value = n;
   }

   public synchronized void reset() {
      this.value = this.startValue;
   }
}
