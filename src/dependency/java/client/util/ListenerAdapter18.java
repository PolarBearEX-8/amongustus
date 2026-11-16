package client.util;

import client.event.PreUpdateListener;

public final class ListenerAdapter18 implements PreUpdateListener {
   @Override
   public String resolveText(int count) {
      return DuplicateStatIdManager.getNumberFormat().format((long)count);
   }
}
