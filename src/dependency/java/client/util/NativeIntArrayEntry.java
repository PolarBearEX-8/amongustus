package client.util;

import client.setting.CombatValueProperty;

public class NativeIntArrayEntry {
   public static final int[] countArray = new int[CombatValueProperty.values().length];

   static {
      try {
         countArray[CombatValueProperty.FLOAT.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[CombatValueProperty.UINT.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[CombatValueProperty.INT.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[CombatValueProperty.USHORT.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[CombatValueProperty.SHORT.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[CombatValueProperty.UBYTE.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[CombatValueProperty.BYTE.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }
   }
}
