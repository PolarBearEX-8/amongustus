package net.augustus.events.listeners;


import net.augustus.events.ClickButtonType;
import net.augustus.events.EventClick;
import net.augustus.events.EventManager;
public interface ClickListener {

   void onClick(EventClick event);

   static EventClick onClick(ClickButtonType clickButtonType) {
      EventClick event = new EventClick(clickButtonType);
      EventManager.getEventBus().post(7, event);
      return event;
   }

   static EventClick onClick(ClickButtonType clickButtonType, boolean flag, boolean flag2, Runnable runnable, Runnable runnable2) {
      EventClick event = new EventClick(clickButtonType, flag, flag2, runnable, runnable2);
      EventManager.getEventBus().post(7, event);
      return event;
   }

   default void onMiddleClick() {
   }
}
