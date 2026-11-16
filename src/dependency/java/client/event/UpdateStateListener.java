package client.event;

import de.florianmichael.dietrichevents2.StateTypes;

public interface UpdateStateListener {
   EventStateUpdate eventStateUpdate = new EventStateUpdate();

   default void onUpdateState(EventStateUpdate event) {
   }

   static EventStateUpdate resolveEventStateUpdate(StateTypes stateTypes) {
      EventStateUpdate.onStateUpdate(eventStateUpdate, stateTypes);
      EventBusManager.getDietrichEvents2().post(43, eventStateUpdate);
      return eventStateUpdate;
   }

   default void onPreUpdateState() {
   }

   default void onPostUpdateState() {
   }
}
