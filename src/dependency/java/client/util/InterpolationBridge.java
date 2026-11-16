package client.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class InterpolationBridge extends Container {
   public IInventory iInventory;

   public InterpolationBridge(IInventory iInventory2, IInventory iInventory3) {
      this.iInventory = iInventory3;

      for (int index = 0; index < 3; index++) {
         for (int index2 = 0; index2 < 3; index2++) {
            this.resolveSlot(new Slot(iInventory3, index2 + index * 3, 62 + index2 * 18, 17 + index * 18));
         }
      }

      for (int index3 = 0; index3 < 3; index3++) {
         for (int index4 = 0; index4 < 9; index4++) {
            this.resolveSlot(new Slot(iInventory2, index4 + index3 * 9 + 9, 8 + index4 * 18, 84 + index3 * 18));
         }
      }

      for (int index5 = 0; index5 < 9; index5++) {
         this.resolveSlot(new Slot(iInventory2, index5, 8 + index5 * 18, 142));
      }
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return this.iInventory.checkEntityPlayer(player);
   }

   @Override
   public ItemStack resolveItemStack(EntityPlayer player, int count) {
      ItemStack stack = null;
      Slot slot = (Slot)this.itemList2.get(count);
      if (slot != null && slot.isActive()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.getItemStack();
         if (count < 9) {
            if (!this.matchesItemStack(stack2, 9, 45, true)) {
               return null;
            }
         } else if (!this.matchesItemStack(stack2, 0, 9, false)) {
            return null;
         }

         if (stack2.animationsToGo == 0) {
            slot.putStack((ItemStack)null);
         } else {
            slot.onSlotChanged();
         }

         if (stack2.animationsToGo == stack.animationsToGo) {
            return null;
         }

         slot.processAction(player, stack2);
      }

      return stack;
   }
}
