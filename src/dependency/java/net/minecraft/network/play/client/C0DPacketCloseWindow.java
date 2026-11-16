package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class C0DPacketCloseWindow implements Packet {
   public int count;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.readByte();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   public int getIndex() {
      return this.count;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeByte(this.count);
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC0DPacketCloseWindow(this);
   }

   public C0DPacketCloseWindow() {
   }

   public C0DPacketCloseWindow(int count2) {
      this.count = count2;
   }
}
