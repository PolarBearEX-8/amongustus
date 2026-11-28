package net.minecraft.item.crafting;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class RecipeBookCloning implements IRecipe {
   @Override
   public int getRecipeSize() {
      return 9;
   }

   @Override
   public ItemStack[] getRemainingItems(InventoryCrafting inventoryCrafting) {
      ItemStack[] stacks = new ItemStack[inventoryCrafting.getSizeInventory()];

      for (int i = 0; i < stacks.length; i++) {
         ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
         if (stackInSlot != null && stackInSlot.getItem() instanceof ItemEditableBook) {
            stacks[i] = stackInSlot;
            break;
         }
      }

      return stacks;
   }

   @Override
   public ItemStack getRecipeOutput() {
      return null;
   }

   @Override
   public boolean matches(InventoryCrafting inventoryCrafting, World world) {
      int n = 0;
      ItemStack stack = null;

      for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
         ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
         if (stackInSlot != null) {
            if (stackInSlot.getItem() == Items.written_book) {
               if (stack != null) {
                  return false;
               }

               stack = stackInSlot;
            } else {
               if (stackInSlot.getItem() != Items.writable_book) {
                  return false;
               }

               n++;
            }
         }
      }

      return stack != null && n > 0;
   }

   @Override
   public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
      int n = 0;
      ItemStack stack = null;

      for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
         ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
         if (stackInSlot != null) {
            if (stackInSlot.getItem() == Items.written_book) {
               if (stack != null) {
                  return null;
               }

               stack = stackInSlot;
            } else {
               if (stackInSlot.getItem() != Items.writable_book) {
                  return null;
               }

               n++;
            }
         }
      }

      if (stack != null && n >= 1 && ItemEditableBook.getGeneration(stack) < 2) {
         ItemStack stack2 = new ItemStack(Items.written_book, n);
         stack2.setTagCompound((NBTTagCompound)stack.getTagCompound().copy());
         stack2.getTagCompound().setInteger("generation", ItemEditableBook.getGeneration(stack) + 1);
         if (stack.hasDisplayName()) {
            stack2.setStackDisplayName(stack.getDisplayName());
         }

         return stack2;
      } else {
         return null;
      }
   }
}
