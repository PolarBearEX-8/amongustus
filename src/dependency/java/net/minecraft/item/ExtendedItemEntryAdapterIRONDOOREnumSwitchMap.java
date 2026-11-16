package net.minecraft.item;

import net.minecraft.util.EnumFacing;

public class ExtendedItemEntryAdapterIRONDOOREnumSwitchMap {
   public static final int[] countArray;
   public static final int[] countArray2 = new int[IRONDOOREnum.values().length];

   static {
      try {
         countArray2[IRONDOOREnum.OPENING.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         countArray2[IRONDOOREnum.WOOD_DOOR.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         countArray2[IRONDOOREnum.GRATES.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         countArray2[IRONDOOREnum.IRON_DOOR.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      countArray = new int[EnumFacing.values().length];

      try {
         countArray[EnumFacing.SOUTH.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         countArray[EnumFacing.WEST.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         countArray[EnumFacing.EAST.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         countArray[EnumFacing.NORTH.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }
   }
}
