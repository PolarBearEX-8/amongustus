package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S47PacketPlayerListHeaderFooter implements Packet {
   private IChatComponent header;
   private IChatComponent footer;

   public S47PacketPlayerListHeaderFooter(IChatComponent component) {
      this.header = component;
   }

   public IChatComponent getHeader() {
      return this.header;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handlePlayerListHeaderFooter(this);
   }

   public IChatComponent getFooter() {
      return this.footer;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.header = buf.readChatComponent();
      this.footer = buf.readChatComponent();
   }

   public S47PacketPlayerListHeaderFooter() {
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeChatComponent(this.header);
      buf.writeChatComponent(this.footer);
   }
}
