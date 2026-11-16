package net.minecraft.client.renderer;

import client.util.DampenerAccumulator;
import net.minecraft.inventory.SlotCraftingResult;

public class GlFogState {
   public SlotCraftingResult slotCraftingResult = new SlotCraftingResult(2912);
   public int mode = 2048;
   public float density = 1.0F;
   public float start = 0.0F;
   public float end = 1.0F;

   public GlFogState(DampenerAccumulator dampenerAccumulator) {
      this();
   }

   private GlFogState() {
   }
}
