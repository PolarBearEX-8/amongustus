package client.event;

import client.util.ContainerCraftingIInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemWrittenBook;
import net.minecraft.item.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class GenerationListener implements PreMotionProcessor {
   @Override
   public ItemStack[] resolveItemStackArray(ContainerCraftingIInventory containerCraftingIInventory) {
      ItemStack[] stackArray = new ItemStack[containerCraftingIInventory.processAction4()];

      for (int index = 0; index < stackArray.length; index++) {
         ItemStack stack = containerCraftingIInventory.processAction3(index);
         if (stack != null && stack.getItem() instanceof ItemWrittenBook) {
            stackArray[index] = stack;
            break;
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
            if (stack2.getItem() == Items.writtenBook) {
               if (stack != null) {
                  return null;
               }

               stack = stack2;
            } else {
               if (stack2.getItem() != Items.writableBook) {
                  return null;
               }

               count++;
            }
         }
      }

      if (stack != null && count >= 1 && ItemWrittenBook.computeCount(stack) < 2) {
         ItemStack stack3 = new ItemStack(Items.writtenBook, count);
         stack3.processAction((NBTTagCompound)stack.getStackTagCompound().getLISTManager());
         stack3.getStackTagCompound().put("generation", ItemWrittenBook.computeCount(stack) + 1);
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
            if (stack2.getItem() == Items.writtenBook) {
               if (stack != null) {
                  return false;
               }

               stack = stack2;
            } else {
               if (stack2.getItem() != Items.writableBook) {
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
