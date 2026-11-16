package client.event;

import client.setting.PhaseMode;

public interface EventConnectionCallback {
   EventConnection eventConnection = new EventConnection();

   default void onConnection(EventConnection event) {
   }

   static EventConnection resolveEventConnection(PhaseMode phaseMode) {
      eventConnection.setCancelled(phaseMode);
      EventBusManager.getDietrichEvents2().post(60, eventConnection);
      return eventConnection;
   }

   default void onConnectionByEventConnection(EventConnection event) {
   }
}
