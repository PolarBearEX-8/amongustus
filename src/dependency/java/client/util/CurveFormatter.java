package client.util;

import client.setting.MovementOffsetProperty;

public class CurveFormatter {
   public static final int[] countArray = new int[MovementOffsetProperty.values().length];

   static {
      try {
         countArray[MovementOffsetProperty.DIRT.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementOffsetProperty.COARSE_DIRT.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }
   }
}
