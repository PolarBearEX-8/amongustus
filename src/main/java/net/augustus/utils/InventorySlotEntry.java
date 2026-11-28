package net.augustus.utils;

import net.augustus.utils.inventory.InventorySlotEntrySyntheticMarker;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class InventorySlotEntry {
   public final int slotIndex;
   public final ItemStack stack;
   public final Slot slot;

   private InventorySlotEntry(int n, Slot slot2, ItemStack stack2) {
      this.slotIndex = n;
      this.slot = slot2;
      this.stack = stack2;
   }

   public InventorySlotEntry(int n, Slot slot2, ItemStack stack2, InventorySlotEntrySyntheticMarker marker) {
      this(n, slot2, stack2);
   }
}
