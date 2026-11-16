package client.util;

import client.event.CoreListener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.IronRing;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class SecondaryContainerAdapter extends Container {
   public final IInventory iInventory;
   public int count3;
   public int count;
   public int count4;
   public int count2;

   @Override
   public ItemStack resolveItemStack(EntityPlayer player, int count5) {
      ItemStack stack = null;
      Slot slot = (Slot)this.itemList2.get(count5);
      if (slot != null && slot.isActive()) {
         ItemStack stack2 = slot.getStack();
         stack = stack2.getItemStack();
         if (count5 == 2) {
            if (!this.matchesItemStack(stack2, 3, 39, true)) {
               return null;
            }

            slot.applyItemStack(stack2, stack);
         } else if (count5 != 1 && count5 != 0) {
            if (IronRing.getIronRing().resolveItemStack(stack2) != null) {
               if (!this.matchesItemStack(stack2, 0, 1, false)) {
                  return null;
               }
            } else if (TileEntityFurnace.matchesItemStack(stack2)) {
               if (!this.matchesItemStack(stack2, 1, 2, false)) {
                  return null;
               }
            } else if (count5 >= 3 && count5 < 30) {
               if (!this.matchesItemStack(stack2, 30, 39, false)) {
                  return null;
               }
            } else if (count5 >= 30 && count5 < 39 && !this.matchesItemStack(stack2, 3, 30, false)) {
               return null;
            }
         } else if (!this.matchesItemStack(stack2, 3, 39, false)) {
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

   @Override
   public boolean matchesEntityPlayer(EntityPlayer player) {
      return this.iInventory.checkEntityPlayer(player);
   }

   @Override
   public void processAction2(int count5, int count6) {
      this.iInventory.processAction(count5, count6);
   }

   @Override
   public void detectAndSendChanges() {
      super.detectAndSendChanges();

      for (int index = 0; index < this.itemList3.size(); index++) {
         CoreListener coreListener = (CoreListener)this.itemList3.get(index);
         if (this.count3 != this.iInventory.processAction(2)) {
            coreListener.applyContainer(this, 2, this.iInventory.processAction(2));
         }

         if (this.count4 != this.iInventory.processAction(0)) {
            coreListener.applyContainer(this, 0, this.iInventory.processAction(0));
         }

         if (this.count2 != this.iInventory.processAction(1)) {
            coreListener.applyContainer(this, 1, this.iInventory.processAction(1));
         }

         if (this.count != this.iInventory.processAction(3)) {
            coreListener.applyContainer(this, 3, this.iInventory.processAction(3));
         }
      }

      this.count3 = this.iInventory.processAction(2);
      this.count4 = this.iInventory.processAction(0);
      this.count2 = this.iInventory.processAction(1);
      this.count = this.iInventory.processAction(3);
   }

   @Override
   public void applyCoreListener(CoreListener coreListener) {
      super.applyCoreListener(coreListener);
      coreListener.applyContainer(this, this.iInventory);
   }

   public SecondaryContainerAdapter(InventoryPlayer inventoryPlayer, IInventory iInventory2) {
      this.iInventory = iInventory2;
      this.resolveSlot(new Slot(iInventory2, 0, 56, 17));
      this.resolveSlot(new AngleMatcher(iInventory2, 1, 56, 53));
      this.resolveSlot(new AngleWriter(inventoryPlayer.targetPlayer, iInventory2, 2, 116, 35));

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
