package cc.baritone.utils;

public class BreadthFirstWatcherHelpeAIRWATEREnumSwitchMap {
   public static final int[] countArray = new int[AIRWATEREnum.values().length];

   static {
      try {
         countArray[AIRWATEREnum.AIR.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[AIRWATEREnum.WATER.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[AIRWATEREnum.AVOID.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[AIRWATEREnum.SOLID.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
