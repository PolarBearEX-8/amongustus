package net.augustus.events.listeners;

import net.augustus.events.EventManager;
import net.augustus.events.EventSilentSlot;
import de.florianmichael.dietrichevents2.StateTypes;

public interface SilentSlotListener {
   EventSilentSlot SILENT_SLOT_EVENT = new EventSilentSlot();

   void onPostSilentSlot();

   default void onPreSilentSlot() {
   }

   static void onSilentSlot(StateTypes stateTypes) {
      EventSilentSlot.setState(SILENT_SLOT_EVENT, stateTypes);
      EventManager.getEventBus().post(34, SILENT_SLOT_EVENT);
   }
}
