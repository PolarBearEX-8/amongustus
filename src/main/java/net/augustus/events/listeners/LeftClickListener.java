package net.augustus.events.listeners;


import net.augustus.events.EventLeftClick;
import net.augustus.events.EventManager;
public interface LeftClickListener {
   EventLeftClick LEFT_CLICK_EVENT = new EventLeftClick();

   static EventLeftClick onLeftClick() {
      EventManager.getEventBus().post(53, LEFT_CLICK_EVENT);
      return LEFT_CLICK_EVENT;
   }

   void onLeftClick(EventLeftClick event);
}
