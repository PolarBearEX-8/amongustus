package client.util;

public class ControllerProvider {
   public int[] countArray = null;
   public int count = 0;
   public int count2 = 0;

   public void updateState() {
      this.count = 0;
      this.count2 = 0;
   }

   public int computeCount(int count3) {
      return this.countArray[count3];
   }

   public void setCount(int count3) {
      this.count = count3;
   }

   public int getCount() {
      return this.count;
   }

   public int[] getCountArray() {
      return this.countArray;
   }

   public ControllerProvider(int count3) {
      this.countArray = new int[count3];
   }

   public void applyIndex(int count3, int count4) {
      this.countArray[count3] = count4;
      if (this.count2 < count3) {
         this.count2 = count3;
      }
   }

   public void applyIndex(int count3) {
      this.countArray[this.count] = count3;
      this.count++;
      if (this.count2 < this.count) {
         this.count2 = this.count;
      }
   }

   public int getCount2() {
      return this.count2;
   }

   public void applyIndexArray(int[] countArray2) {
      int length = countArray2.length;

      for (int index = 0; index < length; index++) {
         this.countArray[this.count] = countArray2[index];
         this.count++;
      }

      if (this.count2 < this.count) {
         this.count2 = this.count;
      }
   }
}
