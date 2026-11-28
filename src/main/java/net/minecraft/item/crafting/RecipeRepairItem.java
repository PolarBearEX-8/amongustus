package net.minecraft.item.crafting;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecipeRepairItem implements IRecipe {
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
   public ItemStack getRecipeOutput() {
      return null;
   }

   @Override
   public boolean matches(InventoryCrafting inventoryCrafting, World world) {
      ArrayList list = Lists.newArrayList();

      for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
         ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
         if (stackInSlot != null) {
            list.add(stackInSlot);
            if (list.size() > 1) {
               ItemStack stack = (ItemStack)list.get(0);
               if (stackInSlot.getItem() != stack.getItem() || stack.stackSize != 1 || stackInSlot.stackSize != 1 || !stack.getItem().isDamageable()) {
                  return false;
               }
            }
         }
      }

      return list.size() == 2;
   }

   @Override
   public int getRecipeSize() {
      return 4;
   }

   @Override
   public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
      ArrayList list = Lists.newArrayList();

      for (int i = 0; i < inventoryCrafting.getSizeInventory(); i++) {
         ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
         if (stackInSlot != null) {
            list.add(stackInSlot);
            if (list.size() > 1) {
               ItemStack stack = (ItemStack)list.get(0);
               if (stackInSlot.getItem() != stack.getItem() || stack.stackSize != 1 || stackInSlot.stackSize != 1 || !stack.getItem().isDamageable()) {
                  return null;
               }
            }
         }
      }

      if (list.size() == 2) {
         ItemStack stack2 = (ItemStack)list.get(0);
         ItemStack stack3 = (ItemStack)list.get(1);
         if (stack2.getItem() == stack3.getItem() && stack2.stackSize == 1 && stack3.stackSize == 1 && stack2.getItem().isDamageable()) {
            Item item = stack2.getItem();
            int n3 = (item.getMaxDamage() - stack2.getItemDamage()) + (item.getMaxDamage() - stack3.getItemDamage()) + item.getMaxDamage() * 5 / 100;
            int n4 = item.getMaxDamage() - n3;
            if (n4 < 0) {
               n4 = 0;
            }

            return new ItemStack(stack2.getItem(), 1, n4);
         }
      }

      return null;
   }
}
