package net.augustus.events;

import net.augustus.events.listeners.SwimListener;
import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventSwim extends CancellableEvent<SwimListener> {
   private float strafe;
   private float forward;
   public static final int ID = 38;

   public float getStrafe() {
      return this.strafe;
   }

   @Override
   public void call(SwimListener listener) {
      listener.onSwim(this);
   }

   private void reset(float f, float f2) {
      this.forward = f;
      this.strafe = f2;
      this.setCancelled(false);
   }


   public float getForward() {
      return this.forward;
   }

   public static void reset(EventSwim event, float f, float f2) {
      event.reset(f, f2);
   }
}
