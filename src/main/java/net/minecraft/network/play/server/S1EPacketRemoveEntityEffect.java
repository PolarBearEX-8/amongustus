package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.potion.PotionEffect;

public class S1EPacketRemoveEntityEffect implements Packet {
   private int entityId;
   private int effectId;

   public int getEffectId() {
      return this.effectId;
   }

   public S1EPacketRemoveEntityEffect(int n, PotionEffect potionEffect) {
      this.entityId = n;
      this.effectId = potionEffect.getPotionID();
   }

   public S1EPacketRemoveEntityEffect() {
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleRemoveEntityEffect(this);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityId);
      buf.writeByte(this.effectId);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readVarIntFromBuffer();
      this.effectId = buf.readUnsignedByte();
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public int getEntityId() {
      return this.entityId;
   }
}
