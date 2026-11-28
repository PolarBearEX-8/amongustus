package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;

public class InventoryLargeChest implements ILockableContainer {
   private String name;
   private ILockableContainer upperChest;
   private ILockableContainer lowerChest;

   @Override
   public void setLockCode(LockCode lockCode) {
      this.upperChest.setLockCode(lockCode);
      this.lowerChest.setLockCode(lockCode);
   }

   @Override
   public boolean hasCustomName() {
      return this.upperChest.hasCustomName() || this.lowerChest.hasCustomName();
   }

   @Override
   public void setInventorySlotContents(int n, ItemStack stack) {
      if (n >= this.upperChest.getSizeInventory()) {
         this.lowerChest.setInventorySlotContents(n - this.upperChest.getSizeInventory(), stack);
      } else {
         this.upperChest.setInventorySlotContents(n, stack);
      }
   }

   @Override
   public ItemStack removeStackFromSlot(int n) {
      return n >= this.upperChest.getSizeInventory()
         ? this.lowerChest.removeStackFromSlot(n - this.upperChest.getSizeInventory())
         : this.upperChest.removeStackFromSlot(n);
   }

   @Override
   public ItemStack decrStackSize(int n, int n2) {
      return n >= this.upperChest.getSizeInventory()
         ? this.lowerChest.decrStackSize(n - this.upperChest.getSizeInventory(), n2)
         : this.upperChest.decrStackSize(n, n2);
   }

   @Override
   public void markDirty() {
      this.upperChest.markDirty();
      this.lowerChest.markDirty();
   }

   @Override
   public String getGuiID() {
      return this.upperChest.getGuiID();
   }

   public boolean isPartOfLargeChest(IInventory inventory) {
      return this.upperChest == inventory || this.lowerChest == inventory;
   }

   @Override
   public void setField(int n, int n2) {
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
   }

   @Override
   public String getName() {
      return this.upperChest.hasCustomName() ? this.upperChest.getName() : (this.lowerChest.hasCustomName() ? this.lowerChest.getName() : this.name);
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public int getField(int n) {
      return 0;
   }

   @Override
   public void clear() {
      this.upperChest.clear();
      this.lowerChest.clear();
   }

   @Override
   public void closeInventory(EntityPlayer player) {
      this.upperChest.closeInventory(player);
      this.lowerChest.closeInventory(player);
   }

   @Override
   public void openInventory(EntityPlayer player) {
      this.upperChest.openInventory(player);
      this.lowerChest.openInventory(player);
   }

   @Override
   public int getInventoryStackLimit() {
      return this.upperChest.getInventoryStackLimit();
   }

   @Override
   public LockCode getLockCode() {
      return this.upperChest.getLockCode();
   }

   @Override
   public boolean isItemValidForSlot(int n, ItemStack stack) {
      return true;
   }

   @Override
   public int getSizeInventory() {
      return this.upperChest.getSizeInventory() + this.lowerChest.getSizeInventory();
   }

   @Override
   public boolean isLocked() {
      return this.upperChest.isLocked() || this.lowerChest.isLocked();
   }

   public InventoryLargeChest(String str, ILockableContainer lockableContainer, ILockableContainer lockableContainer2) {
      this.name = str;
      if (lockableContainer == null) {
         lockableContainer = lockableContainer2;
      }

      if (lockableContainer2 == null) {
         lockableContainer2 = lockableContainer;
      }

      this.upperChest = lockableContainer;
      this.lowerChest = lockableContainer2;
      if (lockableContainer.isLocked()) {
         lockableContainer2.setLockCode(lockableContainer.getLockCode());
      } else if (lockableContainer2.isLocked()) {
         lockableContainer.setLockCode(lockableContainer2.getLockCode());
      }
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return this.upperChest.isUseableByPlayer(player) && this.lowerChest.isUseableByPlayer(player);
   }

   @Override
   public Container createContainer(InventoryPlayer inventoryPlayer, EntityPlayer player) {
      return new ContainerChest(inventoryPlayer, this, player);
   }

   @Override
   public ItemStack getStackInSlot(int n) {
      return n >= this.upperChest.getSizeInventory()
         ? this.lowerChest.getStackInSlot(n - this.upperChest.getSizeInventory())
         : this.upperChest.getStackInSlot(n);
   }
}
