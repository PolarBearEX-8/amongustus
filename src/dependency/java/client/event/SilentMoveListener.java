package client.event;

import de.florianmichael.dietrichevents2.StateTypes;

public interface SilentMoveListener {
   EventSilentMove eventSilentMove = new EventSilentMove();

   static EventSilentMove resolveEventSilentMove(int count, int count2, float factor, StateTypes stateTypes) {
      EventSilentMove.onSilentMove(eventSilentMove, count, count2, factor, stateTypes);
      EventBusManager.getDietrichEvents2().post(19, eventSilentMove);
      return eventSilentMove;
   }

   void onSilentMove(EventSilentMove event);

   default void onPostSilentMove() {
   }
}
