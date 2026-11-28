package net.minecraft.network.login.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.util.IChatComponent;

public class S00PacketDisconnect implements Packet {
   private IChatComponent reason;

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeChatComponent(this.reason);
   }

   public void processPacket(INetHandlerLoginClient handler) {
      handler.handleDisconnect(this);
   }

   public S00PacketDisconnect(IChatComponent component) {
      this.reason = component;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.reason = buf.readChatComponent();
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerLoginClient)handler);
   }

   public S00PacketDisconnect() {
   }

   public IChatComponent getReason() {
      return this.reason;
   }
}
