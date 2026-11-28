package net.minecraft.inventory;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.village.MerchantRecipe;

public class SlotMerchantResult extends Slot {
   private final InventoryMerchant theMerchantInventory;
   private EntityPlayer thePlayer;
   private int removeCount;
   private final IMerchant theMerchant;

   @Override
   public void onCrafting(ItemStack stack, int n) {
      this.removeCount += n;
      this.onCrafting(stack);
   }

   public SlotMerchantResult(EntityPlayer player, IMerchant merchant, InventoryMerchant inventoryMerchant, int n, int n2, int n3) {
      super(inventoryMerchant, n, n2, n3);
      this.thePlayer = player;
      this.theMerchant = merchant;
      this.theMerchantInventory = inventoryMerchant;
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return false;
   }

   @Override
   public ItemStack decrStackSize(int n) {
      if (this.getHasStack()) {
         this.removeCount = this.removeCount + Math.min(n, this.getStack().stackSize);
      }

      return super.decrStackSize(n);
   }

   @Override
   public void onCrafting(ItemStack stack) {
      stack.onCrafting(this.thePlayer.entityWorld, this.thePlayer, this.removeCount);
      this.removeCount = 0;
   }

   private boolean doTrade(MerchantRecipe merchantRecipe, ItemStack stack, ItemStack stack2) {
      ItemStack itemToBuy = merchantRecipe.getItemToBuy();
      ItemStack secondItemToBuy = merchantRecipe.getSecondItemToBuy();
      if (stack != null && stack.getItem() == itemToBuy.getItem()) {
         if (secondItemToBuy != null && stack2 != null && secondItemToBuy.getItem() == stack2.getItem()) {
            stack.stackSize = stack.stackSize - itemToBuy.stackSize;
            stack2.stackSize = stack2.stackSize - secondItemToBuy.stackSize;
            return true;
         }

         if (secondItemToBuy == null && stack2 == null) {
            stack.stackSize = stack.stackSize - itemToBuy.stackSize;
            return true;
         }
      }

      return false;
   }

   @Override
   public void onPickupFromSlot(EntityPlayer player, ItemStack stack) {
      this.onCrafting(stack);
      MerchantRecipe currentRecipe = this.theMerchantInventory.getCurrentRecipe();
      if (currentRecipe != null) {
         ItemStack stackInSlot = this.theMerchantInventory.getStackInSlot(0);
         ItemStack stackInSlot2 = this.theMerchantInventory.getStackInSlot(1);
         if (this.doTrade(currentRecipe, stackInSlot, stackInSlot2) || this.doTrade(currentRecipe, stackInSlot2, stackInSlot)) {
            this.theMerchant.useRecipe(currentRecipe);
            player.triggerAchievement(StatList.timesTradedWithVillagerStat);
            if (stackInSlot != null && stackInSlot.stackSize <= 0) {
               stackInSlot = null;
            }

            if (stackInSlot2 != null && stackInSlot2.stackSize <= 0) {
               stackInSlot2 = null;
            }

            this.theMerchantInventory.setInventorySlotContents(0, stackInSlot);
            this.theMerchantInventory.setInventorySlotContents(1, stackInSlot2);
         }
      }
   }
}
