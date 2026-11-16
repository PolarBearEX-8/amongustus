package client.event;

import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventCommand extends CancellableEvent {
   public static final int count = 5;
   public String labelText;

   public void onCommandCallback(EventCommandCallback event) {
      event.onCommand(this);
   }

   public String getText() {
      return this.labelText;
   }

   public void call(Object entry) {
      EventCommandCallback event = (EventCommandCallback)entry;
      this.onCommandCallback(event);
   }

   private void setCancelled(String cancelled) {
      this.setCancelled(false);
      this.labelText = cancelled;
   }

   public static void onCommand(EventCommand event, String text) {
      event.setCancelled(text);
   }
}
