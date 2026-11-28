package net.minecraft.inventory;

import net.minecraft.init.Items;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ContainerHorseInventory extends Container {
   private IInventory horseInventory;
   private EntityHorse theHorse;

   public ContainerHorseInventory(IInventory inventory, IInventory inventory2, EntityHorse entityHorse, EntityPlayer player) {
      this.horseInventory = inventory2;
      this.theHorse = entityHorse;
      byte b = 3;
      inventory2.openInventory(player);
      int n = (b - 4) * 18;
      this.addSlotToContainer(new ContainerHorseInventory.DefaultSlot(this, inventory2, 0, 8, 18));
      this.addSlotToContainer(new ContainerHorseInventory.DefaultSlot2(this, inventory2, 1, 8, 36, entityHorse));
      if (entityHorse.isChested()) {
         for (int i = 0; i < b; i++) {
            for (int j = 0; j < 5; j++) {
               this.addSlotToContainer(new Slot(inventory2, 2 + j + i * 5, 80 + j * 18, 18 + i * 18));
            }
         }
      }

      for (int k = 0; k < 3; k++) {
         for (int m = 0; m < 9; m++) {
            this.addSlotToContainer(new Slot(inventory, m + k * 9 + 9, 8 + m * 18, 102 + k * 18 + n));
         }
      }

      for (int idx = 0; idx < 9; idx++) {
         this.addSlotToContainer(new Slot(inventory, idx, 8 + idx * 18, 160 + n));
      }
   }

   @Override
   public boolean canInteractWith(EntityPlayer player) {
      return this.horseInventory.isUseableByPlayer(player) && this.theHorse.isEntityAlive() && this.theHorse.getDistanceToEntity(player) < 8.0F;
   }

   @Override
   public void onContainerClosed(EntityPlayer player) {
      super.onContainerClosed(player);
      this.horseInventory.closeInventory(player);
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int n) {
      ItemStack stack = null;
      Slot slot = (Slot)this.inventorySlots.get(n);
      if (slot != null && slot.getHasStack()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.copy();
         if (n < this.horseInventory.getSizeInventory()) {
            if (!this.mergeItemStack(stack2, this.horseInventory.getSizeInventory(), this.inventorySlots.size(), true)) {
               return null;
            }
         } else if (this.getSlot(1).isItemValid(stack2) && !this.getSlot(1).getHasStack()) {
            if (!this.mergeItemStack(stack2, 1, 2, false)) {
               return null;
            }
         } else if (this.getSlot(0).isItemValid(stack2)) {
            if (!this.mergeItemStack(stack2, 0, 1, false)) {
               return null;
            }
         } else if (this.horseInventory.getSizeInventory() <= 2 || !this.mergeItemStack(stack2, 2, this.horseInventory.getSizeInventory(), false)) {
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


   public static class DefaultSlot extends Slot {
      public final ContainerHorseInventory containerHorseInventory;

      @Override
      public boolean isItemValid(ItemStack stack) {
         return super.isItemValid(stack) && stack.getItem() == Items.saddle && !this.getHasStack();
      }

      public DefaultSlot(ContainerHorseInventory containerHorseInventory2, IInventory inventory2, int n, int n2, int n3) {
         super(inventory2, n, n2, n3);
         this.containerHorseInventory = containerHorseInventory2;
      }
   }

   public static class DefaultSlot2 extends Slot {
      public final EntityHorse horse;
      public final ContainerHorseInventory containerHorseInventory;

      public DefaultSlot2(ContainerHorseInventory containerHorseInventory2, IInventory inventory2, int n, int n2, int n3, EntityHorse entityHorse) {
         super(inventory2, n, n2, n3);
         this.containerHorseInventory = containerHorseInventory2;
         this.horse = entityHorse;
      }

      @Override
      public boolean canBeHovered() {
         return this.horse.canWearArmor();
      }

      @Override
      public boolean isItemValid(ItemStack stack) {
         return super.isItemValid(stack) && this.horse.canWearArmor() && EntityHorse.isArmorItem(stack.getItem());
      }
   }
}
