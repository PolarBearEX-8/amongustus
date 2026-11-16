package client.util;

import client.setting.CombatModeProperty;

public class GenericIntArrayEntry {
   public static final int[] countArray = new int[CombatModeProperty.values().length];

   static {
      try {
         countArray[CombatModeProperty.SPRINT.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[CombatModeProperty.JUMP_SPRINT.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[CombatModeProperty.MOTION_SPRINT.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
