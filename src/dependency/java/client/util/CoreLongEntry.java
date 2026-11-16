package client.util;

public class CoreLongEntry {
   public static long timestamp4 = System.currentTimeMillis();
   public static long timestamp = getTimestamp();
   public static long timestamp5 = timestamp4;
   public static long timestamp2 = timestamp;
   public static boolean enabled = false;
   public static int count = 0;
   public static long timestamp3 = 1048576L;

   public static long getTimestamp2() {
      return timestamp / timestamp3;
   }

   public static long getTimestamp4() {
      return timestamp4;
   }

   public static boolean isEnabled() {
      return enabled;
   }

   public static long getTimestampAsTime() {
      return (long)count / timestamp3;
   }

   private static long getTimestamp() {
      Runtime runtime = Runtime.getRuntime();
      return runtime.totalMemory() - runtime.freeMemory();
   }

   public static void updateState() {
      long timestamp6 = System.currentTimeMillis();
      long timestamp7 = getTimestamp();
      enabled = timestamp7 < timestamp2;
      if (enabled) {
         long value = timestamp5 - timestamp4;
         long value2 = timestamp2 - timestamp;
         double offset = (double)value / 1000.0;
         int count2 = (int)((double)value2 / offset);
         if (count2 > 0) {
            count = count2;
         }

         timestamp4 = timestamp6;
         timestamp = timestamp7;
      }

      timestamp5 = timestamp6;
      timestamp2 = timestamp7;
   }
}
