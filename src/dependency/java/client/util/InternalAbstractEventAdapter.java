package client.util;

import client.event.EventAutoSoupListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class InternalAbstractEventAdapter extends AbstractEvent {
   public static final int count = 9;

   public void call(Object entry) {
      EventAutoSoupListener event = (EventAutoSoupListener)entry;
      this.onAutoSoupListener(event);
   }

   public void onAutoSoupListener(EventAutoSoupListener event) {
      event.eatSoup();
   }
}
