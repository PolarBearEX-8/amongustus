package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S3APacketTabComplete implements Packet {
   public String[] stringArray;

   public String[] getStringArray() {
      return this.stringArray;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.stringArray = new String[packet.getInt3()];

      for (int index = 0; index < this.stringArray.length; index++) {
         this.stringArray[index] = packet.resolveTextByIndex(32767);
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS3APacketTabComplete(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.stringArray.length);

      for (String text : this.stringArray) {
         packet.getPacketBuffer(text);
      }
   }

   public S3APacketTabComplete(String[] textArray) {
      this.stringArray = textArray;
   }

   public S3APacketTabComplete() {
   }
}
