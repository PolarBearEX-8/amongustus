package net.augustus.modules.player.crafter;

import net.minecraft.client.gui.inventory.GuiCrafting;

public interface CraftingStep {
   boolean execute(CraftingInventoryActions craftingInventoryActions, GuiCrafting guiCrafting);
}
