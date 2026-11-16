package cc.baritone.utils;

import client.setting.EnumFacingXType;

public class DepthFirstScheduler {
   public static final int[] countArray = new int[EnumFacingXType.values().length];

   static {
      try {
         countArray[EnumFacingXType.X.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[EnumFacingXType.Y.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[EnumFacingXType.Z.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
