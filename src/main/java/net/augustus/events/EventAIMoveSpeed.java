package net.augustus.events;

import net.augustus.events.listeners.AIMoveSpeedListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventAIMoveSpeed extends AbstractEvent<AIMoveSpeedListener> {
   public static final int ID = 0;
   private float moveSpeed;

   public float getMoveSpeed() {
      return this.moveSpeed;
   }

   @Override
   public void call(AIMoveSpeedListener listener) {
      listener.onAIMoveSpeed(this);
   }

   public static void reset(EventAIMoveSpeed event, float f) {
      event.setMoveSpeed(f);
   }



   public void setMoveSpeed(float f) {
      this.moveSpeed = f;
   }
}
