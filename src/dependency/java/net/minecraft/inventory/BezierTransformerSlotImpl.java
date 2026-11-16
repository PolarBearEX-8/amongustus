package net.minecraft.inventory;

import client.setting.LocalOptionType;
import client.util.BezierTransformer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class BezierTransformerSlotImpl extends Slot {
   public final BezierTransformer bezierTransformer;

   public BezierTransformerSlotImpl(BezierTransformer bezierTransformer2, IInventory iInventory, int count, int count2, int count3) {
      super(iInventory, count, count2, count3);
      this.bezierTransformer = bezierTransformer2;
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return stack.getItem() == Items.dye && LocalOptionType.resolveLocalOptionTypeByIndex(stack.getIndexOffset()) == LocalOptionType.BLUE;
   }
}
