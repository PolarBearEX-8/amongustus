package client.util;

import client.event.EventClickGuiListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class ExtraAbstractEventAdapter extends AbstractEvent {
   public static final int count = 8;

   public void onClickGuiListener(EventClickGuiListener event) {
      event.processAction();
   }

   public void call(Object entry) {
      EventClickGuiListener event = (EventClickGuiListener)entry;
      this.onClickGuiListener(event);
   }
}
