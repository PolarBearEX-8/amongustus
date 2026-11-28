package net.augustus.events.listeners;

import net.augustus.events.EventManager;
import net.augustus.events.EventRenderEntities;
import net.minecraft.client.renderer.culling.ICamera;

public interface RenderEntitiesListener {
   EventRenderEntities RENDER_ENTITIES_EVENT = new EventRenderEntities();

   void onRenderEntities(EventRenderEntities event);

   static void onRenderEntities(ICamera camera, double d, double d2, double d3, float f, boolean flag) {
      RENDER_ENTITIES_EVENT.reset(camera, d, d2, d3, f, flag);
      EventManager.getEventBus().post(57, RENDER_ENTITIES_EVENT);
   }
}
