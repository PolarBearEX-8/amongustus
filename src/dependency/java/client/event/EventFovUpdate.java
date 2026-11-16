package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventFovUpdate extends AbstractEvent {
   public float factor;
   public static final int count = 46;

   public void onFovUpdateCallback(EventFovUpdateCallback event) {
      event.onFovUpdate(this);
   }

   public static void onFovUpdate(EventFovUpdate event, float factor2) {
      event.setFactor(factor2);
   }

   public void processAction(float factor2) {
      this.factor = factor2;
   }

   public void call(Object entry) {
      EventFovUpdateCallback event = (EventFovUpdateCallback)entry;
      this.onFovUpdateCallback(event);
   }

   public float getFactor() {
      return this.factor;
   }

   private void setFactor(float factor2) {
      this.factor = factor2;
   }
}
