package net.augustus.events;

import net.augustus.events.listeners.ChatListener;
import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventChat extends CancellableEvent<ChatListener> {
   public static final int ID = 5;
   private String message;

   @Override
   public void call(ChatListener listener) {
      listener.onChat(this);
   }

   private void reset(String str) {
      this.setCancelled(false);
      this.message = str;
   }

   public String getMessage() {
      return this.message;
   }

   public static void reset(EventChat event, String str) {
      event.reset(str);
   }

}
