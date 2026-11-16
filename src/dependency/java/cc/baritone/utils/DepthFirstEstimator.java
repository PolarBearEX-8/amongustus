package cc.baritone.utils;

public final class DepthFirstEstimator extends AbstractDirectListener implements BreadthFirstEngine {
   public final double value;
   public final double value2;
   public final double value3;
   public final double value4;
   public final double value5;
   public final double value6;
   public final boolean enabled;

   private boolean checkDouble(double posX, double posY, double posZ) {
      return posX * posX / this.value4 + posY * posY / this.value5 + posZ * posZ / this.value6 > 1.0;
   }

   @Override
   public boolean checkIndex(int count, int count2, int count3) {
      double offset = Math.abs((double)count + 0.5 - this.value);
      double offset2 = Math.abs((double)count2 + 0.5 - this.value2);
      double offset3 = Math.abs((double)count3 + 0.5 - this.value3);
      return this.checkDouble(offset, offset2, offset3)
         ? false
         : this.enabled || this.checkDouble(offset + 1.0, offset2, offset3) || this.checkDouble(offset, offset2 + 1.0, offset3) || this.checkDouble(offset, offset2, offset3 + 1.0);
   }

   public DepthFirstEstimator(int count, int count2, int count3, boolean flag) {
      super(count, count2, count3);
      this.value = (double)count / 2.0;
      this.value2 = (double)count2 / 2.0;
      this.value3 = (double)count3 / 2.0;
      this.value4 = this.value * this.value;
      this.value5 = this.value2 * this.value2;
      this.value6 = this.value3 * this.value3;
      this.enabled = flag;
   }
}
