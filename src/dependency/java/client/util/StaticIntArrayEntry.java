package client.util;

import client.setting.MovementNumberProperty;
import client.setting.MovementProfileProperty;

public class StaticIntArrayEntry {
   public static final int[] countArray;
   public static final int[] countArray2 = new int[MovementProfileProperty.values().length];

   static {
      try {
         countArray2[MovementProfileProperty.ASCENDING_EAST.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray2[MovementProfileProperty.ASCENDING_WEST.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray2[MovementProfileProperty.ASCENDING_NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray2[MovementProfileProperty.ASCENDING_SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      countArray = new int[MovementNumberProperty.values().length];

      try {
         countArray[MovementNumberProperty.CHEST.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[MovementNumberProperty.FURNACE.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[MovementNumberProperty.TNT.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[MovementNumberProperty.SPAWNER.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray[MovementNumberProperty.HOPPER.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray[MovementNumberProperty.COMMAND_BLOCK.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }
   }
}
