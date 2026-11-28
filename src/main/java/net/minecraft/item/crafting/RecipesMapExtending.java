package net.minecraft.item.crafting;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class RecipesMapExtending extends ShapedRecipes {
   @Override
   public ItemStack getCraftingResult(InventoryCrafting inventoryCrafting) {
      ItemStack stack = null;

      for (int i = 0; i < inventoryCrafting.getSizeInventory() && stack == null; i++) {
         ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
         if (stackInSlot != null && stackInSlot.getItem() == Items.filled_map) {
            stack = stackInSlot;
         }
      }

      stack = stack.copy();
      stack.stackSize = 1;
      if (stack.getTagCompound() == null) {
         stack.setTagCompound(new NBTTagCompound());
      }

      stack.getTagCompound().setBoolean("map_is_scaling", true);
      return stack;
   }

   public RecipesMapExtending() {
      super(
         3,
         3,
         new ItemStack[]{
            new ItemStack(Items.paper),
            new ItemStack(Items.paper),
            new ItemStack(Items.paper),
            new ItemStack(Items.paper),
            new ItemStack(Items.filled_map, 0, 32767),
            new ItemStack(Items.paper),
            new ItemStack(Items.paper),
            new ItemStack(Items.paper),
            new ItemStack(Items.paper)
         },
         new ItemStack(Items.map, 0, 0)
      );
   }

   @Override
   public boolean matches(InventoryCrafting inventoryCrafting, World world) {
      if (!super.matches(inventoryCrafting, world)) {
         return false;
      } else {
         ItemStack stack = null;

         for (int i = 0; i < inventoryCrafting.getSizeInventory() && stack == null; i++) {
            ItemStack stackInSlot = inventoryCrafting.getStackInSlot(i);
            if (stackInSlot != null && stackInSlot.getItem() == Items.filled_map) {
               stack = stackInSlot;
            }
         }

         if (stack == null) {
            return false;
         } else {
            MapData mapData = Items.filled_map.getMapData(stack, world);
            return mapData == null ? false : mapData.scale < 4;
         }
      }
   }
}
