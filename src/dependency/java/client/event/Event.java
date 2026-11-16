package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class Event extends AbstractEvent {
   public static final int count = 53;

   public void call(Object entry) {
      EventListener event = (EventListener)entry;
      this.onListener(event);
   }

   public void onListener(EventListener event) {
      event.applyEvent(this);
   }
}
