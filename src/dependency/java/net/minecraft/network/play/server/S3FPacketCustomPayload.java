package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S3FPacketCustomPayload implements Packet {
   public String labelText;
   public PacketBuffer packetBuffer;

   public String getLabelText() {
      return this.labelText;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.labelText = packet.resolveTextByIndex(20);
      int count = packet.readableBytes();
      if (count >= 0 && count <= 1048576) {
         this.packetBuffer = new PacketBuffer(packet.readBytes(count));
      } else {
         throw new IOException("Payload may not be larger than 1048576 bytes");
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS3FPacketCustomPayload(this);
      this.packetBuffer.release();
   }

   public PacketBuffer getPacketBuffer() {
      return this.packetBuffer;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(this.labelText);
      packet.writeBytes(this.packetBuffer);
   }

   public S3FPacketCustomPayload() {
   }

   public S3FPacketCustomPayload(String text, PacketBuffer packet) {
      this.labelText = text;
      this.packetBuffer = packet;
      if (packet.writerIndex() > 1048576) {
         throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
      }
   }
}
