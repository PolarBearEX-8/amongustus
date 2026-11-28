package net.augustus.events;

import net.augustus.events.listeners.LadderListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventLadder extends AbstractEvent<LadderListener> {
   public static final int ID = 15;
   private double motionY;

   public static void reset(EventLadder event, double d) {
      event.setMotionY(d);
   }

   @Override
   public void call(LadderListener listener) {
      listener.onLadder(this);
   }

   public double getMotionY() {
      return this.motionY;
   }

   public void setMotionY(double d) {
      this.motionY = d;
   }


}
