package net.augustus.events.listeners;

import net.augustus.events.EventManager;
import net.augustus.events.EventRender;
import net.augustus.events.RenderPassType;
import net.minecraft.client.gui.ScaledResolution;

public interface RenderListener {
   EventRender RENDER_EVENT = new EventRender();

   default void onRender2DPost(float partialTicks, ScaledResolution sr) {
   }

   default void onRender3D(float partialTicks) {
   }

   static void postRender(float f, ScaledResolution sr, RenderPassType renderPassType) {
      EventRender.reset(RENDER_EVENT, f, sr, renderPassType);
      EventManager.getEventBus().post(32, RENDER_EVENT);
   }

   default void onRender2DPre(float partialTicks, ScaledResolution sr) {
   }
}
