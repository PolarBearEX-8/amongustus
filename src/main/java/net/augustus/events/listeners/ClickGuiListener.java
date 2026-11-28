package net.augustus.events.listeners;


import net.augustus.events.EventClickGui;
import net.augustus.events.EventManager;
public interface ClickGuiListener {
   EventClickGui CLICK_GUI_EVENT = new EventClickGui();

   default void onClickGuiRender() {
   }

   void onClickGuiUpdate();

   static void postClickGuiRender() {
      EventClickGui.reset(CLICK_GUI_EVENT, true);
      EventManager.getEventBus().post(6, CLICK_GUI_EVENT);
   }

   static void postClickGuiUpdate() {
      EventClickGui.reset(CLICK_GUI_EVENT, false);
      EventManager.getEventBus().post(6, CLICK_GUI_EVENT);
   }
}
