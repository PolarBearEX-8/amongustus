package client.event;

import client.util.ContainerCraftingIInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface PreMotionProcessor {
   int size();

   ItemStack[] resolveItemStackArray(ContainerCraftingIInventory containerCraftingIInventory);

   ItemStack resolveItemStack(ContainerCraftingIInventory containerCraftingIInventory);

   boolean matchesContainerCraftingIInventor(ContainerCraftingIInventory containerCraftingIInventory, World world);

   ItemStack getItemStack();
}
