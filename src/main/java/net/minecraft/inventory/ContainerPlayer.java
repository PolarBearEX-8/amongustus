package net.minecraft.inventory;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public class ContainerPlayer extends Container {
   public InventoryCrafting craftMatrix = new InventoryCrafting(this, 2, 2);
   public IInventory craftResult = new InventoryCraftResult();
   public boolean isLocalWorld;
   private final EntityPlayer thePlayer;

   @Override
   public void onCraftMatrixChanged(IInventory inventory) {
      this.craftResult.setInventorySlotContents(0, CraftingManager.getInstance().findMatchingRecipe(this.craftMatrix, this.thePlayer.entityWorld));
   }

   public ContainerPlayer(InventoryPlayer inventoryPlayer, boolean flag, EntityPlayer player, boolean flag2) {
      this.isLocalWorld = flag;
      this.thePlayer = player;
      int n = flag2 ? 20 : 18;
      if (!flag2) {
         this.addSlotToContainer(new SlotCrafting(inventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 144, 36));

         for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
               this.addSlotToContainer(new Slot(this.craftMatrix, j + i * 2, 88 + j * n, 26 + i * n));
            }
         }

         for (int k = 0; k < 4; k++) {
            this.addSlotToContainer(new ContainerPlayer.DefaultSlot(this, inventoryPlayer, inventoryPlayer.getSizeInventory() - 1 - k, 8, 8 + k * n, k));
         }
      }

      for (int m = 0; m < 3; m++) {
         for (int idx = 0; idx < 9; idx++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, idx + (m + 1) * 9, 8 + idx * n, 84 + m * n));
         }
      }

      if (!flag2) {
         for (int index = 0; index < 9; index++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, index, 8 + index * n, 142));
         }

         this.onCraftMatrixChanged(this.craftMatrix);
      }
   }

   @Override
   public boolean canInteractWith(EntityPlayer player) {
      return true;
   }

   @Override
   public void onContainerClosed(EntityPlayer player) {
      super.onContainerClosed(player);

      for (int i = 0; i < 4; i++) {
         ItemStack stack = this.craftMatrix.removeStackFromSlot(i);
         if (stack != null) {
            player.dropPlayerItemWithRandomChoice(stack, false);
         }
      }

      this.craftResult.setInventorySlotContents(0, (ItemStack)null);
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int n) {
      ItemStack stack = null;
      Slot slot = (Slot)this.inventorySlots.get(n);
      if (slot != null && slot.getHasStack()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.copy();
         if (n == 0) {
            if (!this.mergeItemStack(stack2, 9, 45, true)) {
               return null;
            }

            slot.onSlotChange(stack2, stack);
         } else if (n >= 1 && n < 5) {
            if (!this.mergeItemStack(stack2, 9, 45, false)) {
               return null;
            }
         } else if (n >= 5 && n < 9) {
            if (!this.mergeItemStack(stack2, 9, 45, false)) {
               return null;
            }
         } else if (stack.getItem() instanceof ItemArmor && !((Slot)this.inventorySlots.get(5 + ((ItemArmor)stack.getItem()).armorType)).getHasStack()) {
            int n2 = 5 + ((ItemArmor)stack.getItem()).armorType;
            if (!this.mergeItemStack(stack2, n2, n2 + 1, false)) {
               return null;
            }
         } else if (n >= 9 && n < 36) {
            if (!this.mergeItemStack(stack2, 36, 45, false)) {
               return null;
            }
         } else if (n >= 36 && n < 45) {
            if (!this.mergeItemStack(stack2, 9, 36, false)) {
               return null;
            }
         } else if (!this.mergeItemStack(stack2, 9, 45, false)) {
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
   public boolean canMergeSlot(ItemStack stack, Slot slot) {
      return slot.inventory != this.craftResult && super.canMergeSlot(stack, slot);
   }


   public static class DefaultSlot extends Slot {
      public final int armorType;
      public final ContainerPlayer containerPlayer;

      @Override
      public String getSlotTexture() {
         return ItemArmor.EMPTY_SLOT_NAMES[this.armorType];
      }

      @Override
      public boolean isItemValid(ItemStack stack) {
         return stack == null
            ? false
            : (
               stack.getItem() instanceof ItemArmor
                  ? ((ItemArmor)stack.getItem()).armorType == this.armorType
                  : (stack.getItem() != Item.getItemFromBlock(Blocks.pumpkin) && stack.getItem() != Items.skull ? false : this.armorType == 0)
            );
      }

      @Override
      public int getSlotStackLimit() {
         return 1;
      }

      public DefaultSlot(ContainerPlayer containerPlayer2, IInventory inventory2, int n, int n2, int n3, int n4) {
         super(inventory2, n, n2, n3);
         this.containerPlayer = containerPlayer2;
         this.armorType = n4;
      }
   }
}
