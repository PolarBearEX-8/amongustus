package client.util;

import client.setting.GlStateManagerSType;

public class DampenerAccumulator {
   public static final int[] countArray = new int[GlStateManagerSType.values().length];

   static {
      try {
         countArray[GlStateManagerSType.S.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[GlStateManagerSType.T.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[GlStateManagerSType.R.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[GlStateManagerSType.Q.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
