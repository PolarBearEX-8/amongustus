package client.util;

import de.florianmichael.dietrichevents2.CancellableEvent;

public class CancellableEventAdapter extends CancellableEvent {
   public float factor2;
   public float factor;
   public static final int count = 38;

   private void setCancelled(float factor3, float factor4) {
      this.factor2 = factor3;
      this.factor = factor4;
      this.setCancelled(false);
   }

   public float getFactor2() {
      return this.factor2;
   }

   public void call(Object entry) {
      CancellableEventCallbackAdapter event = (CancellableEventCallbackAdapter)entry;
      this.applyCancellableEventCallbackAd(event);
   }

   public void applyCancellableEventCallbackAd(CancellableEventCallbackAdapter event) {
      event.applyCancellableEventAdapter(this);
   }

   public static void applyCancellableEventAdapter(CancellableEventAdapter event, float factor3, float factor4) {
      event.setCancelled(factor3, factor4);
   }

   public float getFactor() {
      return this.factor;
   }
}
