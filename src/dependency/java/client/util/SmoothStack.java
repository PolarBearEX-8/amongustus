package client.util;

import client.setting.RenderBooleanProperty;

public class SmoothStack {
   public static final int[] countArray = new int[RenderBooleanProperty.values().length];

   static {
      try {
         countArray[RenderBooleanProperty.NORMAL.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[RenderBooleanProperty.COLOR.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[RenderBooleanProperty.UV.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
