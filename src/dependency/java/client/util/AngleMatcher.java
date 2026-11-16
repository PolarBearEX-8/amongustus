package client.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntityFurnace;

public class AngleMatcher extends Slot {
   public static boolean matchesItemStack(ItemStack stack) {
      return stack != null && stack.getItem() != null && stack.getItem() == Items.bucket;
   }

   public AngleMatcher(IInventory iInventory, int count, int count2, int count3) {
      super(iInventory, count, count2, count3);
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return TileEntityFurnace.matchesItemStack(stack) || matchesItemStack(stack);
   }

   @Override
   public int processAction3(ItemStack stack) {
      return matchesItemStack(stack) ? 1 : super.processAction3(stack);
   }
}
