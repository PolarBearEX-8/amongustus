package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;

public class S1FPacketSetExperience implements Packet {
   public float factor;
   public int count2;
   public int count;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS1FPacketSetExperience(this);
   }

   public float getFactor() {
      return this.factor;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.factor = packet.readFloat();
      this.count = packet.getInt3();
      this.count2 = packet.getInt3();
   }

   public int getCount() {
      return this.count;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeFloat(this.factor);
      packet.updateInt(this.count);
      packet.updateInt(this.count2);
   }

   public int getCount2() {
      return this.count2;
   }

   public S1FPacketSetExperience(float factor2, int count3, int count4) {
      this.factor = factor2;
      this.count2 = count3;
      this.count = count4;
   }

   public S1FPacketSetExperience() {
   }
}
