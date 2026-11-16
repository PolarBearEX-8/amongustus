package client.util;

public class SPRINTINGEnumSPRINTINGEnumSwitchMap {
   public static final int[] countArray = new int[SPRINTINGEnum.values().length];

   static {
      try {
         countArray[SPRINTINGEnum.IS_ALIVE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[SPRINTINGEnum.IS_BURNING.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[SPRINTINGEnum.IS_CHILD.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[SPRINTINGEnum.IS_HURT.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[SPRINTINGEnum.IS_IN_LAVA.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[SPRINTINGEnum.IS_IN_WATER.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[SPRINTINGEnum.IS_INVISIBLE.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[SPRINTINGEnum.IS_ON_GROUND.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray[SPRINTINGEnum.IS_RIDDEN.ordinal()] = 9;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray[SPRINTINGEnum.IS_RIDING.ordinal()] = 10;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray[SPRINTINGEnum.IS_SNEAKING.ordinal()] = 11;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray[SPRINTINGEnum.IS_SPRINTING.ordinal()] = 12;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      try {
         countArray[SPRINTINGEnum.IS_WET.ordinal()] = 13;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }
   }
}
