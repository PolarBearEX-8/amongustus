package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S14PacketEntity implements Packet {
   public int entityId;
   public byte posX;
   public byte posY;
   public byte posZ;
   public byte yaw;
   public byte pitch;
   public boolean onGround;
   public boolean rotating;

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityMovement(this);
   }

   public boolean getOnGround() {
      return this.onGround;
   }

   public byte getPosX() {
      return this.posX;
   }

   public byte getPosZ() {
      return this.posZ;
   }

   public S14PacketEntity() {
   }

   public void setPosZ(int n) {
      this.posZ = (byte)n;
   }

   @Override
   public String toString() {
      return "Entity_" + super.toString();
   }

   public byte getYaw() {
      return this.yaw;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public void setEntityId(int n) {
      this.entityId = n;
   }

   public void setPosX(int n) {
      this.posX = (byte)n;
   }

   public void setPosY(int n) {
      this.posY = (byte)n;
   }

   public Entity getEntity(World world) {
      return world.getEntityByID(this.entityId);
   }

   public byte getPosY() {
      return this.posY;
   }

   public boolean isRotating() {
      return this.rotating;
   }

   public byte getPitch() {
      return this.pitch;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readVarIntFromBuffer();
   }

   public S14PacketEntity(int n) {
      this.entityId = n;
   }

   public int getEntityId() {
      return this.entityId;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityId);
   }


   public static class S16PacketEntityLook extends S14PacketEntity {
      public S16PacketEntityLook(int n, byte b, byte b2, boolean flag) {
         super(n);
         this.yaw = b;
         this.pitch = b2;
         this.rotating = true;
         this.onGround = flag;
      }

      public S16PacketEntityLook() {
         this.rotating = true;
      }

      @Override
      public void processPacket(INetHandler handler) {
         super.processPacket((INetHandlerPlayClient)handler);
      }

      @Override
      public void readPacketData(PacketBuffer buf) throws IOException {
         super.readPacketData(buf);
         this.yaw = buf.readByte();
         this.pitch = buf.readByte();
         this.onGround = buf.readBoolean();
      }

      @Override
      public void writePacketData(PacketBuffer buf) throws IOException {
         super.writePacketData(buf);
         buf.writeByte(this.yaw);
         buf.writeByte(this.pitch);
         buf.writeBoolean(this.onGround);
      }
   }

   public static class S15PacketEntityRelMove extends S14PacketEntity {
      @Override
      public void readPacketData(PacketBuffer buf) throws IOException {
         super.readPacketData(buf);
         this.posX = buf.readByte();
         this.posY = buf.readByte();
         this.posZ = buf.readByte();
         this.onGround = buf.readBoolean();
      }

      @Override
      public void processPacket(INetHandler handler) {
         super.processPacket((INetHandlerPlayClient)handler);
      }

      public S15PacketEntityRelMove() {
      }

      @Override
      public void writePacketData(PacketBuffer buf) throws IOException {
         super.writePacketData(buf);
         buf.writeByte(this.posX);
         buf.writeByte(this.posY);
         buf.writeByte(this.posZ);
         buf.writeBoolean(this.onGround);
      }

      public S15PacketEntityRelMove(int n, byte b, byte b2, byte b3, boolean flag) {
         super(n);
         this.posX = b;
         this.posY = b2;
         this.posZ = b3;
         this.onGround = flag;
      }
   }

   public static class S17PacketEntityLookMove extends S14PacketEntity {
      public S17PacketEntityLookMove(int n, byte b, byte b2, byte b3, byte b4, byte b5, boolean flag) {
         super(n);
         this.posX = b;
         this.posY = b2;
         this.posZ = b3;
         this.yaw = b4;
         this.pitch = b5;
         this.onGround = flag;
         this.rotating = true;
      }

      @Override
      public void writePacketData(PacketBuffer buf) throws IOException {
         super.writePacketData(buf);
         buf.writeByte(this.posX);
         buf.writeByte(this.posY);
         buf.writeByte(this.posZ);
         buf.writeByte(this.yaw);
         buf.writeByte(this.pitch);
         buf.writeBoolean(this.onGround);
      }

      @Override
      public void readPacketData(PacketBuffer buf) throws IOException {
         super.readPacketData(buf);
         this.posX = buf.readByte();
         this.posY = buf.readByte();
         this.posZ = buf.readByte();
         this.yaw = buf.readByte();
         this.pitch = buf.readByte();
         this.onGround = buf.readBoolean();
      }

      @Override
      public void processPacket(INetHandler handler) {
         super.processPacket((INetHandlerPlayClient)handler);
      }

      public S17PacketEntityLookMove() {
         this.rotating = true;
      }
   }
}
