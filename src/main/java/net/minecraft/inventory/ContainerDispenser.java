package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ContainerDispenser extends Container {
   private IInventory dispenserInventory;

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int n) {
      ItemStack stack = null;
      Slot slot = (Slot)this.inventorySlots.get(n);
      if (slot != null && slot.getHasStack()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.copy();
         if (n < 9) {
            if (!this.mergeItemStack(stack2, 9, 45, true)) {
               return null;
            }
         } else if (!this.mergeItemStack(stack2, 0, 9, false)) {
            return null;
         }

         if (stack2.stackSize == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }

         if (stack2.stackSize == stack.stackSize) {
            return null;
         }

         slot.onPickupFromSlot(player, stack2);
      }

      return stack;
   }

   @Override
   public boolean canInteractWith(EntityPlayer player) {
      return this.dispenserInventory.isUseableByPlayer(player);
   }

   public ContainerDispenser(IInventory inventory, IInventory inventory2) {
      this.dispenserInventory = inventory2;

      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            this.addSlotToContainer(new Slot(inventory2, j + i * 3, 62 + j * 18, 17 + i * 18));
         }
      }

      for (int k = 0; k < 3; k++) {
         for (int m = 0; m < 9; m++) {
            this.addSlotToContainer(new Slot(inventory, m + k * 9 + 9, 8 + m * 18, 84 + k * 18));
         }
      }

      for (int idx = 0; idx < 9; idx++) {
         this.addSlotToContainer(new Slot(inventory, idx, 8 + idx * 18, 142));
      }
   }
}
