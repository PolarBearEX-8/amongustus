package baritone.api.event.events;

import baritone.api.event.events.type.EventState;
import java.util.function.BiFunction;

public final class TickEvent {
   private static int overallTickCount;
   private final EventState state;
   private final TickEvent.Type type;
   private final int count;

   public TickEvent.Type getType() {
      return this.type;
   }

   public EventState getState() {
      return this.state;
   }

   public TickEvent(EventState event, TickEvent.Type type2, int n) {
      this.state = event;
      this.type = type2;
      this.count = n;
   }

   public int getCount() {
      return this.count;
   }

   public static synchronized BiFunction createNextProvider() {
      int n = overallTickCount++;
      return (BiFunction<EventState, TickEvent.Type, TickEvent>)(event, type2) -> new TickEvent(event, type2, n);
   }


   public enum Type {
      IN,
      OUT;
   }
}
