package client.event;

import de.florianmichael.dietrichevents2.CancellableEvent;

public class EventStepHeight extends CancellableEvent {
   public double value;
   public int count2;
   public static final int count = 12;

   public void processAction(double offset) {
      this.value = offset;
   }

   private void setCancelled(int count3, double offset) {
      this.setCancelled(false);
      this.count2 = count3;
      this.value = offset;
   }

   public void call(Object entry) {
      EventStepHeightCallback event = (EventStepHeightCallback)entry;
      this.onStepHeightCallback(event);
   }

   public void onStepHeightCallback(EventStepHeightCallback event) {
      event.onStepHeight(this);
   }

   public static void onStepHeight(EventStepHeight event, int count3, double offset) {
      event.setCancelled(count3, offset);
   }

   public double getValue() {
      return this.value;
   }

   public void processAction(int count3) {
      this.count2 = count3;
   }

   public int getCount2() {
      return this.count2;
   }
}
