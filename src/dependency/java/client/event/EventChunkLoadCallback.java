package client.event;

import client.setting.MovementAngleProperty;

public interface EventChunkLoadCallback {
   EventChunkLoad eventChunkLoad = new EventChunkLoad();

   void onChunkLoad(EventChunkLoad event);

   static EventChunkLoad resolveEventChunkLoad(boolean flag, MovementAngleProperty movementAngleProperty) {
      EventChunkLoad.onChunkLoad(eventChunkLoad, flag, movementAngleProperty);
      EventBusManager.getDietrichEvents2().post(22, eventChunkLoad);
      return eventChunkLoad;
   }

   void onChunkLoad2(EventChunkLoad event);
}
