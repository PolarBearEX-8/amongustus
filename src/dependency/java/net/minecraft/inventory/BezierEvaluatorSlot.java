package net.minecraft.inventory;

import client.util.BezierEvaluator;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BezierEvaluatorSlot extends Slot {
   public final BezierEvaluator bezierEvaluator;

   @Override
   public boolean isItemValid(ItemStack stack) {
      return stack == null
         ? false
         : stack.getItem() == Items.emerald || stack.getItem() == Items.diamond || stack.getItem() == Items.goldIngot || stack.getItem() == Items.ironIngot;
   }

   public BezierEvaluatorSlot(BezierEvaluator bezierEvaluator2, IInventory iInventory, int count, int count2, int count3) {
      super(iInventory, count, count2, count3);
      this.bezierEvaluator = bezierEvaluator2;
   }

   @Override
   public int processAction2() {
      return 1;
   }
}
