package cc.baritone.api;

import cc.baritone.utils.ExtendedOptionType;

public class IntArrayEntry {
   public static final int[] countArray = new int[ExtendedOptionType.values().length];

   static {
      try {
         countArray[ExtendedOptionType.MOJANG.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[ExtendedOptionType.THEALTENING.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }
   }
}
