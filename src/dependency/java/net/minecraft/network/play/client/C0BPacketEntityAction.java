package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerAction;

public class C0BPacketEntityAction implements Packet {
   public int count2;
   public EntityPlayerAction entityPlayerAction;
   public int count;

   public int getCount() {
      return this.count;
   }

   public EntityPlayerAction getEntityPlayerAction() {
      return this.entityPlayerAction;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.count2 = packet.getInt3();
      this.entityPlayerAction = (EntityPlayerAction)packet.getEnum(EntityPlayerAction.class);
      this.count = packet.getInt3();
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processPacket((INetHandlerPlayServer)iNetHandler);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.count2);
      packet.applyEnum(this.entityPlayerAction);
      packet.updateInt(this.count);
   }

   public void processPacket(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.applyC0BPacketEntityAction(this);
   }

   public C0BPacketEntityAction() {
   }

   public C0BPacketEntityAction(Entity entity, EntityPlayerAction entityPlayerAction2) {
      this(entity, entityPlayerAction2, 0);
   }

   public C0BPacketEntityAction(Entity entity, EntityPlayerAction entityPlayerAction2, int count3) {
      this.count2 = entity.getEntityId();
      this.entityPlayerAction = entityPlayerAction2;
      this.count = count3;
   }
}
