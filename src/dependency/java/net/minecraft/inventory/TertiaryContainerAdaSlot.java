package net.minecraft.inventory;

import client.util.TertiaryContainerAdapter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class TertiaryContainerAdaSlot extends Slot {
   public final TertiaryContainerAdapter tertiaryContainerAdapter;

   @Override
   public boolean isItemValid(ItemStack stack) {
      return super.isItemValid(stack) && stack.getItem() == Items.saddle && !this.isActive();
   }

   public TertiaryContainerAdaSlot(TertiaryContainerAdapter tertiaryContainerAdapter2, IInventory iInventory, int count, int count2, int count3) {
      super(iInventory, count, count2, count3);
      this.tertiaryContainerAdapter = tertiaryContainerAdapter2;
   }
}
