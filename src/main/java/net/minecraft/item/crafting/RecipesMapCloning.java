package net.minecraft.item.crafting;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecipesMapCloning implements IRecipe {
   @Override
   public int getRecipeSize() {
      return 9;
   }

   @Override
   public ItemStack getRecipeOutput() {
      return null;
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
   public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
      int n = 0;
      ItemStack stack = null;

      for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
         ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
         if (stackInSlot != null) {
            if (stackInSlot.getItem() == Items.filled_map) {
               if (stack != null) {
                  return null;
               }

               stack = stackInSlot;
            } else {
               if (stackInSlot.getItem() != Items.map) {
                  return null;
               }

               n++;
            }
         }
      }

      if (stack != null && n >= 1) {
         ItemStack stack2 = new ItemStack(Items.filled_map, n + 1, stack.getMetadata());
         if (stack.hasDisplayName()) {
            stack2.setStackDisplayName(stack.getDisplayName());
         }

         return stack2;
      } else {
         return null;
      }
   }

   @Override
   public boolean matches(InventoryCrafting inventoryCrafting, World world) {
      int n = 0;
      ItemStack stack = null;

      for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
         ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
         if (stackInSlot != null) {
            if (stackInSlot.getItem() == Items.filled_map) {
               if (stack != null) {
                  return false;
               }

               stack = stackInSlot;
            } else {
               if (stackInSlot.getItem() != Items.map) {
                  return false;
               }

               n++;
            }
         }
      }

      return stack != null && n > 0;
   }
}
