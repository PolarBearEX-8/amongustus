package client.util;

import client.setting.MovementProfileProperty;

public class DampenerSystem {
   public static final int[] countArray = new int[MovementProfileProperty.values().length];

   static {
      try {
         countArray[MovementProfileProperty.NORTH_SOUTH.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[MovementProfileProperty.EAST_WEST.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[MovementProfileProperty.ASCENDING_EAST.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[MovementProfileProperty.ASCENDING_WEST.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[MovementProfileProperty.ASCENDING_NORTH.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[MovementProfileProperty.ASCENDING_SOUTH.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[MovementProfileProperty.SOUTH_EAST.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[MovementProfileProperty.SOUTH_WEST.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray[MovementProfileProperty.NORTH_WEST.ordinal()] = 9;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray[MovementProfileProperty.NORTH_EAST.ordinal()] = 10;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }
   }
}
