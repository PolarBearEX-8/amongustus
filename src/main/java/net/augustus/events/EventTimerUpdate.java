package net.augustus.events;

import net.augustus.events.listeners.TimerUpdateListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventTimerUpdate extends AbstractEvent<TimerUpdateListener> {
   private float elapsedPartialTicks;
   private int elapsedTicks;
   public static final int ID = 42;

   public int getElapsedTicks() {
      return this.elapsedTicks;
   }

   public float getElapsedPartialTicks() {
      return this.elapsedPartialTicks;
   }

   @Override
   public void call(TimerUpdateListener listener) {
      listener.onTimerUpdate(this);
   }

   private void reset(float f, int n) {
      this.elapsedPartialTicks = f;
      this.elapsedTicks = n;
   }

   public void setElapsedPartialTicks(float f) {
      this.elapsedPartialTicks = f;
   }

   public static void reset(EventTimerUpdate event, float f, int n) {
      event.reset(f, n);
   }

   public void setElapsedTicks(int n) {
      this.elapsedTicks = n;
   }

}
