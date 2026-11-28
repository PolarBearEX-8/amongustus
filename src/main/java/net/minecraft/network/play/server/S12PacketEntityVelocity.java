package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S12PacketEntityVelocity implements Packet {
   private int entityID;
   private int motionX;
   private int motionY;
   private int motionZ;
   private boolean applyHorizontalVelocity = true;
   private boolean applyVerticalVelocity = true;

   public boolean isApplyVerticalVelocity() {
      return this.applyVerticalVelocity;
   }

   public int getMotionX() {
      return this.motionX;
   }

   public int getMotionZ() {
      return this.motionZ;
   }

   public void setMotionY(int n) {
      this.motionY = n;
   }

   public int getMotionY() {
      return this.motionY;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityVelocity(this);
   }

   public boolean isApplyHorizontalVelocity() {
      return this.applyHorizontalVelocity;
   }

   public S12PacketEntityVelocity() {
   }

   public int getEntityID() {
      return this.entityID;
   }

   public S12PacketEntityVelocity(int n, double d, double d2, double d3) {
      this.entityID = n;
      double d4 = 3.9;
      if (d < -d4) {
         d = -d4;
      }

      if (d2 < -d4) {
         d2 = -d4;
      }

      if (d3 < -d4) {
         d3 = -d4;
      }

      if (d > d4) {
         d = d4;
      }

      if (d2 > d4) {
         d2 = d4;
      }

      if (d3 > d4) {
         d3 = d4;
      }

      this.motionX = (int)(d * 8000.0);
      this.motionY = (int)(d2 * 8000.0);
      this.motionZ = (int)(d3 * 8000.0);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S12PacketEntityVelocity(Entity entity) {
      this(entity.getEntityId(), entity.motionX, entity.motionY, entity.motionZ);
   }

   public void setMotionX(int n) {
      this.motionX = n;
   }

   public void setMotionZ(int n) {
      this.motionZ = n;
   }

   public void setApplyHorizontalVelocity(boolean flag) {
      this.applyHorizontalVelocity = flag;
   }

   public void setApplyVerticalVelocity(boolean flag) {
      this.applyVerticalVelocity = flag;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityID);
      buf.writeShort(this.motionX);
      buf.writeShort(this.motionY);
      buf.writeShort(this.motionZ);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityID = buf.readVarIntFromBuffer();
      this.motionX = buf.readShort();
      this.motionY = buf.readShort();
      this.motionZ = buf.readShort();
   }
}
