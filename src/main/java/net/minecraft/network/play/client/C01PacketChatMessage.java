package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C01PacketChatMessage implements Packet {
   private String message;

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.message = buf.readUtf8String(100);
   }

   public C01PacketChatMessage() {
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processChatMessage(this);
   }

   public C01PacketChatMessage(String str) {
      if (str.length() > 100) {
         str = str.substring(0, 100);
      }

      this.message = str;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeString(this.message);
   }

   public String getMessage() {
      return this.message;
   }
}
