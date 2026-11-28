package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0BPacketEntityAction implements Packet {
   private int entityID;
   private C0BPacketEntityAction.Action action;
   private int auxData;

   public C0BPacketEntityAction.Action getAction() {
      return this.action;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayServer)handler);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityID = buf.readVarIntFromBuffer();
      this.action = (C0BPacketEntityAction.Action)buf.readEnumValue(C0BPacketEntityAction.Action.class);
      this.auxData = buf.readVarIntFromBuffer();
   }

   public C0BPacketEntityAction(Entity entity, C0BPacketEntityAction.Action action2) {
      this(entity, action2, 0);
   }

   public int getAuxData() {
      return this.auxData;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityID);
      buf.writeEnumValue(this.action);
      buf.writeVarIntToBuffer(this.auxData);
   }

   public C0BPacketEntityAction(Entity entity, C0BPacketEntityAction.Action action2, int n) {
      this.entityID = entity.getEntityId();
      this.action = action2;
      this.auxData = n;
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processEntityAction(this);
   }

   public C0BPacketEntityAction() {
   }


   public enum Action {
      START_SNEAKING,
      STOP_SNEAKING,
      STOP_SLEEPING,
      START_SPRINTING,
      STOP_SPRINTING,
      RIDING_JUMP,
      OPEN_INVENTORY;
   }
}
