package net.augustus.events;

import net.augustus.events.listeners.AsyncTickListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventAsyncTick extends AbstractEvent<AsyncTickListener> {
   public static final int ID = 61;

   @Override
   public void call(AsyncTickListener listener) {
      listener.onAsyncTick();
   }

}
