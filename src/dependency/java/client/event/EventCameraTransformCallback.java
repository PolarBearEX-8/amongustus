package client.event;

public interface EventCameraTransformCallback {
   EventCameraTransform eventCameraTransform = new EventCameraTransform();

   void onCameraTransform(EventCameraTransform event);

   static EventCameraTransform getEventCameraTransform(float factor, float factor2) {
      EventCameraTransform.onCameraTransform(eventCameraTransform, factor, factor2);
      EventBusManager.getDietrichEvents2().post(23, eventCameraTransform);
      return eventCameraTransform;
   }
}
