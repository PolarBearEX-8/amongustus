package client.util;

import com.google.common.collect.Lists;
import java.util.List;

public class CacheManager {
   public static int count = 256;
   public static List list3 = Lists.newArrayList();
   public static List list2 = Lists.newArrayList();
   public static List list4 = Lists.newArrayList();
   public static List list = Lists.newArrayList();

   public static synchronized void updateState() {
      if (!list4.isEmpty()) {
         list4.remove(list4.size() - 1);
      }

      if (!list3.isEmpty()) {
         list3.remove(list3.size() - 1);
      }

      list4.addAll(list);
      list3.addAll(list2);
      list.clear();
      list2.clear();
   }

   public static synchronized String getText() {
      return "cache: " + list4.size() + ", tcache: " + list3.size() + ", allocated: " + list.size() + ", tallocated: " + list2.size();
   }

   public static synchronized int[] resolveIndexArray(int count2) {
      if (count2 <= 256) {
         if (list3.isEmpty()) {
            int[] countArray = new int[256];
            list2.add(countArray);
            return countArray;
         } else {
            int[] countArray2 = (int[])list3.remove(list3.size() - 1);
            list2.add(countArray2);
            return countArray2;
         }
      } else if (count2 > count) {
         count = count2;
         list4.clear();
         list.clear();
         int[] countArray3 = new int[count];
         list.add(countArray3);
         return countArray3;
      } else if (list4.isEmpty()) {
         int[] countArray4 = new int[count];
         list.add(countArray4);
         return countArray4;
      } else {
         int[] countArray5 = (int[])list4.remove(list4.size() - 1);
         list.add(countArray5);
         return countArray5;
      }
   }
}
