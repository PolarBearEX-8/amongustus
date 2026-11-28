package net.augustus.events;

import net.augustus.events.listeners.SwingListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventSwing extends AbstractEvent<SwingListener> {
   private boolean cancelClientSwing;
   private boolean cancelServerSwing;
   public static final int ID = 39;

   public void setCancelServerSwing(boolean flag) {
      this.cancelServerSwing = flag;
   }


   public static void reset(EventSwing event) {
      event.reset();
   }

   public boolean cancelClientSwing() {
      return this.cancelClientSwing;
   }

   private void reset() {
      this.cancelClientSwing = false;
      this.cancelServerSwing = false;
   }

   public boolean cancelServerSwing() {
      return this.cancelServerSwing;
   }

   @Override
   public void call(SwingListener listener) {
      listener.onSwing(this);
   }

   public void setCancelClientSwing(boolean flag) {
      this.cancelClientSwing = flag;
   }
}
