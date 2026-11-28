package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ContainerChest extends Container {
   private IInventory lowerChestInventory;
   private int numRows;

   @Override
   public void onContainerClosed(EntityPlayer player) {
      super.onContainerClosed(player);
      this.lowerChestInventory.closeInventory(player);
   }

   public ContainerChest(IInventory inventory, IInventory inventory2, EntityPlayer player) {
      this.lowerChestInventory = inventory2;
      this.numRows = inventory2.getSizeInventory() / 9;
      inventory2.openInventory(player);
      int n = (this.numRows - 4) * 18;

      for (int i = 0; i < this.numRows; i++) {
         for (int j = 0; j < 9; j++) {
            this.addSlotToContainer(new Slot(inventory2, j + i * 9, 8 + j * 18, 18 + i * 18));
         }
      }

      for (int k = 0; k < 3; k++) {
         for (int m = 0; m < 9; m++) {
            this.addSlotToContainer(new Slot(inventory, m + k * 9 + 9, 8 + m * 18, 103 + k * 18 + n));
         }
      }

      for (int idx = 0; idx < 9; idx++) {
         this.addSlotToContainer(new Slot(inventory, idx, 8 + idx * 18, 161 + n));
      }
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int n) {
      ItemStack stack = null;
      Slot slot = (Slot)this.inventorySlots.get(n);
      if (slot != null && slot.getHasStack()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.copy();
         if (n < this.numRows * 9) {
            if (!this.mergeItemStack(stack2, this.numRows * 9, this.inventorySlots.size(), true)) {
               return null;
            }
         } else if (!this.mergeItemStack(stack2, 0, this.numRows * 9, false)) {
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
      return this.lowerChestInventory.isUseableByPlayer(player);
   }

   public IInventory getLowerChestInventory() {
      return this.lowerChestInventory;
   }
}
