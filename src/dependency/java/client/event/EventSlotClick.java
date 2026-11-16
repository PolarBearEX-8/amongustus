package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventSlotClick extends AbstractEvent {
   public static final int count2 = 51;
   public int count;
   public int count3 = 0;
   public boolean enabled;

   public void onSlotClickCallback(EventSlotClickCallback event) {
      event.onSlotClick(this);
   }

   public void updateState2() {
      this.count++;
   }

   public void setActive(boolean active) {
      this.enabled = active;
   }

   private void updateState() {
      this.count3 = 0;
      this.count = 0;
      this.enabled = false;
   }

   public void call(Object entry) {
      EventSlotClickCallback event = (EventSlotClickCallback)entry;
      this.onSlotClickCallback(event);
   }

   public boolean isEnabled() {
      return this.enabled;
   }

   public static void onSlotClick(EventSlotClick event) {
      event.updateState();
   }

   public int getCount3() {
      return this.count3;
   }

   public int getCount() {
      return this.count;
   }

   public void updateState3() {
      this.count3++;
   }

   public EventSlotClick() {
      this.count = 0;
      this.enabled = false;
   }
}
