package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S00PacketKeepAlive implements Packet {
   private int id;

   public S00PacketKeepAlive() {
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.id = buf.readVarIntFromBuffer();
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleKeepAlive(this);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.id);
   }

   public S00PacketKeepAlive(int n) {
      this.id = n;
   }

   public int getId() {
      return this.id;
   }
}
