package client.util;

import client.setting.MovementValueProperty;

public class AngleParser {
   public static final int[] countArray = new int[MovementValueProperty.values().length];

   static {
      try {
         countArray[MovementValueProperty.MICROSOFT_CREDENTIALS.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementValueProperty.MICROSOFT_DEVICE_CODE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[MovementValueProperty.MICROSOFT_EXTERNAL_BROWSER.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[MovementValueProperty.CRACKED.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[MovementValueProperty.THE_ALTENING.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }
   }
}
