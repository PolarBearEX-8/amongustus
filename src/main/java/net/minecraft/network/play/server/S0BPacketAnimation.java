package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S0BPacketAnimation implements Packet {
   private int entityId;
   private int type;

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleAnimation(this);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readVarIntFromBuffer();
      this.type = buf.readUnsignedByte();
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityId);
      buf.writeByte(this.type);
   }

   public int getAnimationType() {
      return this.type;
   }

   public int getEntityID() {
      return this.entityId;
   }

   public S0BPacketAnimation(Entity entity, int n) {
      this.entityId = entity.getEntityId();
      this.type = n;
   }

   public S0BPacketAnimation() {
   }
}
