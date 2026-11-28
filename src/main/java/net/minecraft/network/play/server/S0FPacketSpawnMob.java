package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.List;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S0FPacketSpawnMob implements Packet {
   private int entityId;
   private int type;
   private int x;
   private int y;
   private int z;
   private int velocityX;
   private int velocityY;
   private int velocityZ;
   private byte yaw;
   private byte pitch;
   private byte headPitch;
   private DataWatcher dataWatcher;
   private List watcher;

   public int getEntityID() {
      return this.entityId;
   }

   public S0FPacketSpawnMob() {
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityId);
      buf.writeByte(this.type & 0xFF);
      buf.writeInt(this.x);
      buf.writeInt(this.y);
      buf.writeInt(this.z);
      buf.writeByte(this.yaw);
      buf.writeByte(this.pitch);
      buf.writeByte(this.headPitch);
      buf.writeShort(this.velocityX);
      buf.writeShort(this.velocityY);
      buf.writeShort(this.velocityZ);
      this.dataWatcher.writeTo(buf);
   }

   public byte getPitch() {
      return this.pitch;
   }

   public int getVelocityY() {
      return this.velocityY;
   }

   public int getVelocityZ() {
      return this.velocityZ;
   }

   public int getVelocityX() {
      return this.velocityX;
   }

   public int getEntityType() {
      return this.type;
   }

   public byte getHeadPitch() {
      return this.headPitch;
   }

   public S0FPacketSpawnMob(EntityLivingBase entity) {
      this.entityId = entity.getEntityId();
      this.type = (byte)EntityList.getEntityID(entity);
      this.x = MathHelper.floor_double(entity.posX * 32.0);
      this.y = MathHelper.floor_double(entity.posY * 32.0);
      this.z = MathHelper.floor_double(entity.posZ * 32.0);
      this.yaw = (byte)((int)(entity.rotationYaw * 256.0F / 360.0F));
      this.pitch = (byte)((int)(entity.rotationPitch * 256.0F / 360.0F));
      this.headPitch = (byte)((int)(entity.rotationYawHead * 256.0F / 360.0F));
      double d = 3.9;
      double motionX = entity.motionX;
      double motionY = entity.motionY;
      double motionZ = entity.motionZ;
      if (motionX < -d) {
         motionX = -d;
      }

      if (motionY < -d) {
         motionY = -d;
      }

      if (motionZ < -d) {
         motionZ = -d;
      }

      if (motionX > d) {
         motionX = d;
      }

      if (motionY > d) {
         motionY = d;
      }

      if (motionZ > d) {
         motionZ = d;
      }

      this.velocityX = (int)(motionX * 8000.0);
      this.velocityY = (int)(motionY * 8000.0);
      this.velocityZ = (int)(motionZ * 8000.0);
      this.dataWatcher = entity.getDataWatcher();
   }

   public int getZ() {
      return this.z;
   }

   public byte getYaw() {
      return this.yaw;
   }

   public List getDataManagerEntries() {
      if (this.watcher == null) {
         this.watcher = this.dataWatcher.getAllWatched();
      }

      return this.watcher;
   }

   public int getY() {
      return this.y;
   }

   public int getX() {
      return this.x;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readVarIntFromBuffer();
      this.type = buf.readByte() & 255;
      this.x = buf.readInt();
      this.y = buf.readInt();
      this.z = buf.readInt();
      this.yaw = buf.readByte();
      this.pitch = buf.readByte();
      this.headPitch = buf.readByte();
      this.velocityX = buf.readShort();
      this.velocityY = buf.readShort();
      this.velocityZ = buf.readShort();
      this.watcher = DataWatcher.readWatchedListFromPacketBuffer(buf);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleSpawnMob(this);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }
}
