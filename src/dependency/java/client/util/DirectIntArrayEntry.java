package client.util;

import client.setting.MovementWeightProperty;

public class DirectIntArrayEntry {
   public static final int[] countArray = new int[MovementWeightProperty.values().length];

   static {
      try {
         countArray[MovementWeightProperty.SHA3_224.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementWeightProperty.SHA3_256.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[MovementWeightProperty.SHA3_384.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[MovementWeightProperty.SHA3_512.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
