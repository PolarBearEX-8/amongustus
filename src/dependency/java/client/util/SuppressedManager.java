package client.util;

public class SuppressedManager {
   public static boolean enabled2 = false;
   public static long timestamp = -1L;
   public static int count3 = 0;
   public static int count = 0;
   public static boolean enabled = false;
   public static boolean enabled3 = false;
   public static final int count2 = 10;

   public static void updateState() {
      enabled2 = true;
      if (timestamp < 0L) {
         timestamp = System.currentTimeMillis();
      }

      if (System.currentTimeMillis() > timestamp + 3000L) {
         if (count > 0) {
            BuildManager.applyText2("Suppressed " + count3 + " OpenGL errors");
         }

         enabled = count3 > 10;
         timestamp = System.currentTimeMillis();
         count3 = 0;
         count = 0;
         enabled3 = true;
      }
   }

   public static boolean checkIndex(int count4) {
      if (!enabled2) {
         return true;
      } else {
         count3++;
         if (enabled3) {
            enabled3 = false;
            return true;
         } else {
            if (enabled) {
               count++;
            }

            return !enabled;
         }
      }
   }
}
