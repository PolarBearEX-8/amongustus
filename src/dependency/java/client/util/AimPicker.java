package client.util;

public class AimPicker {
   public final float[] factorArray;
   public int count;

   public float[] getFactorArray() {
      return this.factorArray;
   }

   public AimPicker(float[] factorArray2) {
      this.factorArray = factorArray2;
      this.count = 1;
   }

   public void updateState() {
      this.count++;
   }

   public int getCount() {
      return this.count;
   }
}
