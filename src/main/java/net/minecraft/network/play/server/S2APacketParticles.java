package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.EnumParticleTypes;

public class S2APacketParticles implements Packet {
   private EnumParticleTypes particleType;
   private float xCoord;
   private float yCoord;
   private float zCoord;
   private float xOffset;
   private float yOffset;
   private float zOffset;
   private float particleSpeed;
   private int particleCount;
   private boolean longDistance;
   private int[] particleArguments;

   public float getYOffset() {
      return this.yOffset;
   }

   public float getXOffset() {
      return this.xOffset;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeInt(this.particleType.getParticleID());
      buf.writeBoolean(this.longDistance);
      buf.writeFloat(this.xCoord);
      buf.writeFloat(this.yCoord);
      buf.writeFloat(this.zCoord);
      buf.writeFloat(this.xOffset);
      buf.writeFloat(this.yOffset);
      buf.writeFloat(this.zOffset);
      buf.writeFloat(this.particleSpeed);
      buf.writeInt(this.particleCount);
      int argumentCount = this.particleType.getArgumentCount();

      for (int i = 0; i < argumentCount; i++) {
         buf.writeVarIntToBuffer(this.particleArguments[i]);
      }
   }

   public float getZOffset() {
      return this.zOffset;
   }

   public float getParticleSpeed() {
      return this.particleSpeed;
   }

   public S2APacketParticles() {
   }

   public int[] getParticleArgs() {
      return this.particleArguments;
   }

   public int getParticleCount() {
      return this.particleCount;
   }

   public double getXCoordinate() {
      return (double)this.xCoord;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public double getYCoordinate() {
      return (double)this.yCoord;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleParticles(this);
   }

   public boolean isLongDistance() {
      return this.longDistance;
   }

   public S2APacketParticles(
      EnumParticleTypes enumParticleTypes, boolean flag, float f, float f2, float f3, float f4, float f5, float f6, float f7, int n, int[] ints
   ) {
      this.particleType = enumParticleTypes;
      this.longDistance = flag;
      this.xCoord = f;
      this.yCoord = f2;
      this.zCoord = f3;
      this.xOffset = f4;
      this.yOffset = f5;
      this.zOffset = f6;
      this.particleSpeed = f7;
      this.particleCount = n;
      this.particleArguments = ints;
   }

   public EnumParticleTypes getParticleType() {
      return this.particleType;
   }

   public double getZCoordinate() {
      return (double)this.zCoord;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.particleType = EnumParticleTypes.getParticleFromId(buf.readInt());
      if (this.particleType == null) {
         this.particleType = EnumParticleTypes.BARRIER;
      }

      this.longDistance = buf.readBoolean();
      this.xCoord = buf.readFloat();
      this.yCoord = buf.readFloat();
      this.zCoord = buf.readFloat();
      this.xOffset = buf.readFloat();
      this.yOffset = buf.readFloat();
      this.zOffset = buf.readFloat();
      this.particleSpeed = buf.readFloat();
      this.particleCount = buf.readInt();
      int argumentCount = this.particleType.getArgumentCount();
      this.particleArguments = new int[argumentCount];

      for (int i = 0; i < argumentCount; i++) {
         this.particleArguments[i] = buf.readVarIntFromBuffer();
      }
   }
}
