package net.minecraft.inventory;

import net.minecraft.init.Items;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ContainerBeacon extends Container {
   private IInventory tileBeacon;
   private final ContainerBeacon.BeaconSlot beaconSlot;

   public IInventory getTileBeacon() {
      return this.tileBeacon;
   }

   @Override
   public void updateProgressBar(int n, int n2) {
      this.tileBeacon.setField(n, n2);
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int n) {
      ItemStack stack = null;
      Slot slot = (Slot)this.inventorySlots.get(n);
      if (slot != null && slot.getHasStack()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.copy();
         if (n == 0) {
            if (!this.mergeItemStack(stack2, 1, 37, true)) {
               return null;
            }

            slot.onSlotChange(stack2, stack);
         } else if (!this.beaconSlot.getHasStack() && this.beaconSlot.isItemValid(stack2) && stack2.stackSize == 1) {
            if (!this.mergeItemStack(stack2, 0, 1, false)) {
               return null;
            }
         } else if (n >= 1 && n < 28) {
            if (!this.mergeItemStack(stack2, 28, 37, false)) {
               return null;
            }
         } else if (n >= 28 && n < 37) {
            if (!this.mergeItemStack(stack2, 1, 28, false)) {
               return null;
            }
         } else if (!this.mergeItemStack(stack2, 1, 37, false)) {
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

   public ContainerBeacon(IInventory inventory, IInventory inventory2) {
      this.tileBeacon = inventory2;
      this.addSlotToContainer(this.beaconSlot = new ContainerBeacon.BeaconSlot(this, inventory2, 0, 136, 110));
      byte b = 36;
      short s = 137;

      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 9; j++) {
            this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, b + j * 18, s + i * 18));
         }
      }

      for (int k = 0; k < 9; k++) {
         this.addSlotToContainer(new Slot(inventory, k, b + k * 18, 58 + s));
      }
   }

   @Override
   public boolean canInteractWith(EntityPlayer player) {
      return this.tileBeacon.isUseableByPlayer(player);
   }

   @Override
   public void onContainerClosed(EntityPlayer player) {
      super.onContainerClosed(player);
      if (player != null && !player.entityWorld.isRemote) {
         ItemStack stack = this.beaconSlot.decrStackSize(this.beaconSlot.getSlotStackLimit());
         if (stack != null) {
            player.dropPlayerItemWithRandomChoice(stack, false);
         }
      }
   }

   @Override
   public void onCraftGuiOpened(ICrafting crafting) {
      super.onCraftGuiOpened(crafting);
      crafting.sendAllWindowProperties(this, this.tileBeacon);
   }


   public static class BeaconSlot extends Slot {
      public final ContainerBeacon containerBeacon;

      @Override
      public boolean isItemValid(ItemStack stack) {
         return stack == null
            ? false
            : stack.getItem() == Items.emerald || stack.getItem() == Items.diamond || stack.getItem() == Items.gold_ingot || stack.getItem() == Items.iron_ingot;
      }

      public BeaconSlot(ContainerBeacon containerBeacon2, IInventory inventory2, int n, int n2, int n3) {
         super(inventory2, n, n2, n3);
         this.containerBeacon = containerBeacon2;
      }

      @Override
      public int getSlotStackLimit() {
         return 1;
      }
   }
}
