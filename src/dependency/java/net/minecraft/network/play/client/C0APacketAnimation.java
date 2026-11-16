package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class C0APacketAnimation implements Packet {
   @Override
   public void processAction(PacketBuffer packet) throws IOException {
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC0APacketAnimation(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
   }
}
