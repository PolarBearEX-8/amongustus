package client.util;

import client.event.PreUpdateObserver;

public class PreUpdateObserverPreUpdateObserverCallbImpl implements PreUpdateObserverCallback {
   @Override
   public PreUpdateObserver resolvePreUpdateObserver(String text) {
      SPRINTINGEnum sPRINTINGEnum = SPRINTINGEnum.resolveSPRINTINGEnum(text);
      if (sPRINTINGEnum != null) {
         return sPRINTINGEnum;
      } else {
         IdleTimeEnum idleTimeEnum = IdleTimeEnum.resolveIdleTimeEnum(text);
         return idleTimeEnum != null ? idleTimeEnum : null;
      }
   }
}
