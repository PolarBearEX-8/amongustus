package client.util;

import client.setting.CombatLimitProperty;

public class AdvancedIntArrayHolder {
   public static final int[] countArray = new int[CombatLimitProperty.values().length];

   static {
      try {
         countArray[CombatLimitProperty.COOL_WARM.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[CombatLimitProperty.HEAT_ICE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[CombatLimitProperty.SPECIAL.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
