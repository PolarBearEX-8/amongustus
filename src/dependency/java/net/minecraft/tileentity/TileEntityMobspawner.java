package net.minecraft.tileentity;

import client.event.PreUpdateConsumer;
import client.util.Packet;
import client.util.PassiveStringEntry;
import client.util.StringEntryRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityMobspawner extends TileEntity implements PreUpdateConsumer {
   public final PassiveStringEntry passiveStringEntry = new StringEntryRegistry(this);

   @Override
   public boolean isActiveAsFlag() {
      return true;
   }

   @Override
   public boolean checkIndex(int count, int count2) {
      return this.passiveStringEntry.checkIndex(count) ? true : super.checkIndex(count, count2);
   }

   @Override
   public void applyNBTTagCompoundByNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompoundByNBTTagCompound(tagCompound);
      this.passiveStringEntry.applyNBTTagCompoundByNBTTagCompound(tagCompound);
   }

   @Override
   public void updateState() {
      this.passiveStringEntry.updateState();
   }

   public PassiveStringEntry getPassiveStringEntry() {
      return this.passiveStringEntry;
   }

   @Override
   public Packet getPacket() {
      NBTTagCompound tagCompound = new NBTTagCompound();
      this.applyNBTTagCompound(tagCompound);
      tagCompound.remove("SpawnPotentials");
      return new S35PacketUpdateTileEntity(this.blockPos, 1, tagCompound);
   }

   @Override
   public void applyNBTTagCompound(NBTTagCompound tagCompound) {
      super.applyNBTTagCompound(tagCompound);
      this.passiveStringEntry.applyNBTTagCompound(tagCompound);
   }
}
