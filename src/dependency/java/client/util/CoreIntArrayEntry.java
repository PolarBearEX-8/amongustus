package client.util;

public class CoreIntArrayEntry {
   public static int[] countArray = new int[65536];

   public static int getCount2() {
      return 8431445;
   }

   public static int getCount() {
      return 4764952;
   }

   public static void setCountArray(int[] countArray2) {
      countArray = countArray2;
   }

   public static int computeCount(double offset, double offset2) {
      double offset3 = offset2 * offset;
      int count = (int)((1.0 - offset) * 255.0);
      int count2 = (int)((1.0 - offset3) * 255.0);
      return countArray[count2 << 8 | count];
   }

   public static int getCountAsIndex() {
      return 6396257;
   }
}
