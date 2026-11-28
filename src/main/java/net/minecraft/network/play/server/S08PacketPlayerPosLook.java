package net.minecraft.network.play.server;

import java.util.EnumSet;
import java.io.IOException;
import java.util.Set;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S08PacketPlayerPosLook implements Packet {
   private double x;
   public double y;
   private double z;
   private float yaw;
   private float pitch;
   private float responseYOffset = 0.0F;
   private Set flags;

   public void setResponseYOffset(float f) {
      this.responseYOffset = f;
   }

   public void setX(double d) {
      this.x = d;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.x = buf.readDouble();
      this.y = buf.readDouble();
      this.z = buf.readDouble();
      this.yaw = buf.readFloat();
      this.pitch = buf.readFloat();
      this.flags = S08PacketPlayerPosLook.EnumFlags.unpack(buf.readUnsignedByte());
   }

   public void setPitch(float f) {
      this.pitch = f;
   }

   public void setYaw(float f) {
      this.yaw = f;
   }

   public double getX() {
      return this.x;
   }

   public void setY(double d) {
      this.y = d;
   }

   public Set getFlags() {
      return this.flags;
   }

   public S08PacketPlayerPosLook() {
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public float getPitch() {
      return this.pitch;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeDouble(this.x);
      buf.writeDouble(this.y);
      buf.writeDouble(this.z);
      buf.writeFloat(this.yaw);
      buf.writeFloat(this.pitch);
      buf.writeByte(S08PacketPlayerPosLook.EnumFlags.pack(this.flags));
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handlePlayerPosLook(this);
   }

   public double getZ() {
      return this.z;
   }

   public S08PacketPlayerPosLook(double d, double d2, double d3, float f, float f2, Set set) {
      this.x = d;
      this.y = d2;
      this.z = d3;
      this.yaw = f;
      this.pitch = f2;
      this.flags = set;
   }

   public float getYaw() {
      return this.yaw;
   }

   public void setZ(double d) {
      this.z = d;
   }

   public float getResponseYOffset() {
      return this.responseYOffset;
   }

   public double getY() {
      return this.y;
   }


   public enum EnumFlags {
      X(0),
      Y(1),
      Z(2),
      Y_ROT(3),
      X_ROT(4);

   private int bit;
   public static Set unpack(int n) {
         EnumSet enumSet = EnumSet.noneOf(EnumFlags.class);

         for (EnumFlags enumFlags : values()) {
            if (enumFlags.isSet(n)) {
               enumSet.add(enumFlags);
            }
         }

         return enumSet;
      }

      private boolean isSet(int n) {
         return (n & this.getMask()) == this.getMask();
      }

      public static int pack(Set set) {
         int n = 0;

         for (EnumFlags enumFlags : (Iterable<EnumFlags>)(set)) {
            n |= enumFlags.getMask();
         }

         return n;
      }

      private EnumFlags(int n) {
         this.bit = n;
      }

      private int getMask() {
         return 1 << this.bit;
      }
   }
}
