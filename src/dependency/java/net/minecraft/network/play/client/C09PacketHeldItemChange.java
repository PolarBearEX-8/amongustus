package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class C09PacketHeldItemChange implements Packet {
   public int count;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.readShort();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeShort(this.count);
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC09PacketHeldItemChange(this);
   }

   public C09PacketHeldItemChange(int count2) {
      this.count = count2;
   }

   public C09PacketHeldItemChange() {
   }
}
