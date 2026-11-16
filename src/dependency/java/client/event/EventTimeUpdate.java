package client.event;

import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventTimeUpdate extends AbstractEvent {
   public static final int count = 45;
   public long timestamp;

   private void setTimestamp(long timestamp2) {
      this.timestamp = timestamp2;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public void call(Object entry) {
      EventTimeUpdateCallback event = (EventTimeUpdateCallback)entry;
      this.onTimeUpdateCallback(event);
   }

   public static void onTimeUpdate(EventTimeUpdate event) {
      long value = 6000L;
      event.setTimestamp(value);
   }

   public void updateState(long value) {
      this.timestamp = value;
   }

   public void onTimeUpdateCallback(EventTimeUpdateCallback event) {
      event.onTimeUpdate(this);
   }
}
