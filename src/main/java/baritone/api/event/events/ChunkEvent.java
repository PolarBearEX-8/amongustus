package baritone.api.event.events;

import baritone.api.event.events.type.EventState;

public final class ChunkEvent {
   private final EventState state;
   private final ChunkEvent.Type type;
   private final int x;
   private final int z;

   public ChunkEvent.Type getType() {
      return this.type;
   }

   public int getZ() {
      return this.z;
   }

   public EventState getState() {
      return this.state;
   }

   public boolean isPostPopulate() {
      return this.state == EventState.POST && this.type.isPopulate();
   }

   public ChunkEvent(EventState event, ChunkEvent.Type type2, int n, int n2) {
      this.state = event;
      this.type = type2;
      this.x = n;
      this.z = n2;
   }

   public int getX() {
      return this.x;
   }


   public enum Type {
      LOAD,
      UNLOAD,
      POPULATE_FULL,
      POPULATE_PARTIAL;

   public final boolean isPopulate() {
         return this == POPULATE_FULL || this == POPULATE_PARTIAL;
      }
   }
}
