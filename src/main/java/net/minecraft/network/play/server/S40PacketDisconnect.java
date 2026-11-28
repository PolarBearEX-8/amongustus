package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class S40PacketDisconnect implements Packet {
   private IChatComponent reason;

   public S40PacketDisconnect(IChatComponent component) {
      this.reason = component;
   }

   public S40PacketDisconnect() {
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public IChatComponent getReason() {
      return this.reason;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeChatComponent(this.reason);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleDisconnect(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      try {
         this.reason = buf.readChatComponent();
      } catch (Throwable e) {
         this.reason = new ChatComponentText("FuckYouCrasher!");
      }
   }
}
