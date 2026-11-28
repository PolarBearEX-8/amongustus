package net.augustus.events;

import net.augustus.events.listeners.TimerListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventTimer extends AbstractEvent<TimerListener> {
   private TimerEventType type;
   private int extraTicks;
   private float timerSpeed;
   public static final int ID = 41;

   @Override
   public void call(TimerListener listener) {
      if (this.type == TimerEventType.TICK) {
         listener.onTimerTick(this);
      } else {
         listener.onTimerSpeed(this);
      }
   }

   public float getTimerSpeed() {
      return this.timerSpeed;
   }

   public static void reset(EventTimer event, int n, float f, TimerEventType timerEventType) {
      event.reset(n, f, timerEventType);
   }


   public void setExtraTicks(int n) {
      this.extraTicks = n;
   }

   private void reset(int n, float f, TimerEventType timerEventType) {
      this.extraTicks = n;
      this.timerSpeed = f;
      this.type = timerEventType;
   }

   public int getExtraTicks() {
      return this.extraTicks;
   }

   public void setTimerSpeed(float f) {
      this.timerSpeed = f;
   }
}
