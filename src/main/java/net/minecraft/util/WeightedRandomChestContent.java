package net.minecraft.util;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;

public class WeightedRandomChestContent extends WeightedRandom.Item {
   private ItemStack theItemId;
   private int minStackSize;
   private int maxStackSize;

   public static void generateChestContents(Random rand, List list, IInventory inventory, int n) {
      for (int i = 0; i < n; i++) {
         WeightedRandomChestContent randomItem = (WeightedRandomChestContent)WeightedRandom.getRandomItem(rand, list);
         int n2 = randomItem.minStackSize + rand.nextInt(randomItem.maxStackSize - randomItem.minStackSize + 1);
         if (randomItem.theItemId.getMaxStackSize() >= n2) {
            ItemStack stack = randomItem.theItemId.copy();
            stack.stackSize = n2;
            inventory.setInventorySlotContents(rand.nextInt(inventory.getSizeInventory()), stack);
         } else {
            for (int j = 0; j < n2; j++) {
               ItemStack stack2 = randomItem.theItemId.copy();
               stack2.stackSize = 1;
               inventory.setInventorySlotContents(rand.nextInt(inventory.getSizeInventory()), stack2);
            }
         }
      }
   }

   public WeightedRandomChestContent(Item item, int n, int n2, int n3, int n4) {
      super(n4);
      this.theItemId = new ItemStack(item, 1, n);
      this.minStackSize = n2;
      this.maxStackSize = n3;
   }

   public static List combine(List list, WeightedRandomChestContent[] contents) {
      ArrayList list2 = Lists.newArrayList(list);
      Collections.addAll(list2, contents);
      return list2;
   }

   public WeightedRandomChestContent(ItemStack stack, int n, int n2, int n3) {
      super(n3);
      this.theItemId = stack;
      this.minStackSize = n;
      this.maxStackSize = n2;
   }

   public static void generateDispenserContents(Random rand, List list, TileEntityDispenser tileEntityDispenser, int n) {
      for (int i = 0; i < n; i++) {
         WeightedRandomChestContent randomItem = (WeightedRandomChestContent)WeightedRandom.getRandomItem(rand, list);
         int n2 = randomItem.minStackSize + rand.nextInt(randomItem.maxStackSize - randomItem.minStackSize + 1);
         if (randomItem.theItemId.getMaxStackSize() >= n2) {
            ItemStack stack = randomItem.theItemId.copy();
            stack.stackSize = n2;
            tileEntityDispenser.setInventorySlotContents(rand.nextInt(tileEntityDispenser.getSizeInventory()), stack);
         } else {
            for (int j = 0; j < n2; j++) {
               ItemStack stack2 = randomItem.theItemId.copy();
               stack2.stackSize = 1;
               tileEntityDispenser.setInventorySlotContents(rand.nextInt(tileEntityDispenser.getSizeInventory()), stack2);
            }
         }
      }
   }
}
