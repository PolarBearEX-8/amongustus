package client.util;

import client.setting.RenderSliderProperty;

public class CurveBuffer {
   public static final int[] countArray = new int[RenderSliderProperty.values().length];

   static {
      try {
         countArray[RenderSliderProperty.PRE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[RenderSliderProperty.EARLY.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[RenderSliderProperty.LATE.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[RenderSliderProperty.POST.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[RenderSliderProperty.TICK.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }
   }
}
