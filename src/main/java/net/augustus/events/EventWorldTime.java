package net.augustus.events;

import net.augustus.events.listeners.WorldTimeListener;
import de.florianmichael.dietrichevents2.AbstractEvent;

public class EventWorldTime extends AbstractEvent<WorldTimeListener> {
   public static final int ID = 45;
   private long worldTime;


   public long getWorldTime() {
      return this.worldTime;
   }

   public void setWorldTime(long l) {
      this.worldTime = l;
   }

   @Override
   public void call(WorldTimeListener listener) {
      listener.onWorldTime(this);
   }

   public static void reset(EventWorldTime event, long l) {
      event.setWorldTime(l);
   }

}
