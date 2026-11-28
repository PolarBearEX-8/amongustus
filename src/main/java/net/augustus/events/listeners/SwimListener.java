package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventSwim;
public interface SwimListener {
   EventSwim SWIM_EVENT = new EventSwim();

   void onSwim(EventSwim event);

   static EventSwim onSwim(float f, float f2) {
      EventSwim.reset(SWIM_EVENT, f, f2);
      EventManager.getEventBus().post(38, SWIM_EVENT);
      return SWIM_EVENT;
   }
}
