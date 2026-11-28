package net.augustus.utils.inventory;

import java.util.ArrayList;

public class NearestSlotPathSorter {
   public static ArrayList sortByNearestNeighbor(ArrayList list) {
      int size = list.size();
      boolean[] flags = new boolean[size];
      ArrayList list2 = new ArrayList();
      int n = 0;

      for (int i = 0; i < size; i++) {
         flags[i] = false;
      }

      list2.add(list.get(n));

      for (int j = 0; j < size - 1; j++) {
         flags[n] = true;
         double d = 1.0 / 0.0;
         int n2 = -1;

         for (int k = 0; k < size; k++) {
            if (!flags[k]) {
               double distance = ((InventorySlotPoint)list.get(n)).distanceTo((InventorySlotPoint)list.get(k));
               if (distance < d) {
                  d = distance;
                  n2 = k;
               }
            }
         }

         if (n2 != -1) {
            list2.add(list.get(n2));
            n = n2;
         }
      }

      return list2;
   }
}
