package client.event;

import client.setting.PhaseMode;
import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventConnection extends CancellableEvent {
   public static final int count = 60;
   public PhaseMode phaseMode;

   public PhaseMode getPhaseMode() {
      return this.phaseMode;
   }

   public void setCancelled(PhaseMode cancelled) {
      this.phaseMode = cancelled;
      this.setCancelled(false);
   }

   public void call(Object entry) {
      EventConnectionCallback event = (EventConnectionCallback)entry;
      this.onConnectionCallback(event);
   }

   public void onConnectionCallback(EventConnectionCallback event) {
      if (this.phaseMode == PhaseMode.PRE) {
         event.onConnection(this);
      } else {
         event.onConnectionByEventConnection(this);
      }
   }
}
