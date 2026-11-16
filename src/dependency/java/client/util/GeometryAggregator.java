package client.util;

import client.event.PreMotionProcessor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntityBanner;
import net.minecraft.world.World;

public class GeometryAggregator implements PreMotionProcessor {
   @Override
   public ItemStack[] resolveItemStackArray(ContainerCraftingIInventory containerCraftingIInventory) {
      ItemStack[] stackArray = new ItemStack[containerCraftingIInventory.processAction4()];

      for (int index = 0; index < stackArray.length; index++) {
         ItemStack stack = containerCraftingIInventory.processAction3(index);
         if (stack != null) {
            if (stack.getItem().isActiveAsFlag()) {
               stackArray[index] = new ItemStack(stack.getItem().getItem());
            } else if (stack.canExecute() && TileEntityBanner.computeCount(stack) > 0) {
               stackArray[index] = stack.getItemStack();
               stackArray[index].animationsToGo = 1;
            }
         }
      }

      return stackArray;
   }

   @Override
   public int size() {
      return 2;
   }

   @Override
   public ItemStack resolveItemStack(ContainerCraftingIInventory containerCraftingIInventory) {
      for (int index = 0; index < containerCraftingIInventory.processAction4(); index++) {
         ItemStack stack = containerCraftingIInventory.processAction3(index);
         if (stack != null && TileEntityBanner.computeCount(stack) > 0) {
            ItemStack stack2 = stack.getItemStack();
            stack2.animationsToGo = 1;
            return stack2;
         }
      }

      return null;
   }

   @Override
   public boolean matchesContainerCraftingIInventor(ContainerCraftingIInventory containerCraftingIInventory, World world) {
      ItemStack stack = null;
      ItemStack stack2 = null;

      for (int index = 0; index < containerCraftingIInventory.processAction4(); index++) {
         ItemStack stack3 = containerCraftingIInventory.processAction3(index);
         if (stack3 != null) {
            if (stack3.getItem() != Items.banner) {
               return false;
            }

            if (stack != null && stack2 != null) {
               return false;
            }

            int count = TileEntityBanner.computeCountByItemStack(stack3);
            boolean flag = TileEntityBanner.computeCount(stack3) > 0;
            if (stack != null) {
               if (flag) {
                  return false;
               }

               if (count != TileEntityBanner.computeCountByItemStack(stack)) {
                  return false;
               }

               stack2 = stack3;
            } else if (stack2 != null) {
               if (!flag) {
                  return false;
               }

               if (count != TileEntityBanner.computeCountByItemStack(stack2)) {
                  return false;
               }

               stack = stack3;
            } else if (flag) {
               stack = stack3;
            } else {
               stack2 = stack3;
            }
         }
      }

      return stack != null && stack2 != null;
   }

   @Override
   public ItemStack getItemStack() {
      return null;
   }

   public GeometryAggregator(StandardCustom standardCustom) {
      this();
   }

   private GeometryAggregator() {
   }
}
