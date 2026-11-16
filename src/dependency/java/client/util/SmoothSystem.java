package client.util;

import java.util.Collection;
import java.util.Random;

public class SmoothSystem {
   public static DampenerSorter resolveDampenerSorter(Random random, Collection collection, int count) {
      if (count <= 0) {
         throw new IllegalArgumentException();
      } else {
         int count2 = random.nextInt(count);
         return resolveDampenerSorter(collection, count2);
      }
   }

   public static DampenerSorter resolveDampenerSorter(Random random, Collection collection) {
      return resolveDampenerSorter(random, collection, computeCount(collection));
   }

   public static DampenerSorter resolveDampenerSorter(Collection collection, int count) {
      int count2 = count;

      for (DampenerSorter dampenerSorter : (Iterable<DampenerSorter>) collection) {
         count2 -= dampenerSorter.count;
         if (count2 < 0) {
            return dampenerSorter;
         }
      }

      return (DampenerSorter)null;
   }

   public static int computeCount(Collection collection) {
      int count = 0;

      for (DampenerSorter dampenerSorter : (Iterable<DampenerSorter>) collection) {
         count += dampenerSorter.count;
      }

      return count;
   }
}
