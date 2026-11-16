package client.util;

import net.minecraft.util.EnumFacing;

public class AuxiliaryIntArrayEntry {
   public static final int[] $SwitchMap$EnumFacing = new int[EnumFacing.values().length];

   static {
      try {
         $SwitchMap$EnumFacing[EnumFacing.SOUTH.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         $SwitchMap$EnumFacing[EnumFacing.NORTH.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         $SwitchMap$EnumFacing[EnumFacing.EAST.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         $SwitchMap$EnumFacing[EnumFacing.WEST.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         $SwitchMap$EnumFacing[EnumFacing.UP.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         $SwitchMap$EnumFacing[EnumFacing.DOWN.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }
   }
}
