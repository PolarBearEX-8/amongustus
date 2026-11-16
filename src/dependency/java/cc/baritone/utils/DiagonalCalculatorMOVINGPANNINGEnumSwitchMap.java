package cc.baritone.utils;

public class DiagonalCalculatorMOVINGPANNINGEnumSwitchMap {
   public static final int[] countArray = new int[MOVINGPANNINGEnum.values().length];

   static {
      try {
         countArray[MOVINGPANNINGEnum.PANNING.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MOVINGPANNINGEnum.REQUESTING.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }
   }
}
