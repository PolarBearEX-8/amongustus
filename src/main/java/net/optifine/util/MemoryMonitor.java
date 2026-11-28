package net.optifine.util;

public class MemoryMonitor {
   private static long startTimeMs = System.currentTimeMillis();
   private static long startMemory = getMemoryUsed();
   private static long lastTimeMs = startTimeMs;
   private static long lastMemory = startMemory;
   private static boolean gcEvent = false;
   private static int memBytesSec = 0;
   private static long MB = 1048576L;

   public static boolean isGcEvent() {
      return gcEvent;
   }

   public static long getStartMemoryMb() {
      return startMemory / MB;
   }

   public static long getAllocationRateMb() {
      return (long)memBytesSec / MB;
   }

   public static void update() {
      long l = System.currentTimeMillis();
      long memoryUsed = getMemoryUsed();
      gcEvent = memoryUsed < lastMemory;
      if (gcEvent) {
         long l2 = lastTimeMs - startTimeMs;
         int n = (int)((double)(lastMemory - startMemory) / ((double)l2 / 1000.0));
         if (n > 0) {
            memBytesSec = n;
         }

         startTimeMs = l;
         startMemory = memoryUsed;
      }

      lastTimeMs = l;
      lastMemory = memoryUsed;
   }

   private static long getMemoryUsed() {
      Runtime runtime = Runtime.getRuntime();
      return runtime.totalMemory() - runtime.freeMemory();
   }

   public static long getStartTimeMs() {
      return startTimeMs;
   }
}
