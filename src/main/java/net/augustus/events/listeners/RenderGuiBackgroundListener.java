package net.augustus.events.listeners;

import net.augustus.events.EventManager;
import net.augustus.events.EventRenderGuiBackground;
import net.minecraft.client.gui.GuiScreen;

public interface RenderGuiBackgroundListener {
   EventRenderGuiBackground RENDER_GUI_BACKGROUND_EVENT = new EventRenderGuiBackground();

   void onRenderGuiBackground(EventRenderGuiBackground event);

   static EventRenderGuiBackground postRenderGuiBackground(GuiScreen screen) {
      RENDER_GUI_BACKGROUND_EVENT.setCancelled(false);
      EventRenderGuiBackground.setGuiScreen(RENDER_GUI_BACKGROUND_EVENT, screen);
      EventManager.getEventBus().post(54, RENDER_GUI_BACKGROUND_EVENT);
      return RENDER_GUI_BACKGROUND_EVENT;
   }
}
