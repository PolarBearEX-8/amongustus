package net.augustus.events;

import net.augustus.events.listeners.SlowDownListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventSlowDown extends AbstractEvent<SlowDownListener> {
   public static final int ID = 23;
   private boolean allowSprint;
   private float forwardMultiplier;
   private float strafeMultiplier;

   @Override
   public void call(SlowDownListener listener) {
      listener.onSlowDown(this);
   }

   public boolean isAllowSprint() {
      return this.allowSprint;
   }

   public void setStrafeMultiplier(float f) {
      this.strafeMultiplier = f;
   }

   private void reset(float f, float f2) {
      this.forwardMultiplier = f;
      this.strafeMultiplier = f2;
      this.allowSprint = false;
   }

   public void setForwardMultiplier(float f) {
      this.forwardMultiplier = f;
   }

   public void setAllowSprint(boolean flag) {
      this.allowSprint = flag;
   }

   public float getStrafeMultiplier() {
      return this.strafeMultiplier;
   }


   public static void reset(EventSlowDown event, float f, float f2) {
      event.reset(f, f2);
   }

   public float getForwardMultiplier() {
      return this.forwardMultiplier;
   }
}
