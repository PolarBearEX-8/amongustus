package net.minecraft.inventory;

import net.minecraft.init.Items;
import net.minecraft.stats.AchievementList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class ContainerBrewingStand extends Container {
   private IInventory tileBrewingStand;
   private final Slot theSlot;
   private int brewTime;

   public ContainerBrewingStand(InventoryPlayer inventoryPlayer, IInventory inventory) {
      this.tileBrewingStand = inventory;
      this.addSlotToContainer(new ContainerBrewingStand.Potion(inventoryPlayer.player, inventory, 0, 56, 46));
      this.addSlotToContainer(new ContainerBrewingStand.Potion(inventoryPlayer.player, inventory, 1, 79, 53));
      this.addSlotToContainer(new ContainerBrewingStand.Potion(inventoryPlayer.player, inventory, 2, 102, 46));
      this.theSlot = this.addSlotToContainer(new ContainerBrewingStand.Ingredient(this, inventory, 3, 79, 17));

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
      return this.tileBrewingStand.isUseableByPlayer(player);
   }

   @Override
   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for (int i = 0; i < this.crafters.size(); i++) {
         ICrafting crafting = (ICrafting)this.crafters.get(i);
         if (this.brewTime != this.tileBrewingStand.getField(0)) {
            crafting.sendProgressBarUpdate(this, 0, this.tileBrewingStand.getField(0));
         }
      }

      this.brewTime = this.tileBrewingStand.getField(0);
   }

   @Override
   public void onCraftGuiOpened(ICrafting crafting) {
      super.onCraftGuiOpened(crafting);
      crafting.sendAllWindowProperties(this, this.tileBrewingStand);
   }

   @Override
   public ItemStack transferStackInSlot(EntityPlayer player, int n) {
      ItemStack stack = null;
      Slot slot = (Slot)this.inventorySlots.get(n);
      if (slot != null && slot.getHasStack()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.copy();
         if ((n < 0 || n > 2) && n != 3) {
            if (!this.theSlot.getHasStack() && this.theSlot.isItemValid(stack2)) {
               if (!this.mergeItemStack(stack2, 3, 4, false)) {
                  return null;
               }
            } else if (ContainerBrewingStand.Potion.canHoldPotion(stack)) {
               if (!this.mergeItemStack(stack2, 0, 3, false)) {
                  return null;
               }
            } else if (n >= 4 && n < 31) {
               if (!this.mergeItemStack(stack2, 31, 40, false)) {
                  return null;
               }
            } else if (n >= 31 && n < 40) {
               if (!this.mergeItemStack(stack2, 4, 31, false)) {
                  return null;
               }
            } else if (!this.mergeItemStack(stack2, 4, 40, false)) {
               return null;
            }
         } else {
            if (!this.mergeItemStack(stack2, 4, 40, true)) {
               return null;
            }

            slot.onSlotChange(stack2, stack);
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
   public void updateProgressBar(int n, int n2) {
      this.tileBrewingStand.setField(n, n2);
   }


   public static class Potion extends Slot {
      private EntityPlayer player;

      @Override
      public void onPickupFromSlot(EntityPlayer player2, ItemStack stack) {
         if (stack.getItem() == Items.potionitem && stack.getMetadata() > 0) {
            this.player.triggerAchievement(AchievementList.potion);
         }

         super.onPickupFromSlot(player2, stack);
      }

      @Override
      public boolean isItemValid(ItemStack stack) {
         return canHoldPotion(stack);
      }

      public static boolean canHoldPotion(ItemStack stack) {
         return stack != null && (stack.getItem() == Items.potionitem || stack.getItem() == Items.glass_bottle);
      }

      public Potion(EntityPlayer player2, IInventory inventory2, int n, int n2, int n3) {
         super(inventory2, n, n2, n3);
         this.player = player2;
      }

      @Override
      public int getSlotStackLimit() {
         return 1;
      }
   }

   public static class Ingredient extends Slot {
      public final ContainerBrewingStand containerBrewingStand;

      @Override
      public int getSlotStackLimit() {
         return 64;
      }

      @Override
      public boolean isItemValid(ItemStack stack) {
         return stack != null ? stack.getItem().isPotionIngredient(stack) : false;
      }

      public Ingredient(ContainerBrewingStand containerBrewingStand2, IInventory inventory2, int n, int n2, int n3) {
         super(inventory2, n, n2, n3);
         this.containerBrewingStand = containerBrewingStand2;
      }
   }
}
