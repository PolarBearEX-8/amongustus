package client.util;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityTrap;

public class ClientManagerAdapter2 extends DampenerSorter {
   public ItemStack itemStack;
   public int count2;
   public int count3;

   public ClientManagerAdapter2(ItemStack stack, int count, int count4, int count5) {
      super(count5);
      this.itemStack = stack;
      this.count2 = count;
      this.count3 = count4;
   }

   public ClientManagerAdapter2(Item item, int count, int count4, int count5, int count6) {
      super(count6);
      this.itemStack = new ItemStack(item, 1, count);
      this.count2 = count4;
      this.count3 = count5;
   }

   public static void applyRandom(Random random, List list, IInventory iInventory, int count) {
      for (int index = 0; index < count; index++) {
         ClientManagerAdapter2 clientManagerAdapter = (ClientManagerAdapter2)SmoothSystem.resolveDampenerSorter(random, list);
         int count4 = clientManagerAdapter.count2 + random.nextInt(clientManagerAdapter.count3 - clientManagerAdapter.count2 + 1);
         if (clientManagerAdapter.itemStack.processAction5() >= count4) {
            ItemStack stack = clientManagerAdapter.itemStack.getItemStack();
            stack.animationsToGo = count4;
            iInventory.processAction(random.nextInt(iInventory.processAction4()), stack);
         } else {
            for (int index2 = 0; index2 < count4; index2++) {
               ItemStack stack2 = clientManagerAdapter.itemStack.getItemStack();
               stack2.animationsToGo = 1;
               iInventory.processAction(random.nextInt(iInventory.processAction4()), stack2);
            }
         }
      }
   }

   public static List resolveList(List list, ClientManagerAdapter2[] clientManagerAdapter2Array) {
      ArrayList list2 = Lists.newArrayList(list);
      Collections.addAll(list2, clientManagerAdapter2Array);
      return list2;
   }

   public static void applyRandom(Random random, List list, TileEntityTrap tileEntityTrap, int count) {
      for (int index = 0; index < count; index++) {
         ClientManagerAdapter2 clientManagerAdapter = (ClientManagerAdapter2)SmoothSystem.resolveDampenerSorter(random, list);
         int count4 = clientManagerAdapter.count2 + random.nextInt(clientManagerAdapter.count3 - clientManagerAdapter.count2 + 1);
         if (clientManagerAdapter.itemStack.processAction5() >= count4) {
            ItemStack stack = clientManagerAdapter.itemStack.getItemStack();
            stack.animationsToGo = count4;
            tileEntityTrap.processAction(random.nextInt(tileEntityTrap.processAction4()), stack);
         } else {
            for (int index2 = 0; index2 < count4; index2++) {
               ItemStack stack2 = clientManagerAdapter.itemStack.getItemStack();
               stack2.animationsToGo = 1;
               tileEntityTrap.processAction(random.nextInt(tileEntityTrap.processAction4()), stack2);
            }
         }
      }
   }
}
