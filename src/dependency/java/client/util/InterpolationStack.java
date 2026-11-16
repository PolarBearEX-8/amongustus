package client.util;

import client.gui.ScrollableContainer;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class InterpolationStack extends Container {
   public List list = Lists.newArrayList();

   @Override
   public boolean processAction(Slot slot) {
      return slot.iInventory instanceof InventoryPlayer || slot.indexOffset2 > 90 && slot.indexOffset <= 162;
   }

   public boolean size() {
      return this.list.size() > 45;
   }

   public void applyFloat(float factor) {
      int count = (this.list.size() + 9 - 1) / 9 - 5;
      int count2 = (int)((double)(factor * (float)count) + 0.5);
      if (count2 < 0) {
         count2 = 0;
      }

      for (int index = 0; index < 5; index++) {
         for (int index2 = 0; index2 < 9; index2++) {
            int count3 = index2 + (index + count2) * 9;
            if (count3 >= 0 && count3 < this.list.size()) {
               ScrollableContainer.getInventoryLargeChest().processAction(index2 + index * 9, (ItemStack)this.list.get(count3));
            } else {
               ScrollableContainer.getInventoryLargeChest().processAction(index2 + index * 9, (ItemStack)null);
            }
         }
      }
   }

   @Override
   public boolean processAction(ItemStack stack, Slot slot) {
      return slot.indexOffset2 > 90;
   }

   @Override
   public void processAction(int count, int count2, boolean flag, EntityPlayer player) {
   }

   public InterpolationStack(EntityPlayer player) {
      InventoryPlayer inventoryPlayer = player.inventoryPlayer;

      for (int index = 0; index < 5; index++) {
         for (int index2 = 0; index2 < 9; index2++) {
            this.resolveSlot(new Slot(ScrollableContainer.getInventoryLargeChest(), index * 9 + index2, 9 + index2 * 18, 18 + index * 18));
         }
      }

      for (int index3 = 0; index3 < 9; index3++) {
         this.resolveSlot(new Slot(inventoryPlayer, index3, 9 + index3 * 18, 112));
      }

      this.applyFloat(0.0F);
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return true;
   }

   @Override
   public ItemStack resolveItemStack(EntityPlayer player, int count) {
      if (count >= this.itemList2.size() - 9 && count < this.itemList2.size()) {
         Slot slot = (Slot)this.itemList2.get(count);
         if (slot != null && slot.isActive()) {
            slot.putStack((ItemStack)null);
         }
      }

      return null;
   }
}
