package client.util;

import client.event.PreUpdateListener;

public final class ActiveClientManager implements PreUpdateListener {
   @Override
   public String resolveText(int count) {
      double offset = (double)count / 20.0;
      double offset2 = offset / 60.0;
      double offset3 = offset2 / 60.0;
      double offset4 = offset3 / 24.0;
      double offset5 = offset4 / 365.0;
      return offset5 > 0.5
         ? DuplicateStatIdManager.getDecimalFormat().format(offset5) + " y"
         : (
            offset4 > 0.5
               ? DuplicateStatIdManager.getDecimalFormat().format(offset4) + " d"
               : (
                  offset3 > 0.5
                     ? DuplicateStatIdManager.getDecimalFormat().format(offset3) + " h"
                     : (offset2 > 0.5 ? DuplicateStatIdManager.getDecimalFormat().format(offset2) + " m" : offset + " s")
               )
         );
   }
}
