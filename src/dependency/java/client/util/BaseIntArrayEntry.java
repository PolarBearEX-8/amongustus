package client.util;

import client.setting.CrafterSwordType;

public class BaseIntArrayEntry {
   public static final int[] countArray = new int[CrafterSwordType.values().length];

   static {
      try {
         countArray[CrafterSwordType.SWORD.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[CrafterSwordType.CHEST_PLATE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[CrafterSwordType.TROUSERS.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[CrafterSwordType.HELMET.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[CrafterSwordType.SHOES.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[CrafterSwordType.NULL.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }
   }
}
