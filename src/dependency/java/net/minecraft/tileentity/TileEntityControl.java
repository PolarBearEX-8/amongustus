package net.minecraft.tileentity;

import client.util.CommandStatsManager;
import client.util.Packet;
import client.util.VectorBuilder;
import client.util.VectorDispatcher;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityControl extends TileEntity {
   public final VectorDispatcher vectorDispatcher = new VectorBuilder(this);

   public CommandStatsManager getCommandStatsManager() {
      return this.vectorDispatcher.getCommandStatsManager();
   }

   @Override
   public boolean isActiveAsFlag() {
      return true;
   }

   public VectorDispatcher getVectorDispatcher() {
      return this.vectorDispatcher;
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      this.vectorDispatcher.applyNBTTagCompound(tagCompound);
   }

   @Override
   public Packet getPacket() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      this.applyNBTTagCompound(tagCompound);
      return new S35PacketUpdateTileEntity(this.blockPos, 2, tagCompound);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.vectorDispatcher.applyNBTTagCompoundByNBTTagCompound(tagCompound);
   }
}
