package client.event;

import client.network.CosmeticsPayload;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventMouseClick extends AbstractEvent {
   public int count;
   public float factor;
   public boolean enabled2;
   public static final int count2 = 16;
   public boolean enabled = true;

   public void processAction(float factor2) {
      this.factor = factor2;
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public void setActive(boolean active) {
      this.enabled2 = active;
   }

   public void call(Object entry) {
      MouseClickListener mouseClickListener = (MouseClickListener)entry;
      this.applyMouseClickListener(mouseClickListener);
   }

   private void applyIndex(int count3, float factor2) {
      this.count = count3;
      this.factor = factor2;
      this.enabled = true;
      this.enabled2 = false;
   }

   public float getFactor() {
      return this.factor;
   }

   public int getIndex() {
      return this.count;
   }

   public void applyMouseClickListener(MouseClickListener mouseClickListener) {
      mouseClickListener.onMouseClick(this);
   }

   public void processAction(int count3) {
      this.count = count3;
   }

   public static void onMouseClick(EventMouseClick event, int count3, float factor2) {
      event.applyIndex(count3, factor2);
   }

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public void processBoolean(boolean flag) {
      if (CosmeticsPayload.checkCondition(1)) {
         this.enabled = false;
      } else {
         this.enabled = flag;
      }
   }

   public EventMouseClick() {
      this.enabled2 = false;
   }
}
