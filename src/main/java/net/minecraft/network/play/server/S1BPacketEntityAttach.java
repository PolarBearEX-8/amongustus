package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S1BPacketEntityAttach implements Packet {
   private int leash;
   private int entityId;
   private int vehicleEntityId;

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityAttach(this);
   }

   public S1BPacketEntityAttach() {
   }

   public int getVehicleEntityId() {
      return this.vehicleEntityId;
   }

   public S1BPacketEntityAttach(int n, Entity entity, Entity entity2) {
      this.leash = n;
      this.entityId = entity.getEntityId();
      this.vehicleEntityId = entity2 != null ? entity2.getEntityId() : -1;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeInt(this.entityId);
      buf.writeInt(this.vehicleEntityId);
      buf.writeByte(this.leash);
   }

   public int getLeash() {
      return this.leash;
   }

   public int getEntityId() {
      return this.entityId;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readInt();
      this.vehicleEntityId = buf.readInt();
      this.leash = buf.readUnsignedByte();
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }
}
