package net.augustus.events;

import net.augustus.events.listeners.RayCastListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventRayCast extends AbstractEvent<RayCastListener> {
   public static final int ID = 29;

   @Override
   public void call(RayCastListener listener) {
      listener.onRayCast();
   }

}
