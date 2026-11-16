package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventLiquidCollision extends AbstractEvent {
   public float factor;
   public double value3;
   public double value;
   public PreMotionConsumer preMotionConsumer;
   public static final int count = 57;
   public boolean enabled;
   public double value2;

   public PreMotionConsumer getPreMotionConsumer() {
      return this.preMotionConsumer;
   }

   public double getValue3() {
      return this.value3;
   }

   public double getValue() {
      return this.value;
   }

   public void call(Object entry) {
      EventLiquidCollisionCallback event = (EventLiquidCollisionCallback)entry;
      this.onLiquidCollisionCallback(event);
   }

   public double getValue2() {
      return this.value2;
   }

   public boolean canExecute() {
      return this.enabled;
   }

   public void applyPreMotionConsumer(PreMotionConsumer preMotionConsumer2, double posX, double posY, double posZ, float factor2, boolean flag) {
      this.preMotionConsumer = preMotionConsumer2;
      this.value3 = posX;
      this.value = posY;
      this.value2 = posZ;
      this.factor = factor2;
      this.enabled = flag;
   }

   public float getFactor() {
      return this.factor;
   }

   public void onLiquidCollisionCallback(EventLiquidCollisionCallback event) {
      event.onLiquidCollision(this);
   }
}
