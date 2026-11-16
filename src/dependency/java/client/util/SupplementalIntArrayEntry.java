package client.util;

import client.setting.MovementThresholdProperty;

public class SupplementalIntArrayEntry {
   public static final int[] countArray = new int[MovementThresholdProperty.values().length];

   static {
      try {
         countArray[MovementThresholdProperty.MONSTER.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementThresholdProperty.CREATURE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[MovementThresholdProperty.WATER_CREATURE.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[MovementThresholdProperty.AMBIENT.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
