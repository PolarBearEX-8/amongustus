package client.event;

public interface EventFovUpdateCallback {
   EventFovUpdate eventFovUpdate = new EventFovUpdate();

   static EventFovUpdate resolveEventFovUpdate(float factor) {
      EventFovUpdate.onFovUpdate(eventFovUpdate, factor);
      EventBusManager.getDietrichEvents2().post(46, eventFovUpdate);
      return eventFovUpdate;
   }

   void onFovUpdate(EventFovUpdate event);
}
