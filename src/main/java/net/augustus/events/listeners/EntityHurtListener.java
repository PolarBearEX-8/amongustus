package net.augustus.events.listeners;

import net.augustus.events.EventEntityHurt;
import net.augustus.events.EventManager;
import net.minecraft.entity.EntityLivingBase;

public interface EntityHurtListener {
   EventEntityHurt ENTITY_HURT_EVENT = new EventEntityHurt();

   static void postEntityHurt(EntityLivingBase entity) {
      EventEntityHurt.reset(ENTITY_HURT_EVENT, entity);
      EventManager.getEventBus().post(50, ENTITY_HURT_EVENT);
   }

   void onEntityHurt(EventEntityHurt event);
}
