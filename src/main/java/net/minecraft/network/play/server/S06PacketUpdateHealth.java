package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S06PacketUpdateHealth implements Packet {
   private float health;
   private int foodLevel;
   private float saturationLevel;

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeFloat(this.health);
      buf.writeVarIntToBuffer(this.foodLevel);
      buf.writeFloat(this.saturationLevel);
   }

   public float getHealth() {
      return this.health;
   }

   public float getSaturationLevel() {
      return this.saturationLevel;
   }

   public int getFoodLevel() {
      return this.foodLevel;
   }

   public S06PacketUpdateHealth() {
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleUpdateHealth(this);
   }

   public S06PacketUpdateHealth(float f, int n, float f2) {
      this.health = f;
      this.foodLevel = n;
      this.saturationLevel = f2;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.health = buf.readFloat();
      this.foodLevel = buf.readVarIntFromBuffer();
      this.saturationLevel = buf.readFloat();
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }
}
