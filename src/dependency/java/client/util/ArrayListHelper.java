package client.util;

import java.util.ArrayList;

public class ArrayListHelper {
   public static ArrayList size(ArrayList list) {
      double offset = Double.NaN;
      ArrayList list2 = new ArrayList();

      for (int index = 0; index < list.size(); index++) {
         double offset2 = (Double)list.get(index);
         if (Double.isFinite(offset)) {
            double offset3 = offset2 - offset;
            if (offset3 > 180.0) {
               offset2 -= 360.0;
            } else if (offset3 < -180.0) {
               offset2 += 360.0;
            }
         }

         offset = offset2;
         list2.add(offset2);
      }

      return list2;
   }
}
