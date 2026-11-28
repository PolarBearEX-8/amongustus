package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventWebSlowDown;
public interface WebSlowDownListener {
   EventWebSlowDown WEB_SLOW_DOWN_EVENT = new EventWebSlowDown();

   void onWebSlowDown(EventWebSlowDown event);

   static EventWebSlowDown onWebSlowDown(double d, double d2, boolean flag, boolean flag2) {
      EventWebSlowDown.reset(WEB_SLOW_DOWN_EVENT, d, d2, flag, flag2);
      EventManager.getEventBus().post(49, WEB_SLOW_DOWN_EVENT);
      return WEB_SLOW_DOWN_EVENT;
   }
}
