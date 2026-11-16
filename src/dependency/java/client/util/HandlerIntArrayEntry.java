package client.util;

import client.setting.RenderBooleanProperty;

public class HandlerIntArrayEntry {
   public static final int[] countArray = new int[RenderBooleanProperty.values().length];

   static {
      try {
         countArray[RenderBooleanProperty.POSITION.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[RenderBooleanProperty.UV.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[RenderBooleanProperty.COLOR.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[RenderBooleanProperty.NORMAL.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
