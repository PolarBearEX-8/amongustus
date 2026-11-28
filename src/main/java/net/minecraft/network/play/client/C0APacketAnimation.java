package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0APacketAnimation implements Packet {
   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.handleAnimation(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
   }
}
