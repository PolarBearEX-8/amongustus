package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.Entity;

public class S1BPacketEntityAttach implements Packet {
   public int count3;
   public int count2;
   public int count;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count2 = packet.readInt();
      this.count = packet.readInt();
      this.count3 = packet.readUnsignedByte();
   }

   public int getCount2() {
      return this.count2;
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public int getCount() {
      return this.count;
   }

   public int getCount3() {
      return this.count3;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeInt(this.count2);
      packet.writeInt(this.count);
      packet.writeByte(this.count3);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS1BPacketEntityAttach(this);
   }

   public S1BPacketEntityAttach() {
   }

   public S1BPacketEntityAttach(int count4, Entity entity, Entity entity2) {
      this.count3 = count4;
      this.count2 = entity.getEntityId();
      this.count = entity2 != null ? entity2.getEntityId() : -1;
   }
}
