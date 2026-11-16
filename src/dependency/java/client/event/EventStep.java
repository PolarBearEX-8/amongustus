package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventStep extends AbstractEvent {
   public static final int count = 0;
   public float factor;

   public void call(Object entry) {
      StepListener stepListener = (StepListener)entry;
      this.applyStepListener(stepListener);
   }

   public static void onStep(EventStep event, float factor2) {
      event.setFactor(factor2);
   }

   public float getScaleFactor() {
      return this.factor;
   }

   public void applyStepListener(StepListener stepListener) {
      stepListener.onStep(this);
   }

   public void processAction(float factor2) {
      this.factor = factor2;
   }

   private void setFactor(float factor2) {
      this.factor = factor2;
   }
}
