package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class ContainerHopper extends Container {
   private final IInventory hopperInventory;

   public ContainerHopper(InventoryPlayer inventoryPlayer, IInventory inventory, EntityPlayer player) {
      this.hopperInventory = inventory;
      inventory.openInventory(player);
      byte b = 51;

      for (int i = 0; i < inventory.getSizeInventory(); i++) {
         this.addSlotToContainer(new Slot(inventory, i, 44 + i * 18, 20));
      }

      for (int j = 0; j < 3; j++) {
         for (int k = 0; k < 9; k++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, k + j * 9 + 9, 8 + k * 18, j * 18 + b));
         }
      }

      for (int m = 0; m < 9; m++) {
         this.addSlotToContainer(new Slot(inventoryPlayer, m, 8 + m * 18, 58 + b));
      }
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int n) {
      ItemStack stack = null;
      Slot slot = (Slot)this.inventorySlots.get(n);
      if (slot != null && slot.getHasStack()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.copy();
         if (n < this.hopperInventory.getSizeInventory()) {
            if (!this.mergeItemStack(stack2, this.hopperInventory.getSizeInventory(), this.inventorySlots.size(), true)) {
               return null;
            }
         } else if (!this.mergeItemStack(stack2, 0, this.hopperInventory.getSizeInventory(), false)) {
            return null;
         }

         if (stack2.stackSize == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }
      }

      return stack;
   }

   @Override
   public boolean canInteractWith(EntityPlayer player) {
      return this.hopperInventory.isUseableByPlayer(player);
   }

   @Override
   public void onContainerClosed(EntityPlayer player) {
      super.onContainerClosed(player);
      this.hopperInventory.closeInventory(player);
   }
}
