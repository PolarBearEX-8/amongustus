package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IWorldNameable;

public interface IInventory extends IWorldNameable {
   ItemStack getStackInSlot(int n);

   void setInventorySlotContents(int n, ItemStack stack);

   ItemStack decrStackSize(int n, int n2);

   int getSizeInventory();

   void closeInventory(EntityPlayer player);

   int getInventoryStackLimit();

   int getFieldCount();

   boolean isItemValidForSlot(int n, ItemStack stack);

   void markDirty();

   void openInventory(EntityPlayer player);

   boolean isUseableByPlayer(EntityPlayer player);

   void clear();

   int getField(int n);

   ItemStack removeStackFromSlot(int n);

   void setField(int n, int n2);
}
