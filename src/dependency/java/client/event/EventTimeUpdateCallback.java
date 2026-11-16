package client.event;

public interface EventTimeUpdateCallback {
   EventTimeUpdate eventTimeUpdate = new EventTimeUpdate();

   void onTimeUpdate(EventTimeUpdate event);

   static EventTimeUpdate getEventTimeUpdate() {
      EventTimeUpdate.onTimeUpdate(eventTimeUpdate);
      EventBusManager.getDietrichEvents2().post(45, eventTimeUpdate);
      return eventTimeUpdate;
   }
}
