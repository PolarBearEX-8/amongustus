package cc.baritone.utils;

public class AStarProcessor {
   public static final int[] countArray = new int[HeuristicCoordinator.values().length];

   static {
      try {
         countArray[HeuristicCoordinator.SET_GOAL_AND_PAUSE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[HeuristicCoordinator.REQUEST_PAUSE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[HeuristicCoordinator.CANCEL_AND_SET_GOAL.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[HeuristicCoordinator.FORCE_REVALIDATE_GOAL_AND_PATH.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[HeuristicCoordinator.REVALIDATE_GOAL_AND_PATH.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[HeuristicCoordinator.SET_GOAL_AND_PATH.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }
   }
}
