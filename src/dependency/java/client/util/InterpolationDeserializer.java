package client.util;

import net.minecraft.util.EnumFacing;

public class InterpolationDeserializer {
   public static final int[] countArray = new int[EnumFacing.values().length];

   static {
      try {
         countArray[EnumFacing.SOUTH.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[EnumFacing.NORTH.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
