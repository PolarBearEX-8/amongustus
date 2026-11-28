package net.augustus.utils.math;

import java.util.ArrayList;
import net.augustus.utils.TimeHelper;

public class DoubleListStatistics {

   public static int findMinIndex(ArrayList list, int n, int n2) {
      double d = (Double)list.get(n);
      int n3 = n;

      for (int i = n; i < n2; i++) {
         if (d > (Double)list.get(i)) {
            d = (Double)list.get(i);
            n3 = i;
         }
      }

      return n3;
   }

   public static double max(ArrayList list) {
      double maxVal = (Double)list.get(0);
      for (int i = 1; i < list.size(); i++) {
         double val = (Double)list.get(i);
         if (val > maxVal) {
            maxVal = val;
         }
      }
      return maxVal;
   }

   public static void swap(ArrayList list, int n, int n2) {
      double d = (Double)list.get(n);
      list.set(n, list.get(n2));
      list.set(n2, d);
   }

   public static double mean(ArrayList list) {
      double d = 0.0;

      for (int i = 0; i < list.size(); i++) {
         d += (Double)list.get(i);
      }

      return d / (double)list.size();
   }

   public static void selectionSort(ArrayList list) {

      for (int i = 0; i < list.size(); i++) {
         swap(list, findMinIndex(list, i, list.size()), i);
      }
   }

   public static double sampleVariance(ArrayList list) {
      double d = mean(list);
      double d2 = 0.0;

      for (int i = 0; i < list.size(); i++) {
         d2 += ((Double)list.get(i) - d) * ((Double)list.get(i) - d);
      }

      return d2 / (double)(list.size() - 1);
   }

   public static double sampleStdDev(ArrayList list) {
      return Math.sqrt(sampleVariance(list));
   }

   public static double median(ArrayList list) {
      selectionSort(list);
      return list.size() % 2 == 0 ? ((Double)list.get(list.size() / 2 - 1) + (Double)list.get(list.size() / 2)) / 2.0 : (Double)list.get(list.size() / 2);
   }
}
