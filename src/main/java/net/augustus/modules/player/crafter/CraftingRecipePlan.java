package net.augustus.modules.player.crafter;

import java.util.List;
import net.minecraft.item.ItemStack;

public class CraftingRecipePlan {
   public final ItemStack output;
   public final List ingredientSlots;

   public CraftingRecipePlan(ItemStack stack, List list) {
      this.output = stack;
      this.ingredientSlots = list;
   }
}
