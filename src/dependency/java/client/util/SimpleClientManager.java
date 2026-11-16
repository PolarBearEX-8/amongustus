package client.util;

import client.event.PreUpdateListener;

public final class SimpleClientManager implements PreUpdateListener {
   @Override
   public String resolveText(int count) {
      double offset = (double)count / 100.0;
      double offset2 = offset / 1000.0;
      return offset2 > 0.5
         ? DuplicateStatIdManager.getDecimalFormat().format(offset2) + " km"
         : (offset > 0.5 ? DuplicateStatIdManager.getDecimalFormat().format(offset) + " m" : count + " cm");
   }
}
