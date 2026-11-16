package client.util;

import net.minecraft.inventory.SlotCraftingResult;

public class ItemFilter {
   public SlotCraftingResult slotCraftingResult = new SlotCraftingResult(2929);
   public boolean state = true;
   public int indexOffset = 513;

   public ItemFilter(DampenerAccumulator dampenerAccumulator) {
      this();
   }

   private ItemFilter() {
   }
}
