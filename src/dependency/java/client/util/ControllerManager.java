package client.util;

public class ControllerManager {
   public int count = 18;
   public int count2 = 128;
   public int count3 = 18;
   public int count4 = 0;
   public int count5 = 0;
   public int count6 = 0;
   public int[][][] countArray = null;
   public int[] countArray2 = null;
   public int count7 = 0;

   public int computeCount(int count8, int count9, int count10) {
      try {
         this.countArray2 = this.countArray[count8 - this.count4][count9 - this.count5];
         this.count7 = count10 - this.count6;
         return this.countArray2[this.count7];
      } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
         arrayIndexOutOfBoundsException.printStackTrace();
         return -1;
      }
   }

   public void updateState() {
      for (int index = 0; index < this.count; index++) {
         int[][] countMatrix = this.countArray[index];

         for (int index2 = 0; index2 < this.count2; index2++) {
            int[] countArray3 = countMatrix[index2];

            for (int index3 = 0; index3 < this.count3; index3++) {
               countArray3[index3] = -1;
            }
         }
      }
   }

   public void applyIndex(int count8, int count9, int count10) {
      this.count4 = count8;
      this.count5 = count9;
      this.count6 = count10;
      this.updateState();
   }

   public void printStackTrace(int count8) {
      try {
         this.countArray2[this.count7] = count8;
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public ControllerManager(int count8, int count9, int count10) {
      this.count = count8;
      this.count2 = count9;
      this.count3 = count10;
      this.countArray = new int[count8][count9][count10];
      this.updateState();
   }
}
