package client.util;

import client.setting.EnumFacingXType;
import client.setting.MovementFactorProperty;
import net.minecraft.util.EnumFacing;

public class ContextIntArrayEntry {
   public static final int[] countArray;
   public static final int[] countArray2;
   public static final int[] countArray3 = new int[MovementFactorProperty.values().length];

   static {
      try {
         countArray3[MovementFactorProperty.HORIZONTAL.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray3[MovementFactorProperty.VERTICAL.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      countArray2 = new int[EnumFacing.values().length];

      try {
         countArray2[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray2[EnumFacing.EAST.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray2[EnumFacing.SOUTH.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray2[EnumFacing.WEST.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray2[EnumFacing.UP.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray2[EnumFacing.DOWN.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      countArray = new int[EnumFacingXType.values().length];

      try {
         countArray[EnumFacingXType.X.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         countArray[EnumFacingXType.Y.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         countArray[EnumFacingXType.Z.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }
   }
}
