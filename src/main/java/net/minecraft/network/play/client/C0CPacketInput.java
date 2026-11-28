package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0CPacketInput implements Packet {
   private float strafeSpeed;
   private float forwardSpeed;
   private boolean jumping;
   private boolean sneaking;

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.strafeSpeed = buf.readFloat();
      this.forwardSpeed = buf.readFloat();
      byte b = buf.readByte();
      this.jumping = (b & 1) > 0;
      this.sneaking = (b & 2) > 0;
   }

   public boolean isSneaking() {
      return this.sneaking;
   }

   public boolean isJumping() {
      return this.jumping;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeFloat(this.strafeSpeed);
      buf.writeFloat(this.forwardSpeed);
      byte b = 0;
      if (this.jumping) {
         b = (byte)(b | 1);
      }

      if (this.sneaking) {
         b = (byte)(b | 2);
      }

      buf.writeByte(b);
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processInput(this);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   public C0CPacketInput(float f, float f2, boolean flag, boolean flag2) {
      this.strafeSpeed = f;
      this.forwardSpeed = f2;
      this.jumping = flag;
      this.sneaking = flag2;
   }

   public C0CPacketInput() {
   }

   public float getForwardSpeed() {
      return this.forwardSpeed;
   }

   public float getStrafeSpeed() {
      return this.strafeSpeed;
   }
}
