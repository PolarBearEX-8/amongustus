package net.minecraft.network.play.client;

import client.util.INetHandler;
import client.util.INetHandlerPlayServer;
import client.util.Packet;
import client.util.PacketBuffer;
import client.util.UseEntityAction;
import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class C02PacketUseEntity implements Packet {
   public int entityId;
   public UseEntityAction useEntityAction;
   public Vec3 positionVec;

   public int getEntityId() {
      return this.entityId;
   }

   @Override
   public void processAction(PacketBuffer packet) throws IOException {
      this.entityId = packet.getInt3();
      this.useEntityAction = (UseEntityAction)packet.getEnum(UseEntityAction.class);
      if (this.useEntityAction == UseEntityAction.INTERACT_AT) {
         this.positionVec = new Vec3((double)packet.readFloat(), (double)packet.readFloat(), (double)packet.readFloat());
      }
   }

   @Override
   public void processAction(INetHandler iNetHandler) {
      this.processAction((INetHandlerPlayServer)iNetHandler);
   }

   public Vec3 getPositionVec() {
      return this.positionVec;
   }

   public Entity processAction(World world) {
      return world.getEntityByID(this.entityId);
   }

   @Override
   public void processAction2(PacketBuffer packet) throws IOException {
      packet.updateInt(this.entityId);
      packet.applyEnum(this.useEntityAction);
      if (this.useEntityAction == UseEntityAction.INTERACT_AT) {
         packet.writeFloat((float)this.positionVec.xCoord);
         packet.writeFloat((float)this.positionVec.yCoord);
         packet.writeFloat((float)this.positionVec.zCoord);
      }
   }

   public UseEntityAction getUseEntityAction() {
      return this.useEntityAction;
   }

   public void processAction(INetHandlerPlayServer iNetHandlerPlayServer) {
      iNetHandlerPlayServer.handleC02PacketUseEntity(this);
   }

   public C02PacketUseEntity() {
   }

   public C02PacketUseEntity(Entity entity, UseEntityAction useEntityAction2) {
      this.entityId = entity.getEntityId();
      this.useEntityAction = useEntityAction2;
   }

   public C02PacketUseEntity(Entity entity, Vec3 vec) {
      this(entity, UseEntityAction.INTERACT_AT);
      this.positionVec = vec;
   }
}
