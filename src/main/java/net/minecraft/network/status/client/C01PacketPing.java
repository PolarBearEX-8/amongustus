package net.minecraft.network.status.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusServer;

public class C01PacketPing implements Packet {
   private long clientTime;

   public C01PacketPing() {
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerStatusServer)handler);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeLong(this.clientTime);
   }

   public C01PacketPing(long l) {
      this.clientTime = l;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.clientTime = buf.readLong();
   }

   public void processPacket(INetHandlerStatusServer handler) {
      handler.processPing(this);
   }

   public long getClientTime() {
      return this.clientTime;
   }
}
