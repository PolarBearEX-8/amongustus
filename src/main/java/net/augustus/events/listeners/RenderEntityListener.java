package net.augustus.events.listeners;

import net.augustus.events.EventManager;
import net.augustus.events.EventRenderEntity;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;

public interface RenderEntityListener {
   EventRenderEntity RENDER_ENTITY_EVENT = new EventRenderEntity();

   void onRenderEntity(EventRenderEntity event);

   static void onRenderEntity(EntityLivingBase entity, RendererLivingEntity rendererLivingEntity, float f, double d, double d2, double d3) {
      EventRenderEntity.reset(RENDER_ENTITY_EVENT, entity, rendererLivingEntity, f, d, d2, d3);
      EventManager.getEventBus().post(31, RENDER_ENTITY_EVENT);
   }
}
