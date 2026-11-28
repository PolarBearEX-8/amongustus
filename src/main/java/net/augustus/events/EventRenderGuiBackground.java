package net.augustus.events;

import net.augustus.events.listeners.RenderGuiBackgroundListener;
import de.florianmichael.dietrichevents2.CancellableEvent;
import net.minecraft.client.gui.GuiScreen;

public class EventRenderGuiBackground extends CancellableEvent<RenderGuiBackgroundListener> {
   public static final int ID = 54;
   private GuiScreen guiScreen;

   public GuiScreen getGuiScreen() {
      return this.guiScreen;
   }


   @Override
   public void call(RenderGuiBackgroundListener listener) {
      listener.onRenderGuiBackground(this);
   }

   public static GuiScreen setGuiScreen(EventRenderGuiBackground event, GuiScreen screen) {
      return event.guiScreen = screen;
   }
}
