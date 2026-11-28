package net.augustus.events.listeners;


import net.augustus.events.EventLadder;
import net.augustus.events.EventManager;
public interface LadderListener {
   EventLadder LADDER_EVENT = new EventLadder();

   static EventLadder postLadder(double d) {
      EventLadder.reset(LADDER_EVENT, d);
      EventManager.getEventBus().post(15, LADDER_EVENT);
      return LADDER_EVENT;
   }

   void onLadder(EventLadder event);
}
