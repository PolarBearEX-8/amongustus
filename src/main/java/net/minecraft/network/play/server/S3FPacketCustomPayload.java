package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S3FPacketCustomPayload implements Packet {
   private String channel;
   private PacketBuffer data;

   public PacketBuffer getBufferData() {
      return this.data;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.channel = buf.readStringFromBuffer(20);
      int n = buf.readableBytes();
      if (n >= 0 && n <= 1048576) {
         this.data = new PacketBuffer(buf.readBytes(n));
      } else {
         throw new IOException("Payload may not be larger than 1048576 bytes");
      }
   }

   public S3FPacketCustomPayload(String str, PacketBuffer buf) {
      this.channel = str;
      this.data = buf;
      if (buf.writerIndex() > 1048576) {
         throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
      }
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleCustomPayload(this);
      this.data.release();
   }

   public S3FPacketCustomPayload() {
   }

   public String getChannelName() {
      return this.channel;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeString(this.channel);
      buf.writeBytes(this.data);
   }
}
