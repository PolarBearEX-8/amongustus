package net.augustus.modules.player.crafter;

import net.minecraft.item.Item;

public class RecipeIngredientSlot {
   public final int craftingSlot;
   public final Item item;

   public RecipeIngredientSlot(int n, Item item2) {
      this.craftingSlot = n;
      this.item = item2;
   }
}
