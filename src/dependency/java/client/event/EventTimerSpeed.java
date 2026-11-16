package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventTimerSpeed extends AbstractEvent {
   public float factor;
   public boolean enabled;
   public static final int count = 48;

   public float getScaleFactor() {
      return this.factor;
   }

   public boolean canExecute() {
      return this.enabled;
   }

   public static void onTimerSpeed(EventTimerSpeed event, float factor2, boolean flag) {
      event.applyFloat(factor2, flag);
   }

   public void processAction(float factor2) {
      this.factor = factor2;
   }

   public void call(Object entry) {
      EventTimerSpeedCallback event = (EventTimerSpeedCallback)entry;
      this.onTimerSpeedCallback(event);
   }

   public void onTimerSpeedCallback(EventTimerSpeedCallback event) {
      event.onTimerSpeed(this);
   }

   private void applyFloat(float factor2, boolean flag) {
      this.factor = factor2;
      this.enabled = flag;
   }
}
