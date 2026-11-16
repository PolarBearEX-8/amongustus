package net.minecraft.inventory;

import client.util.BezierTransformer;
import net.minecraft.item.ItemStack;

public class BezierTransformerSlot extends Slot {
   public final BezierTransformer bezierTransformer;

   @Override
   public int processAction2() {
      return 1;
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return true;
   }

   public BezierTransformerSlot(BezierTransformer bezierTransformer2, IInventory iInventory, int count, int count2, int count3) {
      super(iInventory, count, count2, count3);
      this.bezierTransformer = bezierTransformer2;
   }
}
