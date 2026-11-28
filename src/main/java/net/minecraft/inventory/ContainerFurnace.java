package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerFurnace extends Container {
   private final IInventory tileFurnace;
   private int cookTime;
   private int totalCookTime;
   private int furnaceBurnTime;
   private int currentItemBurnTime;

   public ContainerFurnace(InventoryPlayer inventoryPlayer, IInventory inventory) {
      this.tileFurnace = inventory;
      this.addSlotToContainer(new Slot(inventory, 0, 56, 17));
      this.addSlotToContainer(new SlotFurnaceFuel(inventory, 1, 56, 53));
      this.addSlotToContainer(new SlotFurnaceOutput(inventoryPlayer.player, inventory, 2, 116, 35));

      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 9; j++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for (int k = 0; k < 9; k++) {
         this.addSlotToContainer(new Slot(inventoryPlayer, k, 8 + k * 18, 142));
      }
   }

   @Override
   public boolean canInteractWith(EntityPlayer player) {
      return this.tileFurnace.isUseableByPlayer(player);
   }

   @Override
   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for (int i = 0; i < this.crafters.size(); i++) {
         ICrafting crafting = (ICrafting)this.crafters.get(i);
         if (this.cookTime != this.tileFurnace.getField(2)) {
            crafting.sendProgressBarUpdate(this, 2, this.tileFurnace.getField(2));
         }

         if (this.furnaceBurnTime != this.tileFurnace.getField(0)) {
            crafting.sendProgressBarUpdate(this, 0, this.tileFurnace.getField(0));
         }

         if (this.currentItemBurnTime != this.tileFurnace.getField(1)) {
            crafting.sendProgressBarUpdate(this, 1, this.tileFurnace.getField(1));
         }

         if (this.totalCookTime != this.tileFurnace.getField(3)) {
            crafting.sendProgressBarUpdate(this, 3, this.tileFurnace.getField(3));
         }
      }

      this.cookTime = this.tileFurnace.getField(2);
      this.furnaceBurnTime = this.tileFurnace.getField(0);
      this.currentItemBurnTime = this.tileFurnace.getField(1);
      this.totalCookTime = this.tileFurnace.getField(3);
   }

   @Override
   public void onCraftGuiOpened(ICrafting crafting) {
      super.onCraftGuiOpened(crafting);
      crafting.sendAllWindowProperties(this, this.tileFurnace);
   }

   @Override
   public void updateProgressBar(int n, int n2) {
      this.tileFurnace.setField(n, n2);
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int n) {
      ItemStack stack = null;
      Slot slot = (Slot)this.inventorySlots.get(n);
      if (slot != null && slot.getHasStack()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.copy();
         if (n == 2) {
            if (!this.mergeItemStack(stack2, 3, 39, true)) {
               return null;
            }

            slot.onSlotChange(stack2, stack);
         } else if (n != 1 && n != 0) {
            if (FurnaceRecipes.instance().getSmeltingResult(stack2) != null) {
               if (!this.mergeItemStack(stack2, 0, 1, false)) {
                  return null;
               }
            } else if (TileEntityFurnace.isItemFuel(stack2)) {
               if (!this.mergeItemStack(stack2, 1, 2, false)) {
                  return null;
               }
            } else if (n >= 3 && n < 30) {
               if (!this.mergeItemStack(stack2, 30, 39, false)) {
                  return null;
               }
            } else if (n >= 30 && n < 39 && !this.mergeItemStack(stack2, 3, 30, false)) {
               return null;
            }
         } else if (!this.mergeItemStack(stack2, 3, 39, false)) {
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
}
