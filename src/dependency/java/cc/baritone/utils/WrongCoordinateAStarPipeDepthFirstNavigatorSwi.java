package cc.baritone.utils;

public class WrongCoordinateAStarPipeDepthFirstNavigatorSwi {
   public static final int[] countArray = new int[DepthFirstNavigator.values().length];

   static {
      try {
         countArray[DepthFirstNavigator.READY_TO_PLACE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[DepthFirstNavigator.ATTEMPTING.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }
   }
}
