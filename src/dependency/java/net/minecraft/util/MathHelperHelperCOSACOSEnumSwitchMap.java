package net.minecraft.util;

import client.util.COSACOSEnum;

public class MathHelperHelperCOSACOSEnumSwitchMap {
   public static final int[] countArray = new int[COSACOSEnum.values().length];

   static {
      try {
         countArray[COSACOSEnum.SIN.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[COSACOSEnum.COS.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[COSACOSEnum.ASIN.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[COSACOSEnum.ACOS.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
