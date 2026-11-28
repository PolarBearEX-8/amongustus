package net.augustus.events.listeners;


import net.augustus.events.EventGuiScale;
import net.augustus.events.EventManager;
public interface GuiScaleListener {
   EventGuiScale GUI_SCALE_EVENT = new EventGuiScale();

   void onGuiScale();

   static void postGuiScale() {
      EventManager.getEventBus().post(11, GUI_SCALE_EVENT);
   }
}
