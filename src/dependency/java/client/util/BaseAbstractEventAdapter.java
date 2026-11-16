package client.util;

import client.event.ClickTickListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class BaseAbstractEventAdapter extends AbstractEvent {
   public static final int count = 52;
   public boolean enabled = false;

   public static void applyBaseAbstractEventAdapter(BaseAbstractEventAdapter event) {
      event.updateState();
   }

   public void call(Object entry) {
      ClickTickListener clickTickListener = (ClickTickListener)entry;
      this.applyClickTickListener(clickTickListener);
   }

   private void updateState() {
      this.enabled = false;
   }

   public void applyClickTickListener(ClickTickListener clickTickListener) {
      if (!this.enabled) {
         this.enabled = clickTickListener.checkCondition();
      }
   }
}
