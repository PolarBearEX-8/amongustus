package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S11PacketSpawnExperienceOrb implements Packet {
   private int entityID;
   private int posX;
   private int posY;
   private int posZ;
   private int xpValue;

   public S11PacketSpawnExperienceOrb() {
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityID);
      buf.writeInt(this.posX);
      buf.writeInt(this.posY);
      buf.writeInt(this.posZ);
      buf.writeShort(this.xpValue);
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityID = buf.readVarIntFromBuffer();
      this.posX = buf.readInt();
      this.posY = buf.readInt();
      this.posZ = buf.readInt();
      this.xpValue = buf.readShort();
   }

   public int getX() {
      return this.posX;
   }

   public int getY() {
      return this.posY;
   }

   public S11PacketSpawnExperienceOrb(EntityXPOrb entityXPOrb) {
      this.entityID = entityXPOrb.getEntityId();
      this.posX = MathHelper.floor_double(entityXPOrb.posX * 32.0);
      this.posY = MathHelper.floor_double(entityXPOrb.posY * 32.0);
      this.posZ = MathHelper.floor_double(entityXPOrb.posZ * 32.0);
      this.xpValue = entityXPOrb.getXpValue();
   }

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public int getZ() {
      return this.posZ;
   }

   public int getXPValue() {
      return this.xpValue;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleSpawnExperienceOrb(this);
   }

   public int getEntityID() {
      return this.entityID;
   }
}
