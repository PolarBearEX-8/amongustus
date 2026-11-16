package cc.baritone.utils;

public class CommonCustom {
   public static final int[] countArray = new int[DepthFirstNavigator.values().length];

   static {
      try {
         countArray[DepthFirstNavigator.NO_OPTION.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[DepthFirstNavigator.READY_TO_PLACE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[DepthFirstNavigator.ATTEMPTING.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
