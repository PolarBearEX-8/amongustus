package client.event;

import client.setting.MovementSpeedProperty;

public interface ClickListener {
   void onClick(EventClick event);

   default void updateState8() {
   }

   static EventClick resolveEventClick(MovementSpeedProperty movementSpeedProperty, boolean flag, boolean flag2, Runnable task, Runnable task2) {
      EventClick event = new EventClick(movementSpeedProperty, flag, flag2, task, task2);
      EventBusManager.getDietrichEvents2().post(7, event);
      return event;
   }

   static EventClick resolveEventClick(MovementSpeedProperty movementSpeedProperty) {
      EventClick event = new EventClick(movementSpeedProperty);
      EventBusManager.getDietrichEvents2().post(7, event);
      return event;
   }
}
