package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventSlowDown;
public interface SlowDownListener {
   EventSlowDown SLOW_DOWN_EVENT = new EventSlowDown();

   static EventSlowDown onSlowDown(float f, float f2) {
      EventSlowDown.reset(SLOW_DOWN_EVENT, f, f2);
      EventManager.getEventBus().post(23, SLOW_DOWN_EVENT);
      return SLOW_DOWN_EVENT;
   }

   void onSlowDown(EventSlowDown event);
}
