package net.augustus.events.listeners;


import net.augustus.events.EventInventoryClick;
import net.augustus.events.EventManager;
public interface InventoryClickListener {
   EventInventoryClick INVENTORY_CLICK_EVENT = new EventInventoryClick();

   static EventInventoryClick onInventoryClick() {
      EventInventoryClick.reset(INVENTORY_CLICK_EVENT);
      EventManager.getEventBus().post(51, INVENTORY_CLICK_EVENT);
      return INVENTORY_CLICK_EVENT;
   }

   void onInventoryClick(EventInventoryClick event);
}
