package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class C17PacketCustomPayload implements Packet {
   public String labelText;
   public PacketBuffer packetBuffer;

   public String getText() {
      return this.labelText;
   }

   public PacketBuffer getPacketBuffer() {
      return this.packetBuffer;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.labelText = packet.resolveTextByIndex(20);
      int count = packet.readableBytes();
      if (count >= 0 && count <= 32767) {
         this.packetBuffer = new PacketBuffer(packet.readBytes(count));
      } else {
         throw new IOException("Payload may not be larger than 32767 bytes");
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   public void setLabelText(String labelText2) {
      this.labelText = labelText2;
   }

   public void setPacketBuffer(PacketBuffer packetBuffer2) {
      this.packetBuffer = packetBuffer2;
   }

   public PacketBuffer getPacketBufferAsPacketBuffer() {
      return this.packetBuffer;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(this.labelText);
      packet.writeBytes(this.packetBuffer);
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC17PacketCustomPayload(this);
   }

   public String getLabelText() {
      return this.labelText;
   }

   public C17PacketCustomPayload(String text, PacketBuffer packet) {
      this.labelText = text;
      this.packetBuffer = packet;
      if (packet.writerIndex() > 32767) {
         throw new IllegalArgumentException("Payload may not be larger than 32767 bytes");
      }
   }

   public C17PacketCustomPayload() {
   }
}
