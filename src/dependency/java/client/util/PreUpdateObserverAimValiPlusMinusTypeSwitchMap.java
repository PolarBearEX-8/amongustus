package client.util;

public class PreUpdateObserverAimValiPlusMinusTypeSwitchMap {
   public static final int[] countArray = new int[PlusMinusType.values().length];

   static {
      try {
         countArray[PlusMinusType.SMOOTH.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }
   }
}
