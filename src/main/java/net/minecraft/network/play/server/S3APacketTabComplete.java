package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S3APacketTabComplete implements Packet {
   private String[] matches;

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S3APacketTabComplete() {
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleTabComplete(this);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.matches.length);

      for (String str : this.matches) {
         buf.writeString(str);
      }
   }

   public S3APacketTabComplete(String[] strings) {
      this.matches = strings;
   }

   public String[] getMatches() {
      return this.matches;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.matches = new String[buf.readVarIntFromBuffer()];

      for (int i = 0; i < this.matches.length; i++) {
         this.matches[i] = buf.readStringFromBuffer(32767);
      }
   }
}
