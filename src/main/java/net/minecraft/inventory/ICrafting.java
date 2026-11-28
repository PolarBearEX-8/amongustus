package net.minecraft.inventory;

import java.util.List;
import net.minecraft.item.ItemStack;

public interface ICrafting {
   void sendProgressBarUpdate(Container container, int n, int n2);

   void sendSlotContents(Container container, int n, ItemStack stack);

   void sendAllWindowProperties(Container container, IInventory inventory);

   void updateCraftingInventory(Container container, List list);
}
