package net.augustus.modules.player.crafter;

import net.minecraft.client.gui.inventory.GuiCrafting;

public class PlaceIngredientStep implements CraftingStep {
   private final int craftingSlot;

   public PlaceIngredientStep(int n) {
      this.craftingSlot = n;
   }

   @Override
   public boolean execute(CraftingInventoryActions craftingInventoryActions, GuiCrafting guiCrafting) {
      return craftingInventoryActions.placeOneInSlot(guiCrafting, this.craftingSlot);
   }




}
