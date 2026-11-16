package client.util;

import net.minecraft.util.EnumFacing;

public class SmoothBuilder {
   public static final int[] countArray = new int[EnumFacing.values().length];

   static {
      try {
         countArray[EnumFacing.DOWN.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[EnumFacing.UP.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[EnumFacing.EAST.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[EnumFacing.WEST.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         countArray[EnumFacing.NORTH.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[EnumFacing.SOUTH.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }
   }
}
