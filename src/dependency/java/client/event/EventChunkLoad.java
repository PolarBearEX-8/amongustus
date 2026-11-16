package client.event;

import client.setting.MovementAngleProperty;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventChunkLoad extends AbstractEvent {
   public boolean enabled;
   public static final int count2 = 22;
   public MovementAngleProperty movementAngleProperty;

   public void setActive(boolean active) {
      this.enabled = active;
   }

   private void applyMovementAngleProperty(boolean flag, MovementAngleProperty movementAngleProperty2) {
      this.enabled = flag;
      this.movementAngleProperty = movementAngleProperty2;
   }

   public void call(Object entry) {
      EventChunkLoadCallback event = (EventChunkLoadCallback)entry;
      this.onChunkLoadCallback(event);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void onChunkLoadCallback(EventChunkLoadCallback event) {
      if (this.movementAngleProperty == MovementAngleProperty.NIGHT_VISION) {
         event.onChunkLoad2(this);
      } else {
         event.onChunkLoad(this);
      }
   }

   public static void onChunkLoad(EventChunkLoad event, boolean flag, MovementAngleProperty movementAngleProperty2) {
      event.applyMovementAngleProperty(flag, movementAngleProperty2);
   }
}
