package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class InventoryCraftResult implements IInventory {
   private ItemStack[] stackResult = new ItemStack[1];

   @Override
   public ItemStack getStackInSlot(int n) {
      return this.stackResult[0];
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public void setInventorySlotContents(int n, ItemStack stack) {
      this.stackResult[0] = stack;
   }

   @Override
   public ItemStack decrStackSize(int n, int n2) {
      if (this.stackResult[0] != null) {
         ItemStack stack = this.stackResult[0];
         this.stackResult[0] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public boolean hasCustomName() {
      return false;
   }

   @Override
   public ItemStack removeStackFromSlot(int n) {
      if (this.stackResult[0] != null) {
         ItemStack stack = this.stackResult[0];
         this.stackResult[0] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
   }

   @Override
   public void clear() {
      for (int i = 0; i < this.stackResult.length; i++) {
         this.stackResult[i] = null;
      }
   }

   @Override
   public void openInventory(EntityPlayer player) {
   }

   @Override
   public void closeInventory(EntityPlayer player) {
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public int getSizeInventory() {
      return 1;
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return true;
   }

   @Override
   public void setField(int n, int n2) {
   }

   @Override
   public int getField(int n) {
      return 0;
   }

   @Override
   public String getName() {
      return "Result";
   }

   @Override
   public boolean isItemValidForSlot(int n, ItemStack stack) {
      return true;
   }

   @Override
   public void markDirty() {
   }
}
