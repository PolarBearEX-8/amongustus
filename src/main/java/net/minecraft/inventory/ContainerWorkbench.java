package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerWorkbench extends Container {
   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
   public IInventory craftResult = new InventoryCraftResult();
   private World worldObj;
   private BlockPos pos;

   public ContainerWorkbench(InventoryPlayer inventoryPlayer, World world, BlockPos pos2) {
      this.worldObj = world;
      this.pos = pos2;
      this.addSlotToContainer(new SlotCrafting(inventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 124, 35));

      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 3, 30 + j * 18, 17 + i * 18));
         }
      }

      for (int k = 0; k < 3; k++) {
         for (int m = 0; m < 9; m++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, m + k * 9 + 9, 8 + m * 18, 84 + k * 18));
         }
      }

      for (int idx = 0; idx < 9; idx++) {
         this.addSlotToContainer(new Slot(inventoryPlayer, idx, 8 + idx * 18, 142));
      }

      this.onCraftMatrixChanged(this.craftMatrix);
   }

   @Override
   public void onCraftMatrixChanged(IInventory inventory) {
      this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.worldObj));
   }

   @Override
   public void onContainerClosed(EntityPlayer player) {
      super.onContainerClosed(player);
      if (!this.worldObj.isRemote) {
         for (int i = 0; i < 9; i++) {
            ItemStack stack = this.craftMatrix.removeStackFromSlot(i);
            if (stack != null) {
               player.dropPlayerItemWithRandomChoice(stack, false);
            }
         }
      }
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int n) {
      ItemStack stack = null;
      Slot slot = (Slot)this.inventorySlots.get(n);
      if (slot != null && slot.getHasStack()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.copy();
         if (n == 0) {
            if (!this.mergeItemStack(stack2, 10, 46, true)) {
               return null;
            }

            slot.onSlotChange(stack2, stack);
         } else if (n >= 10 && n < 37) {
            if (!this.mergeItemStack(stack2, 37, 46, false)) {
               return null;
            }
         } else if (n >= 37 && n < 46) {
            if (!this.mergeItemStack(stack2, 10, 37, false)) {
               return null;
            }
         } else if (!this.mergeItemStack(stack2, 10, 46, false)) {
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
      return this.worldObj.getBlockState(this.pos).getBlock() != Blocks.crafting_table
         ? false
         : player.getDistanceSq((double)this.pos.getX() + 0.5, (double)this.pos.getY() + 0.5, (double)this.pos.getZ() + 0.5) <= 64.0;
   }

   @Override
   public boolean canMergeSlot(ItemStack stack, Slot slot) {
      return slot.inventory != this.craftResult && super.canMergeSlot(stack, slot);
   }
}
