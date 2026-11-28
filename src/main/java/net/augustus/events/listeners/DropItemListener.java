package net.augustus.events.listeners;


import net.augustus.events.EventDropItem;
import net.augustus.events.EventManager;
public interface DropItemListener {
   EventDropItem DROP_ITEM_EVENT = new EventDropItem();

   static void postDropItem() {
      EventManager.getEventBus().post(9, DROP_ITEM_EVENT);
   }

   void onDropItem();
}
