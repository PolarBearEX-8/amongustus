package client.util;

import client.gui.ScrollableContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CurveMonitor extends Slot {
   public final Slot slot;
   public final ScrollableContainer scrollableContainer;

   @Override
   public String processAction() {
      return this.slot.processAction();
   }

   public static Slot resolveSlot(CurveMonitor curveMonitor) {
      return curveMonitor.slot;
   }

   @Override
   public void processAction(EntityPlayer player, ItemStack stack) {
      this.slot.processAction(player, stack);
   }

   @Override
   public int processAction2() {
      return this.slot.processAction2();
   }

   public CurveMonitor(ScrollableContainer scrollableContainer2, Slot slot2, int count) {
      super(slot2.iInventory, count, 0, 0);
      this.scrollableContainer = scrollableContainer2;
      this.slot = slot2;
   }

   @Override
   public void putStack(ItemStack stack) {
      this.slot.putStack(stack);
   }

   @Override
   public ItemStack decrStackSize(int count) {
      return this.slot.decrStackSize(count);
   }

   @Override
   public boolean isActive() {
      return this.slot.isActive();
   }

   @Override
   public boolean matchesIInventory(IInventory iInventory, int count) {
      return this.slot.matchesIInventory(iInventory, count);
   }

   @Override
   public int processAction3(ItemStack stack) {
      return this.slot.processAction3(stack);
   }

   @Override
   public void onSlotChanged() {
      this.slot.onSlotChanged();
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return this.slot.isItemValid(stack);
   }

   @Override
   public ItemStack getStack() {
      return this.slot.getStack();
   }
}
