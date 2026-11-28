package net.augustus.utils.rotation;

import java.util.ArrayList;

public class AngleUnwrapper {
   public static ArrayList unwrapAngles(ArrayList list) {
      double naN = Double.NaN;
      ArrayList list2 = new ArrayList();

      for (int i = 0; i < list.size(); i++) {
         double d = (Double)list.get(i);
         if (Double.isFinite(naN)) {
            double d2 = d - naN;
            if (d2 > 180.0) {
               d -= 360.0;
            } else if (d2 < -180.0) {
               d += 360.0;
            }
         }

         naN = d;
         list2.add(d);
      }

      return list2;
   }
}
