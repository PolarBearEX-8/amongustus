package net.minecraft.util;

import client.setting.EnumFacingXType;

public class RecordNodeImpl {
   public static final RecordNodeImpl recordNodeImpl = new RecordNodeImpl();
   public final int[] countArray;
   public final int[] countArray2 = new int[EnumFacingXType.values().length];

   public RecordNodeImpl() {
      try {
         this.countArray2[EnumFacingXType.X.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         this.countArray2[EnumFacingXType.Y.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         this.countArray2[EnumFacingXType.Z.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      this.countArray = new int[EnumFacing.getEntries().length];

      try {
         this.countArray[EnumFacing.DOWN.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         this.countArray[EnumFacing.UP.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         this.countArray[EnumFacing.NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         this.countArray[EnumFacing.SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         this.countArray[EnumFacing.WEST.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      try {
         this.countArray[EnumFacing.EAST.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }
   }
}
