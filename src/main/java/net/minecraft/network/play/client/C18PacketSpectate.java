package net.minecraft.network.play.client;

import java.io.IOException;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.world.WorldServer;

public class C18PacketSpectate implements Packet {
   private UUID id;

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeUuid(this.id);
   }

   public Entity getEntity(WorldServer world) {
      return world.getEntityFromUuid(this.id);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   public C18PacketSpectate() {
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.id = buf.readUuid();
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.handleSpectate(this);
   }

   public C18PacketSpectate(UUID uuid) {
      this.id = uuid;
   }
}
