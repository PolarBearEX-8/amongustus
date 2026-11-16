package client.util;

import net.minecraft.inventory.SlotCraftingResult;

public class InventorySorter {
   public SlotCraftingResult slotCraftingResult;
   public int indexOffset;
   public int count = -1;

   public InventorySorter(int count2, int count3) {
      this.indexOffset = count2;
      this.slotCraftingResult = new SlotCraftingResult(count3);
   }
}
