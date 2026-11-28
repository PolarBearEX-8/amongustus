package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ShapelessRecipes implements IRecipe {
   private final ItemStack recipeOutput;
   private final List recipeItems;

   @Override
   public ItemStack getRecipeOutput() {
      return this.recipeOutput;
   }

   @Override
   public boolean matches(InventoryCrafting inventoryCrafting, World world) {
      ArrayList list = Lists.newArrayList(this.recipeItems);

      for (int i = 0; i < inventoryCrafting.getHeight(); i++) {
         for (int j = 0; j < inventoryCrafting.getWidth(); j++) {
            ItemStack stackInRowAndColumn = inventoryCrafting.getStackInRowAndColumn(j, i);
            if (stackInRowAndColumn != null) {
               boolean flag = false;

               for (ItemStack stack : (Iterable<ItemStack>)(list)) {
                  if (stackInRowAndColumn.getItem() == stack.getItem() && (stack.getMetadata() == 32767 || stackInRowAndColumn.getMetadata() == stack.getMetadata())) {
                     flag = true;
                     list.remove(stack);
                     break;
                  }
               }

               if (!flag) {
                  return false;
               }
            }
         }
      }

      return list.isEmpty();
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

   @Override
   public int getRecipeSize() {
      return this.recipeItems.size();
   }

   @Override
   public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
      return this.recipeOutput.copy();
   }

   public ShapelessRecipes(ItemStack stack, List list) {
      this.recipeOutput = stack;
      this.recipeItems = list;
   }
}
