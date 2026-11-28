package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S0EPacketSpawnObject implements Packet {
   private int entityId;
   private int x;
   private int y;
   private int z;
   private int speedX;
   private int speedY;
   private int speedZ;
   private int pitch;
   private int yaw;
   private int type;
   private int data;

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleSpawnObject(this);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public int getData() {
      return this.data;
   }

   public void setData(int n) {
      this.data = n;
   }

   public S0EPacketSpawnObject(Entity entity, int n) {
      this(entity, n, 0);
   }

   public int getType() {
      return this.type;
   }

   public void setZ(int n) {
      this.z = n;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityId);
      buf.writeByte(this.type);
      buf.writeInt(this.x);
      buf.writeInt(this.y);
      buf.writeInt(this.z);
      buf.writeByte(this.pitch);
      buf.writeByte(this.yaw);
      buf.writeInt(this.data);
      if (this.data > 0) {
         buf.writeShort(this.speedX);
         buf.writeShort(this.speedY);
         buf.writeShort(this.speedZ);
      }
   }

   public int getSpeedZ() {
      return this.speedZ;
   }

   public int getY() {
      return this.y;
   }

   public int getSpeedX() {
      return this.speedX;
   }

   public int getZ() {
      return this.z;
   }

   public int getSpeedY() {
      return this.speedY;
   }

   public int getEntityID() {
      return this.entityId;
   }

   public void setY(int n) {
      this.y = n;
   }

   public int getX() {
      return this.x;
   }

   public void setSpeedZ(int n) {
      this.speedZ = n;
   }

   public int getYaw() {
      return this.yaw;
   }

   public S0EPacketSpawnObject(Entity entity, int n, int n2) {
      this.entityId = entity.getEntityId();
      this.x = MathHelper.floor_double(entity.posX * 32.0);
      this.y = MathHelper.floor_double(entity.posY * 32.0);
      this.z = MathHelper.floor_double(entity.posZ * 32.0);
      this.pitch = MathHelper.floor_float(entity.rotationPitch * 256.0F / 360.0F);
      this.yaw = MathHelper.floor_float(entity.rotationYaw * 256.0F / 360.0F);
      this.type = n;
      this.data = n2;
      if (n2 > 0) {
         double motionX = entity.motionX;
         double motionY = entity.motionY;
         double motionZ = entity.motionZ;
         double d = 3.9;
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

         this.speedX = (int)(motionX * 8000.0);
         this.speedY = (int)(motionY * 8000.0);
         this.speedZ = (int)(motionZ * 8000.0);
      }
   }

   public S0EPacketSpawnObject() {
   }

   public void setSpeedX(int n) {
      this.speedX = n;
   }

   public int getPitch() {
      return this.pitch;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readVarIntFromBuffer();
      this.type = buf.readByte();
      this.x = buf.readInt();
      this.y = buf.readInt();
      this.z = buf.readInt();
      this.pitch = buf.readByte();
      this.yaw = buf.readByte();
      this.data = buf.readInt();
      if (this.data > 0) {
         this.speedX = buf.readShort();
         this.speedY = buf.readShort();
         this.speedZ = buf.readShort();
      }
   }

   public void setX(int n) {
      this.x = n;
   }

   public void setSpeedY(int n) {
      this.speedY = n;
   }
}
