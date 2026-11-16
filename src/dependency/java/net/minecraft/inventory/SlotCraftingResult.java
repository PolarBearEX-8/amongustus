package net.minecraft.inventory;

import org.lwjgl.opengl.GL11;

public class SlotCraftingResult {
   public final int count;
   public boolean enabled = false;

   public static boolean checkSlotCraftingResult(SlotCraftingResult slotCraftingResult) {
      return slotCraftingResult.enabled;
   }

   public void updateState() {
      this.setState(true);
   }

   public void updateState2() {
      this.setState(false);
   }

   public void setState(boolean state) {
      if (state != this.enabled) {
         this.enabled = state;
         if (state) {
            GL11.glEnable(this.count);
         } else {
            GL11.glDisable(this.count);
         }
      }
   }

   public SlotCraftingResult(int count2) {
      this.count = count2;
   }
}
