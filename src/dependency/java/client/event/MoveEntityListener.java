package client.event;

import client.setting.TertiaryOptionType;

public interface MoveEntityListener {
   EventMoveEntity eventMoveEntity = new EventMoveEntity();

   default void onMoveEntity(EventMoveEntity event) {
   }

   static EventMoveEntity resolveEventMoveEntity(double posX, double posY, double posZ, TertiaryOptionType tertiaryOptionType) {
      EventMoveEntity.onMoveEntity(eventMoveEntity, posX, posY, posZ, tertiaryOptionType);
      EventBusManager.getDietrichEvents2().post(20, eventMoveEntity);
      return eventMoveEntity;
   }

   default void onPostMoveEntity(EventMoveEntity event) {
   }
}
