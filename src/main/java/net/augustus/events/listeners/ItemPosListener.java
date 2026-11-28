package net.augustus.events.listeners;


import net.augustus.events.EventItemPos;
import net.augustus.events.EventManager;
public interface ItemPosListener {
   EventItemPos ITEM_POS_EVENT = new EventItemPos();

   void onItemPos(EventItemPos event);

   static EventItemPos onItemPos(double d, double d2, double d3, double d4, double d5, double d6, double d7) {
      EventItemPos.reset(ITEM_POS_EVENT, d, d2, d3, d4, d5, d6, d7);
      EventManager.getEventBus().post(13, ITEM_POS_EVENT);
      return ITEM_POS_EVENT;
   }
}
