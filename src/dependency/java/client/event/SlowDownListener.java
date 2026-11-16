package client.event;

import client.setting.CombatThresholdProperty;

public interface SlowDownListener {
   EventSlowDown eventSlowDown = new EventSlowDown();

   void onSlowDown(EventSlowDown event);

   void onCustomSlowDown(EventSlowDown event);

   static EventSlowDown resolveEventSlowDown(int count, float factor, CombatThresholdProperty combatThresholdProperty) {
      EventSlowDown.onSlowDown(eventSlowDown, count, factor, combatThresholdProperty);
      EventBusManager.getDietrichEvents2().post(41, eventSlowDown);
      return eventSlowDown;
   }
}
