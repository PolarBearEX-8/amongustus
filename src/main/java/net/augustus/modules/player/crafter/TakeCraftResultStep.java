package net.augustus.modules.player.crafter;

import net.minecraft.client.gui.inventory.GuiCrafting;

public class TakeCraftResultStep implements CraftingStep {
   private final int resultSlot;

   public TakeCraftResultStep(int n) {
      this.resultSlot = n;
   }

   @Override
   public boolean execute(CraftingInventoryActions craftingInventoryActions, GuiCrafting guiCrafting) {
      return craftingInventoryActions.takeResultSlot(guiCrafting, this.resultSlot);
   }
}
