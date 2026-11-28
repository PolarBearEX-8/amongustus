package net.augustus.modules.player.crafter;

import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.item.Item;

public class PickupIngredientStep implements CraftingStep {
   private final Item item;
   private final int requiredCount;

   public PickupIngredientStep(Item item2, int n) {
      this.item = item2;
      this.requiredCount = n;
   }

   public boolean execute(CraftingInventoryActions craftingInventoryActions, GuiCrafting guiCrafting) {
      int requiredCount2 = this.requiredCount;
      int n = craftingInventoryActions.findInventorySlot(guiCrafting, this.item, requiredCount2);
      return n < 0 ? false : craftingInventoryActions.pickupFromSlot(guiCrafting, n);
   }
}
