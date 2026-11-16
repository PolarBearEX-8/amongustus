package net.minecraft.util;

import client.setting.EnumFacingXType;
import client.setting.MovementFactorProperty;

public class RecordNodeHandler {
   public static final RecordNodeHandler recordNodeHandler = new RecordNodeHandler();
   public final int[] countArray;
   public final int[] countArray2;
   public final int[] countArray3 = new int[MovementFactorProperty.values().length];

   public RecordNodeHandler() {
      try {
         this.countArray3[MovementFactorProperty.HORIZONTAL.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         this.countArray3[MovementFactorProperty.VERTICAL.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      this.countArray2 = new int[EnumFacing.getEntries().length];

      try {
         this.countArray2[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         this.countArray2[EnumFacing.EAST.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         this.countArray2[EnumFacing.SOUTH.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         this.countArray2[EnumFacing.WEST.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }

      try {
         this.countArray2[EnumFacing.UP.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError7) {
      }

      try {
         this.countArray2[EnumFacing.DOWN.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError8) {
      }

      this.countArray = new int[EnumFacingXType.values().length];

      try {
         this.countArray[EnumFacingXType.X.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError9) {
      }

      try {
         this.countArray[EnumFacingXType.Y.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError10) {
      }

      try {
         this.countArray[EnumFacingXType.Z.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError11) {
      }
   }
}
