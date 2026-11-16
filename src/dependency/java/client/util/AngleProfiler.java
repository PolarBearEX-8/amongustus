package client.util;

import client.event.PreMotionInterceptor;

public final class AngleProfiler implements PreMotionInterceptor {
   public String name(Enum enumItem) {
      return enumItem.name();
   }

   @Override
   public String resolveText(Object entry) {
      return this.name((Enum)entry);
   }
}
