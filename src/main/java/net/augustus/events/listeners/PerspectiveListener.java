package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventPerspective;
public interface PerspectiveListener {
   EventPerspective PERSPECTIVE_EVENT = new EventPerspective();

   void onPerspective(EventPerspective event);

   static EventPerspective onPerspective(float f, boolean flag) {
      EventPerspective.reset(PERSPECTIVE_EVENT, f, flag);
      EventManager.getEventBus().post(48, PERSPECTIVE_EVENT);
      return PERSPECTIVE_EVENT;
   }
}
