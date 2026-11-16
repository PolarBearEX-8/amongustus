package client.util;

import client.event.PreMotionProcessor;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class AuxiliaryListenerDispatcher implements PreMotionProcessor {
   public final ItemStack itemStack;
   public final List list;

   @Override
   public int size() {
      return this.list.size();
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
      return this.itemStack.getItemStack();
   }

   @Override
   public boolean matchesContainerCraftingIInventor(ContainerCraftingIInventory containerCraftingIInventory, World world) {
      ArrayList list2 = Lists.newArrayList(this.list);

      for (int index = 0; index < containerCraftingIInventory.getCount2(); index++) {
         for (int index2 = 0; index2 < containerCraftingIInventory.getCount(); index2++) {
            ItemStack stack = containerCraftingIInventory.resolveItemStack(index2, index);
            if (stack != null) {
               boolean flag = false;

               for (ItemStack stack2 : (Iterable<ItemStack>)(Iterable<?>)list2) {
                  if (stack.getItem() == stack2.getItem() && (stack2.getIndexOffset() == 32767 || stack.getIndexOffset() == stack2.getIndexOffset())) {
                     flag = true;
                     list2.remove(stack2);
                     break;
                  }
               }

               if (!flag) {
                  return false;
               }
            }
         }
      }

      return list2.isEmpty();
   }

   @Override
   public ItemStack getItemStack() {
      return this.itemStack;
   }

   public AuxiliaryListenerDispatcher(ItemStack stack, List list2) {
      this.itemStack = stack;
      this.list = list2;
   }
}
