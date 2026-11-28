package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C17PacketCustomPayload implements Packet {
   private String channel;
   private PacketBuffer data;

   public String getChannel() {
      return this.channel;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.channel = buf.readStringFromBuffer(20);
      int n = buf.readableBytes();
      if (n >= 0 && n <= 32767) {
         this.data = new PacketBuffer(buf.readBytes(n));
      } else {
         throw new IOException("Payload may not be larger than 32767 bytes");
      }
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeString(this.channel);
      buf.writeBytes(this.data);
   }

   public PacketBuffer getData() {
      return this.data;
   }

   public C17PacketCustomPayload(String str, PacketBuffer buf) {
      this.channel = str;
      this.data = buf;
      if (buf.writerIndex() > 32767) {
         throw new IllegalArgumentException("Payload may not be larger than 32767 bytes");
      }
   }

   public String getChannelName() {
      return this.channel;
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processVanilla250Packet(this);
   }

   public PacketBuffer getBufferData() {
      return this.data;
   }

   public void setChannel(String str) {
      this.channel = str;
   }

   public C17PacketCustomPayload() {
   }

   public void setData(PacketBuffer buf) {
      this.data = buf;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }
}
