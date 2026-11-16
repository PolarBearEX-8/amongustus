package net.minecraft.client.renderer;

import client.util.DampenerAccumulator;
import net.minecraft.inventory.SlotCraftingResult;

public class GlColorLogicState {
   public SlotCraftingResult slotCraftingResult = new SlotCraftingResult(3058);
   public int logicOp = 5379;

   public GlColorLogicState(DampenerAccumulator dampenerAccumulator) {
      this();
   }

   private GlColorLogicState() {
   }
}
