package client.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public interface AuxiliaryIInventoryExtension extends IInventory {
   boolean matchesItemStackByItemStack(int count, ItemStack stack, EnumFacing enumFacing);

   int[] resolveIndexArray(EnumFacing enumFacing);

   boolean matchesItemStack(int count, ItemStack stack, EnumFacing enumFacing);
}
