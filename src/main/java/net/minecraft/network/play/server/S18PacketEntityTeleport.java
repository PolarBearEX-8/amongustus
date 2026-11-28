package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S18PacketEntityTeleport implements Packet {
   private int entityId;
   private int posX;
   private int posY;
   private int posZ;
   private byte yaw;
   private byte pitch;
   private boolean onGround;

   public int getEntityId() {
      return this.entityId;
   }

   public void setPosY(int n) {
      this.posY = n;
   }

   public S18PacketEntityTeleport() {
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public void setPosX(int n) {
      this.posX = n;
   }

   public byte getYaw() {
      return this.yaw;
   }

   public boolean getOnGround() {
      return this.onGround;
   }

   public S18PacketEntityTeleport(Entity entity) {
      this.entityId = entity.getEntityId();
      this.posX = MathHelper.floor_double(entity.posX * 32.0);
      this.posY = MathHelper.floor_double(entity.posY * 32.0);
      this.posZ = MathHelper.floor_double(entity.posZ * 32.0);
      this.yaw = (byte)((int)(entity.rotationYaw * 256.0F / 360.0F));
      this.pitch = (byte)((int)(entity.rotationPitch * 256.0F / 360.0F));
      this.onGround = entity.onGround;
   }

   public int getX() {
      return this.posX;
   }

   public int getZ() {
      return this.posZ;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityTeleport(this);
   }

   public void setPosZ(int n) {
      this.posZ = n;
   }

   public byte getPitch() {
      return this.pitch;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readVarIntFromBuffer();
      this.posX = buf.readInt();
      this.posY = buf.readInt();
      this.posZ = buf.readInt();
      this.yaw = buf.readByte();
      this.pitch = buf.readByte();
      this.onGround = buf.readBoolean();
   }

   public S18PacketEntityTeleport(int n, int n2, int n3, int n4, byte b, byte b2, boolean flag) {
      this.entityId = n;
      this.posX = n2;
      this.posY = n3;
      this.posZ = n4;
      this.yaw = b;
      this.pitch = b2;
      this.onGround = flag;
   }

   public int getY() {
      return this.posY;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityId);
      buf.writeInt(this.posX);
      buf.writeInt(this.posY);
      buf.writeInt(this.posZ);
      buf.writeByte(this.yaw);
      buf.writeByte(this.pitch);
      buf.writeBoolean(this.onGround);
   }

   public void setEntityId(int n) {
      this.entityId = n;
   }
}
