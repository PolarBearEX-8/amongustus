package net.augustus.events.listeners;


import net.augustus.events.EventDisconnect;
import net.augustus.events.EventManager;
public interface DisconnectListener {
   EventDisconnect DISCONNECT_EVENT = new EventDisconnect();

   static void postDisconnect() {
      EventManager.getEventBus().post(62, DISCONNECT_EVENT);
   }

   void onDisconnect();
}
