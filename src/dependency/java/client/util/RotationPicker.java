package client.util;

import client.setting.MovementProperty;

public class RotationPicker {
   public static final int[] countArray = new int[MovementProperty.values().length];

   static {
      try {
         countArray[MovementProperty.SPRUCE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementProperty.BIRCH.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[MovementProperty.JUNGLE.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[MovementProperty.ACACIA.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[MovementProperty.DARK_OAK.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[MovementProperty.OAK.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }
   }
}
