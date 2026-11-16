package client.util;

import client.setting.CommonOptionType;

public class SmoothEstimator {
   public static final int[] countArray = new int[CommonOptionType.values().length];

   static {
      try {
         countArray[CommonOptionType.DEFAULT.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[CommonOptionType.CHISELED.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[CommonOptionType.LINES_Y.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[CommonOptionType.LINES_X.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[CommonOptionType.LINES_Z.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }
   }
}
