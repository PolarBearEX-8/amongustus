package cc.baritone.utils;

public class BreadthFirstCalibrator extends ExtraCustom {
   public short[] shortCodeArray = new short[2304];
   public int[] countArray = new int[2];
   public int count3;
   public int count4;

   public BreadthFirstCalibrator(int count, int count2) {
      this.count3 = count2;
      this.count4 = count;

      for (int index = 0; index < count2; index++) {
         this.countArray[index] = (short)index;
      }
   }

   public int getCount4() {
      return this.count4;
   }

   @Override
   public void updateState() {
   }

   @Override
   public void applyFloatArray(int count, float[] factorArray) {
      int count2 = this.countArray[count];

      for (int index = 0; index < 32; count2 += this.count3) {
         float factor = factorArray[index++];
         short shortValue = (short)((int)(factor > 32767.0F ? 32767.0F : (factor < -32767.0F ? -32767.0F : factor)));
         this.shortCodeArray[count2] = shortValue;
      }

      this.countArray[count] = count2;
   }

   @Override
   public void applyIndex(int count) {
   }

   @Override
   public void updateState2() {
      for (int index = 0; index < this.count3; index++) {
         this.countArray[index] = (short)index;
      }
   }

   @Override
   public void updateState3() {
   }

   @Override
   public void applyIndex(int count, short shortValue) {
      this.shortCodeArray[this.countArray[count]] = shortValue;
      this.countArray[count] = this.countArray[count] + this.count3;
   }

   public int getCount() {
      return this.countArray[0];
   }

   public int getCount3() {
      return this.count3;
   }

   public short[] getShortCodeArray() {
      return this.shortCodeArray;
   }
}
