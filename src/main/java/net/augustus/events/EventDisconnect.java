package net.augustus.events;

import net.augustus.events.listeners.DisconnectListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventDisconnect extends AbstractEvent<DisconnectListener> {
   public static final int ID = 62;

   @Override
   public void call(DisconnectListener listener) {
      listener.onDisconnect();
   }

}
