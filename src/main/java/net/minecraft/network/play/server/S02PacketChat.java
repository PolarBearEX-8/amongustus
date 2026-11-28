package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S02PacketChat implements Packet {
   private IChatComponent chatComponent;
   private byte type;

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.chatComponent = buf.readChatComponent();
      this.type = buf.readByte();
   }

   public byte getType() {
      return this.type;
   }

   public void setChatComponent(IChatComponent component) {
      this.chatComponent = component;
   }

   public IChatComponent getChatComponent() {
      return this.chatComponent;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeChatComponent(this.chatComponent);
      buf.writeByte(this.type);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleChat(this);
   }

   public S02PacketChat() {
   }

   public boolean isChat() {
      return this.type == 1 || this.type == 2;
   }

   public S02PacketChat(IChatComponent component, byte b) {
      this.chatComponent = component;
      this.type = b;
   }

   public S02PacketChat(IChatComponent component) {
      this(component, (byte)1);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }
}
