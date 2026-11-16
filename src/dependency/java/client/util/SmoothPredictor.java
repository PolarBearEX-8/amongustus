package client.util;

import client.setting.DynamicOptionType;

public class SmoothPredictor {
   public static final int[] countArray = new int[DynamicOptionType.values().length];

   static {
      try {
         countArray[DynamicOptionType.SET_SIZE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[DynamicOptionType.LERP_SIZE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[DynamicOptionType.SET_CENTER.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[DynamicOptionType.SET_WARNING_BLOCKS.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[DynamicOptionType.SET_WARNING_TIME.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[DynamicOptionType.INITIALIZE.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }
   }
}
