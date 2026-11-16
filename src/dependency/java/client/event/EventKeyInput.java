package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventKeyInput extends AbstractEvent {
   public boolean enabled;
   public static final int count = 14;
   public int count2;

   public boolean isEnabled() {
      return this.enabled;
   }

   public int getIndex() {
      return this.count2;
   }

   public static void onKeyInput(EventKeyInput event, int count3, boolean flag) {
      event.applyIndex(count3, flag);
   }

   public void call(Object entry) {
      KeyInputListener keyInputListener = (KeyInputListener)entry;
      this.applyKeyInputListener(keyInputListener);
   }

   public void applyKeyInputListener(KeyInputListener keyInputListener) {
      keyInputListener.onKeyInput(this);
   }

   private void applyIndex(int count3, boolean flag) {
      this.count2 = count3;
      this.enabled = flag;
   }
}
