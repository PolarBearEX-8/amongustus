package client.util;

import client.event.PreUpdateListener;

public final class LocalClientManager implements PreUpdateListener {
   @Override
   public String resolveText(int count) {
      return DuplicateStatIdManager.getDecimalFormat().format((double)count * 0.1);
   }
}
