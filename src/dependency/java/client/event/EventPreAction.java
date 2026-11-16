package client.event;

import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventPreAction extends CancellableEvent {
   public static final int count = 59;

   public void call(Object entry) {
      EventPreListener event = (EventPreListener)entry;
      this.onPreListener(event);
   }

   public void onPreListener(EventPreListener event) {
      event.onPreAction(this);
   }
}
