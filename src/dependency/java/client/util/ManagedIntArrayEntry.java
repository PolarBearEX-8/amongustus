package client.util;

import client.setting.EnumFacingXType;
import net.minecraft.util.EnumFacing;

public class ManagedIntArrayEntry {
   public static final int[] countArray2;
   public static final int[] countArray = new int[EnumFacingXType.values().length];

   static {
      try {
         countArray[EnumFacingXType.X.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[EnumFacingXType.Y.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[EnumFacingXType.Z.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      countArray2 = new int[EnumFacing.values().length];

      try {
         countArray2[EnumFacing.DOWN.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray2[EnumFacing.UP.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray2[EnumFacing.NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray2[EnumFacing.SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray2[EnumFacing.WEST.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         countArray2[EnumFacing.EAST.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }
   }
}
