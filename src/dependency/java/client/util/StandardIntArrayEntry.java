package client.util;

import client.setting.MovementFilterProperty;
import client.setting.StaffDetectorAddPlayerType;

public class StandardIntArrayEntry {
   public static final int[] countArray2;
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

      countArray2 = new int[MovementFilterProperty.values().length];

      try {
         countArray2[MovementFilterProperty.TITLE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray2[MovementFilterProperty.SUBTITLE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray2[MovementFilterProperty.RESET.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }
   }
}
