package client.util;

import client.setting.MovementDelayProperty;

public class GlobalIntArrayEntry {
   public static final int[] countArray = new int[MovementDelayProperty.values().length];

   static {
      try {
         countArray[MovementDelayProperty.UP.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementDelayProperty.DOWN.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[MovementDelayProperty.LEFT.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[MovementDelayProperty.RIGHT.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
