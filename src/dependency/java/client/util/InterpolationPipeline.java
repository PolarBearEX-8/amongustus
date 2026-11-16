package client.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class InterpolationPipeline extends Container {
   public final IInventory iInventory;

   @Override
   public void applyEntityPlayer(EntityPlayer player) {
      super.applyEntityPlayer(player);
      this.iInventory.processAction(player);
   }

   @Override
   public ItemStack resolveItemStack(EntityPlayer player, int count) {
      ItemStack stack = null;
      Slot slot = (Slot)this.itemList2.get(count);
      if (slot != null && slot.isActive()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.getItemStack();
         if (count < this.iInventory.processAction4()) {
            if (!this.matchesItemStack(stack2, this.iInventory.processAction4(), this.itemList2.size(), true)) {
               return null;
            }
         } else if (!this.matchesItemStack(stack2, 0, this.iInventory.processAction4(), false)) {
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

   public InterpolationPipeline(InventoryPlayer inventoryPlayer, IInventory iInventory2, EntityPlayer player) {
      this.iInventory = iInventory2;
      iInventory2.processAction2(player);

      for (int index = 0; index < iInventory2.processAction4(); index++) {
         this.resolveSlot(new Slot(iInventory2, index, 44 + index * 18, 20));
      }

      for (int index2 = 0; index2 < 3; index2++) {
         for (int index3 = 0; index3 < 9; index3++) {
            this.resolveSlot(new Slot(inventoryPlayer, index3 + index2 * 9 + 9, 8 + index3 * 18, index2 * 18 + 51));
         }
      }

      for (int index4 = 0; index4 < 9; index4++) {
         this.resolveSlot(new Slot(inventoryPlayer, index4, 8 + index4 * 18, 109));
      }
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return this.iInventory.checkEntityPlayer(player);
   }
}
