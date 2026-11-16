package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventRenderHand extends AbstractEvent {
   public static final int count2 = 42;
   public float factor;
   public int count;

   public void onRenderHandCallback(EventRenderHandCallback event) {
      event.onRenderHand(this);
   }

   public static void onRenderHand(EventRenderHand event, float factor2, int count3) {
      event.applyFloat(factor2, count3);
   }

   public void call(Object entry) {
      EventRenderHandCallback event = (EventRenderHandCallback)entry;
      this.onRenderHandCallback(event);
   }

   public void processAction(float factor2) {
      this.factor = factor2;
   }

   public void setCount(int count3) {
      this.count = count3;
   }

   private void applyFloat(float factor2, int count3) {
      this.factor = factor2;
      this.count = count3;
   }

   public float getFactor() {
      return this.factor;
   }

   public int getIndex() {
      return this.count;
   }
}
