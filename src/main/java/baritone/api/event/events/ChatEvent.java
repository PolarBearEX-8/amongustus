package baritone.api.event.events;

import baritone.api.event.events.type.Cancellable;

public final class ChatEvent extends Cancellable {
   private final String message;

   public ChatEvent(String str) {
      this.message = str;
   }

   public final String getMessage() {
      return this.message;
   }
}
