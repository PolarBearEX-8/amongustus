package net.minecraft.network.play.server;

import client.util.INetHandler;
import client.util.INetHandlerPlayClient;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class S43PacketCamera implements Packet {
   public int count;

   public Entity resolveEntity(World world) {
      return world.getEntityByID(this.count);
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count = packet.getInt3();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayClient)iNetHandler);
   }

   public void processPacket(INetHandlerPlayClient iNetHandlerPlayClient) {
      iNetHandlerPlayClient.applyS43PacketCamera(this);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count);
   }

   public S43PacketCamera() {
   }

   public S43PacketCamera(Entity entity) {
      this.count = entity.getEntityId();
   }
}
