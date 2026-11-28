package net.minecraft.item.crafting;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IRecipe {
   ItemStack[] getRemainingItems(InventoryCrafting inventoryCrafting);

   int getRecipeSize();

   ItemStack getRecipeOutput();

   ItemStack getCraftingResult(InventoryCrafting inventoryCrafting);

   boolean matches(InventoryCrafting inventoryCrafting, World world);
}
