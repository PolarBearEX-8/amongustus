package cc.baritone.utils;

import client.setting.EnumFacingXType;

public final class DepthFirstHandler extends AbstractDirectListener implements BreadthFirstEngine {
   public final double value;
   public final double value2;
   public final double value3;
   public final double value4;
   public final boolean enabled;
   public final EnumFacingXType enumFacingXType;

   public DepthFirstHandler(int count, int count2, int count3, boolean flag, EnumFacingXType enumFacingXType2) {
      super(count, count2, count3);
      this.value = (double)computeCount(count, count2, enumFacingXType2) / 2.0;
      this.value2 = (double)computeCountByEnumFacingXType(count2, count3, enumFacingXType2) / 2.0;
      this.value3 = (this.value - 1.0) * (this.value - 1.0);
      this.value4 = (this.value2 - 1.0) * (this.value2 - 1.0);
      this.enabled = flag;
      this.enumFacingXType = enumFacingXType2;
   }

   private static int computeCount(int count, int count2, EnumFacingXType enumFacingXType2) {
      return enumFacingXType2 == EnumFacingXType.X ? count2 : count;
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) {
      double offset = Math.abs((double)computeCount(count, count2, this.enumFacingXType) + 0.5 - this.value);
      double offset2 = Math.abs((double)computeCountByEnumFacingXType(count2, count3, this.enumFacingXType) + 0.5 - this.value2);
      return this.checkDouble(offset, offset2) ? false : this.enabled || this.checkDouble(offset + 1.0, offset2) || this.checkDouble(offset, offset2 + 1.0);
   }

   private boolean checkDouble(double offset, double offset2) {
      return offset * offset / this.value3 + offset2 * offset2 / this.value4 > 1.0;
   }

   private static int computeCountByEnumFacingXType(int count, int count2, EnumFacingXType enumFacingXType2) {
      return enumFacingXType2 == EnumFacingXType.Z ? count : count2;
   }
}
