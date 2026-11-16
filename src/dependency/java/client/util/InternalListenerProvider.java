package client.util;

import client.event.PreMotionProcessor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class InternalListenerProvider implements PreMotionProcessor {
   public final int count2;
   public final int count;
   public final ItemStack[] itemStackArray;
   public final ItemStack itemStack;
   public boolean enabled;

   @Override
   public int size() {
      return this.count2 * this.count;
   }

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
   public ItemStack resolveItemStack(ContainerCraftingIInventory containerCraftingIInventory) {
      ItemStack stack = this.getItemStack().getItemStack();
      if (this.enabled) {
         for (int index = 0; index < containerCraftingIInventory.processAction4(); index++) {
            ItemStack stack2 = containerCraftingIInventory.processAction3(index);
            if (stack2 != null && stack2.canExecute()) {
               stack.processAction((NBTTagCompound)stack2.getStackTagCompound().getLISTManager());
            }
         }
      }

      return stack;
   }

   private boolean matchesContainerCraftingIInventor(ContainerCraftingIInventory containerCraftingIInventory, int count3, int count4, boolean flag) {
      for (int index = 0; index < 3; index++) {
         for (int index2 = 0; index2 < 3; index2++) {
            int count5 = index - count3;
            int count6 = index2 - count4;
            ItemStack stack = null;
            if (count5 >= 0 && count6 >= 0 && count5 < this.count2 && count6 < this.count) {
               if (flag) {
                  stack = this.itemStackArray[this.count2 - count5 - 1 + count6 * this.count2];
               } else {
                  stack = this.itemStackArray[count5 + count6 * this.count2];
               }
            }

            ItemStack stack2 = containerCraftingIInventory.resolveItemStack(index, index2);
            if (stack2 != null || stack != null) {
               if (stack2 == null && stack != null || stack2 != null && stack == null) {
                  return false;
               }

               if (stack.getItem() != stack2.getItem()) {
                  return false;
               }

               if (stack.getIndexOffset() != 32767 && stack.getIndexOffset() != stack2.getIndexOffset()) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   @Override
   public boolean matchesContainerCraftingIInventor(ContainerCraftingIInventory containerCraftingIInventory, World world) {
      for (int index = 0; index <= 3 - this.count2; index++) {
         for (int index2 = 0; index2 <= 3 - this.count; index2++) {
            if (this.matchesContainerCraftingIInventor(containerCraftingIInventory, index, index2, true)) {
               return true;
            }

            if (this.matchesContainerCraftingIInventor(containerCraftingIInventory, index, index2, false)) {
               return true;
            }
         }
      }

      return false;
   }

   @Override
   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public InternalListenerProvider(int count3, int count4, ItemStack[] stackArray, ItemStack stack) {
      this.count2 = count3;
      this.count = count4;
      this.itemStackArray = stackArray;
      this.itemStack = stack;
   }
}
