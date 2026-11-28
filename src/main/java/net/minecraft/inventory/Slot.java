package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Slot {
   private final int slotIndex;
   public final IInventory inventory;
   public int slotNumber;
   public int xDisplayPosition;
   public int yDisplayPosition;

   public boolean isItemValid(ItemStack stack) {
      return true;
   }

   public boolean canTakeStack(EntityPlayer player) {
      return true;
   }

   public void onSlotChanged() {
      this.inventory.markDirty();
   }

   public String getSlotTexture() {
      return null;
   }

   public void onCrafting(ItemStack stack) {
   }

   public boolean canBeHovered() {
      return true;
   }

   public double getDistanceTo(Slot slot) {
      int n = slot.xDisplayPosition - this.xDisplayPosition;
      int n2 = slot.yDisplayPosition - this.yDisplayPosition;
      return Math.sqrt((double)(n * n + n2 * n2));
   }

   public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
      this.onSlotChanged();
   }

   public boolean isHere(IInventory inventory2, int n) {
      return inventory2 == this.inventory && n == this.slotIndex;
   }

   public void onSlotChange(ItemStack stack, ItemStack stack2) {
      if (stack != null && stack2 != null && stack.getItem() == stack2.getItem()) {
         int n = stack2.stackSize - stack.stackSize;
         if (n > 0) {
            this.onCrafting(stack, n);
         }
      }
   }

   public void onCrafting(ItemStack stack, int n) {
   }

   public int getSlotStackLimit() {
      return this.inventory.getInventoryStackLimit();
   }

   public void putStack(ItemStack stack) {
      this.inventory.setInventorySlotContents(this.slotIndex, stack);
      this.onSlotChanged();
   }

   public Slot(IInventory inventory2, int n, int n2, int n3) {
      this.inventory = inventory2;
      this.slotIndex = n;
      this.xDisplayPosition = n2;
      this.yDisplayPosition = n3;
   }

   public boolean getHasStack() {
      return this.getStack() != null;
   }

   public ItemStack decrStackSize(int n) {
      return this.inventory.decrStackSize(this.slotIndex, n);
   }

   public int getItemStackLimit(ItemStack stack) {
      return this.getSlotStackLimit();
   }

   public ItemStack getStack() {
      return this.inventory.getStackInSlot(this.slotIndex);
   }
}
