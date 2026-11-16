package net.minecraft.client.renderer;

import client.util.DampenerAccumulator;
import net.minecraft.inventory.SlotCraftingResult;

public class GlColorMaterialState {
   public SlotCraftingResult slotCraftingResult = new SlotCraftingResult(2903);
   public int face = 1032;
   public int mode = 5634;

   private GlColorMaterialState() {
   }

   public GlColorMaterialState(DampenerAccumulator dampenerAccumulator) {
      this();
   }
}
