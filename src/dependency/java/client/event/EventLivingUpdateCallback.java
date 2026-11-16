package client.event;

import net.minecraft.entity.EntityLivingBase;

public interface EventLivingUpdateCallback {
   EventLivingUpdate eventLivingUpdate = new EventLivingUpdate();

   static EventLivingUpdate resolveEventLivingUpdate(EntityLivingBase livingEntity, float posX, float posX2, float posY, float posZ) {
      EventLivingUpdate.onLivingUpdateByEventLivingUpdat(eventLivingUpdate, 25014, livingEntity, posX, posX2, posY, (short)-1428, (char)61676, posZ);
      EventBusManager.getDietrichEvents2().post(10, eventLivingUpdate);
      return eventLivingUpdate;
   }

   void onLivingUpdate(EventLivingUpdate event);
}
