package net.augustus.modules.player.crafter;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CraftingInventorySnapshot {
   private final Map slotCountByItem = new HashMap();
   private final Map totalCountByItem = new HashMap();
   private int emptySlotCount;

   private CraftingInventorySnapshot() {
   }

   public int getTotalCount(Item item) {
      return (Integer)(this.totalCountByItem.getOrDefault(item, 0));
   }

   public int getSlotCount(Item item) {
      return (Integer)(this.slotCountByItem.getOrDefault(item, 0));
   }

   public int getEmptySlotCount() {
      return this.emptySlotCount;
   }

   public Map copyTotalCounts() {
      return new HashMap(this.totalCountByItem);
   }

   public Map copySlotCounts() {
      return new HashMap(this.slotCountByItem);
   }

   public static CraftingInventorySnapshot capture(GuiCrafting guiCrafting) {
      CraftingInventorySnapshot snapshot = new CraftingInventorySnapshot();

      for (int i = 10; i < guiCrafting.inventorySlots.inventorySlots.size(); i++) {
         ItemStack stack = ((Slot)guiCrafting.inventorySlots.inventorySlots.get(i)).getStack();
         if (stack == null) {
            snapshot.emptySlotCount++;
         } else {
            Item item = stack.getItem();
            snapshot.slotCountByItem.merge(item, 1, (obj, obj2) -> Integer.sum((Integer)obj, (Integer)obj2));
            snapshot.totalCountByItem.merge(item, stack.stackSize, (obj3, obj4) -> Integer.sum((Integer)obj3, (Integer)obj4));
         }
      }

      return snapshot;
   }



}
