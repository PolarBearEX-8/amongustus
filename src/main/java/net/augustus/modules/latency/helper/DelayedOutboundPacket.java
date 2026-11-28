package net.augustus.modules.latency.helper;

import net.minecraft.network.NetworkManager;
import io.netty.util.concurrent.GenericFutureListener;
import net.minecraft.network.Packet;

public class DelayedOutboundPacket extends DelayedPacketEntry {
   private final GenericFutureListener[] futureListeners;

   public DelayedOutboundPacket(Packet packet2, GenericFutureListener[] listeners) {
      super(packet2);
      this.futureListeners = listeners;
   }

   public NetworkManager.InboundHandlerTuplePacketListener toInboundHandlerTuple() {
      return new NetworkManager.InboundHandlerTuplePacketListener(this.getPacket(), this.futureListeners);
   }
}
