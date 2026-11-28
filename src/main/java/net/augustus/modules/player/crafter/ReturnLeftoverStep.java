package net.augustus.modules.player.crafter;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.item.Item;

public class ReturnLeftoverStep implements CraftingStep {
   public boolean execute(CraftingInventoryActions craftingInventoryActions, GuiCrafting guiCrafting) {
      if (!craftingInventoryActions.hasHeldCursorStack()) {
         return true;
      } else {
         int n = craftingInventoryActions.findInventorySlot(guiCrafting, (Item)null, (byte)0);
         return n < 0 ? false : craftingInventoryActions.pickupFromSlot(guiCrafting, n);
      }
   }
}
