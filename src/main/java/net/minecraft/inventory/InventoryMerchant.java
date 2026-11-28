package net.minecraft.inventory;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class InventoryMerchant implements IInventory {
   private final IMerchant theMerchant;
   private ItemStack[] theInventory = new ItemStack[3];
   private final EntityPlayer thePlayer;
   private MerchantRecipe currentRecipe;
   private int currentRecipeIndex;

   @Override
   public void markDirty() {
      this.resetRecipeAndSlots();
   }

   @Override
   public int getInventoryStackLimit() {
      return 64;
   }

   @Override
   public ItemStack decrStackSize(int n, int n2) {
      if (this.theInventory[n] != null) {
         if (n == 2) {
            ItemStack stack = this.theInventory[n];
            this.theInventory[n] = null;
            return stack;
         } else if (this.theInventory[n].stackSize <= n2) {
            ItemStack stack2 = this.theInventory[n];
            this.theInventory[n] = null;
            if (this.inventoryResetNeededOnSlotChange(n)) {
               this.resetRecipeAndSlots();
            }

            return stack2;
         } else {
            ItemStack stack3 = this.theInventory[n].splitStack(n2);
            if (this.theInventory[n].stackSize == 0) {
               this.theInventory[n] = null;
            }

            if (this.inventoryResetNeededOnSlotChange(n)) {
               this.resetRecipeAndSlots();
            }

            return stack3;
         }
      } else {
         return null;
      }
   }

   @Override
   public ItemStack removeStackFromSlot(int n) {
      if (this.theInventory[n] != null) {
         ItemStack stack = this.theInventory[n];
         this.theInventory[n] = null;
         return stack;
      } else {
         return null;
      }
   }

   @Override
   public boolean isItemValidForSlot(int n, ItemStack stack) {
      return true;
   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return this.theMerchant.getCustomer() == player;
   }

   @Override
   public void openInventory(EntityPlayer player) {
   }

   public InventoryMerchant(EntityPlayer player, IMerchant merchant) {
      this.thePlayer = player;
      this.theMerchant = merchant;
   }

   @Override
   public ItemStack getStackInSlot(int n) {
      return this.theInventory[n];
   }

   public void resetRecipeAndSlots() {
      this.currentRecipe = null;
      ItemStack stack = this.theInventory[0];
      ItemStack stack2 = this.theInventory[1];
      if (stack == null) {
         stack = stack2;
         stack2 = null;
      }

      if (stack == null) {
         this.setInventorySlotContents(2, (ItemStack)null);
      } else {
         MerchantRecipeList recipes = this.theMerchant.getRecipes(this.thePlayer);
         if (recipes != null) {
            MerchantRecipe merchantRecipe = recipes.canRecipeBeUsed(stack, stack2, this.currentRecipeIndex);
            if (merchantRecipe != null && !merchantRecipe.isRecipeDisabled()) {
               this.currentRecipe = merchantRecipe;
               this.setInventorySlotContents(2, merchantRecipe.getItemToSell().copy());
            } else if (stack2 != null) {
               merchantRecipe = recipes.canRecipeBeUsed(stack2, stack, this.currentRecipeIndex);
               if (merchantRecipe != null && !merchantRecipe.isRecipeDisabled()) {
                  this.currentRecipe = merchantRecipe;
                  this.setInventorySlotContents(2, merchantRecipe.getItemToSell().copy());
               } else {
                  this.setInventorySlotContents(2, (ItemStack)null);
               }
            } else {
               this.setInventorySlotContents(2, (ItemStack)null);
            }
         }
      }

      this.theMerchant.verifySellingItem(this.getStackInSlot(2));
   }

   private boolean inventoryResetNeededOnSlotChange(int n) {
      return n == 0 || n == 1;
   }

   @Override
   public IChatComponent getDisplayName() {
      return (IChatComponent)(this.hasCustomName() ? new ChatComponentText(this.getName()) : new ChatComponentTranslation(this.getName(), new Object[0]));
   }

   public void setCurrentRecipeIndex(int n) {
      this.currentRecipeIndex = n;
      this.resetRecipeAndSlots();
   }

   @Override
   public String getName() {
      return "mob.villager";
   }

   @Override
   public boolean hasCustomName() {
      return false;
   }

   @Override
   public void clear() {
      for (int i = 0; i < this.theInventory.length; i++) {
         this.theInventory[i] = null;
      }
   }

   @Override
   public void setInventorySlotContents(int n, ItemStack stack) {
      this.theInventory[n] = stack;
      if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
         stack.stackSize = this.getInventoryStackLimit();
      }

      if (this.inventoryResetNeededOnSlotChange(n)) {
         this.resetRecipeAndSlots();
      }
   }

   @Override
   public int getSizeInventory() {
      return this.theInventory.length;
   }

   @Override
   public void closeInventory(EntityPlayer player) {
   }

   public MerchantRecipe getCurrentRecipe() {
      return this.currentRecipe;
   }

   @Override
   public int getField(int n) {
      return 0;
   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public void setField(int n, int n2) {
   }
}
