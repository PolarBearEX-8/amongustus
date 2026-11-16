package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S48PacketResourcePackSend implements Packet {
   public String labelText;
   public String labelText2;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.labelText = packet.resolveTextByIndex(32767);
      this.labelText2 = packet.resolveTextByIndex(40);
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.getPacketBuffer(this.labelText);
      packet.getPacketBuffer(this.labelText2);
   }

   public String getText() {
      return this.labelText2;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS48PacketResourcePackSend(this);
   }

   public String getText2() {
      return this.labelText;
   }

   public S48PacketResourcePackSend() {
   }

   public S48PacketResourcePackSend(String text, String text2) {
      this.labelText = text;
      this.labelText2 = text2;
      if (text2.length() > 40) {
         throw new IllegalArgumentException("Hash is too long (max 40, was " + text2.length() + ")");
      }
   }
}
