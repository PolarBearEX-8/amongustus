package net.minecraft.inventory;

import client.event.ComplexListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IInventory extends ComplexListener {
   ItemStack getItemStack(int count, int count2);

   int processAction(int count);

   void processAction(EntityPlayer player);

   ItemStack getStackInSlot(int count);

   void processAction(int count, ItemStack stack);

   int processAction2();

   boolean processAction2(int count, ItemStack stack);

   int processAction3();

   void updateState2();

   int processAction4();

   boolean checkEntityPlayer(EntityPlayer player);

   void processAction(int count, int count2);

   void processAction2(EntityPlayer player);

   ItemStack processAction3(int count);

   void processAction5();
}
