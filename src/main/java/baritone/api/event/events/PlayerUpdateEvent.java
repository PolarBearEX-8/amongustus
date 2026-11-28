package baritone.api.event.events;

import baritone.api.event.events.type.EventState;

public final class PlayerUpdateEvent {
   private final EventState state;

   public final EventState getState() {
      return this.state;
   }

   public PlayerUpdateEvent(EventState event) {
      this.state = event;
   }
}
