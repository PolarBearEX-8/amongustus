package net.augustus.events;

import net.augustus.events.listeners.DropItemListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventDropItem extends AbstractEvent<DropItemListener> {
   public static final int ID = 9;


   @Override
   public void call(DropItemListener listener) {
      listener.onDropItem();
   }
}
