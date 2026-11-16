package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.IChatComponent;

public class S47PacketPlayerListHeaderFooter implements Packet {
   public IChatComponent iChatComponent;
   public IChatComponent iChatComponent2;

   public IChatComponent getIChatComponent() {
      return this.iChatComponent;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.iChatComponent = packet.getChatComponent();
      this.iChatComponent2 = packet.getChatComponent();
   }

   public IChatComponent getIChatComponent2() {
      return this.iChatComponent2;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.setChatComponent(this.iChatComponent);
      packet.setChatComponent(this.iChatComponent2);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS47PacketPlayerListHeaderF(this);
   }

   public S47PacketPlayerListHeaderFooter() {
   }

   public S47PacketPlayerListHeaderFooter(IChatComponent iChatComponent3) {
      this.iChatComponent = iChatComponent3;
   }
}
