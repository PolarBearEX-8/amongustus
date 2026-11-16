package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class S19PacketEntityHeadLook implements Packet {
   public int count;
   public byte byteFlag;

   public Entity resolveEntity(World world) {
      return world.getEntityByID(this.count);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS19PacketEntityHeadLook(this);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.getInt3();
      this.byteFlag = packet.readByte();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public byte getByteFlag() {
      return this.byteFlag;
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count);
      packet.writeByte(this.byteFlag);
   }

   public int getCount() {
      return this.count;
   }

   public void setCount(int count2) {
      this.count = count2;
   }

   public S19PacketEntityHeadLook() {
   }

   public S19PacketEntityHeadLook(Entity entity, byte byteValue) {
      this.count = entity.getEntityId();
      this.byteFlag = byteValue;
   }
}
