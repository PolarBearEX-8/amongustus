package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityEnderChest;

public class InventoryEnderChest extends InventoryBasic {
   private TileEntityEnderChest associatedChest;

   public void loadInventoryFromNBT(NBTTagList tagList) {
      for (int i = 0; i < this.getSizeInventory(); i++) {
         this.setInventorySlotContents(i, (ItemStack)null);
      }

      for (int j = 0; j < tagList.tagCount(); j++) {
         NBTTagCompound compoundTagAt = tagList.getCompoundTagAt(j);
         int n = compoundTagAt.getByte("Slot") & 255;
         if (n >= 0 && n < this.getSizeInventory()) {
            this.setInventorySlotContents(n, ItemStack.loadItemStackFromNBT(compoundTagAt));
         }
      }
   }

   @Override
   public void closeInventory(EntityPlayer player) {
      if (this.associatedChest != null) {
         this.associatedChest.closeChest();
      }

      super.closeInventory(player);
      this.associatedChest = null;
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return this.associatedChest != null && !this.associatedChest.canBeUsed(player) ? false : super.isUseableByPlayer(player);
   }

   public InventoryEnderChest() {
      super("container.enderchest", false, 27);
   }

   public NBTTagList saveInventoryToNBT() {
      NBTTagList tagList = new NBTTagList();

      for (int i = 0; i < this.getSizeInventory(); i++) {
         ItemStack stackInSlot = this.getStackInSlot(i);
         if (stackInSlot != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setByte("Slot", (byte)i);
            stackInSlot.writeToNBT(tag);
            tagList.appendTag(tag);
         }
      }

      return tagList;
   }

   public void setChestTileEntity(TileEntityEnderChest tileEntityEnderChest) {
      this.associatedChest = tileEntityEnderChest;
   }

   @Override
   public void openInventory(EntityPlayer player) {
      if (this.associatedChest != null) {
         this.associatedChest.openChest();
      }

      super.openInventory(player);
   }
}
