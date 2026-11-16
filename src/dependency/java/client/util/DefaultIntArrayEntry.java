package client.util;

public class DefaultIntArrayEntry {
   public static int[] countArray = new int[65536];

   public static void setCountArray(int[] countArray2) {
      countArray = countArray2;
   }

   public static int computeCount(double offset, double offset2) {
      double offset3 = offset2 * offset;
      int count = (int)((1.0 - offset) * 255.0);
      int count2 = (int)((1.0 - offset3) * 255.0) << 8 | count;
      return count2 > countArray.length ? -65281 : countArray[count2];
   }
}
