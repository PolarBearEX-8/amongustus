package client.util;

import client.event.PreMotionProcessor;
import client.setting.CrafterLeatherType;
import client.setting.LocalOptionType;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import net.minecraft.entity.EntitySheep;
import net.minecraft.item.ItemGoldenBoots;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class ExternalClientManager implements PreMotionProcessor {
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
      return 10;
   }

   @Override
   public ItemStack resolveItemStack(ContainerCraftingIInventory containerCraftingIInventory) {
      ItemStack stack = null;
      int[] countArray = new int[3];
      int count = 0;
      int count2 = 0;
      ItemGoldenBoots itemGoldenBoots = null;

      for (int index = 0; index < containerCraftingIInventory.processAction4(); index++) {
         ItemStack stack2 = containerCraftingIInventory.processAction3(index);
         if (stack2 != null) {
            if (stack2.getItem() instanceof ItemGoldenBoots) {
               itemGoldenBoots = (ItemGoldenBoots)stack2.getItem();
               if (itemGoldenBoots.getCrafterLeatherType() != CrafterLeatherType.LEATHER || stack != null) {
                  return null;
               }

               stack = stack2.getItemStack();
               stack.animationsToGo = 1;
               if (itemGoldenBoots.matchesItemStack2(stack2)) {
                  int count3 = itemGoldenBoots.computeCount(stack);
                  float factor = (float)(count3 >> 16 & 0xFF) / 255.0F;
                  float factor2 = (float)(count3 >> 8 & 0xFF) / 255.0F;
                  float factor3 = (float)(count3 & 0xFF) / 255.0F;
                  count = (int)((float)count + Math.max(factor, Math.max(factor2, factor3)) * 255.0F);
                  countArray[0] = (int)((float)countArray[0] + factor * 255.0F);
                  countArray[1] = (int)((float)countArray[1] + factor2 * 255.0F);
                  countArray[2] = (int)((float)countArray[2] + factor3 * 255.0F);
                  count2++;
               }
            } else {
               if (stack2.getItem() != Items.dye) {
                  return null;
               }

               float[] factorArray = EntitySheep.get(LocalOptionType.resolveLocalOptionTypeByIndex(stack2.getIndexOffset()));
               int count4 = (int)(factorArray[0] * 255.0F);
               int count5 = (int)(factorArray[1] * 255.0F);
               int count6 = (int)(factorArray[2] * 255.0F);
               count += Math.max(count4, Math.max(count5, count6));
               countArray[0] += count4;
               countArray[1] += count5;
               countArray[2] += count6;
               count2++;
            }
         }
      }

      if (itemGoldenBoots == null) {
         return null;
      } else {
         int count7 = countArray[0] / count2;
         int count8 = countArray[1] / count2;
         int count9 = countArray[2] / count2;
         float factor4 = (float)count / (float)count2;
         float factor5 = (float)Math.max(count7, Math.max(count8, count9));
         int count10 = (int)((float)count7 * factor4 / factor5);
         int count11 = (int)((float)count8 * factor4 / factor5);
         int count12 = (int)((float)count9 * factor4 / factor5);
         int count13 = ((count10 << 8) + count11 << 8) + count12;
         itemGoldenBoots.applyItemStack(stack, count13);
         return stack;
      }
   }

   @Override
   public boolean matchesContainerCraftingIInventor(ContainerCraftingIInventory containerCraftingIInventory, World world) {
      ItemStack stack = null;
      ArrayList list = Lists.newArrayList();

      for (int index = 0; index < containerCraftingIInventory.processAction4(); index++) {
         ItemStack stack2 = containerCraftingIInventory.processAction3(index);
         if (stack2 != null) {
            if (stack2.getItem() instanceof ItemGoldenBoots) {
               if (((ItemGoldenBoots)stack2.getItem()).getCrafterLeatherType() != CrafterLeatherType.LEATHER || stack != null) {
                  return false;
               }

               stack = stack2;
            } else {
               if (stack2.getItem() != Items.dye) {
                  return false;
               }

               list.add(stack2);
            }
         }
      }

      return stack != null && !list.isEmpty();
   }

   @Override
   public ItemStack getItemStack() {
      return null;
   }
}
