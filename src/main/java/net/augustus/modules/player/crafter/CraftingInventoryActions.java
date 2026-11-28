package net.augustus.modules.player.crafter;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.item.Item;

public interface CraftingInventoryActions {
   boolean pickupFromSlot(GuiCrafting guiCrafting, int n);

   boolean placeOneInSlot(GuiCrafting guiCrafting, int n);

   boolean takeResultSlot(GuiCrafting guiCrafting, int n);

   int findInventorySlot(GuiCrafting guiCrafting, Item item, int n);

   boolean hasHeldCursorStack();
}
