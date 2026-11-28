package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S1FPacketSetExperience implements Packet {
   private float experienceBar;
   private int totalExperience;
   private int level;

   public int getLevel() {
      return this.level;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleSetExperience(this);
   }

   public S1FPacketSetExperience() {
   }

   public float getExperienceBar() {
      return this.experienceBar;
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeFloat(this.experienceBar);
      buf.writeVarIntToBuffer(this.level);
      buf.writeVarIntToBuffer(this.totalExperience);
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public int getTotalExperience() {
      return this.totalExperience;
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.experienceBar = buf.readFloat();
      this.level = buf.readVarIntFromBuffer();
      this.totalExperience = buf.readVarIntFromBuffer();
   }

   public S1FPacketSetExperience(float f, int n, int n2) {
      this.experienceBar = f;
      this.totalExperience = n;
      this.level = n2;
   }
}
