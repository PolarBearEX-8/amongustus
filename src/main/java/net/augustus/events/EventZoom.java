package net.augustus.events;

import net.augustus.events.listeners.ZoomListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventZoom extends AbstractEvent<ZoomListener> {
   public static final int ID = 46;
   private float fovMultiplier;

   public float getFovMultiplier() {
      return this.fovMultiplier;
   }

   public void setFovMultiplier(float f) {
      this.fovMultiplier = f;
   }



   @Override
   public void call(ZoomListener listener) {
      listener.onZoom(this);
   }

   public static void reset(EventZoom event, float f) {
      event.setFovMultiplier(f);
   }
}
