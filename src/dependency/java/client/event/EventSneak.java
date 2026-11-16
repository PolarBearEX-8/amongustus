package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventSneak extends AbstractEvent {
   public boolean enabled;
   public boolean enabled2;
   public static final int count = 39;

   public void setActive(boolean active) {
      this.enabled2 = active;
   }

   public boolean canExecute() {
      return this.enabled;
   }

   public void setEnabled(boolean enabled3) {
      this.enabled = enabled3;
   }

   public void call(Object entry) {
      SneakListener sneakListener = (SneakListener)entry;
      this.applySneakListener(sneakListener);
   }

   public static void onSneak(EventSneak event) {
      event.updateState();
   }

   public void applySneakListener(SneakListener sneakListener) {
      sneakListener.onSneak(this);
   }

   private void updateState() {
      this.enabled2 = false;
      this.enabled = false;
   }

   public boolean canExecute2() {
      return this.enabled2;
   }
}
