package client.util;

import net.minecraft.inventory.SlotCraftingResult;

public class ItemWeightCalculator {
   public SlotCraftingResult slotCraftingResult = new SlotCraftingResult(3553);
   public int indexOffset = 0;

   private ItemWeightCalculator() {
   }

   public ItemWeightCalculator(DampenerAccumulator dampenerAccumulator) {
      this();
   }
}
