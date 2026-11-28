package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventTimerUpdate;
public interface TimerUpdateListener {
   EventTimerUpdate TIMER_UPDATE_EVENT = new EventTimerUpdate();

   void onTimerUpdate(EventTimerUpdate event);

   static EventTimerUpdate onTimerUpdate(float f, int n) {
      EventTimerUpdate.reset(TIMER_UPDATE_EVENT, f, n);
      EventManager.getEventBus().post(42, TIMER_UPDATE_EVENT);
      return TIMER_UPDATE_EVENT;
   }
}
