package client.event;

import de.florianmichael.dietrichevents2.CancellableEvent;
import net.minecraft.client.gui.GuiScreen;

public class EventGuiOpen extends CancellableEvent {
   public GuiScreen guiScreen;
   public static final int count = 54;

   public void onGuiOpenCallback(EventGuiOpenCallback event) {
      event.onGuiOpen(this);
   }

   public static GuiScreen onGuiOpenByEventGuiOpen(EventGuiOpen event, GuiScreen screen) {
      return event.guiScreen = screen;
   }

   public void call(Object entry) {
      EventGuiOpenCallback event = (EventGuiOpenCallback)entry;
      this.onGuiOpenCallback(event);
   }

   public GuiScreen getGuiScreen() {
      return this.guiScreen;
   }
}
