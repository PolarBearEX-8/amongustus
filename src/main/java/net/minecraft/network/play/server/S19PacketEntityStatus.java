package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S19PacketEntityStatus implements Packet {
   private int entityId;
   private byte logicOpcode;

   public S19PacketEntityStatus(Entity entity, byte b) {
      this.entityId = entity.getEntityId();
      this.logicOpcode = b;
   }

   public int getEntityId() {
      return this.entityId;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readInt();
      this.logicOpcode = buf.readByte();
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S19PacketEntityStatus() {
   }

   public byte getOpCode() {
      return this.logicOpcode;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeInt(this.entityId);
      buf.writeByte(this.logicOpcode);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityStatus(this);
   }

   public Entity getEntity(World world) {
      return world.getEntityByID(this.entityId);
   }
}
