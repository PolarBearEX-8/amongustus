package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.potion.PotionEffect;

public class S1DPacketEntityEffect implements Packet {
   private int entityId;
   private byte effectId;
   private byte amplifier;
   private int duration;
   private byte hideParticles;

   public boolean doesShowParticles() {
      return this.hideParticles != 0;
   }

   public byte getAmplifier() {
      return this.amplifier;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityEffect(this);
   }

   public int getEntityId() {
      return this.entityId;
   }

   public S1DPacketEntityEffect(int n, PotionEffect potionEffect) {
      this.entityId = n;
      this.effectId = (byte)(potionEffect.getPotionID() & 0xFF);
      this.amplifier = (byte)(potionEffect.getAmplifier() & 0xFF);
      if (potionEffect.getDuration() > 32767) {
         this.duration = 32767;
      } else {
         this.duration = potionEffect.getDuration();
      }

      this.hideParticles = (byte)(potionEffect.getIsShowParticles() ? 1 : 0);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityId);
      buf.writeByte(this.effectId);
      buf.writeByte(this.amplifier);
      buf.writeVarIntToBuffer(this.duration);
      buf.writeByte(this.hideParticles);
   }

   public int getDuration() {
      return this.duration;
   }

   public byte getEffectId() {
      return this.effectId;
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readVarIntFromBuffer();
      this.effectId = buf.readByte();
      this.amplifier = buf.readByte();
      this.duration = buf.readVarIntFromBuffer();
      this.hideParticles = buf.readByte();
   }

   public S1DPacketEntityEffect() {
   }

   public boolean isMaxDuration() {
      return this.duration == 32767;
   }
}
