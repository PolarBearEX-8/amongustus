package net.augustus.modules.player.crafter;

import java.util.Map;
import net.minecraft.item.Item;

public class SimulatedCraftingInventory {
   private final Map totalCountByItem;
   private final Map slotCountByItem;
   private int emptySlotCount;

   public SimulatedCraftingInventory(CraftingInventorySnapshot snapshot) {
      this.totalCountByItem = snapshot.copyTotalCounts();
      this.slotCountByItem = snapshot.copySlotCounts();
      this.emptySlotCount = snapshot.getEmptySlotCount();
   }

   public int getItemCount(Item item) {
      return (Integer)(this.totalCountByItem.getOrDefault(item, 0));
   }

   public void consumeItem(Item item, int n) {
      int orDefault = (Integer)(this.totalCountByItem.getOrDefault(item, 0));
      int n2 = Math.max(0, orDefault - n);
      this.totalCountByItem.put(item, n2);
      if (n2 <= 0) {
         int orDefault2 = (Integer)(this.slotCountByItem.getOrDefault(item, 0));
         this.emptySlotCount += orDefault2;
         this.slotCountByItem.put(item, 0);
      } else {
         int itemStackLimit = item.getItemStackLimit();
         int n3 = (n2 + itemStackLimit - 1) / itemStackLimit;
         int orDefault3 = (Integer)(this.slotCountByItem.getOrDefault(item, 0));
         if (n3 < orDefault3) {
            this.emptySlotCount += orDefault3 - n3;
            this.slotCountByItem.put(item, n3);
         }
      }
   }

   public boolean canFitItem(Item item, int n) {
      int orDefault = (Integer)(this.totalCountByItem.getOrDefault(item, 0));
      int itemStackLimit = item.getItemStackLimit();
      int orDefault2 = (Integer)(this.slotCountByItem.getOrDefault(item, 0));
      int n4 = (((orDefault + n) + itemStackLimit - 1) / itemStackLimit) - orDefault2;
      return n4 <= 0 || this.emptySlotCount >= n4;
   }

   public void addItem(Item item, int n) {
      int orDefault = (Integer)(this.totalCountByItem.getOrDefault(item, 0));
      int itemStackLimit = item.getItemStackLimit();
      int orDefault2 = (Integer)(this.slotCountByItem.getOrDefault(item, 0));
      int n2 = orDefault + n;
      int n3 = (n2 + itemStackLimit - 1) / itemStackLimit;
      int n4 = n3 - orDefault2;
      if (n4 > 0) {
         this.emptySlotCount -= n4;
         this.slotCountByItem.put(item, n3);
      }

      this.totalCountByItem.put(item, n2);
   }
}
