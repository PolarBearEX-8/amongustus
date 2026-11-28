package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class InventoryCrafting implements IInventory {
   private final ItemStack[] stackList;
   private final int inventoryWidth;
   private final int inventoryHeight;
   private final Container eventHandler;

   @Override
   public void setInventorySlotContents(int n, ItemStack stack) {
      this.stackList[n] = stack;
      this.eventHandler.onCraftMatrixChanged(this);
   }

   @Override
   public int getField(int n) {
      return 0;
   }

   public ItemStack getStackInRowAndColumn(int n, int n2) {
      return n >= 0 && n < this.inventoryWidth && n2 >= 0 && n2 <= this.inventoryHeight
         ? this.getStackInSlot(n + n2 * this.inventoryWidth)
         : null;
   }

   @Override
   public void clear() {
      for (int i = 0; i < this.stackList.length; i++) {
         this.stackList[i] = null;
      }
   }

   @Override
   public ItemStack decrStackSize(int n, int n2) {
      if (this.stackList[n] != null) {
         if (this.stackList[n].stackSize <= n2) {
            ItemStack stack = this.stackList[n];
            this.stackList[n] = null;
            this.eventHandler.onCraftMatrixChanged(this);
            return stack;
         } else {
            ItemStack stack2 = this.stackList[n].splitStack(n2);
            if (this.stackList[n].stackSize == 0) {
               this.stackList[n] = null;
            }

            this.eventHandler.onCraftMatrixChanged(this);
            return stack2;
         }
      } else {
         return null;
      }
   }

   @Override
   public ItemStack getStackInSlot(int n) {
      return n >= this.getSizeInventory() ? null : this.stackList[n];
   }

   @Override
   public void openInventory(EntityPlayer player) {
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return true;
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
   }

   @Override
   public boolean isItemValidForSlot(int n, ItemStack stack) {
      return true;
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public void markDirty() {
   }

   @Override
   public String getName() {
      return "container.crafting";
   }

   @Override
   public void closeInventory(EntityPlayer player) {
   }

   @Override
   public int getSizeInventory() {
      return this.stackList.length;
   }

   @Override
   public ItemStack removeStackFromSlot(int n) {
      if (this.stackList[n] != null) {
         ItemStack stack = this.stackList[n];
         this.stackList[n] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public void setField(int n, int n2) {
   }

   public InventoryCrafting(Container container, int n, int n2) {
      this.stackList = new ItemStack[n * n2];
      this.eventHandler = container;
      this.inventoryWidth = n;
      this.inventoryHeight = n2;
   }

   public int getHeight() {
      return this.inventoryHeight;
   }

   @Override
   public boolean hasCustomName() {
      return false;
   }

   public int getWidth() {
      return this.inventoryWidth;
   }
}
