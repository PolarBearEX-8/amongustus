package client.util;

import client.event.EventPhaseListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class CustomAbstractEventAdapter extends AbstractEvent {
   public static final int count = 27;

   public void call(Object entry) {
      EventPhaseListener event = (EventPhaseListener)entry;
      this.onPhaseListener(event);
   }

   public void onPhaseListener(EventPhaseListener event) {
      event.updateState3();
   }
}
