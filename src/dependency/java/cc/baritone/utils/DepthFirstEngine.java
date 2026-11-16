package cc.baritone.utils;

public class DepthFirstEngine {
   public static final int[] countArray = new int[AuxiliaryOptionType.values().length];

   static {
      try {
         countArray[AuxiliaryOptionType.GOAL_SET.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[AuxiliaryOptionType.PATH_REQUESTED.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[AuxiliaryOptionType.EXECUTING.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
