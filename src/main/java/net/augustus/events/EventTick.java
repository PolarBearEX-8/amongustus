package net.augustus.events;

import net.augustus.events.listeners.TickListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventTick extends AbstractEvent<TickListener> {
   public static final int ID = 40;
   public TickPhase phase;

   private void setPhase(TickPhase tickPhase) {
      this.phase = tickPhase;
   }

   public static void reset(EventTick event, TickPhase tickPhase) {
      event.setPhase(tickPhase);
   }


   @Override
   public void call(TickListener listener) {
      switch (this.phase) {
         case PRE:
            listener.onPreTick();
            break;
         case EARLY:
            listener.onEarlyTick();
            break;
         case LATE:
            listener.onLateTick();
            break;
         case POST:
            listener.onPostTick();
            break;
         case TICK:
            listener.onTick();
      }
   }
}
