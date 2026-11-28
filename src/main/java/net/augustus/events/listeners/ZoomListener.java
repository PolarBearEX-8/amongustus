package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventZoom;
public interface ZoomListener {
   EventZoom ZOOM_EVENT = new EventZoom();

   void onZoom(EventZoom event);

   static EventZoom postZoom(float f) {
      EventZoom.reset(ZOOM_EVENT, f);
      EventManager.getEventBus().post(46, ZOOM_EVENT);
      return ZOOM_EVENT;
   }
}
