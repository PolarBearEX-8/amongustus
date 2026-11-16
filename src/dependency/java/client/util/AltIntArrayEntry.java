package client.util;

import client.setting.AuxiliaryOptionType;

public class AltIntArrayEntry {
   public static final int[] countArray = new int[AuxiliaryOptionType.values().length];

   static {
      try {
         countArray[AuxiliaryOptionType.RENDER3D.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[AuxiliaryOptionType.RENDER2D_PRE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[AuxiliaryOptionType.RENDER2D_POST.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }
   }
}
