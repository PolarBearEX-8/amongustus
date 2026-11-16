package client.util;

import java.util.ArrayList;

public class ArrayListHelperImpl {
   public static ArrayList resolveArrayList(ArrayList list) {
      int size = list.size();
      boolean[] flagArray = new boolean[size];
      ArrayList list2 = new ArrayList();
      int count = 0;

      for (int index = 0; index < size; index++) {
         flagArray[index] = false;
      }

      list2.add(list.get(count));

      for (int index2 = 0; index2 < size - 1; index2++) {
         flagArray[count] = true;
         double offset = Double.POSITIVE_INFINITY;
         int count2 = -1;

         for (int index3 = 0; index3 < size; index3++) {
            if (!flagArray[index3]) {
               GeometryMatcher geometryMatcher = (GeometryMatcher)list.get(count);
               GeometryMatcher geometryMatcher2 = (GeometryMatcher)list.get(index3);
               double offset2 = geometryMatcher.computeValue(geometryMatcher2);
               if (offset2 < offset) {
                  offset = offset2;
                  count2 = index3;
               }
            }
         }

         if (count2 != -1) {
            list2.add(list.get(count2));
            count = count2;
         }
      }

      return list2;
   }
}
