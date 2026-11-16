package client.util;

import client.setting.MovementBooleanProperty;

public class AimSelector {
   public static final int[] countArray = new int[MovementBooleanProperty.values().length];

   static {
      try {
         countArray[MovementBooleanProperty.ALWAYS.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementBooleanProperty.NEVER.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[MovementBooleanProperty.HIDE_FOR_OTHER_TEAMS.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[MovementBooleanProperty.HIDE_FOR_OWN_TEAM.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
