package client.util;

public class TertiaryFloatArrayEntry {
   public float[] factorArray;
   public final int count;

   private int computeCountByIndex(int count2) {
      return (count2 + this.count / 90) % 4;
   }

   public int computeCount(int count2) {
      return (count2 + (4 - this.count / 90)) % 4;
   }

   public float computeFactorByIndex(int count2) {
      if (this.factorArray == null) {
         throw new NullPointerException("uvs");
      } else {
         int count3 = this.computeCountByIndex(count2);
         return count3 != 0 && count3 != 1 ? this.factorArray[2] : this.factorArray[0];
      }
   }

   public void applyFloatArray(float[] factorArray2) {
      if (this.factorArray == null) {
         this.factorArray = factorArray2;
      }
   }

   public float computeFactor(int count2) {
      if (this.factorArray == null) {
         throw new NullPointerException("uvs");
      } else {
         int count3 = this.computeCountByIndex(count2);
         return count3 != 0 && count3 != 3 ? this.factorArray[3] : this.factorArray[1];
      }
   }

   public TertiaryFloatArrayEntry(float[] factorArray2, int count2) {
      this.factorArray = factorArray2;
      this.count = count2;
   }
}
