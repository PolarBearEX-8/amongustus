package client.util;

import client.setting.MovementSpeedProperty;

public class SimpleIntArrayEntry {
   public static final int[] countArray = new int[MovementSpeedProperty.values().length];

   static {
      try {
         countArray[MovementSpeedProperty.LEFT.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementSpeedProperty.MID.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }
   }
}
