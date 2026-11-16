package client.util;

import client.setting.AdapterOptionType;
import client.setting.EnumFacingXType;
import net.minecraft.util.EnumFacing;

public class SmoothConverter {
   public static final int[] countArray2;
   public static final int[] countArray;
   public static final int[] countArray3 = new int[EnumFacingXType.values().length];

   static {
      try {
         countArray3[EnumFacingXType.X.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray3[EnumFacingXType.Z.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      countArray = new int[AdapterOptionType.values().length];

      try {
         countArray[AdapterOptionType.EAST.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[AdapterOptionType.WEST.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[AdapterOptionType.SOUTH.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[AdapterOptionType.NORTH.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[AdapterOptionType.UP_Z.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[AdapterOptionType.UP_X.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray[AdapterOptionType.DOWN_X.ordinal()] = 7;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray[AdapterOptionType.DOWN_Z.ordinal()] = 8;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      countArray2 = new int[EnumFacing.values().length];

      try {
         countArray2[EnumFacing.DOWN.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }

      try {
         countArray2[EnumFacing.UP.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError12) {
      }

      try {
         countArray2[EnumFacing.NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError13) {
      }

      try {
         countArray2[EnumFacing.SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError14) {
      }

      try {
         countArray2[EnumFacing.WEST.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError15) {
      }

      try {
         countArray2[EnumFacing.EAST.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError16) {
      }
   }
}
