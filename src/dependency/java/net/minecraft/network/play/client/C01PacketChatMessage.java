package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class C01PacketChatMessage implements Packet {
   public String labelText;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.labelText = packet.resolveText(100);
   }

   public String getText() {
      return this.labelText;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(this.labelText);
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC01PacketChatMessage(this);
   }

   public C01PacketChatMessage() {
   }

   public C01PacketChatMessage(String text) {
      if (text.length() > 100) {
         text = text.substring(0, 100);
      }

      this.labelText = text;
   }
}
