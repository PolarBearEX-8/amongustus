package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S2BPacketChangeGameState implements Packet {
   public static final String[] stringArray;
   public int count;
   public float factor;

   public float getScaleFactor() {
      return this.factor;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS2BPacketChangeGameState(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.readUnsignedByte();
      this.factor = packet.readFloat();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public void setFactor(float factor2) {
      this.factor = factor2;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeByte(this.count);
      packet.writeFloat(this.factor);
   }

   public int getIndex() {
      return this.count;
   }

   public S2BPacketChangeGameState(int count2, float factor2) {
      this.count = count2;
      this.factor = factor2;
   }

   public S2BPacketChangeGameState() {
   }

   static {
      String text = "tile.bed.notValid";
      stringArray = new String[]{text};
   }
}
