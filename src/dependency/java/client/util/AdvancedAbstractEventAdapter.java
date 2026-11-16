package client.util;

import client.event.EventRenderArrayListListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class AdvancedAbstractEventAdapter extends AbstractEvent {
   public static final int count = 11;

   public void onRenderArrayListListener(EventRenderArrayListListener event) {
      event.updateState();
   }

   public void call(Object entry) {
      EventRenderArrayListListener event = (EventRenderArrayListListener)entry;
      this.onRenderArrayListListener(event);
   }
}
