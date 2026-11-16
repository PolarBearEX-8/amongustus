package client.util;

import client.setting.MovementAlphaProperty;

public class ExternalIntArrayEntry {
   public static final int[] countArray = new int[MovementAlphaProperty.values().length];

   static {
      try {
         countArray[MovementAlphaProperty.FILE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementAlphaProperty.SOUND_EVENT.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }
   }
}
