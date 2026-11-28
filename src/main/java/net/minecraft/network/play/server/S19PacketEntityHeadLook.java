package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S19PacketEntityHeadLook implements Packet {
   private int entityId;
   private byte yaw;

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityId);
      buf.writeByte(this.yaw);
   }

   public int getEntityId() {
      return this.entityId;
   }

   public Entity getEntity(World world) {
      return world.getEntityByID(this.entityId);
   }

   public void setEntityId(int n) {
      this.entityId = n;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readVarIntFromBuffer();
      this.yaw = buf.readByte();
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S19PacketEntityHeadLook(Entity entity, byte b) {
      this.entityId = entity.getEntityId();
      this.yaw = b;
   }

   public byte getYaw() {
      return this.yaw;
   }

   public S19PacketEntityHeadLook() {
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityHeadLook(this);
   }
}
