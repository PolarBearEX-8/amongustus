package client.util;

import client.setting.CombatKeybindProperty;

public class OutdatedServerAuxiliaryICombatKeybindPropertSw {
   public static final int[] countArray = new int[CombatKeybindProperty.values().length];

   static {
      try {
         countArray[CombatKeybindProperty.LOGIN.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[CombatKeybindProperty.STATUS.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }
   }
}
