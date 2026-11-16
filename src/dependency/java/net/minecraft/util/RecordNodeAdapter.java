package net.minecraft.util;

public class RecordNodeAdapter {
   public static final RecordNodeAdapter recordNodeAdapter = new RecordNodeAdapter();
   public final int[] countArray = new int[EnumFacing.getEntries().length];

   public RecordNodeAdapter() {
      try {
         this.countArray[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError noSuchFieldError) {
      }

      try {
         this.countArray[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError noSuchFieldError2) {
      }

      try {
         this.countArray[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError noSuchFieldError3) {
      }

      try {
         this.countArray[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError noSuchFieldError4) {
      }
   }
}
