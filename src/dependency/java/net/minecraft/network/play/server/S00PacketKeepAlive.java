package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S00PacketKeepAlive implements Packet {
   public int count;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.getInt3();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS00PacketKeepAlive(this);
   }

   public int getCount() {
      return this.count;
   }

   public S00PacketKeepAlive() {
   }

   public S00PacketKeepAlive(int count2) {
      this.count = count2;
   }
}
