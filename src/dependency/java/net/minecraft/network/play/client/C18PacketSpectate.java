package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.world.WorldServer;

public class C18PacketSpectate implements Packet {
   public UUID uUID;

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.uUID = packet.getUUID();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   public Entity resolveEntity(WorldServer worldServer) {
      return worldServer.get(this.uUID);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.applyUUID(this.uUID);
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC18PacketSpectate(this);
   }

   public C18PacketSpectate(UUID uuid) {
      this.uUID = uuid;
   }

   public C18PacketSpectate() {
   }
}
