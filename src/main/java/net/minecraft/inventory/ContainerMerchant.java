package net.minecraft.inventory;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerMerchant extends Container {
   private IMerchant theMerchant;
   private InventoryMerchant merchantInventory;
   private final World theWorld;

   @Override
   public void detectAndSendChanges() {
      super.detectAndSendChanges();
   }

   @Override
   public void onCraftGuiOpened(ICrafting crafting) {
      super.onCraftGuiOpened(crafting);
   }

   public InventoryMerchant getMerchantInventory() {
      return this.merchantInventory;
   }

   @Override
   public void updateProgressBar(int n, int n2) {
   }

   @Override
   public boolean canInteractWith(EntityPlayer player) {
      return this.theMerchant.getCustomer() == player;
   }

   public void setCurrentRecipeIndex(int n) {
      this.merchantInventory.setCurrentRecipeIndex(n);
   }

   @Override
   public void onCraftMatrixChanged(IInventory inventory) {
      this.merchantInventory.resetRecipeAndSlots();
      super.onCraftMatrixChanged(inventory);
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
         } else if (n != 0 && n != 1) {
            if (n >= 3 && n < 30) {
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

   @Override
   public void onContainerClosed(EntityPlayer player) {
      super.onContainerClosed(player);
      this.theMerchant.setCustomer((EntityPlayer)null);
      super.onContainerClosed(player);
      if (!this.theWorld.isRemote) {
         ItemStack stack = this.merchantInventory.removeStackFromSlot(0);
         if (stack != null) {
            player.dropPlayerItemWithRandomChoice(stack, false);
         }

         stack = this.merchantInventory.removeStackFromSlot(1);
         if (stack != null) {
            player.dropPlayerItemWithRandomChoice(stack, false);
         }
      }
   }

   public ContainerMerchant(InventoryPlayer inventoryPlayer, IMerchant merchant, World world) {
      this.theMerchant = merchant;
      this.theWorld = world;
      this.merchantInventory = new InventoryMerchant(inventoryPlayer.player, merchant);
      this.addSlotToContainer(new Slot(this.merchantInventory, 0, 36, 53));
      this.addSlotToContainer(new Slot(this.merchantInventory, 1, 62, 53));
      this.addSlotToContainer(new SlotMerchantResult(inventoryPlayer.player, merchant, this.merchantInventory, 2, 120, 53));

      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 9; j++) {
            this.addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
         }
      }

      for (int k = 0; k < 9; k++) {
         this.addSlotToContainer(new Slot(inventoryPlayer, k, 8 + k * 18, 142));
      }
   }
}
