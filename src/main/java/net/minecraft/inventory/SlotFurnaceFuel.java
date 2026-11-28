package net.minecraft.inventory;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class SlotFurnaceFuel extends Slot {
   @Override
   public boolean isItemValid(ItemStack stack) {
      return TileEntityFurnace.isItemFuel(stack) || isBucket(stack);
   }

   public static boolean isBucket(ItemStack stack) {
      return stack != null && stack.getItem() != null && stack.getItem() == Items.bucket;
   }

   @Override
   public int getItemStackLimit(ItemStack stack) {
      return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
   }

   public SlotFurnaceFuel(IInventory inventory2, int n, int n2, int n3) {
      super(inventory2, n, n2, n3);
   }
}
