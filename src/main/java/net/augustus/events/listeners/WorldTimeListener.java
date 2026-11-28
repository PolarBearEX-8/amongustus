package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventWorldTime;
public interface WorldTimeListener {
   EventWorldTime WORLD_TIME_EVENT = new EventWorldTime();

   void onWorldTime(EventWorldTime event);

   static EventWorldTime postWorldTime(long l) {
      EventWorldTime.reset(WORLD_TIME_EVENT, l);
      EventManager.getEventBus().post(45, WORLD_TIME_EVENT);
      return WORLD_TIME_EVENT;
   }
}
