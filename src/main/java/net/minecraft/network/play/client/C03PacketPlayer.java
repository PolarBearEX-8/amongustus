package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C03PacketPlayer implements Packet {
   public double positionX;
   public double positionY;
   public double positionZ;
   public float yaw;
   public float pitch;
   public boolean onGround;
   public boolean moving;
   public boolean rotating;

   public double getPositionX() {
      return this.positionX;
   }

   public void setPositionX(double d) {
      this.positionX = d;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.onGround ? 1 : 0);
   }

   public void setRotating(boolean flag) {
      this.rotating = flag;
   }

   public void setMoving(boolean flag) {
      this.moving = flag;
   }

   public void setPositionZ(double d) {
      this.positionZ = d;
   }

   public double getPositionY() {
      return this.positionY;
   }

   public boolean isMoving() {
      return this.moving;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.onGround = buf.readUnsignedByte() != 0;
   }

   public void setOnGround(boolean flag) {
      this.onGround = flag;
   }

   public boolean isOnGround() {
      return this.onGround;
   }

   public boolean getRotating() {
      return this.rotating;
   }

   public float getYaw() {
      return this.yaw;
   }

   public void setPositionY(double d) {
      this.positionY = d;
   }

   public float getPitch() {
      return this.pitch;
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processPlayer(this);
   }

   public C03PacketPlayer(boolean flag) {
      this.onGround = flag;
   }

   public C03PacketPlayer() {
   }

   public double getPositionZ() {
      return this.positionZ;
   }


   public static class C05PacketPlayerLook extends C03PacketPlayer {
      public C05PacketPlayerLook(float f, float f2, boolean flag) {
         this.yaw = f;
         this.pitch = f2;
         this.onGround = flag;
         this.rotating = true;
      }

      @Override
      public void processPacket(INetHandler handler) {
         super.processPacket((INetHandlerPlayServer)handler);
      }

      @Override
      public void writePacketData(PacketBuffer buf) throws IOException {
         buf.writeFloat(this.yaw);
         buf.writeFloat(this.pitch);
         super.writePacketData(buf);
      }

      @Override
      public void readPacketData(PacketBuffer buf) throws IOException {
         this.yaw = buf.readFloat();
         this.pitch = buf.readFloat();
         super.readPacketData(buf);
      }

      public C05PacketPlayerLook() {
         this.rotating = true;
      }
   }

   public static class C06PacketPlayerPosLook extends C03PacketPlayer {
      @Override
      public void readPacketData(PacketBuffer buf) throws IOException {
         this.positionX = buf.readDouble();
         this.positionY = buf.readDouble();
         this.positionZ = buf.readDouble();
         this.yaw = buf.readFloat();
         this.pitch = buf.readFloat();
         super.readPacketData(buf);
      }

      public C06PacketPlayerPosLook() {
         this.moving = true;
         this.rotating = true;
      }

      @Override
      public void writePacketData(PacketBuffer buf) throws IOException {
         buf.writeDouble(this.positionX);
         buf.writeDouble(this.positionY);
         buf.writeDouble(this.positionZ);
         buf.writeFloat(this.yaw);
         buf.writeFloat(this.pitch);
         super.writePacketData(buf);
      }

      @Override
      public void processPacket(INetHandler handler) {
         super.processPacket((INetHandlerPlayServer)handler);
      }

      public C06PacketPlayerPosLook(double d, double d2, double d3, float f, float f2, boolean flag) {
         this.positionX = d;
         this.positionY = d2;
         this.positionZ = d3;
         this.yaw = f;
         this.pitch = f2;
         this.onGround = flag;
         this.rotating = true;
         this.moving = true;
      }
   }

   public static class C04PacketPlayerPosition extends C03PacketPlayer {
      public C04PacketPlayerPosition() {
         this.moving = true;
      }

      @Override
      public void writePacketData(PacketBuffer buf) throws IOException {
         buf.writeDouble(this.positionX);
         buf.writeDouble(this.positionY);
         buf.writeDouble(this.positionZ);
         super.writePacketData(buf);
      }

      @Override
      public void readPacketData(PacketBuffer buf) throws IOException {
         this.positionX = buf.readDouble();
         this.positionY = buf.readDouble();
         this.positionZ = buf.readDouble();
         super.readPacketData(buf);
      }

      @Override
      public void processPacket(INetHandler handler) {
         super.processPacket((INetHandlerPlayServer)handler);
      }

      public C04PacketPlayerPosition(double d, double d2, double d3, boolean flag) {
         this.positionX = d;
         this.positionY = d2;
         this.positionZ = d3;
         this.onGround = flag;
         this.moving = true;
      }
   }
}
