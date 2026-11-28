package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C00PacketKeepAlive implements Packet {
   private int key;

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processKeepAlive(this);
   }

   public C00PacketKeepAlive() {
   }

   public C00PacketKeepAlive(int n) {
      this.key = n;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.key = buf.readVarIntFromBuffer();
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.key);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   public int getKey() {
      return this.key;
   }
}
