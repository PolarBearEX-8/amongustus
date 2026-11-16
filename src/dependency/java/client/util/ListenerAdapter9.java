package client.util;

import client.event.PreMotionProcessor;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ListenerAdapter9 implements PreMotionProcessor {
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
      return 4;
   }

   @Override
   public ItemStack resolveItemStack(ContainerCraftingIInventory containerCraftingIInventory) {
      ArrayList list = Lists.newArrayList();

      for (int index = 0; index < containerCraftingIInventory.processAction4(); index++) {
         ItemStack stack = containerCraftingIInventory.processAction3(index);
         if (stack != null) {
            list.add(stack);
            if (list.size() > 1) {
               ItemStack stack2 = (ItemStack)list.get(0);
               if (stack.getItem() != stack2.getItem() || stack2.animationsToGo != 1 || stack.animationsToGo != 1 || !stack2.getItem().isActive()) {
                  return null;
               }
            }
         }
      }

      if (list.size() == 2) {
         ItemStack stack3 = (ItemStack)list.get(0);
         ItemStack stack4 = (ItemStack)list.get(1);
         if (stack3.getItem() == stack4.getItem() && stack3.animationsToGo == 1 && stack4.animationsToGo == 1 && stack3.getItem().isActive()) {
            Item item = stack3.getItem();
            int count = item.getIndexOffset2() - stack3.getIndexOffset2();
            int count2 = item.getIndexOffset2() - stack4.getIndexOffset2();
            int count3 = count + count2 + item.getIndexOffset2() * 5 / 100;
            int count4 = item.getIndexOffset2() - count3;
            if (count4 < 0) {
               count4 = 0;
            }

            return new ItemStack(stack3.getItem(), 1, count4);
         }
      }

      return null;
   }

   @Override
   public boolean matchesContainerCraftingIInventor(ContainerCraftingIInventory containerCraftingIInventory, World world) {
      ArrayList list = Lists.newArrayList();

      for (int index = 0; index < containerCraftingIInventory.processAction4(); index++) {
         ItemStack stack = containerCraftingIInventory.processAction3(index);
         if (stack != null) {
            list.add(stack);
            if (list.size() > 1) {
               ItemStack stack2 = (ItemStack)list.get(0);
               if (stack.getItem() != stack2.getItem() || stack2.animationsToGo != 1 || stack.animationsToGo != 1 || !stack2.getItem().isActive()) {
                  return false;
               }
            }
         }
      }

      return list.size() == 2;
   }

   @Override
   public ItemStack getItemStack() {
      return null;
   }
}
