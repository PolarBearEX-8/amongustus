package net.augustus.utils.math;

public class DoubleHeapSort {

   public static void sort(double[] doubles) {
      int length = doubles.length;

      for (int i = length / 2 - 1; i >= 0; i--) {
         heapify(doubles, length, i);
      }

      for (int j = length - 1; j > 0; j--) {
         double d = doubles[0];
         doubles[0] = doubles[j];
         doubles[j] = d;
         heapify(doubles, j, (byte)0);
      }
   }

   public static void heapify(double[] doubles, int n, int n2) {
      int n3 = n2;
      int n4 = 2 * n2 + 1;
      int n5 = 2 * n2 + 2;
      if (n4 < n && doubles[n4] > doubles[n2]) {
         n3 = n4;
      }

      if (n5 < n && doubles[n5] > doubles[n3]) {
         n3 = n5;
      }

      if (n3 != n2) {
         double d = doubles[n2];
         doubles[n2] = doubles[n3];
         doubles[n3] = d;
         heapify(doubles, n, n3);
      }
   }
}
