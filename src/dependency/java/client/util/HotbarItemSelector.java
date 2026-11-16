package client.util;

import net.minecraft.inventory.SlotCraftingResult;

public class HotbarItemSelector {
   public SlotCraftingResult slotCraftingResult = new SlotCraftingResult(3008);
   public int indexOffset = 519;
   public float scaleFactor = -1.0F;

   public HotbarItemSelector(DampenerAccumulator dampenerAccumulator) {
      this();
   }

   private HotbarItemSelector() {
   }
}
