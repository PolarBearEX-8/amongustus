package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class S40PacketDisconnect implements Packet {
   public IChatComponent iChatComponent;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS40PacketDisconnect(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      try {
         this.iChatComponent = packet.getChatComponent();
      } catch (Throwable throwable) {
         this.iChatComponent = new ChatComponentText("FuckYouCrasher!");
      }
   }

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.setChatComponent(this.iChatComponent);
   }

   public S40PacketDisconnect(IChatComponent iChatComponent2) {
      this.iChatComponent = iChatComponent2;
   }

   public S40PacketDisconnect() {
   }
}
