package net.augustus.events;

import net.augustus.events.listeners.LeftClickListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventLeftClick extends AbstractEvent<LeftClickListener> {
   public static final int ID = 53;

   @Override
   public void call(LeftClickListener listener) {
      listener.onLeftClick(this);
   }

}
