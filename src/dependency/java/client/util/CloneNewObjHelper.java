package client.util;

import java.util.ArrayDeque;

public class CloneNewObjHelper {
   public static ArrayDeque arrayDeque = new ArrayDeque();
   public static int count = 10;

   private static long computeTimestamp2(int count2, int count3) {
      long timestamp = System.currentTimeMillis();

      for (int index = 0; index < count3; index++) {
         IBlockState[] blockStateArray = new IBlockState[count2];
      }

      return System.currentTimeMillis() - timestamp;
   }

   private static long computeTimestamp(Class clazz, int count2, int count3) {
      long timestamp = System.currentTimeMillis();
      int count4 = 0;

      while (count4 < count3) {
         count4++;
      }

      return System.currentTimeMillis() - timestamp;
   }

   private static long computeTimestamp3(int count2, int count3) {
      long timestamp = System.currentTimeMillis();
      int count4 = 0;

      while (count4 < count3) {
         count4++;
      }

      return System.currentTimeMillis() - timestamp;
   }

   private static long computeTimestamp(int count2, int count3) {
      long timestamp = System.currentTimeMillis();
      IBlockState[] blockStateArray = new IBlockState[count2];
      int count4 = 0;

      while (count4 < count3) {
         count4++;
      }

      return System.currentTimeMillis() - timestamp;
   }

   public static synchronized void applyIndexArray(int[] countArray) {
      if (arrayDeque.size() < count) {
         arrayDeque.add(countArray);
      }
   }

   public static void main(String[] textArray) throws Exception {
      computeTimestamp3(4096, 500000);
      computeTimestampByIndex(4096, 500000);
      computeTimestamp2(4096, 500000);
      computeTimestamp(4096, 500000);
      computeTimestamp(IBlockState.class, 4096, 500000);
      long value = computeTimestamp3(4096, 500000);
      long value2 = computeTimestampByIndex(4096, 500000);
      long value3 = computeTimestamp2(4096, 500000);
      long value4 = computeTimestamp(4096, 500000);
      long value5 = computeTimestamp(IBlockState.class, 4096, 500000);
      BuildManager.applyText4("New: " + value);
      BuildManager.applyText4("Clone: " + value2);
      BuildManager.applyText4("NewObj: " + value3);
      BuildManager.applyText4("CloneObj: " + value4);
      BuildManager.applyText4("NewObjDyn: " + value5);
   }

   private static synchronized int[] pollLast(int count2) {
      int[] countArray = (int[])arrayDeque.pollLast();
      if (countArray == null || countArray.length < count2) {
         countArray = new int[count2];
      }

      return countArray;
   }

   private static long computeTimestampByIndex(int count2, int count3) {
      long timestamp = System.currentTimeMillis();
      int[] countArray = new int[count2];
      int count4 = 0;

      while (count4 < count3) {
         count4++;
      }

      return System.currentTimeMillis() - timestamp;
   }
}
