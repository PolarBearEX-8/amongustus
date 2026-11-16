package client.util;

import client.setting.StaffDetectorAddPlayerType;

public class ExtraIntArrayEntry {
   public static final int[] countArray = new int[StaffDetectorAddPlayerType.values().length];

   static {
      try {
         countArray[StaffDetectorAddPlayerType.ADD_PLAYER.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[StaffDetectorAddPlayerType.UPDATE_GAME_MODE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[StaffDetectorAddPlayerType.UPDATE_LATENCY.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[StaffDetectorAddPlayerType.UPDATE_DISPLAY_NAME.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[StaffDetectorAddPlayerType.REMOVE_PLAYER.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }
   }
}
