package client.util;

import client.setting.DefaultOptionType;

public class DampenerTransformer {
   public static final int[] countArray = new int[DefaultOptionType.values().length];

   static {
      try {
         countArray[DefaultOptionType.COBBLESTONE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[DefaultOptionType.STONEBRICK.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[DefaultOptionType.MOSSY_STONEBRICK.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[DefaultOptionType.CRACKED_STONEBRICK.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[DefaultOptionType.CHISELED_STONEBRICK.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }
   }
}
