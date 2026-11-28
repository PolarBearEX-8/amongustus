package net.augustus.events;

import net.augustus.events.listeners.NoClipListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventNoClip extends AbstractEvent<NoClipListener> {
   public static final int ID = 27;

   @Override
   public void call(NoClipListener listener) {
      listener.onNoClip();
   }

}
