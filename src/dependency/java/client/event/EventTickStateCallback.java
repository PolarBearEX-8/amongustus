package client.event;

import de.florianmichael.dietrichevents2.StateTypes;

public interface EventTickStateCallback {
   EventTickState eventTickState = new EventTickState();

   void updateState2();

   static EventTickState getEventTickState(float factor, StateTypes stateTypes) {
      EventTickState.onTickState(eventTickState, factor, stateTypes);
      EventBusManager.getDietrichEvents2().post(37, eventTickState);
      return eventTickState;
   }

   static void applyStateTypes(StateTypes stateTypes) {
      float factor = 0.0F;
      EventTickState.onTickState(eventTickState, factor, stateTypes);
      EventBusManager.getDietrichEvents2().post(37, eventTickState);
   }

   void onTickState(EventTickState event);
}
