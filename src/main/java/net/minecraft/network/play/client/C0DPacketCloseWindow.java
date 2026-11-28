package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0DPacketCloseWindow implements Packet {
   private int windowId;

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.windowId);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   public C0DPacketCloseWindow() {
   }

   public int getWindowId() {
      return this.windowId;
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processCloseWindow(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.windowId = buf.readByte();
   }

   public C0DPacketCloseWindow(int n) {
      this.windowId = n;
   }
}
