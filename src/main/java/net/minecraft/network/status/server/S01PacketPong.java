package net.minecraft.network.status.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusClient;

public class S01PacketPong implements Packet {
   private long clientTime;

   public S01PacketPong() {
   }

   public void processPacket(INetHandlerStatusClient handler) {
      handler.handlePong(this);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerStatusClient)handler);
   }

   public S01PacketPong(long l) {
      this.clientTime = l;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.clientTime = buf.readLong();
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeLong(this.clientTime);
   }
}
