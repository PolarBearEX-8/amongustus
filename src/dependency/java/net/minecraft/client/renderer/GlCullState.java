package net.minecraft.client.renderer;

import client.util.DampenerAccumulator;
import net.minecraft.inventory.SlotCraftingResult;

public class GlCullState {
   public SlotCraftingResult slotCraftingResult = new SlotCraftingResult(2884);
   public int mode = 1029;

   public GlCullState(DampenerAccumulator dampenerAccumulator) {
      this();
   }

   private GlCullState() {
   }
}
