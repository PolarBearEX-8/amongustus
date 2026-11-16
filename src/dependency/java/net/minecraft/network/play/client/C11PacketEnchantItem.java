package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class C11PacketEnchantItem implements Packet {
   public int count2;
   public int count;

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC11PacketEnchantItem(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count2 = packet.readByte();
      this.count = packet.readByte();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeByte(this.count2);
      packet.writeByte(this.count);
   }

   public int getCount() {
      return this.count;
   }

   public int getCount2() {
      return this.count2;
   }

   public C11PacketEnchantItem(int count3, int count4) {
      this.count2 = count3;
      this.count = count4;
   }

   public C11PacketEnchantItem() {
   }
}
