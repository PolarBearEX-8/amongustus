package client.event;

public interface EventBoundingBoxCallback {
   EventBoundingBox eventBoundingBox = new EventBoundingBox();

   static EventBoundingBox resolveEventBoundingBox(double posX, double posX2, double posX3, double posX4, double posX5, double posY, double posZ) {
      EventBoundingBox.onBoundingBox(eventBoundingBox, posX, posX2, posX3, posX4, posX5, posY, posZ);
      EventBusManager.getDietrichEvents2().post(13, eventBoundingBox);
      return eventBoundingBox;
   }

   void onBoundingBox(EventBoundingBox event);
}
