package baritone.api.event.events;

import baritone.api.event.events.type.EventState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;

public final class PacketEvent {
   private final NetworkManager networkManager;
   private final EventState state;
   private final Packet packet;

   public final Packet getPacket() {
      return this.packet;
   }

   public final Packet getPacket2() {
      return this.packet;
   }

   public final NetworkManager getNetworkManager() {
      return this.networkManager;
   }

   public final EventState getState() {
      return this.state;
   }

   public PacketEvent(NetworkManager networkManager2, EventState event, Packet packet2) {
      this.networkManager = networkManager2;
      this.state = event;
      this.packet = packet2;
   }
}
