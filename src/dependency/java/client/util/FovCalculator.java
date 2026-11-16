package client.util;

import client.setting.RenderOptionProperty;

public class FovCalculator {
   public static final int[] countArray = new int[RenderOptionProperty.values().length];

   static {
      try {
         countArray[RenderOptionProperty.ALL_STEM.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[RenderOptionProperty.ALL_INSIDE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[RenderOptionProperty.STEM.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
