package net.augustus.utils;

public class TimeHelper {
   private volatile long lastTime = System.currentTimeMillis();

   public void setLastTime(long l) {
      this.lastTime = l;
   }

   public boolean reached(double delayMs) {
      return (double)Math.max(0L, System.currentTimeMillis() - this.lastTime) >= delayMs;
   }

   public boolean reached(long delayMs) {
      return Math.max(0L, System.currentTimeMillis() - this.lastTime) >= delayMs;
   }

   public void reset() {
      this.lastTime = System.currentTimeMillis();
   }

   public long getTime() {
      return Math.max(0L, System.currentTimeMillis() - this.lastTime);
   }




}
