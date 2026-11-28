package net.augustus.events.listeners;

import net.augustus.events.EventInventoryClose;
import net.augustus.events.EventManager;
import de.florianmichael.dietrichevents2.StateTypes;

public interface InventoryCloseListener {
   EventInventoryClose INVENTORY_CLOSE_EVENT = new EventInventoryClose();

   boolean onPreInventoryClose();

   static void postInventoryClose() {
      EventInventoryClose.reset(INVENTORY_CLOSE_EVENT);
      EventManager.getEventBus().post(52, INVENTORY_CLOSE_EVENT);
      StateTypes post = StateTypes.POST;
      EventInventoryClose.setState(INVENTORY_CLOSE_EVENT, post);
      if (EventInventoryClose.isPrevented(INVENTORY_CLOSE_EVENT)) {
         EventInventoryClose.getTimer(INVENTORY_CLOSE_EVENT).reset();
      }

      EventManager.getEventBus().post(52, INVENTORY_CLOSE_EVENT);
   }

   void onPostInventoryClose(EventInventoryClose event);
}
