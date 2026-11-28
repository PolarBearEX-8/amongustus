package net.augustus.events.listeners;

import net.augustus.events.EventManager;
import net.augustus.events.EventUpdate;
import de.florianmichael.dietrichevents2.StateTypes;

public interface UpdateListener {
   EventUpdate UPDATE_EVENT = new EventUpdate();

   default void onPreUpdate(EventUpdate event) {
   }

   default void onPostUpdate() {
   }

   default void onPostUpdate(EventUpdate event) {
   }

   static EventUpdate postUpdate(StateTypes stateTypes) {
      EventUpdate.reset(UPDATE_EVENT, stateTypes);
      EventManager.getEventBus().post(43, UPDATE_EVENT);
      return UPDATE_EVENT;
   }

   default void onPreUpdate() {
   }
}
