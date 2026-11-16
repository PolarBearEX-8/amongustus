package client.util;

import net.minecraft.util.EnumFacing;

public class SecondaryIntArrayEntry {
   public static final int[] $SwitchMap$EnumFacing = new int[EnumFacing.values().length];

   static {
      try {
         $SwitchMap$EnumFacing[EnumFacing.EAST.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         $SwitchMap$EnumFacing[EnumFacing.WEST.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         $SwitchMap$EnumFacing[EnumFacing.SOUTH.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         $SwitchMap$EnumFacing[EnumFacing.NORTH.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
