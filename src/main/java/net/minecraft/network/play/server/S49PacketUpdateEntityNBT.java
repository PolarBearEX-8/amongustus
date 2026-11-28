package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S49PacketUpdateEntityNBT implements Packet {
   private int entityId;
   private NBTTagCompound tagCompound;

   @Override
   public void processPacket(INetHandler handler) {
      this.processPacket((INetHandlerPlayClient)handler);
   }

   public S49PacketUpdateEntityNBT(int n, NBTTagCompound tag) {
      this.entityId = n;
      this.tagCompound = tag;
   }

   public NBTTagCompound getTagCompound() {
      return this.tagCompound;
   }

   public S49PacketUpdateEntityNBT() {
   }

   @Override
   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityId = buf.readVarIntFromBuffer();
      this.tagCompound = buf.readNBTTagCompoundFromBuffer();
   }

   public Entity getEntity(World world) {
      return world.getEntityByID(this.entityId);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityNBT(this);
   }

   @Override
   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarIntToBuffer(this.entityId);
      buf.writeNBTTagCompoundToBuffer(this.tagCompound);
   }
}
