package net.minecraft.client.renderer;

import client.util.DampenerAccumulator;
import net.minecraft.inventory.SlotCraftingResult;

public class GlBlendState {
   public SlotCraftingResult slotCraftingResult = new SlotCraftingResult(3042);
   public int dstFactor = 1;
   public int srcFactor = 0;
   public int count = 1;
   public int count2 = 0;

   private GlBlendState() {
   }

   public GlBlendState(DampenerAccumulator dampenerAccumulator) {
      this();
   }
}
