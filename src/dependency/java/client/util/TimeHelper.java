package client.util;

public class TimeHelper {
   public long lastMs = System.currentTimeMillis();

   public void reset() {
      this.lastMs = System.currentTimeMillis();
   }

   public boolean hasReached(long value) {
      return Math.max(0L, System.currentTimeMillis() - this.lastMs) >= value;
   }

   public static String[] getStringArray() {
      return null;
   }

   public long getTime() {
      return Math.max(0L, System.currentTimeMillis() - this.lastMs);
   }

   public void setEpochMillis(long epochMillis) {
      this.lastMs = epochMillis;
   }

   public boolean hasReachedDouble(double offset) {
      return (double)Math.max(0L, System.currentTimeMillis() - this.lastMs) >= offset;
   }
}
