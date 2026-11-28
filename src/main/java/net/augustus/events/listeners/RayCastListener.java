package net.augustus.events.listeners;


import net.augustus.events.EventManager;
import net.augustus.events.EventRayCast;
public interface RayCastListener {
   EventRayCast RAY_CAST_EVENT = new EventRayCast();

   void onRayCast();

   static void postRayCast() {
      EventManager.getEventBus().post(29, RAY_CAST_EVENT);
   }
}
