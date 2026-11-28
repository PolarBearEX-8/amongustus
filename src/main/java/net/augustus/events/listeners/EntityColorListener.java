package net.augustus.events.listeners;

import net.augustus.events.EventEntityColor;
import net.augustus.events.EventManager;
import net.minecraft.entity.EntityLivingBase;

public interface EntityColorListener {
   EventEntityColor ENTITY_COLOR_EVENT = new EventEntityColor();

   static EventEntityColor onEntityColor(EntityLivingBase entity, float f, float f2, float f3, float f4) {
      EventEntityColor.reset(ENTITY_COLOR_EVENT, entity, f, f2, f3, f4);
      EventManager.getEventBus().post(10, ENTITY_COLOR_EVENT);
      return ENTITY_COLOR_EVENT;
   }

   void onEntityColor(EventEntityColor event);
}
