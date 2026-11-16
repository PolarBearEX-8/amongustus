package client.util;

import client.setting.MovementNumberProperty;

public class SmoothChecker {
   public static final int[] countArray2;
   public static final int[] countArray = new int[MovementNumberProperty.values().length];

   static {
      try {
         countArray[MovementNumberProperty.FURNACE.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementNumberProperty.CHEST.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[MovementNumberProperty.TNT.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[MovementNumberProperty.HOPPER.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[MovementNumberProperty.COMMAND_BLOCK.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      countArray2 = new int[MovingObjectType.values().length];

      try {
         countArray2[MovingObjectType.ENTITY.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray2[MovingObjectType.BLOCK.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray2[MovingObjectType.MISS.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }
   }
}
