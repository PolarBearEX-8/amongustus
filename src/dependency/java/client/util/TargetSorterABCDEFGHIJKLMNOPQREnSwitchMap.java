package client.util;

public class TargetSorterABCDEFGHIJKLMNOPQREnSwitchMap {
   public static final int[] countArray = new int[ABCDEFGHIJKLMNOPQREnum.values().length];

   static {
      try {
         countArray[ABCDEFGHIJKLMNOPQREnum.NUMBER.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[ABCDEFGHIJKLMNOPQREnum.IDENTIFIER.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[ABCDEFGHIJKLMNOPQREnum.BRACKET_OPEN.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[ABCDEFGHIJKLMNOPQREnum.OPERATOR.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
