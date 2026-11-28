package net.augustus.events.listeners;

import net.augustus.events.EventManager;
import net.augustus.events.EventMotion;
import de.florianmichael.dietrichevents2.StateTypes;

public interface MotionListener {
   EventMotion MOTION_EVENT = new EventMotion();

   default void onPostMotion() {
   }

   void onPreMotion(EventMotion event);

   static EventMotion postPreMotion(boolean flag, float f, float f2, double d, double d2, double d3) {
      StateTypes pre = StateTypes.PRE;
      EventMotion.reset(MOTION_EVENT, flag, f, f2, d, d2, d3, pre);
      EventManager.getEventBus().post(18, MOTION_EVENT);
      return MOTION_EVENT;
   }

   static void postPostMotion() {
      StateTypes post = StateTypes.POST;
      double d = 0.0;
      double d2 = 0.0;
      double d3 = 0.0;
      float f = 0.0F;
      float f2 = 0.0F;
      EventMotion.reset(MOTION_EVENT, false, f2, f, d3, d2, d, post);
      EventManager.getEventBus().post(18, MOTION_EVENT);
   }
}
