package client.event;

import net.minecraft.client.gui.GuiScreen;

public interface EventGuiOpenCallback {
   EventGuiOpen eventGuiOpen = new EventGuiOpen();

   void onGuiOpen(EventGuiOpen event);

   static EventGuiOpen resolveEventGuiOpenByGuiScreen(GuiScreen screen) {
      eventGuiOpen.setCancelled(false);
      EventGuiOpen.onGuiOpenByEventGuiOpen(eventGuiOpen, screen);
      EventBusManager.getDietrichEvents2().post(54, eventGuiOpen);
      return eventGuiOpen;
   }
}
