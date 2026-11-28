package net.augustus.events.listeners;

import net.augustus.events.EventManager;
import net.augustus.events.EventShouldRenderEntity;
import net.minecraft.entity.Entity;

public interface ShouldRenderEntityListener {
   EventShouldRenderEntity SHOULD_RENDER_ENTITY_EVENT = new EventShouldRenderEntity();

   static boolean postShouldRenderEntity(Entity entity) {
      EventShouldRenderEntity.reset(SHOULD_RENDER_ENTITY_EVENT, entity);
      EventManager.getEventBus().post(58, SHOULD_RENDER_ENTITY_EVENT);
      return EventShouldRenderEntity.isShouldRender(SHOULD_RENDER_ENTITY_EVENT);
   }

   void onShouldRenderEntity(EventShouldRenderEntity event);
}
