package client.util;

import client.setting.SupplementalOptionType;

public class SystemIntArrayEntry {
   public static final int[] countArray = new int[SupplementalOptionType.values().length];

   static {
      try {
         countArray[SupplementalOptionType.READ_NETTY.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[SupplementalOptionType.SEND.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[SupplementalOptionType.SEND_NETTY.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[SupplementalOptionType.SEND_NETTY_DELAY.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[SupplementalOptionType.READ.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }
   }
}
