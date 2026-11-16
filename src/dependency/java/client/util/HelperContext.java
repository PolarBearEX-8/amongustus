package client.util;

public class HelperContext {
   public static void applyDoubleArray(double[] offsetArray) {
      int length = offsetArray.length;

      for (int index = length / 2 - 1; index >= 0; index--) {
         applyDoubleArray(offsetArray, length, index);
      }

      for (int index2 = length - 1; index2 > 0; index2--) {
         double offset = offsetArray[0];
         offsetArray[0] = offsetArray[index2];
         offsetArray[index2] = offset;
         applyDoubleArray(offsetArray, index2, 0);
      }
   }

   public static void applyDoubleArray(double[] offsetArray, int count, int count2) {
      int count3 = count2;
      int count4 = 2 * count2 + 1;
      int count5 = 2 * count2 + 2;
      if (count4 < count && offsetArray[count4] > offsetArray[count2]) {
         count3 = count4;
      }

      if (count5 < count && offsetArray[count5] > offsetArray[count3]) {
         count3 = count5;
      }

      if (count3 != count2) {
         double offset = offsetArray[count2];
         offsetArray[count2] = offsetArray[count3];
         offsetArray[count3] = offset;
         applyDoubleArray(offsetArray, count, count3);
      }
   }
}
