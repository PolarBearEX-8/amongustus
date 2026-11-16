package client.util;

import client.event.PreMotionProcessor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class ExtendedListenerProvider implements PreMotionProcessor {
   @Override
   public ItemStack[] resolveItemStackArray(ContainerCraftingIInventory containerCraftingIInventory) {
      ItemStack[] stackArray = new ItemStack[containerCraftingIInventory.processAction4()];

      for (int index = 0; index < stackArray.length; index++) {
         ItemStack stack = containerCraftingIInventory.processAction3(index);
         if (stack != null && stack.getItem().isActiveAsFlag()) {
            stackArray[index] = new ItemStack(stack.getItem().getItem());
         }
      }

      return stackArray;
   }

   @Override
   public int size() {
      return 9;
   }

   @Override
   public ItemStack resolveItemStack(ContainerCraftingIInventory containerCraftingIInventory) {
      int count = 0;
      ItemStack stack = null;

      for (int index = 0; index < containerCraftingIInventory.processAction4(); index++) {
         ItemStack stack2 = containerCraftingIInventory.processAction3(index);
         if (stack2 != null) {
            if (stack2.getItem() == Items.filledMap) {
               if (stack != null) {
                  return null;
               }

               stack = stack2;
            } else {
               if (stack2.getItem() != Items.map) {
                  return null;
               }

               count++;
            }
         }
      }

      if (stack != null && count >= 1) {
         ItemStack stack3 = new ItemStack(Items.filledMap, count + 1, stack.getIndexOffset());
         if (stack.isActive()) {
            stack3.resolveItemStack(stack.getString());
         }

         return stack3;
      } else {
         return null;
      }
   }

   @Override
   public boolean matchesContainerCraftingIInventor(ContainerCraftingIInventory containerCraftingIInventory, World world) {
      int count = 0;
      ItemStack stack = null;

      for (int index = 0; index < containerCraftingIInventory.processAction4(); index++) {
         ItemStack stack2 = containerCraftingIInventory.processAction3(index);
         if (stack2 != null) {
            if (stack2.getItem() == Items.filledMap) {
               if (stack != null) {
                  return false;
               }

               stack = stack2;
            } else {
               if (stack2.getItem() != Items.map) {
                  return false;
               }

               count++;
            }
         }
      }

      return stack != null && count > 0;
   }

   @Override
   public ItemStack getItemStack() {
      return null;
   }
}
