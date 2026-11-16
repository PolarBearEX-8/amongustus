package client.util;

import client.setting.ItemNoneType;

public class CurveSelector {
   public static final int[] countArray = new int[ItemNoneType.values().length];

   static {
      try {
         countArray[ItemNoneType.NONE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[ItemNoneType.EAT.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[ItemNoneType.DRINK.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[ItemNoneType.BLOCK.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[ItemNoneType.BOW.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }
   }
}
