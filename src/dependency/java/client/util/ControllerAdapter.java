package client.util;

public class ControllerAdapter {
   public int count = 18;
   public int count2 = 128;
   public int count3 = 18;
   public int count4 = 0;
   public int count5 = 0;
   public int count6 = 0;
   public byte[][][] byteFlagArray = null;
   public byte[] byteFlagArray2 = null;
   public int count7 = 0;

   public ControllerAdapter(int count8, int count9, int count10) {
      this.count = count8;
      this.count2 = count9;
      this.count3 = count10;
      this.byteFlagArray = new byte[count8][count9][count10];
      this.updateState();
   }

   public void applyIndex(int count8, int count9, int count10) {
      this.count4 = count8;
      this.count5 = count9;
      this.count6 = count10;
      this.updateState();
   }

   public byte computeByteCode(int count8, int count9, int count10) {
      try {
         this.byteFlagArray2 = this.byteFlagArray[count8 - this.count4][count9 - this.count5];
         this.count7 = count10 - this.count6;
         return this.byteFlagArray2[this.count7];
      } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
         arrayIndexOutOfBoundsException.printStackTrace();
         return -1;
      }
   }

   public void printStackTrace(byte byteValue) {
      try {
         this.byteFlagArray2[this.count7] = byteValue;
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }

   public void updateState() {
      for (int index = 0; index < this.count; index++) {
         byte[][] byteValueMatrix = this.byteFlagArray[index];

         for (int index2 = 0; index2 < this.count2; index2++) {
            byte[] byteValueArray = byteValueMatrix[index2];

            for (int index3 = 0; index3 < this.count3; index3++) {
               byteValueArray[index3] = -1;
            }
         }
      }
   }
}
