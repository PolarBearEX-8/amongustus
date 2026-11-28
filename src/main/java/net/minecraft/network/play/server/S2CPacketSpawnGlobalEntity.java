package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S2CPacketSpawnGlobalEntity implements Packet {
   private int entityId;
   private int x;
   private int y;
   private int z;
   private int type;

   public int getX() {
      return this.x;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readVarIntFromBuffer();
      this.type = buf.readByte();
      this.x = buf.readInt();
      this.y = buf.readInt();
      this.z = buf.readInt();
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityId);
      buf.writeByte(this.type);
      buf.writeInt(this.x);
      buf.writeInt(this.y);
      buf.writeInt(this.z);
   }

   public S2CPacketSpawnGlobalEntity() {
   }

   public int getEntityId() {
      return this.entityId;
   }

   public int getZ() {
      return this.z;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleSpawnGlobalEntity(this);
   }

   public int getType() {
      return this.type;
   }

   public int getY() {
      return this.y;
   }

   public S2CPacketSpawnGlobalEntity(Entity entity) {
      this.entityId = entity.getEntityId();
      this.x = MathHelper.floor_double(entity.posX * 32.0);
      this.y = MathHelper.floor_double(entity.posY * 32.0);
      this.z = MathHelper.floor_double(entity.posZ * 32.0);
      if (entity instanceof EntityLightningBolt) {
         this.type = 1;
      }
   }
}
