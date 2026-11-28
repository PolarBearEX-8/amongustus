package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventWindowResize;
public interface WindowResizeListener {
   EventWindowResize WINDOW_RESIZE_EVENT = new EventWindowResize();

   static void postWindowResize(int n, int n2) {
      EventWindowResize.reset(WINDOW_RESIZE_EVENT, n, n2);
      EventManager.getEventBus().post(47, WINDOW_RESIZE_EVENT);
   }

   void onWindowResize(int n, int n2);
}
