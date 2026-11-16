package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.util.IChatComponent;

public class S02PacketChat implements Packet {
   public IChatComponent chatComponent;
   public byte byteIndex;

   public boolean isActive() {
      return this.byteIndex == 1 || this.byteIndex == 2;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.chatComponent = packet.getChatComponent();
      this.byteIndex = packet.readByte();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processAction((INetHandlerPlayClient)iNetHandler);
   }

   public void processAction(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.handleS02PacketChat(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.setChatComponent(this.chatComponent);
      packet.writeByte(this.byteIndex);
   }

   public void setChatComponent(IChatComponent chatComponent2) {
      this.chatComponent = chatComponent2;
   }

   public IChatComponent getChatComponent() {
      return this.chatComponent;
   }

   public byte getByteIndex() {
      return this.byteIndex;
   }

   public S02PacketChat() {
   }

   public S02PacketChat(IChatComponent iChatComponent, byte byteValue) {
      this.chatComponent = iChatComponent;
      this.byteIndex = byteValue;
   }

   public S02PacketChat(IChatComponent iChatComponent) {
      this(iChatComponent, (byte)1);
   }
}
