package client.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class AdvancedContainerAdapter extends Container {
   public IInventory iInventory;
   public int count;

   @Override
   public void applyEntityPlayer(EntityPlayer player) {
      super.applyEntityPlayer(player);
      this.iInventory.processAction(player);
   }

   @Override
   public ItemStack resolveItemStack(EntityPlayer player, int count2) {
      ItemStack stack = null;
      Slot slot = (Slot)this.itemList2.get(count2);
      if (slot != null && slot.isActive()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.getItemStack();
         if (count2 < this.count * 9) {
            if (!this.matchesItemStack(stack2, this.count * 9, this.itemList2.size(), true)) {
               return null;
            }
         } else if (!this.matchesItemStack(stack2, 0, this.count * 9, false)) {
            return null;
         }

         if (stack2.animationsToGo == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }
      }

      return stack;
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return this.iInventory.checkEntityPlayer(player);
   }

   public IInventory getIInventory() {
      return this.iInventory;
   }

   public AdvancedContainerAdapter(IInventory iInventory2, IInventory iInventory3, EntityPlayer player) {
      this.iInventory = iInventory3;
      this.count = iInventory3.processAction4() / 9;
      iInventory3.processAction2(player);
      int count2 = (this.count - 4) * 18;

      for (int index = 0; index < this.count; index++) {
         for (int index2 = 0; index2 < 9; index2++) {
            this.resolveSlot(new Slot(iInventory3, index2 + index * 9, 8 + index2 * 18, 18 + index * 18));
         }
      }

      for (int index3 = 0; index3 < 3; index3++) {
         for (int index4 = 0; index4 < 9; index4++) {
            this.resolveSlot(new Slot(iInventory2, index4 + index3 * 9 + 9, 8 + index4 * 18, 103 + index3 * 18 + count2));
         }
      }

      for (int index5 = 0; index5 < 9; index5++) {
         this.resolveSlot(new Slot(iInventory2, index5, 8 + index5 * 18, 161 + count2));
      }
   }
}
