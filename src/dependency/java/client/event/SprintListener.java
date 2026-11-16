package client.event;

import de.florianmichael.dietrichevents2.StateTypes;

public interface SprintListener {
   EventSprint eventSprint = new EventSprint();

   void onSprint(EventSprint event);

   default void onPostSprint(EventSprint event) {
   }

   static EventSprint resolveEventSprint(StateTypes stateTypes, boolean flag) {
      EventSprint.onSprint(eventSprint, stateTypes, flag);
      EventBusManager.getDietrichEvents2().post(33, eventSprint);
      return eventSprint;
   }
}
