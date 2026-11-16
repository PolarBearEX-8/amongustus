package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S06PacketUpdateHealth implements Packet {
   public float factor2;
   public int count;
   public float factor;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS06PacketUpdateHealth(this);
   }

   public float getFactor() {
      return this.factor;
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.factor2 = packet.readFloat();
      this.count = packet.getInt3();
      this.factor = packet.readFloat();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public float getScaleFactor() {
      return this.factor2;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeFloat(this.factor2);
      packet.updateInt(this.count);
      packet.writeFloat(this.factor);
   }

   public S06PacketUpdateHealth() {
   }

   public S06PacketUpdateHealth(float factor3, int count2, float factor4) {
      this.factor2 = factor3;
      this.count = count2;
      this.factor = factor4;
   }
}
