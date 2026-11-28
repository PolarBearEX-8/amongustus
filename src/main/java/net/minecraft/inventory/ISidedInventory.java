package net.minecraft.inventory;

import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public interface ISidedInventory extends IInventory {
   boolean canInsertItem(int n, ItemStack stack, EnumFacing facing);

   boolean canExtractItem(int n, ItemStack stack, EnumFacing facing);

   int[] getSlotsForFace(EnumFacing facing);
}
