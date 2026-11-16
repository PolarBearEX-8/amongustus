package client.util;

import client.setting.CombatSpeedProperty;

public class BezierMonitor {
   public static final int[] countArray = new int[CombatSpeedProperty.values().length];

   static {
      try {
         countArray[CombatSpeedProperty.SOCKS4.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[CombatSpeedProperty.SOCKS5.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }
   }
}
