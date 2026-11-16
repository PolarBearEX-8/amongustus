package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.Entity;

public class S0BPacketAnimation implements Packet {
   public int count;
   public int count2;

   public int getIndex() {
      return this.count;
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS0BPacketAnimation(this);
   }

   public int getIndex2() {
      return this.count2;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.getInt3();
      this.count2 = packet.readUnsignedByte();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count);
      packet.writeByte(this.count2);
   }

   public S0BPacketAnimation(Entity entity, int count3) {
      this.count = entity.getEntityId();
      this.count2 = count3;
   }

   public S0BPacketAnimation() {
   }
}
