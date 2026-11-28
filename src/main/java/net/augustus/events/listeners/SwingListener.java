package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventSwing;
public interface SwingListener {
   EventSwing SWING_EVENT = new EventSwing();

   static EventSwing onSwing() {
      EventSwing.reset(SWING_EVENT);
      EventManager.getEventBus().post(39, SWING_EVENT);
      return SWING_EVENT;
   }

   void onSwing(EventSwing event);
}
