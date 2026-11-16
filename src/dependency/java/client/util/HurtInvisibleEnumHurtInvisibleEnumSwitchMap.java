package client.util;

public class HurtInvisibleEnumHurtInvisibleEnumSwitchMap {
   public static final int[] countArray = new int[HurtInvisibleEnum.values().length];

   static {
      try {
         countArray[HurtInvisibleEnum.IS_ALIVE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[HurtInvisibleEnum.IS_BURNING.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[HurtInvisibleEnum.IS_CHILD.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[HurtInvisibleEnum.IS_HURT.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[HurtInvisibleEnum.IS_IN_LAVA.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[HurtInvisibleEnum.IS_IN_WATER.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[HurtInvisibleEnum.IS_INVISIBLE.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[HurtInvisibleEnum.IS_ON_GROUND.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray[HurtInvisibleEnum.IS_RIDDEN.ordinal()] = 9;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray[HurtInvisibleEnum.IS_RIDING.ordinal()] = 10;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray[HurtInvisibleEnum.IS_SNEAKING.ordinal()] = 11;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray[HurtInvisibleEnum.IS_SPRINTING.ordinal()] = 12;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      try {
         countArray[HurtInvisibleEnum.IS_WET.ordinal()] = 13;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }
   }
}
