package net.minecraft.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ShapedRecipes implements IRecipe {
   private final int recipeWidth;
   private final int recipeHeight;
   private final ItemStack[] recipeItems;
   private final ItemStack recipeOutput;
   private boolean copyIngredientNBT;

   public ItemStack[] getRecipeItems() {
      return this.recipeItems;
   }

   @Override
   public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
      ItemStack stack = this.getRecipeOutput().copy();
      if (this.copyIngredientNBT) {
         for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
            ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
            if (stackInSlot != null && stackInSlot.hasTagCompound()) {
               stack.setTagCompound((NBTTagCompound)stackInSlot.getTagCompound().copy());
            }
         }
      }

      return stack;
   }

   public ShapedRecipes(int n, int n2, ItemStack[] stacks, ItemStack stack) {
      this.recipeWidth = n;
      this.recipeHeight = n2;
      this.recipeItems = stacks;
      this.recipeOutput = stack;
   }

   public int getRecipeWidth() {
      return this.recipeWidth;
   }

   @Override
   public ItemStack[] getRemainingItems(InventoryCrafting inventoryCrafting) {
      ItemStack[] stacks = new ItemStack[inventoryCrafting.getSizeInventory()];

      for (int i = 0; i < stacks.length; i++) {
         ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
         if (stackInSlot != null && stackInSlot.getItem().hasContainerItem()) {
            stacks[i] = new ItemStack(stackInSlot.getItem().getContainerItem());
         }
      }

      return stacks;
   }

   private boolean checkMatch(InventoryCrafting inventoryCrafting, int n, int n2, boolean flag) {
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            int n3 = i - n;
            int n4 = j - n2;
            ItemStack stack = null;
            if (n3 >= 0 && n4 >= 0 && n3 < this.recipeWidth && n4 < this.recipeHeight) {
               if (flag) {
                  stack = this.recipeItems[this.recipeWidth - n3 - 1 + n4 * this.recipeWidth];
               } else {
                  stack = this.recipeItems[n3 + n4 * this.recipeWidth];
               }
            }

            ItemStack stackInRowAndColumn = inventoryCrafting.getStackInRowAndColumn(i, j);
            if (stackInRowAndColumn != null || stack != null) {
               if (stackInRowAndColumn == null && stack != null || stackInRowAndColumn != null && stack == null) {
                  return false;
               }

               if (stack.getItem() != stackInRowAndColumn.getItem()) {
                  return false;
               }

               if (stack.getMetadata() != 32767 && stack.getMetadata() != stackInRowAndColumn.getMetadata()) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   @Override
   public int getRecipeSize() {
      return this.recipeWidth * this.recipeHeight;
   }

   @Override
   public boolean matches(InventoryCrafting inventoryCrafting, World world) {
      for (int i = 0; i <= 3 - this.recipeWidth; i++) {
         for (int j = 0; j <= 3 - this.recipeHeight; j++) {
            if (this.checkMatch(inventoryCrafting, i, j, true)) {
               return true;
            }

            if (this.checkMatch(inventoryCrafting, i, j, false)) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public ItemStack getRecipeOutput() {
      return this.recipeOutput;
   }
}
