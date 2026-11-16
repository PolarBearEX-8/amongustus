package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Slot {
   public final int indexOffset3;
   public final IInventory iInventory;
   public int indexOffset1;
   public int indexOffset;
   public int indexOffset2;

   public String processAction() {
      return null;
   }

   public boolean processAction(EntityPlayer player) {
      return true;
   }

   public int processAction2() {
      return this.iInventory.processAction2();
   }

   public void processAction(ItemStack stack, int count) {
   }

   public void applyItemStack(ItemStack stack, ItemStack stack2) {
      if (stack != null && stack2 != null && stack.getItem() == stack2.getItem()) {
         int count = stack2.animationsToGo - stack.animationsToGo;
         if (count > 0) {
            this.processAction(stack, count);
         }
      }
   }

   public void onSlotChanged() {
      this.iInventory.processAction5();
   }

   public void processAction(EntityPlayer player, ItemStack stack) {
      this.onSlotChanged();
   }

   public ItemStack getStack() {
      return this.iInventory.processAction3(this.indexOffset3);
   }

   public boolean getHasStack() {
      return true;
   }

   public ItemStack decrStackSize(int count) {
      return this.iInventory.getItemStack(this.indexOffset3, count);
   }

   public boolean isItemValid(ItemStack stack) {
      return true;
   }

   public void putStack(ItemStack stack) {
      this.iInventory.processAction(this.indexOffset3, stack);
      this.onSlotChanged();
   }

   public boolean matchesIInventory(IInventory iInventory2, int count) {
      return iInventory2 == this.iInventory && count == this.indexOffset3;
   }

   public boolean isActive() {
      return this.getStack() != null;
   }

   public int processAction3(ItemStack stack) {
      return this.processAction2();
   }

   public void processAction4(ItemStack stack) {
   }

   public Slot(IInventory iInventory2, int count, int count2, int count3) {
      this.iInventory = iInventory2;
      this.indexOffset3 = count;
      this.indexOffset = count2;
      this.indexOffset2 = count3;
   }
}
