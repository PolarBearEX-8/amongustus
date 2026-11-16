package client.util;

import client.setting.MovementIntervalProperty;

public class BezierEstimatorMovementIntervalPropSwitchMap {
   public static final int[] countArray = new int[MovementIntervalProperty.values().length];

   static {
      try {
         countArray[MovementIntervalProperty.WINDOWS.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementIntervalProperty.OSX.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[MovementIntervalProperty.LINUX.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
