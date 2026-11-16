package client.util;

import net.minecraft.util.EnumFacing;

public class DistanceDeserializerEnumFacingSwitchMap {
   public static final int[] countArray = new int[EnumFacing.values().length];

   static {
      try {
         countArray[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }
   }
}
