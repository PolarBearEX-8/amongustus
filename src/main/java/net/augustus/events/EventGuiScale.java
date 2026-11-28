package net.augustus.events;

import net.augustus.events.listeners.GuiScaleListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventGuiScale extends AbstractEvent<GuiScaleListener> {
   public static final int ID = 11;


   @Override
   public void call(GuiScaleListener listener) {
      listener.onGuiScale();
   }
}
