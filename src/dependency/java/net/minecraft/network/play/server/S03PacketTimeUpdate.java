package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S03PacketTimeUpdate implements Packet {
   public long timestamp;
   public long timestamp2;

   public long getTimestamp() {
      return this.timestamp;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS03PacketTimeUpdate(this);
   }

   public long getTimestamp2() {
      return this.timestamp2;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.timestamp = packet.readLong();
      this.timestamp2 = packet.readLong();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeLong(this.timestamp);
      packet.writeLong(this.timestamp2);
   }

   public S03PacketTimeUpdate(long value, long value2, boolean flag) {
      this.timestamp = value;
      this.timestamp2 = value2;
      if (!flag) {
         this.timestamp2 = -this.timestamp2;
         if (this.timestamp2 == 0L) {
            this.timestamp2 = -1L;
         }
      }
   }

   public S03PacketTimeUpdate() {
   }
}
