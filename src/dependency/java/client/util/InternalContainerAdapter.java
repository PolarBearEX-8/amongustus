package client.util;

import client.event.CoreListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class InternalContainerAdapter extends Container {
   public IInventory iInventory;
   public final Slot slot;
   public int count;

   @Override
   public ItemStack resolveItemStack(EntityPlayer player, int count2) {
      ItemStack stack = null;
      Slot slot2 = (Slot)this.itemList2.get(count2);
      if (slot2 != null && slot2.isActive()) {
         ItemStack stack2 = slot2.getStack();
         stack = stack2.getItemStack();
         if ((count2 < 0 || count2 > 2) && count2 != 3) {
            if (!this.slot.isActive() && this.slot.isItemValid(stack2)) {
               if (!this.matchesItemStack(stack2, 3, 4, false)) {
                  return null;
               }
            } else if (CurveProvider.matchesItemStack(stack)) {
               if (!this.matchesItemStack(stack2, 0, 3, false)) {
                  return null;
               }
            } else if (count2 >= 4 && count2 < 31) {
               if (!this.matchesItemStack(stack2, 31, 40, false)) {
                  return null;
               }
            } else if (count2 >= 31 && count2 < 40) {
               if (!this.matchesItemStack(stack2, 4, 31, false)) {
                  return null;
               }
            } else if (!this.matchesItemStack(stack2, 4, 40, false)) {
               return null;
            }
         } else {
            if (!this.matchesItemStack(stack2, 4, 40, true)) {
               return null;
            }

            slot2.applyItemStack(stack2, stack);
         }

         if (stack2.animationsToGo == 0) {
            slot2.putStack((ItemStack)null);
         } else {
            slot2.onSlotChanged();
         }

         if (stack2.animationsToGo == stack.animationsToGo) {
            return null;
         }

         slot2.processAction(player, stack2);
      }

      return stack;
   }

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return this.iInventory.checkEntityPlayer(player);
   }

   @Override
   public void processAction2(int count2, int count3) {
      this.iInventory.processAction(count2, count3);
   }

   @Override
   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for (int index = 0; index < this.itemList3.size(); index++) {
         CoreListener coreListener = (CoreListener)this.itemList3.get(index);
         if (this.count != this.iInventory.processAction(0)) {
            coreListener.applyContainer(this, 0, this.iInventory.processAction(0));
         }
      }

      this.count = this.iInventory.processAction(0);
   }

   @Override
   public void applyCoreListener(CoreListener coreListener) {
      super.applyCoreListener(coreListener);
      coreListener.applyContainer(this, this.iInventory);
   }

   public InternalContainerAdapter(InventoryPlayer inventoryPlayer, IInventory iInventory2) {
      this.iInventory = iInventory2;
      this.resolveSlot(new CurveProvider(inventoryPlayer.targetPlayer, iInventory2, 0, 56, 46));
      this.resolveSlot(new CurveProvider(inventoryPlayer.targetPlayer, iInventory2, 1, 79, 53));
      this.resolveSlot(new CurveProvider(inventoryPlayer.targetPlayer, iInventory2, 2, 102, 46));
      this.slot = this.resolveSlot(new CurveService(this, iInventory2, 3, 79, 17));

      for (int index = 0; index < 3; index++) {
         for (int index2 = 0; index2 < 9; index2++) {
            this.resolveSlot(new Slot(inventoryPlayer, index2 + index * 9 + 9, 8 + index2 * 18, 84 + index * 18));
         }
      }

      for (int index3 = 0; index3 < 9; index3++) {
         this.resolveSlot(new Slot(inventoryPlayer, index3, 8 + index3 * 18, 142));
      }
   }
}
