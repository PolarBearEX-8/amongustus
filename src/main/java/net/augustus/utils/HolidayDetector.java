package net.augustus.utils;

public class HolidayDetector {
   private static ClientHoliday detectedHoliday;
   private static boolean checked;

   private static ClientHoliday detectHoliday() {
      for (ClientHoliday clientHoliday : ClientHoliday.values()) {
         if (clientHoliday.active) {
            return clientHoliday;
         }
      }

      return null;
   }

   public static ClientHoliday getCurrentHoliday() {
      if (!checked) {
         detectedHoliday = detectHoliday();
         checked = true;
      }

      return detectedHoliday;
   }



   static {
      detectedHoliday = null;
      checked = false;
   }
}
