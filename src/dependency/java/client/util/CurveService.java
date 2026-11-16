package client.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class CurveService extends Slot {
   public final InternalContainerAdapter internalContainerAdapter;

   @Override
   public boolean isItemValid(ItemStack stack) {
      return stack != null ? stack.getItem().matchesItemStackByItemStack(stack) : false;
   }

   @Override
   public int processAction2() {
      return 64;
   }

   public CurveService(InternalContainerAdapter internalContainerAdapter2, IInventory iInventory, int count, int count2, int count3) {
      super(iInventory, count, count2, count3);
      this.internalContainerAdapter = internalContainerAdapter2;
   }
}
