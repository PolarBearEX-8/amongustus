package client.event;

import net.minecraft.entity.EntityLivingBase;

public interface EventTargetEntityCallback {
   EventTargetEntity eventTargetEntity = new EventTargetEntity();

   void onTargetEntity(EventTargetEntity event);

   static void applyEntityLivingBase(EntityLivingBase livingEntity) {
      EventTargetEntity.onTargetEntity(eventTargetEntity, livingEntity);
      EventBusManager.getDietrichEvents2().post(50, eventTargetEntity);
   }
}
