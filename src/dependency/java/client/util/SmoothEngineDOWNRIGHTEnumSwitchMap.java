package client.util;

public class SmoothEngineDOWNRIGHTEnumSwitchMap {
   public static final int[] countArray = new int[DOWNRIGHTEnum.values().length];

   static {
      try {
         countArray[DOWNRIGHTEnum.UP.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[DOWNRIGHTEnum.UP_RIGHT.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[DOWNRIGHTEnum.RIGHT.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[DOWNRIGHTEnum.DOWN_RIGHT.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[DOWNRIGHTEnum.DOWN.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[DOWNRIGHTEnum.DOWN_LEFT.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[DOWNRIGHTEnum.LEFT.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[DOWNRIGHTEnum.UP_LEFT.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }
   }
}
