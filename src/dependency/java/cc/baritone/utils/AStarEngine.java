package cc.baritone.utils;

public class AStarEngine {
   public static final int[] countArray = new int[DepthFirstPlanner.values().length];

   static {
      try {
         countArray[DepthFirstPlanner.UNKNOWN.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[DepthFirstPlanner.NOT_EXPLORED.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[DepthFirstPlanner.EXPLORED.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
