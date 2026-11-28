package net.augustus.events;

import net.augustus.events.listeners.NightVisionListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventNightVision extends AbstractEvent<NightVisionListener> {
   private boolean enabled;
   public static final int ID = 22;
   private NightVisionPhase phase;

   @Override
   public void call(NightVisionListener listener) {
      if (this.phase == NightVisionPhase.NIGHT_VISION) {
         listener.onNightVision(this);
      } else {
         listener.onFogNightVision(this);
      }
   }

   private void reset(boolean flag, NightVisionPhase nightVisionPhase) {
      this.enabled = flag;
      this.phase = nightVisionPhase;
   }

   public static void reset(EventNightVision event, boolean flag, NightVisionPhase nightVisionPhase) {
      event.reset(flag, nightVisionPhase);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setEnabled(boolean flag) {
      this.enabled = flag;
   }

}
