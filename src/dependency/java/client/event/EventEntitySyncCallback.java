package client.event;

import net.minecraft.entity.Entity;

public interface EventEntitySyncCallback {
   EventEntitySync eventEntitySync = new EventEntitySync();

   void onEntitySync(EventEntitySync event);

   static boolean matchesEntity(Entity entity) {
      EventEntitySync.onEntitySyncByEventEntitySync(eventEntitySync, entity);
      EventBusManager.getDietrichEvents2().post(58, eventEntitySync);
      return EventEntitySync.onEntitySync(eventEntitySync);
   }
}
