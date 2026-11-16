package net.minecraft.util;

public class RecordNode {
   public static final RecordNode recordNode = new RecordNode();
   public final int[] countArray = new int[EnumFacing.getEntries().length];

   public RecordNode() {
      try {
         this.countArray[EnumFacing.DOWN.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         this.countArray[EnumFacing.UP.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         this.countArray[EnumFacing.NORTH.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         this.countArray[EnumFacing.SOUTH.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }

      try {
         this.countArray[EnumFacing.WEST.ordinal()] = 5;
      } catch (NoSuchFieldError noSuchFieldError5) {
      }

      try {
         this.countArray[EnumFacing.EAST.ordinal()] = 6;
      } catch (NoSuchFieldError noSuchFieldError6) {
      }
   }
}
