package client.event;

import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventCancelAction extends CancellableEvent {
   public static final int count = 4;

   public void onCancelListener(EventCancelListener event) {
      event.onCancelAction(this);
   }

   public void call(Object entry) {
      EventCancelListener event = (EventCancelListener)entry;
      this.onCancelListener(event);
   }
}
