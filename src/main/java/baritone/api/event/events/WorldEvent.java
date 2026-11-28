package baritone.api.event.events;

import baritone.api.event.events.type.EventState;
import net.minecraft.client.multiplayer.WorldClient;

public final class WorldEvent {
   private final WorldClient world;
   private final EventState state;

   public WorldEvent(WorldClient world2, EventState event) {
      this.world = world2;
      this.state = event;
   }

   public final WorldClient getWorld() {
      return this.world;
   }

   public final EventState getState() {
      return this.state;
   }
}
