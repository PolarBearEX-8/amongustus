package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventCameraTransform extends AbstractEvent {
   public static final int count = 23;
   public float factor;
   public float factor2;
   public boolean enabled;

   public static void onCameraTransform(EventCameraTransform event, float factor3, float factor4) {
      event.applyFloat(factor3, factor4);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void onCameraTransformCallback(EventCameraTransformCallback event) {
      event.onCameraTransform(this);
   }

   public float getScaleFactor() {
      return this.factor2;
   }

   public void processAction(float factor3) {
      this.factor = factor3;
   }

   public float getScaleFactor2() {
      return this.factor;
   }

   public void call(Object entry) {
      EventCameraTransformCallback event = (EventCameraTransformCallback)entry;
      this.onCameraTransformCallback(event);
   }

   public void processAction2(float factor3) {
      this.factor2 = factor3;
   }

   public void setActive(boolean active) {
      this.enabled = active;
   }

   private void applyFloat(float factor3, float factor4) {
      this.factor2 = factor3;
      this.factor = factor4;
      this.enabled = false;
   }
}
