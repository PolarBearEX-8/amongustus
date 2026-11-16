package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class S19PacketEntityStatus implements Packet {
   public int count;
   public byte byteFlag;

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS19PacketEntityStatus(this);
   }

   public byte processValue() {
      return this.byteFlag;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.readInt();
      this.byteFlag = packet.readByte();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public Entity getEntity(World world) {
      return world.getEntityByID(this.count);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.writeInt(this.count);
      packet.writeByte(this.byteFlag);
   }

   public int getIndex() {
      return this.count;
   }

   public S19PacketEntityStatus() {
   }

   public S19PacketEntityStatus(Entity entity, byte byteValue) {
      this.count = entity.getEntityId();
      this.byteFlag = byteValue;
   }
}
