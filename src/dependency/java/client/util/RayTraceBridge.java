package client.util;

import client.setting.MovementStateProperty;

public class RayTraceBridge {
   public static final int[] countArray = new int[MovementStateProperty.values().length];

   static {
      try {
         countArray[MovementStateProperty.END_COMBAT.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementStateProperty.ENTITY_DIED.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }
   }
}
